/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.Serializable;
import java.time.LocalDate;

/**
*
* The class {@code Request} defines a request for the server.
*
**/

public class Request implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String service;
	private String username;
	private String password;
	private String newPassword;
	private String wineName;
	private int wineYear;
	private Actor actor;
	private String surname;
	private LocalDate inDate;
	private LocalDate finDate;
	private int discount;
	private ProposalOrder pord;
	
	/**
	 * Class constructor.
	 *
	 * @param s the request asked service.
	 * @param u the request actor user name.
	 * @param p the request actor password.
	 * @param np the request actor new password.
	 * @param n the request wine name.
	 * @param y the request wine year.
	 * @param a the request actor.
	 * @param su the request customer surname.
	 * @param i the request promotion start date.
	 * @param f the request promotion end date.
	 * @param d the request promotion discount.
	 * @param po the request order.
	 *
	**/
	public Request(String s, String u, String p, String np, String n, int y, Actor a, String su, LocalDate i, LocalDate f, int d, ProposalOrder po)
	{
		this.service = s;
		this.username = u;
		this.password = p;
		this.newPassword = np;
		this.wineName = n;
		this.wineYear = y;
		this.actor = a;
		this.surname = su;
		this.inDate = i;
		this.finDate = f;
		this.discount = d;
		this.pord = po;
	}

	/**
	 * Gets the request service.
	 *
	 * @return request service.
	 *
	**/
	public String getService() 
	{
		return this.service;
	}

	/**
	 * Gets the request actor user name.
	 *
	 * @return request actor user name.
	 *
	**/
	public String getUsername() 
	{
		return this.username;
	}
	
	/**
	 * Gets the request actor password.
	 *
	 * @return request actor password.
	 *
	**/
	public String getPassword() 
	{
		return this.password;
	}
	
	/**
	 * Gets the request actor new password.
	 *
	 * @return request actor new password.
	 *
	**/
	public String getNewPassword() 
	{
		return this.newPassword;
	}
	
	/**
	 * Gets the request wine name.
	 *
	 * @return request wine name.
	 *
	**/
	public String getWineName() 
	{
		return this.wineName;
	}
	
	/**
	 * Gets the request wine year.
	 *
	 * @return request wine year.
	 *
	**/
	public int getWineYear() 
	{
		return this.wineYear;
	}

	/**
	 * Gets the request actor.
	 *
	 * @return request actor.
	 *
	**/
	public Actor getActor()
	{
		return this.actor;
	}
	
	/**
	 * Gets the request actor surname.
	 *
	 * @return request actor surname.
	 *
	**/
	public String getSurname() 
	{
		return this.surname;
	}
	
	/**
	 * Gets the request promotion start date.
	 *
	 * @return request promotion start date.
	 *
	**/
	public LocalDate getInDate()
	{
		return this.inDate;
	}
	
	/**
	 * Gets the request promotion end date.
	 *
	 * @return request promotion end date.
	 *
	**/
	public LocalDate getFinDate()
	{
		return this.finDate;
	}
	
	/**
	 * Gets the request promotion discount.
	 *
	 * @return request promotion discount.
	 *
	**/
	public int getDiscount()
	{
		return this.discount;
	}
	
	/**
	 * Gets the request order.
	 *
	 * @return request order.
	 *
	**/
	public ProposalOrder getProposalOrder()
	{
		return this.pord;
	}
}
