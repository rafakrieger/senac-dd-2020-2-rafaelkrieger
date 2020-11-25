# Aplicação de controle de vacinas para covid-19

<p align="center">
  <a href="#tela-principal">Tela principal</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#man_in_tuxedo-cadastro-de-pessoas">Cadastro de Pessoas</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#female_detective-consulta-de-pessoas">Consulta de pessoas</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#pill-cadastro-de-vacinas">Cadastro de Vacinas</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#mag_right-consulta-de-vacinas">Consulta de Vacinas</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="#syringe-cadastro-de-aplicações">Cadastro de Aplicações</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#mag_right-consulta-de-aplicações">Consulta de Aplicações</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#page_with_curl-regras-de-negócio">Regras de Negócio</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#construction_worker-como-rodar">Como Rodar</a>
</p>

> <strong>Autores:</strong> Rafael Krieger, Gustavo Martins, Altieste Schmidt

### Tela principal

![Tela principal](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/utils/images/principal.jpg)

- [X] Painel inicial com dashboard de resumos de relatórios

### :man_in_tuxedo: Cadastro de Pessoas

![Cadastro de Pessoas](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/utils/images/addpessoas.jpg)

- [x] Campo "Instituição" só aparece caso o checkbox "Pesquisador" esteja selecionado

### :female_detective: Consulta de Pessoas

![Consulta de Pessoas](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/utils/images/consulta-pessoas.jpg)

- [x] Após pesquisar com filtros, será possível editar ou excluir o registro. Versão final terá botão para salvar em XLS.

### :pill: Cadastro de Vacinas

![Cadastro de Vacinas](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/utils/images/addvacinas.jpg)

- [x] Combobox via banco de dados para selecionar estágio e pesquisador
- [x] Combobox via array Java para selecionar país de origem

### :mag_right: Consulta de Vacinas

![Consulta de Vacinas](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/utils/images/consulta-vacinas.jpg)

- [x] Após pesquisar com filtros, será possível editar ou excluir o registro. Versão final terá botão para salvar em XLS.

### :syringe: Cadastro de Aplicações

![Cadastro de Aplicações](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/utils/images/addvacinacao.jpg)

- [x] Combobox via banco de dados para selecionar vacina e pessoa
- [x] Combobox via array Java para selecionar avaliação

### :mag_right: Consulta de Aplicações

![Consulta de Aplicações](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/utils/images/consulta-aplicacoes.jpg)

- [x] Após pesquisar com filtros, será possível editar ou excluir o registro. Versão final terá botão para salvar em XLS.

## :page_with_curl: Regras de Negócio

- Uma vacina pode ser aplicada em pessoas conforme a fase:
1 - Somente pesquisadores;
2 - Voluntários;
3 - Público em geral.

- Cada pessoa pode tomar apenas uma vacina por ano

- Todas as pessoas devem possuir CPF válido com 11 caracteres  numéricos (OK)

- Todas as pessoas devem possuir nome e um sobrenome com pelo menos três caracteres (OK)

- Não podem ser cadastradas duas pessoas com o mesmo CPF

## :construction_worker: Como Rodar

#### :inbox_tray: Importando o Projeto

```bash
# Clone o Repositório
> https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger.git

# Em sua IDE converta o projeto para Maven
```

#### :file_cabinet: Banco de dados
> Em seu MySQL rode este script abaixo:
```bash
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
```
