/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.Serializable;

/**
 *
 * The class {@code Actor} defines an user of the system.
 *
**/

public class Actor implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String username;
	private String name;
	private String surname;
	private String FiscalCode;
	private String email;
	private String phone;
	private String address;
	private String password;
	private String role;
	private String defaultPassword;
	
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
	 * @param pas the actor password.
	 * @param defpas the actor default password.
	 * @param r the actor role.
	 *
	**/
	public Actor(String u, String n, String s, String fc, String e, String p, String a, String pas, String defpas, String r)
	{
		this.username = u;
		this.name = n;
		this.surname = s;
		this.FiscalCode = fc;
		this.email = e;
		this.phone = p;
		this.password = pas;
		this.address = a;
		this.role = r;
		this.defaultPassword = defpas;
	}

	/**
	 * Gets the actor user name.
	 *
	 * @return actor user name.
	 *
	**/
	public String getUsername()
	{
		return this.username;
	}
	
	/**
	 * Gets the actor name.
	 *
	 * @return actor name.
	 *
	**/
	public String getName()
	{
		return this.name;
	}	
	
	/**
	 * Gets the actor surname.
	 *
	 * @return actor surname.
	 *
	**/
	public String getSurname()
	{
		return this.surname;
	}	
	
	/**
	 * Gets the actor fiscal code.
	 *
	 * @return actor fiscal code.
	 *
	**/
	public String getFiscalCode()
	{
		return this.FiscalCode;
	}	
	
	/**
	 * Gets the actor email.
	 *
	 * @return actor email.
	 *
	**/
	public String getEmail() 
	{
		return this.email;
	}
	
	
	/**
	 * Gets the actor phone.
	 *
	 * @return actor phone.
	 *
	**/
	public String getPhone()
	{
		return this.phone;
	}
	
	/**
	 * Gets the actor address.
	 *
	 * @return actor address.
	 *
	**/
	public String getAddress()
	{
		return this.address;
	}	
	
	/**
	 * Gets the actor password.
	 *
	 * @return actor password.
	 *
	**/
	public String getPassword()
	{
		return this.password;
	}
	
	/**
	 * Gets the actor role.
	 *
	 * @return actor role.
	 *
	**/
	public String getRole()
	{
		return this.role;
	}

	/**
	 * This is used only for employee.
	 * Gets the actor default password.
	 *
	 * @return actor default password.
	 *
	**/
	public String getDefaultPass()
	{
		return this.defaultPassword;
	}
}
