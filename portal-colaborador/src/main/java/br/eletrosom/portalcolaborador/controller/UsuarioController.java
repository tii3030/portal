package br.eletrosom.portalcolaborador.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.eletrosom.portalcolaborador.details.UsuarioDetails;
import br.eletrosom.portalcolaborador.entity.Usuario;
import br.eletrosom.portalcolaborador.model.UsuarioModel;
import br.eletrosom.portalcolaborador.service.LogService;
import br.eletrosom.portalcolaborador.service.UsuarioService;

/*
 * CONTROLADOR DE PAGINAS PARA USUARIO LOGADO
 */

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LogService logService;

	//ABRE PAGINA INICIAL
	@GetMapping("/logado")
	public String logado(@AuthenticationPrincipal UsuarioDetails usuarioDetails, Model model) {
		String codigo = usuarioDetails.getUsername();
		Usuario user = usuarioService.getUsuario(codigo);

		model.addAttribute("user", user);
		model.addAttribute("pageTitle", "Account Details");

		return "about";
	}

	//ABRE PAGINA PARA CADASTRO DA CHAVE DE SEGURANÇA
	@RequestMapping(value = "/seguranca", method = RequestMethod.GET)
	public String chave(Model model) {
		return "usuario/seguranca";
	}

	//SALVA A CHAVE DE SEGURANÇA
	@RequestMapping(value = "/seguranca", method = RequestMethod.POST)
	public ModelAndView cadastrachave(Principal principal, String chave, Model model,
			RedirectAttributes redirectAttributes) {

		Usuario usuario = usuarioService.getUsuario(principal.getName());

		if (usuario.getChaveColaborador() == null || usuario.getChaveColaborador().isEmpty()
				|| usuario.getChaveColaborador().isBlank()) {

			UsuarioModel usuriomodel = new UsuarioModel(usuario.getCodigo(), usuario.getSenha(), chave, true);

			usuarioService.alterarUsuario(usuriomodel);
			logService.registrarLog("GRAVOU CHAVE SEG.", principal.getName());

		}else {
			String status = "chave_cadastrada";
			model.addAttribute("status", status);
			return new ModelAndView("redirect:/usuario/seguranca");
		}

		model.addAttribute("principal", principal);
	

		ModelAndView modelAndView = new ModelAndView("redirect:/home");

		redirectAttributes.addFlashAttribute("msg_resultado", "Permissão para opções com segurança concedida!");

		return modelAndView;
	}

	
	@RequestMapping(value = "/salvarUsuario", method = RequestMethod.POST)
	public ModelAndView salvarUsuario(@ModelAttribute("usuarioModel") UsuarioModel usuarioModel,
			final BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		
		/*
		 * VERIFICA SE TEM ALGUM ERRO (@NotEmpty), SE TIVER ALGUM ERRO DEVEMOS RETORNAR
		 * PARA A MESMA PÁGINA PARA O USUÁRIO CORRIGIR
		 */
		
		if (result.hasErrors()) {

			/* ADICIONA OS DADOS DO USUÁRIO PARA COLOCAR NO FORMULÁRIO */
			model.addAttribute("usuarioModel", usuarioModel);

			/* RETORNA A VIEW */
			return new ModelAndView("chave");
		} else {

			/* SALVANDO UM NOVO REGISTRO */
			usuarioService.alterarUsuario(usuarioModel);

		}

		ModelAndView modelAndView = new ModelAndView("redirect:/usuario/chave");

		/*
		 * PASSANDO O ATRIBUTO PARA O ModelAndView QUE VAI REALIZAR O REDIRECIONAMENTO
		 * COM A MENSAGEM DE SUCESSO
		 */
		redirectAttributes.addFlashAttribute("msg_resultado", "Registro salvo com sucesso!");

		/* REDIRECIONANDO PARA UM NOVO CADASTRO */
		return modelAndView;
	}
}
