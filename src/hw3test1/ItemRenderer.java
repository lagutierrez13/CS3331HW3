/*
 * CS 3331 - Dr. Cheon
 * Created by: Alejandra Maciel, Luis Gutierrez & Victor Huicochea
 * Last Edit Date: 04/21/2019
 */

package hw3test1;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import base.ItemView;

public class ItemRenderer extends JPanel implements ListCellRenderer<ItemView>{

	public ItemRenderer() {
        this.setOpaque(true);
    }
	
	@Override
	public Component getListCellRendererComponent(JList<? extends ItemView> list, ItemView view, int index, boolean isSelected,
			boolean cellHasFocus) {		
		
		if (isSelected) {
            view.setBackground(list.getSelectionBackground());
            view.setForeground(list.getSelectionForeground());
        } else {
            view.setBackground(list.getBackground());
            view.setForeground(list.getForeground());
        }
		
		return view;
	}
	

}
