/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.Serializable;
import java.util.List;

/**
*
* The class {@code Response} defines a response for the client.
*
**/

public class Response implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Actor actor;
	private List<Wine> wines;
	private boolean bool;
	private List<Actor> customers;
	private List<ProposalOrder> custOrders; 
	private List<PurchaseOrder> empOrders; 
	private Report report;
	private List<Promotion> promotions;
	private List<Notification> notifications;
  
	/**
	 * Class constructor.
	 *
	 * @param a the response actor.
	 * @param w the response wines.
	 * @param b the response success(not in all cased used).
	 * @param c the response actors.
	 * @param co the response proposals purchase/sales orders.
	 * @param eo the response purchase orders.
	 * @param r the response report.
	 * @param p the response promotions.
	 * @param n the response notifications.
	 *
	**/
	public Response(Actor a, List<Wine> w, boolean b, List<Actor> c, List<ProposalOrder> co, List<PurchaseOrder> eo, Report r, List<Promotion> p, List<Notification> n)
	{
		this.actor = a;
		this.wines = w;
		this.bool = b;
		this.customers = c;
		this.custOrders = co;
		this.empOrders = eo;
		this.report = r;
		this.promotions = p;
		this.notifications = n;
	}
  
	/**
	 * Gets the response actor.
	 *
	 * @return response actor.
	 *
	**/
	public Actor getActor()
	{
		return this.actor;
	}
	
	/**
	 * Gets the response wines.
	 *
	 * @return response wines.
	 *
	**/
	public List<Wine> getWines()
	{
		return this.wines;
	}
	
	/**
	 * Gets the response success.
	 *
	 * @return response success.
	 *
	**/
	public boolean getBool()
	{
		return this.bool;
	}
	
	/**
	 * Gets the response actors.
	 *
	 * @return response actors.
	 *
	**/
	public List<Actor> getCustomers()
	{
		return this.customers;
	}
	
	/**
	 * Gets the response proposals purchase/sales orders.
	 *
	 * @return response proposals purchase/sales orders.
	 *
	**/
	public List<ProposalOrder> getCustOrders()
	{
		return this.custOrders;
	}
	
	/**
	 * Gets the response purchase orders.
	 *
	 * @return response purchase orders.
	 *
	**/
	public List<PurchaseOrder> getEmpOrders()
	{
		return this.empOrders;
	}
	
	/**
	 * Gets the response report.
	 *
	 * @return response report.
	 *
	**/
	public Report getReport()
	{
		return this.report;
	}
	
	/**
	 * Gets the response promotions.
	 *
	 * @return response promotions.
	 *
	**/
	public List<Promotion> getPromotions()
	{
		return this.promotions;
	}
	
	/**
	 * Gets the response notifications.
	 *
	 * @return response notifications.
	 *
	**/
	public List<Notification> getNotifications()
	{
		return this.notifications;
	}
	
}
