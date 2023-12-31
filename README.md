# Projeto para Processo Seletivo da ESIG - Cálculo de Salários
## Introdução
Pequeno projeto que visa criar uma tela para exibir salários de funcionarios de acordo com o seu respectivo cargos e os vencimentos que compôem o salário final de cada cargo, isto é, um sistema que seja capas de exibir o salário final de um colaborador cujo o cargo tenha uma ou mais "bonificações" embutidas no seu cargo.

No sistema há 5 entidades, **Pessoa**, **Cargo**, **Vencimento**, **CargoVencimento** e **PessoaSalario**, onde o **PessoaSalario** é o objeto que vai armazenar cada salário calculado em função de quantos *vencimentos* um *cargo* de uma *pessoa* possúi, sendo somados e guardados neste objeto **PessoaSalario**.

Na tela de listágem de salários, há um botão que recalcula todos os salários de todas as pessoas que possúem um cargo.

## Do processo seletivo
Dos pontos abordados no processo seletivo, os seguintes foram concluídos:
* Listagem de Salários
* Cálculo de Salários
* Criação, Listagem e Remoção de Pessoa


## Executando o projeto

Para iniciar o projeto, é necessário ter o JDK 8, Eclipse, Apache Tomcat 8.5 e um banco de dados Postgresql baixados e configurados no computador.

1. Importe o projeto no seu Eclipse
2. Crie um novo servidor Apache Tomcat 8.5
3. Adicione o projeto ao servidor clicando com o botão direito no servidor > Add or Remove... e adicione o projeto a lista de recursos
4. Inicie o projeto clicando no botão de iniciar projeto, na janela de servidores
5. Acesse o [localhost](http://localhost:8080/projeto/index.xhtml)

### Banco de dados
É necessário ter um banco Postgresql configurado com os seguintes parâmetros:
* login: postgres
* senha: postgres
* porta: 5432
* database: projetoEsig

## Tecnologias Usadas
- JDK 1.8 (Java 8)
- Glassfish Faces 2.2
- Hibernate 5.5.2-final
- PrimeFaces 8.0
- JUnit 4.11
- Apache Tomcat 8.5
- Postgresql
