/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
*
* The class {@code SigninController} defines a controller for sign in and register employee page interface.
*
**/

public class SigninController 
{
	private Client client;

	@FXML
	private TextField name; 
	@FXML
	private TextField surname; 
	@FXML
	private TextField fiscalcode;
	@FXML
	private TextField email; 
	@FXML
	private TextField phone; 
	@FXML
	private TextField address;
	@FXML
	private TextField username; 
	@FXML
	private PasswordField password; 
	@FXML
	private Text actiontarget;
	@FXML
	private Button login;
	@FXML
	private Button signin;
	@FXML
	private Button home;
	
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
	 * Check input fields validity, if corrects try to sign in.
	 *
	 * @param n the actor name.
	 * @param s the actor surname.
	 * @param f the actor fiscal code.
	 * @param e the actor email.
	 * @param t the actor phone.
	 * @param a the actor address.
	 * @param u the actor user name.
	 * @param p the actor password.
	 * @param role the actor role.
	 *
	**/
	int checkSignin(TextField n, TextField s, TextField f, TextField e, TextField t, TextField a, TextField u, PasswordField p, String role)
	{
		if(n.getText().isEmpty() || s.getText().isEmpty() || f.getText().isEmpty() || e.getText().isEmpty() || t.getText().isEmpty() || a.getText().isEmpty() || u.getText().isEmpty() ||  p.getText().isEmpty())
		{
			return 0;
		}
		else if(n.getText().length() > 30 || s.getText().length() > 30 || e.getText().length() > 30 || u.getText().length() > 30 || p.getText().length() > 30)
		{
			return 1;
		}
		else if(t.getText().length() > 15)
		{
			return 2;
		}
		else if(f.getText().length() != 16)
		{
			return 3;
		}
		else if(a.getText().length() > 500)
		{
			return 4;
		}
		
		boolean user = this.client.signin(u.getText(),n.getText(),s.getText(),f.getText(),e.getText(),
				t.getText(),a.getText(),p.getText(),role);
		
		if(user)
		{
			return 5;
		}
		return 6;	
	}
	
	/**
	 * Show the input format error/success of operation.
	 *
	 * @param state the result state of checkSignin function.
	 * @param scene the primary stage scene.
	 * @param primaryStage the primary stage.
	 * @param role the actor role.
	 *
	**/
	void handleState(int state, Scene scene, Stage primaryStage, String role)
	{
		switch(state)
		{
			case 0:
				actiontarget.setText("Tutti i campi sono obbligatori.");
				break;
			case 1:
				actiontarget.setText("Massimo 30 caratteri per nome, cognome, email, nome utente e password.");
				break;
			case 2:
				actiontarget.setText("Massimo 15 caratteri per il telefono.");
				break;
			case 3:
				actiontarget.setText("Tra 16 caratteri per il codice fiscale.");
				break;
			case 4:
				actiontarget.setText("Massimo 500 caratteri per l'indirizzo.");
				break;
			case 5:
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Registrato con successo", "Accout " + username.getText() + " registrato.");
				
				if(role.equals("customer"))
				{
					HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
					pageHandler.loadLogin();
				}
				
				break;
			case 6:
				actiontarget.setText("Nome utente, email o codice fiscale già utilizzati.");
				break;
		}
	}
	
	/**
	 * Handle sign in (customer) button.
	 *
	**/
	@FXML
	public void handleSigninButton()
	{
		Scene scene = signin.getScene();
		Stage primaryStage = (Stage) signin.getScene().getWindow();
		actiontarget.setFill(Color.FIREBRICK);
    	int state = 0;
		state = checkSignin(name, surname, fiscalcode, email, phone, address, username, password, "customer");
		
    	handleState(state, scene, primaryStage, "customer");
	}
	
	/**
	 * Handle register employee button.
	 *
	**/
	@FXML
	public void handleAddEmpButton()
	{
		Scene scene = signin.getScene();
		Stage primaryStage = (Stage) signin.getScene().getWindow();
		actiontarget.setFill(Color.FIREBRICK);
    	int state = 0;
		state = checkSignin(name, surname, fiscalcode, email, phone, address, username, password, "employee");
		
		handleState(state, scene, primaryStage, "employee");
	}
	
	/**
	 * Handle home button.
	 *
	**/
	@FXML
	public void handleHomeAdminButton()
	{
		Scene scene = home.getScene();
		Stage primaryStage = (Stage) home.getScene().getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadAdminmenu();
	}
	
	/**
	 * Handle load login page button.
	 *
	**/
	@FXML 
	public void handleLoginFromSignin() throws IOException
	{
		Scene scene = login.getScene();
		Stage primaryStage = (Stage) login.getScene().getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadLogin();
	}
	
}