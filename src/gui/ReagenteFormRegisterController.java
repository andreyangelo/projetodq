package gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Reagente;
import model.services.ReagenteService;

public class ReagenteFormRegisterController implements Initializable{
	
	private Reagente entity;
	
	private ReagenteService service;
	
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtReagente;
	@FXML
	private TextField txtClassificacao;
	@FXML
	private TextField txtFormula;
	@FXML
	private TextField txtRisco;
	@FXML
	private TextField txtFabricante;
	@FXML
	private TextField txtUnidade;
	@FXML
	private TextField txtQuantidadeMinima;
	@FXML
	private TextField txtLocalizacao;
	@FXML
	private Button btSalvar;
	@FXML
	private Button btSair;
	@FXML
	private DatePicker dpDataCadastro;
	
	private ObservableList<Reagente> obsList;
	
	
	
	public void setReagente(Reagente entity) {
		this.entity = entity;
	}
	public void setService(ReagenteService service) {
		this.service = service;
	}
	
	@FXML
	public void onBtSalvalAction() {
		entity = new Reagente();
		//entity = new Reagente("PAULO","AC001","ACETATO DE AMÔNIO","ORGÂNICO","CH3COONH4","SEM RISCO","SIGMA","500 g", 10, "GRADUAÇÃO", LocalDate.of(2022,03,21));
		
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		
		try {
			entity = getFormData();
			//service.register_reagente(entity);
			Alerts.showInformation("Reagente", "Salvo");
		}
		catch(DbException e) {
			Alerts.showAlert("Erros saving object", null, e.getMessage(), AlertType.ERROR);
		}
		
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
		
	}
	
	private Reagente getFormData() {
		Reagente obj = new Reagente();
		obj.setResponsavel("Paulo Couceiro");
		obj.setCodigo(txtCodigo.getText().toUpperCase());
		obj.setReagente(txtReagente.getText().toUpperCase());
		obj.setClassificacao(txtClassificacao.getText().toUpperCase());
		obj.setFormula(txtFormula.getText().toUpperCase());
		obj.setRisco(txtRisco.getText().toUpperCase());
		obj.setFabricante(txtFabricante.getText().toUpperCase());
		obj.setUnidade(txtUnidade.getText().toUpperCase());
		obj.setQuantidadeMinima(Integer.parseInt(txtQuantidadeMinima.getText().toUpperCase()));
		obj.setLocalizacao(txtLocalizacao.getText().toUpperCase());
		obj.setDataCadastro(dpDataCadastro.getValue());
		return obj;
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtCodigo.setText((entity.getCodigo()));
		txtReagente.setText(entity.getReagente());
		txtClassificacao.setText(entity.getClassificacao());
		txtFormula.setText(entity.getFormula());
		txtRisco.setText(entity.getRisco());
		txtFabricante.setText(entity.getFabricante());
		txtUnidade.setText(entity.getUnidade());
		txtQuantidadeMinima.setText(Integer.toString(entity.getQuantidadeMinima()));
		txtLocalizacao.setText(entity.getLocalizacao());
		dpDataCadastro.setValue(entity.getDataCadastro());
	}
}
