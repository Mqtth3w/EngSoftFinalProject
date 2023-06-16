/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
*
* The class {@code ProposalOrder} defines a sales order/proposal purchase (sometimes also purchase order).
*
**/

public class ProposalOrder implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	private LocalDate orderDate;//yyyy-mm-dd
	private LocalDate assignDate;//yyyy-mm-dd
	private String customerEmail;
	private String employeeEmail;
	private List<WineOrder> wineOrders;
	private String address;
	private String state; // depending on the context it indicates type or state of the order
	
	/**
	 * Class constructor.
	 *
	 * @param i the order id.
	 * @param o the order date.
	 * @param a the order assigned date.
	 * @param ce the order customer email.
	 * @param ee the order employee email.
	 * @param w the order wines.
	 * @param ad the order customer address.
	 * @param s the order state.
	 *
	**/
	public ProposalOrder(int i, LocalDate o, LocalDate a, String ce, String ee, List<WineOrder> w, String ad, String s)
	{
		this.id = i;
		this.orderDate = o;
		this.assignDate = a;
		this.customerEmail = ce;
		this.employeeEmail = ee;
		this.wineOrders = w;
		this.address = ad;
		this.state = s;
	}
	
	/**
	 * Gets the order id.
	 *
	 * @return order id.
	 *
	**/
	int getId()
	{
		return this.id;
	}
	
	/**
	 * Gets the order date.
	 *
	 * @return order date.
	 *
	**/
	LocalDate getOrderDate()
	{
		return this.orderDate;
	}
	
	/**
	 * Gets the order assigned date.
	 *
	 * @return order assigned date.
	 *
	**/
	LocalDate getAssignDate()
	{
		return this.assignDate;
	}
	
	/**
	 * Gets the order customer email.
	 *
	 * @return order customer email.
	 *
	**/
	String getCustomerEmail()
	{
		return this.customerEmail;
	}
	
	/**
	 * Gets the order employee email.
	 *
	 * @return order employee email.
	 *
	**/
	String getEmployeeEmail()
	{
		return this.employeeEmail;
	}
	
	/**
	 * Gets the order wines.
	 *
	 * @return order wines.
	 *
	**/
	List<WineOrder> getWines()
	{
		return this.wineOrders;
	}
	
	/**
	 * Gets the order customer address.
	 *
	 * @return order customer address.
	 *
	**/
	String getAddress()
	{
		return this.address;
	}
	
	/**
	 * Gets the order state.
	 *
	 * @return order state.
	 *
	**/
	String getState()
	{
		return this.state;
	}
	
	/**
	 * Sets the order state.
	 *
	 * @param s the order state.
	 *
	**/
	void setState(String s)
	{
		this.state = s;
	}
	
}