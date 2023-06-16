/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.Serializable;

/**
 *
 * The class {@code WineOrder} defines a wine order, 
 * all customers proposals/orders contain one or more wine order.
 *
**/

public class WineOrder implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String wineName;
	private int wineYear;
	private int quantity;
	private double price;
	
	/**
	 * Class constructor.
	 *
	 * @param n the wine name.
	 * @param y the wine year.
	 * @param p the wine price.
	 * @param qt the wine quantity.
	 *
	**/
	public WineOrder(String n, int y, int q, double p)
	{
		this.wineName = n;
		this.wineYear = y;
		this.quantity = q;
		this.price = p;
	}
	
	/**
	 * Gets the wine name.
	 *
	 * @return wine name.
	 *
	**/
	public String getWineName()
	{
		return this.wineName;
	}
	
	/**
	 * Gets the wine year.
	 *
	 * @return wine year.
	 *
	**/
	public int getWineYear()
	{
		return this.wineYear;
	}
	
	/**
	 * Gets the wine quantity.
	 *
	 * @return wine quantity.
	 *
	**/
	public int getQuantity()
	{
		return this.quantity;
	}
	
	/**
	 * Gets the wine price.
	 *
	 * @return wine price.
	 *
	**/
	public double getPrice()
	{
		return this.price;
	}
}
