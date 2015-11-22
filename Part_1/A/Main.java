//Andriy Zasypkin and Jason Tufano
//2015-11-19
//Final Project part 1A

/** Description:
  *   provide a bunch of output for a calc
  *
  * Assumptions:
  *   - 
  */

import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    //create an input reader object
    BufferedReader input
            = new BufferedReader(new InputStreamReader(System.in));
    
    Matrix A = new Matrix();
    A.input(input);
    
    Matrix B = new Matrix();
    for(int i=0; i<1; i++) {
      B.input(input);
      String a = A.toString();
      String b = B.toString();
      System.out.println("===============");
      System.out.printf("|x1|   %s^-1   %s\n",
        a.split("\n")[0], b.split("\n")[0]);
      System.out.printf("|x2| = %s    * %s\n",
        a.split("\n")[1], b.split("\n")[1]);
      System.out.printf("|x3|   %s      %s\n",
        a.split("\n")[2], b.split("\n")[2]);
      System.out.println("===============");
      
      A.inverse().multiply(B).print();
    }
    
    //close stream
    input.close();
    System.exit(0);
  }
}
