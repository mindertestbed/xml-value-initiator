package wrapper;

import xmlservices.XMLGenerationService;

public class XMLWorker implements Runnable {
  private boolean isTerminated;
  private XmlGeneratorWrapper xmlGeneratorWrapper;


  public XMLWorker(XmlGeneratorWrapper xmlGeneratorWrapper) {
    this.xmlGeneratorWrapper = xmlGeneratorWrapper;
    this.isTerminated = false;
  }

  protected void terminate() {
    isTerminated = true;
  }

  @Override
  public void run() {
    while (!isTerminated) {
      byte[] requestMessage = null;
      try {
        requestMessage = xmlGeneratorWrapper.take();
      } catch (InterruptedException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

      XMLGenerationService xmlService = new XMLGenerationService();
      byte[] generatedXML = xmlService.generateXML(requestMessage);

      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      System.out.println("Generated xml: ");
      System.out.println(new String(generatedXML));
      xmlGeneratorWrapper.xmlProduced(generatedXML);
    }
  }
}
