/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
*
* The class {@code PropertyNotify} defines a availability notification in simple property format.
*  
* since a Simple*Property cannot be sent in the socket easily I created this class,
*  which always represents notification, to show in notification table javaFX.
*  
**/

public class PropertyNotify 
{

	private SimpleIntegerProperty quantity;
	private SimpleStringProperty wineName;
	private SimpleIntegerProperty wineYear;
	
	/**
	 * Class constructor.
	 *
	 * @param q the notification wine quantity.
	 * @param n the notification wine name.
	 * @param y the notification wine year.
	 *
	**/
	public PropertyNotify(int q, String n, int y)
	{
		this.quantity = new SimpleIntegerProperty(q);
		this.wineName = new SimpleStringProperty(n);
		this.wineYear = new SimpleIntegerProperty(y);
	}
	
	/**
	 * Gets the notification wine quantity.
	 *
	 * @return notification wine quantity.
	 *
	**/
	public int getQuantity()
	{	
		return this.quantity.get();
	}
	
	/**
	 * Gets the notification wine name.
	 *
	 * @return notification wine name.
	 *
	**/
	public String getWineName()
	{
		return this.wineName.get();
	}
	
	/**
	 * Gets the notification wine year.
	 *
	 * @return notification wine year.
	 *
	**/
	public int getWineYear()
	{
		return this.wineYear.get();
	}

}
