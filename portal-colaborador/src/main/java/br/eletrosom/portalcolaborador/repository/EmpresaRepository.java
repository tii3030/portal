package br.eletrosom.portalcolaborador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.eletrosom.portalcolaborador.entity.Empresa;

/*
 * PERSISTENCIA DA EMPRESA
 */
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	
	Empresa findByCodigo(String codigo);
	
	@Query(value = "SELECT emp FROM Empresa emp"
			+ " WHERE emp.codigo = ?1"
			+ " and emp.ativa = 'S'")
	Empresa findAtivabyCodigo(String codigo);
	
	@Query(value = "SELECT emp FROM Empresa emp"
			+ " WHERE emp.ativa = 'S' order by emp.codigo")
	List<Empresa> findAllAtiva();

}
