package com.exemploprojeto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.CategoriaEquipamentoDAO;
import com.exemploprojeto.dao.ClienteDAO;
import com.exemploprojeto.dao.EquipamentoDAO;
import com.exemploprojeto.model.CategoriaEquipamento;
import com.exemploprojeto.model.Cliente;
import com.exemploprojeto.model.Equipamento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CadastroEquipamentoDialogController implements Initializable {
	
	@FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonCategoria;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private ComboBox<CategoriaEquipamento> comboBoxCategoria;

    @FXML
    private ComboBox<Cliente> comboBoxCliente;

    @FXML
    private Label labelCategoria;

    @FXML
    private Label labelCliente;

    @FXML
    private Label labelModelo;

    @FXML
    private Label labelTelefone;

    @FXML
    private TextField textFieldModelo;

    @FXML
    private TextField textFieldMarca;
    
    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableClientes;
    private Cliente cliente;
    private ClienteDAO clienteDAO = new ClienteDAO();
    
    private Stage dialogStage = new Stage();
    private boolean buttonConfirmarClick = false;
    
    private Equipamento equipamento;
    private EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
    
    private List<CategoriaEquipamento> listCategorias;
    private ObservableList<CategoriaEquipamento> observableCategorias;
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

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
		this.textFieldMarca.setText(equipamento.getMarca());
		this.textFieldModelo.setText(equipamento.getModelo());
		this.comboBoxCliente.setValue(equipamento.getCliente());
		this.comboBoxCategoria.setValue(equipamento.getCategoriaEquipamento());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarComboBoxCliente();
		carregarComboBoxCategoria();
	}
	
	 @FXML
	    void handleButtonConfirmar(ActionEvent event) {
		 
		 	equipamento.setModelo(textFieldModelo.getText());
		 	equipamento.setMarca(textFieldMarca.getText());
		 	equipamento.setCliente(comboBoxCliente.getValue());
		 	equipamento.setCategoriaEquipamento(comboBoxCategoria.getValue());
		 	
		 	buttonConfirmarClick = true;
		 	dialogStage.close();
	    }
	 
	 @FXML
	    void handleButtonCancelar(ActionEvent event) {
		 	dialogStage.close();

	    }
	 
	 public void carregarComboBoxCliente() {
			listClientes = clienteDAO.selectAll();
			observableClientes = FXCollections.observableArrayList(listClientes);
			comboBoxCliente.setItems(observableClientes);
		}
	 
	 public void carregarComboBoxCategoria() {
			listCategorias = categoriaEquipamentoDAO.selectAll();
			observableCategorias = FXCollections.observableArrayList(listCategorias);
			comboBoxCategoria.setItems(observableCategorias);
		}
	 
	 @FXML
		public void buttonCadastroCategoria() throws IOException{
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CadastroCategoriaEquipamentoController.class.getResource("/com/exemploprojeto/view/CadastroCategoriaEquipamento.fxml"));
			AnchorPane ap = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			Scene scene = new Scene(ap);
			dialogStage.setTitle("Cadastro de Categoria");
			dialogStage.setResizable(false);
			dialogStage.setScene(scene);
			
			CadastroCategoriaEquipamentoController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCategoriaEquipamento(new CategoriaEquipamento());
			
			dialogStage.showAndWait();
			
			if(controller.isButtonConfirmarClick()) {
				CategoriaEquipamento categoriaEquipamento= controller.getCategoriaEquipamento();
				categoriaEquipamentoDAO.insert(categoriaEquipamento);
				carregarComboBoxCategoria();
			}
		}

}
