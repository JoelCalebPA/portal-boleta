package pe.com.domain.portal.repository;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import pe.gob.sernanp.alfresco.bean.RptaBean;
import pe.gob.sernanp.alfresco.caller.CallServiceRest;

@Repository("alfrescoRepository")
public class AlfrescoRepository {

	private static final Logger log = LoggerFactory.getLogger(AlfrescoRepository.class);

	private static final String HOST = "207.246.78.28";
	private static final String PORT = "8080";
	private static final String USER = "admin";
	private static final String PASS = "Alfr3sc02020";

	public RptaBean getDocuments(String user) {
		
		log.info("getDocuments() for user: {}", user);

		HashMap<String, String> metadatos = new HashMap<String, String>();
		metadatos.put("boleta:usuario", user);

		String rutaInterna = null;
		String palabras = null;
		String frase = null;
		String paraIgnorar = null;
		String queryLibre = null;
		String tipoDocumental = null;

		try {
			RptaBean rpta = CallServiceRest.ServiceSearch(HOST, PORT, USER, PASS, rutaInterna, palabras, frase,
					paraIgnorar, queryLibre, tipoDocumental, metadatos);

			if (rpta.getCode() != "00000") {
				throw new Exception("Error en el servicio getDocuments()");
			}

			return rpta;
		} catch (Exception e) {
			return null;
		}

	}

	public RptaBean getContent(String uuid) {
		
		log.info("getContent() for document: {}", uuid);

		try {
			RptaBean rpta = CallServiceRest.ServiceDownload(HOST, PORT, USER, PASS, uuid);

			if (rpta.getCode() != "00000") {
				throw new Exception("Error en el servicio getContent()");
			}

			return rpta;
		} catch (Exception e) {
			return null;
		}

	}
	
	public String getContentUrl() {
		return "http://" + HOST + ":" + PORT + "/alfresco/service/api/node/content;cm:content/workspace/SpacesStore/";
	}
	
	public String getTicket() {
		String ticket = "";
		try {
			HttpClient client = new HttpClient();
			PostMethod mPost = new PostMethod("http://" + HOST + ":" + PORT + "/alfresco/s/api/login");
			String jsonDataObject = "{\"username\":\"" + USER + "\",\"password\":\"" + PASS + "\"}";
			mPost.setDoAuthentication(true);
			mPost.setRequestHeader("Content-Type", "application/json");
			mPost.setRequestEntity(new StringRequestEntity(jsonDataObject, "application/json", "UTF-8"));
			int status = client.executeMethod(mPost);
			if (status != HttpStatus.SC_OK) {
				throw new Exception("Error al obtener ticket: " + status);
			} else {
				ByteArrayOutputStream result = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int length;
				while ((length = mPost.getResponseBodyAsStream().read(buffer)) != -1) {
					result.write(buffer, 0, length);
				}
				ticket = result.toString("UTF-8");
				ticket = ticket.substring(ticket.indexOf("ticket") + 9,
						ticket.length() - 6);
			}
			return ticket;
		} catch (Exception e) {
			return null;
		}
	}

}

