package com.exemploprojeto.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.exemploprojeto.dao.CategoriaEquipamentoDAO;
import com.exemploprojeto.dao.ClienteDAO;
import com.exemploprojeto.dao.FuncionarioDAO;
import com.exemploprojeto.dao.OrcamentoDAO;
import com.exemploprojeto.dao.ServicoDAO;
import com.exemploprojeto.model.CategoriaEquipamento;
import com.exemploprojeto.model.Cliente;
import com.exemploprojeto.model.Equipamento;
import com.exemploprojeto.model.Funcionario;
import com.exemploprojeto.model.Orcamento;
import com.exemploprojeto.model.Servico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CadastroServicoDialogController implements Initializable {
	
		@FXML
	    private Button buttonCancelar;

	    @FXML
	    private Button buttonConfirmar;
	    
	    @FXML
	    private Button buttonDescricao;

	    @FXML
	    private Button buttonEquipamento;
	    
	    @FXML
	    private Button buttonOrcamento;


	    @FXML
	    private CheckBox checkBoxFinalizado;

	    @FXML
	    private ComboBox<Cliente> comboBoxCliente;

	    @FXML
	    private ComboBox<Funcionario> comboBoxFuncionario;

	    @FXML
	    private DatePicker datePickerPrevisao;
	    
	    @FXML
	    private DatePicker datePickerAbertura;
	    
	    private Stage dialogStage = new Stage();
	    private boolean buttonConfirmarClick = false;
	    private Servico servico;
	    private ServicoDAO servicoDAO = new ServicoDAO();
	    
	    private List<CategoriaEquipamento> listCategorias;
	    private ObservableList<CategoriaEquipamento> observableCategorias;
	    private List<Funcionario> listFuncionarios;
	    private ObservableList<Funcionario> observableFuncionarios;
	    private List<Cliente> listClientes;
	    private ObservableList<Cliente> observableClientes;
	    private Funcionario funcionario;
	    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	    private Cliente cliente;
	    private ClienteDAO clienteDAO = new ClienteDAO();
	    private CategoriaEquipamento categoriaEquipamento;
	    private CategoriaEquipamentoDAO categoriaEquipamentoDAO = new CategoriaEquipamentoDAO();
	    
	    private Orcamento orcamento;
	    private OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
	    private List<Orcamento> orcamentos;
	    
	    private ObservableList<Equipamento> equipamentosSelecionados = FXCollections.observableArrayList();
	    

	    public Stage getDialogStage() {
			return dialogStage;
		}

		public void setDialogStage(Stage dialogStage) {
			this.dialogStage = dialogStage;
			
			dialogStage.setOnCloseRequest(event -> {
		        if (!buttonConfirmarClick) {  
		            servico = null; 
		        }
		    });
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
			this.datePickerAbertura.setValue(servico.getDatAb());
			this.datePickerPrevisao.setValue(servico.getDatFe());
			this.comboBoxCliente.setValue(servico.getCliente());
			this.comboBoxFuncionario.setValue(servico.getFuncionario());
		
			if (servico.getEquipamento() != null && !servico.getEquipamento().isEmpty()) {
			    this.equipamentosSelecionados.addAll(servico.getEquipamento());
			}
			
			if(this.servico.getStatus() != null && this.servico.getStatus().equals("Finalizado")) {
				this.checkBoxFinalizado.setSelected(true);
				this.buttonOrcamento.setDisable(false);
				this.datePickerPrevisao.setDisable(false);
			} else {
				this.checkBoxFinalizado.setSelected(false);
				this.buttonOrcamento.setDisable(true);
				this.datePickerPrevisao.setDisable(true);
			}
			
		}
		public ServicoDAO getServicoDAO() {
			return servicoDAO;
		}

		public void setServicoDAO(ServicoDAO servicoDAO) {
			this.servicoDAO = servicoDAO;
		}

		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarComboBoxFuncionario();
		carregarComboBoxCliente();
		
		checkBoxFinalizado.selectedProperty().addListener((observable, oldValue, newValue) -> {
		    if (newValue) {
		        this.buttonOrcamento.setDisable(false);
		        this.datePickerPrevisao.setDisable(false);
		    } else {
		        this.buttonOrcamento.setDisable(true);
		        this.datePickerPrevisao.setDisable(true);
		    }
		});
	}
	
	public void carregarComboBoxFuncionario() {
		listFuncionarios = funcionarioDAO.selectAll();
		observableFuncionarios = FXCollections.observableArrayList(listFuncionarios);
		comboBoxFuncionario.setItems(observableFuncionarios);
	}
	public void carregarComboBoxCliente() {
		
		listClientes = clienteDAO.selectAll();
		observableClientes = FXCollections.observableArrayList(listClientes);
		comboBoxCliente.setItems(observableClientes);
	}
	 
	   @FXML
		public String showDialogDescricao(Servico servico) throws IOException{
			
		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ServicoDescricaoController.class.getResource("/com/exemploprojeto/view/ServicoDescricao.fxml"));
			AnchorPane ap = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			Scene scene = new Scene(ap);
			dialogStage.setResizable(false);
			dialogStage.setScene(scene);
			
			ServicoDescricaoController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setServico(servico);
			
			dialogStage.showAndWait();
			
			return controller.getDescricao();
		}
	   
	   @FXML
	   public void handleButtonDescricao() throws IOException{
		   String descricao = showDialogDescricao(servico);
			if(descricao != null && !descricao.isEmpty()) {
				servico.setDescricao(descricao);
			}	
	   }
	   
	   @FXML
	   public boolean showDialogOrcamento(Orcamento orcamento) throws IOException {

		   FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(ServicoOrcamentoController.class.getResource("/com/exemploprojeto/view/ServicoOrcamento.fxml"));
		    AnchorPane ap = (AnchorPane) loader.load();

		    Stage dialogStage = new Stage();
		    Scene scene = new Scene(ap);
		    dialogStage.setTitle("Inserção de Orcamento");
		    dialogStage.setResizable(false);
		    dialogStage.setScene(scene);

		    ServicoOrcamentoController controller = loader.getController();
		    controller.setDialogStage(dialogStage);
		    controller.setServico(this.servico);

		    // Passa o orçamento ao controller
		    controller.setOrcamento(orcamento);

		    dialogStage.showAndWait();

		    return controller.isButtonConfirmarClick();    
		}
	   
	   public void handleButtonOrcamento() throws IOException {
		    Orcamento orcamento = null;

		    // Procura o orcamento certo que esta atrelado com o serviço, caso esse serviço já exista
		    if (this.servico.getId() != 0) {
		        List<Orcamento> orcamentos = orcamentoDAO.findByServico(this.servico);
		        if (!orcamentos.isEmpty()) {
		            orcamento = orcamentos.get(0);  // <- Pega o primeiro orcamento da lista que está atrelado com o serviço
		        }
		    }

		    // Se não tiver nenhum orcamento, cria um novo
		    if (orcamento == null) {
		        orcamento = new Orcamento();
		    }

		    boolean buttonConfirmarClick = showDialogOrcamento(orcamento);

		    if (buttonConfirmarClick) {
		        // Inserir o serviço se ainda não estiver salvo no banco de dados
		        if (servico.getId() == 0) {
		            servicoDAO.insert(servico);
		        }

		        // Seta o serviço no orcamento
		        orcamento.setServico(servico);

		        // Verificar se o orçamento já existe no banco
		        if (orcamento.getId() != 0) {
		            orcamentoDAO.update(orcamento);
		            System.out.println("Orçamento atualizado"); // <- Se estiver, apenas atualiza o orcamento
		        } else {
		            orcamentoDAO.insert(orcamento);
		            System.out.println("Orçamento inserido"); // <- se não, vai inserir um novo orcamento daquele serviço
		        }
		    }
		}
	   
	   @FXML
		public void handleButtonDialogEquipamentos() throws IOException{
			
		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CadastroServicoSelectEquipamentoController.class.getResource("/com/exemploprojeto/view/CadastroServicoSelectEquipamento.fxml"));
			AnchorPane ap = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			Scene scene = new Scene(ap);
			dialogStage.setTitle("Seleção de Equipamentos");
			dialogStage.setResizable(false);
			dialogStage.setScene(scene);
			
			CadastroServicoSelectEquipamentoController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
			 if (controller.isButtonConfirmarClick()) {
	                ObservableList<Equipamento> selecionados = controller.getEquipamentosSelecionados();
	                equipamentosSelecionados.setAll(selecionados);
	            }
	   }
	   
	   @FXML
	   public boolean checkedBoxFinalizado() {
		   
		   if(checkBoxFinalizado.isSelected()) {
			   return true;
		   } else {
			   return false;
		   }
	   }
	   
	   @FXML
	    void handleButtonConfirmar(ActionEvent event) {
		   
		   if(EntradaDeDados()) {
				
				servico.setDatAb(datePickerAbertura.getValue());
				servico.setDatFe(datePickerPrevisao.getValue());
				servico.setCliente(comboBoxCliente.getSelectionModel().getSelectedItem());
				servico.setFuncionario(comboBoxFuncionario.getSelectionModel().getSelectedItem());
				
				if(checkedBoxFinalizado()) {
					servico.setStatus("Finalizado");
					
				} else {
					servico.setStatus("Pendente");
				}
		         
				servico.setEquipamento(equipamentosSelecionados);
		       
				buttonConfirmarClick = true;
				dialogStage.close();
		 	}

	    }
	   
	   @FXML
	    void handleButtonCancelar(ActionEvent event) {
		   dialogStage.close(); 
	    }

	   private boolean EntradaDeDados(){
			
			String errorMessage = "";
			
			if(datePickerAbertura.getValue() == null) {
				errorMessage += "Data de Abertura invalida \n";
			} 
			if(comboBoxCliente.getSelectionModel().getSelectedItem() == null){
				errorMessage += "Cliente invalido \n";
			}
			
			if(errorMessage.length() == 0) {
				return true;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Erro ao cadastrar");
				alert.setHeaderText("Campo inválido...");
				alert.setContentText(errorMessage);
				alert.show();
				return false;
			}
		}
}
