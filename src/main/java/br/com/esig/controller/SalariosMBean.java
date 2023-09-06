package br.com.esig.controller;
import br.com.esig.dao.PessoaDAO;
import br.com.esig.dao.PessoaSalarioDAO;
import br.com.esig.model.Pessoa;
import br.com.esig.model.PessoaSalario;
import br.com.esig.util.FacesUtil;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class SalariosMBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<PessoaSalario> pessoasSalarios;
	

	// Propriedades para a busca
	private Long id;

	public SalariosMBean() {
		buscarTodosOsSalarios();
	}
	
	public String calcularSalarios() {
		PessoaSalarioDAO psDAO = new PessoaSalarioDAO();
		PessoaDAO pDAO = new PessoaDAO();
		List<Pessoa> pessoas = pDAO.buscarTodasPessoas();
		if(pessoas.isEmpty()) {
			FacesUtil.enviarMensagem("Erro de Pessoas", "Não há pessoas cadastradas no sistema.", FacesMessage.SEVERITY_ERROR, FacesContext.getCurrentInstance());
			return null;
		}
		
		psDAO.updateSalarios(pessoasSalarios, pessoas);
		FacesUtil.enviarMensagem("Recalculo de Salários Completo", "O recálculo de salários foi feito com sucesso!", FacesMessage.SEVERITY_INFO, FacesContext.getCurrentInstance());
		return null;
		
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

	public void buscarTodosOsSalarios(){
		PessoaSalarioDAO psDAO = new PessoaSalarioDAO();
		this.pessoasSalarios = psDAO.buscarTodasPessoasSalario();
	}
	
	// Função simples para pegar um único parâmetro vindo da requisição
	


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

}
