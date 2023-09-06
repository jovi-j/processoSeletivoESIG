package br.com.esig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.esig.model.Pessoa;
import br.com.esig.model.PessoaSalario;
import br.com.esig.util.EMUtil;

public class PessoaSalarioDAO {

	public void updateSalarios() {
		EntityManager em = EMUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			String hql = "SELECT p, SUM(cv.vencimento.valor) FROM Pessoa p "
					+ "JOIN CargoVencimento cv ON p.cargo = cv.cargo GROUP BY p";

			TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
			List<Object[]> resultList = query.getResultList();

			for (Object[] resultado : resultList) {
				Pessoa p = (Pessoa) resultado[0];
				Double salCalc = (Double) resultado[1];
				PessoaSalario existingSalario = getExistingPessoaSalario(p);

				if (existingSalario == null || !existingSalario.getSalario().equals(salCalc)) {
					PessoaSalario pessoaSalario = new PessoaSalario();
					pessoaSalario.setPessoa(p);
					pessoaSalario.setNome(p.getNome());
					pessoaSalario.setSalario(salCalc);
					em.merge(pessoaSalario);
				}
			}
			
			removeOldSalarios();
			
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	private PessoaSalario getExistingPessoaSalario(Pessoa pessoa) {
		EntityManager em = EMUtil.getEntityManager();
		String hql = "SELECT ps FROM PessoaSalario ps WHERE ps.pessoa = :pessoa";
		TypedQuery<PessoaSalario> query = em.createQuery(hql, PessoaSalario.class);
		query.setParameter("pessoa", pessoa);
		PessoaSalario ps = new PessoaSalario();
		try {
			ps = query.getSingleResult();
		} catch (Exception e) {
			ps = null;
		} finally {
			em.close();
		}
		return ps;
	}
	
	private void removeOldSalarios() {
		EntityManager em = EMUtil.getEntityManager();
		em.getTransaction().begin();
        String hql = "DELETE FROM PessoaSalario ps WHERE ps.pessoa NOT IN " +
                      "(SELECT p FROM Pessoa p JOIN CargoVencimento cv ON p.cargo = cv.cargo)";
        em.createQuery(hql).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

	public List<PessoaSalario> buscarTodasPessoasSalario() {
		EntityManager em = EMUtil.getEntityManager();
		TypedQuery<PessoaSalario> pessoaSalQuery = em.createQuery("from PessoaSalario ps", PessoaSalario.class);
		List<PessoaSalario> psList = pessoaSalQuery.getResultList();
		em.close();
		return psList;
	}
}
