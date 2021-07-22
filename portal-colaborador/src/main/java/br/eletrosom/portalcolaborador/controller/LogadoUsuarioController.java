package br.eletrosom.portalcolaborador.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.eletrosom.portalcolaborador.service.UsuarioService;
import br.eletrosom.portalcolaborador.view.UsuarioView;


// CONTROLADOR PARA PAGINAS QUE DEPENDEM SOMENTE DE O USUARIO ESTAR LOGADO


@Controller
//@RequestMapping("/logado_usuario")
public class LogadoUsuarioController {

	@Autowired
	UsuarioService usuarioService;

	//ABRE PAGINA COM AS OPÇOES DE SOLICITAÇÕES
	@RequestMapping(value = "/logado_usuario/solicita_diversas", method = RequestMethod.GET)
	private String solicita_diversas(Principal principal, Model model) {
		
		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, 
				principal.getName()));

		return "logado_usuario/solicita_diversas";

	}

	//CARREGA O CABEÇALHO DA PAGINA
	@RequestMapping(value = "/logado_usuario/header", method = RequestMethod.GET)
	private String header(Principal principal, Model model) {
		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, 
				principal.getName()));

		return "logado_usuario/header";

	}

}
