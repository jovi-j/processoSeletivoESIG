package br.com.esig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.esig.model.Pessoa;
import br.com.esig.util.EMUtil;

public class PessoaDAO {
	
	public List<Pessoa> buscarTodasPessoas(){
		EntityManager em = EMUtil.getEntityManager();
		TypedQuery<Pessoa> pessoaQuery = em.createQuery("select p from Pessoa p", Pessoa.class);
		List<Pessoa> pessoas = pessoaQuery.getResultList();
		em.close();
		return pessoas;
	}

	public void salvarPessoa(Pessoa pessoa) {
		EntityManager em = EMUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			if(pessoa.getCargo() == null && pessoa.getCargoId() != null) {
				CargoDAO cDAO = new CargoDAO();
				pessoa.setCargo(cDAO.findById(pessoa.getCargoId()));
			}
			if (pessoa.getId() == null) {
				em.persist(pessoa);
			}
			pessoa = em.merge(pessoa);
			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}
}
