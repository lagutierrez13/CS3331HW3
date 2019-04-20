package hw3test1;

import javax.swing.*;

import javafx.scene.control.ToolBar;

import java.awt.*;
import java.net.URL;

public class PriceWatcherUI {
	
	private final Dimension dim = new Dimension(500, 900);
	private ItemViewManager itemManager = new ItemViewManager(); 
	
	public static void main(String[] args) {
		new PriceWatcherUI();
	}
	
	public PriceWatcherUI() {

		JFrame frame = this.makeFrame(this.dim);
		
	}
	
	public JFrame makeFrame(Dimension dim) {
		JFrame frame = new JFrame("Price Watcher");
		frame.setSize(dim);
		
		return frame;
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
}
