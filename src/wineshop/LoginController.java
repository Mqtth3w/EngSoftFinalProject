/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
*
* The class {@code LoginController} defines a controller for login page interface.
*
**/

public class LoginController 
{

	private Client client;
	
	@FXML
	private TextField userNameField; 
	@FXML
	private PasswordField passwordField; 
	@FXML
	private Text actiontargetLogin;
	@FXML
	private Button login;
	@FXML
	private Button signin;
	
	/**
	 * Sets the client.
	 *
	 * @param c the client.
	 *
	**/
	public void setClient(Client c)
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
        Stage primaryStage = (Stage)scene.getWindow();
		primaryStage.close();		
	}
	
	/**
	 * Handle sign in button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleSigninFromLogin() throws IOException
	{		
		
		Scene scene = signin.getScene();
		Stage primaryStage = (Stage) signin.getScene().getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadSignin();
	}
	
	/**
	 * Handle login button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleLoginButton() throws IOException
	{
		Stage primaryStage = (Stage) login.getScene().getWindow();
		Scene scene = login.getScene();
		actiontargetLogin.setFill(Color.FIREBRICK);
		if(userNameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
		    actiontargetLogin.setText("Nome utente e password obbligatori.");
        }
		else if(userNameField.getText().length() > 30 || passwordField.getText().length() > 30)
		{
			actiontargetLogin.setText("Masssimo 30 caratteri per nome utente e password.");
		}
	    else
	    {
	    	Actor user = null;
			user = this.client.login(userNameField.getText(), passwordField.getText());
			
			if(user != null)
			{
				HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
				switch(user.getRole())
				{
					case "customer":
						pageHandler.loadCustomermenu();
						break;
					case "employee":
						pageHandler.loadEmployeemenu();
						break;
					case "admin":
						pageHandler.loadAdminmenu();
						break;
				}
			}
			else
			{
				actiontargetLogin.setText("Nome utente e/o password errati.");
			}
	    }
	}	
	
}
