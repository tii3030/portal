package br.eletrosom.portalcolaborador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.eletrosom.portalcolaborador.entity.Ferias;

@Repository
public interface FeriasRepository extends JpaRepository<Ferias, Long> {
	
	@Query(value = "SELECT fer FROM Ferias fer"
			+ " WHERE fer.usuario = ?1 order by fer.periodoInicio, fer.inidio desc")
	List<Ferias> findAllByUsuario(String usuario);
	
	@Query(value = "SELECT fer FROM Ferias fer"
			+ " WHERE fer.usuario = ?1 and fer.status like 'AGUARDANDO APROVAÇÃO' ")
	Ferias findAllByUsuarioPendente(String usuario);
	
	@Query(value = "SELECT fer FROM Ferias fer"
			+ " WHERE fer.id = ?1")
	Ferias findFeriasById(Long id);
	
	@Query(value = "SELECT fer FROM Ferias fer"
			+ " WHERE fer.obs = ?1 and fer.codges = ?2 "
			+ " and fer.status like 'AGUARDANDO APROVAÇÃO' "
			+ " order by fer.dataSolicitacao asc")
	List<Ferias> findAllByObsAndCodges(String codEmp, String gestor);
	
	@Query(value = "SELECT max(fer.id) FROM Ferias fer")
	Long findMaxId();

}
