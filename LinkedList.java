import java.util.*;
public class DList implements Iterable<String>{

    private static class Node {
        private String data;
        public Node next;
        public Node prev;
    }
    //private Node head;
    //private Node tail;
    private int size;
    private Node nil; //head and tail of the List

    public DList(){
        nil = new Node();
        nil.next = nil;
        nil.prev = nil;
        nil.data = null;
        size = 0;
        //head = nil;
        //tail = nil;
    }
    
    public void addFirst(String elem){
        Node newNode = new Node();
        newNode.data = elem; //store the data
        newNode.prev = nil; //newNode prev is nil
        newNode.next = nil.next; //newNode's next is nil's next 
        nil.next.prev = newNode; //nil's next's node prev is newNode
        nil.next = newNode; //nil's next is newNode now
        size++; // increment the size
    }
    public void addLast(String elem){
        Node newNode = new Node();
        newNode.data = elem; //stores the data
        newNode.next = nil; //newNode next is nil
        newNode.prev = nil.prev; //newNOde prev is nil's prev
        nil.prev.next = newNode; //nil's prev's nodes next is newNode
        nil.prev = newNode; //nil's prev is newNode now
        size++; //increment the size
    }
    public String getFirst(){
        return nil.next.data; //retun's the data of the first node which is nil's next
    }
    public String getLast(){
        return nil.prev.data; //return's the data of the last node which is node's prev
    }
    public String removeFirst(){
        String data = nil.next.data; //stores the data of the node next to nil
        nil.next = nil.next.next; //nil's next node is nil's next's next
        size--; //decrement size 
        return data; //return the data of the node which is being removed
    }
    public String removeLast(){
        String data = nil.prev.data;
        nil.prev = nil.prev.prev;
        size--;
        return data;
    }
    public String get(int index){
        if(index < 0 || index >= size){ //if index doesn't exist then throe Expection
            throw new IndexOutOfBoundsException("index does not exist");
        }

        Node curr; //new node
        if(index < size / 2){ //if index is in the first half
            curr = nil.next; //start from the beginning
            for(int i = 0; i< index; i++){ // loop through the List from the start
                curr = curr.next; //current get's assigned the next value(next node)
            }
        }else{ // else start form the end of the List
            curr = nil.prev; // last node is current now
            for(int i = size-1; i > index; i--){ //reverse loop
                curr = curr.prev;
            }
        }
        return curr.data; //when found data is returned

    }
    public String set(int index, String value){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException(); //error checking
        }
        Node curr = nil.next; //nil's next is the curr node
        for(int i = 0; i < index; i++){ //looping through the List until we hit the index
            curr = curr.next;
        }
        String oldVal = curr.data;
        curr.data = value;
        return oldVal;
    }
    //checks if the List contains the node or not
    public boolean contains(Object o){ 
        if(!(o instanceof String)){
            return false;
        }
        if(indexOf(o) != -1){
            return true;
        }
        return false;
    }
    //returns the size
    public int size(){ return size; }
    
    //searching through the list and returning the index, if obj doesn't exist return -1
    public int indexOf(Object o){
        if(!(o instanceof String)){
            return -1;
        }
        String str = (String)o;
        Node iter = nil.next;
        int i = 0;
        while(iter != nil){
            if(iter.data.equals(str)){
                return i;
            }
            iter = iter.next;
            i++;
        }
        return -1;
    }
    // ListIterator method for our class
    private class DListIterator implements Iterator<String>{
        private Node pointer;

        public DListIterator (){
            if(nil.next == nil){
                pointer = nil;
            }else{
                pointer = nil.next;
            }
        }
        public boolean hasNext(){
            return pointer != nil;
        }
        public String next(){
            String newData = pointer.data;
            pointer  = pointer.next;
            return newData;
        }

    }
    @Override
    public Iterator<String> iterator() {
        return new DListIterator();
    }
    }
