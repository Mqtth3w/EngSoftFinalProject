/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
*
* The class {@code HandlePages} defines a page interface loader.
*
**/

public class HandlePages 
{
	private Scene scene;
	private Stage primaryStage;
	private Client client;
	
	private static final String loginFxml = "loginFXML.fxml";
	private static final String signinFxml = "signinFXML.fxml";
	private static final String adminMenuFxml = "adminmenuFXML.fxml";
	private static final String employeeMenuFxml = "employeemenuFXML.fxml";
	private static final String customerMenuFxml = "customermenuFXML.fxml";
	private static final String addempFxml = "addempFXML.fxml";
	private static final String changepassFxml = "changepassFXML.fxml";
	private static final String handleempFxml = "handleempFXML.fxml";
	private static final String searchcustomersFxml = "searchcustomersFXML.fxml";
	private static final String searchwinesFxml = "searchwinesFXML.fxml";
	private static final String searchordersFxml = "searchordersFXML.fxml";
	private static final String reportFxml = "reportFXML.fxml";
	private static final String handlepromotionFxml = "handlepromotionFXML.fxml";
	private static final String searchbuyFxml = "searchbuyFXML.fxml";
	private static final String checkoutFxml = "checkoutFXML.fxml";
	private static final String historyordersFxml = "historyordersFXML.fxml";
	private static final String employeeactivityFxml = "employeeactivityFXML.fxml";
	
	/**
	 * Class constructor.
	 *
	 * @param sc the application scene.
	 * @param st the application primary stage.
	 * @param c the client.
	 *
	**/
	public HandlePages(Scene sc, Stage st, Client c)
	{
		this.scene = sc;
		this.primaryStage = st;
		this.client = c;
	}
	
	/**
	 * Load login page.
	 *
	**/
	@FXML
	void loadLogin() 
	{
         try {
        	 
        	FXMLLoader loader = new FXMLLoader(getClass().getResource(loginFxml));
        	scene.setRoot((Parent) loader.load());
			
			LoginController controller = loader.<LoginController>getController();
	        controller.setClient(this.client);
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * Load sign in page.
	 *
	**/
	@FXML
	void loadSignin()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(signinFxml));
			scene.setRoot((Parent) loader.load());
			
			SigninController controller = loader.<SigninController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Registrati OnlineWineShop");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load administrator menu page.
	 *
	**/
	@FXML
	void loadAdminmenu()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(adminMenuFxml));
			scene.setRoot((Parent) loader.load());
			
			AdminController controller = loader.<AdminController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Menu amministratore OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load employee menu page.
	 *
	**/
	@FXML
	void loadEmployeemenu()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(employeeMenuFxml));
			scene.setRoot((Parent) loader.load());
			
			EmployeeController controller = loader.<EmployeeController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Menu impiegato OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load customer menu page.
	 *
	**/
	@FXML
	void loadCustomermenu()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(customerMenuFxml));
			scene.setRoot((Parent) loader.load());
			
			CustomerController controller = loader.<CustomerController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Menu cliente OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load register employee page.
	 *
	**/
	@FXML
	void loadAddEmp()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(addempFxml));
			scene.setRoot((Parent) loader.load());
			
			SigninController controller = loader.<SigninController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Aggiungi impiegato OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load handle employee page.
	 *
	**/
	@FXML
	void loadHandleEmp()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(handleempFxml));
			scene.setRoot((Parent) loader.load());
			
			AdminController controller = loader.<AdminController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Gestisci impiegato OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load change password page.
	 *
	**/
	@FXML
	void loadChangepass()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(changepassFxml));
			scene.setRoot((Parent) loader.load());
			
			ChangePassController controller = loader.<ChangePassController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Cambia password OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load search customer page.
	 *
	**/
	@FXML
	void loadSearchcustomers()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(searchcustomersFxml));
			scene.setRoot((Parent) loader.load());
			
			SearchCustController controller = loader.<SearchCustController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Cerca clienti per cognome OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load search wines page.
	 *
	**/
	@FXML
	void loadSearchwines()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(searchwinesFxml));
			scene.setRoot((Parent) loader.load());
			
			SearchWineController controller = loader.<SearchWineController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Cerca vini per nome e/o anno di produzione OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load search orders page.
	 *
	**/
	@FXML
	void loadSearchorders()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(searchordersFxml));
			scene.setRoot((Parent) loader.load());
			
			OrderController controller = loader.<OrderController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Cerca ordni di vendita, proposte di acquisto e ordini di acquisto "
					+ "per intervallo di date OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load create/search report page.
	 *
	**/
	@FXML
	void loadReport()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(reportFxml));
			scene.setRoot((Parent) loader.load());
			
			ReportController controller = loader.<ReportController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Cerca/crea report da mese e anno OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load create promotion page.
	 *
	**/
	@FXML
	void loadHandlepromotion()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(handlepromotionFxml));
			scene.setRoot((Parent) loader.load());
			
			PromotionController controller = loader.<PromotionController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Crea promozione OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load search and buy page.
	 *
	**/
	@FXML
	void loadSearchbuy()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(searchbuyFxml));
			scene.setRoot((Parent) loader.load());
			
			BuyController controller = loader.<BuyController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Cerca, compra vini OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load checkout page.
	 *
	**/
	@FXML
	void loadCheckout(ProposalOrder po)
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(checkoutFxml));
			scene.setRoot((Parent) loader.load());
			
			CheckOutController controller = loader.<CheckOutController>getController();
	        controller.setClient(this.client);
	        controller.setWaitingOrder(po);
			
			primaryStage.setTitle("Compra vini OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load history orders page.
	 *
	**/
	@FXML
	void loadHistoryorders()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(historyordersFxml));
			scene.setRoot((Parent) loader.load());
			
			HistoryController controller = loader.<HistoryController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Storico ordini OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Load complete activity page.
	 *
	**/
	@FXML
	void loadTask()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(employeeactivityFxml));
			scene.setRoot((Parent) loader.load());
			
			TaskController controller = loader.<TaskController>getController();
	        controller.setClient(this.client);
			
			primaryStage.setTitle("Gestisci ordini/proposte OnlineWineShop");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
