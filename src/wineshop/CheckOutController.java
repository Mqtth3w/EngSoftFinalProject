/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
*
* The class {@code CheckOutController} defines a controller for checkout page interface.
*
**/

public class CheckOutController implements Initializable
{
	private Client client;
	private ProposalOrder waitingOrder;
	private boolean bool = true;
	@FXML
	private TextField cardnumber;	
	@FXML
	private TextField cvc;
	@FXML
	private DatePicker expdate;
	@FXML
	private Text actiontarget;
	
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
	 * Sets the confirmed sales order.
	 *
	 * @param po the sales order confirmed.
	 *
	**/
	void setWaitingOrder(ProposalOrder po)
	{
		this.waitingOrder = po;
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
	 * Handle search and buy wines button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleSearchBuy(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadSearchbuy();
	}
	
	/**
	 * Handle history orders button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleHistory(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadHistoryorders();
	}
	
	/**
	 * Execute the classic sales order(order not came from history confirmed order).
	 *
	 * @param primaryStage the primary stage.
	 *
	**/
	void executeOrder(Stage primaryStage)
	{
		switch(client.makeProposalOrder(false))
		{
			case 1:
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Ordine "
						+ "effettuato con successo. Troverai l'ordine nello storico.");
				client.cancelProposalOrder(true); 
				bool = false;
				break;
			case 2:
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Richiesta "
						+ "fallita.");
				break;
		}
	}
	
	/**
	 * Handle pay with card button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handlePayCard(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(bool)
		{
			if(cardnumber.getText().isEmpty() || cvc.getText().isEmpty() || expdate.getValue() == null)
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Numero carta, "
						+ "cvc e data sono obbliatori per pagare con la carta.");
				
			}
			else if(expdate.getValue().isBefore(LocalDate.now()))
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
						"Inserire una carta non scaduta.");
			}
			else
			{
				long cn;
				try {
					
		            cn = Long.parseLong(cardnumber.getText());
		        }
		        catch (NumberFormatException ex){
		            ex.printStackTrace();
		            showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Numero "
		            		+ "carta deve essere un numero valido.");
		            return;
		        }
				
				int c;
				try {
					
		            c = Integer.parseInt(cvc.getText());
		        }
		        catch (NumberFormatException ex){
		            ex.printStackTrace();
		            showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Cvc "
		            		+ "deve essere un numero valido.");
		            return;
		        }
				
				if(cn < 0 || c < 0) 
				{
					showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Numero "
							+ "carta e cvc devono essere numeri positivi.");
					return;
				}
				
				if(cardnumber.getText().length() != 16)
				{
					showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Numero "
							+ "della carta deve avere 16 cifre.");
					return;
				}
				
				if(cvc.getText().length() != 3)
				{
					showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Cvc "
							+ "della carta deve avere 3 cifre.");
					return;
				}
				
				if(this.waitingOrder == null)
				{
					executeOrder(primaryStage);
				}
				else
				{
					client.confirmSalesOrder(waitingOrder);
					showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Ordine "
							+ "effettuato con successo. Troverai l'ordine nello storico.");
					bool = false;
				}
				
			}
		}
		else
		{
			showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
					"Hai già effettuato l'ordine.");
		}
		
	}
	
	/**
	 * Handle pay with IBAN button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handlePayIban(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(bool)
		{
			if(this.waitingOrder == null)
			{
				executeOrder(primaryStage);
			}
			else
			{
				client.confirmSalesOrder(waitingOrder);
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Ordine "
						+ "effettuato con successo. Troverai l'ordine nello storico.");
				bool = false;
			}
			
		}
		else
		{
			showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", 
					"Hai già effettuato l'ordine.");
		}
	}	
	
	/**
	 * Show the total to be paid.
	 *
	**/
	void setTotal()
	{
		double tot = 0;
		List<WineOrder> ws;
		if(waitingOrder == null)
		{
			ws = client.getWineProposalOrder(true);
		}
		else 
		{
			ws = waitingOrder.getWines();
		}
		
		for(WineOrder p : ws)
		{
			tot = tot + p.getPrice();
		}
		actiontarget.setText("Importo totale: " + new DecimalFormat("#.##").format(tot) + "€");
	}
	
	/** {@inheritDoc} **/
	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
		
	    
	    new java.util.Timer().schedule( 
		        new java.util.TimerTask() 
		        {
		            @Override
		            public void run() 
		            {
		            	setTotal();
		            }
		        },25);
	}
}
