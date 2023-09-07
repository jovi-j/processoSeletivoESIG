package br.com.esig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.esig.model.Pessoa;
import br.com.esig.model.PessoaSalario;
import br.com.esig.util.EMUtil;

public class PessoaDAO {

	public List<Pessoa> buscarTodasPessoas() {
		EntityManager em = EMUtil.getEntityManager();
		List<Pessoa> pessoas = null;
		try {
			TypedQuery<Pessoa> cQuery = em.createQuery("FROM Pessoa p LEFT JOIN FETCH p.cargo", Pessoa.class);
			pessoas = cQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return pessoas;
	}

	public void salvarPessoa(Pessoa pessoa) {
		EntityManager em = EMUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			if ((pessoa.getCargo() == null || pessoa.getCargo().getId() == null) && pessoa.getCargoId() != null) {
				CargoDAO cDAO = new CargoDAO();
				pessoa.setCargo(cDAO.findById(pessoa.getCargoId()));
			}
			if (pessoa.getId() == null) {
				em.persist(pessoa);
			}
			pessoa = em.merge(pessoa);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void editarPessoa(Pessoa pessoa) {
		EntityManager em = EMUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(pessoa);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void deletarPessoa(Long pessoaId) {
		Pessoa p = findPessoaById(pessoaId);
		deletarPessoa(p);
	}

	private Pessoa findPessoaById(Long pessoaId) {
		EntityManager em = EMUtil.getEntityManager();
		try {
			TypedQuery<Pessoa> query = em.createQuery("SELECT p FROM Pessoa p WHERE p.id = :pessoaId", Pessoa.class);
			query.setParameter("pessoaId", pessoaId);
			return query.getSingleResult();
		} finally {
			em.close();
		}
	}
	
	
	public List<Pessoa> buscaPessoasPorNomeOuUsuario(String texto) {
		EntityManager em = EMUtil.getEntityManager();
		em.getTransaction().begin();
		List<Pessoa> resultados = null;
		try {
			String hql = "SELECT p FROM Pessoa p WHERE p.nome LIKE :texto OR p.usuario LIKE :texto";
			TypedQuery<Pessoa> query = em.createQuery(hql, Pessoa.class);
			query.setParameter("texto", "%" + texto + "%");

			// Execute the query to get the search results
			resultados = query.getResultList();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return resultados;
	}



	public void deletarPessoa(Pessoa pessoa) {
		 EntityManager em = EMUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            PessoaSalarioDAO pessoaSalarioDAO = new PessoaSalarioDAO();
            PessoaSalario pessoaSalario = pessoaSalarioDAO.getExistingPessoaSalario(pessoa);

            if (pessoaSalario != null) {
                pessoaSalarioDAO.deletePessoaSalario(pessoaSalario);
            }
            Pessoa mergedPessoa = em.merge(pessoa);
            em.remove(mergedPessoa);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
	}
}
