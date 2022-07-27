package gui.util;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class Utils {
	
	public static Stage currentStage(ActionEvent event) {
		return (Stage)((Node)event.getSource()).getScene().getWindow();
	}
	
	public static Stage currentStage(MenuBar myMenuBar) {
		return (Stage) myMenuBar.getScene().getWindow();
	}
	
	public static List<String> listComboBoxFabricante(){
		List<String> list = new ArrayList<>();
		list.add("MERC");
		list.add("SIGMA");
		return list;
	}
	
	public static List<String> listComboBoxUnidade(){
		List<String> list = new ArrayList<>();
		list.add("500g");
		list.add("250g");
		return list;
	}
	
	public static List<String> listComboBoxReagente(){
		List<String> list = new ArrayList<>();
		list.add("ACETATO DE AM�NIO");
		list.add("ACETATO DE B�RIO");
		return list;
	}
	public static List<String> listComboBoxLocal(){
		List<String> list = new ArrayList<>();
		list.add("LABORAT�RIO DE GRADUA��O");
		list.add("LABORAT�RIO DE P�S-GRADUA��O");
		return list;
	}

}
