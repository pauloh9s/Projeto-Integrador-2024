package com.exemploprojeto.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_servico")
	private long id;
	
	private String status;
	private String descricao;
	
	@Column(name = "data_abertura")
	private LocalDate datAb;
	
	@Column(name = "data_fechamento")
	private LocalDate datFe;
	
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
        name = "servico_equipamento", 
        joinColumns = @JoinColumn(name = "id_servico"),
        inverseJoinColumns = @JoinColumn(name = "id_equipamento"))
    private List<Equipamento> equipamentos;
	
	@ManyToOne
	@JoinColumn(name = "nome_categoria")
	private CategoriaEquipamento categoriaEquipamento;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDatAb() {
		return datAb;
	}

	public void setDatAb(LocalDate localDate) {
		this.datAb = localDate;
	}


	public LocalDate getDatFe() {
		return datFe;
	}

	public void setDatFe(LocalDate datFe) {
		this.datFe = datFe;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Equipamento> getEquipamento() {
		return equipamentos;
	}

	public void setEquipamento(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public CategoriaEquipamento getCategoriaEquipamento() {
		return categoriaEquipamento;
	}

	public void setCategoriaEquipamento(CategoriaEquipamento categoriaEquipamento) {
		this.categoriaEquipamento = categoriaEquipamento;
	}
	
 }
