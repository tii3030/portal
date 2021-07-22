package br.eletrosom.portalcolaborador.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eletrosom.portalcolaborador.entity.CadastroProdutos;
import br.eletrosom.portalcolaborador.entity.SolicitacoesDiversas;
import br.eletrosom.portalcolaborador.entity.Usuario;
import br.eletrosom.portalcolaborador.model.EnvioProdutosModel;
import br.eletrosom.portalcolaborador.repository.CadastroProdutosRepository;
import br.eletrosom.portalcolaborador.repository.SolicitacoesDiversasRepositorio;
import br.eletrosom.portalcolaborador.repository.UsuarioRepository;

/*
 * SERVIÇOS DE SOLICITAÇÕES DIVERSAS
 */
@Service
@Transactional
public class SolicitacoesDiversasService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SolicitacoesDiversasRepositorio solDivRepository;

	@Autowired
	private CadastroProdutosRepository cadProdRepository;

	//RETORNA LISTA DE SOLICITAÇÕES POR USUARIO E MODALIDADE DA SOLICITACAO(CRACHA, UNIFORME...)
	public List<SolicitacoesDiversas> listaUsuarioSolicitacao(String usuario, String solicitacao) {

		List<SolicitacoesDiversas> lista = solDivRepository.findByUsuarioSolicitacao(usuario, solicitacao);

		return lista;

	}

	//SALVA SOLICITAÇÃO
	public void salvarSolicitacao(SolicitacoesDiversas item, String solicitacao) {

		if (item == null) {
			throw new DisabledException("Não foram encontradas solicitações para este usuario");
		}

		Usuario usuario = usuarioRepository.findByCodigo(item.getUsuario().getCodigo());

		Long maxid = solDivRepository.findMaxId() == null ? 0 : solDivRepository.findMaxId();

		List<CadastroProdutos> listaproduto = cadProdRepository.findByTipoAndUnidade(solicitacao,
				usuario.getFilial().getCodigo());

		if (listaproduto.isEmpty() || listaproduto == null) {
			listaproduto = cadProdRepository.findByTipo(solicitacao);
		}

		if (!listaproduto.isEmpty()) {
			CadastroProdutos produto = listaproduto.get(0);

			Date dataEntrega = new Date();

			Calendar cal = Calendar.getInstance();
			cal.setTime(dataEntrega);
			cal.add(Calendar.DATE, produto.getPrazoEntrega());
			dataEntrega = cal.getTime();
			item.setDataPrevisao(dataEntrega);
		}

		item.setId(maxid + 1);
		item.setEmpresa(usuario.getFilial());
		item.setSolicitacao(solicitacao);
		item.setStatus("PENDENTE");

		try {
			solDivRepository.save(item);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	//ATUALIZA STATUS DA SOLICITAÇÃO APÓS SER ENVIADA PELO RH
	public void salvarEnvioSolicitacao(EnvioProdutosModel envioProdutosModel) {

		try {
			for (SolicitacoesDiversas item : envioProdutosModel.getLista()) {
				SolicitacoesDiversas sol = solDivRepository.findBySolicitacaoId(item.getId());
				sol.setDataEntrega(envioProdutosModel.getDataEnvio());
				sol.setStatus("ENVIADO");
				solDivRepository.saveAndFlush(sol);
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public EnvioProdutosModel listaEnvioExportacao(EnvioProdutosModel envioProdutosModel) {

		EnvioProdutosModel novo = new EnvioProdutosModel();
		novo.setDataEnvio(envioProdutosModel.getDataEnvio());

		for (SolicitacoesDiversas item : envioProdutosModel.getLista()) {
			SolicitacoesDiversas sol = solDivRepository.findBySolicitacaoId(item.getId());
			novo.getLista().add(sol);
		}
		return novo;
	}

	public List<SolicitacoesDiversas> listaPendentesFiltro(SolicitacoesDiversas item) {

		List<SolicitacoesDiversas> lista = null;

		if (item.getSolicitacao().equals("ALL") && !item.getEmpresa().getCodigo().equals("9999")) {
			lista = solDivRepository.findPendenteByUnidade(item.getEmpresa().getCodigo());
		} else if (!item.getSolicitacao().equals("ALL") && item.getEmpresa().getCodigo().equals("9999")) {
			lista = solDivRepository.findBySolicitacao(item.getSolicitacao());
		} else if (!item.getSolicitacao().equals("ALL") && !item.getEmpresa().getCodigo().equals("9999")) {
			lista = solDivRepository.findPendenteByUnidadeSolicitacao(item.getEmpresa().getCodigo(),
					item.getSolicitacao());
		} else {
			lista = solDivRepository.findAllPendente();
		}

		return lista;

	}

	public List<SolicitacoesDiversas> listaUsuario(Usuario usuario) {

		List<SolicitacoesDiversas> lista = solDivRepository.findAllByUsuario(usuario.getCodigo());

		return lista;

	}

	public List<SolicitacoesDiversas> listaTodosPendentes() {

		List<SolicitacoesDiversas> lista = solDivRepository.findAllPendente();

		return lista;

	}

	public List<SolicitacoesDiversas> listaPendenteUnidade(String unidade) {

		List<SolicitacoesDiversas> lista = solDivRepository.findPendenteByUnidade(unidade);

		return lista;

	}

	public List<SolicitacoesDiversas> listaPendentePorUnidadeSolicitacao(String unidade, String solicitacao) {

		// List<SolicitacoesDiversas> lista =
		// solDivRepository.findPendenteByUnidadeSolicitacao(unidade, solicitacao);

		List<SolicitacoesDiversas> lista = null;

		unidade = (unidade == null || unidade.equals("9999")) ? null : unidade;
		solicitacao = (solicitacao == null || solicitacao.equals("ALL")) ? null : solicitacao;

		if (unidade != null && solicitacao == null) {
			lista = solDivRepository.findPendenteByUnidade(unidade);
		} else if (unidade == null && solicitacao != null) {
			lista = solDivRepository.findBySolicitacao(solicitacao);
		} else if (unidade != null && solicitacao != null) {
			lista = solDivRepository.findPendenteByUnidadeSolicitacao(unidade, solicitacao);
		} else {
			lista = solDivRepository.findAllPendente();
		}

		return lista;

	}

	public EnvioProdutosModel listaEnvioProdutosModel(String unidade, String solicitacao) {

		// List<SolicitacoesDiversas> lista =
		// solDivRepository.findPendenteByUnidadeSolicitacao(unidade, solicitacao);

		List<SolicitacoesDiversas> lista = null;

		unidade = (unidade == null || unidade.equals("9999")) ? null : unidade;
		solicitacao = (solicitacao == null || solicitacao.equals("ALL")) ? null : solicitacao;

		if (unidade != null && solicitacao == null) {
			lista = solDivRepository.findPendenteByUnidade(unidade);
		} else if (unidade == null && solicitacao != null) {
			lista = solDivRepository.findBySolicitacao(solicitacao);
		} else if (unidade != null && solicitacao != null) {
			lista = solDivRepository.findPendenteByUnidadeSolicitacao(unidade, solicitacao);
		} else {
			lista = solDivRepository.findAllPendente();
		}

		EnvioProdutosModel listaEnvio = new EnvioProdutosModel();

		listaEnvio.setLista(lista);

		/*
		 * for (SolicitacoesDiversas item : lista) { EnvioProdutosModel envio = new
		 * EnvioProdutosModel(); envio.setSolicitacaoDiversa(item);
		 * listaEnvio.add(envio); }
		 */

		return listaEnvio;

	}

}
