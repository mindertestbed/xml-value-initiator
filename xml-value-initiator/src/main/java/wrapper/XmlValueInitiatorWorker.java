package wrapper;

import java.io.IOException;

import xmlservices.XMLValueInitiatorService;

public class XmlValueInitiatorWorker implements Runnable{
	private boolean isTerminated;
	private XmlValueInitiatorWrapper xmlValueInitiatorWrapper;
	
	
	public XmlValueInitiatorWorker(XmlValueInitiatorWrapper xmlValueInitiatorWrapper) {
		this.xmlValueInitiatorWrapper = xmlValueInitiatorWrapper;
		this.isTerminated = false;
	}

	protected void terminate() {
		isTerminated = true;
	}
	
	@Override
	public void run() {
		while (!isTerminated) {
			int requestedNrOfBooks=0;
			try {
				requestedNrOfBooks = xmlValueInitiatorWrapper.take();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			XMLValueInitiatorService xmlService = new XMLValueInitiatorService();
		    byte[] generatedBooksData=null;
			try {
				generatedBooksData = xmlService.getSampleBooksData(requestedNrOfBooks);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
		    try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    xmlValueInitiatorWrapper.initialDataCreated(generatedBooksData);
		}
	}
}
