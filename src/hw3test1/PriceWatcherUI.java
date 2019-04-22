package hw3test1;

import javax.swing.*;

import base.ItemView;
import javafx.scene.control.ToolBar;
import priceWatcher.Item;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

public class PriceWatcherUI {

	private final Dimension dim = new Dimension(500, 900);
	private ItemViewManager itemManager = new ItemViewManager();
	private JButton addButton;
	private JButton removeButton;
	private JButton checkButton;
	private JButton editButton;
	private JList list;

	public static void main(String[] args) {
		new PriceWatcherUI();
	}

	public PriceWatcherUI() {

		// frame
		JFrame frame = this.makeFrame(this.dim);

		// buttons
		addButton = makeNavigationButton("/image/add.png", "Add item", "Add");
		addButton.addActionListener(new AddButtonActionListener());
		removeButton = makeNavigationButton("/image/remove.png", "Remove item", "Remove");
		removeButton.addActionListener(new RemoveButtonActionListener());
		checkButton = makeNavigationButton("/image/check.png", "Check item", "Check");
		checkButton.addActionListener(new CheckButtonActionListener());
		editButton = makeNavigationButton("/image/edit.png", "Edit item", "Edit");
		editButton.addActionListener(new EditButtonActionListener());

		// toolbar
		JToolBar toolBar = makeToolBar();

		toolBar.add(addButton);
		toolBar.add(checkButton);
		toolBar.add(removeButton);
		toolBar.add(editButton);

		// menu
		JMenuBar menu = makeMenu();

		// list
		list = new JList(itemManager.itemViews);
		list.setCellRenderer(new ItemRenderer());
		JScrollPane scrollPane = new JScrollPane(list);

		frame.add(toolBar, BorderLayout.NORTH);
		frame.setJMenuBar(menu);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.show();
	}

	private JMenuBar makeMenu() {

		JMenuBar menuBar = new JMenuBar();

		// Build first menu
		JMenu menu = new JMenu("Item");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);

		// Add
		ImageIcon icon = createImageIcon("/image/add.png");
		Image scaled = scaleImage(icon.getImage(), 10, 10);
		ImageIcon scaledIcon = new ImageIcon(scaled);

		JMenuItem menuItem = new JMenuItem("Add Item", scaledIcon);
		menuItem.setMnemonic(KeyEvent.VK_A);
		menuItem.addActionListener(new AddButtonActionListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
		menu.add(menuItem);

		// Remove
		icon = createImageIcon("/image/remove.png");
		scaled = scaleImage(icon.getImage(), 10, 10);
		scaledIcon = new ImageIcon(scaled);

		menuItem = new JMenuItem("Remove Item", scaledIcon);
		menuItem.setMnemonic(KeyEvent.VK_R);
		menuItem.addActionListener(new RemoveButtonActionListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		menu.add(menuItem);

		// Check Prices
		icon = createImageIcon("/image/check.png");
		scaled = scaleImage(icon.getImage(), 10, 10);
		scaledIcon = new ImageIcon(scaled);

		menuItem = new JMenuItem("Check Prices", scaledIcon);
		menuItem.setMnemonic(KeyEvent.VK_C);
		menuItem.addActionListener(new CheckButtonActionListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		menu.add(menuItem);

		// Edit
		icon = createImageIcon("/image/edit.png");
		scaled = scaleImage(icon.getImage(), 10, 10);
		scaledIcon = new ImageIcon(scaled);

		menuItem = new JMenuItem("Edit Item", scaledIcon);
		menuItem.setMnemonic(KeyEvent.VK_E);
		menuItem.addActionListener(new EditButtonActionListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menu.add(menuItem);

		return menuBar;
	}

	private Image scaleImage(Image image, int w, int h) {
		Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);

		return scaled;
	}

	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	// make frame
	public JFrame makeFrame(Dimension dim) {
		JFrame frame = new JFrame("Price Watcher");
		frame.setSize(dim);
		frame.setLayout(new BorderLayout());

		return frame;
	}

	// make toolbar
	public JToolBar makeToolBar() {
		JToolBar toolBar = new JToolBar("Price Watcher");
		toolBar.setPreferredSize(new Dimension(100, 70));

		return toolBar;
	}

	// make toolbar buttons
	protected JButton makeNavigationButton(String path, String toolTipText, String altText) {
		// String imageLocation = image + ".png";
		java.net.URL imgURL = getClass().getResource(path);

		// URL imageURL = ToolBar.class.getResource(imageLocation);

		JButton button = new JButton();
		// button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);

		ImageIcon icon = createImageIcon(path);
		Image scaled = scaleImage(icon.getImage(), 20, 20);
		ImageIcon scaledIcon = new ImageIcon(scaled);

		if (imgURL != null) {
			button.setIcon(scaledIcon);
		} else {
			button.setText(altText);
			System.out.println("Resource not found");
		}
		return button;
	}

	// action listeners for button
	class AddButtonActionListener implements ActionListener {
		private JTextField nameTextField = new JTextField();
		private JTextField priceTextField = new JTextField();
		private JTextField urlTextField = new JTextField();

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane options = new JOptionPane();
			Object[] addFields = { "Name: ", nameTextField, "Price: ", priceTextField, "URL: ", urlTextField };

			int option = JOptionPane.showConfirmDialog(null, addFields, "Add item", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.OK_OPTION) {
				String name = nameTextField.getText();
				String price = priceTextField.getText();
				String url = urlTextField.getText();

				double doublePrice = Double.parseDouble(price);

				itemManager.addItem(new ItemView(new Item(name, doublePrice, url)));

				// clear text fields
				nameTextField.setText("");
				priceTextField.setText("");
				urlTextField.setText("");
			}
		}
	}

	class CheckButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int size = itemManager.itemViews.size();

			for (int i = 0; i < size; i++) {
				itemManager.getItemViews().getElementAt(i).getItem().setRandomPrice();
				itemManager.getItemViews().getElementAt(i).getItem().setChange();
			}
		}
	}

	class RemoveButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ListSelectionModel lsm = list.getSelectionModel();
			int firstSelected = lsm.getMinSelectionIndex();
			int lastSelected = lsm.getMaxSelectionIndex();
			itemManager.itemViews.removeRange(firstSelected, lastSelected);

			int size = itemManager.itemViews.size();

			if (size == 0) {

				// List is empty: disable delete, up, and down buttons.
				removeButton.setEnabled(false);
				// upButton.setEnabled(false);
				// downButton.setEnabled(false);

			} else {
				// Adjust the selection.
				if (firstSelected == itemManager.itemViews.getSize()) {
					// Removed item in last position.
					firstSelected--;
				}
				list.setSelectedIndex(firstSelected);
			}
		}
	}

	class IndividualCheckButtonActionListener implements ActionListener {
		ListSelectionModel lsm = list.getSelectionModel();
		int selected = lsm.getMinSelectionIndex();

		@Override
		public void actionPerformed(ActionEvent e) {
			itemManager.getItemViews().getElementAt(selected).getItem().setRandomPrice();
			itemManager.getItemViews().getElementAt(selected).getItem().setChange();
		}
	}

	class EditButtonActionListener implements ActionListener {
		private JTextField nameTextField = new JTextField();
		private JTextField priceTextField = new JTextField();
		private JTextField urlTextField = new JTextField();
		private JButton checkPriceButton = new JButton("Check Price");
		private JButton sitePageButton = new JButton("Go to site");

		@Override
		public void actionPerformed(ActionEvent e) {

			// get item to edit
			ListSelectionModel lsm = list.getSelectionModel();
			int selected = lsm.getMinSelectionIndex();

			ItemView itemView = (ItemView) list.getModel().getElementAt(selected);

			// change item
			checkPriceButton.addActionListener(new IndividualCheckButtonActionListener());
			sitePageButton.addActionListener(new SiteButtonActionListener());

			JOptionPane options = new JOptionPane();
			Object[] addFields = { "Name: ", nameTextField, "Price: ", priceTextField, "URL: ", urlTextField,
					checkPriceButton, sitePageButton };

			nameTextField.setText(itemManager.getItemViews().getElementAt(selected).getItem().getName());
			priceTextField
					.setText(Double.toString(itemManager.getItemViews().getElementAt(selected).getItem().getPrice()));
			urlTextField.setText(itemManager.getItemViews().getElementAt(selected).getItem().getUrl());

			int option = JOptionPane.showConfirmDialog(null, addFields, "Edit item", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.OK_OPTION) {
				String name = nameTextField.getText();
				String price = priceTextField.getText();
				String url = urlTextField.getText();

				double doublePrice = Double.parseDouble(price);

				itemView.getItem().setName(name);
				itemView.getItem().setCurrentPrice(doublePrice);
				itemView.getItem().setUrl(url);

				// clear text fields
				nameTextField.setText("");
				priceTextField.setText("");
				urlTextField.setText("");
			}
		}
	}

	class SiteButtonActionListener implements ActionListener {
		ListSelectionModel lsm = list.getSelectionModel();
		int selected = lsm.getMinSelectionIndex();

		@Override
		public void actionPerformed(ActionEvent arg0) {
			itemManager.getItemViews().getElementAt(selected).getItem().launchBrowser();
		}

	}
}
