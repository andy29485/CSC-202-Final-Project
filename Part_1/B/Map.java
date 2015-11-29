//Andriy Zasypkin and Jason Tufano
//2015-11-28
//Final Project part 1B

public class Map {
  private Robot            robot;
  private Station          stations[];
  private Station          pickup;
  private Station          unload;
  private static final int NUM_STATIONS = 9; //should be 1+actual number
                                             //  this way station 0 is included
  public Map() {
    this.stations = new Station[NUM_STATIONS];
    this.robot    = new Robot(this.pickup); //Robot starts at pickup
    this.pickup   = new Station();
    this.unload   = new Station();

    this.pickup.setLimit(-1);
    this.unload.setLimit(-1);
    this.stations[8].setLimit(-1);

    //set station names
    this.pickup.setName("Pickup");
    this.unload.setName("Refrigeration Container");
    for(int i=0; i<NUM_STATIONS; i++) {
      this.stations[i].setName(String.format("Station %02d", i));
    }
  }

  //Main part(so to speak) - will be looped in actual main
  //robot should:
  //  1) go to pickup station - if not there
  //  2) pick up item
  //  3) figure out which station it goes in
  //  4) go to that station
  //  5) place item
  //     - if item was not placed, pick a new station and go to step #4
  //  6) return to pickup station? - not sure if this is needed
  //
  //will return false when no items left in pickup station
  public boolean start() {
    //Go to station if not there
    if(robot.getStation() != this.pickup)
      robot.moveToStation(this.pickup);

    //Can't do much is robot does not have an item
    if(!robot.pickItem() && robot.getItem() == null)
      return false;

    //Place holder
    //  note: this will be the station id that the robot goes to
    //        with the default # of station this should be in range [0-8]
    //  note: in the loop - if this value is not -1, that means that the
    //        number that is currently being stored, is the id of a station
    //        in which the robot COULD NOT place an item in, therefore the
    //        robot should chose a DIFFENT number station
    int station_num = -1;

    do {
      int     item_id     = robot.getItem().getID();
      double  item_temp   = robot.getItem().getTemp();
      double  item_mass   = robot.getItem().getMass();
      //TODO figure out which station to go to
      //  and set station_num to that number(range [0-8])

      //Go to station
      robot.moveToStation(this.stations[station_num]);
    } while(!robot.putItem());

    //Go the the pickup station, this is the robot's 'base'
    //  this way, at the end of the day the robot is in his 'base'
    robot.moveToStation(this.pickup);
    return true;
  }

  //Adds item to pickup 'station'
  public void addItem(Item item) {
    if(!this.pickup.addItem(item))
      throw new RuntimeException("pickup does not want to accept more items");
  }

  //Sends unload message to robot
  public void unload() {
    this.unload(5);//default unload station is 5
  }

  public void unload(int station) {
    if(station < -1 || station >= this.stations.length)
      throw new ArrayIndexOutOfBoundsException(
        String.format("valid stations [-1, %d]",this.stations.length-1));
    System.out.println("Unloading");
    if(robot.getItem() == null) {
      robot.moveToStation(this.stations[station]);
      robot.pickItem();
    }
    while(robot.getItem() != null) {
      robot.moveToStation(this.unload);//move to unload station
      if(!robot.putItem())
        throw new RuntimeException("robot could not unload item");
      if(station == -1)
        robot.moveToStation(this.pickup);
      else
        robot.moveToStation(this.stations[station]);
      robot.pickItem();
    }
    System.out.println("Done Unloading");
  }
}
