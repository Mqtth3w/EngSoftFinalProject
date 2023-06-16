/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.Serializable;

/**
 *
 * The class {@code WineReport} defines monthly wine report info.
 *
**/

public class WineReport implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String wineName;
	private int wineYear;
	private int numSell;
	private int numAv;
	private double income;
	
	/**
	 * Class constructor.
	 *
	 * @param n the wine name.
	 * @param y the wine year.
	 * @param s the wine monthly number of sales.
	 * @param a the wine monthly number available.
	 * @param i the wine monthly income.
	 *
	**/
	public WineReport(String n, int y, int s, int a, double i)
	{
		this.wineName = n;
		this.wineYear = y;
		this.numSell = s;
		this.numAv = a;
		this.income = i;
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
	 * Gets the wine monthly sales number.
	 *
	 * @return wine monthly sales number.
	 *
	**/
	public int getNumSell()
	{
		return this.numSell;
	}
	
	/**
	 * Gets the wine monthly available.
	 *
	 * @return wine monthly available.
	 *
	**/
	public int getNumAv()
	{
		return this.numAv;
	}
	
	/**
	 * Gets the wine monthly income.
	 *
	 * @return wine monthly income.
	 *
	**/
	public double getIncome()
	{
		return this.income;
	}
}
