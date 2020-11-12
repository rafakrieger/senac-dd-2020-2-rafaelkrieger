# Aplicação de controle de vacinas para covid-19

## Protótipo de telas (não funcional)

_**Autores: Rafael Krieger, Gustavo Martins, Altieste Schmidt**_

### Tela principal

![Tela principal](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/images/main.jpg)

- [X] Painel inicial com dashboard de resumos de relatórios

### Cadastro de Pessoas

![Cadastro de Pessoas](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/images/pessoas.jpg)

- [x] Campo "Instituição" só aparece caso o checkbox "Pesquisador" esteja selecionado

### Consulta de pessoas

![Consulta de Pessoas](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/images/consulta-pessoas.jpg)

- [x] Após pesquisar com filtros, será possível editar ou excluir o registro. Versão final terá botão para salvar em XLS.

### Cadastro de Vacinas

![Cadastro de Vacinas](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/images/vacinas.jpg)

- [x] Combobox via banco de dados para selecionar estágio e pesquisador
- [x] Combobox via array Java para selecionar país de origem

### Consulta de vacinas

![Consulta de Vacinas](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/images/consulta-vacinas.jpg)

- [x] Após pesquisar com filtros, será possível editar ou excluir o registro. Versão final terá botão para salvar em XLS.

### Cadastro de Aplicações

![Cadastro de Aplicações](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/images/aplicacoes.jpg)

- [x] Combobox via banco de dados para selecionar vacina e pessoa
- [x] Combobox via array Java para selecionar avaliação

### Consulta de aplicações

![Consulta de Aplicações](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/images/consulta-aplicacoes.jpg)

- [x] Após pesquisar com filtros, será possível editar ou excluir o registro. Versão final terá botão para salvar em XLS.

## Regras de Negócio

* Uma vacina pode ser aplicada em pessoas conforme a fase: 1 - Somente pesquisadores; 2 - Voluntários; 3 - Público em geral

* Cada pessoa pode tomar apenas uma vacina por ano

* Todas as pessoas devem possuir CPF válido com 11 caracteres  numéricos (OK)

* Todas as pessoas devem possuir nome e um sobrenome com pelo menos três caracteres (OK)

* Não podem ser cadastradas duas pessoas com o mesmo CPF 

## Banco de dados

    SET SQL_SAFE_UPDATES = 0;
    DROP DATABASE IF EXISTS DBVACINA;
    CREATE DATABASE DBVACINA;
    USE DBVACINA;

    CREATE TABLE PESSOA (
      IDPESSOA INT NOT NULL AUTO_INCREMENT
        , NOME VARCHAR (100) NOT NULL
        , DT_NASCIMENTO DATE
        , SEXO ENUM ('M', 'F')
        , CPF VARCHAR (11)
        , VOLUNTARIO BOOLEAN
        , PRIMARY KEY (IDPESSOA)
    );

    CREATE TABLE PESQUISADOR (
      IDPESQUISADOR INT NOT NULL AUTO_INCREMENT
        , NOME VARCHAR (100)
        , IDPESSOA INT
        , INSTITUICAO VARCHAR (256)
        , PRIMARY KEY (IDPESQUISADOR)
    );

    CREATE TABLE VACINA (
      IDVACINA INT NOT NULL AUTO_INCREMENT
        , IDPESQUISADOR INT NOT NULL
        , PAIS_ORIGEM VARCHAR(45)
        , ESTAGIO_PESQUISA ENUM ('1', '2', '3')
      , DT_INICIO DATE     
        , PRIMARY KEY (IDVACINA)
        , FOREIGN KEY (IDPESQUISADOR) REFERENCES PESQUISADOR (IDPESQUISADOR)
    );

    CREATE TABLE VACINACAO (
      IDVACINACAO INT NOT NULL AUTO_INCREMENT
        , IDVACINA INT NOT NULL
        , IDPESSOA INT NOT NULL
        , DT_VACINACAO DATE
        , AVALIACAO ENUM ('1', '2', '3', '4', '5')
        , PRIMARY KEY (IDVACINACAO)
        , FOREIGN KEY (IDVACINA) REFERENCES VACINA (IDVACINA)
        , FOREIGN KEY (IDPESSOA) REFERENCES PESSOA (IDPESSOA)
    );
