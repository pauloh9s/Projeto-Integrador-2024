package com.exemploprojeto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.FuncionarioDAO;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CadastroFuncionarioController implements Initializable{
	
	 	@FXML
	    private Button buttonAlterar;

		@FXML
		private Button buttonInserir;

	    @FXML
	    private Button buttonRemover;
	    
	    @FXML
	    private Label labelEmailFuncionario;
	    
	    @FXML
	    private Label labelTelefoneFuncionario;

	    @FXML
	    private Label labelCargoFuncionario;

	    @FXML
	    private Label labelDataContratoFuncionario;

	    @FXML
	    private Label labelIdFuncionario;

	    @FXML
	    private Label labelNomeFuncionario;

	    @FXML
	    private TableColumn<Funcionario, String> tableColumnNome;

	    @FXML
	    private TableColumn<Funcionario, Integer> tableColumnId;

	    @FXML
	    private TableView<Funcionario> tableViewFuncionario;

	    private List<Funcionario> listFuncionarios;
	    private ObservableList<Funcionario> observableFuncionarios;
	    private Funcionario funcionario;
	    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarTableView();
		tableViewFuncionario.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue ) -> selectTableView(newValue));
	}
	
	public void carregarTableView(){
		
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		listFuncionarios = funcionarioDAO.selectAll();
		
		observableFuncionarios = FXCollections.observableArrayList(listFuncionarios);
		tableViewFuncionario.setItems(observableFuncionarios);

	}
	
	public void selectTableView(Funcionario funcionario){
	
		 if (funcionario != null) {
	            labelIdFuncionario.setText(String.valueOf(funcionario.getId()));
	            labelNomeFuncionario.setText(funcionario.getNome());
	            labelTelefoneFuncionario.setText(funcionario.getTelefone());
	            labelEmailFuncionario.setText(funcionario.getEmail());
	            labelCargoFuncionario.setText(funcionario.getCargo());
	        } else {
	            labelIdFuncionario.setText("");
	            labelNomeFuncionario.setText("");
	            labelTelefoneFuncionario.setText("");
	            labelEmailFuncionario.setText("");
	            labelCargoFuncionario.setText("");
	        }
		
	}
	
	@FXML
	public boolean showCadastroFuncionarioDialog(Funcionario funcionario) throws IOException{
		
	
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CadastroFuncionarioDialogController.class.getResource("/com/exemploprojeto/view/CadastroFuncionarioDialog.fxml"));
		AnchorPane ap = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		Scene scene = new Scene(ap);
		dialogStage.setResizable(false);
		dialogStage.setTitle("Cadastro/Alteração de Funcionarios");
		dialogStage.setScene(scene);
		
		CadastroFuncionarioDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setFuncionario(funcionario);
		
		dialogStage.showAndWait();
		
		return controller.isButtonConfirmarClick();
	}
	
	@FXML
	 public void handleButtonInserir() throws IOException{
			Funcionario funcionario = new Funcionario();
			boolean buttonConfirmarClick = showCadastroFuncionarioDialog(funcionario);
			if(buttonConfirmarClick) {
				funcionarioDAO.insert(funcionario);
				carregarTableView();
		}
	}
	 @FXML
	 void handleButtonAlterar() throws IOException{
		 	Funcionario funcionario = tableViewFuncionario.getSelectionModel().getSelectedItem();
			if(funcionario != null) {
				
				boolean buttonConfirmarClick = showCadastroFuncionarioDialog(funcionario);
				if(buttonConfirmarClick) {
					funcionarioDAO.update(funcionario);
					carregarTableView();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Selecione um funcionario na tabela.");
				alert.show();
				
			}
		}
	  @FXML
	  void handleButtonRemover(ActionEvent event) {
			Funcionario funcionario = tableViewFuncionario.getSelectionModel().getSelectedItem();
			if(funcionario != null) {
				
				funcionarioDAO.delete(funcionario);
				carregarTableView();
				
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Selecione um funcionario na tabela.");
				alert.show();
			}
	  }
	
}
