package com.exemploprojeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class CategoriaEquipamento {
	
	@Id
	@Column(name = "nome_categoria")
	private String nomeCategoria;
	
	@Column(name = "descricao_categoria")
	private String descricao;

	public String getNome() {
		return nomeCategoria;
	}

	public void setNome(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getDesc() {
		return descricao;
	}

	public void setDesc(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return nomeCategoria;
	}
	

}
