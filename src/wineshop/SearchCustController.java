/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
*
* The class {@code SearchCustController} defines a controller for search customers page interface.
*
**/

public class SearchCustController implements Initializable
{

	private Client client;
	
	@FXML
	private TableView<PropertyActor> tvData;
	@FXML
	private TableColumn<String, String> colusername;
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
	private TableColumn<String, String> coladdress;

	private ObservableList<PropertyActor> tvObservableList = FXCollections.observableArrayList();
	
	@FXML
	private TextField surnameCust;
	
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
	 * Handle search button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleSearchButton(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(surnameCust.getText().isEmpty())
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Cognome obbligatorio.");
		}
		else 
		{
			tvObservableList.clear();
			List<Actor> customers = client.searchCustomers(surnameCust.getText());
			if(customers.size() > 0)
			{
				for(Actor c : customers)
				{
					PropertyActor cust = new PropertyActor(c.getUsername(),c.getName(),c.getSurname(),
							c.getFiscalCode(),c.getEmail(),c.getPhone(),c.getAddress());
					tvObservableList.add(cust);
				}
				tvData.setItems(tvObservableList);
			}
			else
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Nessun risultato.");
			}
		}
		
	}
	
	/** {@inheritDoc} **/
	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
	    colusername.setCellValueFactory(new PropertyValueFactory<>("username"));
	    colname.setCellValueFactory(new PropertyValueFactory<>("name"));
	    colsurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
	    colfiscalcode.setCellValueFactory(new PropertyValueFactory<>("FiscalCode"));
	    colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
	    colphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
	    coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
	    tvData.setItems(tvObservableList);
	}
}
