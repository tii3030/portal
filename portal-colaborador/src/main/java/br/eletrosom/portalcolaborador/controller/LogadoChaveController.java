package br.eletrosom.portalcolaborador.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.eletrosom.portalcolaborador.entity.Usuario;
import br.eletrosom.portalcolaborador.service.UsuarioService;

/*
 * CONTROLADOR PARA VALIDAÇÃO DA CHAVE DE 3 CARACTERES
 */

@Controller
@RequestMapping("/logado_chave")
public class LogadoChaveController {

	@Autowired
	private UsuarioService usuarioService;

	// ABRE PAGINA PARA INSERIR A CHAVE D SEGURANÇA
	@RequestMapping(value = "/chave_cadastrais", method = RequestMethod.GET)
	public String getchaveCadastrais(Principal principal, Model model, String id, RedirectAttributes redirectAttributes) {

		Usuario usuario = usuarioService.getUsuario(principal.getName());

		model.addAttribute("id", id);

		model.addAttribute("principal", principal);

		model.addAttribute("usuario", usuario);

		redirectAttributes.addFlashAttribute("msg_resultado", "Permissão para opções com segurança concedida!");

		return "logado_chave/chave_cadastrais";
	}
	
	@RequestMapping(value = "/chave_documentos", method = RequestMethod.GET)
	public String getchaveDocumentos(Principal principal, Model model, String id, RedirectAttributes redirectAttributes) {

		Usuario usuario = usuarioService.getUsuario(principal.getName());

		model.addAttribute("id", id);

		model.addAttribute("principal", principal);

		model.addAttribute("usuario", usuario);

		redirectAttributes.addFlashAttribute("msg_resultado", "Permissão para opções com segurança concedida!");

		return "logado_chave/chave_documentos";
	}
	
	@RequestMapping(value = "/chave_ferias", method = RequestMethod.GET)
	public String getchaveFerias(Principal principal, Model model, String id, RedirectAttributes redirectAttributes) {

		Usuario usuario = usuarioService.getUsuario(principal.getName());

		model.addAttribute("id", id);

		model.addAttribute("principal", principal);

		model.addAttribute("usuario", usuario);

		redirectAttributes.addFlashAttribute("msg_resultado", "Permissão para opções com segurança concedida!");

		return "logado_chave/chave_ferias";
	}

	// VALIDA PAGINA DE SEGURANÇA E REDIRECIONA À MINHAS INFORMAÇÕES
	@RequestMapping(value = "/chave_cadastrais", method = RequestMethod.POST)
	public ModelAndView chaveCadastrais(Principal principal, String chave, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = usuarioService.getUsuario(principal.getName());

		if (usuario.getChaveColaborador() == null || usuario.getChaveColaborador().isEmpty()) {
			redirectAttributes.addFlashAttribute("message",
					"Você não possui chave cadsatrada. Favor realizar o cadastro!");
			
			model.addAttribute("status", "sem_cadastro");
			return new ModelAndView("redirect:/logado_chave/chave_cadastrais");
		}

		if (!usuario.getChaveColaborador().equals(chave)) {
			redirectAttributes.addFlashAttribute("message",
					"A chave informada está incorreta, por favor tente novamente!");
			model.addAttribute("status", "chave_incorreta");
			return new ModelAndView("redirect:/logado_chave/chave_cadastrais");
		}

		model.addAttribute("principal", principal);

		model.addAttribute("usuario", usuario);

		String pagina = "dadosCadastrais";

		ModelAndView modelAndView = new ModelAndView("redirect:/logado_chave/" + pagina);

		redirectAttributes.addFlashAttribute("msg_resultado", "Permissão para opções com segurança concedida!");

		return modelAndView;
	}

	// VALIDA PAGINA DE SEGURANÇA E REDIRECIONA À DOCUMENTOS
	@RequestMapping(value = "/chave_documentos", method = RequestMethod.POST)
	public ModelAndView chaveDocumentos(Principal principal, String chave, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = usuarioService.getUsuario(principal.getName());

		if (usuario.getChaveColaborador() == null || usuario.getChaveColaborador().isEmpty()) {
			redirectAttributes.addFlashAttribute("message",
					"Você não possui chave cadsatrada. Favor realizar o cadastro!");
			model.addAttribute("status", "sem_cadastro");
			return new ModelAndView("redirect:/logado_chave/chave_documentos");
		}

		if (!usuario.getChaveColaborador().equals(chave)) {
			redirectAttributes.addFlashAttribute("message",
					"A chave informada está incorreta, por favor tente novamente!");
			model.addAttribute("status", "chave_incorreta");
			return new ModelAndView("redirect:/logado_chave/chave_documentos");
		}

		model.addAttribute("principal", principal);

		model.addAttribute("usuario", usuario);

		String pagina = "documentos";

		ModelAndView modelAndView = new ModelAndView("redirect:/logado_chave/" + pagina);

		redirectAttributes.addFlashAttribute("msg_resultado", "Permissão para opções com segurança concedida!");

		return modelAndView;
	}

	// VALIDA PAGINA DE SEGURANÇA E REDIRECIONA À FÉRIAS
	@RequestMapping(value = "/chave_ferias", method = RequestMethod.POST)
	public ModelAndView chaveFerias(Principal principal, String chave, Model model, RedirectAttributes redirectAttributes) {

		Usuario usuario = usuarioService.getUsuario(principal.getName());

		if (usuario.getChaveColaborador() == null || usuario.getChaveColaborador().isEmpty()) {
			redirectAttributes.addFlashAttribute("message",
					"Você não possui chave cadsatrada. Favor realizar o cadastro!");
			model.addAttribute("status", "sem_cadastro");
			return new ModelAndView("redirect:/logado_chave/chave_ferias");
		}

		if (!usuario.getChaveColaborador().equals(chave)) {
			redirectAttributes.addFlashAttribute("message",
					"A chave informada está incorreta, por favor tente novamente!");
			model.addAttribute("status", "chave_incorreta");
			return new ModelAndView("redirect:/logado_chave/chave_ferias");
		}

		model.addAttribute("principal", principal);

		model.addAttribute("usuario", usuario);

		String pagina = "ferias";

		ModelAndView modelAndView = new ModelAndView("redirect:/logado_chave/" + pagina);

		redirectAttributes.addFlashAttribute("msg_resultado", "Permissão para opções com segurança concedida!");

		return modelAndView;
	}

}
