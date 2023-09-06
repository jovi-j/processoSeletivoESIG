package br.com.esig.model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cargo_vencimento")
@SessionScoped
@ManagedBean
public class CargoVencimento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;
	
	@OneToOne
	@JoinColumn(name = "vencimento_id")
	private Vencimento vencimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Vencimento getVencimento() {
		return vencimento;
	}

	public void setVencimento(Vencimento vencimento) {
		this.vencimento = vencimento;
	}
	
	
}
