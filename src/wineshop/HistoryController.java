/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.net.URL;
import java.util.LinkedList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
*
* The class {@code HistoryController} defines a controller for orders history page interface.
*
**/

public class HistoryController implements Initializable
{

	private Client client;
	
	@FXML
	private TableView<PropertyOrder> tvDataHistory;
	@FXML
	private TableColumn<String, String> colid;
	@FXML
	private TableColumn<String, String> coldate;
	@FXML
	private TableColumn<String, String> colcust;
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

	private ObservableList<PropertyOrder> tvObservableListHistory = FXCollections.observableArrayList();
	
	@FXML
	private ChoiceBox<Integer> selectid;
	private ObservableList<Integer> tvObservableListIds = FXCollections.observableArrayList();
			
	private List<ProposalOrder> waitingOrders = new LinkedList<>();
	
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
		pageHandler.loadCustomermenu();
	}
	
	/**
	 * Execute the operation choice made.
	 *
	 * @param type the type of operation(rejected/confirmed).
	 * @param primaryStage the primary stage.
	 * @param scene the scene of primary stage.
	 *
	**/
	void handleOrderChoice(String type, Stage primaryStage, Scene scene)
	{
		if(tvObservableListIds.size() > 0)
		{
			if(selectid.getValue() == null)
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
						"Devi prima selezionare l'id di un ordine.");
				return;
			}
			
			for(ProposalOrder wo : waitingOrders)
			{
				if(wo.getId() == selectid.getValue())
				{
					wo.setState(type);
					if(type.equals("rejected"))
					{
						if(client.rejectSalesOrder(wo))
						{
							showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
									"Operazione eseguita con successo.");
							startTable();
						}
					}
					else
					{
						HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
						pageHandler.loadCheckout(wo);
					}
					break;
				}
			}
		}
		else
		{
			showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
					"Non hai ordini in attesa di conferma.");
		}
				
	}
	
	/**
	 * Handle confirm button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleConfirm(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		handleOrderChoice("confirmed", primaryStage, scene);
	}
	
	/**
	 * Handle reject button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleReject(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		handleOrderChoice("rejected", primaryStage, scene);
	}
	
	/**
	 * Initialize history table.
	 *
	**/
	void startTable()
	{
		tvObservableListHistory.clear();
		tvObservableListIds.clear();
		waitingOrders.clear();
		List<ProposalOrder> orders = client.getHistory();
		
		for(ProposalOrder o : orders)
		{
			if(o.getState().equals("waitingconfirm"))
			{
				tvObservableListIds.add(o.getId());
				waitingOrders.add(o);
			}
			for(WineOrder w : o.getWines())
			{
				PropertyOrder ord = new PropertyOrder(o.getId(),o.getOrderDate(),o.getAssignDate(),
						o.getCustomerEmail(),o.getEmployeeEmail(),o.getAddress(),o.getState(),
						w.getWineName(),w.getWineYear(),w.getQuantity(),w.getPrice());
				tvObservableListHistory.add(ord);
			}				
		}
		selectid.setItems(tvObservableListIds);
		tvDataHistory.setItems(tvObservableListHistory);
	}
	
	/** {@inheritDoc} **/
	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
		colid.setCellValueFactory(new PropertyValueFactory<>("id"));
		coldate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		colcust.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
		coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
		colstate.setCellValueFactory(new PropertyValueFactory<>("state"));
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
		            	startTable();
		            }
		        },25);
	}
}