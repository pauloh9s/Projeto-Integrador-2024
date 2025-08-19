package com.exemploprojeto.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.EquipamentoDAO;
import com.exemploprojeto.dao.OrcamentoDAO;
import com.exemploprojeto.dao.ServicoDAO;
import com.exemploprojeto.model.Equipamento;
import com.exemploprojeto.model.Orcamento;
import com.exemploprojeto.model.Servico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ServicoDetalhesController implements Initializable {
	
    @FXML
    private Label labelCliente;

    @FXML
    private Label labelDataAbertura;

    @FXML
    private Label labelDataPrevisao;

    @FXML
    private Label labelFuncionario;

    @FXML
    private Label labelIdServico;

    @FXML
    private Label labelStatus;
    
    @FXML
    private Label labelValor;
    
    @FXML
    private TextArea textAreaDiagnostico;

    @FXML
    private TableColumn<Equipamento, Integer> tableColumnEquipamentoID;

    @FXML
    private TableColumn<Equipamento, String> tableColumnEquipamentoModelo;

    @FXML
    private TableView<Equipamento> tableViewEquipamento;

    @FXML
    private TextArea textAreaDescricao;
    
    private Stage dialogStage = new Stage();
    private Servico servico;
    private ServicoDAO servicoDAO = new ServicoDAO();
    
    private List<Equipamento> listEquipamentos;
    private ObservableList<Equipamento> observableEquipamentos;
    private EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
    private Equipamento equipamento;
    private Orcamento orcamento;
    private OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
    private List<Orcamento> orcamentos;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
    
    private ObservableList<Equipamento> equipamentosSelecionados = FXCollections.observableArrayList();

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
		this.labelIdServico.setText(String.valueOf(servico.getId()));
		this.labelDataAbertura.setText(String.valueOf(servico.getDatAb().format(formatter)));
		if(servico.getDatFe() != null) {
			
		this.labelDataPrevisao.setText(String.valueOf(servico.getDatFe().format(formatter)));
		
		} else {
			
		this.labelDataPrevisao.setText("");
		
		}
		
		this.labelStatus.setText(servico.getStatus());
		this.labelFuncionario.setText(String.valueOf(servico.getFuncionario()));
		this.labelCliente.setText(String.valueOf(servico.getCliente()));
		this.textAreaDescricao.setText(servico.getDescricao());
		this.equipamentosSelecionados.setAll(servico.getEquipamento());
		this.labelValor.setText("");
		this.textAreaDiagnostico.setText("");
		
		orcamentos = orcamentoDAO.findByServico(this.servico);
		for (Orcamento orcamentoProcurado: orcamentos) {
			if (orcamentoProcurado != null) {
				
				this.labelValor.setText(String.valueOf(orcamentoProcurado.getValor()));
				this.textAreaDiagnostico.setText(orcamentoProcurado.getDiagnostico());
				
		
			}
		}

	}
	
	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarEquipamentos();
	}
	
	public void carregarEquipamentos() {
		
		tableColumnEquipamentoID.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnEquipamentoModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

		tableViewEquipamento.setItems(equipamentosSelecionados);
		
	}

}
