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
* The class {@code PurchaseOrder} defines a purchase order.
*
**/

public class PurchaseOrder implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id_order;
	private LocalDate orderDate;//yyyy-mm-dd
	private LocalDate assignDate;//yyyy-mm-dd
	private String employeeEmail;
	private List<WineOrder> wineOrders;
	private String state;
	
	/**
	 * Class constructor.
	 *
	 * @param i the order id.
	 * @param o the order date.
	 * @param a the order assigned date.
	 * @param ee the order employee email.
	 * @param w the order wines.
	 * @param s the order state.
	 *
	**/
	public PurchaseOrder(int i, LocalDate o, LocalDate a, String ee, List<WineOrder> w, String s)
	{
		this.id_order = i;
		this.orderDate = o;
		this.employeeEmail = ee;
		this.wineOrders = w;
		this.assignDate = a;
		this.state = s;
	}
	
	/**
	 * Gets the order id.
	 *
	 * @return order id.
	 *
	**/
	int getIdOrd()
	{
		return this.id_order;
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
	 * Gets the order state.
	 *
	 * @return order state.
	 *
	**/
	String getState()
	{
		return this.state;
	}

}

