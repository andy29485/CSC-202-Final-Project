//Andriy Zasypkin and Jason Tufano
//2015-11-28
//Final Project part 1B

public class Item {
  private Item   next; //This object will be part of a single linked list
  private int    id;

  public Item() {
    this.next = null;
    this.id   = 0;
  }

  public Item(int id) {
    this.next = null;
    this.id   = id;
  }

  //Accessor/Mutator methods
  public void setNext(Item next) {
    this.next = next;
  }

  public Item getNext() {
    return this.next;
  }

  public void setID(int id) {
    this.id = id;
  }

  public int getID() {
    return this.id;
  }
}
