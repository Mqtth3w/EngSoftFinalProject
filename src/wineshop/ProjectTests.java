package wineshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
*
* The class {@code ProjectTests} defines a class to test the main base classes.
*
**/

public class ProjectTests 
{
	private static Actor actor = new Actor("usr","name","surname","fc",
			"email","phone","addr","passwd","defpasswd","cust");
	
	private static Notification notif = new Notification(10,"wine",2010,"email");
	
	private static Promotion promo = new Promotion(LocalDate.of(2023, 03, 10),
			LocalDate.of(2023, 03, 12),5,"wine",2010);
	
	private static Wine wine = new Wine("wine","productor","origin",2010,
			"tecnote","grape",90,90,70,90,90);
	
	/*
	 * Actor methods test
	 */
	@Test
	public void getActorName()
	{
		assertEquals("name", actor.getName());
	}
	
	@Test
	public void getActorSurname()
	{
		assertEquals("surname", actor.getSurname());
	}
	
	@Test
	public void getActorUsername()
	{
		assertEquals("usr", actor.getUsername());
	}
	
	@Test
	public void getActorPassword()
	{
		assertEquals("passwd", actor.getPassword());
	}
	
	@Test
	public void getActorDefaultPassword()
	{
		assertEquals("defpasswd", actor.getDefaultPass());
	}
	
	@Test
	public void getActorFiscalCode()
	{
		assertEquals("fc", actor.getFiscalCode());
	}
	
	@Test
	public void getActorEmail()
	{
		assertEquals("email", actor.getEmail());
	}
	
	@Test
	public void getActorPhone()
	{
		assertEquals("phone", actor.getPhone());
	}
	
	@Test
	public void getActorAddress()
	{
		assertEquals("addr", actor.getAddress());
	}
	
	@Test
	public void getActorRole()
	{
		assertEquals("cust", actor.getRole());
	}
	
	/*
	 * Notification methods test
	 */
	@Test
	public void getNotificanWineName()
	{
		assertEquals("wine", notif.getWineName());
	}
	
	@Test
	public void getNotificationWineYear()
	{
		assertEquals(2010, notif.getWineYear());
	}
	
	@Test
	public void getNotificationQuantity()
	{
		assertEquals(10, notif.getQuantity());
	}
	
	@Test
	public void getNotificationEmail()
	{
		assertEquals("email", notif.getCustEmail());
	}
	
	/*
	 * Promotion methods test
	 */
	@Test
	public void getPromotionInDate()
	{
		assertEquals(LocalDate.of(2023, 03, 10), promo.getInday());
	}
	
	@Test
	public void getPromotionFinDate()
	{
		assertEquals(LocalDate.of(2023, 03, 12), promo.getFinday());
	}
	
	@Test
	public void getPromotionDiscount()
	{
		assertEquals(5, promo.getDiscount());
	}
	
	@Test
	public void getPromotionWineName()
	{
		assertEquals("wine", promo.getWineName());
	}
	
	@Test
	public void getPromotionWineYear()
	{
		assertEquals(2010, promo.getWineYear());
	}
	
	/*
	 * Wine methods test
	 */
	@Test
	public void getWineName()
	{
		assertEquals("wine", wine.getName());
	}
	
	@Test
	public void getWineYear()
	{
		assertEquals(2010, wine.getYear());
	}
	
	@Test
	public void getWineProductor()
	{
		assertEquals("productor", wine.getProductor());
	}
	
	@Test
	public void getWineOrigin()
	{
		assertEquals("origin", wine.getOrigin());
	}
	
	@Test
	public void getWineTecNotes()
	{
		assertEquals("tecnote", wine.getTechnicalNotes());
	}
	
	@Test
	public void getWineGrape()
	{
		assertEquals("grape", wine.getGrapeVariety());
	}
	
	@Test
	public void getWinePrice() 
	{
		assertEquals(90, wine.getPrice());
	}
	
	@Test
	public void getWineSupplierPrice()
	{
		assertEquals(70, wine.getSupplierPrice());
	}
	
	@Test
	public void getWineQuality()
	{
		assertEquals(90, wine.getQuality());
	}
	
	@Test
	public void getWineQuantity()
	{
		assertEquals(90, wine.getQuantity());
	}
	
	@Test
	public void getWineSalesNumber()
	{
		assertEquals(90, wine.getSalesNumber());
	}
	
}
