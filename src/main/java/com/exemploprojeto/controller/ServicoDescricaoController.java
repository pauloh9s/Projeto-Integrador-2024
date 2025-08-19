package com.exemploprojeto.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.ServicoDAO;
import com.exemploprojeto.model.Servico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ServicoDescricaoController implements Initializable{
	
	   @FXML
	    private Button buttonCancelar;

	    @FXML
	    private Button buttonConfirmar;

	    @FXML
	    private TextArea textAreaDescricao;
	    
	    private Stage dialogStage = new Stage();
	    private boolean buttonConfirmarClick = false;
	    private Servico servico;
	    private ServicoDAO servicoDAO = new ServicoDAO();

	    
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

		public Servico getServico() {
			return servico;
		}

		public void setServico(Servico servico) {
			this.servico = servico;
			this.textAreaDescricao.setText(servico.getDescricao());
		}
		 public String getDescricao() {
			 
			 if (buttonConfirmarClick) {
				 return textAreaDescricao.getText();
			 } else {
				 return null;
			 }
		 
		 }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	@FXML
    void handleButtonConfirmar(ActionEvent event) {
		buttonConfirmarClick = true;
		dialogStage.close();
    }
	
	@FXML
    void handleButtonCancelar(ActionEvent event) {
		dialogStage.close();
    }


}
