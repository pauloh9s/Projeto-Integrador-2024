package com.exemploprojeto.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.CategoriaEquipamentoDAO;
import com.exemploprojeto.model.CategoriaEquipamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroCategoriaEquipamentoController implements Initializable{
	
	@FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private TextArea textAreaDescricao;

    @FXML
    private TextField textFieldNome;
    
    private Stage dialogStage;
    private boolean buttonConfirmarClick;
    
    private CategoriaEquipamento categoriaEquipamento;
    private CategoriaEquipamentoDAO categoriaEquipamentoDAO = new CategoriaEquipamentoDAO();
    

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isButtonConfirmarClick() {
		return buttonConfirmarClick;
	}

	public void setButtonConfirmarClick(boolean buttonConfirmarClick) {
		this.buttonConfirmarClick = buttonConfirmarClick;
	}

	public CategoriaEquipamento getCategoriaEquipamento() {
		return categoriaEquipamento;
	}

	public void setCategoriaEquipamento(CategoriaEquipamento categoriaEquipamento) {
		this.categoriaEquipamento = categoriaEquipamento;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	 @FXML
	    void handleButtonConfirmar(ActionEvent event) {
		 
		 if(EntradaDeDados()) {
				
				categoriaEquipamento.setNome(textFieldNome.getText());
				categoriaEquipamento.setDesc(textAreaDescricao.getText());
				
				buttonConfirmarClick = true;
				dialogStage.close();
		 	}
	 	}
	 @FXML
	    void handleButtonCancelar(ActionEvent event) {
		 	dialogStage.close();
	    }
	 
	 private boolean EntradaDeDados(){
			
			String errorMessage = "";
			
			if(textFieldNome.getText() == null || textFieldNome.getText().length() == 0) {
				errorMessage += "Nome invalido \n";
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
