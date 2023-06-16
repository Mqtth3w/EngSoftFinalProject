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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * The class {@code TaskController} defines a controller for complete activity employee page interface.
 *
**/

public class TaskController implements Initializable
{

	private Client client;
	
	@FXML
	private TableView<PropertyActor> tvDataOperators;
	@FXML
	private TableColumn<String, String> colname;
	@FXML
	private TableColumn<String, String> colsurname;
	@FXML
	private TableColumn<String, String> colfiscalcode;
	@FXML
	private TableColumn<String, String> colemail;
	@FXML
	private TableColumn<String, String> colphone;
	@FXML
	private TableColumn<String, String> colopaddress;
	@FXML
	private TableColumn<String, String> colrole;
	
	@FXML
	private TableView<PropertyOrder> tvDataTask;
	@FXML
	private TableColumn<String, String> colid;
	@FXML
	private TableColumn<String, String> coldate;
	@FXML
	private TableColumn<String, String> colassigndate;
	@FXML
	private TableColumn<String, String> colcust;
	@FXML
	private TableColumn<String, String> coladdress;
	@FXML
	private TableColumn<String, String> coltype;
	@FXML
	private TableColumn<String, String> colwname;
	@FXML
	private TableColumn<String, String> colwyear;
	@FXML
	private TableColumn<String, String> colquantity;
	@FXML
	private TableColumn<String, String> colprice;

	private ObservableList<PropertyActor> tvObservableListOperators = FXCollections.observableArrayList();
	private ObservableList<PropertyOrder> tvObservableListTask = FXCollections.observableArrayList();
	
	private String service = "";
	private ProposalOrder order;
	
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
		pageHandler.loadEmployeemenu();
	}
	
	/**
	 * Handle complete sales order button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleCompleteSalesOrder(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(service.equals("salesorder"))
		{
			if(order.getState().equals("confirmed"))
			{
				order.setState("confirmedcompleted");
			}
			else
			{
				order.setState("completed");
			}
			if(client.completeSalesOrder(order))
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
						"L'attività è stata completata con successo.");
				startActivity();
				service = "";
			}
			else
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
						"L'attività è scaduta, non è stata completata entro i tre minuti.");
				startActivity();
				service = "";
			}
		}
		else if(!service.equals(""))
		{
			showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
					"L'attività che ti è stata assegnata attualmente non prevede questo pulsante.");
		}
	}
	
	/**
	 * Handle generate purchase order button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleGeneratePurchaseOrder(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(service.equals("proposalpurchase"))
		{
			tvObservableListTask.clear();
			List<PurchaseOrder> po = client.generatePurchaseOrder(order);
			PurchaseOrder p = po.get(0);
			for(WineOrder w : p.getWines())
			{
				PropertyOrder ord = new PropertyOrder(p.getIdOrd(),p.getOrderDate(),p.getAssignDate(),
						"","","",p.getState(),w.getWineName(),w.getWineYear(),w.getQuantity(),w.getPrice());
				tvObservableListTask.add(ord);
			}
			tvDataTask.setItems(tvObservableListTask);
			service = "colpetegenerate";
		}
		else if(!service.equals(""))
		{
			showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
					"L'attività che ti è stata assegnata attualmente non prevede questo pulsante.");
		}
	}
	
	/**
	 * Handle complete purchase order and generate sales order button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleCompletePurchaseOrderGenerateSalesOrder(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(service.equals("colpetegenerate"))
		{
			order.setState("completed");
			if(client.completeProposalPurchase(order))
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
						"L'attività è stata completata con successo.");
				startActivity();
				service = "";
			}
			else
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
						"L'attività è scaduta, non è stata completata entro i tre minuti.");
				startActivity();
				service = "";
			}
		}
		else if(!service.equals(""))
		{
			showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
					"L'attività che ti è stata assegnata attualmente non prevede questo pulsante.");
		}
	}
	
	/**
	 * Handle complete purchase order button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleCompletePurchaseOrder(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(service.equals("purchaseorder"))
		{
			order.setState("completed");
			if(client.completePurchaseOrder(order))
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
						"L'attività è stata completata con successo.");
				startActivity();
				service = "";
			}
			else
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
						"L'attività è scaduta, non è stata completata entro i tre minuti.");
				startActivity();
				service = "";
			}
		}
		else if(!service.equals(""))
		{
			showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
					"L'attività che ti è stata assegnata attualmente non prevede questo pulsante.");
		}
	}	
	
	/**
	 * Initialize operators(courier,supplier) table.
	 *
	**/
	void startOperators()
	{
		List<Actor> operators = client.getOperators();
		tvObservableListOperators.clear();
		for(Actor c : operators)
		{
			PropertyActor cust = new PropertyActor(c.getRole(),c.getName(),c.getSurname(),
					c.getFiscalCode(),c.getEmail(),c.getPhone(),c.getAddress());
			tvObservableListOperators.add(cust);
		}
		tvDataOperators.setItems(tvObservableListOperators);
		
	}
	
	/**
	 * Initialize activity(order data) table.
	 *
	**/
	void startActivity()
	{
		List<ProposalOrder> task = client.getAssignment();
		tvObservableListTask.clear();
		if(task.size() > 0)
		{
			order = task.get(0);
			
			switch(order.getState()) 
			{
				case "salesorder":
					service = "salesorder";
					break;
				case "purchaseorder":
					service = "purchaseorder";
					break;
				case "proposalpurchase":
					service = "proposalpurchase";
					break;
				case "confirmed":
					service = "salesorder";
					break;
			}
			
			for(WineOrder w : order.getWines())
			{
				PropertyOrder ord = new PropertyOrder(order.getId(),order.getOrderDate(),order.getAssignDate(),
						order.getCustomerEmail(),"",order.getAddress(),order.getState(),
						w.getWineName(),w.getWineYear(),w.getQuantity(),w.getPrice());
				tvObservableListTask.add(ord);
			}				
		}
		tvDataTask.setItems(tvObservableListTask);
	}
	
	
	/** {@inheritDoc} **/
	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
		colname.setCellValueFactory(new PropertyValueFactory<>("name"));
		colsurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		colfiscalcode.setCellValueFactory(new PropertyValueFactory<>("FiscalCode"));
		colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colphone.setCellValueFactory(new PropertyValueFactory<>("phone"));	    
		colopaddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		colrole.setCellValueFactory(new PropertyValueFactory<>("username"));
		//for operators the user name doesn't matter, I reuse it to indicate the role
		
		colid.setCellValueFactory(new PropertyValueFactory<>("id"));
		coldate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		colassigndate.setCellValueFactory(new PropertyValueFactory<>("assignDate"));
		colcust.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
		coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
		coltype.setCellValueFactory(new PropertyValueFactory<>("state"));	    
		colwname.setCellValueFactory(new PropertyValueFactory<>("wineName"));
		colwyear.setCellValueFactory(new PropertyValueFactory<>("wineYear"));
		colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		colprice.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() 
		        {
		            @Override
		            public void run() 
		            {
		            	startOperators();
		            	startActivity();
		            }
		        },25);
	}
}
