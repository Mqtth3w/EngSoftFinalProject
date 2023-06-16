/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
*
* The class {@code Report} defines a report.
*
**/

public class Report implements Serializable
{
	private static final long serialVersionUID = 1L;
	private double income;
	private double expense;
	private int totalSell;
	private int totalAvaiable;
	private LocalDate monthYearReport; //yyyy-mm-dd but we are interested only for yyyy-mm
	private List<WineReport> winereports = new LinkedList<WineReport>();
	private List<EmployeeReport> employeereports = new LinkedList<EmployeeReport>();
	
	/**
	 * Class constructor.
	 *
	 * @param i the report monthly total income.
	 * @param e the report monthly total expense.
	 * @param ts the report monthly total number of sales.
	 * @param ta the report monthly total number available.
	 * @param mr the report monthly date.
	 * @param wr the wine reports.
	 * @param er the employee reports.
	 *
	**/
	public Report(double i, double e, int ts, int ta, LocalDate mr, List<WineReport> wr, List<EmployeeReport> er)
	{
		this.income = i;
		this.expense = e;
		this.totalSell = ts;
		this.totalAvaiable = ta;
		this.monthYearReport = mr;
		this.winereports = wr;
		this.employeereports = er;
	}
	
	/**
	 * Gets the report monthly total income.
	 *
	 * @return report monthly total income.
	 *
	**/
	public double getIncome()
	{
		return this.income;
	}
	
	/**
	 * Gets the report monthly total expense.
	 *
	 * @return report monthly total expense.
	 *
	**/
	public double getExpense()
	{
		return this.expense;
	}
	
	/**
	 * Gets the report monthly total number of sales.
	 *
	 * @return report monthly total number of sales.
	 *
	**/
	public int getTotSell()
	{
		return this.totalSell;
	}
	
	/**
	 * Gets the report monthly total number available.
	 *
	 * @return report monthly total number available.
	 *
	**/
	public int getTotAvaiable()
	{
		return this.totalAvaiable;
	}
	
	/**
	 * Gets the report monthly date.
	 *
	 * @return report monthly date.
	 *
	**/
	public LocalDate getMontYearReport()
	{
		return this.monthYearReport;
	}
	
	/**
	 * Gets the monthly wine reports.
	 *
	 * @return monthly wine reports.
	 *
	**/
	public List<WineReport> getWineReports()
	{
		return this.winereports;
	}
	
	/**
	 * Gets the monthly employee reports.
	 *
	 * @return monthly employee reports.
	 *
	**/
	public List<EmployeeReport> getEmpReposrts()
	{
		return this.employeereports;
	}
}
