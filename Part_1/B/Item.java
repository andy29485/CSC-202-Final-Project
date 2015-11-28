//Andriy Zasypkin
//2015-11-28
//Final Project part 1B

public class Item {
  private Item   next; //This object will be part of a single linked list
  private int    id;
  private double temp;
  private double mass;

  public Item() {
    this.next = null;
    this.id   = 0;
    this.temp = 0;
    this.mass = 0;
  }

  public Item(int id) {
    this.next = null;
    this.id   = id;
    this.temp = 0;
    this.mass = 0;
  }

  //adds item pos right after current item
  // return false if it could not
  public boolean insert(Item next) {
    return this.insert(0, next);
  }

  //adds item pos positions after current item
  //returns false if could not add
  public boolean insert(int pos, Item next) {
    if(next == null || pos < 0)
      return false;
    if(pos == 0) {
      next.setNext(this.getNext());
      this.next = next;
      return true;
    }
    if(this.getNext() == null)
      return false;
    return this.getNext().insert(pos-1, next);
  }

  //Adds item to end of the list
  public void append(Item next) {
    if(this.getNext() == null)
      this.setNext(next);
    else
      this.getNext().append(next);
  }

  //removes and returns item from the end of the list
  public Item pop() {
    if(this.getNext() == null) {
      Item next = this.getNext();
      this.setNext(null);
      return next;
    }
    else {
      return this.getNext().pop();
    }
  }

  //returns number of items ahead of current +1(for this item)
  public int size() {
    if(this.getNext() == null)
      return 1;
    else
      return 1 + this.getNext().size();
  }

  /*Deamed not possible - not double linked - cannot access previouse element
   * //removes current item(and returns itself)
   * public Item remove() {
   *   //TODO
   *   return this;
   * }
   */

  //Accessor/Mutator methods
  private void setNext(Item next) {
    //private to decrease errors - use append or insert methods
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

  public void setTemp(double temp) {
    this.temp = temp;
  }

  public double getTemp() {
    return this.temp;
  }

  public void setMass(double mass) {
    this.mass = mass;
  }

  public double getMass() {
    return this.mass;
  }
}
