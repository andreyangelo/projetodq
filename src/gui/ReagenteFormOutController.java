package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
import model.exceptions.ValidationException;
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
		catch(ValidationException e) {
			setErrorMessage(e.getErros());
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
		ValidationException exception = new ValidationException("Errors");
		
		obj.setResponsavel("Paulo Couceiro");
		
		if(txtCodigo.getText()==null) {
			exception.addError("codigo", "Insira o Codigo");
			throw exception;
		}
		else {
			obj.setCodigo(txtCodigo.getText().toUpperCase());
		}
		if(txtReagente.getText()==null) {
			exception.addError("reagente", "Insira o Reagente");
		}
		else {
			obj.setReagente(txtReagente.getText().toUpperCase());
		}
		if(comboBoxFabricante.getValue()==null) {
			exception.addError("fabricante", "Insira o Fabricante");
		}
		else {
			obj.setFabricante(comboBoxFabricante.getValue().toUpperCase());
		}
		if(comboBoxUnidade.getValue()==null) {
			exception.addError("unidade", "Insira a Unidade");
		}
		else {
			obj.setUnidade(comboBoxUnidade.getValue().toUpperCase());
		}
		/*if(txtQuantidade.getText()==null) {
			exception.addError("quantidade", "Insira a Quantidade");
		}
		else {
			obj.setQuantidade(Integer.parseInt(txtQuantidade.getText().toUpperCase()));
		}*/
		if(comboBoxLocal.getValue()==null) {
			exception.addError("localizacao", "Insira a Localizacao");
		}
		else {
			obj.setLocalizacao(comboBoxLocal.getValue().toUpperCase());
		}
		if(dpDataEntrada.getValue()==null) {
			exception.addError("data_entrada", "Insira a Data");
		}
		else {
			obj.setDataEntrada(dpDataEntrada.getValue());
		}
		
		/*if(exception.getErros().size()>0) {
			throw exception;
		}
		else {
			return obj;
		}*/
		
		return obj;
	}
	
	public void setErrorMessage(Map<String, String> errors ) {
		
		Set<String> fields = errors.keySet();
		
		if(fields.contains("codigo")) {
			Alerts.showAlert("Cadastro Incompleto", null, errors.get("codigo"), AlertType.ERROR);
		}
		if(fields.contains("reagente")) {
			Alerts.showAlert("Cadastro Incompleto", null, errors.get("reagente"), AlertType.ERROR);
		}
		if(fields.contains("fabricante")) {
			Alerts.showAlert("Cadastro Incompleto", null, errors.get("fabricante"), AlertType.ERROR);
		}
		if(fields.contains("unidade")) {
			Alerts.showAlert("Cadastro Incompleto", null, errors.get("unidade"), AlertType.ERROR);
		}
		if(fields.contains("quantidade")) {
			Alerts.showAlert("Cadastro Incompleto", null, errors.get("quantidade"), AlertType.ERROR);
		}
		if(fields.contains("localizacao")) {
			Alerts.showAlert("Cadastro Incompleto", null, errors.get("localizacao"), AlertType.ERROR);
		}
		if(fields.contains("data_entrada")) {
			Alerts.showAlert("Cadastro Incompleto", null, errors.get("data_entrada"), AlertType.ERROR);
		}
		
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
	
	//inicializa os comboBox da Tela
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
	
	
	//não está sendo usada
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
