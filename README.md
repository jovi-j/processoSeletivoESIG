# Projeto para Processo Seletivo da ESIG - Cálculo de Salários
## Introdução
Pequeno projeto que visa criar uma tela para exibir salários de funcionarios de acordo com o seu respectivo cargos e os vencimentos que compôem o salário final de cada cargo, isto é, um sistema que seja capas de exibir o salário final de um colaborador cujo o cargo tenha uma ou mais "bonificações" embutidas no seu cargo.

No sistema há 5 entidades, **Pessoa**, **Cargo**, **Vencimento**, **CargoVencimento** e **PessoaSalario**, onde o **PessoaSalario** é o objeto que vai armazenar cada salário calculado em função de quantos *vencimentos* um *cargo* de uma *pessoa* possúi, sendo somados e guardados neste objeto **PessoaSalario**.

Na tela de listágem de salários, há um botão que recalcula todos os salários de todas as pessoas que possúem um cargo.


## Executando o projeto

Para iniciar o projeto, é necessário ter o JDK 8, Eclipse, e o Apache Tomcat 8.5 baixados no computador.

1. Importe o projeto no seu Eclipse
2. Crie um novo servidor Apache Tomcat 8.5
3. Adicione o projeto ao servidor clicando com o botão direito no servidor > Add or Remove... e adicione o projeto a lista de recursos
4. Inicie o projeto clicando no botão de iniciar projeto, na janela de servidores
5. Acesse o [localhost](http://localhost:8080/projeto/listaDeSalarios.xhtml)

## Tecnologias Usadas
- JDK 1.8 (Java 8)
- Glassfish Faces 2.2
- Hibernate 5.5.2-final
- PrimeFaces 8.0
- JUnit 4.11
- Apache Tomcat 8.5
