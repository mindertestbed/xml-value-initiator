package wrapper;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import minderengine.*;

public abstract class XmlValueInitiatorWrapper extends Wrapper {

  private boolean isRunning = false;
  private ArrayList<XmlValueInitiatorWorker> xmlWorkerList;
  private LinkedBlockingQueue<Integer> requestQueue;
  private final int NUMBER_OF_WORKER_THREAD = 5;

  private  SUTIdentifier sutIdentifier;

  public XmlValueInitiatorWrapper(){
    sutIdentifier = new SUTIdentifier();
    sutIdentifier.setSutName("XML Value Initiator");
  }


  @Override
  public UserDTO getCurrentTestUserInfo() {
    // TODO Auto-generated method stub
    return null;
  }

  public Integer take() throws InterruptedException {
    return requestQueue.take();
  }

  @Override
  public void startTest() {
    isRunning = true;

    requestQueue = new LinkedBlockingQueue<Integer>();

    xmlWorkerList = new ArrayList<XmlValueInitiatorWorker>();
    for (int i = 0; i < NUMBER_OF_WORKER_THREAD; i++) {
      XmlValueInitiatorWorker xmlWorker = new XmlValueInitiatorWorker(this);
      Thread thread = new Thread(xmlWorker);
      thread.start();
      xmlWorkerList.add(xmlWorker);
    }
  }

  @Override
  public void finishTest() {
    isRunning = false;

    for (int i = 0; i < xmlWorkerList.size(); i++) {
      xmlWorkerList.get(i).terminate();
    }
  }

  @Signal
  public abstract void initialDataCreated(byte[] generatedBooksData);

  @Slot
  public void generateBooksData(int nrOfBooks) {
    if (!isRunning)
      throw new MinderException(MinderException.E_SUT_NOT_RUNNING);

    System.out.println("Client Requested #" + nrOfBooks + " from me");
    requestQueue.add(nrOfBooks);
  }


  @Override
  public SUTIdentifier getSUTIdentifier() {
    return sutIdentifier;
  }
}
