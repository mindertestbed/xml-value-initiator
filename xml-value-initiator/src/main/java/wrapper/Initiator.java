package wrapper;

import gov.tubitak.minder.client.MinderClientApp;

/**
 * @author: yerlibilgin
 * @date: 26/08/15.
 */
public class Initiator {
  public static void main(String[] args) throws InterruptedException {
    MinderClientApp.main(args);

    Thread.sleep(1000000);
  }
}
