//Andriy Zasypkin and Jason Tufano
//2015-11-27
//Final Project part 1B

public class Station {
  //Creates an ItemList, an integer limit, and a String name for the object
  private ItemList  items;
  private int       limit;
  private String    name;

  //Constructor, sets default values for class variables
  public Station() {
    this.items = new ItemList();
    this.limit = 8;
    this.name  = "";
  }

  //Adds the input Item to the station if there is room
  public boolean addItem(Item item) {
    if(!this.maxedOut()) {
      this.items.append(item);
      return true;
    }
    return false;
  }

  //Returns and removes the item at the top of the ItemList
  public Item getItem() {
    if(this.items.size() == 0) {
      return null;
    }
    return this.items.pop();
  }

  //Returns true if there is no more space in the station
  public boolean maxedOut() {
    return this.items.size() >= this.limit && this.limit != -1;
  }

  //Sets the limit for the stations capacity
  public void setLimit(int limit) {
    this.limit = limit;
  }

  //Returns the current capacity limit
  public int getLimit() {
    return this.limit;
  }

  //Sets the name of the station
  public void setName(String name) {
    this.name = name;
  }

  //Returns the name of the station
  public String getName() {
    return this.name;
  }
}
