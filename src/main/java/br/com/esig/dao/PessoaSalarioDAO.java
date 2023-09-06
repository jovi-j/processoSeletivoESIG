package br.com.esig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.esig.controller.EMUtil;
import br.com.esig.model.Pessoa;
import br.com.esig.model.PessoaSalario;

public class PessoaSalarioDAO {


	public void updateSalarios(List<PessoaSalario> psList, List<Pessoa> pessoas) {
		EntityManager em = EMUtil.getEntityManager();
		em.getTransaction().begin();
		CargoDAO cDAO = new CargoDAO();
		
		if(psList.isEmpty()) {
			for (Pessoa p : pessoas) {
				PessoaSalario ps;
				if(p.getCargo() == null) {
					 ps = new PessoaSalario(p);
				}
				else {
					ps = new PessoaSalario(p, cDAO.getSalarioByCargo(p.getCargo()));
				}
				ps = em.merge(ps);
				em.persist(ps);
				psList.add(ps);
			}
			em.close();
			return;
		}

		for (PessoaSalario ps : psList) {
			if(ps.getPessoa().getCargo() != null) {
				ps.setSalario(cDAO.getSalarioByCargo(ps.getPessoa().getCargo()));
				ps = em.merge(ps);
				em.persist(ps);
			}
		}
		em.close();

	}
	
	
	public List<PessoaSalario> buscarTodasPessoasSalario(){
		EntityManager em = EMUtil.getEntityManager();
		TypedQuery<PessoaSalario> pessoaSalQuery = em.createQuery("select ps from PessoaSalario ps", PessoaSalario.class);
		List<PessoaSalario> psList = pessoaSalQuery.getResultList();
		em.close();
		return psList;
	}
}
