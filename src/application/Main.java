package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

//atualizado em 03-10-2022
public class Main extends Application {
	
	private static Scene mainScene;
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			ScrollPane scrollPane = FXMLLoader.load(getClass().getResource("/gui/MainView.fxml"));
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			mainScene = new Scene(scrollPane);
			mainScene.setFill(null);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Gerenciador");
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
