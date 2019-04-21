package hw3;
import hw3test1.ItemViewManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import base.*;
import javafx.scene.control.ToolBar;
import java.net.*;

public class PriceWatcherUI extends JPanel implements ActionListener {
	ItemViewManager manager = new ItemViewManager();
	
	public static void main(String[] args) {
		new PriceWatcherUI();
	}

	public PriceWatcherUI() {
		JFrame frame = new JFrame("Price Watcher");

		// dimension
		Dimension dim = new Dimension(500, 900);

		// jframe
		frame.setSize(dim);
		frame.setLayout(new BorderLayout());

		// buttons
		JButton addButton = null;
		JButton checkButton = null;
		JButton removeButton = null;
		JButton editButton = null;

		addButton = makeNavigationButton("aaaa", "Add item", "Add");
		checkButton = makeNavigationButton("aaaa", "Check item", "Check");
		removeButton = makeNavigationButton("aaaa", "Remove item", "Remove");
		editButton = makeNavigationButton("aaaa", "Edit item", "Edit");
		

		// toolbar
		JToolBar toolbar = new JToolBar("Price Watcher");
		toolbar.add(addButton);
		toolbar.add(checkButton);
		toolbar.add(removeButton);
		toolbar.add(editButton);

		toolbar.setPreferredSize(new Dimension(100, 70));
		frame.add(toolbar, BorderLayout.NORTH);
		frame.add(new JLabel("Hello"), BorderLayout.SOUTH);

		// list
		ItemView item1 = new ItemView();
		ItemView item2 = new ItemView();
		
		addButton.addActionListener(new AddButtonListener());
//		DefaultListModel listModel = new DefaultListModel();
//		listModel.addElement(item1);
//		listModel.addElement(item2);
		
		this.manager.addItem(item1);
		JList list = new JList(this.manager.getItemViews());

//		// table
//		JTable table = new JTable(new ItemViewManager(list));
//		table.setDefaultRenderer(ItemView.class, new ItemView());
//		table.setRowHeight(200);
//		table.getTableHeader().setVisible(false);
//		table.add(item1, 0);
//		frame.add(new JScrollPane(table));
//		// frame.add(table);
//		// frame.add(table, BorderLayout.CENTER);
		frame.add(list, BorderLayout.CENTER);
		frame.show();

	}

	protected JButton makeNavigationButton(String image, String toolTipText, String altText) {
		String imageLocation = image + ".png";

		URL imageURL = ToolBar.class.getResource(imageLocation);

		JButton button = new JButton();
		// button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.addActionListener(this);

		if (imageURL != null) {
			button.setIcon(new ImageIcon(imageURL, altText));
		} else {
			button.setText(altText);
			System.out.println("Resource not found");
		}
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	
	class AddButtonListener implements ActionListener{
		@Override
		public void ActionPerformed(ActionEvent e) {
			
		}

		
	}
	/*
	 * // cambiar y hacer clase y action performed para cada boton class
	 * AddButtonListener implements ActionListener { public void
	 * actionPerformed(ActionEvent e) { if (nameField.getText().equals("")) { //
	 * User didn't type in a name... Toolkit.getDefaultToolkit().beep(); return; }
	 * 
	 * int index = list.getSelectedIndex(); int size = listModel.getSize();
	 * 
	 * // If no selection or if item in last position is selected, // add the new
	 * one to end of list, and select new one. if (index == -1 || (index + 1 ==
	 * size)) { listModel.addElement(nameField.getText());
	 * list.setSelectedIndex(size);
	 * 
	 * // Otherwise insert the new one after the current selection, // and select
	 * new one. } else { listModel.insertElementAt(nameField.getText(), index + 1);
	 * list.setSelectedIndex(index + 1); } } }
	 * 
	 * class DeleteButtonListener implements ActionListener { public void
	 * actionPerformed(ActionEvent e) { /* This method can be called only if there's
	 * a valid selection, so go ahead and remove whatever's selected.
	 */
	/*
	 * ListSelectionModel lsm = list.getSelectionModel(); int firstSelected =
	 * lsm.getMinSelectionIndex(); int lastSelected = lsm.getMaxSelectionIndex();
	 * listModel.removeRange(firstSelected, lastSelected);
	 * 
	 * int size = listModel.size();
	 * 
	 * if (size == 0) { // List is empty: disable delete, up, and down buttons.
	 * deleteButton.setEnabled(false); upButton.setEnabled(false);
	 * downButton.setEnabled(false);
	 * 
	 * } else { // Adjust the selection. if (firstSelected == listModel.getSize()) {
	 * // Removed item in last position. firstSelected--; }
	 * list.setSelectedIndex(firstSelected); } } }
	 */
}