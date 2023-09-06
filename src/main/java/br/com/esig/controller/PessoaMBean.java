package br.com.esig.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.esig.dao.CargoDAO;
import br.com.esig.dao.PessoaDAO;
import br.com.esig.model.Cargo;
import br.com.esig.model.Pessoa;
import br.com.esig.util.FacesUtil;

@ManagedBean
@SessionScoped
public class PessoaMBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private List<Cargo> cargos;
	
	public PessoaMBean() {
		this.pessoa = new Pessoa();
		CargoDAO cDAO = new CargoDAO();
		this.setCargos(cDAO.buscarTodosCargos());
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
	
	
}
