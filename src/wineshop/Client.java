/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
*
* The class {@code Client} defines a client that sends an object
* to a server and receives its answer.
*
**/

public class Client
{
	private static final int SPORT = 4444;
	private static final String SHOST = "localhost";
	private Socket client;
	private ObjectInputStream  inputStream = null;
	private ObjectOutputStream outputStream = null;
	private List<WineOrder> winesOrder = new LinkedList<WineOrder>();
	private List<WineOrder> winesProposal = new LinkedList<WineOrder>();	
	private Actor myuser;
	
	/**
	 * Class constructor.
	 *
	**/
	public Client()
	{
		try 
		{
			client = new Socket(SHOST, SPORT);
			outputStream = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the actor.
	 *
	 * @return current logged user.
	 *
	**/
	Actor getUser()
	{
		return this.myuser;
	}
	
	/**
	 * Execute hash.
	 *
	 * @param str the string to be hashed.
	 * @return the hash.
	 *
	**/
	String hashFunction(String str)
	{
		HashSHA3 hashc = new HashSHA3();
		String hash = "";
		try {
			hash = hashc.executeHash(str);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hash;
	}
	
	/**
	 * Send the request to the server.
	 *
	 * @param req the request.
	 *
	**/
	void sendRequest(Request req) 
	{
		  try 
		  {			  
			  if (outputStream == null)
		      {
				  outputStream = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
		      }
			  outputStream.writeObject(req);
			  outputStream.flush();
			  outputStream.reset();
		  } 
		  catch (IOException e) 
		  {
				e.printStackTrace();
		  }
	}
	
	/**
	 * Read the server response.
	**/
	Response readResponse() 
	{
		try 
		{
			
			if (inputStream == null)
	        {
				inputStream = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
	        }
			
			Object i = inputStream.readObject();
			
		    if (i instanceof Response)
		    {
		        Response res = (Response) i;
		        return res;
		    }
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Ask to server to login into the system.
	 *
	 * @param username the actor user name.
	 * @param password the actor password.
	 * @return the current logged actor (null if wrong data).
	 *
	**/
	Actor login(String username, String password)
	{
		Request req = new Request("login", username, hashFunction(password), "", "", -1, null, "", null, null, -1, null);
		sendRequest(req);
		Response res = readResponse();
		myuser = res.getActor();
		return myuser;
	}
	
	/**
	 * Ask to server logout the employee from the system.
	 *
	 * @return true when logout is completed.
	 *
	**/
	boolean logout()
	{
		if(myuser.getRole().equals("employee"))
		{
			Request req = new Request("logout", myuser.getUsername(), "", "", "", -1, null, "", null, null, -1, null);
			sendRequest(req);
		}
		return true;
	}
	
	/**
	 * Close the socket with server.
	 *
	 * @return true when is done.
	 *
	**/
	boolean close()
	{
		Request req = new Request("close", "", "", "", "", -1, null, "", null, null, -1, null);
		sendRequest(req);
		try {
			this.client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Ask to server to sign in into the system.
	 *
	 * @param username the actor user name.
	 * @param name the actor name.
	 * @param ssurname the actor surname.
	 * @param fiscalcode the actor fiscal code.
	 * @param email the actor email.
	 * @param phone the actor phone.
	 * @param address the actor address.
	 * @param password the actor password.
	 * @param role the actor role.
	 *
	 * @return the success of operation(boolean).
	 *
	**/
	boolean signin(String username, String name, String surname, String fiscalcode, String email, String phone, String address, String password, String role) 
	{
		
		Actor a = new Actor(username, name, surname, fiscalcode, email, phone, address, hashFunction(password), "", role);
		Request req = new Request("signin", email, hashFunction(password), "", "", -1, a, "", null, null, -1, null);
		sendRequest(req);
		Response res = readResponse();
		return res.getBool();
	}
	
	/**
	 * Ask to server to search wines into system.
	 *
	 * @param name the wine name.
	 * @param year the wine year.
	 * @return the found wines.
	 *
	**/
	List<Wine> searchWine(String name, int year)
	{
		Request req = new Request("searchWine", "", "", "", name, year, null, "", null, null, -1, null);
		sendRequest(req);
		List<Wine> wines = readResponse().getWines();
		return wines;
	}
	
	/**
	 * Ask to server to change the password.
	 * 
	 * @param username the actor user name.
	 * @param oldp the actor old password.
	 * @param newp the actor new password.
	 * @return the success of operation(boolean).
	 *
	**/
	boolean changePass(String username, String oldp, String newp) 
	{
		String hasholdp = "";
		String hashnewp = "";
		hasholdp = hashFunction(oldp);
		hashnewp = hashFunction(newp);
		Request req = new Request("changePass", username, hasholdp, hashnewp, "", -1, null, "", null, null, -1, null);
		sendRequest(req);
		Response res = readResponse();
		return res.getBool();
	}
	
	/**
	 * Ask to server to reset the password.
	 * 
	 * @param username the actor user name.
	 * @return the success of operation(boolean).
	 *
	**/
	boolean resetPass(String username) 
	{
		Request req = new Request("resetPass", username, "", "", "", -1, null, "", null, null, -1, null);
		sendRequest(req);
		Response res = readResponse();
		return res.getBool();
	}
	
	/**
	 * Ask to server to send actors from a surname.
	 * 
	 * @param surname the actor surname.
	 * @return a list of actors.
	 *
	**/
	List<Actor> searchCustomers(String surname)
	{
		Request req = new Request("searchCustomers", "", "", "", "", -1, null, surname, null, null, -1, null);
		sendRequest(req);
		Response res = readResponse();
		List<Actor> customers = res.getCustomers();
		return customers;
	}
	
	/**
	 * Ask to server to send the operators(supplier, courier).
	 * 
	 * @return a list of actors.
	 *
	**/
	List<Actor> getOperators()
	{
		Request req = new Request("getOperators", "", "", "", "", -1, null, "", null, null, -1, null);
		sendRequest(req);
		Response res = readResponse();
		List<Actor> operators = res.getCustomers();
		return operators;
	}
	
	/**
	 * Ask to server to send sales orders/proposals purchase by date range.
	 * 
	 * @param username the actor user name.
	 * @param inDate the initial research date.
	 * @param finDate the final research date.
	 * @param order indicate the type of order.
	 * @return a list of orders.
	 *
	**/
	List<ProposalOrder> searchCustOrders(LocalDate inDate, LocalDate finDate, String order) //searchSalesOrders or searchProposalsPurchase
	{
		Request req = new Request(order, "", "", "", "", -1, null, "", inDate, finDate, -1, null);
		sendRequest(req);
		Response res = readResponse();
		List<ProposalOrder> orders = res.getCustOrders();
		return orders;
	}
	
	/**
	 * Ask to server to block an employee.
	 * 
	 * @param username the actor user name.
	 * @return the success of operation(boolean).
	 *
	**/
	boolean fireEmployee(String username)
	{
		Request req = new Request("fireEmployee", username, "", "", "", -1, null, "", null, null, -1, null);
		sendRequest(req);
		boolean bool = readResponse().getBool();
		return bool;
	}
	
	/**
	 * Ask to server to unblock an employee.
	 * 
	 * @param username the actor user name.
	 * @return the success of operation(boolean).
	 *
	**/
	boolean unfireEmployee(String username)
	{
		Request req = new Request("unfireEmployee", username, "", "", "", -1, null, "", null, null, -1, null);
		sendRequest(req);
		boolean bool = readResponse().getBool();
		return bool;
	}
	
	/**
	 * Ask to server to send purchase orders by date range.
	 * 
	 * @param username the actor user name.
	 * @param inDate the initial research date.
	 * @param finDate the final research date.
	 * @return a list of orders.
	 *
	**/
	List<PurchaseOrder> searchPurchaseOrders(LocalDate inDate, LocalDate finDate)
	{
		Request req = new Request("searchPurchaseOrders", "", "", "", "", -1, null, "", inDate, finDate, -1, null);
		sendRequest(req);
		List<PurchaseOrder> orders = readResponse().getEmpOrders();
		return orders;
	}
	
	/**
	 * Ask to server to send the report from date.
	 * 
	 * @param monthYearReport the report date.
	 * @return the report.
	 *
	**/
	Report makeReport(LocalDate monthYearReport)
	{
		Request req = new Request("report", "", "", "", "", -1, null, "", monthYearReport, null, -1, null);
		sendRequest(req); //send the report date like inDate for space saving
		Report report = readResponse().getReport();
		return report;
	}
	
	/**
	 * Ask to server to create a promotion.
	 * 
	 * @param name the wine name.
	 * @param year the wine year.
	 * @param discount the promotion discount.
	 * @param inDate the promotion initial date.
	 * @param finDate the promotion end date.
	 * @return the success of operation(boolean).
	 *
	**/
	boolean createPromotion(String name, int year, int discount, LocalDate inDate, LocalDate finDate)
	{
		Request req = new Request("createPromotion", "", "", "", name, year, null, "", inDate, finDate, discount, null);
		sendRequest(req);
		boolean bool = readResponse().getBool();
		return bool;
	}
	
	/**
	 * Ask to server to send current promotions.
	 * 
	 * @return a list of promotions.
	 *
	**/
	List<Promotion> getPromotions()
	{
		Request req = new Request("getPromotions", "", "", "", "", -1, null, "", null, null, -1, null);
		sendRequest(req);
		List<Promotion> promotions = readResponse().getPromotions();
		return promotions;
	}
	
	/**
	 * Ask to server to make sales order/proposal purchase.
	 * 
	 * @param bool indicate the order type.
	 * @return the success of operation(boolean).
	 *
	**/
	int makeProposalOrder(boolean bool)
	{
		if(this.winesProposal.size() == 0 && bool)
		{
			return 0;
		}
		String type;
		List<WineOrder> wos;
		if(bool)
		{
			wos = this.winesProposal;
			type = "proposalpurchase";
		}
		else
		{
			wos = this.winesOrder;
			type = "salesorder";
		}
		ProposalOrder order = new ProposalOrder(-1,LocalDate.now(),LocalDate.now(),
				myuser.getEmail(),"",wos,myuser.getAddress(),type);
		Request req = new Request("makeProposalOrder", "", "", "", "", -1, null, "", null, null, -1, order);
		sendRequest(req);
		boolean bool1 = readResponse().getBool();
		if(bool1)
		{
			cancelProposalOrder(!bool);
			return 1;
		}
		return 2;
	}

	/**
	 * Ask to server to complete a sales order.
	 * 
	 * @param order the order.
	 * @return the success of operation(boolean).
	 *
	**/
	boolean completeSalesOrder(ProposalOrder order)  
	{
		Request req = new Request("completeSalesOrder", "", "", "", "", -1, null, "", null, null, -1, order);
		sendRequest(req);
		boolean bool = readResponse().getBool();
		return bool;
	}
	
	/**
	 * Ask to server to complete a proposal purchase.
	 * 
	 * @param order the proposal.
	 * @return the success of operation(boolean).
	 *
	**/
	boolean completeProposalPurchase(ProposalOrder order)  
	{
		Request req = new Request("completeProposalPurchase", "", "", "", "", -1, null, "", null, null, -1, order);
		sendRequest(req);
		boolean bool = readResponse().getBool();
		return bool;
	}
	
	/**
	 * Ask to server to complete a purchase order.
	 * 
	 * @param order the order.
	 * @return the success of operation(boolean).
	 *
	**/
	boolean completePurchaseOrder(ProposalOrder order)  
	{
		Request req = new Request("completePurchaseOrder", "", "", "", "", -1, null, "", null, null, -1, order);
		sendRequest(req);
		boolean bool = readResponse().getBool();
		return bool;
	}
	
	/**
	 * Ask to server to generate a purchase order from a proposal.
	 * 
	 * @param order the proposal.
	 * @return a list of purchase order with the order.
	 *
	**/
	List<PurchaseOrder> generatePurchaseOrder(ProposalOrder order)  
	{
		Request req = new Request("generatePurchaseOrder", "", "", "", "", -1, null, "", null, null, -1, order);
		sendRequest(req);
		List<PurchaseOrder> po = readResponse().getEmpOrders();
		return po;
	}
	
	/**
	 * Ask to server to reject a sales order.
	 * 
	 * @param order the order.
	 * @return the success of operation(boolean).
	 *
	**/
	boolean rejectSalesOrder(ProposalOrder order) 
	{
		Request req = new Request("rejectSalesOrder", "", "", "", "", -1, null, "", null, null, -1, order);
		sendRequest(req);
		boolean bool = readResponse().getBool();
		return bool;
	}
	
	/**
	 * Ask to server to confirm a sales order.
	 * 
	 * @param order the order.
	 * @return the success of operation(boolean).
	 *
	**/
	boolean confirmSalesOrder(ProposalOrder order)  
	{
		Request req = new Request("confirmSalesOrder", "", "", "", "", -1, null, "", null, null, -1, order);
		sendRequest(req);
		boolean bool = readResponse().getBool();
		return bool;
	}
	
	/**
	 * Ask to server to send actor notifications.
	 * 
	 * @return a list of notifications.
	 *
	**/
	List<Notification> getNotifications() 
	{
		Request req = new Request("getNotifications", "", "", "", "", -1, myuser, "", null, null, -1, null);
		sendRequest(req);
		List<Notification> notifications = readResponse().getNotifications();
		return notifications;
	}
	
	/**
	 * Ask to server to create a availability notification.
	 * 
	 * @param name the wine name.
	 * @param year the wine year.
	 * @param quantity the asked quantity.
	 * @return the result of the request(int).
	 *
	**/
	int createNotification(String name, int year, int quantity)
	{
		List<Wine> wine = searchWine(name, year);
		if(wine.size() != 1)
		{
			return 1; //no wine found.
		}
		
		if(wine.get(0).getQuantity() >= quantity)
		{
			return 2; //already available.
		}
		
		Request req = new Request("createNotification", "", "", "", name, 
				year, myuser, "", null, null, quantity, null);
		sendRequest(req);
		boolean bool = readResponse().getBool();
		if(bool)
		{
			return 3; //notification created.
		}
		
		return 4; //any error.
	}
	
	/**
	 * Check if the order contain something.
	 * 
	 * @param bool indicate the type (sales/proposal).
	 * @return the order size.
	 *
	**/
	int checkOrderLenght(boolean bool)
	{
		if(bool)
		{
			return this.winesOrder.size();
		}
		else
		{
			return this.winesProposal.size();
		}
	}
	
	/**
	 * Clear the session order.
	 * 
	 * @param bool indicate the type (sales/proposal).
	 *
	**/
	void cancelProposalOrder(boolean bool)
	{
		if(bool)
		{
			this.winesOrder.clear();
		}
		else
		{
			this.winesProposal.clear();
		}
	}
	
	/**
	 * Add wine to the session order.
	 * 
	 * @param bool indicate the type (sales/proposal).
	 * @param name the wine name.
	 * @param year the wine year.
	 * @param boxq the quantity of single bottles.
	 * @param box6 the quantity of 6 bottles chest.
	 * @param box12 the quantity of 12 bottles chest.
	 * @return the result of the request(int).
	 *
	**/
	int addWineToProposalOrder(boolean bool, String name, int year, int boxq, int box6, int box12)
	{
		List<WineOrder> wos;
		if(bool)
		{
			wos = this.winesOrder;
		}
		else
		{
			wos = this.winesProposal;
		}
		
		for(WineOrder wo : wos)
		{
			if(wo.getWineName().equals(name) && wo.getWineYear() == year)
			{
				return 0; //already into the order.
			}
		}
		
		List<Wine> wines = searchWine(name, year);
		if(wines.size() != 1)
		{
			return 1; //no wine found.
		}
		
		Wine wine = wines.get(0);
		int quantity = boxq + (box6*6) + (box12*12);
		
		if(wine.getQuantity() < quantity && bool)
		{
			return 2; //already available(proposal).
		}
		
		if(wine.getQuantity() >= quantity && !bool)
		{
			return 2; //not available(sales).
		}
		
		double basePrice = wine.getPrice();
		double price = basePrice*boxq;
		int discount = 0;
		
		for(int i = 1; i <= box6; i++)
		{
			if(i == 1)
			{
				discount = 5;
			}
			else if(i == 2)
			{
				discount = 7;
			}
		}
		
		price = price + (box6*6*basePrice*(1-(discount/100)));
		
		for(int i = 1; i <= box12; i++)
		{
			if(i == 1)
			{
				discount = 10;
			}
			else if(i == 2)
			{
				discount = 13;
			}
		}
		
		price = price + (box12*12*basePrice*(1-(discount/100)));
		
		List<Promotion> promotions = getPromotions();
		for(Promotion p : promotions)
		{
			if(p.getWineName().equals(name) && p.getWineYear() == year)
			{
				price = price - (price*p.getDiscount()/100);
				break;
			}
		}
		if(bool)
		{
			this.winesOrder.add(new WineOrder(name, year, quantity, price));
		}
		else
		{
			this.winesProposal.add(new WineOrder(name, year, quantity, price));
		}
		
		return 3; //added.
	}
	
	List<WineOrder> getWineProposalOrder(boolean bool)
	{
		if(bool)
		{
			return this.winesOrder;
		}
		return this.winesProposal;
	}
	
	/**
	 * Ask to server orders history (sales).
	 * 
	 * @return a list of orders.
	 *
	**/
	List<ProposalOrder> getHistory() 
	{
		Request req = new Request("getHistory", "", "", "", "", -1, myuser, "", null, null, -1, null);
		sendRequest(req);
		Response res = readResponse();
		List<ProposalOrder> orders = res.getCustOrders();
		return orders;
	}
	
	/**
	 * Ask to server to send the assigned task.
	 * 
	 * @return a list of orders.
	 *
	**/
	List<ProposalOrder> getAssignment()
	{
		Request req = new Request("getAssignment", "", "", "", "", -1, myuser, "", null, null, -1, null);
		sendRequest(req);
		Response res = readResponse();
		List<ProposalOrder> task = res.getCustOrders();
		return task;
	}
	
}
