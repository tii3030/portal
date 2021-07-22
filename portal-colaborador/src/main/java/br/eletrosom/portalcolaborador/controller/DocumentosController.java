package br.eletrosom.portalcolaborador.controller;

import java.io.File;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.eletrosom.portalcolaborador.entity.Usuario;
import br.eletrosom.portalcolaborador.requests.SoapFactory;
import br.eletrosom.portalcolaborador.service.UsuarioService;
import br.eletrosom.portalcolaborador.utils.DateUtil;

/*
 * CONTROLADOR DA EMISSÃO DE DOCUMENTOS
 */
@Controller
public class DocumentosController {

	// private String URL_API =
	// "http://srveletrosom55/portal_colab/ws_portal_colab.php?funcao=2&codigo=";

	@Autowired
	private UsuarioService usuarioService;

	// LISTA OS 12 MESES ANTERIORES A DATA ATUAL
	@ModelAttribute("mesesAno")
	public List<String> populateMesesAno() {
		DateUtil dateUtil = new DateUtil();

		return dateUtil.buscaUltimosDozeMeses();
	}
	
	// LISTA OS 3 ANOS ANTERIORES A DATA ATUAL
		@ModelAttribute("ultimosAnos")
		public List<String> populateAnos() {
			DateUtil dateUtil = new DateUtil();

			return dateUtil.buscaUltimosTresAnos();
		}

	// APRESENTA TELA DE OPÇÕES DE DOCUMENTOS
	@RequestMapping(value = "/logado_chave/documentos", method = RequestMethod.GET)
	private String escolherDocumento(Principal principal, Model model) {

		return "logado_chave/documentos";
	}

	// ABRE TELA PARA BAIXAR FOLHA DE PONTO
	@RequestMapping(value = "/logado_chave/cartao_ponto", method = RequestMethod.GET)
	private String buscaMesesPonto(Principal principal, Model model, String mesref) {

		model.addAttribute("mesref", mesref);

		return "logado_chave/cartao_ponto";

	}

	// FAZ O DOWNLOAD DO PDF
	@RequestMapping(value = "/logado_chave/cartao_ponto", method = RequestMethod.POST)
	private ResponseEntity<FileSystemResource>  buscaPdfPonto(Principal principal, Model model, String mesref) {

		Usuario usuario = usuarioService.getUsuario(principal.getName());

		model.addAttribute("mesref", mesref);
		
		String[] mr = mesref.split("/");
		String mesreffmt = mr[0].concat(mr[1]);

		List<String> datas = new DateUtil().buscaInicioEFimMes(mesref);
		
		SoapFactory soapf = new SoapFactory();
		//solicita o relatorio a api da senior e salva na pasta no servidor do sistema
		String FILE_NAME = soapf.buscarDoc("cartao_ponto", usuario.getCodigo(), usuario.getFilial().getCodigo(),
				datas.get(0), datas.get(1), mesreffmt);
		model.addAttribute("file", FILE_NAME);
		
		//faz o download do relatorio gerado
		//windows
	//	String fullPath = "//srveletrosom4/Senior/Vetorh/Relatorios/ponto/" + FILE_NAME + ".pdf";
		//vm linux
		String fullPath = "/relatorios/ponto/" + FILE_NAME + ".pdf";
		File file = new File(fullPath);
		long fileLength = file.length();

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.APPLICATION_PDF);
		respHeaders.setContentLength(fileLength);
		respHeaders.setContentDispositionFormData("attachment", FILE_NAME + ".pdf");

		return new ResponseEntity<FileSystemResource>(new FileSystemResource(file), respHeaders, HttpStatus.OK);

	}

	// ABRE TELA PARA BAIXAR DEMONSTRATIVO DE PAGAMENTO
	@RequestMapping(value = "/logado_chave/demonstrativo", method = RequestMethod.GET)
	private String demonstrativoPagto(Principal principal, Model model, String mesref) {

		model.addAttribute("mesref", mesref);

		return "logado_chave/demonstrativo";

	}

	// FAZ O DOWNLOAD DO PDF
	@RequestMapping(value = "/logado_chave/demonstrativo", method = RequestMethod.POST)
	private ResponseEntity<FileSystemResource>  buscaPdfDemonstrativo(Principal principal, Model model, String mesref) {

		Usuario usuario = usuarioService.getUsuario(principal.getName());

		String[] mr = mesref.split("/");
		String mesreffmt = mr[0].concat(mr[1]);
		
		model.addAttribute("mesref", mesref);

		List<String> datas = new DateUtil().buscaInicioEFimMes(mesref);

		SoapFactory soapf = new SoapFactory();
		String FILE_NAME = soapf.buscarDoc("demonstrativo", usuario.getCodigo(), usuario.getFilial().getCodigo(),
				datas.get(0), datas.get(1), mesreffmt);
		model.addAttribute("file", FILE_NAME);
		
		//windows
		//String fullPath = "//srveletrosom4/Senior/Vetorh/Relatorios/demonstrativo/" + FILE_NAME + ".pdf";
		//vm linux
		String fullPath = "/relatorios/demonstrativo/" + FILE_NAME + ".pdf";
		File file = new File(fullPath);
		long fileLength = file.length();

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.APPLICATION_PDF);
		respHeaders.setContentLength(fileLength);
		respHeaders.setContentDispositionFormData("attachment", FILE_NAME + ".pdf");
		
		return new ResponseEntity<FileSystemResource>(new FileSystemResource(file), respHeaders, HttpStatus.OK);

	}

	// ABRE TELA PARA BAIXAR INFORME DE RENDIMENTOS
	@RequestMapping(value = "/logado_chave/informe_rendimentos", method = RequestMethod.GET)
	private String informeRendimentos(Principal principal, Model model, String ano) {

		model.addAttribute("ano", ano);

		return "logado_chave/informe_rendimentos";

	}

	// FAZ O DOWNLOAD DO PDF
	@RequestMapping(value = "/logado_chave/informe_rendimentos", method = RequestMethod.POST)
	private ResponseEntity<FileSystemResource> buscaPdfInforme(Principal principal, Model model, String ano) {

		Usuario usuario = usuarioService.getUsuario(principal.getName());

		model.addAttribute("ano", ano);
		
	//	Calendar hoje = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
	//	hoje.setTime(new Date());
	//	Integer anoCorrente = hoje.get(Calendar.YEAR);
		Integer anoEntrega = Integer.parseInt(ano) + 1;
		
		String entrega = "28/02/" + anoEntrega;

		SoapFactory soapf = new SoapFactory();
		String FILE_NAME = soapf.buscarDoc("informe_rendimentos", usuario.getCodigo(), usuario.getFilial().getCodigo(),
				ano, entrega, "");
		
		model.addAttribute("file", FILE_NAME);
		
		//windows
		//String fullPath = "//srveletrosom4/Senior/Vetorh/Relatorios/informe/" + FILE_NAME + ".pdf";
		//vm linux
		String fullPath = "/relatorios/informe/" + FILE_NAME + ".pdf";
		File file = new File(fullPath);
		long fileLength = file.length();

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.APPLICATION_PDF);
		respHeaders.setContentLength(fileLength);
		respHeaders.setContentDispositionFormData("attachment", FILE_NAME + ".pdf");
		
		return new ResponseEntity<FileSystemResource>(new FileSystemResource(file), respHeaders, HttpStatus.OK);

	}

}
