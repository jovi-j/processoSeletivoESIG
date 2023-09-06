package br.com.esig.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.esig.model.Cargo;

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
	
	
}
