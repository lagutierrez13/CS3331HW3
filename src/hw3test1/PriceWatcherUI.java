/*
 * CS 3331 - Dr. Cheon
 * Created by: Alejandra Maciel, Luis Gutierrez & Victor Huicochea
 * Last Edit Date: 04/21/2019
 */

package hw3test1;

import javax.swing.*;

import base.ItemView;
import javafx.scene.control.ToolBar;
import priceWatcher.Item;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;

public class PriceWatcherUI {

	private final Dimension dim = new Dimension(500, 900);
	private ItemViewManager itemManager = new ItemViewManager();
	private JButton addButton;
	private JButton removeButton;
	private JButton checkButton;
	private JButton editButton;
	private JList list;
	private JPopupMenu popup;
	private MouseListener popupListener = new PopupListener();

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
		
		// popUp menu
		popup = getPopupMenu();
		
		// list
		JList<ItemView> views = new JList<>(itemManager.listModel);
		list = views;
		
		
		frame.add(toolBar, BorderLayout.NORTH);
		frame.setJMenuBar(menu);
		frame.add(new JScrollPane(list), BorderLayout.CENTER);
		list.addMouseListener(popupListener);
		list.setCellRenderer(new ItemRenderer());
		frame.show();
	}

	
	private JPopupMenu getPopupMenu() {
		//Create the popup menu.
	    JPopupMenu popup = new JPopupMenu();
	    // Add 
	 	ImageIcon icon = createImageIcon("/image/add.png");
	 	Image scaled = scaleImage(icon.getImage(), 10, 10);
	    ImageIcon scaledIcon = new ImageIcon(scaled);
	    
	    JMenuItem menuItem = new JMenuItem("Add Item", scaledIcon);
	    
	    menuItem.addActionListener(new AddButtonActionListener());

	    popup.add(menuItem);
	    
	    // Remove 
	 	icon = createImageIcon("/image/remove.png");
	 	scaled = scaleImage(icon.getImage(), 10, 10);
	    scaledIcon = new ImageIcon(scaled);
	    
	    menuItem = new JMenuItem("Remove Item", scaledIcon);
	    
	    menuItem.addActionListener(new RemoveButtonActionListener());

	    popup.add(menuItem);
	    
	    // Check 
	 	icon = createImageIcon("/image/check.png");
	 	scaled = scaleImage(icon.getImage(), 10, 10);
	    scaledIcon = new ImageIcon(scaled);
	    
	    menuItem = new JMenuItem("Check Price", scaledIcon);
	    
	    menuItem.addActionListener(new CheckButtonActionListener());

	    popup.add(menuItem);
	    
	    // Edit 
	 	icon = createImageIcon("/image/edit.png");
	 	scaled = scaleImage(icon.getImage(), 10, 10);
	    scaledIcon = new ImageIcon(scaled);
	    
	    menuItem = new JMenuItem("Edit Item", scaledIcon);
	    
	    menuItem.addActionListener(new EditButtonActionListener());

	    popup.add(menuItem);

		return popup;
	}

	private JMenuBar makeMenu() {
		
		JMenuBar menuBar = new JMenuBar();
		
		//Build first menu
		JMenu menu = new JMenu("Item");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
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
		java.net.URL imgURL = getClass().getResource(path);
		
		JButton button = new JButton();
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

	// Mouse Listener for popup menu
	class PopupListener extends MouseAdapter {
	    public void mousePressed(MouseEvent e) {
	        maybeShowPopup(e);
	    }

	    public void mouseReleased(MouseEvent e) {
	        maybeShowPopup(e);
	    	
	    }

	    private void maybeShowPopup(MouseEvent e) {
	        if (e.isPopupTrigger()) {
	            popup.show(e.getComponent(),
	                       e.getX(), e.getY());
	        }
	    }
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
				
				ItemView toAdd = new ItemView(new Item(name, doublePrice, url));
				
				itemManager.addItem(toAdd);
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
			int size = itemManager.listModel.size();

			for (int i = 0; i < size; i++) {
				itemManager.getItemViews().getElementAt(i).getItem().updatePrice();
			}
		}
	}

	class RemoveButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ListSelectionModel lsm = list.getSelectionModel();
			int firstSelected = lsm.getMinSelectionIndex();
			int lastSelected = lsm.getMaxSelectionIndex();
			itemManager.listModel.removeRange(firstSelected, lastSelected);

			int size = itemManager.listModel.size();

			// Adjust the selection.
			if (firstSelected == itemManager.listModel.getSize()) {
				// Removed item in last position.
				firstSelected--;
			}
			list.setSelectedIndex(firstSelected);
			
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
