package priceWatcher;

import java.util.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.*;

public class Item {
	private String name;
	private double initPrice;
	private double currentPrice;
	private String url;
	private double change;
	private String dateAdded;

	// default constructor
	public Item() {
		this.name = "";
		this.initPrice = 0;
		this.currentPrice = 0;
		this.url = "";
		this.change = 0;
		this.dateAdded = "";
	}
	
	public Item(String name, double initPrice, String url) {
		this.name = name;
		this.initPrice = initPrice;
		this.currentPrice = initPrice;
		this.url = url;
		calculateChange();
		this.dateAdded = calculateDate();
	}

	public Item(String name, double initPrice, double currentPrice, String url) {
		this.name = name;
		this.initPrice = initPrice;
		this.currentPrice = currentPrice;
		this.url = url;
		calculateChange();
		this.dateAdded = calculateDate();
	}
	
	public void launchBrowser() {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(this.url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String calculateDate() {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		String formattedDate = dateFormat.format(date);

		return formattedDate;
	}

	private void calculateChange() {
		// TODO Auto-generated method stub
		// get the current price from the site and calculate the change
		double change = 0;

		if (this.initPrice == 0) {
			change = 0;
		}

		change =  ((this.currentPrice * 100) / this.initPrice) - 100;

		this.change = Math.round(change);
	}

	public void updatePrice() {
		// TODO Auto-generated method stub
		this.setRandomPrice();
		this.calculateChange();

	}

	// getters
	public String getName() {
		return this.name;
	}

	public double getInitPrice() {
		return this.initPrice;
	}

	public double getPrice() {
		return this.currentPrice;
	}

	public String getUrl() {
		return this.url;
	}

	public double getChange() {
		return this.change;
	}

	public String getDateAdded() {
		return this.dateAdded;
	}

	// setters
	public void setRandomPrice() {
		this.currentPrice = Math.round((Math.random() * (this.initPrice * 2)) + (this.initPrice * .5));
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setInitPrice(double price) {
		this.initPrice = price;
	}
	
	public void setCurrentPrice(double price) {
		this.currentPrice = price;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setChange() {
		calculateChange();
	}
	

}
