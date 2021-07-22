package br.eletrosom.portalcolaborador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.eletrosom.portalcolaborador.entity.Usuario;

/*
 * PERSISTENCIA DE USUARIO
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value = "SELECT usr FROM Usuario usr"
			+ " WHERE usr.codigo = ?1 and sr_deleted = ''")
	Usuario findByCodigo(String codigo);

	@Query("SELECT usr.codigo, usr.senha, usr.chaveColaborador, usr.deleted FROM Usuario usr"
			+ " WHERE usr.codigo = :codigo" + " and usr.senha = :senha")
	Usuario findByCodigoAndSenha(@Param("codigo") String codigo, @Param("senha") String senha);

	@Query("SELECT usr.codigo, usr.senha, usr.chaveColaborador, usr.deleted FROM Usuario usr"
			+ " WHERE usr.codigo = :codigo" + " and usr.senha = :senha" + " and usr.chaveColaborador = :chave")
	Usuario findByCodigoAndSenhaAndChave(@Param("codigo") String codigo, @Param("senha") String senha,
			@Param("chave") String chave);

}
