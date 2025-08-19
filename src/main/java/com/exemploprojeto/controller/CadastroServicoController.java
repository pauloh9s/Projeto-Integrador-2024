package com.exemploprojeto.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.OrcamentoDAO;
import com.exemploprojeto.dao.ServicoDAO;
import com.exemploprojeto.model.Orcamento;
import com.exemploprojeto.model.Servico;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CadastroServicoController implements Initializable {
	
		@FXML
	    private Button buttonAlterar;

	    @FXML
	    private Button buttonDetalhes;

	    @FXML
	    private Button buttonInserir;

	    @FXML
	    private Button buttonRemover;

	    @FXML
	    private TableColumn<Servico, LocalDate> tableColumnDataAbertura;

	    @FXML
	    private TableColumn<Servico, LocalDate> tableColumnDataPrevisao;

	    @FXML
	    private TableColumn<Servico, Integer> tableColumnId;

	    @FXML
	    private TableColumn<Servico, String> tableColumnStatus;

	    @FXML
	    private TableView<Servico> tableViewServico;

	    private List<Servico> listServicos;
	    private ObservableList<Servico> observableServicos;
	    private Servico servico;
	    private ServicoDAO servicoDAO = new ServicoDAO();
	    private Orcamento orcamento;
	    private OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
	    private List<Orcamento> orcamentos;
	    
	   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarTableView();
	}
	
	public void carregarTableView(){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
		
		tableColumnDataAbertura.setCellValueFactory(new PropertyValueFactory<>("datAb"));
		tableColumnDataPrevisao.setCellValueFactory(new PropertyValueFactory<>("datFe"));
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		formatarColunaData(tableColumnDataAbertura, formatter);
	    formatarColunaData(tableColumnDataPrevisao, formatter);
		
		listServicos = servicoDAO.selectAll();
		observableServicos = FXCollections.observableArrayList(listServicos);
		tableViewServico.setItems(observableServicos);

	}
	
	private void formatarColunaData(TableColumn<Servico, LocalDate> coluna, DateTimeFormatter formatter) {
	    coluna.setCellFactory(column -> new TableCell<Servico, LocalDate>() {
	        @Override
	        protected void updateItem(LocalDate item, boolean empty) {
	            super.updateItem(item, empty);
	            setText(empty || item == null ? null : item.format(formatter));
	        }
	    });
	}
	
	
	@FXML
	public boolean showCadastroServicoDialog(Servico servico) throws IOException{
		
	
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CadastroServicoDialogController.class.getResource("/com/exemploprojeto/view/CadastroServicoDialog.fxml"));
		AnchorPane ap = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		Scene scene = new Scene(ap);
		dialogStage.setResizable(false);
		dialogStage.setTitle("Cadastro/Alteração de Serviços");
		dialogStage.setScene(scene);
		
		CadastroServicoDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setServico(servico);
		
		dialogStage.showAndWait();
		
		return controller.isButtonConfirmarClick();
	}
	
	public void showServicoDetalhes(Servico servico) throws IOException{
		
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ServicoDetalhesController.class.getResource("/com/exemploprojeto/view/ServicoDetalhes.fxml"));
		AnchorPane ap = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		Scene scene = new Scene(ap);
		dialogStage.setTitle("Detalhes do Serviço");
		dialogStage.setResizable(false);
		dialogStage.setScene(scene);
		
		ServicoDetalhesController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setServico(servico);
		
		dialogStage.showAndWait();
		
	}
	
	
	public void handleButtonDetalhes() throws IOException {
		Servico servico = tableViewServico.getSelectionModel().getSelectedItem();
		if(servico != null) {
		 showServicoDetalhes(servico);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Selecione um serviço na tabela.");
			alert.show();
		}
	}
	
	@FXML
	 public void handleButtonInserir() throws IOException{
			Servico servico = new Servico();
			boolean buttonConfirmarClick = showCadastroServicoDialog(servico);
			if(buttonConfirmarClick) {
				if (servico.getId() != 0) {
					servicoDAO.update(servico);
					carregarTableView();
				} else {
					servicoDAO.insert(servico);
					carregarTableView();
		}}
	}
	 @FXML
	 void handleButtonAlterar() throws IOException{
		 	Servico servico = tableViewServico.getSelectionModel().getSelectedItem();
			if(servico != null) {
				
				boolean buttonConfirmarClick = showCadastroServicoDialog(servico);
				if(buttonConfirmarClick) {
					servicoDAO.update(servico);
					carregarTableView();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Selecione um serviço na tabela.");
				alert.show();
				
			}
		}
	  @FXML
	  void handleButtonRemover(ActionEvent event) {
			Servico servico = tableViewServico.getSelectionModel().getSelectedItem();
			if(servico != null) {
				
				orcamentos = orcamentoDAO.findByServico(servico);
				for (Orcamento orcamento : orcamentos) {
					if (orcamento != null) {
						orcamentoDAO.delete(orcamento);
					}
				}
				
				servicoDAO.delete(servico);
				carregarTableView();
				
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Selecione um serviço na tabela.");
				alert.show();
			}
	  }
	
	
	

}
