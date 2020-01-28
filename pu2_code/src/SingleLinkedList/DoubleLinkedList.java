package SingleLinkedList;

/**
 * An implementation of the IDoubleLinkedList interface using {@code Node}s.
 *
 * @author Carl Granström
 * @version 2020-01-28
 * @see ISingleLinkedList
 */
public class DoubleLinkedList<E> implements IDoubleLinkedList<E>{

    protected DoubleNode<E> head;
    protected DoubleNode<E> tail;
    protected int size;

    public DoubleLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    protected class DoubleNode<E> {
        protected E mElement;
        protected DoubleNode<E> mNextNode;
        protected DoubleNode<E> mPrevNode;

        protected boolean hasNext() { return mNextNode != null; }
        protected boolean hasPrev() { return mPrevNode != null; }

        protected DoubleNode next(){
            if (hasNext()){
                return this.mNextNode;
            }
            return null;
        }
        protected DoubleNode prev() {
            if (hasPrev()){
                return this.mPrevNode;
            }
            return null;
        }

        protected E element(){
            return this.mElement;
        }

        @Override
        public String toString(){
            return this.mElement.toString();
        }
    }

    /**
     * Executes in constant time.
     */
    protected boolean isEmpty(){
        return size == 0;
    }

    /**
     * Executes in constant time.
     */
    public int size(){return size;}

    /**
     * Executes in constant time.
     */
    public void add(E element){
        if (element == null){
            throw new IllegalArgumentException("Can't add null elements to list");
        }

        //create new node and add the element to it
        DoubleNode newNode = new DoubleNode();
        newNode.mElement = element;

        //sort out pointers
        if (isEmpty()) {
            tail = newNode;
            head = newNode;
        } else if (size == 1){
            tail = newNode;             //set the new node to be the tail if size is 1
            newNode.mPrevNode = head;   //set the new node's prev to be the head
            head.mNextNode = newNode;   //set the head's next node to be the new node
        } else {
            newNode.mPrevNode = tail;   //set the new node's prev to be the old tail
            tail.mNextNode = newNode;   //set the old tail's next node to be the new node
            tail = newNode;             //set the new node to be the new tail
        }
        size++;
    }

    /**
     * O(n) worst-case.
     *
     * TODO logic could be cleaner, not enamoured of all the if-statements
     */
    public void add(int index, E element) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (element == null){
            throw new IllegalArgumentException("Can't add null elements to list");
        }

        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index must not be negative or larger than the size of the list");
        }
        //create the new node and assign in the element
        DoubleNode newNode = new DoubleNode();
        newNode.mElement = element;

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else if (size == 1){
            //if adding element at index 0 in a list with a single node, new node becomes head
            if (index == 0) {
                newNode.mNextNode = head;
                head.mPrevNode = newNode;
                head = newNode;
            } else {    //adding at the end of a list with a single node, new node becomes tail
                newNode.mPrevNode = head;
                head.mNextNode = newNode;
                tail = newNode;
            }
        } else {
            if ( index == 0) {
                newNode.mNextNode = head;
                head.mPrevNode = newNode;
                head = newNode;
            } else if (index == size) {
                tail.mNextNode = newNode;
                newNode.mPrevNode = tail;
                tail = newNode;
            } else {
                DoubleNode oldIndex = getNode(index);
                DoubleNode tmpPrev = oldIndex.mPrevNode;
                //redo all pointers at insertion point
                tmpPrev.mNextNode = newNode;
                newNode.mPrevNode = tmpPrev;
                newNode.mNextNode = oldIndex;
                oldIndex.mPrevNode = newNode;
            }
        }
        size++;
    }

    /**
     * Helper method to get the {@code Node} of a certain index position.
     * O(n) worst-case, if the {@code Node} is located last in the list.
     *
     * TODO Could be made more effective to search from the end if index position is larger than size/2.
     */
    private DoubleNode getNode(int index){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index must not be negative or larger than the size of the list");
        }

        DoubleNode current = head;

        //move through the list to find index
        for (int i = 0; i < index ; i++){
            current = current.mNextNode;
        }
        return current;
    }

    public void clear(){
        if (!isEmpty()){
            DoubleNode current = head;
            while (current.hasNext()){
                DoubleNode tmpNext = current.mNextNode;
                current = tmpNext;      //hopefully by removing all references to the old node the GC will remove them
            }
        }
        size = 0;
    }

    /**
     * O(n) worst-case, if the {@code Node} is located last in the list. This is because it uses the O(n) helper method
     * {@code getNode()} to get the {@code Node} containing the element.
     */
    public E get(int index) throws IndexOutOfBoundsException {
        //throw exception if trying to access an element at an index position which does not exist
        if (index >= this.size()) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds for index " + index +". List max index is " + (this.size() - 1) + ".");
        }
        DoubleNode node = getNode(index);
        return (E)node.mElement;
    }

    /**
     * O(n) worst-case, if the element is located last in the list.
     */
    public int indexOf(E element){
        DoubleNode current = head;
        for (int i = 0; i < size; i++){
            if (current.mElement.equals(element)){
                return i;
            }
            if (current.hasNext()) {
                current = current.next();
            }
        }
        return -1;
    }

    /**
     * O(n) worst-case, if the element is located first in the list.
     */
    public int lastIndexOf(E element){

        if (isEmpty()){ return -1; }    //return sentinel value if list is empty
        DoubleNode current = tail;
        for (int i = size - 1; i >= 0; i--){
            if (current.mElement.equals(element)){
                return i;
            }
            if (current.hasPrev()){
                current = current.mPrevNode;
            }
        }

        return -1;
    }

    /**
     * O(n) worst-case, if the element is located last in the list. This is because it uses the O(n) helper method
     * {@code getNode()} to get the {@code Node} containing the element.
     */
    public E remove(int index) throws IndexOutOfBoundsException{
        //throw exception if trying to remove an element at an index position which does not exist
        if (index >= this.size() || index < 0) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds for index " + index +". List max index is " + (this.size() - 1) + ".");
        }
        //save node element for returning
        E tmp = get(index);

        //special case for removing the head node
        if (index == 0) {
            if (head.hasNext()){        //if list is larger than one, orphan the old head node to make GC clean it up
                head = head.mNextNode;
                head.mPrevNode = null;  //remove reference to old head
            } else {                    //if head is the only node it is also tail: remove both references from the list
                head = null;
                tail = null;
            }
        }
        //special case for removing the tail
        else if (index == size - 1){
            if (tail.hasPrev()){        //if list is larger than one, orphan the old tail for GC clean-up
                tail = tail.mPrevNode;
                tail.mNextNode = null;  //remove reference to old tail
            } else {                    //if tail is the only node it is also head: remove both references from the list
                tail = null;
                head = null;
            }
        } else {
            DoubleNode current = getNode(index - 1);
            current.mNextNode = current.next().next();      //don't use getNode to avoid iterating over the nodes again
            current.mNextNode.mPrevNode = current;          //set the prev-reference of the node at position index+1
        }
        size--;

        return tmp;
    }

    /**
     * O(n) worst-case, if the element is located last in the list. This is because it uses the O(n) helper method
     * {@code getNode()} to get the {@code Node} containing the element.
     */
    public E set(int index, E element) throws IndexOutOfBoundsException{
        if ( (isEmpty() && index != 0) || index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        DoubleNode node = getNode(index);
        E tmp = (E)node.mElement;
        node.mElement = element;
        return tmp;
    }

    /**
     * Worst- and best-case O(n), because it always needs to iterate over the whole list.
     */
    public E[] toArray(){
        Object[] tmp = new Object[size];
        if (isEmpty()){
            return null;
        }

        DoubleNode current = head;
        for (int i = 0; i < size; i++){
            tmp[i] = current.mElement;
            current = current.next();
        }
        //I can't seem to get rid of this cast here. Todo Think more, read more.
        return (E[])tmp;
    }

    /**
     * Worst- and best-case O(n), because it always needs to iterate over the whole list.
     */
    @Override
    public String toString(){
        if (isEmpty()){
            return "[]";
        }
        String returnString = "[";

        //concatenate all elements except the final one with the specified formatting
        String elementString = "";
        DoubleNode current = head;
        while (current.hasNext()){
            elementString = elementString.concat(current.mElement.toString() + ", ");
            current = current.mNextNode;
        }
        elementString = elementString.concat(current.mElement.toString()); //remove the final two characters

        returnString = returnString.concat(elementString);  //concatenate startbracket and the elements
        returnString = returnString.concat("]");            //add end bracket

        return returnString;
    }
}