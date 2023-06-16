/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import javafx.beans.property.SimpleStringProperty;

/**
*
* The class {@code PropertyActor} defines an user of the system in simple property format.
*
* since a SimpleStringProperty cannot be sent in the socket easily I created this class,
*  which always represents actor, to show the clients in the javaFX tables.
*  
**/

public class PropertyActor
{
	private SimpleStringProperty username;
	private SimpleStringProperty name;
	private SimpleStringProperty surname;
	private SimpleStringProperty FiscalCode;
	private SimpleStringProperty email;
	private SimpleStringProperty phone;
	private SimpleStringProperty address;
	
	/**
	 * Class constructor.
	 *
	 * @param u the actor user name.
	 * @param n the actor name.
	 * @param s the actor surname.
	 * @param fc the actor fiscal code.
	 * @param e the actor email.
	 * @param p the actor phone.
	 * @param a the actor address.
	 *
	**/
	public PropertyActor(String u, String n, String s, String fc, String e, String p, String a)
	{
		this.username = new SimpleStringProperty(u);
		this.name = new SimpleStringProperty(n);
		this.surname = new SimpleStringProperty(s);
		this.FiscalCode = new SimpleStringProperty(fc);
		this.email = new SimpleStringProperty(e);
		this.phone = new SimpleStringProperty(p);
		this.address = new SimpleStringProperty(a);
	}

	/**
	 * Gets the actor user name.
	 *
	 * @return actor user name.
	 *
	**/
	public String getUsername()
	{
		return this.username.get();
	}
	
	/**
	 * Gets the actor name.
	 *
	 * @return actor name.
	 *
	**/
	public String getName()
	{
		return this.name.get();
	}	
	
	/**
	 * Gets the actor surname.
	 *
	 * @return actor surname.
	 *
	**/
	public String getSurname()
	{
		return this.surname.get();
	}
	
	/**
	 * Gets the actor fiscal code.
	 *
	 * @return actor fiscal code.
	 *
	**/
	public String getFiscalCode()
	{
		return this.FiscalCode.get();
	}	
	
	/**
	 * Gets the actor email.
	 *
	 * @return actor email.
	 *
	**/
	public String getEmail() 
	{
		return this.email.get();
	}	
	
	/**
	 * Gets the actor phone.
	 *
	 * @return actor phone.
	 *
	**/
	public String getPhone()
	{
		return this.phone.get();
	}
	
	/**
	 * Gets the actor address.
	 *
	 * @return actor address.
	 *
	**/
	public String getAddress()
	{
		return this.address.get();
	}
	
}