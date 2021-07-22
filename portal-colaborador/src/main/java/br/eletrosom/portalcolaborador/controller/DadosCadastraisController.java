package br.eletrosom.portalcolaborador.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import br.eletrosom.portalcolaborador.entity.CepJson;
import br.eletrosom.portalcolaborador.entity.DadosCadastrais;

/*
 * Controlador da tela Minhas Informações
 */

@Controller
public class DadosCadastraisController {

	private String URL_API = "http://srveletrosom55/portal_colab/ws_portal_colab.php?funcao=2&codigo=";
	private String URL_SEND_API = "http://srveletrosom55/portal_colab/ws_portal_colab.php?funcao=5&codigo=";
	private String URL_CID_API = "http://srveletrosom55/portal_colab/ws_portal_colab.php?funcao=6&codigo=";

	@ModelAttribute("tipLogr")
	public List<String> populateTipoLogradouro() {

		return this.criaListTL();
	}

//REALIZA A REQUISIÇÃO AO WS PARA BUSCA DOS DADOS DA TELA MINHAS INFORMAÇÕES	
	@RequestMapping(value = "/logado_chave/dadosCadastrais", method = RequestMethod.GET)
	private String buscaDadosCadastrais(Principal principal, Model model) {

		/*
		 * if(model.getAttribute("usuario") == null) { return "logado_chave/chave"; }
		 */

		RestTemplate restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);

		DadosCadastrais[] list = restTemplate.getForObject(URL_API + principal.getName(), DadosCadastrais[].class);

		if (list != null) {
			for (DadosCadastrais item : list) {
				model.addAttribute("dadosCadastrais", item);
			}
		}

		return "logado_chave/dadosCadastrais";

	}

	@RequestMapping(value = "/logado_chave/atualiza_cadastro", method = RequestMethod.GET)
	private String atualizaCadastrais(Principal principal, String cepCodigo, Model model) {

		if (cepCodigo == null || cepCodigo.equals("")) {
			model.addAttribute("codigo", principal.getName());
		} else {
			model.addAttribute("codigo", principal.getName());
			model.addAttribute("cepCodigo", cepCodigo);

			List<CepJson> listacep = Arrays.asList(this.buscacidade(cepCodigo, principal.getName()));

			if (listacep == null || listacep.isEmpty() || listacep.get(0).getCepbai() == null) {
				return "logado_chave/erro_cad";
			}

			model.addAttribute("listacep", listacep);
			model.addAttribute("cid", listacep.get(0).getCodcid());

		}

		return "logado_chave/atualiza_cadastro";

	}

	@RequestMapping(value = "/logado_chave/atualiza_cadastro", method = RequestMethod.POST)
	private String atualizaDadosCadastrais(Principal principal, String cep, String cidade, String tipLog, String endereco,
			String numero, String complemento, String bairro, String dddtel1, String tel1, String dddtel2, String tel2,
			String email, Model model) {
		
		cep = cep.equals("") ? "" :  ",\"cep\":" + "\"" + cep + "\"";
		cidade = cidade.equals("") ? "" : ",\"cidade\":" + "\"" + cidade + "\"";
		tipLog = tipLog.equals("ZZZ") ? "" : ",\"tplogra\":" + "\"" + tipLog + "\"";
		endereco = endereco.equals("") ? "" : ",\"endereco\":" + "\"" + endereco + "\"";
		numero = numero.equals("") ? "" : ",\"numero\":" + "\"" + numero + "\"";
		complemento = complemento.equals("") ? "" : ",\"complemento\":" + "\"" + complemento + "\"";
		bairro = bairro.equals("B") ? "" : ",\"bairro\":" + "\"" + bairro + "\"";
		dddtel1 = dddtel1.equals("") ? "" : ",\"dddtel1\":" + "\"" + dddtel1 + "\"" ;
		tel1 = tel1.equals("") ? "" : ",\"telefone1\":" + "\"" + tel1 + "\"";
		dddtel2 = dddtel2.equals("") ? "" : ",\"dddtel2\":" + "\"" + dddtel2 + "\"";
		tel2 = tel2.equals("") ? "" : ",\"telefone2\":" + "\"" + tel2 + "\"";
		email = email.equals("") ? "" : ",\"emailpar\":" + "\"" + email + "\"";
		
		String json = "{\"ambiente\":\"PRODUCAO\" " + cep + cidade + tipLog + 
				endereco + numero + complemento + bairro + dddtel1 +  
				tel1 + dddtel2 + tel2  + email + "}";
		

		/*String json = "{\"ambiente\":\"PRODUCAO\", " + "\"tplogra\":" + "\"" + tipLog + "\", " + "\"endereco\":" + "\""
				+ endereco + "\", " + "\"endereco\":" + "\"" + endereco + "\", " + "\"numero\":" + "\"" + numero
				+ "\", " + "\"complemento\":" + "\"" + complemento + "\", " + "\"bairro\":" + "\"" + bairro + "\", "
				+ "\"cep\":" + "\"" + cep + "\"cidade\":" + "\"" + cidade + "\"estado\":" + "\"\"," + "\"dddtel1\":" + "\""
				+ dddtel1 + "\", " + "\"telefone1\":" + "\"" + tel1 + "\", " + "\"dddtel2\":" + "\"" + dddtel2 + "\", "
				+ "\"telefone2\":" + "\"" + tel2 + "\", " + "\"emailpar\":" + "\"" + email + "\"} ";
*/
		// atualiza dados cadastrais

		String url = URL_SEND_API + principal.getName() + "&json={json}";

		RestTemplate restTemplateSend = new RestTemplate();

		String response = restTemplateSend.getForObject(url, String.class, json);

		if (response == null) {
			System.out.println("Request Failed");

		}
		

		// busca dados atualizados

		RestTemplate restTemplateGet = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplateGet.setMessageConverters(messageConverters);

		DadosCadastrais[] list = restTemplateGet.getForObject(URL_API + principal.getName(), DadosCadastrais[].class);

		if (list != null) {
			for (DadosCadastrais item : list) {
				model.addAttribute("dadosCadastrais", item);
			}
		}

		return "logado_chave/dadosCadastrais";

	}

	public CepJson[] buscacidade(String cep, String codigo) {

		RestTemplate restTemplateb = new RestTemplate();

		List<HttpMessageConverter<?>> messageConvertersb = new ArrayList<>();
		MappingJackson2HttpMessageConverter converterb = new MappingJackson2HttpMessageConverter();
		converterb.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConvertersb.add(converterb);
		restTemplateb.setMessageConverters(messageConvertersb);

		String json_cid = "{" + "\"cep\":" + cep + "}";

		String url_cid = URL_CID_API + codigo + "&json={json}";

		CepJson[] listCid = restTemplateb.getForObject(url_cid, CepJson[].class, json_cid);

		if (listCid == null) {
			return new CepJson[] {};
		}

		return listCid;
	}

	private List<String> criaListTL() {
		List<String> tipLog = new ArrayList<>();
		tipLog.add("A");
		tipLog.add("AL");
		tipLog.add("AV");
		tipLog.add("CAM");
		tipLog.add("CH");
		tipLog.add("CJ");
		tipLog.add("COND");
		tipLog.add("COR");
		tipLog.add("CPO");
		tipLog.add("DT");
		tipLog.add("EST");
		tipLog.add("FAZ");
		tipLog.add("JD");
		tipLog.add("LD");
		tipLog.add("LOT");
		tipLog.add("NUC");
		tipLog.add("PC");
		tipLog.add("PRQ");
		tipLog.add("Q");
		tipLog.add("R");
		tipLog.add("REC");
		tipLog.add("RES");
		tipLog.add("ROD");
		tipLog.add("SIT");
		tipLog.add("ST");
		tipLog.add("TR");
		tipLog.add("TRV");
		tipLog.add("TV");
		tipLog.add("V");
		tipLog.add("VC");
		tipLog.add("VL");
		tipLog.add("VLA");

		return tipLog;
	}

}
