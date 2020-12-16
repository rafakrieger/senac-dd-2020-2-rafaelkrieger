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

![Tela principal](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/utils/images/Principal.jpg)

- [X] Painel inicial com dashboard de resumos de relatórios

### :man_in_tuxedo: Cadastro de Pessoas

![Cadastro de Pessoas](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/utils/images/AddPessoa.jpg)

- [x] Ao entrar com CPF, pesquisa se já existe registro e permite atualizar cadastro
- [x] Campo "Instituição" só aparece caso o checkbox "Pesquisador" esteja selecionado

### :female_detective: Consulta de Pessoas

![Consulta de Pessoas](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/utils/images/BuscaPessoa.jpg)

### :pill: Cadastro de Vacinas

![Cadastro de Vacinas](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/utils/images/AddVacina.jpg)

- [x] Combo Box via banco de dados para selecionar estágio e pesquisador
- [x] Combo Box via array Java para selecionar país de origem

### :mag_right: Consulta de Vacinas

![Consulta de Vacinas](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/utils/images/BuscaVacina.jpg)


### :syringe: Cadastro de Aplicações

![Cadastro de Aplicações](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/utils/images/AddVacinacao.jpg)

- [x] Regra de negócio de aplicação da vacina conforme a fase é verificada na seleção do Combo Box
- [x] Combo Box via banco de dados para selecionar vacina e pessoa
- [x] Combo Box via array Java para selecionar avaliação

### :mag_right: Consulta de Aplicações

![Consulta de Aplicações](https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/src/br/com/senac/vacinas/utils/images/BuscaVacinacao.jpg)


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

# OU baixe e execute o arquivo JAR: 
> https://github.com/rafakrieger/senac-dd-2020-2-rafaelkrieger/blob/master/VACINAS_Altieste_Gustavo_Rafael.jar
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
    , CPF VARCHAR (11) NOT NULL
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

DELIMITER $$

CREATE TRIGGER TR_PESSOA BEFORE DELETE ON PESSOA FOR EACH ROW 
BEGIN 

	DECLARE vIDVACINACAO INT;
    
    SELECT IDVACINACAO INTO vIDVACINACAO FROM VACINACAO WHERE IDPESSOA = OLD.IDPESSOA;
    
    DELETE FROM VACINACAO WHERE IDVACINACAO = vIDVACINACAO;    

END $$

DELIMITER ;


INSERT INTO PESSOA VALUES (1, "DIEGO ARMANDO MARADONA", '1960-10-30', "M", "02134567890", 1);
INSERT INTO PESSOA VALUES (2, "ROMÁRIO DE SOUZA FARIA", '1966-01-29', "M", "03134567880", 0);
INSERT INTO PESSOA VALUES (3, "MARTA VIEIRA DA SILVA", '1986-02-19', "F", "00558234306", 1);
INSERT INTO PESSOA VALUES (4, "EDMUNDO ALVES DE SOUZA", '1971-04-02', "M", "55134767880", 0);
INSERT INTO PESSOA VALUES (5, "CRISTIANE DE SOUZA SILVA", '1985-05-15', "F", "99134767880", 1);
INSERT INTO PESSOA VALUES (6, "ANTÔNIO REIS JÚNIOR", '1975-01-30', "M", "19982000304", 1);
INSERT INTO PESSOA VALUES (7, "HANNIBAL LECTER", '1960-10-30', "M", "00100200304", 0);
INSERT INTO PESSOA VALUES (8, "IVO PITANGUY", '1960-10-30', "M", "00100200304", 0);

INSERT INTO PESQUISADOR VALUES (1, "ANTÔNIO REIS JÚNIOR", 6, "FIOCRUZ");
INSERT INTO PESQUISADOR VALUES (2, "HANNIBAL LECTER", 7, "HARVARD");
INSERT INTO PESQUISADOR VALUES (3, "IVO PITANGUY", 8, "USP");

INSERT INTO VACINA VALUES (1, 1, "BRASIL", 1, '2020-08-31');
INSERT INTO VACINA VALUES (2, 1, "FRANÇA", 2, '2020-09-15');
INSERT INTO VACINA VALUES (3, 2, "CHINA", 3, '2020-06-05');
INSERT INTO VACINA VALUES (4, 2, "RÚSSIA", 1, '2020-07-20');
INSERT INTO VACINA VALUES (5, 3, "EUA", 2, '2020-10-23');

INSERT INTO VACINACAO VALUES (1, 1, 6, '2020-10-12', 4);
INSERT INTO VACINACAO VALUES (2, 2, 1, '2020-10-10', 3);
INSERT INTO VACINACAO VALUES (3, 3, 2, '2020-11-19', 4);
INSERT INTO VACINACAO VALUES (4, 3, 4, '2020-09-02', 3);
INSERT INTO VACINACAO VALUES (5, 4, 7, '2020-08-18', 3);
INSERT INTO VACINACAO VALUES (6, 5, 5, '2020-12-01', 4);
INSERT INTO VACINACAO VALUES (7, 5, 3, '2020-11-02', 5);

```
