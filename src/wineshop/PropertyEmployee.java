/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
*
* The class {@code PropertyEmployee} defines a employee report info in simple property format.
*  
* since a Simple*Property cannot be sent in the socket easily I created this class,
*  which always represents employee activity, to show the employee activity in report table javaFX.
*  
**/

public class PropertyEmployee 
{

	private SimpleStringProperty email;
	private SimpleIntegerProperty numCompleted;
	private SimpleIntegerProperty numExpired;
	
	/**
	 * Class constructor.
	 *
	 * @param e the employee report email.
	 * @param nc the number of completed activity.
	 * @param ne the number of uncompleted activity.
	 *
	**/
	public PropertyEmployee(String e, int nc, int ne)
	{
		this.email = new SimpleStringProperty(e);
		this.numCompleted = new SimpleIntegerProperty(nc);
		this.numExpired = new SimpleIntegerProperty(ne);
	}
	
	/**
	 * Gets the employee email.
	 *
	 * @return employee email.
	 *
	**/
	public String getEmail()
	{
		return this.email.get();
	}
	
	/**
	 * Gets the number of completed activity.
	 *
	 * @return number of completed activity.
	 *
	**/
	public int getNumCompleted()
	{
		return this.numCompleted.get();
	}
	
	/**
	 * Gets the number of uncompleted activity.
	 *
	 * @return number of uncompleted activity.
	 *
	**/
	public int getNumExpired()
	{
		return this.numExpired.get();
	}
}
