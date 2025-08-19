package com.exemploprojeto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.ClienteDAO;
import com.exemploprojeto.model.Cliente;

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

public class CadastroClienteController implements Initializable{
	
	   @FXML
	    private Button buttonAlterar;

	    @FXML
	    private Button buttonInserir;

	    @FXML
	    private Button buttonRemover;

	    @FXML
	    private Label labelEmailCliente;

	    @FXML
	    private Label labelEnderecoCliente;

	    @FXML
	    private Label labelIdCliente;

	    @FXML
	    private Label labelNomeCliente;

	    @FXML
	    private Label labelTelefoneCliente;

	    @FXML
	    private TableColumn<Cliente, Integer> tableColumnId;

	    @FXML
	    private TableColumn<Cliente, String> tableColumnNome;

	    @FXML
	    private TableView<Cliente> tableViewCliente;
	    
	    private Cliente cliente;
	    private ClienteDAO clienteDAO = new ClienteDAO();
	    private List<Cliente> listClientes;
	    private ObservableList<Cliente> observableClientes;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarTableView();
		tableViewCliente.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue ) -> selectTableView(newValue));
	}
	
	public void carregarTableView(){
		
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		listClientes = clienteDAO.selectAll();
		
		observableClientes = FXCollections.observableArrayList(listClientes);
		tableViewCliente.setItems(observableClientes);

	}
	
	public void selectTableView(Cliente cliente){
	
		 if (cliente != null) {
	            labelIdCliente.setText(String.valueOf(cliente.getId()));
	            labelNomeCliente.setText(cliente.getNome());
	            labelTelefoneCliente.setText(cliente.getTelefone());
	            labelEmailCliente.setText(cliente.getEmail());
	            labelEnderecoCliente.setText(cliente.getEndereco());
	        } else {
	            labelIdCliente.setText("");
	            labelNomeCliente.setText("");
	            labelTelefoneCliente.setText("");
	            labelEmailCliente.setText("");
	            labelEnderecoCliente.setText("");
	        }
	}
	@FXML
	public boolean showCadastroClienteDialog(Cliente cliente) throws IOException{
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CadastroClienteDialogController.class.getResource("/com/exemploprojeto/view/CadastroClienteDialog.fxml"));
		AnchorPane ap = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		Scene scene = new Scene(ap);
		dialogStage.setTitle("Cadastro/Alteração de Cliente");
		dialogStage.setScene(scene);
		dialogStage.setResizable(false);
		
		CadastroClienteDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setCliente(cliente);
		
		dialogStage.showAndWait();
		
		return controller.isButtonConfirmarClick();
	}
		@FXML
	    void handleButtonInserir() throws IOException {
			
			Cliente cliente = new Cliente();
			boolean buttonConfirmarClick = showCadastroClienteDialog(cliente);
			if(buttonConfirmarClick) {
				clienteDAO.insert(cliente);
				carregarTableView();
	    }
	}
		@FXML
	    void handleButtonAlterar() throws IOException {
		 	Cliente cliente = tableViewCliente.getSelectionModel().getSelectedItem();
			if(cliente != null) {
				boolean buttonConfirmarClick = showCadastroClienteDialog(cliente);
				if(buttonConfirmarClick) {
					clienteDAO.update(cliente);
					carregarTableView();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Selecione um cliente na tabela.");
				alert.show();	
			}
	    }
	    @FXML
	    void handleButtonRemover(ActionEvent event) {
	    	Cliente cliente = tableViewCliente.getSelectionModel().getSelectedItem();
			if(cliente != null) {
				
				clienteDAO.delete(cliente);
				carregarTableView();
				
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Selecione um cliente na tabela.");
				alert.show();
			}
	    }

}
