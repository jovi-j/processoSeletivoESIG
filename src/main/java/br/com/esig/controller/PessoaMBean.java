package br.com.esig.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.esig.dao.CargoDAO;
import br.com.esig.dao.PessoaDAO;
import br.com.esig.model.Cargo;
import br.com.esig.model.Pessoa;
import br.com.esig.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PessoaMBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private List<Pessoa> pessoasFiltradas;
	private List<Cargo> cargos;
	
	public PessoaMBean() {
		CargoDAO cDAO = new CargoDAO();
		PessoaDAO pDAO = new PessoaDAO();
		this.pessoa = new Pessoa();
		this.setCargos(cDAO.buscarTodosCargos());
		this.setPessoas(pDAO.buscarTodasPessoas());
	}
	
	
	public void buscarTodasPessoas() {
		PessoaDAO pDAO = new PessoaDAO();
		this.pessoas = pDAO.buscarTodasPessoas();
	}
	
	public String salvarPessoa() {
	    PessoaDAO pDAO = new PessoaDAO();
	    pDAO.salvarPessoa(this.pessoa);
	    this.pessoa = new Pessoa();
	    FacesUtil.enviarMensagem("Sucesso", "Pessoa salva com sucesso!", FacesMessage.SEVERITY_INFO, FacesContext.getCurrentInstance());
	    return "listaDeSalarios";	
	}
	

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}


	public List<Pessoa> getPessoas() {
		return pessoas;
	}


	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}


	public List<Pessoa> getPessoasFiltradas() {
		return pessoasFiltradas;
	}


	public void setPessoasFiltradas(List<Pessoa> pessoasFiltradas) {
		this.pessoasFiltradas = pessoasFiltradas;
	}
	
	
}
