package br.eletrosom.portalcolaborador.requests;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import br.eletrosom.portalcolaborador.entity.Demonstrativo;

public class DemonstrativoSoapClient {

	String soapEndpointUrl = "http://10.0.0.223:80/g5-senior-services/rubi_Synccom_senior_g5_rh_fp_relatorios?wsdl";

	String line;
	String response = "";
	String reqresponse;
	String returnvalue = "";

	public String buscarDocumento(Demonstrativo dem) {

		try {
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();

			// Send SOAP Message to SOAP Server
			SOAPMessage soapRequest = createSOAPRequest(soapEndpointUrl, dem);
			SOAPMessage soapResponse = soapConnection.call(soapRequest, soapEndpointUrl);

			// System.out.println("Requisição: \n" +
			// passarXMLParaString(soapRequest.getSOAPBody().getOwnerDocument(), 4));

			System.out.println("Resposta: \n" + soapResponse.getSOAPBody().getTextContent());
		} catch (Exception e) {
			e.printStackTrace();
			returnvalue = "erro";
		}

		return returnvalue;

	}

	private SOAPMessage createSOAPRequest(String soapAction, Demonstrativo dem) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		createSoapEnvelope(soapMessage, dem);

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("Authorization", "Basic ");
		headers.addHeader("SOAPAction", soapAction);
		headers.addHeader("Content-Type", "text/xml");
		headers.addHeader("Host", "10.0.0.223");
		headers.addHeader("Connection", "keep-alive");

		soapMessage.saveChanges();

		return soapMessage;
	}

	private void createSoapEnvelope(SOAPMessage soapMessage, Demonstrativo dem) throws SOAPException {
		SOAPPart soapPart = soapMessage.getSOAPPart();

		String myNamespace2 = "ser";
		String myNamespaceURI2 = "http://services.senior.com.br";

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		// envelope.addNamespaceDeclaration(myNamespace1, myNamespaceURI1);
		envelope.addNamespaceDeclaration(myNamespace2, myNamespaceURI2);

		// SOAPHeader soapHeaer = envelope.getHeader();

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();

		SOAPElement soapBodyElem = soapBody.addChildElement("Relatorios", "ser");

		SOAPElement user = soapBodyElem.addChildElement("user");
		user.addTextNode("Portal");

		SOAPElement senha = soapBodyElem.addChildElement("password");
		senha.addTextNode("Projeto123*");

		SOAPElement encryption = soapBodyElem.addChildElement("encryption");
		encryption.addTextNode("0");

		SOAPElement parameters = soapBodyElem.addChildElement("parameters");

		// QName prdirName = new QName("prDir", "parameters");
		SOAPElement prdir = parameters.addChildElement("prDir");
		prdir.addTextNode("C:\\Senior\\Vetorh\\Relatorios\\demonstrativo");

		// QName execfmtName = new QName("prExecFmt", "parameters");
		SOAPElement execfmt = parameters.addChildElement("prExecFmt");
		execfmt.addTextNode("tefFile");

		// QName saveFormatName = new QName("prSaveFormat", "parameters");
		SOAPElement saveFormat = parameters.addChildElement("prSaveFormat");
		saveFormat.addTextNode("tsfPDF");

		returnvalue = "Holerite_" + dem.getUsuario() + "_" + dem.getMesref();

		// QName fileNametName = new QName("prFileName", "parameters");
		SOAPElement fileName = parameters.addChildElement("prFileName");
		fileName.addTextNode(returnvalue);

		// QName entranceIsXMLName = new QName("prEntranceIsXML", "parameters");
		SOAPElement entranceIsXML = parameters.addChildElement("prEntranceIsXML");
		entranceIsXML.addTextNode("F");

		// QName relatorioName = new QName("prRelatorio", "parameters");
		SOAPElement relatorio = parameters.addChildElement("prRelatorio");
		relatorio.addTextNode("FPEN302.ENV");
		
		String cdataString = "<![CDATA[<EIncOca=S><EMarPon=N><ETruMar=N><ETruEnt=0><ETruSai=0><ETruLim=N>"
				+ "><EIniPerCal=" + dem.getDataIni() + "><EFimPerCal=" + dem.getDataFim() 
				+ "><EAbrTipCal=11><EAbrEmp=1><EAbrTcl=1>"
				+ "<EAbrCad=" + dem.getUsuario() + ">]]>";

		/*String cdataString = "<![CDATA[<EIncOca=S><EMarPon=N><EIniPerCal=" + dem.getDataIni() + "><EFimPerCal="
				+ dem.getDataFim() + "><EAbrTipCal="
				+ "><EAbrEmp=1><EAbrTcl=1><EAbrCad=" + dem.getUsuario() + ">]]>";*/
		// QName entradaName = new QName("prEntrada", "parameters");
		SOAPElement entrada = parameters.addChildElement("prEntrada");
		entrada.addTextNode(cdataString);


	}

}
