package xmltest;

import org.junit.Test;

import xmlservices.XMLGenerationService;

public class XMLTests {
	
	@Test
	public void generateXML() {
		XMLGenerationService xmlService = new XMLGenerationService();
		
		xmlService.generateXML(null);
	}

}
