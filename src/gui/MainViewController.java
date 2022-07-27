package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.entities.Reagente;
import model.services.ReagenteService;

public class MainViewController implements Initializable{
	
	@FXML
	private MenuBar myMenuBar;
	@FXML
	private MenuItem menuItemCadastroReagente;
	@FXML
	private MenuItem menuItemEntradaReagente;
	@FXML 
	private MenuItem menuItemRetiradaReagente;
	@FXML
	private MenuItem menuItemEstoqueReagente;
	@FXML
	private MenuItem menuItemRelatorioReagente;
	@FXML
	private MenuItem menuItemMaterial;
	@FXML
	private MenuItem menuItemEquipamento;
	@FXML
	private MenuItem menuItemSobre;
	
	@FXML //abre a tela de cadastro de reagente
	public void onMenuItemCadastroReagenteAction(){
		Stage parentStage = Utils.currentStage(myMenuBar);
		//Reagente obj = new Reagente();
		createDialogForm("/gui/ReagenteFormRegister.fxml", parentStage,
				(ReagenteFormRegisterController controller) ->{
					controller.setService(new ReagenteService());
				});
	}
	
	@FXML
	public void onMenuItemEntradaReagenteAction(ActionEvent event){
		Stage parentStage = Utils.currentStage(myMenuBar);
		Reagente obj = new Reagente();
		createDialogForm("/gui/ReagenteForm.fxml", parentStage, 
				(ReagenteFormController controller) -> {
					controller.setReagente(obj);
					controller.updateFormData();
					controller.setService(new ReagenteService());
					controller.loadAssociatedObjects();
				});
	}
	
	@FXML
	public void onMenuItemRetiradaReagenteAction(){
		Stage parentStage = Utils.currentStage(myMenuBar);
		Reagente obj = new Reagente();
		createDialogForm("/gui/ReagenteFormOut.fxml", parentStage, 
				(ReagenteFormOutController controller) -> {
					controller.setReagente(obj);
					controller.updateFormData();
					controller.setService(new ReagenteService());
					controller.loadAssociatedObjects();
				});
	}
	
	@FXML
	public void onMenuItemEstoqueReagenteAction() {
		loadView("/gui/ListaReagente.fxml", (ListaReagenteControle controller) -> {
			controller.setReagenteService(new ReagenteService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemRelatorioReagenteAction(){
		System.out.println("onMenuItemRelatorioReagenteAction");
	}
	
	@FXML
	public void onMenuItemMaterialAction(){
		System.out.println("onMenuItemMaterialAction");
	}
	@FXML
	public void onMenuItemEquipamentoAction(){
		System.out.println("onMenuItemEquipamentoAction");
	}
	@FXML
	public void onMenuItemSobreAction(){
		loadView("/gui/Sobre.fxml", x -> {});
	}
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
		
	}
	
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
				
			if(loader.load() instanceof VBox) {
				//VBox newVbox = loader.load();
				VBox newVbox = loader.getRoot();
				Scene mainScene = Main.getMainScene();
				VBox mainVBox = (VBox)(((ScrollPane)mainScene.getRoot()).getContent());
				Node mainMenu = mainVBox.getChildren().get(0);
				mainVBox.getChildren().clear();
				mainVBox.getChildren().add(mainMenu);
				mainVBox.getChildren().addAll(newVbox.getChildren());
				T controller = loader.getController();
				initializingAction.accept(controller);
				
			}
			
			else {
				
				loader = new FXMLLoader(getClass().getResource(absoluteName));
				Pane pane = loader.load();
				T controller = loader.getController();
				initializingAction.accept(controller);
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Reagente");
				dialogStage.setScene(new Scene(pane));
				dialogStage.showAndWait();
				
				
			}
		}
		catch(IOException e) {
			Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private synchronized <T> void createDialogForm(String absoluteName, 
			Stage parentStage, Consumer <T> initializingAction) {
		
			try {
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
				AnchorPane pane = loader.load();
				
				
				T controller = loader.getController();
				initializingAction.accept(controller);
				
				System.out.println(pane.getPrefWidth());
				System.out.println(pane.getPrefHeight());
				System.out.println();
				
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Reagente");
				dialogStage.setScene(new Scene(pane));
				dialogStage.setWidth(pane.getPrefWidth()+AnchorPane.getLeftAnchor(pane.getChildren().get(0)));
				dialogStage.setHeight(pane.getPrefHeight()+AnchorPane.getTopAnchor(pane.getChildren().get(0)));
				
				
				//System.out.println(dialogStage.getWidth());
				//System.out.println(dialogStage.getHeight());
				//System.out.println(parentStage.getWidth()/2.0);
				//System.out.println(parentStage.getWidth()/2.0);
				//System.out.println(dialogStage.getWidth()+parentStage.getWidth());
				
				dialogStage.setX((parentStage.getWidth()/2.0)-(dialogStage.getWidth()/2));
				dialogStage.setY((parentStage.getHeight()/2.0)-(dialogStage.getHeight()/2));
				dialogStage.showAndWait();
				
				
			
			}
			
			catch(IOException e) {
				e.printStackTrace();
				Alerts.showAlert("IO Exception", null, e.getMessage(), AlertType.ERROR);
			}
	}
	
	
}
