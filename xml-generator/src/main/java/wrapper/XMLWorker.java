package wrapper;

import java.util.concurrent.LinkedBlockingQueue;

import minderengine.UserDTO;
import xmlservices.XMLGenerationService;

public class XMLWorker extends XmlGeneratorWrapper implements Runnable{
	private final LinkedBlockingQueue<byte[]> requestQueue;
	
	private boolean isTerminated;
	
	
	public XMLWorker(LinkedBlockingQueue<byte[]> requestQueue) {
		this.isTerminated = false;
		this.requestQueue = requestQueue;
	}

	protected void terminate() {
		isTerminated = true;
	}
	
	@Override
	public void run() {
		while (!isTerminated) {
			byte[] requestMessage=null;
			try {
				requestMessage = requestQueue.take();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			XMLGenerationService xmlService = new XMLGenerationService();
		    byte []generatedXML = xmlService.generateXML(requestMessage);
		    
		    try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    xmlProduced(generatedXML);
		}
	}

	@Override
	public void xmlProduced(byte[] generatedXML) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDTO getCurrentTestUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}
