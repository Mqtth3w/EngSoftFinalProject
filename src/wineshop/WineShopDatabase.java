/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.sql.PreparedStatement;

/**
 *
 * The class {@code WineShopDatabase} defines the server database
 * and give all methods to work over the "system memory".
 * 
**/

public class WineShopDatabase 
{
	private static final String DBURL = "jdbc:mysql://localhost:3306/wineshopdb?";
	private static final String ARGS = "createDatabaseIfNotExist=true&serverTimezone=CET";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "";
	private List<Actor> employees = new LinkedList<Actor>();
	private List<ProposalOrder> orders = new LinkedList<ProposalOrder>();
	private int id_neword = 0; 
	private List<Actor> confirmEmps = new LinkedList<Actor>();
	private int assigned;
	private int index;

	/**
	 * Class constructor.
	 *
	**/
	public WineShopDatabase()
	{
		//create tables if not exist
		start_users(); 
		start_wines(); 
		start_salesOrders();
		start_notifications();
		start_proposalsPurchase();
		start_purchaseOrders();
		start_reports();
		start_employeereports();
		start_winereports();
		start_promotions();
		start_newOrders();
		start_ids();
		start_assignments();
	}
	
	/**
	 * Execute SQL insert, update, delete.
	 *
	 * @param update the SQL update to be execute.
	**/
	private void executeUpdate(String update) 
	{
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			stmt.executeUpdate(update);
		    
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}
	
	/**
	 * Create table users if not exists, 
	 * and insert default users.
	 *
	**/
	private void start_users()
	{
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			String create = "create table if not exists users  " +
					  "(username VARCHAR(150)," +
			          " password VARCHAR(150), " + //save the password hash with SHA3-256
			          " initialPassword VARCHAR(150), " + //save the password hash with SHA3-256
			          " name VARCHAR(50), " +
			          " surname VARCHAR(50), " +
			          " FiscalCode VARCHAR(50) unique, " +
			          " email VARCHAR(50) unique, " + 
			          " phone VARCHAR(20), " + 
			          " address VARCHAR(1000), " +
			          " role VARCHAR(20) default 'customer' check (role in ('employee','admin','supplier','courier','customer','fired')), " + 
			          " primary key ( username ));";
			
			stmt.executeUpdate(create); 
			List<Actor> users = List.of( //password: 1234
							new Actor("admin1","Pippo", "Tango", "TNOPOP", "admin1@wineshop.com", "+39 596211237", 
									"via admin1", "1d6442ddcfd9db1ff81df77cbefcd5afcc8c7ca952ab3101ede17a84b866d3f3", "", "admin"),
							
							new Actor("supplier1","Zir", "Zeta", "ZIEZTA", "supplier@wineshop.com", "+39 359611241", 
									"via supplier", "1d6442ddcfd9db1ff81df77cbefcd5afcc8c7ca952ab3101ede17a84b866d3f3", "", "supplier"),
							
							new Actor("courier1","Coriandolo", "Volante", "COIVAN", "courier@wineshop.com", 
									"+39 396211242", "via courier", "1d6442ddcfd9db1ff81df77cbefcd5afcc8c7ca952ab3101ede17a84b866d3f3", "", "courier"),
							
							new Actor("emp1","Mario", "Rossi", "RSIMRI", "employee1@wineshop.com", 
									"+39 596211238", "via emp1", "1d6442ddcfd9db1ff81df77cbefcd5afcc8c7ca952ab3101ede17a84b866d3f3", 
									"1d6442ddcfd9db1ff81df77cbefcd5afcc8c7ca952ab3101ede17a84b866d3f3", "employee"),
							
							new Actor("emp2","Alessia", "Re", "AESREA", "employee2@wineshop.com", 
									"+39 756211239", "via emp2", "1d6442ddcfd9db1ff81df77cbefcd5afcc8c7ca952ab3101ede17a84b866d3f3", 
									"1d6442ddcfd9db1ff81df77cbefcd5afcc8c7ca952ab3101ede17a84b866d3f3", "employee"),
							
							new Actor("emp3","Maria", "Rossi", "RSIMRA", "employee3@wineshop.com", 
									"+39 359621240", "via emp3", "1d6442ddcfd9db1ff81df77cbefcd5afcc8c7ca952ab3101ede17a84b866d3f3", 
									"1d6442ddcfd9db1ff81df77cbefcd5afcc8c7ca952ab3101ede17a84b866d3f3", "employee"));
			
			String insertSql = "INSERT IGNORE INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		    PreparedStatement pstmt = conn.prepareStatement(insertSql);

		    for (Actor u : users)
		    {
		    	pstmt.setString(1, u.getUsername());
			    pstmt.setString(2, u.getPassword());
		        pstmt.setString(3, u.getDefaultPass());
		        pstmt.setString(4, u.getName());
		        pstmt.setString(5, u.getSurname());
		        pstmt.setString(6, u.getFiscalCode());
		        pstmt.setString(7, u.getEmail());
		        pstmt.setString(8, u.getPhone());
		        pstmt.setString(9, u.getAddress());
		        pstmt.setString(10, u.getRole());
		        pstmt.addBatch();
		    }
		    pstmt.executeBatch();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}
	
	/**
	 * Create table wines if not exists, 
	 * and insert default wines.
	 *
	**/
	private void start_wines()
	{
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			String create = "create table if not exists wines  " +
			          "(name varchar(50), " +
			          " productor varchar(50), " +
			          " origin varchar(50), " +
			          " yearr year, " +
			          " TechnicalNotes varchar(1000), " +
			          " GrapeVariety varchar(1000), " + 
			          " quality float, " + 
			          " price float, " +
			          " supplierPrice float, " +
			          " quantity int, " +
			          " SalesNumber int, " +
			          " primary key (name, yearr));";
			
			stmt.executeUpdate(create);
			var wines = List.of(
							new Wine("Champagne", "Pommery", "France", 2022, "Harvest done manually", "Pinot nero 38% Pinot meunier 32% Chardonnay 30%", 95.00f, 65.50f, 45.50f, 100, 0),
							new Wine("Prosecco", "Armando", "Italy", 2021, "Bubbly wine", "Glera 30%",  91.00f, 50.00f, 30.00f, 100, 0),
							new Wine("Barolo", "IlVeronese", "Verona, Italy", 2018, "Only grapes from the Piedmontese Langhe", "Nebbiolo", 86.00f, 30.00f, 20.00f, 100, 0),
							new Wine("Supertuscan", "Tosco", "Italy", 2019, "Traditional", "Tuscan vines", 87.40f, 26.50f, 16.50f, 0, 0),
							new Wine("Cabernet Sauvignon", "Bordeaux srl", "France", 1924, "Red", "Black grape variety", 98.60f, 200.00f, 180.00f, 60, 0),
							new Wine("Merlot", "Bordolese", "France", 2009, "Red, bright fruit aroma", "Black grape variety", 95.00f, 150.00f, 130.00f, 100, 0),
							new Wine("Arién", "LosAmigos", "Spain", 2020, "White, serve 7-12 degrees", "Vitis vinifera", 93.00f, 78.00f, 58.00f, 30, 0),
							new Wine("Tempranillo", "TheSpanish", "Spain", 2015, "decant one hour, 15-20 degrees", "Black grape variety", 89.43f, 67.67f, 47.67f, 50, 0),
							new Wine("Pinot Noir", "Louis", "New Zeland", 2022, "Red, decant 30 minutes, 12-15 degrees", "Pinot Noir", 90.20f, 70.00f, 50.00f, 120, 0),
							new Wine("Sauvignon Blanc", "Louis", "France", 2021, "Wild vines", "White grape variety", 88.00f, 72.89f, 52.89f, 20, 0));
			
			String insertSql = "INSERT IGNORE INTO wines VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		    PreparedStatement pstmt = conn.prepareStatement(insertSql);

		    for (Wine w : wines) {
		        pstmt.setString(1, w.getName());
		        pstmt.setString(2, w.getProductor());
		        pstmt.setString(3, w.getOrigin());
		        pstmt.setString(4, Integer.toString(w.getYear()));
		        pstmt.setString(5, w.getTechnicalNotes());
		        pstmt.setString(6, w.getGrapeVariety());
		        pstmt.setString(7, Double.toString(w.getQuality()));
		        pstmt.setString(8, Double.toString(w.getPrice()));
		        pstmt.setString(9, Double.toString(w.getSupplierPrice()));
		        pstmt.setString(10, Integer.toString(w.getQuantity()));
		        pstmt.setString(11, Long.toString(w.getSalesNumber()));
		       
		        pstmt.addBatch();
		    }
		    pstmt.executeBatch();
		    pstmt.close();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}
	
	/**
	 * Create table salesorders if not exists.
	 *
	**/
	private void start_salesOrders()
	{
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			String create = "create table if not exists salesorders  " +
			          "(id int AUTO_INCREMENT,"
			          + "id_order int, " +
			          " wine_name varchar(50), " +
			          " wine_year year, " +
			          " order_date date, " +
			          " assignment_date date, " +
			          " customerEmail VARCHAR(50), " +
			          " employeeEmail VARCHAR(50), " + 
			          " quantity int, " + 
			          " tot_price float, " +
			          " address VARCHAR(1000), " +
			          " state VARCHAR(40) check (state in ('rejected','expired','completed','waitingconfirm','confirmed')), " + 
			          " primary key (id)," +
			          " foreign key (customerEmail) references users(email)," +
			          " foreign key (employeeEmail) references users(email)," +
			          " foreign key (wine_name,wine_year) references wines(name,yearr));";

			stmt.executeUpdate(create);		
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}
	
	/**
	 * Create table notifications if not exists.
	 *
	**/
	private void start_notifications()
	{
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			String create = "create table if not exists notifications  " +
					"(wine_name varchar(50), " +
			          " wine_year year, " +
			          " customerEmail VARCHAR(50), " +
			          " quantity int, " +  
			          " primary key (customerEmail,wine_name,wine_year)," +
			          " foreign key (customerEmail) references users(email)," +
			          " foreign key (wine_name,wine_year) references wines(name,yearr));";
			
			stmt.executeUpdate(create);		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}
	
	/**
	 * Create table proposalspurchase if not exists.
	 *
	**/
	private void start_proposalsPurchase()
	{
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			String create = "create table if not exists proposalspurchase  " +
					"(id int AUTO_INCREMENT, " + 
					"id_order int, " +
			          " wine_name varchar(50), " +
			          " wine_year year, " +
			          " order_date date, " +
			          " assignment_date date, " +
			          " customerEmail VARCHAR(50), " +
			          " employeeEmail VARCHAR(50), " +  
			          " quantity int, " + 
			          " tot_price float, " +
			          " address VARCHAR(1000), " +
			          " state VARCHAR(30) check (state in ('completed','expired')), " + 
			          " primary key (id)," +
			          " foreign key (customerEmail) references users(email)," +
			          " foreign key (employeeEmail) references users(email)," +
			          " foreign key (wine_name,wine_year) references wines(name,yearr));";
			
			stmt.executeUpdate(create);		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}
	
	/**
	 * Create table purchaseorders if not exists.
	 *
	**/
	private void start_purchaseOrders()
	{
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			String create = "create table if not exists purchaseorders  " +
					"(id int AUTO_INCREMENT, "
					+ "id_order int, " +
			          " wine_name varchar(50), " +
			          " wine_year year, " +
			          " order_date date, " +
			          " assignment_date date, " +
			          " employeeEmail VARCHAR(50), " +  
			          " quantity int, " + 
			          " tot_price float, " +
			          " state VARCHAR(30) check (state in ('completed','expired')), " + 
			          " primary key (id)," +
			          " foreign key (employeeEmail) references users(email)," +
			          " foreign key (wine_name,wine_year) references wines(name,yearr));";
			
			stmt.executeUpdate(create);		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}
	
	/**
	 * Create table neworders if not exists.
	 * Orders to be scheduled by RR.
	 *
	**/
	private void start_newOrders()
	{
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			String create = "create table if not exists neworders  " +
					"( id_order int, " +
			          " wine_name varchar(50), " +
			          " wine_year year, " +
			          " order_date date, " +
			          " customerEmail VARCHAR(50), " + 
			          " quantity int, " + 
			          " tot_price float, " +
			          " address VARCHAR(1000), " +
			          " type VARCHAR(40) check (type in ('salesorder','proposalpurchase','purchaseorder','confirmed')), " + 
			          " primary key (id_order,wine_name,wine_year)," +
			          " foreign key (customerEmail) references users(email)," +
			          " foreign key (wine_name,wine_year) references wines(name,yearr));";

			stmt.executeUpdate(create);	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}
	
	/**
	 * Gets the current id_order to handle it locally.
	 *
	**/
	private void start_ids()
	{		
		String selIdNeword = "SELECT MAX(id_order) AS greatest_id " +
				"FROM (SELECT MAX(id_order) AS id_order FROM neworders UNION ALL " +
				"SELECT MAX(id_order) AS id_order FROM salesorders UNION ALL " +
				"SELECT MAX(id_order) AS id_order FROM proposalspurchase UNION ALL " +
				"SELECT MAX(id_order) AS id_order FROM purchaseorders ) AS orders;";
		
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
	
			ResultSet rsetIdNeword = stmt.executeQuery(selIdNeword);
			if(rsetIdNeword.next()) 
			{				
				this.id_neword = rsetIdNeword.getInt("greatest_id");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}
	
	/**
	 * Create table reports if not exists.
	 *
	**/
	private void start_reports()
	{
		String create = "create table if not exists reports  " +
				"( yearr year, " +
		          " month int, " +
		          " income float, " +
		          " expense float, " +
		          " totalsell int, " +  
		          " totalavaiable int, " + 
		          " primary key (yearr, month));";
		
		executeUpdate(create);		
	}
	
	/**
	 * Create table employeereports if not exists.
	 *
	**/
	private void start_employeereports()
	{
		String create = "create table if not exists employeereports  " +
				"( yearr year, " +
		          " month int, " +
		          " email VARCHAR(50), " +
		          " totalcompleted int, " +  
		          " totalexpired int, " + 
		          " primary key (yearr, month, email)," +
		          " foreign key (email) references users(email));";
		
		executeUpdate(create);		
	}
	
	/**
	 * Create table winereports if not exists.
	 *
	**/
	private void start_winereports()
	{
		String create = "create table if not exists winereports  " +
				"( yearr year, " +
		          " month int, " +
		          " wine_name VARCHAR(50), " +
		          " wine_year year, " +
		          " totalsell int, " +  
		          " totalavaiable int, " +
		          " totincome int, " +
		          " primary key (yearr, month, wine_name, wine_year)," +
		          " foreign key (wine_name,wine_year) references wines(name,yearr));";
		
		executeUpdate(create);
	}
	
	/**
	 * Create table promotions if not exists.
	 *
	**/
	private void start_promotions()
	{
		String create = "create table if not exists promotions  " +
				"( wine_name VARCHAR(50), " +
		          " wine_year year, " +
		          " inday date, " +  
		          " finday date, " +
		          " discount int, " +
		          " primary key (wine_name, wine_year, inday)," +
		          " foreign key (wine_name,wine_year) references wines(name,yearr));";
		
		executeUpdate(create);
	}
	
	/**
	 * Create table assignments if not exists.
	 *
	**/
	private void start_assignments()
	{
		String create = "create table if not exists assignments  " +
				"( id_order int, " +
		          " empEmail VARCHAR(50), " + 
		          " type VARCHAR(50), " +// check (type in ('salesorder','proposalpurchase','purchaseorder','confirmed')), " +  
		          " primary key (id_order)," +
		          " foreign key (empemail) references users(email));";
		
		executeUpdate(create);
	}

	/**
	 * Search into database wines from name and/or year.
	 *
	 * @param name the wine name.
	 * @param year the wine year.
	 * 
	 * @return the wines found list.
	**/
	List<Wine> findWine(String name, int year) 
	{
		List<Wine> wines = new LinkedList<Wine>();
		String strSelect = "";
		if(year != -1) 
		{
			strSelect = "SELECT * FROM wines WHERE name = '" + name + "' AND yearr = " + year;
		}
		else 
		{
			strSelect = "SELECT * FROM wines WHERE name = '" + name + "'";
		}	
		
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
							
			ResultSet rset = stmt.executeQuery(strSelect);
			
			while(rset.next()) 
			{
				Wine wine = new Wine(rset.getString("name"),rset.getString("productor"),rset.getString("origin"),rset.getInt("yearr"),rset.getString("TechnicalNotes"),
						rset.getString("GrapeVariety"),rset.getDouble("quality"),rset.getDouble("price"),rset.getDouble("supplierPrice"),rset.getInt("quantity"),rset.getInt("SalesNumber"));
				wines.add(wine);
			}
				rset.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wines;
	}
	
	/**
	 * Remove from online employees list 
	 * the employee with user name passed.
	 *
	 * @param username the employee user name.
	 * 
	**/
	void logoutEmployee(String username) 
	{
		int i = 0;
		for(Actor e : this.employees)
		{
			if(e.getUsername().equals(username))
			{
				index = (i <= index) ? index-- : index;
				this.employees.remove(e);
				break;
			}
			i++;
		}
		for(Actor e : this.confirmEmps)
		{
			if(e.getUsername().equals(username))
			{
				this.confirmEmps.remove(e);
				break;
			}
		}
	}
	
	/**
	 * Check user authentication data to login.
	 *
	 * @param u the actor user name.
	 * @param p the actor hashed password.
	 * 
	 * @return the actor if logged.
	**/
	Actor login(String u, String p)  
	{
		Actor user = null;
		String strSelect = "SELECT * FROM users WHERE username = '" + u + "' AND password = '" + p + "' AND role <> 'fired' AND role <> 'supplier' AND role <> 'courier'";				
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
		
			ResultSet rset = stmt.executeQuery(strSelect);

			if(rset.next()) 
			{				
			    user = new Actor(rset.getString("username"),rset.getString("name"),rset.getString("surname"),rset.getString("FiscalCode"),rset.getString("email"),
			    		rset.getString("phone"),rset.getString("address"),rset.getString("password"),"",rset.getString("role"));
			    
			    if(rset.getString("role").equals("employee"))
			    {
			    	employees.add(user);
			    }
			    
			    return user;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return user;
	}
	
	/**
	 * Sign in if passed a new actor.
	 *
	 * @param a the new actor.
	 * 
	 * @return the actor if signed in.
	**/
	Actor signin(Actor a)  
	{
		String strSelect = "SELECT * FROM users WHERE email = '" + a.getEmail() + "' OR FiscalCode = '" 
				+ a.getFiscalCode() + "' OR username = '" + a.getUsername() + "'";
		
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
		
			ResultSet rset = stmt.executeQuery(strSelect);

			if(rset.next()) 
			{				
			    return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String insertSql = "";
		if(a.getRole().equals("customer"))
		{
			insertSql = "INSERT INTO users(username,password,name,surname,FiscalCode,email,phone,address,role) " +
					"values ('"+a.getUsername()+"','"+a.getPassword()+"','"+a.getName()+"','"+a.getSurname()+"','"+a.getFiscalCode()+
					"','"+a.getEmail()+"','"+a.getPhone()+"','"+a.getAddress()+"','"+a.getRole()+"')";
		}
		else
		{
			insertSql = "INSERT INTO users(username,password,initialPassword,name,surname,FiscalCode,email,phone,address,role) " +
					"values ('"+a.getUsername()+"','"+a.getPassword()+"','"+a.getPassword()+"','"+a.getName()+"','"+a.getSurname()+
					"','"+a.getFiscalCode()+"','"+a.getEmail()+"','"+a.getPhone()+"','"+a.getAddress()+"','"+a.getRole()+"')";
		}
		
	    executeUpdate(insertSql);	    
		return a;
	}
	
	/**
	 * Change the actor(employee/administrator) password.
	 *
	 * @param u the actor user name.
	 * @param oldp the actor old hashed password.
	 * @param newp the actor new hashed password.
	 * 
	 * @return the success of operation(boolean).
	**/
	boolean changePass(String u, String oldp, String newp) 
	{
		Actor user = login(u, oldp);
		if(user != null)
		{
			String update = "UPDATE users SET password = '" + newp + "' WHERE username = '" 
					+ u + "' AND (role = 'admin' OR role = 'employee')";
			executeUpdate(update);
			return true;
		}
		return false;
	}
	
	/**
	 * Reset the actor(employee) password.
	 *
	 * @param u the employee user name.
	 * 
	 * @return the success of operation(boolean).
	**/
	boolean resetPass(String u) 
	{
		String strSelect = "SELECT * FROM users WHERE username = '" + u + "' AND role = 'employee'";
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			ResultSet rset = stmt.executeQuery(strSelect);
		
			if(rset.next()) 
			{				
			    String initialPass = rset.getString("initialPassword");
			    String update = "UPDATE users SET password = '" + initialPass + "' WHERE username = '" + u + "'";
				executeUpdate(update);
				return true;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Search customers into database.
	 *
	 * @param s the customer surname.
	 * 
	 * @return a list of found customers.
	**/
	List<Actor> searchCustomers(String s) 
	{
		List<Actor> customers = new LinkedList<Actor>();
		String strSelect = "SELECT * FROM users WHERE surname = '" + s + "' AND role = 'customer'";
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
		
			ResultSet rset = stmt.executeQuery(strSelect);
		
			while(rset.next()) 
			{				
			    Actor user = new Actor(rset.getString("username"),rset.getString("name"),rset.getString("surname"),rset.getString("FiscalCode"),rset.getString("email"),
			    		rset.getString("phone"),rset.getString("address"),"","",rset.getString("role"));;
			    customers.add(user);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return customers;
	}
	
	/**
	 * Gets the operators(courier,supplier).
	 * 
	 * @return a list of operators(actors).
	**/
	List<Actor> getOperators() 
	{
		List<Actor> operators = new LinkedList<>();
		String strSelect = "SELECT * FROM users WHERE role = 'supplier' OR role = 'courier'";
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
		
			ResultSet rset = stmt.executeQuery(strSelect);
		
			while(rset.next()) 
			{				
			    Actor user = new Actor("",rset.getString("name"),rset.getString("surname"),rset.getString("FiscalCode"),rset.getString("email"),
			    		rset.getString("phone"),rset.getString("address"),"","",rset.getString("role"));;
			    		operators.add(user);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return operators;
	}
	
	/**
	 * Block/unblock an employee, employee will be not able/able to login next time.
	 *
	 * @param u the employee user name.
	 * 
	 * @return the success of operation(boolean).
	**/
	boolean fireEmployee(String username, String role) 
	{
		String strSelect = "SELECT * FROM users WHERE username = '" + username + "' AND role = '" + role + "'";
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
		
			ResultSet rset = stmt.executeQuery(strSelect);
		
			if(rset.next()) 
			{			
				String update = "";
				if(role.equals("employee"))
				{
					update = "UPDATE users SET role = 'fired' WHERE username = '" + username + "'";
				}
				else if(role.equals("fired"))
				{
					update = "UPDATE users SET role = 'employee' WHERE username = '" + username + "'";
				}
				executeUpdate(update);
				return true;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Search proposals purchase/sales orders by date range.
	 *
	 * @param inDate the start date.
	 * @param finDate the end date.
	 * @param table the order type.
	 * 
	 * @return a list of orders.
	**/
	List<ProposalOrder> searchProposalOrders(LocalDate inDate, LocalDate finDate, String table) 
	{
		List<ProposalOrder> orders = new LinkedList<ProposalOrder>();
		// get expired/completed orders
		int pastid = 0;
		String strSelect = "SELECT * FROM "+table+" WHERE order_date BETWEEN '"+inDate+"' AND '"+finDate+"'";
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			ResultSet rset = stmt.executeQuery(strSelect);
	
			List<WineOrder> wineOrders = new LinkedList<WineOrder>();
			boolean bool = true;
			LocalDate ld = null;
			LocalDate ad = null;
			String state = "";
			String address = "";
			String custemail = "";
			String empemail = "";
			while(rset.next()) 
			{					
				if(bool)
				{
					pastid = rset.getInt("id_order");
					ld = rset.getDate("order_date").toLocalDate();
					custemail = rset.getString("customerEmail");
					address = rset.getString("address");
					state = rset.getString("state");
					ad = rset.getDate("assignment_date").toLocalDate();
					empemail = rset.getString("employeeEmail");
					bool = false;
				}
				
				if(pastid == rset.getInt("id_order")) 
				{
					WineOrder myWineOrder = new WineOrder(rset.getString("wine_name"),rset.getInt("wine_year"),
							rset.getInt("quantity"),rset.getDouble("tot_price"));
					wineOrders.add(myWineOrder);
				}
				else
				{
					ProposalOrder myOrder = new ProposalOrder(pastid,ld,
							ad,custemail,empemail,wineOrders,address,state);
					orders.add(myOrder);
					wineOrders = new LinkedList<WineOrder>();
					pastid = rset.getInt("id_order");
					ld = rset.getDate("order_date").toLocalDate();
					custemail = rset.getString("customerEmail");
					address = rset.getString("address");
					state = rset.getString("state");
					ad = rset.getDate("assignment_date").toLocalDate();
					empemail = rset.getString("employeeEmail");
					WineOrder myWineOrder = new WineOrder(rset.getString("wine_name"),rset.getInt("wine_year"),
							rset.getInt("quantity"),rset.getDouble("tot_price"));
					wineOrders.add(myWineOrder);
				}
			}
			//get last order
			ProposalOrder lastOrder = new ProposalOrder(pastid,ld,
					ad,custemail,empemail,wineOrders,address,state);
			orders.add(lastOrder);
			wineOrders = new LinkedList<WineOrder>();
			
			//get pending orders
			strSelect = "SELECT * FROM neworders WHERE type = '"+table+
					"' AND order_date BETWEEN '"+inDate+"' AND '"+finDate+"'";
			rset = stmt.executeQuery(strSelect);
			bool = true;
			while(rset.next()) 
			{	
				if(bool)
				{
					pastid = rset.getInt("id_order");
					ld = rset.getDate("order_date").toLocalDate();
					custemail = rset.getString("customerEmail");
					address = rset.getString("address");
					bool = false;
				}
				
				if(pastid == rset.getInt("id_order")) 
				{
					WineOrder myWineOrder = new WineOrder(rset.getString("wine_name"),rset.getInt("wine_year"),
							rset.getInt("quantity"),rset.getDouble("tot_price"));
					wineOrders.add(myWineOrder);
				}
				else
				{
					ProposalOrder myOrder = new ProposalOrder(pastid,ld,
							LocalDate.now(),custemail,"",wineOrders,address,"pending");
					orders.add(myOrder);
					wineOrders = new LinkedList<WineOrder>();
					
					pastid = rset.getInt("id_order");
					ld = rset.getDate("order_date").toLocalDate();
					custemail = rset.getString("customerEmail");
					address = rset.getString("address");
					WineOrder myWineOrder = new WineOrder(rset.getString("wine_name"),rset.getInt("wine_year"),
							rset.getInt("quantity"),rset.getDouble("tot_price"));
					wineOrders.add(myWineOrder);
				}
			}
			//get last order
			ProposalOrder myOrder = new ProposalOrder(pastid,ld,
					LocalDate.now(),custemail,"",wineOrders,address,"pending");
			orders.add(myOrder);			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return orders;
	}
	
	/**
	 * Search purchase orders by date range.
	 *
	 * @param inDate the start date.
	 * @param finDate the end date.
	 * 
	 * @return a list of orders.
	**/
	List<PurchaseOrder> searchPurchaseOrders(LocalDate inDate, LocalDate finDate)
	{
		List<PurchaseOrder> orders = new LinkedList<PurchaseOrder>();
		
		int pastid = 0;
		String strSelect = "SELECT * FROM purchaseorders WHERE order_date BETWEEN '"+inDate+"' AND '"+finDate+"'";
		
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
		
				ResultSet rset = stmt.executeQuery(strSelect);
		
				List<WineOrder> wineOrders = new LinkedList<WineOrder>();
				boolean bool = true;
				LocalDate ld = null;
				LocalDate ad = null;
				String state = "";
				String empemail = "";
				while(rset.next()) 
				{
					
					if(bool)
					{
						pastid = rset.getInt("id_order");
						state = rset.getString("state");
						ad = rset.getDate("assignment_date").toLocalDate();
						ld = rset.getDate("order_date").toLocalDate();
						empemail = rset.getString("employeeEmail");
						bool = false;
					}
					
					if(pastid == rset.getInt("id_order")) 
					{
						WineOrder myWineOrder = new WineOrder(rset.getString("wine_name"),rset.getInt("wine_year"),
								rset.getInt("quantity"),rset.getDouble("tot_price"));
						wineOrders.add(myWineOrder);
					}
					else
					{
						PurchaseOrder myOrder = new PurchaseOrder(pastid,ld,ad,empemail,wineOrders,state);
						orders.add(myOrder);
						wineOrders = new LinkedList<WineOrder>();
						pastid = rset.getInt("id_order");
						state = rset.getString("state");
						ad = rset.getDate("assignment_date").toLocalDate();
						ld = rset.getDate("order_date").toLocalDate();
						empemail = rset.getString("employeeEmail");
						WineOrder myWineOrder = new WineOrder(rset.getString("wine_name"),rset.getInt("wine_year"),
								rset.getInt("quantity"),rset.getDouble("tot_price"));
						wineOrders.add(myWineOrder);
					}
				}
				//get last order
				PurchaseOrder lastOrder = new PurchaseOrder(pastid,ld,ad,empemail,wineOrders,state);
				orders.add(lastOrder);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return orders;
	}
	
	/**
	 * Gets the employee activity(assigned order).
	 *
	 * @param email the employee email.
	 * 
	 * @return a list of orders.
	**/
	List<ProposalOrder> getAssignment(String email) 
	{
		List<ProposalOrder> task = new LinkedList<>();
		
		String strSelect = "SELECT * FROM assignments WHERE empEmail = '"+email+"'";
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			ResultSet rset = stmt.executeQuery(strSelect);
	
			if(rset.next())
			{
				int id = rset.getInt("id_order");
				String type = rset.getString("type");
				strSelect = "SELECT * FROM neworders WHERE id_order = "+id;
				rset = stmt.executeQuery(strSelect);
				String cust = "";
				String address = "";
				LocalDate ld = null;
				boolean bool = true;
				List<WineOrder> wineOrders = new LinkedList<WineOrder>();
				while(rset.next())
				{
					if(bool && type.equals("purchaseorder"))
					{
						ld = rset.getDate("order_date").toLocalDate();
						bool = false;
					}
					else if(bool)
					{
						ld = rset.getDate("order_date").toLocalDate();
						cust = rset.getString("customerEmail");
						address = rset.getString("address");
						bool = false;
					}
					
					WineOrder myWineOrder = new WineOrder(rset.getString("wine_name"),rset.getInt("wine_year"),
							rset.getInt("quantity"),rset.getDouble("tot_price"));
					wineOrders.add(myWineOrder);
					
				}
				ProposalOrder myOrder = new ProposalOrder(id,ld,LocalDate.now(),cust,email,wineOrders,address,type);
				task.add(myOrder);
			}	
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return task;
	}
	
	/**
	 * Search/create the monthly report by date(year&month).
	 *
	 * @param monthYearReport the report month date.
	 * 
	 * @return the report.
	**/
	Report report(LocalDate monthYearReport)
	{
		Report report = null;
		String strSelect = "SELECT * FROM reports WHERE yearr = "+monthYearReport.getYear()+
				" AND month = "+monthYearReport.getMonthValue();
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			ResultSet rset = stmt.executeQuery(strSelect);
		
			if(rset.next()) //report already exists then get it and return it
			{
				report = SelectReport(monthYearReport, report, rset);
			}
			else //report not exists then create it and return it
			{
				report = CreateReport(monthYearReport, report);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return report;
	}
	
	/**
	 * Select the monthly report by date(year&month).
	 *
	 * @param monthYearReport the report month date.
	 * @param report the report object(passage by reference).
	 * @param rset the selected report from database.
	 * 
	 * @return the report.
	**/
	Report SelectReport(LocalDate monthYearReport, Report report, ResultSet rset)
	{
		List<WineReport> winereports = new LinkedList<WineReport>();
		List<EmployeeReport> employeereports = new LinkedList<EmployeeReport>();
		
		String strSelectEmp = "SELECT * FROM employeereports WHERE yearr = "+monthYearReport.getYear()+
				" AND month = "+monthYearReport.getMonthValue();
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			ResultSet rsetemp = stmt.executeQuery(strSelectEmp);
					
			while(rsetemp.next())
			{
				EmployeeReport empr = new EmployeeReport(rsetemp.getString("email"),
						rsetemp.getInt("totalcompleted"),rsetemp.getInt("totalexpired"));
				employeereports.add(empr);
			}

			String strSelectWine = "SELECT * FROM winereports WHERE yearr = "+monthYearReport.getYear()+
					" AND month = "+monthYearReport.getMonthValue();
			ResultSet rsetwine = stmt.executeQuery(strSelectWine);
			while(rsetwine.next())
			{
				WineReport winer = new WineReport(rsetwine.getString("wine_name"),rsetwine.getInt("wine_year"),
						rsetwine.getInt("totalsell"),rsetwine.getInt("totalavaiable"),rsetwine.getDouble("totincome"));
				winereports.add(winer);
			}

			LocalDate rdate = LocalDate.of(rset.getInt("yearr"), rset.getInt("month"), 1);
			report = new Report(rset.getDouble("income"),rset.getDouble("expense"),rset.getInt("totalsell"),
					rset.getInt("totalavaiable"),rdate,winereports,employeereports);
			return report;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Create the monthly report by date(year&month).
	 *
	 * @param monthYearReport the report month date.
	 * @param report the report object(passage by reference).
	 * 
	 * @return the report.
	**/
	Report CreateReport(LocalDate monthYearReport, Report report)
	{
		List<WineReport> winereports = new LinkedList<WineReport>();
		List<EmployeeReport> employeereports = new LinkedList<EmployeeReport>();
		double income = 0;
		double expense = 0;
		int totsell = 0;
		int totavaiable = 0;
		//get wines finance info
		String selectWines = "SELECT wine_name, wine_year, SUM(salesorders.quantity) AS totsell, "+
						"SUM(salesorders.tot_price) AS totincome, wines.quantity AS totavaiable "+
				"FROM salesorders JOIN wines ON wine_name = name AND wine_year = yearr "+
				"WHERE state = 'completed' AND YEAR(order_date) = '"+monthYearReport.getYear()+
				"' AND EXTRACT(MONTH FROM order_date) = '"+monthYearReport.getMonthValue()+"'"+
				"GROUP BY wine_name, wine_year, totavaiable";
		
		String insWinesReport = "INSERT INTO winereports VALUES ";
		int l1 = insWinesReport.length();
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			ResultSet rsetwine = stmt.executeQuery(selectWines);
			
			while(rsetwine.next()) 
			{
				WineReport winer = new WineReport(rsetwine.getString("wine_name"),rsetwine.getInt("wine_year"),
						rsetwine.getInt("totsell"),rsetwine.getInt("totavaiable"),rsetwine.getDouble("totincome"));
				winereports.add(winer);
				income = income + rsetwine.getDouble("totincome");
				totsell = totsell + rsetwine.getInt("totsell");
				insWinesReport = insWinesReport + "("+monthYearReport.getYear()+","+monthYearReport.getMonthValue()+",'"
						+rsetwine.getString("wine_name")+"',"+rsetwine.getInt("wine_year")+","+rsetwine.getInt("totsell")
						+","+rsetwine.getInt("totavaiable")+","+rsetwine.getDouble("totincome")+"),";
			}
			if(insWinesReport.length() > l1)
			{
				String inswr = insWinesReport.substring(0, insWinesReport.length() - 1);
				executeUpdate(inswr);
			}
	
			String selectCompExp = "SELECT employeeEmail, SUM(totcompleted) AS totcompleted, "
					+ "SUM(totexpired) AS totexpired FROM "
					+ "(SELECT employeeEmail, "
					+ "COUNT(DISTINCT id_order, IF(state='completed',1,null)) AS totcompleted, "
					+ "COUNT(DISTINCT id_order, IF(state='expired',1,null)) AS totexpired "
					+ "FROM salesorders "
					+ "WHERE YEAR(order_date) = '"+monthYearReport.getYear()+"' "
					+ "AND EXTRACT(MONTH FROM order_date) = '"+monthYearReport.getMonthValue()+"' "
					+ "GROUP BY employeeEmail "
					+ "UNION ALL "
					+ "SELECT employeeEmail, "
					+ "COUNT(DISTINCT id_order, IF(state='completed',1,null)) AS totcompleted, "
					+ "COUNT(DISTINCT id_order, IF(state='expired',1,null)) AS totexpired "
					+ "FROM purchaseorders "
					+ "WHERE YEAR(order_date) = '"+monthYearReport.getYear()+"'  "
					+ "AND EXTRACT(MONTH FROM order_date) = '"+monthYearReport.getMonthValue()+"' "
					+ "GROUP BY employeeEmail "
					+ "UNION ALL "
					+ "SELECT employeeEmail, "
					+ "COUNT(DISTINCT id_order, IF(state='completed',1,null)) AS totcompleted, "
					+ "COUNT(DISTINCT id_order, IF(state='expired',1,null)) AS totexpired "
					+ "FROM proposalspurchase "
					+ "WHERE YEAR(order_date) = '"+monthYearReport.getYear()+"' "
					+ "AND EXTRACT(MONTH FROM order_date) = '"+monthYearReport.getMonthValue()+"' "
					+ "GROUP BY employeeEmail) AS tasks "
					+ "GROUP BY employeeEmail ";
			
			
			String insEmpReport = "INSERT INTO employeereports VALUES ";
			ResultSet rsetcompexp = stmt.executeQuery(selectCompExp);
			l1 = insEmpReport.length();
			
			while(rsetcompexp.next()) 
			{
				EmployeeReport empr = new EmployeeReport(rsetcompexp.getString("employeeEmail"),
						rsetcompexp.getInt("totcompleted"),rsetcompexp.getInt("totexpired"));
				employeereports.add(empr);
				insEmpReport = insEmpReport + "("+monthYearReport.getYear()+","+monthYearReport.getMonthValue()+",'"
						+rsetcompexp.getString("employeeEmail")+"',"+rsetcompexp.getInt("totcompleted")+","+rsetcompexp.getInt("totexpired")+"),";
			}
			if(insEmpReport.length() > l1)
			{
				String inser = insEmpReport.substring(0, insEmpReport.length() - 1);
				executeUpdate(inser);
			}
			
			String selectExpense = "SELECT SUM(tot_price) AS totprice FROM purchaseorders "
					+"WHERE YEAR(order_date) = '"+monthYearReport.getYear()
					+"' AND EXTRACT(MONTH FROM order_date) = '"+monthYearReport.getMonthValue()+"'";
			ResultSet rsetexpen = stmt.executeQuery(selectExpense);
			if(rsetexpen.next()) 
			{
				expense = rsetexpen.getDouble("totprice");
			}
			
			String selectAaiable = "SELECT SUM(quantity) AS totav FROM wines";
			ResultSet rsetav = stmt.executeQuery(selectAaiable);
			if(rsetav.next()) 
			{
				totavaiable = rsetav.getInt("totav");
			}

			LocalDate rdate = LocalDate.of(monthYearReport.getYear(), monthYearReport.getMonthValue(), 1);
			report = new Report(income,expense,totsell,totavaiable,rdate,winereports,employeereports);
			
			String insReport = "INSERT INTO reports VALUES ("+monthYearReport.getYear()+","
			+monthYearReport.getMonthValue()+","+income+","+expense+","+totsell+","+totavaiable+")";
			executeUpdate(insReport);
			return report;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Create a wine promotion.
	 *
	 * @param name the wine name.
	 * @param year the wine year.
	 * @param discount the wine discount.
	 * @param inDate the promotion start date.
	 * @param finDate the promotion end date.
	 * 
	 * @return the success of operation(boolean).
	**/
	boolean createPromotion(String name, int year, int discount, LocalDate inDate, LocalDate finDate)
	{
		//prevent date overlap 
		String query2 = "SELECT * FROM promotions WHERE " +
				" (inday BETWEEN '"+inDate+"' AND '"+finDate+"' AND wine_name = '"+name+"' AND wine_year = "+year+")" +
				" OR (finday BETWEEN '"+inDate+"' AND '"+finDate+"' AND wine_name = '"+name+"' AND wine_year = "+year+")";
		
		String query = "SELECT * FROM wines WHERE name = '"+name+"' AND yearr = "+year;
		
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();Statement stmt2 = conn.createStatement();){
		
			ResultSet rset = stmt.executeQuery(query);
			ResultSet rset2 = stmt2.executeQuery(query2);
			
			if(!rset.next()) //wine not exist
			{
				return false;
			}
			else if (rset2.next()) //promotion already exists for the interval
			{
				return false;
			}
			else 
			{
				query = "INSERT INTO promotions VALUES ('"+name+"',"+year+",'"+inDate+"','"+finDate+"',"+discount+")";
				executeUpdate(query);
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Gets current promotions.
	 * 
	 * @return a list of promotion.
	**/
	List<Promotion> getPromotions()
	{
		List<Promotion> promotions = new LinkedList<Promotion>();
		String query = "SELECT * FROM promotions WHERE CURRENT_DATE BETWEEN inday AND finday";
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
		
			ResultSet rset = stmt.executeQuery(query);
			
			while(rset.next()) 
			{
				Promotion promo = new Promotion(rset.getDate("inday").toLocalDate(),rset.getDate("finday").toLocalDate(),
						rset.getInt("discount"),rset.getString("wine_name"),rset.getInt("wine_year"));
				promotions.add(promo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return promotions;
	}
	
	/**
	 * Insert into database a proposal purchase/sales order.
	 *
	 * @param order the order.
	 * @param type the order type.
	 * 
	**/
	void insertProposalOrder(ProposalOrder order, String type)
	{
		String insquery = "INSERT INTO "+type+"(id_order,wine_name,wine_year,order_date,assignment_date,"
				+ "customerEmail,employeeEmail,quantity,tot_price,address,state) VALUES "; 
		for(WineOrder wo : order.getWines())
		{
			insquery = insquery + "("+order.getId()+",'"+wo.getWineName()+"',"+wo.getWineYear()+",'"+order.getOrderDate()+"','"
					+order.getAssignDate()+"','"+order.getCustomerEmail()+"','"+order.getEmployeeEmail()+"',"+
					wo.getQuantity()+","+wo.getPrice()+",'"+order.getAddress()+"','"+order.getState()+"'),";
		}
		String inso = insquery.substring(0, insquery.length() - 1);
		executeUpdate(inso);
	}
	
	/**
	 * Check if an order is in assignments table.
	 *
	 * @param id the order id.
	 * 
	 * @return false if found.
	**/
	boolean checkExpired(int id)
	{
		String query = "SELECT * FROM assignments WHERE id_order = "+id;
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
		
			ResultSet rset = stmt.executeQuery(query);
			if(rset.next()) 
			{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Complete/expire/reject/confirm sales order/proposal purchase.
	 *
	 * @param order the order.
	 * @param type the order type.
	 * 
	 * @return the success of operation(boolean).
	**/
	synchronized boolean handleProposalOrder(ProposalOrder order, String type) 
	{   		
		if(order.getState().equals("expired"))
		{
			insertProposalOrder(order, type);
			String delquery = "DELETE FROM assignments WHERE id_order = "+order.getId();
			executeUpdate(delquery);
		}
		else if(order.getState().equals("completed"))
		{
			if(checkExpired(order.getId()))
			{
				return false;
			}
			if(type.equals("salesorders"))
			{
				insertProposalOrder(order, type);
				String delquery = "DELETE FROM neworders WHERE id_order = "+order.getId();
				executeUpdate(delquery);
				delquery = "DELETE FROM assignments WHERE id_order = "+order.getId();
				executeUpdate(delquery);
			}
			else //proposalspurchase
			{
				insertPurchaseOrder(order);
				order.setState("waitingconfirm");
				insertProposalOrder(order, "salesorders");
				order.setState("completed");
				insertProposalOrder(order, "proposalspurchase");
			}
		}
		else if(order.getState().equals("rejected"))
		{
			String update = "UPDATE salesorders SET state = 'rejected' WHERE id_order = "+order.getId();
			executeUpdate(update);
		}
		else if(order.getState().equals("confirmedcompleted"))
		{
			String update = "UPDATE salesorders SET state = 'completed' WHERE id_order = "+order.getId();
			executeUpdate(update);
			String delquery = "DELETE FROM neworders WHERE id_order = "+order.getId();
			executeUpdate(delquery);
			delquery = "DELETE FROM assignments WHERE id_order = "+order.getId();
			executeUpdate(delquery);
		}
		else //confirmed
		{
			String update = "UPDATE salesorders SET state = 'confirmed' WHERE id_order = "+order.getId();
			executeUpdate(update);
			makeProposalOrder(order);
		}
		return true;
	}
	
	/**
	 * Generate sales order from proposal purchase.
	 *
	 * @param order the proposal purchase.
	 * 
	 * @return a list of one sales order.
	**/
	List<PurchaseOrder> generatePurchaseOrder(ProposalOrder order) 
	{
		List<PurchaseOrder> orders = new LinkedList<PurchaseOrder>();
		
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
				
				List<WineOrder> wineOrders = new LinkedList<WineOrder>();
				for(WineOrder wo : order.getWines())
				{
					String strSelect = "SELECT supplierPrice FROM wines WHERE name = '"
							+wo.getWineName()+"' AND yearr  = "+wo.getWineYear();
					ResultSet rset = stmt.executeQuery(strSelect);
					if(rset.next())
					{
						WineOrder myWineOrder = new WineOrder(wo.getWineName(),wo.getWineYear(),
								wo.getQuantity(),rset.getDouble("supplierPrice")*wo.getQuantity());
						wineOrders.add(myWineOrder);
					}
				}
				PurchaseOrder myOrder = new PurchaseOrder(order.getId(),order.getOrderDate(),
						order.getAssignDate(),order.getEmployeeEmail(),wineOrders,"purchaseorder");
				orders.add(myOrder);
				
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return orders;
	}
	
	/**
	 * Insert into database a purchase order.
	 *
	 * @param order the order.
	 * 
	 * @return the success of operation(boolean).
	**/
	boolean insertPurchaseOrder(ProposalOrder order) 
	{
		String insord = "INSERT INTO purchaseorders(id_order,wine_name,wine_year,order_date,"
				+ "assignment_date,employeeEmail,quantity,tot_price,state) VALUES ";
		String update = "";
		for(WineOrder wo : order.getWines())
		{
			insord = insord + "("+order.getId()+",'"+wo.getWineName()+"',"+wo.getWineYear()
				+",'"+order.getOrderDate()+"','"+order.getAssignDate()+"','"+order.getEmployeeEmail()
				+"',"+wo.getQuantity()+","+wo.getPrice()+",'"+order.getState()+"'),";
			
			update = "UPDATE wines SET quantity = quantity + "+wo.getQuantity()+" WHERE name = '"
					+wo.getWineName()+"' AND yearr = "+wo.getWineYear()+";";
			executeUpdate(update);
		}
		String ins = insord.substring(0, insord.length() - 1);
		executeUpdate(ins);
		
		String delquery = "DELETE FROM neworders WHERE id_order = "+order.getId();
		executeUpdate(delquery);
		delquery = "DELETE FROM assignments WHERE id_order = "+order.getId();
		executeUpdate(delquery);
		
		return true;
	}
	
	/**
	 * Check threshold for all wines.
	 *
	 * @param orderDate the sales order date.
	 * 
	**/
	void checkQuantity(LocalDate orderDate)
	{
		// threshold 15 with less than 100 sales, otherwise 15% of the number of sales
		String strSelect = "SELECT * FROM wines WHERE quantity < " 
				+"(CASE WHEN SalesNumber < 100 THEN 15 ELSE SalesNumber*0.15 END) "
				+ "AND (name, yearr) NOT IN (SELECT wine_name, wine_year FROM neworders WHERE type = 'purchaseorder')"; 
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			ResultSet rset = stmt.executeQuery(strSelect);
			String insert = "INSERT INTO neworders(id_order,wine_name,wine_year,"
					+ "order_date,quantity,tot_price,type) VALUES ";
			int l = insert.length();
			
			this.id_neword++;
			while(rset.next()) //make "new purchase order" for employee with wines under threshold
			{			
				int quantity = (int) ((100 > rset.getInt("SalesNumber")) ? 15 : rset.getInt("SalesNumber")*0.15)*2;
				//buy double the threshold
				insert = insert + "("+this.id_neword+",'"+rset.getString("name")+"',"+rset.getInt("yearr")
						+",'"+orderDate+"',"+quantity+","+rset.getDouble("supplierPrice")*quantity+",'purchaseorder'),";
			}
			
			if(insert.length() > l)
			{
				String insno = insert.substring(0, insert.length() - 1);
				executeUpdate(insno);
			}
			else
			{
				this.id_neword--;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Insert into database(neworders) a new proposal/sales from customer.
	 *
	 * @param order the order.
	 * 
	 * @return the success of operation(boolean).
	**/
	boolean makeProposalOrder(ProposalOrder order)
	{
		LocalDate orderDate = order.getOrderDate();
		String cEmail = order.getCustomerEmail();
		List<WineOrder> wineOrders = order.getWines();
		String address = order.getAddress();
		String state = order.getState();
		String insert = "INSERT INTO neworders VALUES ";
		String update = "";
		
		int id;
		if(state.equals("confirmed"))
		{
			id = order.getId();
		}
		else
		{
			id = ++this.id_neword;
		}
		
		for(WineOrder wo : wineOrders)
		{
			insert = insert + "("+id+",'"+wo.getWineName()+"',"+wo.getWineYear()+",'"+orderDate+"','"+
					cEmail+"',"+wo.getQuantity()+","+wo.getPrice()+",'"+address+"','"+state+"'),";
			
			if(state.equals("salesorder") || state.equals("confirmed")) //only sales orders decrease quantity
			{
				if(wo.getQuantity()%10==0)
				{
					update = "UPDATE wines SET price = price + 0.05 WHERE name = '"
							+wo.getWineName()+"' AND yearr = "+wo.getWineYear()+"; ";
					executeUpdate(update);
				}
				
				update = "UPDATE wines SET quantity = quantity - "+wo.getQuantity()+" WHERE name = '"
						+wo.getWineName()+"' AND yearr = "+wo.getWineYear()+";";
				executeUpdate(update);
				
				update = "UPDATE wines SET SalesNumber = SalesNumber + "+wo.getQuantity()+" WHERE name = '"
						+wo.getWineName()+"' AND yearr = "+wo.getWineYear()+";";
				executeUpdate(update);
				
			}
		}
		String insno = insert.substring(0, insert.length() - 1);
		executeUpdate(insno + ";");
		if(state.equals("salesorder") || state.equals("salesorder")) // check threshold
		{
			//executeUpdate(update);
			checkQuantity(orderDate);
		}		
		return true;
	}
	
	/**
	 * Check if there are expired assignments and save them like expired.
	 *
	**/
	void checkAssignments()
	{		
		for(Actor e : this.confirmEmps)
		{
			this.employees.add(e);			
		}
		confirmEmps.clear();
		
		String select = "SELECT * FROM assignments";
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();Statement stmt2 = conn.createStatement();){
			
			ResultSet rset = stmt.executeQuery(select);
			while(rset.next()) 
			{
				select = "SELECT * FROM neworders WHERE id_order = "+rset.getInt("id_order");
				String insert = "INSERT INTO purchaseorders(id_order,wine_name,wine_year,order_date,"
						+ "assignment_date,employeeEmail,quantity,tot_price,state) VALUES ";
				String type = rset.getString("type");
				List<WineOrder> wineOrders = new LinkedList<WineOrder>();
				LocalDate ld = null;
				String address = "";
				String custemail = "";
				String table = "";
				boolean bool = true;
				
				if(type.equals("confirmed")) 
				{
					String up = "UPDATE salesorders SET state = 'expired' WHERE id_order = "+rset.getInt("id_order");
					executeUpdate(up);	
					up = "UPDATE neworders SET type = 'salesorder' WHERE id_order = "+rset.getInt("id_order");
					executeUpdate(up);
				}
				else
				{
					ResultSet rset2 = stmt2.executeQuery(select);
					while(rset2.next())
					{
						if(type.equals("purchaseorder"))  
						{
							insert = insert + "("+rset2.getInt("id_order")+",'"+rset2.getString("wine_name")+"',"
									+rset2.getInt("wine_year")+",'"+rset2.getDate("order_date").toLocalDate()+"','"
									+LocalDate.now()+"','"+rset.getString("empEmail")+"',"+rset2.getInt("quantity")
									+","+rset2.getDouble("tot_price")+",'expired'),";
						}
						else if(type.equals("proposalpurchase") || type.equals("salesorder"))
						{
							if(bool)
							{
								table = (type.equals("proposalpurchase")) ? "proposalspurchase" : "salesorders";
								ld = rset2.getDate("order_date").toLocalDate();
								custemail = rset2.getString("customerEmail");
								address = rset2.getString("address");
								bool = false;
							}
							WineOrder myWineOrder = new WineOrder(rset2.getString("wine_name"),rset2.getInt("wine_year"),
									rset2.getInt("quantity"),rset2.getDouble("tot_price"));
							wineOrders.add(myWineOrder);
						}
						
					}
					if(type.equals("purchaseorder"))  
					{
						String inso = insert.substring(0, insert.length() - 1);
						executeUpdate(inso);
					}
					else if(type.equals("proposalpurchase") || type.equals("salesorder"))
					{
						ProposalOrder myOrder = new ProposalOrder(rset.getInt("id_order"),ld,
								LocalDate.now(),custemail,rset.getString("empEmail"),wineOrders,address,"expired");
						handleProposalOrder(myOrder, table);
					}
				}//else
				String del = "DELETE FROM assignments WHERE id_order = "+rset.getInt("id_order");
				executeUpdate(del);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Assign activities(orders) to online employees.
	 *
	**/
	void assignments()
	{
		String insert = "";
		String email = "";
		
		for(ProposalOrder o : orders)
		{
			if(o.getState().equals("confirmed"))
			{
				orders.remove(o);
				try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
						Statement stmt = conn.createStatement();){
					
					String select = "SELECT employeeEmail FROM salesorders WHERE id_order = "+o.getId()+" LIMIT 1";
					ResultSet rset = stmt.executeQuery(select);
					if(rset.next())
					{
						email = rset.getString("employeeEmail");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				int i = 0;
				for(Actor e : employees)
				{
					if(e.getEmail().equals(email))
					{
						confirmEmps.add(e);
						employees.remove(e);
						index = (i <= index && index != 0) ? index-- : index;
						assigned++;
						insert = "INSERT INTO assignments VALUES ("+o.getId()+",'"+email+"','confirmed')";
						executeUpdate(insert);
						break;
					}
					i++;
				}	
			}
		}
		
		
		while(assigned < employees.size() && orders.size() > 0)
		{
			Actor emp = getCircleActor();
			ProposalOrder p = orders.get(0);
			orders.remove(0);
			assigned++;
			insert = "INSERT INTO assignments VALUES ("+p.getId()+",'"+emp.getEmail()+"','"+p.getState()+"')";
			executeUpdate(insert);
		}
	}
	
	/**
	 * Gets the next actor with a circular policy.
	 * 
	 * @return the next employee.
	**/
	Actor getCircleActor()
	{
		int i = 0;
		if(index >= employees.size())
		{
			index = 0;
		}
		
		for(Actor e : employees)
		{
			
			if(i == index)
			{
				index++;
				return e;
			}
			i++;
		}
		return null;
	}
	
	/**
	 * The Round Robin algorithm.
	 * 
	**/
	void RoundRobin()
	{
		assigned = 0;
		checkAssignments(); 
		int numEmp = employees.size();
		if(numEmp > 0)
		{
			getNewOrders(numEmp);
			int numOrd = orders.size();
			if(numOrd > 0)
			{
				assignments();
				if(assigned < numEmp && numEmp <= numOrd)
				{
					assignments();
				}
			}
		}
	}
	
	/**
	 * Gets from database new orders with time precedence
	 * with limit employee online*2.
	 * 
	**/
	void getNewOrders(int numEmp)
	{	
		orders.clear();
		//I recycle the PropostaOrdine class because it holds the same information plus the employee's email 
		//which is left blank and I use the order status to indicate its type (salesorders or proposalspurchase)
		int pastid = 0;
		String strSelect = "SELECT * FROM neworders ORDER BY order_date DESC LIMIT "+numEmp*2;
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			ResultSet rset = stmt.executeQuery(strSelect);
	
			List<WineOrder> wineOrders = new LinkedList<WineOrder>();
			boolean bool = true;
			LocalDate ld = null;
			String type = "";
			String address = "";
			String custemail = "";
			while(rset.next()) 
			{
				
				if(bool)
				{
					pastid = rset.getInt("id_order");
					ld = rset.getDate("order_date").toLocalDate();
					custemail = rset.getString("customerEmail");
					address = rset.getString("address");
					type = rset.getString("type");
					bool = false;
				}
				
				if(pastid == rset.getInt("id_order")) 
				{
					WineOrder myWineOrder = new WineOrder(rset.getString("wine_name"),rset.getInt("wine_year"),
							rset.getInt("quantity"),rset.getDouble("tot_price"));
					wineOrders.add(myWineOrder);
				}
				else
				{
					ProposalOrder myOrder = new ProposalOrder(pastid,ld,
							LocalDate.now(),custemail,"",wineOrders,address,type);
					orders.add(myOrder);
					wineOrders = new LinkedList<WineOrder>();
					pastid = rset.getInt("id_order");
					ld = rset.getDate("order_date").toLocalDate();
					custemail = rset.getString("customerEmail");
					address = rset.getString("address");
					type = rset.getString("type");
					WineOrder myWineOrder = new WineOrder(rset.getString("wine_name"),rset.getInt("wine_year"),
							rset.getInt("quantity"),rset.getDouble("tot_price"));
					wineOrders.add(myWineOrder);
				}
			}
			//get last order
			ProposalOrder lastOrder = new ProposalOrder(pastid,ld,
					LocalDate.now(),custemail,"",wineOrders,address,type);
			orders.add(lastOrder);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Create an availability notification for a wine and a customer.
	 * 
	 * @param name the wine name.
	 * @param year the wine year.
	 * @param email the customer email.
	 * @param quantity the wine quantity for notification.
	 * 
	 * @return the next employee.
	**/
	boolean createNotification(String name, int year, String email, int quantity)
	{
		String select = "SELECT * FROM notifications WHERE wine_name = '"+name
				+"' AND wine_year = "+year+" AND customerEmail = '"+email+"'";
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			ResultSet rset = stmt.executeQuery(select);
			if(rset.next())
			{
				return false;
			}
			
			String insert = "INSERT INTO notifications VALUES ('"+name+"',"+year+",'"+email+"',"+quantity+")";
			executeUpdate(insert);
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * Gets the notification if there is quantity asked and delete them for a customer.
	 * 
	 * @param email the customer email.
	 * 
	 * @return a list of notifications.
	**/
	List<Notification> getNotifications(String email)
	{
		List<Notification> notifications = new LinkedList<Notification>();
		
		String select = "SELECT * FROM notifications WHERE customerEmail = '"+email+"'";
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();Statement stmt2 = conn.createStatement();){
			
			ResultSet rset = stmt.executeQuery(select);
			while(rset.next()) 
			{
				select = "SELECT quantity from wines WHERE quantity >= "+rset.getInt("quantity")
					+" AND name = '"+rset.getString("wine_name")+"' AND yearr = "+rset.getInt("wine_year");
				
				int wy = rset.getInt("wine_year");
				int q = rset.getInt("quantity");
				String wn = rset.getString("wine_name");
				
				ResultSet rset2 = stmt2.executeQuery(select);
				if(rset2.next())
				{
					Notification notfy = new Notification(q,
							wn,wy,email);
					notifications.add(notfy);
					
					String del = "DELETE FROM notifications WHERE customerEmail = '"+email+
							"' AND wine_name = '"+wn+
							"' AND wine_year = "+wy;
					executeUpdate(del);
				}
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return notifications;
	}
	
	/**
	 * Gets history orders(sales orders) for a customer.
	 * 
	 * @param email the customer email.
	 * 
	 * @return a list of orders.
	**/
	List<ProposalOrder> getHistory(String email)
	{
		List<ProposalOrder> orders = new LinkedList<ProposalOrder>();
		// get waitingconfirm/completed orders
		int pastid = 0;
		String strSelect = "SELECT id_order, wine_name, wine_year, order_date, customerEmail, "
				+ "quantity, tot_price, address, state FROM salesorders "
				+ "WHERE customerEmail = '"+email+"' AND (state = 'completed' OR state = 'waitingconfirm' OR state = 'rejected');";
		try(Connection conn = DriverManager.getConnection(DBURL + ARGS , LOGIN, PASSWORD);
				Statement stmt = conn.createStatement();){
			
			ResultSet rset = stmt.executeQuery(strSelect);
			
			List<WineOrder> wineOrders = new LinkedList<WineOrder>();
			boolean bool = true;
			LocalDate ld = null;
			String state = "";
			String address = "";
			String custemail = "";
			while(rset.next()) 
			{					
				if(bool)
				{
					pastid = rset.getInt("id_order");
					ld = rset.getDate("order_date").toLocalDate();
					custemail = rset.getString("customerEmail");
					address = rset.getString("address");
					state = rset.getString("state");
					bool = false;
				}
				
				if(pastid == rset.getInt("id_order")) 
				{
					WineOrder myWineOrder = new WineOrder(rset.getString("wine_name"),rset.getInt("wine_year"),
							rset.getInt("quantity"),rset.getDouble("tot_price"));
					wineOrders.add(myWineOrder);
				}
				else
				{
					ProposalOrder myOrder = new ProposalOrder(pastid,ld,
							LocalDate.now(),custemail,"",wineOrders,address,state);
					orders.add(myOrder);
					wineOrders = new LinkedList<WineOrder>();
					pastid = rset.getInt("id_order");
					ld = rset.getDate("order_date").toLocalDate();
					custemail = rset.getString("customerEmail");
					address = rset.getString("address");
					state = rset.getString("state");
					WineOrder myWineOrder = new WineOrder(rset.getString("wine_name"),rset.getInt("wine_year"),
							rset.getInt("quantity"),rset.getDouble("tot_price"));
					wineOrders.add(myWineOrder);
				}
			}
			//get last order
			ProposalOrder lastOrder = new ProposalOrder(pastid,ld,
					LocalDate.now(),custemail,"",wineOrders,address,state);
			orders.add(lastOrder);
			wineOrders = new LinkedList<WineOrder>();
			
			//get pending orders
			strSelect = "SELECT * FROM neworders "
					+ "WHERE customerEmail = '"+email+"' AND (type = 'salesorder' OR type = 'confirmed');";
			rset = stmt.executeQuery(strSelect);
			bool = true;
			while(rset.next()) 
			{	
				if(bool)
				{
					pastid = rset.getInt("id_order");
					ld = rset.getDate("order_date").toLocalDate();
					custemail = rset.getString("customerEmail");
					address = rset.getString("address");
					bool = false;
				}
				
				if(pastid == rset.getInt("id_order")) 
				{
					WineOrder myWineOrder = new WineOrder(rset.getString("wine_name"),rset.getInt("wine_year"),
							rset.getInt("quantity"),rset.getDouble("tot_price"));
					wineOrders.add(myWineOrder);
				}
				else
				{
					ProposalOrder myOrder = new ProposalOrder(pastid,ld,
							LocalDate.now(),custemail,"",wineOrders,address,"pending");
					orders.add(myOrder);
					wineOrders = new LinkedList<WineOrder>();
					
					pastid = rset.getInt("id_order");
					ld = rset.getDate("order_date").toLocalDate();
					custemail = rset.getString("customerEmail");
					address = rset.getString("address");
					WineOrder myWineOrder = new WineOrder(rset.getString("wine_name"),rset.getInt("wine_year"),
							rset.getInt("quantity"),rset.getDouble("tot_price"));
					wineOrders.add(myWineOrder);
				}
			}
			//get last order
			ProposalOrder myOrder = new ProposalOrder(pastid,ld,
					LocalDate.now(),custemail,"",wineOrders,address,"pending");
			orders.add(myOrder);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return orders;
	}
	
}
