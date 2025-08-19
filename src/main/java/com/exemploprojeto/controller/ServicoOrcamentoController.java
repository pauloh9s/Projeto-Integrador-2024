package com.exemploprojeto.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.OrcamentoDAO;
import com.exemploprojeto.dao.ServicoDAO;
import com.exemploprojeto.model.Orcamento;
import com.exemploprojeto.model.Servico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ServicoOrcamentoController implements Initializable {

	   @FXML
	    private Button buttonCancelar;

	    @FXML
	    private Button buttonConfirmar;

	    @FXML
	    private TextArea textAreaDescricao;

	    @FXML
	    private TextField textFieldValor;
	    
	    private Stage dialogStage = new Stage();
	    private boolean buttonConfirmarClick = false;
	    private Servico servico;
	    private ServicoDAO servicoDAO = new ServicoDAO();
	    private Orcamento orcamento;
	    private OrcamentoDAO orcamentoDAO;
	    
	    
	    
	  
	public Orcamento getOrcamento() {
			return orcamento;
		}

		public void setOrcamento(Orcamento orcamento) {
			this.orcamento = orcamento;
			
			 if (orcamento != null) {
			        textFieldValor.setText(String.valueOf(orcamento.getValor()));
			        textAreaDescricao.setText(orcamento.getDiagnostico());
			    }
			 this.buttonConfirmar.setDisable(true);
		}

		public OrcamentoDAO getOrcamentoDAO() {
			return orcamentoDAO;
		}

		public void setOrcamentoDAO(OrcamentoDAO orcamentoDAO) {
			this.orcamentoDAO = orcamentoDAO;
		}

	public Stage getDialogStage() {
			return dialogStage;
		}

		public void setDialogStage(Stage dialogStage) {
			this.dialogStage = dialogStage;
		}

		public Servico getServico() {
			return servico;
		}

		public void setServico(Servico servico) {
			this.servico = servico;
		}

		public ServicoDAO getServicoDAO() {
			return servicoDAO;
		}

		public void setServicoDAO(ServicoDAO servicoDAO) {
			this.servicoDAO = servicoDAO;
		}
		
		public boolean isButtonConfirmarClick() {
			return buttonConfirmarClick;
		}

		public void setButtonConfirmarClick(boolean buttonConfirmarClick) {
			this.buttonConfirmarClick = buttonConfirmarClick;
		}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		
		textFieldValor.textProperty().addListener((observable, oldValue, newValue) -> {
		if(newValue != null) {
			if (!newValue.equals(oldValue)) {
		        this.buttonConfirmar.setDisable(false);
		       
		    }}
		});
		
		textAreaDescricao.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null) {
			if (!newValue.equals(oldValue)) {
		        this.buttonConfirmar.setDisable(false);
		       
		    }}
		});
	}
	
	@FXML
    void handleButtonConfirmar(ActionEvent event) {
		
		orcamento.setValor(Float.valueOf(textFieldValor.getText()));
		orcamento.setDiagnostico(textAreaDescricao.getText());
		System.out.println(orcamento.getId());
		
		buttonConfirmarClick = true;
		dialogStage.close();
    }
	
	@FXML
    void handleButtonCancelar(ActionEvent event) {
		dialogStage.close();
    }

}
