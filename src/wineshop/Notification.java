/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.Serializable;

/**
*
* The class {@code Notification} defines a availability notification that customer ask/receive.
*
**/

public class Notification implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int quantity;
	private String wineName;
	private int wineYear;
	private String custEmail;
	
	/**
	 * Class constructor.
	 *
	 * @param q the wine quantity.
	 * @param n the wine name.
	 * @param y the wine year.
	 * @param c the customer email.
	 *
	**/
	public Notification(int q, String n, int y, String c)
	{
		this.quantity = q;
		this.wineName = n;
		this.wineYear = y;
		this.custEmail = c;
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
	 * Gets the customer email.
	 *
	 * @return customer email.
	 *
	**/
	public String getCustEmail()
	{
		return this.custEmail;
	}
	
}
