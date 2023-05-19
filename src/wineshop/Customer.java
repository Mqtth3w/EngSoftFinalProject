/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.Serializable;

/**
*
* The class {@code Customer} defines a customer of the system.
*
**/

public class Customer extends Actor implements Serializable
{

	//private String DeliveryAddress;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
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
	public Customer(String u, String n, String s, String fc, String e, String p, String a, String pas, String r)
	{
		super(u, n, s, fc, e, p, a, pas, r);
		
	}

	
}
