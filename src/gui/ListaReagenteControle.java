package gui;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Reagente;
import model.services.ReagenteService;

public class ListaReagenteControle implements Initializable{
	
	private ReagenteService reagenteService;
	
	@FXML
	private TableView<Reagente> tableViewReagente;
	
	@FXML
	private TableColumn<Reagente,String> tableColumnCodigo;
	
	@FXML
	private TableColumn<Reagente,String> tableColumnSubstancia;
	
	@FXML
	private TableColumn<Reagente,String> tableColumnFabricante;
	
	@FXML
	private TableColumn<Reagente,String> tableColumnUnidade;
	
	@FXML
	private TableColumn<Reagente,Integer> tableColumnQuantidade;
	
	@FXML
	private TableColumn<Reagente,Integer> tableColumnLocal;
	
	@FXML
	private TableColumn<Reagente,LocalDate> tableColumnData;
	
	@FXML
	private TableColumn<Reagente,String> tableColumnUsuario;
	
	@FXML
	private Button btEntrada;
	
	@FXML
	private Button btRetirada;
	
	private ObservableList<Reagente> obsList;
	
	public ReagenteService getReagenteService() {
		return this.reagenteService;
	}

	public void setReagenteService(ReagenteService reagenteService) {
		this.reagenteService = reagenteService;
	}
	
	public void onBtEntradaAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/ReagenteForm.fxml", parentStage);
	}
	
	public void onBtRetiradaAction() {
		System.out.println("onBtRetiradaAction");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tableColumnSubstancia.setCellValueFactory(new PropertyValueFactory<>("reagente"));
		tableColumnFabricante.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
		tableColumnUnidade.setCellValueFactory(new PropertyValueFactory<>("unidade"));
		tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tableColumnLocal.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
		tableColumnData.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
		tableColumnUsuario.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
		
		Stage stage = (Stage)Main.getMainScene().getWindow();
		tableViewReagente.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if(reagenteService == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Reagente> list = getReagenteService().findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewReagente.setItems(obsList);
	}
	
	private void createDialogForm(String absoluteName, Stage parentStage) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Reagente");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
		}
		
		catch(IOException e) {
			Alerts.showAlert("IOException", "Error", e.getMessage(), AlertType.ERROR);
		}
	}

}
