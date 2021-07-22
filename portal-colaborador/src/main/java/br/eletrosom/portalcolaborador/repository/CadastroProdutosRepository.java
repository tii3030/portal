package br.eletrosom.portalcolaborador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.eletrosom.portalcolaborador.entity.CadastroProdutos;
/*
 * PERSISTENCIA DO CADASTRO DE PRODUTOS
 */
@Repository
public interface CadastroProdutosRepository extends JpaRepository<CadastroProdutos, Long> {
	
	@Query(value = "SELECT prod FROM CadastroProdutos prod"
			+ " WHERE prod.tipo = ?1"
			+ " and prod.unidade = ?2 order by prod.codigo")
	List<CadastroProdutos> findByTipoAndUnidade(String tipo, String unidade);
	
	@Query(value = "SELECT prod FROM CadastroProdutos prod"
			+ " WHERE prod.tipo = ?1 order by prod.codigo")
	List<CadastroProdutos> findByTipo(String tipo);
	
	@Query(value = "SELECT prod FROM CadastroProdutos prod"
			+ " WHERE prod.unidade = ?1 order by prod.codigo")
	List<CadastroProdutos> findByUnidade(String unidade);
	
	CadastroProdutos findByCodigo(String codigo);
	
	@Query(value = "SELECT max(pro.id) FROM CadastroProdutos pro")
	Long findMaxId();

}
