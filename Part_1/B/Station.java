//Andriy Zasypkin and Jason Tufano
//2015-11-27
//Final Project part 1B

public class Station {
  private ItemList  items;
  private int       limit;
  private String    name;

  public Station() {
    this.items = new ItemList();
    this.limit = 8;
    this.name  = "";
  }

  public boolean addItem(Item item) {
    if(!this.maxedOut()) {
      this.items.append(item);
      return true;
    }
    return false;
  }

  public Item getItem() {
    if(this.items.size() == 0) {
      return null;
    }
    return this.items.pop();
  }

  public boolean maxedOut() {
    return this.items.size() >= this.limit && this.limit != -1;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public int getLimit() {
    return this.limit;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
