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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
*
* The class {@code EmployeeController} defines a controller for employee menu page interface.
*
**/

public class EmployeeController implements Initializable
{
	
	private Client client;
	
	@FXML
	private TableView<PropertyOrder> tvDataTask;
	@FXML
	private TableColumn<String, String> colid;
	@FXML
	private TableColumn<String, String> colactivity;
	
	private ObservableList<PropertyOrder> tvObservableListTask = FXCollections.observableArrayList();
	
	
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
	 * Handle exit button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	void handleExit(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
        Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        client.logout();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadLogin();
	}
	
	/**
	 * Handle change password button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	void handleChangePass(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
        Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadChangepass();
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
	 * Handle search customers button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleSearchCustomers(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadSearchcustomers();
	}
	
	/**
	 * Handle search wines button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleSearchWines(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadSearchwines();
	}
	
	/**
	 * Handle search orders button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleSearchOrders(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadSearchorders();
	}
	
	/**
	 * Handle create promotion button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handlePromotion(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadHandlepromotion();
	}
	
	/**
	 * Handle complete activity button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleTask(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadTask();
	}
	
	/**
	 * Initialize table assigned task.
	 *
	**/
	void checkAssigned()
	{
		tvObservableListTask.clear();
		List<ProposalOrder> task = client.getAssignment();
		if(task.size() > 0)
		{
			ProposalOrder order = task.get(0);
			PropertyOrder ord = new PropertyOrder(order.getId(),null,
					null,"","","",order.getState(),"",0,0,0);
			tvObservableListTask.add(ord);		
		}
		tvDataTask.setItems(tvObservableListTask);
		
	}
	
	/** {@inheritDoc} **/
	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{		
		colid.setCellValueFactory(new PropertyValueFactory<>("id"));
		colactivity.setCellValueFactory(new PropertyValueFactory<>("state"));
		
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() 
		        {
		            @Override
		            public void run() 
		            {
		            	checkAssigned();
		            }
		        },25);
	}
	
}
