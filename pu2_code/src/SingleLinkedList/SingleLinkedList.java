package SingleLinkedList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleLinkedList<E> implements ISingleLinkedList<E> {

    Node<E>[] nodes;
    Node<E> head;
    Node<E> tail;
    int size;

    public SingleLinkedList(){
        nodes = new Node[10];
        head = null;
        tail = null;
        size = 0;
    }

    private class Node<E> {
        public Node(E mElement, Node mNextNode){
            this.mElement = mElement;
            this.mNextNode = mNextNode;
        }
        E mElement;
        Node mNextNode;
    }

    public void add(E element){

        //special case of empty list
        if (this.size() == 0){
            Node newNode = new Node(element, null);
            this.head = newNode;
            this.tail = newNode;
            this.size++;
        }

        //special case if array is full
        if (this.size() == nodes.length) {
            expand();
            Node newNode = new Node(element, null);
            this.tail = newNode;
            this.size++;
        }
        //normal case: list is not empty, array is not full
        else {
            Node newNode = new Node(element, null);
            this.tail = newNode;
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

    public void add(int index, E element) throws IndexOutOfBoundsException {

        //special case of empty list
        if (this.size() == 0){
            Node newNode = new Node(element, null);
            this.head = newNode;
            this.tail = newNode;
            this.size++;
        }

        //throw exception if trying to insert an element at an index position which does not exist
        if (index >= this.size()) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds for index " + index +". List max index is " + (this.size() - 1) + ".");
        }

        //special case if array is full
        if (this.size() == nodes.length) {
            expand();
            Node newNode = new Node(element, null);
            this.tail = newNode;
            this.size++;
        }

        //create a subarray of all Nodes to be moved and then move them one step to the right
        int indexPosition = index + 1;
        Node[] subArray = Arrays.copyOfRange(nodes, index, (nodes.length - 1));
        int position = 0;
        for (Node node : subArray){
            nodes[indexPosition] = subArray[position];
            indexPosition++;
            position++;
        }
        nodes[index].mElement = element;
        nodes[index].mNextNode = nodes[index + 1];
        this.size++;
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
        for (int i = 0; i < this.size - 1 ; i++){
            if (nodes[i].mElement.equals(element)){
                return i;
            }
        }
        return -1;
    }

    public E remove(int index) throws IndexOutOfBoundsException{
        //throw exception if trying to remove an element at an index position which does not exist
        if (index >= this.size()) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds for index " + index +". List max index is " + (this.size() - 1) + ".");
        }
        //save node element for returning
        E tmp = (E) nodes[index].mElement;

        //move all nodes and fix pointers at point of removal and end of list
        for (int i = index; i < this.size; i++){
            nodes[i] = nodes[i + 1];
        }
        nodes[index - 1].mNextNode = nodes[index];
        nodes[this.size - 1] = null;
        nodes[this.size - 2].mNextNode = null;
        size--;

        return tmp;
    }

    public E set(int index, E element) throws IndexOutOfBoundsException{
        E tmp = nodes[index].mElement;
        nodes[index].mElement = element;
        return tmp;
    }

    public int size(){
        return size;
    }

    public E[] toArray(){
        Object[] tmp = new Object[size];
        for (int i = 0; i < size; i++){
            tmp[i] = nodes[i].mElement;
        }
        //I can't seem to get rid of this cast here. Todo Think more, read more.
        return (E[])tmp;
    }

    @Override
    public String toString(){
        String returnString = "";
        for (Node node : nodes){
            returnString.concat("[" + node.mElement.toString() + "], ");
        }
        return returnString;
    }

}


