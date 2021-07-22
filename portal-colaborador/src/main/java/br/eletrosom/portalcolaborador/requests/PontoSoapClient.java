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

import br.eletrosom.portalcolaborador.entity.FolhaPonto;

public class PontoSoapClient {

	String soapEndpointUrl = "http://10.0.0.223/g5-senior-services/ronda_Synccom_senior_g5_rh_hr_relatorios?wsd";

	String line;
	String response = "";
	String reqresponse;
	String returnvalue = "";

	public String buscarDocumento(FolhaPonto ponto) {

		//String returnvalue = "";
		try {
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();

			// Send SOAP Message to SOAP Server
			SOAPMessage soapRequest = createSOAPRequest(soapEndpointUrl, ponto);
			SOAPMessage soapResponse = soapConnection.call(soapRequest, soapEndpointUrl);
			
			
		//	System.out.println("Requisição: \n" + passarXMLParaString(soapRequest.getSOAPBody().getOwnerDocument(), 4));

			System.out.println("Resposta: \n" + soapResponse.getSOAPBody().getTextContent());
		} catch (Exception e) {
			e.printStackTrace();
			returnvalue = "erro";
		}
		
		return returnvalue;

	}
	
	private  SOAPMessage createSOAPRequest(String soapAction, FolhaPonto ponto) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		createSoapEnvelope(soapMessage,ponto);

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("Authorization", "Basic ");
		headers.addHeader("SOAPAction", soapAction);
		headers.addHeader("Content-Type", "text/xml");
		headers.addHeader("Host", "10.0.0.223");
		headers.addHeader("Connection", "keep-alive");

		soapMessage.saveChanges();


		return soapMessage;
	}
	
	private void createSoapEnvelope(SOAPMessage soapMessage, FolhaPonto ponto) throws SOAPException {
		SOAPPart soapPart = soapMessage.getSOAPPart();

		String myNamespace2 = "ser";
		String myNamespaceURI2 = "http://services.senior.com.br";

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		//envelope.addNamespaceDeclaration(myNamespace1, myNamespaceURI1);
		envelope.addNamespaceDeclaration(myNamespace2, myNamespaceURI2);

		

		// SOAPHeader soapHeaer = envelope.getHeader();

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
	
		SOAPElement soapBodyElem = soapBody.addChildElement("Relatorios","ser");
		
		SOAPElement user = soapBodyElem.addChildElement("user");
		user.addTextNode("Portal");

		SOAPElement senha = soapBodyElem.addChildElement("password");
		senha.addTextNode("Projeto123*");

		SOAPElement encryption = soapBodyElem.addChildElement("encryption");
		encryption.addTextNode("0");

		SOAPElement parameters = soapBodyElem.addChildElement("parameters");
		
		//QName prdirName = new QName("prDir", "parameters");
		SOAPElement prdir = parameters.addChildElement("prDir");
		prdir.addTextNode("C:\\Senior\\Vetorh\\Relatorios\\ponto");
		
	//	QName execfmtName = new QName("prExecFmt", "parameters");
		SOAPElement execfmt = parameters.addChildElement("prExecFmt");
		execfmt.addTextNode("tefFile");

		//QName saveFormatName = new QName("prSaveFormat", "parameters");
		SOAPElement saveFormat = parameters.addChildElement("prSaveFormat");
		saveFormat.addTextNode("tsfPDF");

		returnvalue = "Cartao_Ponto_" + ponto.getUsuario() + "_" + ponto.getMesref();
		
		//QName fileNametName = new QName("prFileName", "parameters");
		SOAPElement fileName = parameters.addChildElement("prFileName");
		fileName.addTextNode(returnvalue);
		
		//QName entranceIsXMLName = new QName("prEntranceIsXML", "parameters");
		SOAPElement entranceIsXML = parameters.addChildElement("prEntranceIsXML");
		entranceIsXML.addTextNode("F");

	//	QName relatorioName = new QName("prRelatorio", "parameters");
		SOAPElement relatorio = parameters.addChildElement("prRelatorio");
		relatorio.addTextNode("HRCP110.APU");

		String cdataString = "<![CDATA[<EDatInR=" + ponto.getDataIni() + "><EDatFiR=" + ponto.getDataFim() +"><EMarAfa=S" + 
				"><EMarFol=S><ETruMar=N><ELisCab=N><ELisDem=S><EAbrGPe=N><EAbrTDc=0>" + 
				"<GRDLOTE=0><EAbrEmp=1><EAbrTcl=1><EAbrCad=" + ponto.getUsuario() + ">]]>";
	//	QName entradaName = new QName("prEntrada", "parameters");
		SOAPElement entrada = parameters.addChildElement("prEntrada");
		entrada.addTextNode(cdataString);
	//	CDATASection cdata = entrada.getOwnerDocument().createCDATASection(cdataString);
	//	entrada.appendChild(cdata);

	}
	
	/*public  String passarXMLParaString(Document xml, int espacosIdentacao) {
		try {
			// set up a transformer
			TransformerFactory transfac = TransformerFactory.newInstance();
			transfac.setAttribute("indent-number", Integer.valueOf(espacosIdentacao));
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			// create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(xml);
			trans.transform(source, result);
			String xmlString = sw.toString();
			return xmlString;
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}*/
	
}
