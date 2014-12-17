package wrapper;

import java.util.HashMap;

import reportengine.Report;
import reportengine.ReportManager;
import minderengine.MinderException;
import minderengine.Signal;
import minderengine.Signal.ConnectionType;
import minderengine.Slot;
import minderengine.Wrapper;

public abstract class reportGeneratorWrapper extends Wrapper {
  
  private boolean isRunning = false;
  private ReportManager rmg;
  
  @Override
  public void startTest() {
    isRunning = true;
    rmg= new ReportManager();
    
  }
  
  @Override
  public void finishTest() {
    isRunning = false;
  }

 
 
  
  @Slot
  public void setReportTemplate(byte[] template){
    if(!isRunning )
      throw new MinderException(MinderException.E_SUT_NOT_RUNNING);
    
    	rmg.setReportTemplate(template);
    
  }
  
  @Slot
  public void setReportAuthor(String author, String email){
    if(!isRunning)
      throw new MinderException(MinderException.E_SUT_NOT_RUNNING);
    
    rmg.setReportAuthor(author, email);
   
  }

  @Slot
  public void setReportTitle(String title){
    if(!isRunning )
      throw new MinderException(MinderException.E_SUT_NOT_RUNNING);
    rmg.setReportTitle(title);
    
  }
  
  @Slot
  public void setReportData(HashMap<String,String> data){
    if(!isRunning )
      throw new MinderException(MinderException.E_SUT_NOT_RUNNING);
    rmg.setReportData(data);
  }
  
  @Slot
  public byte[] generateReport(){
    return rmg.generateReport();
  }
}
