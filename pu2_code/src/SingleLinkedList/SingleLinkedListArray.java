package SingleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of the ISingleLinkedList interface using both Nodes and a backing array of nodes.
 * @param <E>
 * @see ISingleLinkedList
 */
public class SingleLinkedListArray<E> implements ISingleLinkedList<E>{

    private Node<E>[] nodes;
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SingleLinkedListArray(){
        nodes = new Node[10];
        head = null;
        tail = null;
        size = 0;
    }

    private class Node<E> {
        E mElement;
        Node mNextNode;

        public Node(E mElement, Node mNextNode){
            this.mElement = mElement;
            this.mNextNode = mNextNode;
        }

        @Override
        public String toString(){
            return this.mElement.toString();
        }
    }

    public void add(E element){

        //special case of empty list
        if (this.size() == 0){
            Node newNode = new Node(element, null);
            this.head = newNode;
            this.tail = newNode;
            this.nodes[size] = newNode;
            this.size++;
        }

        //special case if array is full
        else if (this.size() == nodes.length) {
            expand();
            Node newNode = new Node(element, null);
            this.tail = newNode;
            this.nodes[size] = newNode;
            this.size++;
        }

        //normal case: list is not empty, array is not full
        else {
            Node newNode = new Node(element, null);
            this.tail = newNode;
            this.nodes[size] = newNode;
            this.size++;
        }
    }

    //double the size of the nodes-array and copy all nodes into the new array
    private void expand() {
        Node[] newNodes = new Node[this.nodes.length * 2];
        for (int i = 0; i < nodes.length; i++) {
            //break to avoid copying null nodes
            if (nodes[i] == null){
                break;
            }
            //copy nodes to new array
            newNodes[i] = nodes[i];
        }
        //replace the old array with the new one
        this.nodes = newNodes;
    }

    //used when list shrinks to 1/4 of max size
    private void contract() {
        Node[] newNodes = new Node[this.nodes.length / 2];
        for (int i = 0; i < nodes.length; i++) {
            //break to avoid copying null nodes
            if (nodes[i] == null){
                break;
            }
            //copy nodes to new array
            newNodes[i] = nodes[i];
        }
        //replace the old array with the new one
        this.nodes = newNodes;
    }

    public void add(int index, E element) throws IndexOutOfBoundsException {

        //special case of adding element to index 0 in empty list
        //todo this is tested for in the JUnit tests, but not included in specifications?
        if (isEmpty() && index == 0){
            Node newNode = new Node(element, nodes[index + 1]);
            nodes[index] = newNode;
            size++;
            return;
        }

        //throw exception if trying to insert an element at an index position which does not exist
        if (index > this.size() || index < 0) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds for index " + index +". List max index is " + (this.size() - 1) + ".");
        }

        //grow array if array is full
        if (this.size() == nodes.length) {
            expand();
        }

        //shift nodes to the right
        for (int i = size; i > index; i--){
            nodes[i] = nodes[i - 1];
        }
        Node newNode = new Node(element, nodes[index + 1]);
        nodes[index] = newNode;
        size++;
    }

    public void clear(){
        this.size = 0;
        this.tail = null;
        this.head = null;
        this.nodes = new Node[10];
    }

    public E get(int index) throws IndexOutOfBoundsException {
        //throw exception if trying to access an element at an index position which does not exist
        if (index >= this.size()) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds for index " + index +". List max index is " + (this.size() - 1) + ".");
        }
        return nodes[index].mElement;
    }

    public int indexOf(E element){
        for (int i = 0; i < this.size; i++){
            if (nodes[i].mElement.equals(element)){
                return i;
            }
        }
        return -1;
    }

    public E remove(int index) throws IndexOutOfBoundsException{
        //throw exception if trying to remove an element at an index position which does not exist
        if (index >= this.size() || index < 0) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds for index " + index +". List max index is " + (this.size() - 1) + ".");
        }
        //save node element for returning
        E tmp = nodes[index].mElement;

        //move all nodes and fix pointers at point of removal and end of list
        for (int i = index; i < this.size; i++){
            nodes[i] = nodes[i + 1];
        }
        nodes[this.size - 1] = null;
        nodes[this.size - 2].mNextNode = null;
        size--;
        //set node pointers of the node before insertion point unless insertion point was index 0
        if (index != 0){
            nodes[index - 1].mNextNode = nodes[index];
        }

        if ( (size > 10) && size < (nodes.length / 4) ){
            contract();
        }

        return tmp;
    }

    public E set(int index, E element) throws IndexOutOfBoundsException{
        if (isEmpty() || index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        E tmp = nodes[index].mElement;
        nodes[index].mElement = element;
        return tmp;
    }

    public int size(){
        return size;
    }

    public E[] toArray(){
        Object[] tmp = new Object[size];
        if (isEmpty()){
            return null;
        }

        for (int i = 0; i < size; i++){
            tmp[i] = nodes[i].mElement;
        }
        //I can't seem to get rid of this cast here. Todo Think more, read more.
        return (E[])tmp;
    }

    @Override
    public String toString(){
        if (isEmpty()){
            return "[]";
        }
        String returnString = "[";

        //concatenate all elements except the final one with the specified formatting
        String elementString = "";
        for (int i = 0; i < size - 1; i++){
            elementString = elementString.concat(nodes[i].mElement.toString() + ", ");
        }
        //handle the final element, which should not end the same way
        elementString = elementString.concat(nodes[size - 1].mElement.toString());

        //concat
        returnString = returnString.concat(elementString);
        //add end bracket
        returnString = returnString.concat("]");

        return returnString;
    }

    private boolean isEmpty(){
        return size == 0;
    }
}


