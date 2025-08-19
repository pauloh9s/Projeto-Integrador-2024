package com.exemploprojeto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.EquipamentoDAO;
import com.exemploprojeto.model.Equipamento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CadastroServicoSelectEquipamentoController implements Initializable{

	  	@FXML
	    private Button buttonCadastrar;

	    @FXML
	    private Button buttonCancelar;

	    @FXML
	    private Button buttonConfirmar;
	    
	    @FXML
	    private TableView<Equipamento> tableViewEquipamento;

	    @FXML
	    private TableColumn<Equipamento, String> tableColumnCliente;

	    @FXML
	    private TableColumn<Equipamento, String> tableColumnCategoria;

	    @FXML
	    private TableColumn<Equipamento, String> tableColumnEquipamento;
	    
	    @FXML
	    private TableColumn<Equipamento, Boolean> tableColumnSelecionar;

	    
	    private List<Equipamento> listEquipamentos;
	    private ObservableList<Equipamento> observableEquipamentos;
	    private EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
	    private Equipamento equipamento;
	    private Stage dialogStage = new Stage();
	    private boolean buttonConfirmarClick = false;
	    
	    private ObservableList<Equipamento> equipamentosSelecionados = FXCollections.observableArrayList();
	    
	public Equipamento getEquipamento() {
			return equipamento;
		}

		public void setEquipamento(Equipamento equipamento) {
			this.equipamento = equipamento;
		
		}

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
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarTableView();
		tableViewEquipamento.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
		configurarColunaSelecionar();
	}
	
	public void carregarTableView(){
		
		tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		tableColumnEquipamento.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		tableColumnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaEquipamento"));
		
		listEquipamentos = equipamentoDAO.selectAll();
		
		for (Equipamento equipamento : listEquipamentos) {
	        if (equipamentosSelecionados.contains(equipamento)) {
	            equipamento.setSelected(true);
	        }
	    }
		
		observableEquipamentos = FXCollections.observableArrayList(listEquipamentos);
		tableViewEquipamento.setItems(observableEquipamentos);
		
		configurarColunaSelecionar();
	}
	
	@FXML
	public void buttonCadastroEquipamento() throws IOException{
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CadastroEquipamentoDialogController.class.getResource("/com/exemploprojeto/view/CadastroEquipamentoDialog.fxml"));
		AnchorPane ap = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		Scene scene = new Scene(ap);
		dialogStage.setTitle("Cadastro/Alteração de Equipamento");
		dialogStage.setResizable(false);
		dialogStage.setScene(scene);
		
		CadastroEquipamentoDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setEquipamento(new Equipamento());
		
		dialogStage.showAndWait();
		
		if(controller.isButtonConfirmarClick()) {
			Equipamento equipamento = controller.getEquipamento();
			equipamentoDAO.insert(equipamento);
			carregarTableView();
		}
		
	}
	 @FXML
	    void handleButtonConfirmar(ActionEvent event) {
		
		 equipamentosSelecionados.clear();
		 for (Equipamento equipamento : tableViewEquipamento.getItems()) {
		        if (equipamento.getSelected() != null && equipamento.getSelected()) {
		            if (!equipamentosSelecionados.contains(equipamento)) {
		                equipamentosSelecionados.add(equipamento);  
		            }
		        }
		    }
		 
		 buttonConfirmarClick = true;
		 dialogStage.close();

	    }
	 @FXML
	    void handleButtonCancelar(ActionEvent event) {
		 dialogStage.close();
		 
	    }
	 
	 	public ObservableList<Equipamento> getEquipamentosSelecionados() {
		    return equipamentosSelecionados;
		}
	 	
	 	 private void configurarColunaSelecionar() {
	 	    tableColumnSelecionar.setCellFactory(column -> new TableCell<Equipamento, Boolean>() {
	 	        private final CheckBox checkBox = new CheckBox();

	 	        @Override
	 	        protected void updateItem(Boolean item, boolean empty) {
	 	            super.updateItem(item, empty);
	 	            if (empty) {
	 	                setGraphic(null);
	 	            } else {
	 	                Equipamento equipamento = getTableRow().getItem();
	 	                if (equipamento != null) {
	 	                    checkBox.setSelected(equipamento.getSelected() != null && equipamento.getSelected());
	 	                    checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
	 	                        equipamento.setSelected(isNowSelected);
	 	                    });
	 	                }
	 	                setGraphic(checkBox);
	 	            }
	 	        }
	 	    });
	 	}
	 	 
	 	 

}
