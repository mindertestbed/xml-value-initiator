package wrapper;

import gov.tubitak.minder.client.MinderClientApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: yerlibilgin
 * @date: 26/08/15.
 */
public class Initiator {
  public static void main(String[] args) throws InterruptedException, IOException {
    System.out.println("jenerator");
    MinderClientApp.main(args);


    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line = null;
    while ((line = br.readLine()) != null) {
      System.out.println("Input command " + line);
      if ("quit".equalsIgnoreCase(line)) {
        System.exit(0);
      }
    }
  }
}
