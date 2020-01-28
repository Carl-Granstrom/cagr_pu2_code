import SingleLinkedList.SingleLinkedListArray;

public class Main {

    public static void main(String[] args){
        SingleLinkedListArray<String> list = new SingleLinkedListArray<String>();

        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Three");
        list.add("Three");
        list.add("Three");
        list.add("Three");
        list.add("Three");
        list.add("Three");
        list.add("Three");
        list.add("Three");
        list.add("Three");
        list.add("Three");
        list.add("Three");
        System.out.println(list.size());
        System.out.println(list.toString());
        list.add(2, "Four");
        System.out.println(list.size());
        System.out.println(list);
        list.add(0, "Eight");
        System.out.println(list.size());
        System.out.println(list);
        System.out.println(list.remove(0));
    }
}
