package com.exemploprojeto.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.FuncionarioDAO;
import com.exemploprojeto.dao.ServicoDAO;
import com.exemploprojeto.model.Funcionario;
import com.exemploprojeto.model.Servico;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PaneMainController implements Initializable {
	
	   @FXML
	    private AnchorPane anchorPaneMain;
	
	  	@FXML
	    private ImageView imageViewLogo;
	  
	  	@FXML
	    private Menu menuCadastro;

	    @FXML
	    private MenuItem menuItemEquipamentos;
	    
	    @FXML
	    private MenuItem menuItemCliente;

	    @FXML
	    private MenuItem menuItemFuncionario;

	    @FXML
	    private MenuItem menuItemServico;
	    
	    @FXML
	    private Button buttonClientes;

	    @FXML
	    private Button buttonEquipamentos;

	    @FXML
	    private Button buttonFuncionarios;

	    @FXML
	    private Button buttonServicos;
	    
		@FXML
		private TabPane tabPaneMain;
		
		@FXML
	    private TableColumn<Servico, LocalDate> tableColumnDataAbPendente;

	    @FXML
	    private TableColumn<Servico, String> tableColumnDescricaoPendente;

	    @FXML
	    private TableColumn<Servico, Integer> tableColumnIdPendente;

	    @FXML
	    private TableView<Servico> tableViewPendente;
	    
	    private List<Servico> listServicos;
	    private ObservableList<Servico> observableServicos;
	    private Servico servico;
	    private ServicoDAO servicoDAO = new ServicoDAO();
	    
	    private Funcionario funcionario;
	    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	   
	    boolean animationPlayed = false;
	    
	    private String nameUser;
	 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
	}
	
	public void animationPlayTabPane() {
		
		if(!animationPlayed) {
		TranslateTransition trans = new TranslateTransition();
		trans.setNode(tabPaneMain);
		trans.setDuration(Duration.millis(500));
		trans.setByY(-200);
		trans.setOnFinished(event -> animationPlayed = true);
		trans.play();
		
		}
	}
	
	public void animationReverseTabPane() {
		
		if(animationPlayed) {
		TranslateTransition trans = new TranslateTransition();
		trans.setNode(tabPaneMain);
		trans.setDuration(Duration.millis(500));
		trans.setByY(200);
		trans.setOnFinished(event -> animationPlayed = false);
		trans.play();
		
		}
	}
	
	public void setLoginUser(String nameUser) {
		this.funcionario = funcionarioDAO.findByName(nameUser);
		this.nameUser = nameUser;
		carregarTableViewPendente();
		if(funcionario.isAdmin() == false) {
			buttonFuncionarios.setDisable(true);
			menuItemFuncionario.setDisable(true);
		} else {
			buttonFuncionarios.setDisable(false);
			menuItemFuncionario.setDisable(false);
		}
	}
	
	public String getLoginUser() {
		return nameUser;
	}
	
	public void carregarTableViewPendente() {
		
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");

	    tableColumnIdPendente.setCellValueFactory(new PropertyValueFactory<>("id"));
	    tableColumnDescricaoPendente.setCellValueFactory(new PropertyValueFactory<>("descricao"));
	    tableColumnDataAbPendente.setCellValueFactory(new PropertyValueFactory<>("datAb"));

	    listServicos = servicoDAO.findServicosPendentesFuncionarios(funcionario);
	    
	    observableServicos = FXCollections.observableArrayList(listServicos);
	    formatarColunaData(tableColumnDataAbPendente, formatter);
	    tableViewPendente.setItems(observableServicos);
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
	
	public void handleMenuItemServico() throws IOException {
		
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/exemploprojeto/view/CadastroServico.fxml"));
		anchorPaneMain.getChildren().setAll(ap);
		
	}
	
	public void handleMenuItemFuncionario() throws IOException{
		
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/exemploprojeto/view/CadastroFuncionario.fxml"));
		anchorPaneMain.getChildren().setAll(ap);
		
	}
	
	public void handleMenuItemCliente() throws IOException {
		
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/exemploprojeto/view/CadastroCliente.fxml"));
		anchorPaneMain.getChildren().setAll(ap);
		
	}
	
	public void handleMenuItemEquipamentos() throws IOException {
		
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/exemploprojeto/view/CadastroEquipamento.fxml"));
		anchorPaneMain.getChildren().setAll(ap);
		
	}
	
	public void handleButtonFuncionarios() throws IOException {
		
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/exemploprojeto/view/CadastroFuncionario.fxml"));
		anchorPaneMain.getChildren().setAll(ap);
		
	}
	
	public void handleButtonClientes() throws IOException {
		
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/exemploprojeto/view/CadastroCliente.fxml"));
		anchorPaneMain.getChildren().setAll(ap);
		
	}
	
	public void handleButtonServicos() throws IOException {
		
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/exemploprojeto/view/CadastroServico.fxml"));
		anchorPaneMain.getChildren().setAll(ap);
		
	}
	
	public void handleButtonEquipamentos() throws IOException {
		
		AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/exemploprojeto/view/CadastroEquipamento.fxml"));
		anchorPaneMain.getChildren().setAll(ap);
		
	}
	
	public void handleButtonHome() throws IOException {
		
		// Pega a tela atual
		Stage currentStage = (Stage) anchorPaneMain.getScene().getWindow();

		// Fecha a tela atual
		currentStage.close();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/exemploprojeto/view/PaneMain.fxml"));
		VBox vb = loader.load();
		
		
		// Cria uma nova cena
		Stage newStage = new Stage();
		Scene newScene = new Scene(vb);
		
		newScene.getStylesheets().add(getClass().getResource("/com/exemploprojeto/css/style.css").toExternalForm());

		// Define as propriedades da nova cena
		newStage.setResizable(false);
		newStage.setTitle("Projeto Integrador");
		newStage.setScene(newScene);
		newStage.setWidth(615);
		newStage.setHeight(439);
		
		
		PaneMainController controller = loader.getController();
		controller.setLoginUser(nameUser);

		// Exibe o novo Stage
		newStage.show();
		
		
	}
}
