package hw3test1;
import base.ItemView;
import priceWatcher.Item;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class ItemViewManager {
	

	
	DefaultListModel<ItemView> listModel;
		
	public ItemViewManager(DefaultListModel<ItemView> itemViews) {
		this.listModel = itemViews;
	}

	public ItemViewManager() {
		this.listModel = new DefaultListModel<ItemView>();
	}

	public void addItem(ItemView item) {
		this.listModel.addElement(item);
	}
	
	public void removeItem(int index) {
		this.listModel.removeElementAt(index);
	}
	
	
	public DefaultListModel<ItemView> getItemViews(){
		return this.listModel;
	}
	

//	public void editItem(int index) {
//		ItemView itemToEdit = this.itemViews.getElementAt(index);
//	}
	
		
//	@Override
//	public int getColumnCount() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int getRowCount() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public Object getValueAt(int arg0, int arg1) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	
}
