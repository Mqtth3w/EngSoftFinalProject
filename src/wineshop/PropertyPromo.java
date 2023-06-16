/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;


/**
*
* The class {@code PropertyPromo} defines a promotion in simple property format.
*  
* since a Simple*Property cannot be sent in the socket easily I created this class,
*  which always represents promotions info, to show in promotion table javaFX.
*  
**/


public class PropertyPromo 
{
	private SimpleObjectProperty<LocalDate> inday;
	private SimpleObjectProperty<LocalDate> finday;
	private SimpleIntegerProperty discount;
	private SimpleStringProperty wineName;
	private SimpleIntegerProperty wineYear;
	
	
	/**
	 * Class constructor.
	 *
	 * @param i the promotion start date.
	 * @param f the promotion end date.
	 * @param d the promotion discount.
	 * @param n the promotion wine name.
	 * @param y the promotion wine year.
	 *
	**/
	public PropertyPromo(LocalDate i, LocalDate f, int d, String n, int y)
	{
		this.inday = new SimpleObjectProperty<>(i);
		this.finday = new SimpleObjectProperty<>(f);
		this.discount = new SimpleIntegerProperty(d);
		this.wineName = new SimpleStringProperty(n);
		this.wineYear = new SimpleIntegerProperty(y);
	}
	
	/**
	 * Gets the promotion start date.
	 *
	 * @return promotion start date.
	 *
	**/
	public LocalDate getInday()
	{
		return this.inday.get();
	}
	
	/**
	 * Gets the promotion end date.
	 *
	 * @return promotion end date.
	 *
	**/
	public LocalDate getFinday()
	{
		return this.finday.get();
	}
	
	/**
	 * Gets the promotion discount.
	 *
	 * @return promotion discount.
	 *
	**/
	public int getDiscount()
	{	
		return this.discount.get();
	}
	
	/**
	 * Gets the promotion wine name.
	 *
	 * @return promotion wine name.
	 *
	**/
	public String getWineName()
	{
		return this.wineName.get();
	}
	
	/**
	 * Gets the promotion wine year.
	 *
	 * @return promotion wine year.
	 *
	**/
	public int getWineYear()
	{
		return this.wineYear.get();
	}
}
