package br.eletrosom.portalcolaborador.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping(value = "/empresa")
public class EmpresaController {
	
	//private String LOCAL_ARQUIVOS = "C:/portal_colaborador/";
	//private String LOCAL_ARQUIVOS = "home/ELETROSOM1/paulo.salomao/informacoes/";

	@RequestMapping(value = "/empresa", method = RequestMethod.GET)
	public String empresa() {
		return "empresa";
	}

	@RequestMapping(value = "/empresa/sobre", method = RequestMethod.GET)
	public String sobre() {

		return "empresa/sobre";
	}

	@RequestMapping(value = "/empresa/info", method = RequestMethod.GET)
	public String info() {
		return "empresa/info";
	}

	@RequestMapping(value = "/empresa/pol_viagens", method = RequestMethod.GET)
	public ResponseEntity<FileSystemResource> downloadPolViagens() throws IOException {
		
		String FILE_NAME = "Politica_de_Viagem_2021_ead.pdf";
	//	String fullPath = LOCAL_ARQUIVOS + FILE_NAME;
		String fullPath = new File(".").getCanonicalPath() + "/informacoes/" + FILE_NAME;
		File file = new File(fullPath);
		long fileLength = file.length();

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.APPLICATION_PDF);
		respHeaders.setContentLength(fileLength);
		respHeaders.setContentDispositionFormData("attachment", FILE_NAME);

		return new ResponseEntity<FileSystemResource>(new FileSystemResource(file), respHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/empresa/cod_conduto", method = RequestMethod.GET)
	public ResponseEntity<FileSystemResource> downloadCodCondutoa() throws IOException {

		String FILE_NAME = "Código_de_Conduta_Eletrosom_-_Atualizado.pdf";
		//String fullPath = LOCAL_ARQUIVOS + FILE_NAME;
		String fullPath = new File(".").getCanonicalPath() + "/informacoes/" + FILE_NAME;
		File file = new File(fullPath);
		long fileLength = file.length();

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.APPLICATION_PDF);
		respHeaders.setContentLength(fileLength);
		respHeaders.setContentDispositionFormData("attachment", FILE_NAME);

		return new ResponseEntity<FileSystemResource>(new FileSystemResource(file), respHeaders, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/empresa/layoutizacao", method = RequestMethod.GET)
	public ResponseEntity<FileSystemResource> downloadLayout() throws IOException {

		String FILE_NAME = "Manual_de_Padronização_de_Ponto_de_Venda_NOVA_eletrosom.pdf";
		//String fullPath = LOCAL_ARQUIVOS + FILE_NAME;
		String fullPath = new File(".").getCanonicalPath() + "/informacoes/" + FILE_NAME;
		File file = new File(fullPath);
		long fileLength = file.length();

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.APPLICATION_PDF);
		respHeaders.setContentLength(fileLength);
		respHeaders.setContentDispositionFormData("attachment", FILE_NAME);

		return new ResponseEntity<FileSystemResource>(new FileSystemResource(file), respHeaders, HttpStatus.OK);

	}

}
