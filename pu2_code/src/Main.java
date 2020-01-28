import SingleLinkedList.DoubleLinkedList;
import SingleLinkedList.IDoubleLinkedList;
import SingleLinkedList.SingleLinkedListArray;

public class Main {

    public static void main(String[] args){
        IDoubleLinkedList<String> list = new DoubleLinkedList<String>();

        list.add(0,"One");
        list.add(0, "Two");
        list.add(0, "Three");
        list.add(0, "Four");
        list.add(0, "Five");
        System.out.println(list);
        list.add(3, "Seven");
        System.out.println(list);
        list.add(6, "Eight");
        System.out.println(list);
    }
}
