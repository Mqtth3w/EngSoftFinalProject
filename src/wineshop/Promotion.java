/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.Serializable;
import java.time.LocalDate;

/**
*
* The class {@code Promotion} defines a promotion of a wine.
*
**/

public class Promotion implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private LocalDate inday;
	private LocalDate finday;
	private int discount;
	private String wineName;
	private int wineYear;
	
	/**
	 * Class constructor.
	 *
	 * @param i the promotion start date.
	 * @param f the promotion end date.
	 * @param d the promotion discount.
	 * @param n the promotion wine name.
	 * @param y the promotion wine year.
	 *
	**/
	public Promotion(LocalDate i, LocalDate f, int d, String n, int y)
	{
		this.inday = i;
		this.finday = f;
		this.discount = d;
		this.wineName = n;
		this.wineYear = y;
	}
	
	/**
	 * Gets the promotion start date.
	 *
	 * @return promotion start date.
	 *
	**/
	public LocalDate getInday()
	{
		return this.inday;
	}
	
	/**
	 * Gets the promotion end date.
	 *
	 * @return promotion end date.
	 *
	**/
	public LocalDate getFinday()
	{
		return this.finday;
	}
	
	/**
	 * Gets the promotion discount.
	 *
	 * @return promotion discount.
	 *
	**/
	public int getDiscount()
	{	
		return this.discount;
	}
	
	/**
	 * Gets the promotion wine name.
	 *
	 * @return promotion wine name.
	 *
	**/
	public String getWineName()
	{
		return this.wineName;
	}
	
	/**
	 * Gets the promotion wine year.
	 *
	 * @return promotion wine year.
	 *
	**/
	public int getWineYear()
	{
		return this.wineYear;
	}
	
}
