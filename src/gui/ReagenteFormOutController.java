package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entities.Reagente;
import model.services.ReagenteService;

public class ReagenteFormOutController implements Initializable{
	
	private Reagente entity;
	
	private ReagenteService service;
	
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtReagente;
	@FXML
	private ComboBox<String> comboBoxFabricante;
	@FXML
	private ComboBox<String> comboBoxUnidade;
	@FXML
	private TextField txtQuantidade;
	@FXML
	private ComboBox<String> comboBoxLocal;
	@FXML
	private ComboBox<String> comboBoxCarrega;
	@FXML
	private Button btSalvar; // esse botão é para enviar o reagente
	@FXML
	private Button btSair;
	@FXML
	private DatePicker dpDataEntrada;
	
	private ObservableList<String> obsList;
	

	public void setReagente(Reagente entity) {
		this.entity = entity;
	}
	
	public void setService(ReagenteService service) {
		this.service = service;
	}
	
	//botão subtrai do estoque
	@FXML
	public void onBtSalvalAction() {
		//int id;
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		
		try {
			//Reagente reagente;
			entity = getFormData();
			//id = service.insert_reagent(entity);
			//reagente = service.get_inserted(19);
			service.minus_stock_reagente(entity);
			
			//System.out.println(reagente.getId());
		}
		catch(DbException e) {
			Alerts.showAlert("Erros saving object", null, e.getMessage(), AlertType.ERROR);
		}
		
		System.out.println("onBtEnviarAction");
	}
	
	@FXML
	public void onBtSairAction(ActionEvent event) {
		Stage currentStage = Utils.currentStage(event);
		currentStage.close();
	}
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	public void initializeNodes() {
		initializeComboBox();
	}
	
	private Reagente getFormData() {
		Reagente obj = new Reagente();
		
		obj.setResponsavel("Paulo Couceiro");
		obj.setCodigo(txtCodigo.getText().toUpperCase());
		obj.setReagente(txtReagente.getText().toUpperCase());
		obj.setFabricante(comboBoxFabricante.getValue().toUpperCase());
		obj.setUnidade(comboBoxUnidade.getValue().toUpperCase());
		obj.setQuantidade(Integer.parseInt(txtQuantidade.getText().toUpperCase()));
		obj.setLocalizacao(comboBoxLocal.getValue().toUpperCase());
		obj.setDataEntrada(dpDataEntrada.getValue());
		
		return obj;
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtCodigo.setText((entity.getCodigo()));
		txtReagente.setText(entity.getReagente());
		comboBoxUnidade.setValue(entity.getUnidade());
		comboBoxLocal.setValue(entity.getLocalizacao());
		dpDataEntrada.setValue(entity.getDataEntrada());
		
		if(entity.getFabricante() == null) {
			comboBoxFabricante.getSelectionModel().selectFirst();
		}
		else {
			comboBoxFabricante.setValue(entity.getFabricante());
		}
		
	}
	
	public void loadAssociatedObjects() {
		int cont;
		if(service==null) {
			throw new IllegalStateException("Service was null");
		}
		List<Reagente> list = service.findAll();
		List<String> fabricante = new ArrayList<String>();
		List<String> unidade = new ArrayList<String>();
		List<String> local = new ArrayList<String>();
		
		for(cont=0;cont<list.size();cont++) {
			fabricante.add(list.get(cont).getFabricante());
			unidade.add(list.get(cont).getUnidade());
			local.add(list.get(cont).getLocalizacao());
		}
		obsList = FXCollections.observableArrayList(fabricante);
		comboBoxFabricante.setItems(obsList);
		obsList = FXCollections.observableArrayList(unidade);
		comboBoxUnidade.setItems(obsList);
		obsList = FXCollections.observableArrayList(local);
		comboBoxLocal.setItems(obsList);
	}
	
	public void initializeComboBox() {
		Callback<ListView<String>, ListCell<String>> factory = lv -> new ListCell<String>() {
			
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item);
			}
		};
		//comboBoxFabricante.setCellFactory(factory);
		//comboBoxFabricante.setButtonCell(factory.call(null));
	}
}
