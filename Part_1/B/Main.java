//Andriy Zasypkin and Jason Tufano
//2015-11-19
//Final Project part 1B

//TO-DO: Edit Anwari grammar (and possibly spelling errors)
//TO-DO: Figure out what the hell the las sentence means
/** Description:
  *   Write a program to simulate the operation of a simple robot. The robot moves in four
  *   directions: forward, backward, right, and left. The job of the robot is to move items
  *   and place it in the right slots in each station. There are 8 stations plus the pick up
  *   station. Pick up station is the initial start where the robot picks the items. 8 items
  *   can be placed on each station. The nearest station must be filled before placing items
  *   on the next stations. The items are marked with identification or serial numbers. The
  *   items with even serial numbers go to the right and items with odd serial numbers go to
  *   the left. The last slot, number 7, is reserved for light items which are less than 80 kg.
  *   The serial number is a five digit number, the MSB digit 5 indicates that the product must
  *   be placed in the fifth station which is keeping the product at 20 degrees F. By receiving
  *   the message of unload, the machine should start emptying the fifth station and place the
  *   items in the refridgeration container until it is empty.
  *
  * Assumptions:
  *   - The robot does not need to turn
  *   - The 8 stations will be labeled 0-7, the pick-up station will be labeled [something]
  *   - The "nearest station" will be interpretted as the lowest number station
  *   - The priority goes refridgeration-->weight-->left/right side
  *   - Serial numbers marked with an MSB of 5 indicates refridgeration, serial numbers marked
  *   	with an MSB of 7 indicates a weight lower than 80kg.
  */

public class Main {
  public static void main(String[] args) {
    
    System.exit(0);
  }
}
