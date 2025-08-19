# 🖥️ Sistema de Gerenciamento de Manutenção de Computadores

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/JavaFX-000000?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white"/>
  <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white"/>
  <img src="https://img.shields.io/badge/SQLite-003B57?style=for-the-badge&logo=sqlite&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
  <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white"/>
</p>

Este projeto foi desenvolvido em **2024** no curso de **Desenvolvimento de Sistemas (SENAC Quixadá)**.  
O objetivo é criar um sistema **simples, mas completo**, para gerenciar uma loja de manutenção de computadores.

---

## 📑 Tópicos
- [🚀 Tecnologias Utilizadas](#-tecnologias-utilizadas)  
- [📋 Funcionalidades](#-funcionalidades)  
- [📑 Estrutura de Páginas](#-estrutura-de-páginas)  
- [🗂️ Fluxo de Navegação do Sistema](#️-fluxo-de-navegação-do-sistema)  
- [🎯 Objetivo](#-objetivo)  


---


## 🚀 Tecnologias Utilizadas
- **Java** (com Maven)  
- **Hibernate** (ORM para persistência de dados)  
- **JavaFX** (interface gráfica)  
- **PostgreSQL** (banco de dados principal)  
- **SQLite** (adaptação posterior para ambiente alternativo)

---

## 📋 Funcionalidades
O sistema oferece um gerenciamento completo de:  
- Funcionários  
- Clientes  
- Serviços  
- Equipamentos  

### 🔧 O que é possível fazer:
- **Cadastrar, editar e excluir serviços** vinculados a clientes e seus respectivos equipamentos.  
- Associar os **funcionários responsáveis** por cada manutenção.  
- Inserir **mais de um equipamento** por serviço.  
- Gerar **orçamentos estimados** pelos funcionários.  
- Registrar uma **descrição detalhada** do caso.  
- Acompanhar a **data de abertura** e a **data de fechamento** do serviço.  

---

## 📑 Estrutura de Páginas
Cada entidade do sistema (**Funcionários, Clientes, Serviços e Equipamentos**) possui uma **página dedicada**.  
Nessas páginas, há uma **tabela geral** para visualização e gerenciamento dos dados, permitindo:  
- Listar todos os registros cadastrados.  
- Adicionar novos registros.  
- Editar registros existentes.  
- Excluir registros.  

Essa estrutura garante uma navegação simples e intuitiva, centralizando o controle de cada módulo do sistema.

---

## 🗂️ Fluxo de Navegação do Sistema

```mermaid
flowchart TD
    A[🏠 Página Principal] --> B[👨 Funcionários]
    A --> C[👥 Clientes]
    A --> D[🛠️ Serviços]
    A --> E[💻 Equipamentos]

    %% Tabelas
    B --> B1[📋 Tabela de Funcionários]
    C --> C1[📋 Tabela de Clientes]
    D --> D1[📋 Tabela de Serviços]
    E --> E1[📋 Tabela de Equipamentos]

    %% CRUD Funcionários
    B1 --> B2[➕ Cadastrar Funcionário]
    B1 --> B3[✏️ Editar Funcionário]
    B1 --> B4[🗑️ Excluir Funcionário]

    %% CRUD Clientes
    C1 --> C2[➕ Cadastrar Cliente]
    C1 --> C3[✏️ Editar Cliente]
    C1 --> C4[🗑️ Excluir Cliente]

    %% CRUD Serviços
    D1 --> D2[➕ Cadastrar Serviço]
    D1 --> D3[✏️ Editar Serviço]
    D1 --> D4[🗑️ Excluir Serviço]

    %% CRUD Equipamentos
    E1 --> E2[➕ Cadastrar Equipamento]
    E1 --> E3[✏️ Editar Equipamento]
    E1 --> E4[🗑️ Excluir Equipamento]

    %% Fluxo Produto/Serviço -> Categoria
    E2 --> F[📂 Selecionar Categoria]
    F --> F1[➕ Cadastrar Categoria]
    F --> F2[✏️ Editar Categoria]
    F --> F3[🗑️ Excluir Categoria]

    %% Fluxo Serviço -> Produto
    D2 --> E[📂 Selecionar Equipamento]
```
---

## 🎯 Objetivo
Este projeto visa oferecer uma solução prática e educativa para consolidar os conhecimentos em **desenvolvimento de sistemas desktop**, utilizando boas práticas de programação e banco de dados.
