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
  *   - The 8 normal stations will be labeled 1-8,
  *       the pickup station  will be labeled 0
  *       the unload station  will be labeled 9
  *   - The "nearest station" will be interpretted as the lowest number station
  *   - The priority goes refrigeration-->weight-->left/right side
  *   - Serial numbers marked with an MSB of 5 indicates refrigeration, serial
  *   	  numbers marked with an MSB of 7 indicates a weight lower than 80kg.
  *   - Temperature is measured in fahrenheit - as doubles
  */

public class Main {
  public static void main(String[] args) {
    Map map = new Map();
    
    System.exit(0);
  }
}
