//Andriy Zasypkin and Jason Tufano
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

	public String getStrID() {
		return String.format("%05d", this.id);
	}

	public int getMSD() {
		return this.getStrID().charAt(0) - '0';
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
