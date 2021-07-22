package br.eletrosom.portalcolaborador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.eletrosom.portalcolaborador.entity.Acessos;
/*
 * PERSISTENCIA DA ENTIDADE ACESSOS
 */
@Repository
public interface AcessosRepository extends JpaRepository<Acessos, Long>{
	
	//List<Acessos> findByAcessosPkProfileId(Integer perfilId);
	
	@Query("SELECT acs FROM Acessos acs"
			+ " WHERE acs.acessosPK.profile_id = :profile_id")
	List<Acessos> findByAcessosPkProfileId (@Param("profile_id") Integer profile_id);
	
	@Query("SELECT acs FROM Acessos acs"
			+ " WHERE acs.acessosPK.profile_id = :profile_id"
			+ " and acs.acessosPK.node in ('portal_colaborador.usuario','portal_colaborador.rh',"
			+ "'portal_colaborador.gestor')")
	List<Acessos> findByAcessosPkProfileIdPortal (@Param("profile_id") Integer profile_id);

}
