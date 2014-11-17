package sampleMinderWrapper;

import minderengine.MinderException;
import minderengine.Signal;
import minderengine.Signal.ConnectionType;
import minderengine.Slot;
import minderengine.Wrapper;

public abstract class MyWrapper extends Wrapper {
  
  private boolean isRunning = false;
  
  @Override
  public void start() {
    isRunning = true;
  }
  
  @Override
  public void stop() {
    isRunning = false;
  }

  @Signal
  public abstract void deliverMessageToGateway(byte []as4message, byte []partyId);

  @Signal
  public abstract void deliverMessageToConnector(byte []userMessage);
  
  @Slot
  public void submitMessage(byte []userMessage){
    if(!isRunning)
      throw new MinderException(MinderException.E_SUT_NOT_RUNNING);
    //submit message to the SUT
    String []umandAs4 = new String(userMessage).split("\\|");
    //now emit message deliverMessageToGateway
    String as4Message = umandAs4[0];
    String partyId = umandAs4[1];
    //
    this.deliverMessageToGateway(as4Message.getBytes(), partyId.getBytes());
  }

  @Slot
  public void receiveAs4Message(byte []as4Message, byte []partyId){
    if(!isRunning )
      throw new MinderException(MinderException.E_SUT_NOT_RUNNING);
    String userMessage = new String(as4Message) + "|" + new String(partyId);
    this.deliverMessageToConnector(userMessage.getBytes());
  }
  
  
  @Slot
  public String provideInfo(){
    return "This is my wrapper";
  }
}
