package br.eletrosom.portalcolaborador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.eletrosom.portalcolaborador.entity.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long>{
	
	@Query(value = "SELECT max(l.id) FROM Log l")
	Long findMaxId();
}
