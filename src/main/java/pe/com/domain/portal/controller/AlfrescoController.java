package pe.com.domain.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.domain.portal.repository.AlfrescoRepository;
import pe.gob.sernanp.alfresco.bean.BeanFile;

@RestController
@RequestMapping("/api")
public class AlfrescoController {

	@Autowired
	private AlfrescoRepository alfrescoRepository;
	
	@GetMapping( value = "/alfresco", produces = "application/json")
	public List<BeanFile> getAlfresco() {
		
		return alfrescoRepository.getDocuments("caleb").getNodes();
	}
	
}
