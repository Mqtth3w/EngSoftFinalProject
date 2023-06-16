/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.Serializable;

/**
*
* The class {@code EmployeeReport} defines employee report info.
*
**/

public class EmployeeReport implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String email;
	private int numCompleted;
	private int numExpired;
	
	/**
	 * Class constructor.
	 *
	 * @param e the employee email.
	 * @param nc the number of completed activity.
	 * @param ne the number of uncompleted activity.
	 *
	 **/
	public EmployeeReport(String e, int nc, int ne)
	{
		this.email = e;
		this.numCompleted = nc;
		this.numExpired = ne;
	}
	
	/**
	 * Gets the employee email.
	 *
	 * @return employee email.
	 *
	 **/
	public String getEmail()
	{
		return this.email;
	}
	
	/**
	 * Gets the number of completed activity.
	 *
	 * @return completed activity.
	 *
	 **/
	public int getNumCompleted()
	{
		return this.numCompleted;
	}
	
	/**
	 * Gets the number of uncompleted activity.
	 *
	 * @return uncompleted activity.
	 *
	 **/
	public int getNumExpired()
	{
		return this.numExpired;
	}
	
}
