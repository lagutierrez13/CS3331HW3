package hw3;

import java.awt.*;

import javax.swing.JList;
import javax.swing.table.AbstractTableModel;

public class ItemViewManager extends AbstractTableModel {

	JList itemViews;
	
	public ItemViewManager(JList list) {
		this.itemViews = list;
	}
	
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
