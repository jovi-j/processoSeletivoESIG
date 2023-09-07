package br.com.esig.controller;
import br.com.esig.dao.PessoaSalarioDAO;
import br.com.esig.model.PessoaSalario;
import br.com.esig.util.FacesUtil;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class SalariosMBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<PessoaSalario> pessoasSalarios;
	private List<PessoaSalario> pessoasSalariosFiltrados;
	

	// Propriedades para a busca
	private Long id;

	public SalariosMBean() {
		buscarTodosOsSalarios();
	}
	
	public String calcularSalarios() {
		PessoaSalarioDAO psDAO = new PessoaSalarioDAO();
		psDAO.updateSalarios();
		FacesUtil.enviarMensagem("Recalculo de Salários Completo", "O recálculo de salários foi feito com sucesso!", FacesMessage.SEVERITY_INFO, FacesContext.getCurrentInstance());
		buscarTodosOsSalarios();
		return null;
	}
	

	public void buscarTodosOsSalarios(){
		PessoaSalarioDAO psDAO = new PessoaSalarioDAO();
		this.pessoasSalarios = psDAO.buscarTodasPessoasSalario();
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

	public List<PessoaSalario> getPessoasSalariosFiltrados() {
		return pessoasSalariosFiltrados;
	}

	public void setPessoasSalariosFiltrados(List<PessoaSalario> pessoasSalariosFiltrados) {
		this.pessoasSalariosFiltrados = pessoasSalariosFiltrados;
	}

}
