/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
*
* The class {@code PropertyWineReport} defines a wine report info in simple property format.
* 
* since a Simple*Property cannot be sent in the socket easily I created this class,
*  which always represents wine report info, to show in report table javaFX.
*
**/

public class PropertyWineReport 
{

	private SimpleStringProperty wineName;
	private SimpleIntegerProperty wineYear;
	private SimpleIntegerProperty numSell;
	private SimpleIntegerProperty numAv;
	private SimpleDoubleProperty income;
	
	/**
	 * Class constructor.
	 *
	 * @param n the wine name.
	 * @param y the wine year.
	 * @param s the wine monthly number of sales.
	 * @param a the wine monthly available.
	 * @param i the wine monthly income.
	 *
	**/
	public PropertyWineReport(String n, int y, int s, int a, double i)
	{
		this.wineName = new SimpleStringProperty(n);
		this.wineYear = new SimpleIntegerProperty(y);
		this.numSell = new SimpleIntegerProperty(s);
		this.numAv = new SimpleIntegerProperty(a);
		this.income = new SimpleDoubleProperty(i);
	}

	/**
	 * Gets the wine name.
	 *
	 * @return wine name.
	 *
	**/
	public String getWineName()
	{
		return this.wineName.get();
	}
	
	/**
	 * Gets the wine year.
	 *
	 * @return wine year.
	 *
	**/
	public int getWineYear()
	{
		return this.wineYear.get();
	}
	
	/**
	 * Gets the wine monthly number of sales.
	 *
	 * @return wine monthly number of sales.
	 *
	**/
	public int getNumSell()
	{
		return this.numSell.get();
	}
	
	/**
	 * Gets the wine monthly available.
	 *
	 * @return wine monthly available.
	 *
	**/
	public int getNumAv()
	{
		return this.numAv.get();
	}
	
	/**
	 * Gets the wine monthly income.
	 *
	 * @return wine monthly income.
	 *
	**/
	public double getIncome()
	{
		return this.income.get();
	}
}
