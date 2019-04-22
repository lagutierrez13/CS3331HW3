package hw3test1;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import base.ItemView;

public class ItemRenderer extends JPanel implements ListCellRenderer<ItemView>{

	public ItemRenderer() {
        setOpaque(true);
    }
	
	@Override
	public Component getListCellRendererComponent(JList<? extends ItemView> list, ItemView view, int index, boolean isSelected,
			boolean cellHasFocus) {
		// TODO Auto-generated method stub
		
		
		if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
		
		return view;
	}
	

}
