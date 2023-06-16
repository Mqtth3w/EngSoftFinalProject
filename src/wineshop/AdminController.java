/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Node;

/**
*
* The class {@code AdminController} defines a controller for administrator menu interface.
*
**/

public class AdminController 
{
	
	private Client client;
	
	@FXML
	private TextField usernameEmp;
	
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
	 * Handle add employee button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	void handleAddEmp(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
        Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadAddEmp();
	}
	
	/**
	 * Handle handle employee button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	void handleEmp(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
        Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadHandleEmp();
	}
	
	/**
	 * Handle reset password button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	void handleResetEmpPass(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(usernameEmp.getText().isEmpty())
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Username obbligatorio.");
		}
		else 
		{
			boolean bool = client.resetPass(usernameEmp.getText());
			
			if(bool)
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Successo", "Password resettata con successo.");
			}
			else
			{
				showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Username inesistente.");
			}
		}
	}
	
	/**
	 * Handle fire(block) employee button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	void handleFireEmp(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(usernameEmp.getText().isEmpty())
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Username obbligatorio.");
		}
		else 
		{
			boolean bool = client.fireEmployee(usernameEmp.getText());
			if(bool)
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Successo", "Impiegato bloccato con successo.");
			}
			else
			{
				showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Impiegato inesistente.");
			}
		}
	}
	
	/**
	 * Handle unfire(unblock) employee button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	void handleUnfireEmp(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(usernameEmp.getText().isEmpty())
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Username obbligatorio.");
		}
		else 
		{
			boolean bool = client.unfireEmployee(usernameEmp.getText());
			if(bool)
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Successo", "Impiegato sbloccato con successo.");
			}
			else
			{
				showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Impiegato bloccato inesistente.");
			}
		}
	}
	
	/**
	 * Handle home button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleHomeAdminButton(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadAdminmenu();
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
	 * Handle search/create report button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleReport(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadReport();
	}
	
}