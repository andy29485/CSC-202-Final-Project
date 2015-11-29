//Andriy Zasypkin and Jason Tufano
//2015-11-27
//Final Project part 1B

public class Robot {
  private Item item;
  private Station  currentStation;

  public Robot() {
    this.items          = null;
    this.currentStation = null;
  }

  public void moveToStation(Station station) {
    this.currentStation = station;
  }

  public boolean pickItem() {
    if(this.currentStation == null || this.item != null) {
      return false;
    }
    return (this.item = this.currentStation.getItem()) != null;
  }

  public boolean putItem() {
    if(this.currentStation == null || this.item == null) {
      return false;
    }
    if(currentStation.maxedOut()) {
      return false;
    }
  }

  public Item getItem() {
    return this.item;
  }
}
