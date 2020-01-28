package SingleLinkedList;

/**
 * An implementation of the IStack interface using {@code Node}s and generics.
 *
 * @author Carl Granström
 * @version 2020-01-28
 * @see IStack
 * @see SingleLinkedList
 * @see ISingleLinkedList
 */
public class Stack<E> extends SingleLinkedList<E> implements IStack<E> {

    /**
     * Executes in constant time.
     */
    public E pop(){
        if (isEmpty()){
            return null;
        }
        E tmp = head.mElement;
        head = head.mNextNode;
        super.size--;

        return tmp;
    }

    /**
     * Executes in constant time.
     */
    public void push(E element){
        if (isEmpty()){
            Node newNode = new Node();
            newNode.mElement = element;
            head = newNode;
            tail = newNode;
            size++;
        } else {
            Node newNode = new Node();
            newNode.mElement = element;
            newNode.mNextNode = head;
            head = newNode;
            size++;
        }
    }

    /**
     * O(n) worst-case, if the element is located last in the list or the item is not contained in the list at all
     * as the method will then have iterated over all elements in the list.
     */
    public int search(E element){
        int distance = -1;
        Node current = head;
        for (int i = 0; i < size; i++){
            if (current.mElement.equals(element)){
                distance = i;
            }
            current = current.next();
        }
        return distance;
    }

    /**
     * Executes in constant time.
     */
    public int size(){
        return size;
    }

    /**
     * Executes in constant time.
     */
    public E top(){
        if (isEmpty()){return null;}
        return head.mElement;
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

        while (!isEmpty()){
            elementString = elementString.concat(pop().toString() + ", ");
        }
        elementString = elementString.substring(0, elementString.length() - 2); //remove the final two characters

        returnString = returnString.concat(elementString);  //concatenate startbracket and the elements
        returnString = returnString.concat("]");            //add end bracket

        return returnString;
    }
}
