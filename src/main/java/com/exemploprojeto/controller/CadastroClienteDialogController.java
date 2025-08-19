package com.exemploprojeto.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.ClienteDAO;
import com.exemploprojeto.model.Cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroClienteDialogController implements Initializable {

	   @FXML
	    private Button buttonCancelar;

	    @FXML
	    private Button buttonConfirmar;

	    @FXML
	    private Label labelEmail;

	    @FXML
	    private Label labelEndereco;

	    @FXML
	    private Label labelNome;

	    @FXML
	    private Label labelTelefone;

	    @FXML
	    private TextField textFieldEmail;

	    @FXML
	    private TextField textFieldEndereco;

	    @FXML
	    private TextField textFieldNome;

	    @FXML
	    private TextField textFieldTelefone;
	    
	    private Stage dialogStage;
	    public Stage getDialogStage() {
			return dialogStage;
		}
		public void setDialogStage(Stage dialogStage) {
			this.dialogStage = dialogStage;
			
			dialogStage.setOnCloseRequest(event -> {
		        if (!buttonConfirmarClick) {  
		            cliente = null; 
		        }
		    });
		}
		
	    private boolean buttonConfirmarClick = false;
	    private Cliente cliente;
	    private ClienteDAO clienteDAO = new ClienteDAO();
	    
		public boolean isButtonConfirmarClick() {
			return buttonConfirmarClick;
		}

		public void setButtonConfirmarClick(boolean buttonConfirmarClick) {
			this.buttonConfirmarClick = buttonConfirmarClick;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
			this.textFieldNome.setText(cliente.getNome());
			this.textFieldTelefone.setText(cliente.getTelefone());
			this.textFieldEmail.setText(cliente.getEmail());
			this.textFieldEndereco.setText(cliente.getEndereco());
		}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	 @FXML
	    void handleButtonConfirmar() {
		 
		 if(EntradaDeDados()) {
				
				cliente.setNome(textFieldNome.getText());
				cliente.setTelefone(textFieldTelefone.getText());
				cliente.setEmail(textFieldEmail.getText());
				cliente.setEndereco(textFieldEndereco.getText());
				
				buttonConfirmarClick = true;
				dialogStage.close();
		 	}
	    }
	   @FXML
	    void handleButtonCancelar() {
		   dialogStage.close();
	    }
	   private boolean EntradaDeDados(){
			
			String errorMessage = "";
			
			if(textFieldNome.getText() == null || textFieldNome.getText().length() == 0) {
				errorMessage += "Nome invalido \n";
			} 
			if(textFieldTelefone.getText() == null ||  textFieldTelefone.getText().length() == 0){
				errorMessage += "Telefone invalido \n";	
			}
			if(textFieldEmail.getText() == null ||  textFieldEmail.getText().length() == 0){
				errorMessage += "Email invalida \n";
			}
			
			if(errorMessage.length() == 0) {
				return true;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Erro ao cadastrar");
				alert.setHeaderText("Campo inválido...");
				alert.setContentText(errorMessage);
				alert.show();
				return false;
			}
		}
}
