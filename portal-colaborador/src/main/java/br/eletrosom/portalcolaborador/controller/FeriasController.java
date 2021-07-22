package br.eletrosom.portalcolaborador.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import br.eletrosom.portalcolaborador.entity.DadosCadastrais;
import br.eletrosom.portalcolaborador.entity.Ferias;
import br.eletrosom.portalcolaborador.entity.FeriasJson;
import br.eletrosom.portalcolaborador.service.FeriasService;
import br.eletrosom.portalcolaborador.service.LogService;
import br.eletrosom.portalcolaborador.service.UsuarioService;
import br.eletrosom.portalcolaborador.utils.DateUtil;
import br.eletrosom.portalcolaborador.view.UsuarioView;

/*
 * CONTROLADOR DE SOLICITAÇÃO DE FÉRIAS
 */
@Controller
public class FeriasController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private FeriasService feriasService;

	@Autowired
	private LogService logService;

	private String URL_API = "http://srveletrosom55/portal_colab/ws_portal_colab.php?funcao=4&codigo=";

	// LISTA AS SOLICITAÇÕES DE FÉRIAS POR USUARIO
	@ModelAttribute("allFeriasUsuario")
	public List<Ferias> populateFerias(Principal principal) {
		return this.feriasService.listaFeriosPorUsuario(principal.getName());
	}

	// APRESENTA TELA DE OPÇÕES DE FÉRIAS
	@RequestMapping(value = "/logado_chave/ferias", method = RequestMethod.GET)
	private String escolherDocumento(Principal principal, Model model) {

		return "logado_chave/ferias";
	}

	@RequestMapping(value = "/logado_chave/solicita_ferias", method = RequestMethod.GET)
	private String solicitarFerias(Principal principal, Model model) {

		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, principal.getName()));
		Ferias ferias = new Ferias();
		ferias.setUsuario(usuarioView.getCodigo());
		model.addAttribute("ferias", ferias);
		String hoje = new DateUtil().converteDateView(new Date());

		model.addAttribute("hoje", hoje);

		RestTemplate restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);

		FeriasJson[] list = restTemplate.getForObject(URL_API + principal.getName(), FeriasJson[].class);

		

		if (list == null || list[0] == null) {

			return "logado_chave/erro_ferias";
			/*
			 * for (FeriasJson item : list) { model.addAttribute("dadosCadastrais", item); }
			 */
		}
		FeriasJson feriasJson = new FeriasJson();
		feriasJson = list[0];

		model.addAttribute("feriasJson", feriasJson);
		//cria data limite para solicitação de ferias - 60 dias após data atual
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 60);
		
		String dataLimite = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		
		model.addAttribute("dataLimite", dataLimite);

		return "logado_chave/solicita_ferias";
	}

	@RequestMapping(value = "/logado_chave/solicita_ferias", method = RequestMethod.POST)
	public String saveSolicitacaoFerias(Principal principal, final Ferias ferias, String datasolicitacao,
			String periodoini, String periodofim, Integer saldoferias, String inicioferias, Integer quantidade,
			final BindingResult bindingResult, final ModelMap model) throws ParseException {
		if (bindingResult.hasErrors()) {
			return "erro";
		}

		String dataSolicitacaoo = datasolicitacao.substring(6, 10) + "-" + datasolicitacao.substring(3, 5) + "-"
				+ datasolicitacao.substring(0, 2);

		ferias.setUsuario(principal.getName());
		ferias.setDataSolicitacao(new DateUtil().stringToDateBd(dataSolicitacaoo));
		ferias.setPeriodoInicio(new DateUtil().stringToDateBd(periodoini));
		ferias.setPeriodoFim(new DateUtil().stringToDateBd(periodofim));
		ferias.setSaldo(saldoferias);
		ferias.setInidio(new DateUtil().stringToDateBd(inicioferias));
		ferias.setFim(new DateUtil().somaDiasData(inicioferias, quantidade));
		ferias.setObs(usuarioService.getUsuario(principal.getName()).getFilial().getCodigo());
		ferias.setStatus("AGUARDANDO APROVAÇÃO");
		ferias.setCodges(this.buscarGestor(principal.getName()));
		ferias.setNome(usuarioService.buscarUsuarioView(new UsuarioView(), principal.getName()).getNome());

		feriasService.salvarFerias(ferias);
		logService.registrarLog("SOLICITOU FERIAS", principal.getName());

		// this.solService.salvarSolicitacao(cracha, "CRA");
		// logService.registrarLog("SOLICITOU CRACHA", principal.getName());
		// model.clear();
		return "redirect:/logado_chave/ferias";
	}

	@RequestMapping(value = "/logado_chave/historico_ferias", method = RequestMethod.GET)
	private String hitoricoFerias(Principal principal, Model model) {

		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, principal.getName()));

		return "logado_chave/historico_ferias";
	}

	private String buscarGestor(String codigo) {

		String URL = "http://srveletrosom55/portal_colab/ws_portal_colab.php?funcao=2&codigo=";

		RestTemplate restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);

		DadosCadastrais[] list = restTemplate.getForObject(URL + codigo, DadosCadastrais[].class);

		return list[0].getCodges();
	}

}
