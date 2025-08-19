package com.exemploprojeto.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.FuncionarioDAO;
import com.exemploprojeto.model.Funcionario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroFuncionarioDialogController implements Initializable {
	
	 @FXML
	    private Button buttonCancelar;

	    @FXML
	    private Button buttonConfirmar;
	    
	    @FXML
	    private CheckBox checkBoxAdm;
	    
	    @FXML
	    private Label labelCargo;

	    @FXML
	    private Label labelEmail;

	    @FXML
	    private Label labelNome;
	    
	    @FXML
	    private Label labelSenha;

	    @FXML
	    private Label labelTelefone;
	    

	    @FXML
	    private TextField textFieldCargo;

	    @FXML
	    private TextField textFieldEmail;

	    @FXML
	    private TextField textFieldNome;
	    
	    @FXML
	    private TextField textFieldSenha;


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
			            funcionario = null; 
			        }
			    });
		}
		public boolean isButtonConfirmarClick() {
			return buttonConfirmarClick;
		}
		public void setButtonConfirmarClick(boolean buttonConfirmarClick) {
			this.buttonConfirmarClick = buttonConfirmarClick;
		}
		public Funcionario getFuncionario() {
			return funcionario;
		}
		public void setFuncionario(Funcionario funcionario) {
			this.funcionario = funcionario;
			this.textFieldNome.setText(funcionario.getNome());
			this.textFieldSenha.setText(funcionario.getSenha());
			this.textFieldTelefone.setText(funcionario.getTelefone());
			this.textFieldEmail.setText(funcionario.getEmail());
			this.textFieldCargo.setText(funcionario.getCargo());
			
			if(funcionario.isAdmin() == true) {
				this.checkBoxAdm.setSelected(true);
			}
		}
		
		public FuncionarioDAO getFuncionarioDAO() {
			return funcionarioDAO;
		}
		public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
			this.funcionarioDAO = funcionarioDAO;
			
		}

		private boolean buttonConfirmarClick = false;
	    private Funcionario funcionario;
	    private FuncionarioDAO funcionarioDAO;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean checkedBoxAdm() {
		   
		   if(checkBoxAdm.isSelected()) {
			   return true;
		   } else {
			   return false;
		   }
	   }
	
	 @FXML
	  void handleButtonConfirmar() {
		 
		 if(EntradaDeDados()) {
				
				funcionario.setNome(textFieldNome.getText());
				funcionario.setSenha(textFieldSenha.getText());
				funcionario.setTelefone(textFieldTelefone.getText());
				funcionario.setEmail(textFieldEmail.getText());
				funcionario.setCargo(textFieldCargo.getText());
				funcionario.setAdmin(checkedBoxAdm());
				
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
			if(textFieldCargo.getText() == null ||  textFieldCargo.getText().length() == 0){
				errorMessage += "Cargo invalida \n";
			}
			if(textFieldSenha.getText() == null ||  textFieldSenha.getText().length() == 0){
				errorMessage += "Senha invalida \n";
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
