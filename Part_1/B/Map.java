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
}
