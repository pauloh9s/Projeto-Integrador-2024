package com.exemploprojeto.app;

import com.exemploprojeto.connect.Connect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		
		Connect connect = new Connect();
		connect.getEntityManager();
		
		Parent root = FXMLLoader.load(getClass().getResource("/com/exemploprojeto/view/PaneLogin.fxml"));
	
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/exemploprojeto/css/style.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Projeto Integrador");
		stage.setResizable(false);
		stage.show();
	
	}

}
