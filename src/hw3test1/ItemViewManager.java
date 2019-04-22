/*
 * CS 3331 - Dr. Cheon
 * Created by: Alejandra Maciel, Luis Gutierrez & Victor Huicochea
 * Last Edit Date: 04/21/2019
 */

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
	

}
