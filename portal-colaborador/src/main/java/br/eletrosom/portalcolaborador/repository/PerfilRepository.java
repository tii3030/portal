package br.eletrosom.portalcolaborador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.eletrosom.portalcolaborador.entity.Perfil;

/*
 * PERSISTENCIA DO PERFIL DO USUARIO
 */
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	List<Perfil> findAll();
	
	Perfil findById(Integer profileId);

}
