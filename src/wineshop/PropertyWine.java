/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
*
* The class {@code PropertyWine} defines a wine in simple property format.
* 
* since a Simple*Property cannot be sent in the socket easily I created this class,
*  which always represents wine, to show the wines in the javaFX table.
*
**/

public class PropertyWine 
{
	private SimpleStringProperty name;
	private SimpleStringProperty productor;
	private SimpleStringProperty origin;
	private SimpleIntegerProperty year;
	private SimpleStringProperty TechnicalNotes;
	private SimpleStringProperty GrapeVariety; 
	private SimpleDoubleProperty quality; 
	private SimpleDoubleProperty price;
	private SimpleDoubleProperty supplierPrice;
	private SimpleIntegerProperty quantity;
	private SimpleIntegerProperty SalesNumber;
	
	/**
	 * Class constructor.
	 *
	 * @param n the wine name.
	 * @param pr the wine productor.
	 * @param o the wine origin.
	 * @param y the wine year.
	 * @param tn the wine technical notes.
	 * @param gp the wine grape variety.
	 * @param q the wine quality.
	 * @param p the wine price.
	 * @param sp the wine supplier price.
	 * @param qt the wine quantity.
	 * @param sn the wine sales number.
	 *
	**/
	public PropertyWine(String n, String pr, String o, int y, String tn, String gp, double q, double p, double sp, int qt, int sn)
	{
		this.name = new SimpleStringProperty(n);
		this.productor = new SimpleStringProperty(pr);
		this.origin = new SimpleStringProperty(o);
		this.year = new SimpleIntegerProperty(y);
		this.TechnicalNotes = new SimpleStringProperty(tn);
		this.GrapeVariety = new SimpleStringProperty(gp);
		this.quality = new SimpleDoubleProperty(q);
		this.price = new SimpleDoubleProperty(p);
		this.supplierPrice = new SimpleDoubleProperty(sp);
		this.quantity = new SimpleIntegerProperty(qt);
		this.SalesNumber = new SimpleIntegerProperty(sn);
	}
	
	/**
	 * Gets the wine name.
	 *
	 * @return wine name.
	 *
	**/
	public String getName()
	{
		return this.name.get();
	}	
	
	/**
	 * Gets the wine productor.
	 *
	 * @return wine productor.
	 *
	**/
	public String getProductor()
	{
		return this.productor.get();
	}

	/**
	 * Gets the wine origin.
	 *
	 * @return wine origin.
	 *
	**/
	public String getOrigin()
	{
		return this.origin.get();
	}
	
	/**
	 * Gets the wine year.
	 *
	 * @return wine year.
	 *
	**/
	public int getYear()
	{
		return this.year.get();
	}
	
	/**
	 * Gets the wine technical notes.
	 *
	 * @return wine technical notes.
	 *
	**/
	public String getTechnicalNotes()
	{
		return this.TechnicalNotes.get();
	}
	
	/**
	 * Gets the wine grape variety.
	 *
	 * @return wine grape variety.
	 *
	**/
	public String getGrapeVariety()
	{
		return this.GrapeVariety.get();
	}
	
	/**
	 * Gets the wine quality.
	 *
	 * @return wine quality.
	 *
	**/
	public double getQuality()
	{
		return this.quality.get();
	}
	
	/**
	 * Gets the wine price.
	 *
	 * @return wine price.
	 *
	**/
	public double getPrice()
	{
		return this.price.get();
	}

	/**
	 * Gets the wine supplier price.
	 *
	 * @return wine supplier price.
	 *
	**/
	public double getSupplierPrice()
	{
		return this.supplierPrice.get();
	}
	
	/**
	 * Gets the wine quantity.
	 *
	 * @return wine quantity.
	 *
	**/
	public int getQuantity()
	{
		return this.quantity.get();
	}
	
	/**
	 * Gets the wine sales number.
	 *
	 * @return wine sales number.
	 *
	**/
	public int getSalesNumber()
	{
		return this.SalesNumber.get();
	}
	
}
