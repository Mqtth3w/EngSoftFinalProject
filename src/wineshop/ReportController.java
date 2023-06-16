/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
*
* The class {@code ReportController} defines a controller for create/search page interface.
*
**/

public class ReportController implements Initializable
{

	private Client client;
	
	@FXML
	private TableView<PropertyFinance> tvDataFinance;
	@FXML
	private TableColumn<String, String> coldate;
	@FXML
	private TableColumn<String, String> coltotincome;
	@FXML
	private TableColumn<String, String> colexpense;
	@FXML
	private TableColumn<String, String> coltotsell;
	@FXML
	private TableColumn<String, String> coltotav;
	@FXML
	private TableView<PropertyWineReport> tvDataWine;
	@FXML
	private TableColumn<String, String> colwname;
	@FXML
	private TableColumn<String, String> colwyear;
	@FXML
	private TableColumn<String, String> colsell;
	@FXML
	private TableColumn<String, String> colav;
	@FXML
	private TableColumn<String, String> colincome;
	@FXML
	private TableView<PropertyEmployee> tvDataEmployee;
	@FXML
	private TableColumn<String, String> colemp;
	@FXML
	private TableColumn<String, String> colcomp;
	@FXML
	private TableColumn<String, String> colexp;
	@FXML
	private DatePicker rDate;
	
	private ObservableList<PropertyFinance> tvObservableListFinance = FXCollections.observableArrayList();
	private ObservableList<PropertyWineReport> tvObservableListWine = FXCollections.observableArrayList();
	private ObservableList<PropertyEmployee> tvObservableListEmployee = FXCollections.observableArrayList();
	
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
		pageHandler.loadAdminmenu();
	}
	
	/**
	 * Show the report(in JavaFX table).
	 *
	 * @param r the report.
	 *
	**/
	public void handleReport(Report r)
	{
		PropertyFinance pf = new PropertyFinance(r.getIncome(),r.getExpense(),r.getTotSell(),
				r.getTotAvaiable(),r.getMontYearReport());
		tvObservableListFinance.add(pf);
		//rDate.valueProperty().bindBidirectional(PropertyFinance.getMontYearreport());
		tvDataFinance.setItems(tvObservableListFinance);
		
		for(WineReport w : r.getWineReports())
		{
			PropertyWineReport pw = new PropertyWineReport(w.getWineName(),w.getWineYear(),
					w.getNumSell(),w.getNumAv(),w.getIncome());
			tvObservableListWine.add(pw);
		}
		tvDataWine.setItems(tvObservableListWine);
		
		for(EmployeeReport er : r.getEmpReposrts())
		{
			PropertyEmployee pe = new PropertyEmployee(er.getEmail(),er.getNumCompleted(),er.getNumExpired());
			tvObservableListEmployee.add(pe);
		}
		tvDataEmployee.setItems(tvObservableListEmployee);
	}
	
	/**
	 * Handle create/search report button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleSearchButton(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		if(rDate.getValue() == null)
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Data obbligatoria per la ricerca.");
		}
		else if((rDate.getValue().getMonthValue() < LocalDate.now().getMonthValue() 
				&& rDate.getValue().getYear() <= LocalDate.now().getYear()) 
				|| rDate.getValue().getYear() < LocalDate.now().getYear())
		{
			tvObservableListFinance.clear();
			tvObservableListWine.clear();
			tvObservableListEmployee.clear();
			Report r = client.makeReport(rDate.getValue());
			if(r != null)
			{
				handleReport(r);
			}
			else
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Nessun risultato, "
						+ "non ci sono state vendite/acquisti in questo periodo.");
			}
		}
		else  
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "La data deve essere almeno "
					+ "un mese minore dell'attuale data.");
		}
	}
	
	
	/** {@inheritDoc} **/
	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
		coltotincome.setCellValueFactory(new PropertyValueFactory<>("income"));
		colexpense.setCellValueFactory(new PropertyValueFactory<>("expense"));
		coldate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		coltotsell.setCellValueFactory(new PropertyValueFactory<>("totalSell"));
		coltotav.setCellValueFactory(new PropertyValueFactory<>("totalAvaiable"));	    
	    colwname.setCellValueFactory(new PropertyValueFactory<>("wineName"));
	    colwyear.setCellValueFactory(new PropertyValueFactory<>("wineYear"));
	    colsell.setCellValueFactory(new PropertyValueFactory<>("numSell"));
	    colav.setCellValueFactory(new PropertyValueFactory<>("numAv"));
	    colincome.setCellValueFactory(new PropertyValueFactory<>("income"));	    
	    colemp.setCellValueFactory(new PropertyValueFactory<>("email"));
	    colcomp.setCellValueFactory(new PropertyValueFactory<>("numCompleted"));
	    colexp.setCellValueFactory(new PropertyValueFactory<>("numExpired"));
	    tvDataFinance.setItems(tvObservableListFinance);
	    tvDataWine.setItems(tvObservableListWine);
	    tvDataEmployee.setItems(tvObservableListEmployee);
	}
}
