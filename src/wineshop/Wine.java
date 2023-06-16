/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.Serializable;

/**
 *
 * The class {@code Wine} defines the wine.
 * 
**/

public class Wine implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String name;
	private String productor;
	private String origin;
	private int year;
	private String TechnicalNotes;
	private String GrapeVariety; 
	private double quality; 
	private double price;
	private double supplierPrice;
	private int quantity;
	private int SalesNumber;
	
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
	public Wine(String n, String pr, String o, int y, String tn, String gp, double q, double p, double sp, int qt, int sn)
	{
		this.name = n;
		this.productor = pr;
		this.origin = o;
		this.year = y;
		this.TechnicalNotes = tn;
		this.GrapeVariety = gp;
		this.quality = q;
		this.price = p;
		this.supplierPrice = sp;
		this.quantity = qt;
		this.SalesNumber = sn;
	}
	
	/**
	 * Gets the wine name.
	 *
	 * @return wine name.
	 *
	**/
	String getName()
	{
		return this.name;
	}
	
	/**
	 * Gets the wine productor.
	 *
	 * @return wine productor.
	 *
	**/
	String getProductor()
	{
		return this.productor;
	}
	
	/**
	 * Gets the wine origin.
	 *
	 * @return wine origin.
	 *
	**/
	String getOrigin()
	{
		return this.origin;
	}

	/**
	 * Gets the wine year.
	 *
	 * @return wine year.
	 *
	**/
	int getYear()
	{
		return this.year;
	}
	
	/**
	 * Gets the wine technical notes.
	 *
	 * @return wine technical notes.
	 *
	**/
	String getTechnicalNotes()
	{
		return this.TechnicalNotes;
	}
	
	/**
	 * Gets the wine grape variety.
	 *
	 * @return wine grape variety.
	 *
	**/
	String getGrapeVariety()
	{
		return this.GrapeVariety;
	}
	
	/**
	 * Gets the wine quality.
	 *
	 * @return wine quality.
	 *
	**/
	double getQuality()
	{
		return this.quality;
	}
	
	/**
	 * Gets the wine price.
	 *
	 * @return wine price.
	 *
	**/
	double getPrice()
	{
		return this.price;
	}
	
	/**
	 * Gets the wine supplier price.
	 *
	 * @return wine supplier price.
	 *
	**/
	double getSupplierPrice()
	{
		return this.supplierPrice;
	}
	
	/**
	 * Gets the wine quantity.
	 *
	 * @return wine quantity.
	 *
	**/
	int getQuantity()
	{
		return this.quantity;
	}

	/**
	 * Gets the wine sales number.
	 *
	 * @return wine sales number.
	 *
	**/
	int getSalesNumber()
	{
		return this.SalesNumber;
	}

}
