package br.eletrosom.portalcolaborador.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import br.eletrosom.portalcolaborador.entity.SolicitacoesDiversas;
import br.eletrosom.portalcolaborador.service.EmpresaService;
import br.eletrosom.portalcolaborador.service.LogService;
import br.eletrosom.portalcolaborador.service.SolicitacoesDiversasService;
import br.eletrosom.portalcolaborador.service.UsuarioService;
import br.eletrosom.portalcolaborador.view.UsuarioView;

/*
 * CONTROLADOR DAS PAGINAS DE SOLICITAÇÕES DIVERSAS
 */

@Controller
public class SolicitacoesDiversasController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private SolicitacoesDiversasService solService;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private LogService logService;

	// private static String UPLOADED_FOLDER = "C://tmp//";

	// LISTA AS SOLICITAÇÕES DE CRACHA POR USUARIO
	@ModelAttribute("allCrachaUsuario")
	public List<SolicitacoesDiversas> populateCrachaTable(Principal principal) {
		return this.solService.listaUsuarioSolicitacao(principal.getName(), "CRA");
	}

	// LISTA AS SOLICITAÇÕES DE UNIFORME POR USUARIO
	@ModelAttribute("allUniformeUsuario")
	public List<SolicitacoesDiversas> populateUniformes(Principal principal) {
		return this.solService.listaUsuarioSolicitacao(principal.getName(), "UNI");
	}

	// LISTA AS SOLICITAÇÕES DE VALE ALIMENTACAO POR USUARIO
	@ModelAttribute("allAlimentacaoUsuario")
	public List<SolicitacoesDiversas> populateAlimentacao(Principal principal) {
		return this.solService.listaUsuarioSolicitacao(principal.getName(), "ALI");
	}

	// LISTA AS SOLICITAÇÕES DE VALE TRANSPORTE POR USUARIO
	@ModelAttribute("allTransporteUsuario")
	public List<SolicitacoesDiversas> populateTransporte(Principal principal) {
		return this.solService.listaUsuarioSolicitacao(principal.getName(), "TRA");
	}

	// LISTA AS SOLICITAÇÕES DE PLANO SAUDE POR USUARIO
	@ModelAttribute("allSaudeUsuario")
	public List<SolicitacoesDiversas> populateSaude(Principal principal) {
		return this.solService.listaUsuarioSolicitacao(principal.getName(), "SAU");
	}

	// LISTA AS SOLICITAÇÕES DE PLANO ODONTOLOGICO POR USUARIO
	@ModelAttribute("allOdontologicoUsuario")
	public List<SolicitacoesDiversas> populateOdontologico(Principal principal) {
		return this.solService.listaUsuarioSolicitacao(principal.getName(), "ODO");
	}

	// LISTA AS SOLICITAÇÕES DE CONVENIO DROGALIDER POR USUARIO
	@ModelAttribute("allDrogaliderUsuario")
	public List<SolicitacoesDiversas> populateDrogalider(Principal principal) {
		return this.solService.listaUsuarioSolicitacao(principal.getName(), "DRO");
	}

	// CARREGA PAGINA DE SOLICITAÇÃO DE CRACHA
	@GetMapping("/logado_usuario/cracha")
	public String listaHistoricoCracha(Principal principal, Model model) {

		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, principal.getName()));

		// dataFormatada = new
		// DateUtil().converteDateView(Calendar.getInstance().getTime());
		SolicitacoesDiversas solicitacoesDiversas = new SolicitacoesDiversas();
		solicitacoesDiversas.setDataSolicitacao(Calendar.getInstance().getTime());
		model.addAttribute(solicitacoesDiversas);

		return "logado_usuario/cracha";
	}

	// SALVA A SOLICITAÇÃO DE CRACHA
	@PostMapping("/logado_usuario/cracha")
	public String saveSolicitaCracha(Principal principal, final SolicitacoesDiversas cracha, MultipartFile file,
			final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "/logado_usuario/cracha";
		}
		cracha.setUsuario(usuarioService.getUsuario(principal.getName()));
		cracha.setDataSolicitacao(new Date());
		String arquivo = "cra_" + cracha.getUsuario().getFilial().getCodigo() + "_" + cracha.getUsuario().getCodigo();
		String upload = "";

		if (file.getSize() > 0) {
			upload = "/solicitacoes/" + arquivo + ".jpg";
			this.gravarArquivo(arquivo + ".jpg", file);
		}
		cracha.setUpload(upload);
		this.solService.salvarSolicitacao(cracha, "CRA");


		logService.registrarLog("SOLICITOU CRACHA", principal.getName());
		model.clear();
		return "redirect:/logado_usuario/cracha";
	}

	// CARREGA PAGINA DE SOLICITAÇÃO DE UNIFORME
	@GetMapping("/logado_usuario/uniforme")
	public String listaHistoricoUniforme(Principal principal, Model model) {

		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, principal.getName()));

		// dataFormatada = new
		// DateUtil().converteDateView(Calendar.getInstance().getTime());
		SolicitacoesDiversas solicitacoesDiversas = new SolicitacoesDiversas();
		solicitacoesDiversas.setDataSolicitacao(Calendar.getInstance().getTime());
		model.addAttribute(solicitacoesDiversas);

		return "logado_usuario/uniforme";
	}

	// SALVA A SOLICITAÇÃO DE UNIFORME
	@PostMapping("/logado_usuario/uniforme")
	public String saveSolicitaUniforme(Principal principal, final SolicitacoesDiversas solicitacoesDiversas,
			final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "logado_usuario/uniforme";
		}
		solicitacoesDiversas.setUsuario(usuarioService.getUsuario(principal.getName()));
		solicitacoesDiversas.setDataSolicitacao(new Date());
		this.solService.salvarSolicitacao(solicitacoesDiversas, "UNI");
		logService.registrarLog("SOLICITOU UNIFORME", principal.getName());
		model.clear();
		return "redirect:/logado_usuario/uniforme";
	}

	// CARREGA PAGINA DE SOLICITAÇÃO DE VALE ALIMENTACAO
	@GetMapping("/logado_usuario/alimentacao")
	public String listaHistoricoAlimentacao(Principal principal, Model model) {

		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, principal.getName()));

		// dataFormatada = new
		// DateUtil().converteDateView(Calendar.getInstance().getTime());
		SolicitacoesDiversas solicitacao = new SolicitacoesDiversas();
		solicitacao.setEmpresa(empresaService.buscarPorCodigo(usuarioView.getFilialCodigo()));
		solicitacao.setDataSolicitacao(new Date());
		model.addAttribute("solicitacao", solicitacao);
		model.addAttribute("solicitacoesDiversas", new SolicitacoesDiversas());

		return "logado_usuario/alimentacao";
	}

	// SALVA A SOLICITAÇÃO DE V.ALIMENTACAO
	@PostMapping("/logado_usuario/alimentacao")
	public String saveSolicitaAlimentacao(Principal principal, final SolicitacoesDiversas solicitacoesDiversas,
			final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "logado_usuario/alimentacao";
		}
		solicitacoesDiversas.setUsuario(usuarioService.getUsuario(principal.getName()));
		solicitacoesDiversas.setDataSolicitacao(new Date());
		this.solService.salvarSolicitacao(solicitacoesDiversas, "ALI");
		logService.registrarLog("SOLICITOU VAL.ALI", principal.getName());
		model.clear();
		return "redirect:/logado_usuario/alimentacao";
	}

	// CARREGA PAGINA DE SOLICITAÇÃO DE VALE TRANSPORTE
	@GetMapping("/logado_usuario/transporte")
	public String listaHistoricoTransporte(Principal principal, Model model) {

		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, principal.getName()));

		// dataFormatada = new
		// DateUtil().converteDateView(Calendar.getInstance().getTime());
		SolicitacoesDiversas solicitacoesDiversas = new SolicitacoesDiversas();
		solicitacoesDiversas.setDataSolicitacao(Calendar.getInstance().getTime());
		model.addAttribute(solicitacoesDiversas);

		return "logado_usuario/transporte";
	}

	// SALVA A SOLICITAÇÃO DE V.TRANSPORTE
	@PostMapping("/logado_usuario/transporte")
	public String saveSolicitaTransporte(Principal principal, final SolicitacoesDiversas solicitacoesDiversas,
			final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "logado_usuario/transporte";
		}
		solicitacoesDiversas.setUsuario(usuarioService.getUsuario(principal.getName()));
		solicitacoesDiversas.setDataSolicitacao(new Date());
		this.solService.salvarSolicitacao(solicitacoesDiversas, "TRA");
		logService.registrarLog("SOLICITOU VAL.TRANS", principal.getName());
		model.clear();
		return "redirect:/logado_usuario/transporte";
	}

	//// CARREGA PAGINA DE SOLICITAÇÃO DE PLANO SAUDE
	@GetMapping("/logado_usuario/saude")
	public String listaHistoricoSaude(Principal principal, Model model) {

		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, principal.getName()));

		// dataFormatada = new
		// DateUtil().converteDateView(Calendar.getInstance().getTime());
		SolicitacoesDiversas solicitacoesDiversas = new SolicitacoesDiversas();
		solicitacoesDiversas.setDataSolicitacao(Calendar.getInstance().getTime());
		model.addAttribute(solicitacoesDiversas);

		return "logado_usuario/saude";
	}

	// SALVA A SOLICITAÇÃO DE P. SAUDE
	@PostMapping("/logado_usuario/saude")
	public String saveSolicitaSaude(Principal principal, final SolicitacoesDiversas solicitacoesDiversas,
			MultipartFile file, final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "logado_usuario/saude";
		}
		solicitacoesDiversas.setUsuario(usuarioService.getUsuario(principal.getName()));
		solicitacoesDiversas.setDataSolicitacao(new Date());

		String arquivo = "sau_" + solicitacoesDiversas.getUsuario().getFilial().getCodigo() + "_"
				+ solicitacoesDiversas.getUsuario().getCodigo();

		String upload = "";

		if (file.getSize() > 0) {
			upload = "/solicitacoes/" + arquivo + ".jpg";
			this.gravarArquivo(arquivo + ".jpg", file);
		}
		solicitacoesDiversas.setUpload(upload);

		this.solService.salvarSolicitacao(solicitacoesDiversas, "SAU");
		logService.registrarLog("SOLICITOU P.SAUDE", principal.getName());
		model.clear();
		return "redirect:/logado_usuario/saude";
	}

	// CARREGA PAGINA DE SOLICITAÇÃO DE PLANO ODONTOLOGICO
	@GetMapping("/logado_usuario/odontologico")
	public String listaHistoricoOdontologico(Principal principal, Model model) {

		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, principal.getName()));

		// dataFormatada = new
		// DateUtil().converteDateView(Calendar.getInstance().getTime());
		SolicitacoesDiversas solicitacoesDiversas = new SolicitacoesDiversas();
		solicitacoesDiversas.setDataSolicitacao(Calendar.getInstance().getTime());
		model.addAttribute(solicitacoesDiversas);

		return "logado_usuario/odontologico";
	}

	// SALVA A SOLICITAÇÃO DE P. ODONTO
	@PostMapping("/logado_usuario/odontologico")
	public String saveSolicitaOdontologico(Principal principal, final SolicitacoesDiversas solicitacoesDiversas,
			MultipartFile file, final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "logado_usuario/odontologico";
		}
		solicitacoesDiversas.setUsuario(usuarioService.getUsuario(principal.getName()));
		solicitacoesDiversas.setDataSolicitacao(new Date());

		String arquivo = "odo_" + solicitacoesDiversas.getUsuario().getFilial().getCodigo() + "_"
				+ solicitacoesDiversas.getUsuario().getCodigo();
		String upload = "";

		if (file.getSize() > 0) {
			upload = "/solicitacoes/" + arquivo + ".jpg";
			this.gravarArquivo(arquivo + ".jpg", file);
		}
		solicitacoesDiversas.setUpload(upload);
		this.solService.salvarSolicitacao(solicitacoesDiversas, "ODO");
		logService.registrarLog("SOLICITOU P.ODON", principal.getName());
		model.clear();
		return "redirect:/logado_usuario/odontologico";
	}

	// CARREGA PAGINA DE SOLICITAÇÃO DE CONVENIO DROGALIDER
	@GetMapping("/logado_usuario/drogalider")
	public String listaHistoricoDrogasil(Principal principal, Model model) {

		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, principal.getName()));

		// dataFormatada = new
		// DateUtil().converteDateView(Calendar.getInstance().getTime());
		SolicitacoesDiversas solicitacoesDiversas = new SolicitacoesDiversas();
		solicitacoesDiversas.setDataSolicitacao(Calendar.getInstance().getTime());
		model.addAttribute(solicitacoesDiversas);

		return "logado_usuario/drogalider";
	}

	// SALVA A SOLICITAÇÃO DE DROGALIDER
	@PostMapping("/logado_usuario/drogalider")
	public String saveSolicitaDrogalider(Principal principal, final SolicitacoesDiversas solicitacoesDiversas,
			final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "logado_usuario/drogasil";
		}
		solicitacoesDiversas.setUsuario(usuarioService.getUsuario(principal.getName()));
		solicitacoesDiversas.setDataSolicitacao(new Date());
		this.solService.salvarSolicitacao(solicitacoesDiversas, "DRO");
		logService.registrarLog("SOLICITOU DROGALIDER", principal.getName());
		model.clear();
		return "redirect:/logado_usuario/drogalider";
	}

	// RECEBE UPLOAD DE ARQUIVOS
	public void gravarArquivo(String nome, MultipartFile file) {
		try {

			String UPLOADED_FOLDER = new File(".").getCanonicalPath() + "/solicitacoes/";
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + nome);
			Files.write(path, bytes);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
