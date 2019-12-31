

public class Deque<Item> implements Iterable<Item> {
    private class DequeElement<I>{
        private I value;
        private DequeElement<I> prev;
        private DequeElement<I> next;
        public DequeElement(I item, DequeElement<I> prev1, DequeElement<I> next1){
            value = item;
            prev = prev1;
            next = next1;
        }
        public I getValue(){
            return value;
        }
        public void setValue(I newValue){
            value = newValue;
        }
        public DequeElement<I> getNext(){
            return next;
        }
        public void setNext(DequeElement<I> newNext){
            next = newNext;
        }
        public DequeElement<I> getPrev(){
            return prev;
        }
        public void setPrev(DequeElement<I> newPrev){
            prev = newPrev;
        }
    }

    DequeElement<Item> head, tail;
    Integer size;

    // construct an empty deque
    public Deque(){
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the deque
    public int size(){
        return size.intValue();
    }

    // add the item to the front
    public void addFirst(Item item){
        DequeElement<Item> tmpElement = new DequeElement<Item>(item,null,null);
        if(head == null){
            head = tmpElement;
            tail = tmpElement;
        }else{
            head.setPrev(tmpElement);
            tmpElement.setNext(head);
            head = tmpElement;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item){
        DequeElement<Item> tmpElement = new DequeElement<Item>(item,null,null);
        if(tail == null){
            head = tmpElement;
            tail = tmpElement;
        }else{
            tail.setNext(tmpElement);
            tmpElement.setPrev(tail);
            tail = tmpElement;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()){
            return null;
        }
        DequeElement<Item> tmpElement = head;
        if(head == tail){
            head = null;
            tail = null;
        }else{
            head = head.getNext();
        }
        size--;
        tmpElement.setNext(null);
        return tmpElement.getValue();
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()){
            return null;
        }
        DequeElement<Item> tmpElement = tail;
        if(head == tail){
            head = null;
            tail = null;
        }else{
            tail = tail.getPrev();
        }
        size--;
        tmpElement.setPrev(null);
        return tmpElement.getValue();
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){

    }

    // unit testing (required)
    public static void main(String[] args){

    }

}