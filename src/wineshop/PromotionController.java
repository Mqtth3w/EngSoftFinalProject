/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
*
* The class {@code PromotionController} defines a controller for create promotion page interface.
*
**/

public class PromotionController 
{	
	private Client client;
	
	@FXML
	private DatePicker inDate;
	@FXML
	private DatePicker finDate;
	@FXML
	private TextField winename;
	@FXML
	private TextField wineyear;
	@FXML
	private TextField discount;
	
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
	 * Handle create button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleCreatePromo(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		
		if(inDate.getValue() == null || finDate.getValue() == null)
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Data inizio e data fine sono obbligatorie per la ricerca.");
		}
		else if(!inDate.getValue().isBefore(finDate.getValue()))
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Data inizio deve eessere prima della data fine.");
		}
		else if(finDate.getValue().isBefore(LocalDate.now()))
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Il periodo di promozione non può essere passato.");
		}
		else if(winename.getText().isEmpty() || wineyear.getText().isEmpty() || discount.getText().isEmpty())
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Nome, anno e sconto sono obbligatori.");
		}
		else  
		{
			int wy;
			int d;
			try {
					
				wy = Integer.parseInt(wineyear.getText());
		    }
		    catch (NumberFormatException ex){
	            ex.printStackTrace();
	            showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Anno e sconto devono essere un numero.");
	            return;
	        }
			
			try {
				
				d = Integer.parseInt(discount.getText());
		    }
		    catch (NumberFormatException ex){
	            ex.printStackTrace();
	            showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Anno e sconto devono essere un numero.");
	            return;
	        }
			
			if( d <= 0 || d >= 100)
			{
				showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Lo scondo deve essere maggiore di zero e minore di 100.");
				return;
			}
				
			boolean bool = client.createPromotion(winename.getText(), wy, d, inDate.getValue(), finDate.getValue());
			if(bool)
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Successo", "Promozione creata.");
			}
			else
			{
				showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Vino inesistente o intrvallo già utilizzato.");
			}
		}
	}	
	
}
