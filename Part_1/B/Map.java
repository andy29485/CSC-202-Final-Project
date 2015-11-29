//Andriy Zasypkin and Jason Tufano
//2015-11-28
//Final Project part 1B

public class Map {
  private Robot   robot;
  private Station stations[];

  public Map() {
    this.robot    = new Robot();
    this.stations = new Station[10];
  }

  public Map(Robot robot) {
    this.robot    = robot;
    this.stations = new Station[10];
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
  public void start() {
    //TODO start tasks
  }

  //Adds item to pickup 'station'
  public void addItem(Item item) {
    //TODO add items
  }

  //Sends unload message to robot
  public void unload() {
    //TODO unload
  }
}
