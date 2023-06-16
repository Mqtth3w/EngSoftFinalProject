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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * The class {@code ServerThread} manages the interaction
 * with a client of the server(this thread manage client request).
 *
**/

public class ServerThread implements Runnable 
{

	//private Server server;
	private Socket socket;
	private final WineShopDatabase db;
	private ObjectInputStream  inputStream = null;
	private ObjectOutputStream outputStream = null;
	private Actor user;

	/**
	 * Class constructor.
	 *
	 * @param s  the server.
	 * @param c  the client socket.
	 * @param d  the server database.
	 *
	 **/
	public ServerThread(final Server s, final Socket c, final WineShopDatabase d)
	{
		//this.server = s;
		this.socket = c;
		this.db = d;
	}
	
	/**
	 * Send the response to the client.
	 *
	 * @param res the response.
	 *
	**/
	void sendResponse(Response res) 
	{
		  try 
		  {
			  if (outputStream == null)
			  {
				  outputStream = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
			  }
			  outputStream.writeObject(res);
			  outputStream.flush();
			  outputStream.reset();
		  } 
		  catch (IOException e) 
		  {
			  db.logoutEmployee(this.user.getUsername());
			  e.printStackTrace();
		  }
	}
	
	/**
	 * Read the client request.
	**/
	Request readRequest() 
	{
		Request req = null;
		try 
		{
			Object i = inputStream.readObject();

		    if (i instanceof Request)
		    {
		        req = (Request) i;
		    }
		} 
		catch (IOException | ClassNotFoundException e) 
		{
			db.logoutEmployee(this.user.getUsername());
			e.printStackTrace();
		}
		return req;
	}
	
	/**
	 * Handle search wines request.
	 *
	 * @param req the request.
	 *
	**/
	void searchWine(Request req) 
	{
		List<Wine> wines = new LinkedList<Wine>();
		wines = db.findWine(req.getWineName(), req.getWineYear());
		Response res = new Response(null, wines, false, null, null, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle login request.
	 *
	 * @param req the request.
	 *
	**/
	void login(Request req)
	{
		Actor user = null;
		Response res;
		user = db.login(req.getUsername(), req.getPassword());
		this.user = user;
		if(user != null)
		{
			res = new Response(user, null, true, null, null, null, null, null, null);
			sendResponse(res);
		}
		else
		{
			res = new Response(null, null, false, null, null, null, null, null, null);
			sendResponse(res);
		}
	}
	
	/**
	 * Handle logout employee request.
	 *
	 * @param req the request.
	 *
	**/
	void logout(Request req)
	{
		db.logoutEmployee(this.user.getUsername());
	}
	
	/**
	 * Handle sign in request.
	 *
	 * @param req the request.
	 *
	**/
	void signin(Request req)
	{
		Actor user = null;
		Response res;
		user = db.signin(req.getActor());
		if(user != null)
		{
			res = new Response(user, null, true, null, null, null, null, null, null);
			sendResponse(res);
		}
		else
		{
			res = new Response(null, null, false, null, null, null, null, null, null);
			sendResponse(res);
		}
	}
	
	/**
	 * Handle change password request.
	 *
	 * @param req the request.
	 *
	**/
	void changePass(Request req)
	{
		Response res;
		boolean result = db.changePass(req.getUsername(), req.getPassword(), req.getNewPassword());
		res = new Response(null, null, result, null, null, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle reset password request.
	 *
	 * @param req the request.
	 *
	**/
	void resetPass(Request req)
	{
		Response res;
		boolean result = db.resetPass(req.getUsername());
		res = new Response(null, null, result, null, null, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle search customers request.
	 *
	 * @param req the request.
	 *
	**/
	void searchCustomers(Request req)
	{
		Response res;
		List<Actor> customers = db.searchCustomers(req.getSurname());
		res = new Response(null, null, false, customers, null, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle block employee request.
	 *
	 * @param req the request.
	 *
	**/
	void fireEmployee(Request req)
	{
		Response res;
		boolean result = db.fireEmployee(req.getUsername(), "employee");
		res = new Response(null, null, result, null, null, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle unblock employee request.
	 *
	 * @param req the request.
	 *
	**/
	void unfireEmployee(Request req)
	{
		Response res;
		boolean result = db.fireEmployee(req.getUsername(), "fired");
		res = new Response(null, null, result, null, null, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle search proposals purchase/sales orders request.
	 *
	 * @param req the request.
	 * @param table the order table (proposalspurchase/salesorders).
	 *
	**/
	void searchCustomerOrders(Request req, String table)
	{
		Response res;
		List<ProposalOrder> orders = db.searchProposalOrders(req.getInDate(), req.getFinDate(), table);
		res = new Response(null, null, false, null, orders, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle search purchase orders request.
	 *
	 * @param req the request.
	 *
	**/
	void searchPurchaseOrders(Request req)
	{
		Response res;
		List<PurchaseOrder> orders = db.searchPurchaseOrders(req.getInDate(), req.getFinDate());
		res = new Response(null, null, false, null, null, orders, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle create/search report request.
	 *
	 * @param req the request.
	 *
	**/
	void report(Request req)
	{
		Response res;
		Report report = db.report(req.getInDate());
		res = new Response(null, null, false, null, null, null, report, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle create promotion request.
	 *
	 * @param req the request.
	 *
	**/
	void createPromotion(Request req)
	{
		Response res;
		boolean bool = db.createPromotion(req.getWineName(),req.getWineYear(),req.getDiscount(),req.getInDate(),req.getFinDate());
		res = new Response(null, null, bool, null, null, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle get current promotions request.
	 *
	 * @param req the request.
	 *
	**/
	void getPromotions(Request req)
	{
		Response res;
		List<Promotion> promotions = db.getPromotions();
		res = new Response(null, null, false, null, null, null, null, promotions, null);
		sendResponse(res);
	}
	
	/**
	 * Handle make proposal purchase/sales order request.
	 *
	 * @param req the request.
	 *
	**/
	void makeProposalOrder(Request req)
	{
		Response res;
		boolean bool = db.makeProposalOrder(req.getProposalOrder());
		res = new Response(null, null, bool, null, null, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle complete/reject/confirm proposal/sales request.
	 *
	 * @param req the request.
	 * @param type the order type(proposalspurchase/salesorders).
	 *
	**/
	void handleProposalOrder(Request req, String type) 
	{
		Response res;
		boolean bool = db.handleProposalOrder(req.getProposalOrder(), type);
		res = new Response(null, null, bool, null, null, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle get availability notifications request.
	 *
	 * @param req the request.
	 *
	**/
	void getNotifications(Request req) 
	{
		Response res;
		List<Notification> notifications = db.getNotifications(req.getActor().getEmail());
		res = new Response(null, null, false, null, null, null, null, null, notifications);
		sendResponse(res);
	}
	
	/**
	 * Handle create availability notification request.
	 *
	 * @param req the request.
	 *
	**/
	void createNotification(Request req) 
	{
		Response res; //used discount field for quantity to save space
		boolean bool = db.createNotification(req.getWineName(), req.getWineYear(), 
				req.getActor().getEmail(), req.getDiscount());
		res = new Response(null, null, bool, null, null, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle get order history request.
	 *
	 * @param req the request.
	 *
	**/
	void getHistory(Request req) 
	{
		Response res;
		List<ProposalOrder> orders = db.getHistory(req.getActor().getEmail());
		res = new Response(null, null, false, null, orders, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle terminate connection request.
	 *
	**/
	void close()
	{
		try 
		{
			this.socket.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Handle get operators(courier,supplier) request.
	 *
	**/
	void getOperators()
	{
		Response res;
		List<Actor> operators = db.getOperators();
		res = new Response(null, null, false, operators, null, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle get employee assignment request.
	 *
	 * @param req the request.
	 *
	**/
	void getAssignment(Request req)
	{
		Response res;
		List<ProposalOrder> task = db.getAssignment(req.getActor().getEmail());
		res = new Response(null, null, false, null, task, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle complete activity purchase order request.
	 *
	 * @param req the request.
	 *
	**/
	void completePurchaseOrder(Request req)
	{
		Response res;
		boolean bool = db.insertPurchaseOrder(req.getProposalOrder());
		res = new Response(null, null, bool, null, null, null, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Handle generate purchase order from proposal request.
	 *
	 * @param req the request.
	 *
	**/
	void generatePurchaseOrder(Request req)
	{
		Response res;
		List<PurchaseOrder> orders = db.generatePurchaseOrder(req.getProposalOrder());
		res = new Response(null, null, false, null, null, orders, null, null, null);
		sendResponse(res);
	}
	
	/**
	 * Run the thread.
	 * 
	**/
	@Override
	public void run()
	{

		try
		{
			inputStream = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
  
		Request req;
		boolean state = true;
		while (state)
		{
			try 
			{
				req = readRequest();
				switch(req.getService()) 
				{
					case "close":
						close();
						state = false;
						break;
					case "login":
						login(req);
						break;
					case "logout":
						logout(req);
						break;
					case "signin":
						signin(req);
						break;
					case "searchWine":
						searchWine(req);
						break;
					case "changePass":
						changePass(req);
						break;
					case "resetPass":
						resetPass(req);
						break;
					case "searchCustomers":
						searchCustomers(req);
						break;
					case "fireEmployee":
						fireEmployee(req);
						break;
					case "unfireEmployee":
						unfireEmployee(req);
						break;
					case "searchSalesOrders":
						searchCustomerOrders(req, "salesorders");
						break;
					case "searchProposalsPurchase":
						searchCustomerOrders(req, "proposalspurchase");
						break;
					case "searchPurchaseOrders":
						searchPurchaseOrders(req);
						break;
					case "report":
						report(req);
						break;
					case "createPromotion":
						createPromotion(req);
						break;
					case "getPromotions":
						getPromotions(req);
						break;
					case "makeProposalOrder":
						makeProposalOrder(req);
						break;
					case "getOperators":
						getOperators();
						break;
					case "completeSalesOrder":
						handleProposalOrder(req, "salesorders");
						break;
					case "completeProposalPurchase":
						handleProposalOrder(req, "proposalspurchase");
						break;
					case "rejectSalesOrder":
						handleProposalOrder(req, "");
						break;
					case "confirmSalesOrder":
						handleProposalOrder(req, "");
						break;
					case "completePurchaseOrder":
						completePurchaseOrder(req);
						break;
					case "generatePurchaseOrder":
						generatePurchaseOrder(req);
						break;
					case "getNotifications":
						getNotifications(req);
						break;
					case "createNotification":
						createNotification(req);
						break;
					case "getHistory":
						getHistory(req);
						break;
					case "getAssignment":
						getAssignment(req);
						break;
						
				}//switch
			}
			catch (NullPointerException e)
			{
				e.printStackTrace();
				try 
				{
					this.socket.close();
					state = false;
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
							
					
		}//while true
		
	}//run	
}