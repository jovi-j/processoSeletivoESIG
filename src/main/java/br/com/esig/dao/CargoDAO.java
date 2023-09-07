package br.com.esig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.esig.model.Cargo;
import br.com.esig.util.EMUtil;

public class CargoDAO {
	
	
	public Double getSalarioByCargo(Cargo cargo) {
		EntityManager em = EMUtil.getEntityManager();
		Double salario = null;
		try {
			Query salQuery = em.createNativeQuery("select SUM(v.valor) "
					+ "FROM vencimento v "
					+ "JOIN cargo_vencimento cv ON "
					+ "cv.vencimento_id = v.id "
					+ "JOIN cargo c ON c.id = cv.cargo_id "
					+ "WHERE c.id = " + cargo.getId());
			salario = (Double) salQuery.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return salario;
	}

	public List<Cargo> buscarTodosCargos() {
		EntityManager em = EMUtil.getEntityManager();
		List<Cargo> cargos = null;
		try {
			TypedQuery<Cargo> cQuery = em.createQuery("from Cargo c", Cargo.class);
			cargos = cQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return cargos;
	}

	public Cargo findById(Long cargoId) {
		EntityManager em = EMUtil.getEntityManager();
		TypedQuery<Cargo> cQuery = em.createQuery("select c from Cargo c where c.id = :id", Cargo.class);
		cQuery.setParameter("id", cargoId);
		Cargo cargo = cQuery.getSingleResult();
		em.close();
		return cargo;
	}
	
	
}
