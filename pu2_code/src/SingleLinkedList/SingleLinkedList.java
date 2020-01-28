package SingleLinkedList;

/**
 * An implementation of the ISingleLinkedList interface using both Nodes and a backing array of nodes.
 * @param <E>
 * @see ISingleLinkedList
 */
public class SingleLinkedList<E> implements ISingleLinkedList<E>{

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SingleLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    private class Node<E> {
        private E mElement;
        private Node<E> mNextNode;

        private boolean hasNext(){
            return mNextNode != null;
        }

        private Node next(){
            return this.mNextNode;
        }

        private E element(){
            return this.mElement;
        }
    }

    private boolean isEmpty(){
        return size == 0;
    }

    public int size(){return size;}

    public void add(E element){
        if (element == null){
            throw new IllegalArgumentException();
        }

        //create new node and add the element to it
        Node newTail = new Node();
        newTail.mElement = element;

        //sort out pointers
        if (tail != null){
            tail.mNextNode = newTail;
        } else {
            tail = newTail;
            head = newTail;
        }
        tail = newTail;

        size++;
    }

    public void add(int index, E element) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (element == null){
            throw new IllegalArgumentException("Can't add null elements to list");
        }

        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index must not be negative or larger than the size of the list");
        }

        if (size == 0){
            add(element);
        } else {
            Node newNode = new Node();
            newNode.mElement = element;

            Node indexedNode = getNode(index);
            Node tmp = indexedNode.mNextNode;
            indexedNode.mNextNode = newNode;
            newNode.mNextNode = tmp;

            size++;
        }
    }

    //helper method to get the node with a certain index
    private Node getNode(int index){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index must not be negative or larger than the size of the list");
        }

        Node current = head;
        //move through the list to find index
        for (int i = 0; i < index ;i++){
            current = current.mNextNode;
        }
        return current;
    }

    //todo might be enough to just set the references to null to make the GB clean out the list
    public void clear(){
        if (!isEmpty()){
            Node current = head;
            while (current.hasNext()){
                Node tmpNext = current.mNextNode;
                current = null;
                current = tmpNext;
            }
        }
        size = 0;
    }

    public E get(int index) throws IndexOutOfBoundsException {
        //throw exception if trying to access an element at an index position which does not exist
        if (index >= this.size()) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds for index " + index +". List max index is " + (this.size() - 1) + ".");
        }
        return (E)getNode(index).mElement;
    }

    public int indexOf(E element){
        int index = 0;
        Node current = head;
        while (current.hasNext()){
            if (current.mElement.equals(element)){
                return index;
            }
            index++;
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
        E tmp = get(index);
        Node current = getNode(index - 1);
        //just dropping the reference to the indexed node ought to make the GC clean it up
        current.mNextNode = current.next().next();      //don't use getNode to avoid iterating over the nodes again

        return tmp;
    }

    public E set(int index, E element) throws IndexOutOfBoundsException{
        if (isEmpty() || index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node node = getNode(index);
        E tmp = (E)node.mElement;
        node.mElement = element;
        return tmp;
    }

    public E[] toArray(){
        Object[] tmp = new Object[size];
        if (isEmpty()){
            return null;
        }

        Node current = head;
        for (int i = 0; i < size; i++){
            tmp[i] = current.mElement;
            current = current.next();
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
        Node current = head;
        elementString = elementString.concat(current.mElement.toString() + ", ");
        while (current.hasNext()){
            elementString = elementString.concat(current.next().mElement.toString() + ", ");
        }
        //handle the final element, which should not end the same way
        elementString = elementString.concat(current.mElement.toString());

        //concat
        returnString = returnString.concat(elementString);
        //add end bracket
        returnString = returnString.concat("]");

        return returnString;
    }


}


