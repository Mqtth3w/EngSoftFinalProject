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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
*
* The class {@code BuyController} defines a controller for search and buy page interface.
*
**/

public class BuyController implements Initializable
{

	private Client client;
	
	@FXML
	private TableView<PropertyWine> tvDataWine;
	@FXML
	private TableColumn<String, String> colname;
	@FXML
	private TableColumn<String, String> colyear;
	@FXML
	private TableColumn<String, String> colproductor;
	@FXML
	private TableColumn<String, String> colorigin;
	@FXML
	private TableColumn<String, String> colnotes;
	@FXML
	private TableColumn<String, String> colgrape;
	@FXML
	private TableColumn<String, String> colquality;
	@FXML
	private TableColumn<String, String> colprice;
	@FXML
	private TableColumn<String, String> colquant;

	private ObservableList<PropertyWine> tvObservableListWine = FXCollections.observableArrayList();
	
	@FXML
	private TableView<PropertyOrder> tvDataOrder;
	@FXML
	private TableColumn<String, String> colnameorder;
	@FXML
	private TableColumn<String, String> colyearorder;
	@FXML
	private TableColumn<String, String> colquantityorder;
	@FXML
	private TableColumn<String, String> colpriceorder;

	private ObservableList<PropertyOrder> tvObservableListOrder = FXCollections.observableArrayList();
	
	@FXML
	private TableView<PropertyOrder> tvDataProposal;
	@FXML
	private TableColumn<String, String> colnameproposal;
	@FXML
	private TableColumn<String, String> colyearproposal;
	@FXML
	private TableColumn<String, String> colquantityproposal;
	@FXML
	private TableColumn<String, String> colpriceproposal;

	private ObservableList<PropertyOrder> tvObservableListProposal = FXCollections.observableArrayList();
	
	@FXML
	private TextField wineName;
	@FXML
	private TextField wineYear;
	@FXML
	private TextField wineName1;
	@FXML
	private TextField wineYear1;
	
	@FXML
	private Spinner<Integer> boxq;
	private SpinnerValueFactory<Integer> svfq = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 1);
	@FXML
	private Spinner<Integer> box6;
	private SpinnerValueFactory<Integer> svf6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
	@FXML
	private Spinner<Integer> box12;
	private SpinnerValueFactory<Integer> svf12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 0);
	
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
	 * Handle search wine button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleSearchButton(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		
		if(wineName.getText().isEmpty())
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Nome vino obbligatorio.");
		}
		else  
		{
			int number = -1;
			if(!wineYear.getText().isEmpty())
			{
				try {
					
		            number = Integer.parseInt(wineYear.getText());
		        }
		        catch (NumberFormatException ex){
		            ex.printStackTrace();
		            showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Anno deve essere un numero.");
		            number = -1;
		        }
			}
			
			tvObservableListWine.clear();
			List<Wine> wines = client.searchWine(wineName.getText(), number);
			if(wines.size() > 0)
			{
				for(Wine w : wines)
				{
					PropertyWine wine = new PropertyWine(w.getName(),w.getProductor(),
							w.getOrigin(),w.getYear(),w.getTechnicalNotes(),w.getGrapeVariety(),
							w.getQuality(),w.getPrice(),0,w.getQuantity(),0);
					tvObservableListWine.add(wine);
				}
				tvDataWine.setItems(tvObservableListWine);
			}
			else
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Nessun risultato.");
			}
		}
		
	}
	
	/**
	 * Check coherence wine year field input.
	 *
	 * @param primaryStage the primary stage.
	 *
	**/
	int checkWineField(Stage primaryStage)
	{
		if(wineName1.getText().isEmpty() || wineYear1.getText().isEmpty())
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Nome, anno vino obbligatori per l'aggiunta.");
			return 0;
		}
		
		int wy;
		try {
			
            wy = Integer.parseInt(wineYear1.getText());
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Anno deve essere un numero intero.");
            return 0;
        }			
		
		return wy;
	}
	
	/**
	 * Check if there is quantity.
	 * 
	 * @return check result
	 * 
	**/
	boolean checkQuantity()
	{
		if(boxq.getValue() + box6.getValue() + box12.getValue() > 0)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Handle add to sales order button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleAddSalesBuy(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		int wy = checkWineField(primaryStage);
		if(wy != 0)
		{
			if(checkQuantity())
			{
				switch(client.addWineToProposalOrder(true, wineName1.getText(), wy, 
						boxq.getValue(), box6.getValue(), box12.getValue()))
				{
					case 1:
						showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Nessun "
								+ "vino con questo nome e anno.");
						break;
					case 2:
						showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Quantità "
								+ "desideara maggiore di quella disponibile.");
						break;
					case 3:
						startTable(true);
						break;
					case 0:
						showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Hai già "
								+ "aggiunto questo vino.");
						break;
				}
			}
			else
			{
				showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "La quantità deve essere maggiore di zero.");
			}
		}
		
	}
	
	/**
	 * Handle add to proposal purchase button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleAddProposal(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		int wy = checkWineField(primaryStage);
		if(wy != 0)
		{
			if(checkQuantity())
			{
				switch(client.addWineToProposalOrder(false, wineName1.getText(), wy, 
						boxq.getValue(), box6.getValue(), box12.getValue()))
				{
					case 1:
						showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Nessun "
								+ "vino con questo nome e anno.");
						break;
					case 2:
						showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "La quantità "
								+ "desideara deve essere maggiore di quella disponibile.");
						break;
					case 3:
						startTable(false);
						break;
					case 0:
						showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Hai già "
								+ "aggiunto questo vino.");
						break;
				}
			}
			else
			{
				showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "La quantità deve essere maggiore di zero.");
			}
		}
	}
	
	/**
	 * Handle ask notification button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleNotification(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		int wy = checkWineField(primaryStage);
		if(wy != 0)
		{
			switch(client.createNotification(wineName1.getText(), wy, boxq.getValue() + 
					(box6.getValue()*6) + (box12.getValue()*12)))
			{
				case 1:
					showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Nessun "
							+ "vino con questo nome e anno.");
					break;
				case 2:
					showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "La quantità "
							+ "desideara deve essere maggiore di quella disponibile.");
					break;
				case 3:
					showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Richiesta "
							+ "di notifica creata con successo.");
					break;
				case 4:
					showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Hai già "
							+ "chiesto una notifica per quessto vino.");
					break;
			}
		}
	}
	
	/**
	 * Handle clear sales order button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleClearOrder(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(client.checkOrderLenght(true) != 0)
		{
			client.cancelProposalOrder(true);
			tvObservableListOrder.clear();
			tvDataOrder.setItems(tvObservableListOrder);
		}
		else
		{
			showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "L'ordine "
					+ "è vuoto.");
		}
	}
	
	/**
	 * Handle clear proposal purchase button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleClearProposal(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(client.checkOrderLenght(false) != 0)
		{
			client.cancelProposalOrder(false);
			tvObservableListProposal.clear();
			tvDataProposal.setItems(tvObservableListProposal);
		}
		else
		{
			showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "La proposta "
					+ "è vuota.");
		}
	}
	
	/**
	 * Handle send proposal purchase button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleSendProposal(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(client.checkOrderLenght(false) != 0)
		{
			switch(client.makeProposalOrder(true))
			{
				case 0:
					showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Prima "
							+ "inserisci dei vini nella proposta.");
					break;
				case 1:
					tvObservableListProposal.clear();
					tvDataProposal.setItems(tvObservableListProposal);
					showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Proposta "
							+ "inviata con successo, troverai un conseguente ordine di vendita "
							+ "da confermare nello storico.");
					break;
				case 2:
					showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Richiesta "
							+ "fallita.");
					break;
			}
		}
		else
		{
			showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "La proposta "
					+ "è vuota.");
		}
	}
	
	/**
	 * Handle send sales order and pay button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleCheckOutOrder(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(client.checkOrderLenght(true) != 0)
		{
			HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
			pageHandler.loadCheckout(null);
		}
		else
		{
			showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "L'ordine "
					+ "è vuoto.");
		}
	}
	
	/**
	 * Initialize the table.
	 *
	 * @param bool indicate the table.
	 *
	**/
	void startTable(boolean bool)
	{
		if(bool) 
		{
			tvObservableListOrder.clear();
		}
		else
		{
			tvObservableListProposal.clear();
		}
		List<WineOrder> ws = client.getWineProposalOrder(bool);
		
		for(WineOrder p : ws)
		{
			PropertyOrder pr = new PropertyOrder(-1,null,null,"","","","",p.getWineName(),p.getWineYear(),p.getQuantity(),p.getPrice());
			if(bool) 
			{
				tvObservableListOrder.add(pr);
			}
			else
			{
				tvObservableListProposal.add(pr);
			}
		}
		
		if(bool) 
		{
			tvDataOrder.setItems(tvObservableListOrder);
		}
		else
		{
			tvDataProposal.setItems(tvObservableListProposal);
		}
	}
	
	/** {@inheritDoc} **/
	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
		
		colname.setCellValueFactory(new PropertyValueFactory<>("name"));
	    colyear.setCellValueFactory(new PropertyValueFactory<>("year"));
	    colproductor.setCellValueFactory(new PropertyValueFactory<>("productor"));
	    colorigin.setCellValueFactory(new PropertyValueFactory<>("origin"));
	    colnotes.setCellValueFactory(new PropertyValueFactory<>("TechnicalNotes"));
	    colgrape.setCellValueFactory(new PropertyValueFactory<>("GrapeVariety"));
	    colquality.setCellValueFactory(new PropertyValueFactory<>("quality"));
	    colprice.setCellValueFactory(new PropertyValueFactory<>("price"));
	    colquant.setCellValueFactory(new PropertyValueFactory<>("quantity"));
	    tvDataWine.setItems(tvObservableListWine);
		boxq.setValueFactory(svfq);
		box6.setValueFactory(svf6);
		box12.setValueFactory(svf12);
		
		colnameorder.setCellValueFactory(new PropertyValueFactory<>("wineName"));
		colyearorder.setCellValueFactory(new PropertyValueFactory<>("wineYear"));
		colquantityorder.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		colpriceorder.setCellValueFactory(new PropertyValueFactory<>("price"));
	    tvDataOrder.setItems(tvObservableListOrder);
	    
	    colnameproposal.setCellValueFactory(new PropertyValueFactory<>("wineName"));
	    colyearproposal.setCellValueFactory(new PropertyValueFactory<>("wineYear"));
	    colquantityproposal.setCellValueFactory(new PropertyValueFactory<>("quantity"));
	    colpriceproposal.setCellValueFactory(new PropertyValueFactory<>("price"));
	    tvDataProposal.setItems(tvObservableListProposal);
	    
	    new java.util.Timer().schedule( 
		        new java.util.TimerTask() 
		        {
		            @Override
		            public void run() 
		            {
		            	startTable(true);
		            	startTable(false);
		            }
		        },25);
	}
}
