package br.eletrosom.portalcolaborador.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.eletrosom.portalcolaborador.model.UsuarioModel;
import br.eletrosom.portalcolaborador.service.UsuarioService;
import br.eletrosom.portalcolaborador.view.UsuarioView;

// CONTROLADOR DAS PAGINAS COM ACESSO PUBLICO

@Controller
public class MainController {

	/*
	 * ESSE MÉTODO CARREGA A PÁGINA(index.html) DE LOGIN DA NOSSA APLICAÇÃO
	 * @return
	 */

	@Autowired
	UsuarioService usuarioService;

	//PAGINA INICIAL DE LOGIN
	@RequestMapping(value="/", method= RequestMethod.GET)	
	public String index(){	
		 
	    return "index";
	}

	//PAGINA INICIAL DE LOGIN
	@RequestMapping(value="/login", method= RequestMethod.GET)	
	public String login(){	
		 
	    return "index";
	}

	//LOGOUT
	@RequestMapping(value="/logout", method= RequestMethod.GET)	
	public String logout(){	
 
	    return "index";
	}

	//LAYOUT DA ÁREA DO USUARIO AUTENTICADO
	@RequestMapping(value="/layout", method= RequestMethod.GET)	
	public String layout(Principal principal, Model model){	
		
		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, 
				principal.getName()));
 
	    return "layout";
	}

	//PAGINA INICIAL DA ÁREA DE USUARIO AUTENTICADO
	@RequestMapping(value="/about", method= RequestMethod.GET)
	public String home(Principal principal, Model model){
		
		UsuarioView usuarioView = new UsuarioView();
		model.addAttribute("usuarioView", usuarioService.buscarUsuarioView(usuarioView, 
				principal.getName()));
			
		System.out.println("Teseeeee");
		
		return "logado_usuario/about";
	}
 
	/***
	 * MOSTRA UM PÁGINA COM A MENSAGEM DE ACESSO NEGADO QUANDO O 
	 * USUÁRIO NÃO TIVER PERMISSÃO DE ACESSAR UMA DETERMINADA FUNÇÃO DO SISTEMA
	 * @return
	 */
	@RequestMapping(value="/acessoNegado", method= RequestMethod.GET)
	public String acessoNegado(){
 
		return "acessoNegado";
	}
	
	@RequestMapping(value="/erro", method= RequestMethod.GET)
	public String erro(){
		 
		return "erro";
	}

}
