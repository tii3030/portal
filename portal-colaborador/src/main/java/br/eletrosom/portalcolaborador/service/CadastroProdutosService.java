package br.eletrosom.portalcolaborador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.eletrosom.portalcolaborador.entity.CadastroProdutos;
import br.eletrosom.portalcolaborador.repository.CadastroProdutosRepository;

/*
 * SERVIÇOES DO CADASTRO DE PRODUTOS
 */
@Service
public class CadastroProdutosService {

	@Autowired
	private CadastroProdutosRepository cadProdRepository;

	//RETORNA PROXIMO ID DE PRODUTO
	public Long buscaId() {
		Long maxid = cadProdRepository.findMaxId() == null ? 0 : cadProdRepository.findMaxId();
		return (maxid + 1);
	}

	//SALVA PRODUTO
	public CadastroProdutos salvarProduto(CadastroProdutos produto) {

		 Long maxid = cadProdRepository.findMaxId() == null ? 0 :
		 cadProdRepository.findMaxId();
		 produto.setId(maxid + 1);

		return cadProdRepository.saveAndFlush(produto);
	}

	//RETORNA LISTA DE PRODUTOS FILTRADO POR TIPO(CRACHA, UNIFORME...) E UNIDADE
	public List<CadastroProdutos> listaProdutoTipoEUnidade(String tipo, String unidade) {

		List<CadastroProdutos> lista = cadProdRepository.findByTipoAndUnidade(tipo, unidade);

		return lista;
	}

	//RETORNA LISTA DE PRODUTOS FILTRADO POR TIPO(CRACHA, UNIFORME...)
	public List<CadastroProdutos> listaProdutoTipo(String tipo) {

		List<CadastroProdutos> lista = cadProdRepository.findByTipo(tipo);

		return lista;
	}

	//RETORNA LISTA DE PORDUTOS FILTRADO POR FILIAL
	public List<CadastroProdutos> listaProdutoUnidade(String unidade) {

		List<CadastroProdutos> lista = cadProdRepository.findByUnidade(unidade);

		return lista;
	}

	//RETORNA LISTA COM TODOS OS PRODUTOS CADASTRADOS
	public List<CadastroProdutos> listaTodosProduto() {

		return cadProdRepository.findAll();
	}

	//RETORNA PRODUTO POR CODIGO
	public CadastroProdutos buscaProduto(String codigo) {

		CadastroProdutos produto = cadProdRepository.findByCodigo(codigo);

		return produto;
	}
}
