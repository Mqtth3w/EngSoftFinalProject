/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

/**
*
* The class {@code Employee} defines an employee of the system.
*
**/

public class Employee extends Actor{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	 * @param r the actor role.
	 *
	 **/
	public Employee(String u, String n, String s, String fc, String e, String p, String a, String pas, String defpas, String r)
	{
		super(u, n, s, fc, e, p, a, pas, r);
		this.defaultPassword = defpas;
	}

	/**
	 * Gets the employee default password.
	 *
	 * @return employee default password.
	 *
	 **/
	String getDefaultPass()
	{
		return this.defaultPassword;
	}
}
