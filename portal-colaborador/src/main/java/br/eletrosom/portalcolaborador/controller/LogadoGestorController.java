package br.eletrosom.portalcolaborador.controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.eletrosom.portalcolaborador.entity.Ferias;
import br.eletrosom.portalcolaborador.model.ListaFerias;
import br.eletrosom.portalcolaborador.service.FeriasService;
import br.eletrosom.portalcolaborador.service.LogService;
import br.eletrosom.portalcolaborador.utils.DateUtil;
import br.eletrosom.portalcolaborador.view.UsuarioView;

@Controller
public class LogadoGestorController {

	@Autowired
	FeriasService feriasService;
	
	@Autowired
	private LogService logService;
	
	
	@RequestMapping(value = "/logado_gestor/opcoes_gestor", method = RequestMethod.GET)
	public String opcoesGestor(Principal principal, Model model) {

		UsuarioView usuarioView = null;

		model.addAttribute("usuarioView", usuarioView);

		return "logado_gestor/opcoes_gestor";
	}

	@RequestMapping(value = "/logado_gestor/equipe_ferias", method = RequestMethod.GET)
	public String visualizarFerias(Principal principal, Model model) {

		UsuarioView usuarioView = null;

		model.addAttribute("usuarioView", usuarioView);

		ListaFerias listaFerias = feriasService.listarFeriasPorGestor(principal.getName());

		List<Ferias> lFerias = listaFerias.getLista();
		model.addAttribute("lFerias", lFerias);
		model.addAttribute("listaFerias", listaFerias);

		return "logado_gestor/equipe_ferias";
	}

	@RequestMapping(value = "/logado_gestor/processa_ferias", method = RequestMethod.POST)
	public ModelAndView processarFerias(Long id, final Model model) {

		model.addAttribute("id", id);
		Ferias feriasCol = feriasService.buscaFeriasPorId(id);
		model.addAttribute("feriasCol", feriasCol);

		//return "logado_gestor/colaborador_ferias/{" .concat(usuario) .concat("}");
		return new ModelAndView("logado_gestor/colaborador_ferias");
	}

	@RequestMapping(value = "/logado_gestor/colaborador_ferias", method = RequestMethod.GET)
	public ModelAndView viewcolaboradorferias(Long id, Ferias feriasCol, Model model) {

		UsuarioView usuarioView = null;

		model.addAttribute("usuarioView", usuarioView);
		
		Ferias ferias = new Ferias();

		model.addAttribute("ferias", ferias);

		//String usuario = model.getAttribute("usuario").toString();

		//feriasCol = feriasService.buscaFeriasUsuarioPendente(usuario);
		
		feriasCol = feriasService.buscaFeriasPorId(id);

		model.addAttribute("feriasCol", feriasCol);

		//return "logado_gestor/colaborador_ferias";
		return new ModelAndView("logado_gestor/colaborador_ferias");
	}

	@RequestMapping(value = "/logado_gestor/colaborador_ferias", method = RequestMethod.POST)
	public String salvarColaboradorFerias(HttpServletResponse response, Principal principal, final Ferias ferias,
			Long id, String inicioferias, Integer quantidade, String fimferias, String status,
			final BindingResult bindingResult, final Model model) throws ParseException {
		
		if (bindingResult.hasErrors()) {
			return "erro";
		}
		
		Ferias feriaCol = feriasService.buscaFeriasPorId(id);
		
		if(status.equals("ALTERADO E APROVADO")) {
			feriaCol.setInidio(new DateUtil().stringToDateBd(inicioferias));
			feriaCol.setFim(new DateUtil().stringToDateBd(fimferias));
			feriaCol.setQuantidade(quantidade);
			feriaCol.setStatus(status);
		}else {
			feriaCol.setStatus(status);
		}
		
		feriasService.salvarFerias(feriaCol);
		logService.registrarLog("AUTORIZA FERIAS", principal.getName());
		
		return "redirect:/logado_gestor/equipe_ferias";
		// return new ModelAndView("erro");
	}

}
