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
    this.item = this.currentStation.getItem();
    if(this.item == null)
      System.out.printf("Unable to retrieve item from %s\n",
        this.currentStation.getName());
    else
      System.out.printf("Retrieved item from %s: {id:%d, temp:%f, mass:%f}\n",
        this.currentStation.getName(),//TODO
        this.item.getID(),
        this.item.getTemp(),
        this.item.getMass());
    return this.item != null;
  }

  public boolean putItem() {
    if(this.currentStation == null || this.item == null) {
      System.out.printf("%s: unable to palce item\n",
        this.currentStation.getName());
      return false;
    }
    if(currentStation.maxedOut()) {
      System.out.printf("%s: unable to palce item\n",
        this.currentStation.getName());
      return false;
    }
    if(this.currentStation.addItem(this.item)) {
      System.out.printf("%s: placed item: {id:%d, temp:%f, mass:%f}\n",
        this.currentStation.getName(),//TODO
        this.item.getID(),
        this.item.getTemp(),
        this.item.getMass());
      this.item = null;
      return true;
    }
    System.out.printf("%s: unable to palce item\n",
      this.currentStation.getName());
    return false;
  }

  public Item getItem() {
    return this.item;
  }
}
