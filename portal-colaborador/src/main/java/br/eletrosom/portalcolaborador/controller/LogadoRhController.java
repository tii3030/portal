package br.eletrosom.portalcolaborador.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.eletrosom.portalcolaborador.entity.CadastroProdutos;
import br.eletrosom.portalcolaborador.entity.Empresa;
import br.eletrosom.portalcolaborador.entity.SolicitacoesDiversas;
import br.eletrosom.portalcolaborador.exporter.EnvioProdutosExporter;
import br.eletrosom.portalcolaborador.model.EnvioProdutosModel;
import br.eletrosom.portalcolaborador.service.CadastroProdutosService;
import br.eletrosom.portalcolaborador.service.EmpresaService;
import br.eletrosom.portalcolaborador.service.LogService;
import br.eletrosom.portalcolaborador.service.SolicitacoesDiversasService;
import br.eletrosom.portalcolaborador.service.UsuarioService;
import br.eletrosom.portalcolaborador.utils.DateUtil;
import br.eletrosom.portalcolaborador.view.UsuarioView;

/*
 * CONTROLADOR DA SESSÃO DE ACESSO DO RH
 */

@Controller
//@RequestMapping("logado_rh")
public class LogadoRhController {

	// private static final Model = null;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private CadastroProdutosService produtosService;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private LogService logService;

	@Autowired
	private SolicitacoesDiversasService solicitacoesService;

	// lista produtos cadastrados
	@ModelAttribute("allProdutos")
	public List<CadastroProdutos> populateProdutos() {
		return this.produtosService.listaTodosProduto();
	}

	// lista empresas cadastradas
	@ModelAttribute("allEmpresas")
	public List<Empresa> populateEmpresas() {
		return this.empresaService.listarEmpresasAtivas();
	}

	// lista todas as solcitacoes com status pendente
	@ModelAttribute("allSolicitacoesPendentes")
	public List<SolicitacoesDiversas> populateSolicitacoes() {
		return this.solicitacoesService.listaTodosPendentes();
	}

	// TELA DAS OPÇÕES DISPONIVEIS AO RH
	@GetMapping("/logado_rh/opcoes_rh")
	public String opcoesRh(Principal principal, Model model) {

		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, principal.getName()));

		return "logado_rh/opcoes_rh";
	}

	// CADASTRA PRODUTOS
	@GetMapping("/logado_rh/produto")
	public String cadastroProdutos(Principal principal, Model model) {

		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, principal.getName()));

		CadastroProdutos cadastroProduto = new CadastroProdutos();
		Long id = produtosService.buscaId();
		cadastroProduto.setId(id);
		cadastroProduto.setCodigo(id.toString());
		model.addAttribute("cadastroProduto", cadastroProduto);
		return "logado_rh/produto";
	}

	// VISUALIZA PRODUTOS CADASTRADOS
	@GetMapping("/logado_rh/visualizar_produtos")
	public String visualizarProdutos(Principal principal, Model model, String solicitacao) {

		UsuarioView usuarioView = null;

		model.addAttribute("usuarioView", usuarioView);

		if (solicitacao == null || solicitacao.equals("ALL")) {
			List<CadastroProdutos> listaProdutos = produtosService.listaTodosProduto();

			model.addAttribute("listaProdutos", listaProdutos);

			return "logado_rh/visualizar_produtos";
		}

		List<CadastroProdutos> listaProdutos = produtosService.listaProdutoTipo(solicitacao);

		if(listaProdutos != null || listaProdutos.isEmpty()) {
			model.addAttribute("listaProdutos", listaProdutos);
		}else {
			listaProdutos.add(new CadastroProdutos());
			model.addAttribute("listaProdutos",listaProdutos );
		}
		

		return "logado_rh/visualizar_produtos";
	}

	// LISTA AS SOLICITAÇÕES PENDENTES PARA ENVIO
	@GetMapping("/logado_rh/envio_produtos")
	public String enviarProdutosView(Principal principal, Model model, String solicitacao, String unidade) {

		UsuarioView usuarioView = null;

		model.addAttribute("usuarioView", usuarioView);

		EnvioProdutosModel listaEnvio = solicitacoesService.listaEnvioProdutosModel(unidade, solicitacao);
		List<SolicitacoesDiversas> listaSolicitacoes = listaEnvio.getLista();
		model.addAttribute("listaSolicitacoes", listaSolicitacoes);
		model.addAttribute("listaEnvio", listaEnvio);

		return "logado_rh/envio_produtos";
	}

	// POST - CADSATRO DOS PRODUTOS
	@PostMapping("/logado_rh/produto")
	public String saveProduto(Principal principal, final CadastroProdutos cadastroProduto,
			final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "logado_rh/produto";
		}

		this.produtosService.salvarProduto(cadastroProduto);
		logService.registrarLog("CADASTRO PRODUTO", principal.getName());
		model.clear();
		return "redirect:/logado_rh/produto";
	}

	// REGISTRA OS ENVIOS DAS SOLICITAÇÕES SELECIONADS
	@PostMapping("/logado_rh/envio_produtos")
	public String salvarEnvio(HttpServletResponse response, Principal principal, final EnvioProdutosModel listaEnvio,
			String calendario, final BindingResult bindingResult, final Model model) {

		try {

			// String red = "";
			if (bindingResult.hasErrors()) {
				// return new ModelAndView("logado_rh/envio_produtos");
				return "erro";
			//	enviarProdutosView(principal, model, "", "");
			}

			if (listaEnvio.getLista().isEmpty()) {
				// return new ModelAndView("logado_rh/envio_produtos");
				return "erro";
				//enviarProdutosView(principal, model, "", "");
			}

			String tipo = "";

			EnvioProdutosModel envio = new EnvioProdutosModel();

			try {
				envio.setDataEnvio(new DateUtil().stringToDate(calendario));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (SolicitacoesDiversas item : listaEnvio.getLista()) {
				if (item.isSelected()) {
					// listaEnvio.getLista().remove(item);

					tipo = item.getSolicitacao();
					switch (tipo) {
					case "CRA":
						tipo = "Cracha";
						break;
					case "UNI":
						tipo = "Uniforme";
						break;
					case "SAU":
						tipo = "Plano Saude";
						break;
					case "ODO":
						tipo = "Plano Odontologico";
						break;
					case "ALI":
						tipo = "Vale Alimentação";
						break;
					case "TRA":
						tipo = "Vale Transporte";
						break;
					case "DRO":
						tipo = "Drogalider";
						break;
					default:
						tipo = item.getSolicitacao();
						break;

					}
					// item.setSolicitacao(tipo);
					envio.getLista().add(item);
				}

			}

			model.addAttribute("listaEnvio", envio);

			this.solicitacoesService.salvarEnvioSolicitacao(envio);
			logService.registrarLog("ENVIO PRODUTO", principal.getName());
			// this.exportToExcel(response, envio, tipo);

			response.setContentType("application/octet-stream");
			DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

			String envioformat = dateFormatter.format(envio.getDataEnvio());

			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=envio_" + tipo + "_" + envioformat + ".xlsx";
			response.setHeader(headerKey, headerValue);

			EnvioProdutosModel e = solicitacoesService.listaEnvioExportacao(envio);

			if (e != null) {
				EnvioProdutosExporter excelExporter = new EnvioProdutosExporter(e);
				excelExporter.export(response, tipo);
			}
			// red = "logado_rh/envio_produtos";
			// ModelAndView modelAndView = new ModelAndView("erro");
			// return modelAndView;
		//	enviarProdutosView(principal, model, "", "");

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "erro";
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}

		// red = "/logado_rh/envio_produtos";
		// return modelAndView;

		// RequestDispatcher dd=response.getRequestDispatcher(red);
		// dd.forward(request, response);
		// model.clear();
		// return new ModelAndView("erro");
		return "logado_rh/envio_produtos";
	}

	// EXPORTA OS PRODUTOS ENVIADOS PARA XLSX
	/*
	 * public void exportToExcel(HttpServletResponse response, EnvioProdutosModel
	 * envio, String tipo) { response.setContentType("application/octet-stream");
	 * DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
	 * 
	 * String envioformat = dateFormatter.format(envio.getDataEnvio());
	 * 
	 * String headerKey = "Content-Disposition"; String headerValue =
	 * "attachment; filename=envio_" + tipo + "_" + envioformat + ".xlsx";
	 * response.setHeader(headerKey, headerValue);
	 * 
	 * EnvioProdutosModel e = solicitacoesService.listaEnvioExportacao(envio);
	 * 
	 * EnvioProdutosExporter excelExporter = new EnvioProdutosExporter(e);
	 * 
	 * try { excelExporter.export(response.getOutputStream(), tipo); } catch
	 * (IOException e1) { // TODO Auto-generated catch block e1.printStackTrace(); }
	 * }
	 */

}
