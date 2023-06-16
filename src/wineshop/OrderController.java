/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
*
* The class {@code OrderController} defines a controller for research orders page interface.
*
**/

public class OrderController implements Initializable
{

	private Client client;
	
	@FXML
	private TableView<PropertyOrder> tvData;
	@FXML
	private TableColumn<String, String> colid;
	@FXML
	private TableColumn<String, String> coldate;
	@FXML
	private TableColumn<String, String> colassigndata;
	@FXML
	private TableColumn<String, String> colcust;
	@FXML
	private TableColumn<String, String> colemp;
	@FXML
	private TableColumn<String, String> coladdress;
	@FXML
	private TableColumn<String, String> colstate;
	@FXML
	private TableColumn<String, String> colwname;
	@FXML
	private TableColumn<String, String> colwyear;
	@FXML
	private TableColumn<String, String> colquantity;
	@FXML
	private TableColumn<String, String> colprice;
	@FXML
	private DatePicker inDate;
	@FXML
	private DatePicker finDate;

	private ObservableList<PropertyOrder> tvObservableList = FXCollections.observableArrayList();
	
	/**
	 * Sets the client.
	 *
	 * @param c the client.
	 *
	**/
	void setClient(Client c)
	{
		this.client = c;
	}
	
	/**
	 * Create an alert.
	 *
	 * @param alertType the alert type.
	 * @param owner the window.
	 * @param title the alert title.
	 * @param message the alert message.
	 *
	**/
	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) 
	{
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
	
	/**
	 * Handle home button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleHomeButton(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		String role = client.getUser().getRole();
		if(role.equals("employee"))
		{
			pageHandler.loadEmployeemenu();
		}
		else if(role.equals("admin"))
		{
			pageHandler.loadAdminmenu();
		}
	}
	
	/**
	 * Basing on the order type(sales order/proposal purchase) make research and show results.
	 *
	 * @param type the order type.
	 * @param primaryStage the primary stage.
	 *
	**/
	public void handlecustorder(Stage primaryStage, String type)
	{
		tvObservableList.clear();
		List<ProposalOrder> orders = client.searchCustOrders(inDate.getValue(), finDate.getValue(), type);
		if(orders.size() > 0)
		{
			for(ProposalOrder o : orders)
			{
				for(WineOrder w : o.getWines())
				{
					PropertyOrder ord = new PropertyOrder(o.getId(),o.getOrderDate(),o.getAssignDate(),
							o.getCustomerEmail(),o.getEmployeeEmail(),o.getAddress(),o.getState(),
							w.getWineName(),w.getWineYear(),w.getQuantity(),w.getPrice());
					tvObservableList.add(ord);
				}				
				
			}
			tvData.setItems(tvObservableList);
		}
		else
		{
			showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Nessun risultato.");
		}
	}
	
	/**
	 * Make research and show results for purchase order.
	 *
	 * @param primaryStage the primary stage.
	 *
	**/
	public void handlepurchaseorder(Stage primaryStage)
	{
		tvObservableList.clear();
		List<PurchaseOrder> orders = client.searchPurchaseOrders(inDate.getValue(), finDate.getValue());
		if(orders.size() > 0)
		{
			for(PurchaseOrder o : orders)
			{
				for(WineOrder w : o.getWines())
				{
					PropertyOrder ord = new PropertyOrder(o.getIdOrd(),o.getOrderDate(),o.getAssignDate(),"",
							o.getEmployeeEmail(),"",o.getState(),w.getWineName(),w.getWineYear(),w.getQuantity(),w.getPrice());
					tvObservableList.add(ord);
				}				
				
			}
			tvData.setItems(tvObservableList);
		}
		else
		{
			showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Nessun risultato.");
		}
	}
	
	/**
	 * Basing on the order type call the right research function.
	 *
	 * @param type the order type.
	 * @param primaryStage the primary stage.
	 *
	**/
	public void handleOrder(Stage primaryStage, String type)
	{
		
		
		if(inDate.getValue() == null || finDate.getValue() == null)
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Data inizio e data fine sono obbligatorie per la ricerca.");
		}
		else  
		{
			switch(type)
			{
				case "searchSalesOrders":
					handlecustorder(primaryStage, "searchSalesOrders");
					break;
				case "searchProposalsPurchase":
					handlecustorder(primaryStage, "searchProposalsPurchase");
					break;
				case "searchPurchaseOrders":
					handlepurchaseorder(primaryStage);
					break;
				default:
					showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Errore.");
					break;
			}
		}
		
	}
	
	/**
	 * Handle search sales order button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleSearchSalesButton(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		handleOrder(primaryStage, "searchSalesOrders");
	}
	
	/**
	 * Handle search proposal purchase button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleSearchProposalButton(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		handleOrder(primaryStage, "searchProposalsPurchase");
	}
	
	/**
	 * Handle search purchase order button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleSearchPurchaseButton(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		handleOrder(primaryStage, "searchPurchaseOrders");
	}
	
	/** {@inheritDoc} **/
	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
	    colid.setCellValueFactory(new PropertyValueFactory<>("id"));
	    coldate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
	    colassigndata.setCellValueFactory(new PropertyValueFactory<>("assignDate"));
	    colcust.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
	    colemp.setCellValueFactory(new PropertyValueFactory<>("employeeEmail"));
	    coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
	    colstate.setCellValueFactory(new PropertyValueFactory<>("state"));
	    colwname.setCellValueFactory(new PropertyValueFactory<>("wineName"));
	    colwyear.setCellValueFactory(new PropertyValueFactory<>("wineYear"));
	    colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
	    colprice.setCellValueFactory(new PropertyValueFactory<>("price"));
	    tvData.setItems(tvObservableList);
	}
	
}
