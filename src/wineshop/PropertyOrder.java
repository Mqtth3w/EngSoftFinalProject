/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.time.LocalDate;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
*
* The class {@code PropertyOrder} defines a sales order/proposal purchase/purchase order in simple property format.
*  
* since a Simple*Property cannot be sent in the socket easily I created this class,
*  which always represents a single item in each type of order, to show the orders in the javaFX table.
*  
**/

public class PropertyOrder 
{

	private SimpleIntegerProperty id;
	private SimpleObjectProperty<LocalDate> orderDate;
	private SimpleObjectProperty<LocalDate> assignDate;
	private SimpleStringProperty customerEmail;
	private SimpleStringProperty employeeEmail;
	private SimpleStringProperty address;
	private SimpleStringProperty state; 
	private SimpleStringProperty wineName;
	private SimpleIntegerProperty wineYear;
	private SimpleIntegerProperty quantity;
	private SimpleDoubleProperty price;
	
	/**
	 * Class constructor.
	 *
	 * @param i the order id.
	 * @param o the order date.
	 * @param a the order assigned date.
	 * @param ce the order customer email.
	 * @param ee the order employee email.
	 * @param ad order customer address.
	 * @param s order state.
	 * @param w order wine name.
	 * @param y order wine year.
	 * @param q order wine quantity.
	 * @param p order wine total price.
	 *
	**/
	public PropertyOrder(int i, LocalDate o, LocalDate a, String ce, String ee, String ad, String s, String w, int y, int q, double p)
	{
		this.id = new SimpleIntegerProperty(i);
		this.orderDate = new SimpleObjectProperty<>(o);
		this.assignDate = new SimpleObjectProperty<>(a);
		this.customerEmail = new SimpleStringProperty(ce);
		this.employeeEmail = new SimpleStringProperty(ee);
		this.address = new SimpleStringProperty(ad);
		this.state = new SimpleStringProperty(s);
		this.wineName = new SimpleStringProperty(w);
		this.wineYear = new SimpleIntegerProperty(y);
		this.quantity = new SimpleIntegerProperty(q);
		this.price = new SimpleDoubleProperty(p);
	}
	
	/**
	 * Gets the order id.
	 *
	 * @return order id.
	 *
	**/
	public int getId()
	{
		return this.id.get();
	}
	
	/**
	 * Gets the order date.
	 *
	 * @return order date.
	 *
	**/
	public LocalDate getOrderDate()
	{
		return this.orderDate.get();
	}
	
	/**
	 * Gets the order assigned date.
	 *
	 * @return order assigned date.
	 *
	**/
	public LocalDate getAssignDate()
	{
		return this.assignDate.get();
	}
	
	/**
	 * Gets the order customer email.
	 *
	 * @return order customer email.
	 *
	**/
	public String getCustomerEmail()
	{
		return this.customerEmail.get();
	}
	
	/**
	 * Gets the order employee email.
	 *
	 * @return order employee email.
	 *
	**/
	public String getEmployeeEmail()
	{
		return this.employeeEmail.get();
	}
	
	/**
	 * Gets the order customer address.
	 *
	 * @return order customer address.
	 *
	**/
	public String getAddress()
	{
		return this.address.get();
	}
	
	/**
	 * Gets the order state.
	 *
	 * @return order state.
	 *
	**/
	public String getState()
	{
		return this.state.get();
	}
	
	/**
	 * Gets the order wine name.
	 *
	 * @return order wine name.
	 *
	**/
	public String getWineName()
	{
		return this.wineName.get();
	}
	
	/**
	 * Gets the order wine year.
	 *
	 * @return order wine year.
	 *
	**/
	public int getWineYear()
	{
		return this.wineYear.get();
	}
	
	/**
	 * Gets the order quantity.
	 *
	 * @return order quantity.
	 *
	**/
	public int getQuantity()
	{
		return this.quantity.get();
	}
	
	/**
	 * Gets the order total price.
	 *
	 * @return order total price.
	 *
	**/
	public double getPrice()
	{
		return this.price.get();
	}
}
