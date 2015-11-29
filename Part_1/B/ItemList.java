//Andriy Zasypkin and Jason Tufano
//2015-11-28
//Final Project part 1B

public class ItemList {
  private Item head;
  private Item tail;

  public ItemList() {
    this.head = null;
    this.tail = null;
  }

  public Item get(int index) {
    if(head == null)
      throw new RuntimeException("no elements in list");
    if(index < 0)
      throw new RuntimeException("index can't be negative");

    Item item = head;
    for(int i=0; i<index; i++) {
      if(item.getNext() == null)
        throw new RuntimeException("no such index: "+index);
      item = item.getNext();
    }
    return item;
  }

  public int size() {
    if(head == null)
      return 0;
    int length = 1;
    Item item = head;
    while((item = item.getNext()) != null) length++;
    return length;
  }

  public Item append(Item item) {
    if(tail == null)
      head = item;
    else
      tail.setNext(item);
    return tail = item;
  }

  public Item append() {
    return this.append(new Item());
  }

  public Item insert(int pos, Item item) {
    Item old;

    if((pos == 0 && head == null) || pos == this.size())
      return this.append(item);
    else if(pos == 0)
      old = head;
    else
      old = this.get(pos-1);

    if(pos == 0) {
      item.setNext(old);
      head = item;
    }
    else {
      item.setNext(old.getNext());
      old.setNext(item);
    }
    return item;
  }

  public Item insert(int pos) {
    return this.insert(pos, new Item());
  }

  public Item remove(int index) {
    if(index >= this.size())
      throw new RuntimeException("no such index: "+index);
    if(index < 0)
      throw new RuntimeException("index can't be negative");

    Item tmp;
    if(index == 0) {
      tmp = head;
      head = head.getNext();
      if(head == null)
        tail = null;
    }
    else {
      Item prev = this.get(index-1);
      tmp = prev.getNext();
      prev.setNext(prev.getNext().getNext());
    }
    tmp.setNext(null);
    return tmp;
  }

  //not actually pop, just remove and return head item
  public Item pop() {
    return this.remove(0);
  }
}
