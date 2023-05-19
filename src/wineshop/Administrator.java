/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

/**
*
* The class {@code Administrator} defines an administrator of the system.
*
**/

public class Administrator extends Actor{

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
	public Administrator(String u, String n, String s, String fc, String e, String p, String a, String pas, String r) 
	{
		super(u, n, s, fc, e, p, a, pas, r);
		
	}
	

}
