package br.eletrosom.portalcolaborador.requests;

import br.eletrosom.portalcolaborador.entity.Demonstrativo;
import br.eletrosom.portalcolaborador.entity.FolhaPonto;
import br.eletrosom.portalcolaborador.entity.InformeRendimentos;

/*
 * FACOTORY PARA ENVIO DE REQUISIÇÕES
 */
public class SoapFactory {

	public SoapFactory() {
		super();
	}

	public String buscarDoc(String tipo, String usuario, String empresa, String inicio, String fim, String mesref) {

		String retorno = "";
		if (tipo.equals("cartao_ponto")) {
			retorno =  new PontoSoapClient().buscarDocumento(new FolhaPonto(usuario, empresa, inicio, fim, mesref));
		} else if (tipo.equals("demonstrativo")) {
			retorno = new DemonstrativoSoapClient().buscarDocumento(new Demonstrativo(usuario, empresa, inicio, fim, mesref));
		} else if (tipo.equals("informe_rendimentos")) {
			retorno = new InformeSoapClient().buscarDocumento(new InformeRendimentos(usuario, empresa, inicio, fim, mesref));
		}
		
		return retorno;
	}

}
