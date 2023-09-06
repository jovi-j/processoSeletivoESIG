package br.com.esig.controller;
import br.com.esig.dao.PessoaDAO;
import br.com.esig.dao.PessoaSalarioDAO;
import br.com.esig.model.Pessoa;
import br.com.esig.model.PessoaSalario;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class PessoaMBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Pessoa> pessoas;
	private List<PessoaSalario> pessoasSalarios;
	private boolean calculandoSalarios = false;
	
	private Pessoa pessoa;

	// Propriedades para a busca
	private Long id;

	public PessoaMBean() {
		this.pessoa = new Pessoa();
		buscarTodasAsPessoas();
	}
	
	public String calcularSalarios() {
		calculandoSalarios = true;
		PessoaSalarioDAO psDAO = new PessoaSalarioDAO();
		if(pessoas.isEmpty()) 
			return null;
		
		psDAO.updateSalarios(pessoasSalarios, pessoas);
		calculandoSalarios = false;
		return "listaDePessoas";
		
	}

//	public String adicionarPessoa(){
//		em.getTransaction().begin();
//		this.pessoa = em.merge(this.pessoa);
//		em.persist(this.pessoa);
//		em.getTransaction().commit();
//		this.pessoa = new Pessoa();
//		buscarTodasAsPessoas();
//		return "listaDeTarefas";
//	}
//
//	public String modificarPessoa() {
//		String id = getParam("id");
//		this.pessoa = em.find(Pessoa.class, Long.parseLong(id));
//		return "index";
//	}

//	public void concluirTarefa() {
//		String pid = getParam("id");
//		this.pessoa = em.find(Pessoa.class, Long.parseLong(pid));
//		if(this.pessoa.getStatus()) {
//			enviarMensagem("A tarefa já foi concluida", "A tarefa já está concluida.", FacesMessage.SEVERITY_INFO);
//			this.pessoa = new Tarefa();
//			return;
//		}
//		this.pessoa.setStatus(true);
//		enviarMensagem("Tarefa concluida.", "Tarefa modificada com sucesso.", FacesMessage.SEVERITY_INFO);
//		adicionarTarefa();
//	}

//	public String removerPessoa(){
//		String pid = getParam("id");
//		this.pessoa = em.find(Pessoa.class, Long.parseLong(pid));
//		em.getTransaction().begin();
//		pessoa = em.merge(pessoa);
//		em.remove(pessoa);
//		em.getTransaction().commit();
//		this.pessoa = new Pessoa();
//		buscarTodasAsPessoas();
//		return "listaDeTarefas";
//	}

//	public String buscarTarefas(){
//		// Se for, realizar uma busca única por id
//		if(this.id != null){
//			Pessoa p = em.find(Pessoa.class, this.id);
//			if(p == null) {	
//				enviarMensagem("Nenhum Resultado", "Id não existe no banco de dados.", FacesMessage.SEVERITY_ERROR);
//				setPessoas(new ArrayList<Pessoa>());
//			}else {
//				setPessoas(new ArrayList<Pessoa>());
//				pessoas.add(p);
//			}
//		} else if(this.pessoas.isEmpty()) {	
//			enviarMensagem("Nenhum Resultado", "A busca não retornou resultados", FacesMessage.SEVERITY_ERROR);
//		} 
//		return "listaDeTarefas";
//	}

	public void buscarTodasAsPessoas(){
		PessoaDAO pDAO = new PessoaDAO();
		PessoaSalarioDAO psDAO = new PessoaSalarioDAO();
		this.pessoas = pDAO.buscarTodasPessoas();
		this.pessoasSalarios = psDAO.buscarTodasPessoasSalario();
	}
	
	// Função simples para pegar um único parâmetro vindo da requisição
	public String getParam(String param) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get(param);
	}
	
	public void enviarMensagem(String mensagemResumida, String mensagemInteira, FacesMessage.Severity severidade) {
		FacesMessage fm = new FacesMessage(severidade, mensagemResumida, mensagemInteira);
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage("messageOutput", fm);
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PessoaSalario> getPessoasSalarios() {
		return pessoasSalarios;
	}

	public void setPessoasSalarios(List<PessoaSalario> pessoasSalarios) {
		this.pessoasSalarios = pessoasSalarios;
	}

	public boolean isCalculandoSalarios() {
		return calculandoSalarios;
	}

}
