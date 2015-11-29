//Andriy Zasypkin and Jason Tufano
//2015-11-28
//Final Project part 1B

public class Map {
  private Robot            robot;
  private Station          stations[];
  private static final int NUM_STATIONS = 10; //should be 2+actual number
  public Map() {
    this.stations = new Station[NUM_STATIONS];
    this.robot    = new Robot(this.stations[0]); //Robot starts at pickup
    this.stations[0].setLimit(-1);
    this.stations[NUM_STATIONS-1].setLimit(-1);

    //set station names
    this.stations[0].setName("Pickup");
    this.stations[0].setName("Refrigeration Container");
    for(int i=1; i<NUM_STATIONS-1; i++) {
      this.stations[i].setName(String.format("Station %02d", i);
    }
  }

  public Map(Robot robot) {
    this.robot    = robot;
    this.stations = new Station[NUM_STATIONS];
    this.stations[0].setLimit(-1);
    this.stations[NUM_STATIONS-1].setLimit(-1);

    //set station names
    this.stations[0].setName("Pickup");
    this.stations[0].setName("Refrigeration Container");
    for(int i=1; i<NUM_STATIONS-1; i++) {
      this.stations[i].setName(String.format("Station %02d", i);
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
  public boolean start() {
    //Go to station if not there
    if(robot.getStation() != this.stations[0])
      robot.moveToStation(this.stations[0]);

    //Can't do much is robot does not have an item
    if(!robot.pickItem() && robot.getItem() == null)
      return false;

    //Place holder
    //  note: this will be the station id that the robot goes to
    //        with the default # of station this should be in range [1-8]
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

      //Go to station
      robot.moveToStation(this.station[station_num]);
    } while(!robot.putItem());

    //Go the the pickup station, this is the robot's 'base'
    //  this way, at the end of the day the robot is in his 'base'
    robot.moveToStation(this.stations[0]);
  }

  //Adds item to pickup 'station'
  public void addItem(Item item) {
    if(!this.stations[0].addItem(item))
      throw new RuntimeException("pickup does not want to accept more items");
  }

  //Sends unload message to robot
  public void unload() {
    System.out.println("Unloading");
    if(robot.getItem() == null) {
      robot.moveToStation(this.stations[5]);
      robot.pickItem();
    }
    while(robot.getItem() != null) {
      robot.moveToStation(this.stations[NUM_STATIONS-1]);//move to unload station
      if(!robot.putItem())
        throw new RuntimeException("robot could not unload item");
      robot.moveToStation(this.stations[5]);
      robot.pickItem();
    }
    System.out.println("Done Unloading");
  }
}
