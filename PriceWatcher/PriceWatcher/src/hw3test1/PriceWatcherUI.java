package hw3test1;

import javax.swing.*;

import base.ItemView;
import javafx.scene.control.ToolBar;
import priceWatcher.Item;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		addButton = makeNavigationButton("aaaa", "Add item", "Add");
		addButton.addActionListener(new AddButtonActionListener());
		removeButton = makeNavigationButton("aaaa", "Add item", "Remove");
		removeButton.addActionListener(new RemoveButtonActionListener());
		checkButton = makeNavigationButton("aaaa", "Check item", "Check");
		checkButton.addActionListener(new CheckButtonActionListener());
		editButton = makeNavigationButton("aaaa", "Edit item", "Edit");
		editButton.addActionListener(new EditButtonActionListener());

		// toolbar
		JToolBar toolBar = makeToolBar();

		toolBar.add(addButton);
		toolBar.add(checkButton);
		toolBar.add(removeButton);
		toolBar.add(editButton);

		// list
		list = new JList(itemManager.itemViews);

		frame.add(toolBar, BorderLayout.NORTH);
		frame.add(list, BorderLayout.CENTER);
		frame.show();
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
	protected JButton makeNavigationButton(String image, String toolTipText, String altText) {
		String imageLocation = image + ".png";

		URL imageURL = ToolBar.class.getResource(imageLocation);

		JButton button = new JButton();
		// button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);

		if (imageURL != null) {
			button.setIcon(new ImageIcon(imageURL, altText));
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
			Object[] addFields = { "Name: ", nameTextField, "Price: ", priceTextField, "URL: ", urlTextField, };
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

	class EditButtonActionListener implements ActionListener {
		private JTextField nameTextField = new JTextField();
		private JTextField priceTextField = new JTextField();
		private JTextField urlTextField = new JTextField();

		@Override
		public void actionPerformed(ActionEvent e) {
			// get item to edit
			ListSelectionModel lsm = list.getSelectionModel();
			int selected = lsm.getMinSelectionIndex();

			ItemView itemView = (ItemView) list.getModel().getElementAt(selected);

			// change item
			JOptionPane options = new JOptionPane();
			Object[] addFields = { "Name: ", nameTextField, "Price: ", priceTextField, "URL: ", urlTextField, };
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
}
