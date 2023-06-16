/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * The class {@code ChangePassController} defines a controller for change password page interface.
 *
**/

public class ChangePassController 
{
	private Client client;
	
	@FXML
	private PasswordField oldp;
	@FXML
	private PasswordField newp;
	@FXML
	private PasswordField newp2;
	@FXML
	private Button home;
	@FXML
	private Button change;
	
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
	**/
	@FXML
	public void handleHomeButton()
	{
		Scene scene = home.getScene();
		Stage primaryStage = (Stage) home.getScene().getWindow();
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
	 * Handle change password button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	void handleChangePass(final ActionEvent e)
	{
		Scene scene = change.getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		
		if(oldp.getText().isEmpty() || newp.getText().isEmpty() || newp2.getText().isEmpty())
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Tutti i campi sono obbligatori.");
		}
		else if(newp.getText().equals(newp2.getText()))
		{
			boolean bool = client.changePass(client.getUser().getUsername(), oldp.getText(), newp.getText());
			
			if(bool)
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Successo", "Password aggiornata con successo.");
			}
			else
			{
				showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Vecchia password scorretta.");
			}
		}
		else 
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Le password non coincidono.");
		}
	}
	
}