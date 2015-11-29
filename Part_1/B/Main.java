//Andriy Zasypkin and Jason Tufano
//2015-11-19
//Final Project part 1B

//TO-DO: Edit Anwari grammar (and possibly spelling errors)
//TO-DO: Figure out what the hell the last sentence means
/** Description:
  *   Write a program to simulate the operation of a simple robot. The robot
  *   moves in four directions: forward, backward, right, and left. It's job
  *   is to pick up items and place them in the right slots in each station.
  *   There are 10 stations (8 normal, 1 pickup, 1 unload). Robot will start
  *   at the pickup station. Only 8 items can be placed on each station. The
  *   nearest station must be filled before robot can start placing items on
  *   the next stations. The items shall be marked with id (serial) numbers.
  *   The items with even serial numbers go to the right, and items with odd
  *   serial numbers go to the left. The last odd slot, 7, is to be reserved
  *   for light items which are less than 80 kg. The serial number is a five
  *   digit number, the MSD (most significant digit left most base 10 digit)
  *   5 indicates that the product must be placed in the fifth station which
  *   should keep the product at 20 degrees Fahrenheit. When it receives the
  *   message of unload, the machine should start emptying the fifth station
  *   and placing the items in the refrigeration container (station 9) until
  *   it is empty.
  *
  * Assumptions:
  *   - The robot does not need to turn
  *   - The movement will be virtual, and shall be printed
  *   - Picking and placing items shall be printed
  *   - The refrigeration container will be refferd to as the unload station
  *   - The 8 normal stations will be labeled 0-8,
  *       the pickup station  will be labeled -1
  *       the unload station  will be labeled -2
  *   - The "nearest station" will be interpretted as the lowest number station
  *   - The priority goes refrigeration-->weight-->left/right side
  *   - Because 5 and 7 have special purposes, they will not be used to store
  *       ordinary items
  *   - Station 8 will be used for unloading non-refrigerated items
  *   - Serial numbers marked with an MSB of 5 indicates refrigeration, serial
  *   	  numbers marked with an MSB of 7 indicates a weight lower than 80kg.
  *   - Temperature is measured in fahrenheit - as doubles
  *   - Robot can only hold 1 item at a time
  *   - Station 8, not sure what it is, but it was on the diagram
  *       see next assumption
  *   - If stations are full(in said row)
  *       dump into station 8
  *       !except station 5! - that goes to Refrigeration Container
  *   - Directions:
  *     - Backward: to pickup station
  *     - Forward:  to refrigeration container
  *     - Left:     to odd stations
  *     - Right:    to even stations
  *   - Movement: characters are 1 space appart, to be in a station the
  *                 robot must be on said number
  *               see diagarm:
  *     ---------
  *     | 6420  |
  *     |R     P|
  *     | 75318 |
  *     ---------
  */

import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    //Creates a map in which the robot and the stations are located
    Map storeRoom = new Map();
    //Creates an input reader object
    BufferedReader input
            = new BufferedReader(new InputStreamReader(System.in));
    String item_info;

    while((item_info = input.readLine()) != null) {
      Item item = new Item();
      item.setID(Integer.valueOf(item_info));
      storeRoom.addItem(item);
    }

    while(storeRoom.canStart()) {
      storeRoom.start();
      //TODO figure out how unload works into this
    }

    //Closes stream
    input.close();
    System.exit(0);
  }
}
