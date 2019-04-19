package hw3test1;
import base.ItemView;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class ItemViewManager extends AbstractTableModel {
	DefaultListModel<ItemView> itemViews;
	
	public ItemViewManager(DefaultListModel<ItemView> itemViews) {
		this.itemViews = itemViews;
	}

	public ItemViewManager() {
		this.itemViews = new DefaultListModel<ItemView>();
	}

	public void addItem(ItemView item) {
		this.itemViews.addElement(item);
	}
	
	public void removeItem(int index) {
		this.itemViews.removeElementAt(index);
	}
	
	
	public DefaultListModel<ItemView> getItemViews(){
		return this.itemViews;
	}
	
//	public void editItem(int index) {
//		ItemView itemToEdit = this.itemViews.getElementAt(index);
//	}
	
		
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
