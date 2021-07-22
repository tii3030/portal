package br.eletrosom.portalcolaborador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.eletrosom.portalcolaborador.entity.SolicitacoesDiversas;

/*
 * PERSISTENCIA DE SOLICITACOES DIVERSAS
 */
@Repository
public interface SolicitacoesDiversasRepositorio extends JpaRepository<SolicitacoesDiversas, Long> {

	@Query(value = "SELECT sol FROM SolicitacoesDiversas sol"
			+ " WHERE sol.usuario.codigo = ?1")
	List<SolicitacoesDiversas> findAllByUsuario(String usuario);
	
	@Query(value = "SELECT sol FROM SolicitacoesDiversas sol"
			+ " WHERE sol.status = 'PENDENTE' order by sol.dataSolicitacao")
	List<SolicitacoesDiversas> findAllPendente();
	
	@Query(value = "SELECT sol FROM SolicitacoesDiversas sol"
			+ " WHERE sol.empresa.codigo = ?1"
			+ " and sol.status = 'PENDENTE' order by sol.dataSolicitacao")
	List<SolicitacoesDiversas> findPendenteByUnidade(String unidade);
	
	@Query(value = "SELECT sol FROM SolicitacoesDiversas sol"
			+ " WHERE sol.empresa.codigo = ?1"
			+ " and sol.solicitacao = ?2"
			+ " and sol.status = 'PENDENTE' order by sol.dataSolicitacao")
	List<SolicitacoesDiversas> findPendenteByUnidadeSolicitacao(String unidade, String solicitacao);
	
	@Query(value = "SELECT cra FROM SolicitacoesDiversas cra"
			+ " WHERE cra.usuario.codigo = ?1"
			+ " and cra.solicitacao = ?2 order by cra.dataSolicitacao")
	List<SolicitacoesDiversas> findByUsuarioSolicitacao(String codigoUsuario, String solicitacao);
	
	@Query(value = "SELECT sol FROM SolicitacoesDiversas sol"
			+ " WHERE sol.solicitacao = ?1 and sol.status = 'PENDENTE'"
			+ " order by sol.dataSolicitacao")
	List<SolicitacoesDiversas> findBySolicitacao(String solicitacao);
	
	@Query(value = "SELECT max(cra.id) FROM SolicitacoesDiversas cra")
	Long findMaxId();
	
	@Query(value = "SELECT cra FROM SolicitacoesDiversas cra"
			+ " where cra.id = ?1")
	SolicitacoesDiversas findBySolicitacaoId(Long id); 
	
}
