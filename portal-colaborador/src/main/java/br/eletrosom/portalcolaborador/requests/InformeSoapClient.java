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

import br.eletrosom.portalcolaborador.entity.InformeRendimentos;

public class InformeSoapClient {

	String soapEndpointUrl = "http://10.0.0.223:80/g5-senior-services/rubi_Synccom_senior_g5_rh_fp_relatorios?wsdl";

	String line;
	String response = "";
	String reqresponse;
	String returnvalue = "";

	public String buscarDocumento(InformeRendimentos informe) {

		// String returnvalue = "";
		try {
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();

			// Send SOAP Message to SOAP Server
			SOAPMessage soapRequest = createSOAPRequest(soapEndpointUrl, informe);
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

	private SOAPMessage createSOAPRequest(String soapAction, InformeRendimentos informe) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		createSoapEnvelope(soapMessage, informe);

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("Authorization", "Basic ");
		headers.addHeader("SOAPAction", soapAction);
		headers.addHeader("Content-Type", "text/xml");
		headers.addHeader("Host", "10.0.0.223");
		headers.addHeader("Connection", "keep-alive");

		soapMessage.saveChanges();

		return soapMessage;
	}

	private void createSoapEnvelope(SOAPMessage soapMessage, InformeRendimentos informe) throws SOAPException {
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
		prdir.addTextNode("C:\\Senior\\Vetorh\\Relatorios\\informe");

		// QName execfmtName = new QName("prExecFmt", "parameters");
		SOAPElement execfmt = parameters.addChildElement("prExecFmt");
		execfmt.addTextNode("tefFile");

		// QName saveFormatName = new QName("prSaveFormat", "parameters");
		SOAPElement saveFormat = parameters.addChildElement("prSaveFormat");
		saveFormat.addTextNode("tsfPDF");

		returnvalue = "Informe_Rendimentos_" + informe.getUsuario() + "_" + informe.getDataIni();

		// QName fileNametName = new QName("prFileName", "parameters");
		SOAPElement fileName = parameters.addChildElement("prFileName");
		fileName.addTextNode(returnvalue);

		// QName entranceIsXMLName = new QName("prEntranceIsXML", "parameters");
		SOAPElement entranceIsXML = parameters.addChildElement("prEntranceIsXML");
		entranceIsXML.addTextNode("F");

		// QName relatorioName = new QName("prRelatorio", "parameters");
		SOAPElement relatorio = parameters.addChildElement("prRelatorio");
		relatorio.addTextNode("FPIN001.ANU");

		String cdataString = "<![CDATA[<EAnoBase=" + informe.getDataIni() + "><EDatEnt=" + informe.getDataFim()
				+ "><ETipNot=0><ELisAut=1>"
				+ "<EConIrf=T><EConLim=N><ERenAci=0><ECon13s=N><EGraTab=N><ENivOrd=0><EEndFon=0>"
				+ "<ELisEve=N><ESomTit=N><E13sCpl=A><EDesRee=P><EAbrEmp=1><EAbrCad=" + informe.getUsuario() +">]]>";

		
		// QName entradaName = new QName("prEntrada", "parameters");
		SOAPElement entrada = parameters.addChildElement("prEntrada");
		entrada.addTextNode(cdataString);
		// CDATASection cdata =
		// entrada.getOwnerDocument().createCDATASection(cdataString);
		// entrada.appendChild(cdata);

	}

}
