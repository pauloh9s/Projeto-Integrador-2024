package com.exemploprojeto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.EquipamentoDAO;
import com.exemploprojeto.model.Equipamento;
import com.exemploprojeto.model.Funcionario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CadastroEquipamentoController implements Initializable {
	
	 	@FXML
	    private Button buttonAlterar;

	    @FXML
	    private Button buttonInserir;

	    @FXML
	    private Button buttonRemover;

	    @FXML
	    private TableColumn<Equipamento, String> tableColumnCategoria;

	    @FXML
	    private TableColumn<Equipamento, String> tableColumnCliente;

	    @FXML
	    private TableColumn<Equipamento, Integer> tableColumnId;

	    @FXML
	    private TableColumn<Equipamento, String> tableColumnMarca;

	    @FXML
	    private TableColumn<Equipamento, String> tableColumnModelo;

	    @FXML
	    private TableView<Equipamento> tableViewEquipamento;
	    
	    private List<Equipamento> listEquipamentos;
	    private ObservableList<Equipamento> observableEquipamentos;
	    private Equipamento equipamento;
	    private EquipamentoDAO equipamentoDAO = new EquipamentoDAO();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarTableView();
	}
	
	public void carregarTableView(){
		
		tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		tableColumnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		tableColumnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaEquipamento"));
		
		listEquipamentos = equipamentoDAO.selectAll();
		
		observableEquipamentos = FXCollections.observableArrayList(listEquipamentos);
		tableViewEquipamento.setItems(observableEquipamentos);

	}
	
	@FXML
	public boolean showCadastroEquipamentoDialog(Equipamento equipamento) throws IOException{
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CadastroEquipamentoDialogController.class.getResource("/com/exemploprojeto/view/CadastroEquipamentoDialog.fxml"));
		AnchorPane ap = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		Scene scene = new Scene(ap);
		dialogStage.setResizable(false);
		dialogStage.setTitle("Cadastro/Alteração de Equipamento");
		dialogStage.setScene(scene);
		
		CadastroEquipamentoDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setEquipamento(equipamento);
		
		dialogStage.showAndWait();
		
		return controller.isButtonConfirmarClick();
	}
	
	  @FXML
	    void handleButtonInserir(ActionEvent event) throws IOException {
		 Equipamento equipamento = new Equipamento();
			boolean buttonConfirmarClick = showCadastroEquipamentoDialog(equipamento);
			if(buttonConfirmarClick) {
				equipamentoDAO.insert(equipamento);
				carregarTableView();
			}
	    }
	  
	  @FXML
	    void handleButtonAlterar(ActionEvent event) throws IOException {
		  Equipamento equipamento = tableViewEquipamento.getSelectionModel().getSelectedItem();
			if(equipamento != null) {
				
				boolean buttonConfirmarClick = showCadastroEquipamentoDialog(equipamento);
				if(buttonConfirmarClick) {
					equipamentoDAO.update(equipamento);
					carregarTableView();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Selecione um equipamento na tabela.");
				alert.show();
			}

	    }

	  @FXML
	    void handleButtonRemover(ActionEvent event) throws IOException {
		 Equipamento equipamento = tableViewEquipamento.getSelectionModel().getSelectedItem();
			if(equipamento != null) {
				
				equipamentoDAO.delete(equipamento);
				carregarTableView();
				
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Selecione um equipamento na tabela.");
				alert.show();
			}
	  }

}

