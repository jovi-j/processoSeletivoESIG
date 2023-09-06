package br.com.esig.controller;
import br.com.esig.dao.CargoDAO;
import br.com.esig.model.Cargo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CargoMBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Cargo> cargos;
	

	public CargoMBean() {
		buscarTodosOsCargos();
	}
	
	public void buscarTodosOsCargos(){
		CargoDAO cDAO = new CargoDAO();
		this.cargos = cDAO.buscarTodosCargos();
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
	
	
}
