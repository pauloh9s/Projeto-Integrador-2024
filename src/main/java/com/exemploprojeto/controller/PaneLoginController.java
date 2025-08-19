package com.exemploprojeto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.FuncionarioDAO;
import com.exemploprojeto.model.Funcionario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PaneLoginController implements Initializable{
	
		@FXML
	    private AnchorPane anchorPaneLogin;
	
	 	@FXML
	    private Button buttonLogin;

	    @FXML
	    private PasswordField passwordFieldSenha;

	    @FXML
	    private TextField textFieldNome;
	    
	    private Funcionario funcionario;
	    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	    private List<Funcionario> funcionarios = funcionarioDAO.selectAll();
	    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	public void handleButtonLogin() throws IOException{
		
	if(VerificarDados()) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/exemploprojeto/view/PaneMain.fxml"));
		VBox root = loader.load();
		anchorPaneLogin.getChildren().setAll(root);
		
		String nameUser = textFieldNome.getText();
	
	    PaneMainController controller = loader.getController();
	    if(!funcionarios.isEmpty()) {
	    controller.setLoginUser(nameUser);}
		
		}
	}
	
	public boolean VerificarDados() {
	  String nomeInvalido = "Nome inválido \n";
	  String senhaInvalida = "Senha inválida \n";
		    
	  boolean nomeCorreto = false;
	  boolean senhaCorreta = false;
	  
	  if (funcionarios.isEmpty()) {
		  
		  nomeCorreto = true;
		  senhaCorreta = true;
		  
	  } else {
		    
	  for (Funcionario funcionario : funcionarios) {
		  if (funcionario.getNome().equals(textFieldNome.getText())) {
		      nomeCorreto = true;
		      if (funcionario.getSenha().equals(passwordFieldSenha.getText())) {
		          senhaCorreta = true;
		            break; 
		          }}
		        }
	 
		    String errorMessage = "";
		    
		    if (!nomeCorreto) {
		        errorMessage += nomeInvalido;
		    }
		    if (!senhaCorreta) {
		        errorMessage += senhaInvalida;
		    }
		    if (!errorMessage.isEmpty()) {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Erro no Login");
		        alert.setHeaderText("Campo inválido...");
		        alert.setContentText(errorMessage);
		        alert.show();
		        return false;
		    } else {
		    return true; 
		  }
	  } return true;
	}
}
