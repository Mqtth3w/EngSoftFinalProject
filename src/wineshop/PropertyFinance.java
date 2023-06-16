/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
*
* The class {@code PropertyFinance} defines a report finance info in simple property format.
*  
* since a Simple*Property cannot be sent in the socket easily I created this class,
*  which always represents finance report info, to show in report table javaFX.
*  
**/

public class PropertyFinance 
{

	private SimpleDoubleProperty income;
	private SimpleStringProperty orderDate; //report date
	private SimpleDoubleProperty expense;
	private SimpleIntegerProperty totalSell;
	private SimpleIntegerProperty totalAvaiable;
	
	/**
	 * Class constructor.
	 *
	 * @param i the monthly total income.
	 * @param e the monthly total expense.
	 * @param ts the monthly total number of sales.
	 * @param ta the monthly total number available.
	 * @param mr the monthly report date.
	 *
	**/
	public PropertyFinance(double i, double e, int ts, int ta, LocalDate mr)
	{
		this.income = new SimpleDoubleProperty(i);
		this.orderDate = new SimpleStringProperty(mr.format(DateTimeFormatter.ofPattern("yyyy-MM")));
		this.expense = new SimpleDoubleProperty(e);
		this.totalSell = new SimpleIntegerProperty(ts);
		this.totalAvaiable = new SimpleIntegerProperty(ta);
	}
	
	/**
	 * Gets the monthly total income.
	 *
	 * @return monthly total income.
	 *
	**/
	public double getIncome()
	{
		return this.income.get();
	}
	
	/**
	 * Gets the monthly report date.
	 *
	 * @return monthly report date.
	 *
	**/
	public String getOrderDate()
	{
		return this.orderDate.get();
	}
	
	/**
	 * Gets the monthly total expense.
	 *
	 * @return monthly total expense.
	 *
	**/
	public double getExpense()
	{
		return this.expense.get();
	}
	
	/**
	 * Gets the monthly total number of sales.
	 *
	 * @return monthly total number of sales.
	 *
	**/
	public int getTotalSell()
	{
		return this.totalSell.get();
	}
	
	/**
	 * Gets the monthly total number available.
	 *
	 * @return monthly total number available.
	 *
	**/
	public int getTotalAvaiable()
	{
		return this.totalAvaiable.get();
	}
	
}
