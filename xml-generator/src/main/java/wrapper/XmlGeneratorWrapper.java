package wrapper;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import minderengine.*;
import xmlservices.XMLGenerationService;

public abstract class XmlGeneratorWrapper extends Wrapper {

  private boolean isRunning = false;
  private ArrayList<XMLWorker> xmlWorkerList;
  private LinkedBlockingQueue<byte[]> requestQueue;
  private final int NUMBER_OF_WORKER_THREAD = 5;

  private SUTIdentifiers sutIdentifiers;

  public XmlGeneratorWrapper() {
    sutIdentifiers = new SUTIdentifiers();
    SUTIdentifier identifier = new SUTIdentifier();
    identifier.setSutName("XML Generator");
    sutIdentifiers.getIdentifiers().add(identifier);
  }

  public byte[] take() throws InterruptedException {
    return requestQueue.take();
  }

  @Override
  public void startTest(StartTestObject sto) {
    isRunning = true;

    requestQueue = new LinkedBlockingQueue<byte[]>();

    xmlWorkerList = new ArrayList<XMLWorker>();
    for (int i = 0; i < NUMBER_OF_WORKER_THREAD; i++) {
      XMLWorker xmlWorker = new XMLWorker(this);
      Thread thread = new Thread(xmlWorker);
      thread.start();
      xmlWorkerList.add(xmlWorker);
    }
  }

  @Override
  public void finishTest(FinishTestObject fto) {
    isRunning = false;

    for (int i = 0; i < xmlWorkerList.size(); i++) {
      xmlWorkerList.get(i).terminate();
    }
  }

  @Signal
  public abstract void xmlProduced(byte[] generatedXML);


  @Slot
  public void generateXML(byte[] inputBooks) {
    if (!isRunning)
      throw new MinderException(MinderException.E_SUT_NOT_RUNNING);

    System.out.println("Client wants me to generate books");
    requestQueue.add(inputBooks);
  }

  @Override
  public SUTIdentifiers getSUTIdentifiers() {
    return sutIdentifiers;
  }
}
