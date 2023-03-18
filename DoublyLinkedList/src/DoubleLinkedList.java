import java.util.*;

interface ILinkedList {
    /**
     * Inserts a specified element at the specified position in the list.
     * @param index
     * @param element
     */
    public void add(int index, Object element);
    /**
     * Inserts the specified element at the end of the list.
     * @param element
     */
    public void add(Object element);
    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    public Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    public void set(int index, Object element);
    /**
     * Removes all of the elements from this list.
     */
    public void clear();
    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();
    /**
     * Removes the element at the specified position in this list.
     * @param index
     */
    public void remove(int index);
    /**
     * @return the number of elements in this list.
     */
    public int size();
    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */
    public ILinkedList sublist(int fromIndex, int toIndex);
    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    public boolean contains(Object o);
}


public class DoubleLinkedList implements ILinkedList {
    /* Implement your linked list class here*/

    public static class Node
    {
        int element;
        int length;
        Node previous;
        Node next;
    }
    Node head = null;
    Node tail = null;
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        ILinkedList my_list = new DoubleLinkedList();
        Scanner read = new Scanner(System.in);
        String num_string = read.nextLine().replaceAll("\\[|\\]", "");
        String[] nums_array = num_string.split(", ");
        String operation = read.nextLine();
        if (!(nums_array.length == 1 && nums_array[0].isEmpty()))
        {
            for (int i = 0; i < nums_array.length; i++)
            {
                Node node = new Node();
                node.element = Integer.parseInt(nums_array[i]);
                my_list.add(node);
            }
        }

        switch (operation)
        {
            case "size":
                System.out.println(my_list.size());
                break;
            case "add":
                int number_to_be_inserted = read.nextInt();
                Node node = new Node();
                node.element = number_to_be_inserted;
                my_list.add(node);
                System.out.print("[");
                int size_new = my_list.size();
                for (int i = 0; i < size_new; i++)
                {
                    Node element = new Node();
                    element = (Node) my_list.get(i);
                    if (i == size_new - 1)
                    {
                        System.out.print(element.element + "]");
                        System.out.println();
                        break;
                    }
                    System.out.print(element.element + ", ");
                }
                break;
            case "addToIndex":
                int index = read.nextInt();
                int number_to_be_added = read.nextInt();
                if (index < 0 || index > my_list.size() - 1)
                {
                    System.out.println("Error");
                    break;
                }
                Node node1 = new Node();
                node1.element = number_to_be_added;
                node1.length = my_list.size();;
                my_list.add(index, node1);
                System.out.print("[");
                int size = my_list.size();
                for (int i = 0; i < size; i++)
                {
                    Node element = new Node();
                    element = (Node) my_list.get(i);
                    if (i == size - 1)
                    {
                        System.out.print(element.element + "]");
                        System.out.println();
                        break;
                    }
                    System.out.print(element.element + ", ");
                }
                break;
            case "get":
                int index_to_obtain = read.nextInt();
                if (index_to_obtain < 0 || index_to_obtain > my_list.size() - 1)
                {
                    System.out.println("Error");
                    break;
                }
                Node obtained_digit = new Node();
                obtained_digit = (Node) my_list.get(index_to_obtain);
                System.out.println(obtained_digit.element);
                break;
            case "set":
                int index_to_set = read.nextInt();
                int new_value = read.nextInt();
                if (index_to_set < 0 || index_to_set > my_list.size() - 1)
                {
                    System.out.println("Error");
                    break;
                }
                Node node3 = new Node();
                node3.element = new_value;
                node3.length = my_list.size();
                my_list.set(index_to_set, node3);
                System.out.print("[");
                int newsize = my_list.size();
                for (int i = 0; i < newsize; i++)
                {
                    Node element = new Node();
                    element = (Node) my_list.get(i);
                    if (i == newsize - 1)
                    {
                        System.out.print(element.element + "]");
                        System.out.println();
                        break;
                    }
                    System.out.print(element.element + ", ");
                }
                break;
            case "clear":
                my_list.clear();
                System.out.print("[");
                for (int i = 0; i < my_list.size(); i++)
                {
                    Node element = new Node();
                    element = (Node) my_list.get(i);
                    System.out.print(element.element);
                }
                System.out.print("]\n");
                break;
            case "isEmpty":
                if (my_list.isEmpty())
                {
                    System.out.println("True");
                }
                else
                {
                    System.out.println("False");
                }
                break;
            case "remove":
                int index_to_be_removed = read.nextInt();
                if (index_to_be_removed < 0 || index_to_be_removed > my_list.size() - 1)
                {
                    System.out.println("Error");
                    break;
                }
                my_list.remove((index_to_be_removed));
                System.out.print("[");
                int less_size = my_list.size();
                for (int i = 0; i < less_size; i++)
                {
                    Node element = new Node();
                    element = (Node) my_list.get(i);
                    if (i == less_size - 1)
                    {
                        System.out.print(element.element + "]");
                        System.out.println();
                        break;
                    }
                    System.out.print(element.element + ", ");
                }
                break;
            case "sublist":
                int fromIndex = read.nextInt();
                int toIndex = read.nextInt();
                if (fromIndex > toIndex || fromIndex < 0 || toIndex > my_list.size() - 1 || fromIndex > my_list.size() - 1)
                {
                    System.out.println("Error");
                    break;
                }
                ILinkedList sublist = my_list.sublist(fromIndex, toIndex);
                System.out.print("[");
                int sub_size = sublist.size();
                for (int i = 0; i < sub_size; i++)
                {
                    Node element = new Node();
                    element = (Node) sublist.get(i);
                    if (i == sub_size - 1)
                    {
                        System.out.print(element.element + "]");
                        System.out.println();
                        break;
                    }
                    System.out.print(element.element + ", ");
                }
                break;
            case "contains":
                int num_to_find = read.nextInt();
                Node searcher = new Node();
                searcher.length = my_list.size();
                searcher.element = num_to_find;
                if (my_list.contains(searcher))
                {
                    System.out.println("True");
                }
                else
                {
                    System.out.println("False");
                }
                break;
            default:
                System.out.println("Error");
                break;
        }

    }
    public void add(int index, Object element) {
        Node number = (Node) element;
        if (index == 0)
        {
            number.next = head;
            head.previous = number;
            head = number;
            return;
        }
        if (head == null)
        {
            System.out.println("Error");
            return;
        }
        if (index >= number.length / 2) // last half -> move from tail till the index
        {
            Node toIndex_backwards = tail;
            int counter = 0;
            while (counter != number.length - index - 1)
            {
                toIndex_backwards = toIndex_backwards.previous;
                counter++;
            }
            number.previous = toIndex_backwards.previous;
            toIndex_backwards.previous.next = number;
            number.next = toIndex_backwards;
            toIndex_backwards.previous = number;
        }
        else // first half
        {
            Node toIndex_forwards = head;
            int counter = 0;
            while (counter != index - 1)
            {
                toIndex_forwards = toIndex_forwards.next;
                counter++;
            }
            number.next = toIndex_forwards.next;
            number.previous = toIndex_forwards;
            toIndex_forwards.next.previous = number;
            toIndex_forwards.next = number;
        }
    }

    public void add(Object element) {
        Node added = (Node) element;
        if (head == null) // become first
        {
            head = added;
        }
        if (tail == null)
        {
            tail = added;
            return;
        }
        tail.next = added;
        added.previous = tail;
        tail = added;
    }

    public Object get(int index) {
        Node node = new Node();
        int counter = 0;
        node = head;
        while (counter != index && node.next != null)
        {
            node = node.next;
            counter++;
        }
        // needs special handling for out of boundary indexes.
        return node;
    }

    public void set(int index, Object element) {
        Node added_at = (Node) element;
        if (index >= added_at.length / 2) { // use tail
            if (tail == null)
            {
                if (index != 0)
                {
                    System.out.println("Error");
                    return;
                }
                else {
                    tail = added_at;
                    head = added_at;
                }
            }
            else
            {
                Node from_tail = tail;
                if (index == added_at.length - 1) {
                    added_at.previous = tail.previous;
                    tail.previous.next = added_at;
                    return;
                }
                int counter = 0;
                while (counter != added_at.length - index - 1)
                {
                    from_tail = from_tail.previous;
                    counter++;
                }
                added_at.previous = from_tail.previous;
                added_at.previous.next = added_at;
                added_at.next = from_tail.next;
            }
        }
        else {
            Node till_the_index = head;
            if (head == null) // if the list is empty
            {
                if (index != 0) // the prompted is not the first index
                {
                    System.out.println("Error");
                    return;
                } else {
                    head = added_at; // add first
                    tail = added_at;
                }
            } else // head not null
            {
                if (index == 0) {
                    added_at.next = head.next;
                    head.previous = added_at;
                    head = added_at;
                    return;
                }
                int counter = 0;
                while (counter != index - 1) {
                    till_the_index = till_the_index.next;
                    counter++;
                }
                added_at.next = till_the_index.next.next;
                added_at.previous = till_the_index;
                till_the_index.next = added_at;
            }
        }
    }

    public void clear() {
        while (!isEmpty())
        {
            remove(0); // remove first index item
        }
    }

    public boolean isEmpty() {
        if (head == null || tail == null)
            return true; // empty
        return false;
    }

    public void remove(int index) {
        if (head == null)
        {
            System.out.println("Error");
            return;
        }
        if (index == 0)
        {
            head = head.next;
            return;
        }
        Node node = new Node();
        node = head;
        int counter = 0;
        while (counter != index - 1)
        {
            node = node.next;
            counter++;
        }
        try {
            node.next.next.previous = node.next;
        } catch (NullPointerException ignored){

        }
            node.next = node.next.next;
    }

    public int size() {
        if (head == null)
        {
            return 0; // empty
        }
        Node node = new Node();
        node = head;
        int counter = 0;
        while (node.next != null)
        {
            node = node.next;
            counter++;
        }
        return (counter + 1);
    }

    public ILinkedList sublist(int fromIndex, int toIndex) {
        Node node = new Node();
        node = head;
        ILinkedList sub = new DoubleLinkedList();
        int counter = 0;
        if (fromIndex == 0)
        {
            Node to_sublist = new Node();
            to_sublist.element = node.element;
            sub.add(to_sublist);
        }
        else {
            while (counter != fromIndex - 1) {
                node = node.next;
                counter++;
            }
        }
        while (counter != toIndex)
        {
            node = node.next;
            Node to_sub_list = new Node();
            to_sub_list.element = node.element;
            sub.add(to_sub_list);
            counter++;
        }
        return sub;
    }

    public boolean contains(Object o) {
        if (isEmpty())
        {
            return false;
        }
        Node number = (Node) o;
        Node node = new Node();
        node = head;
        while (node.element != number.element)
        {
            node = node.next;
            if (node == null)
            {
                return false;
            }
        }
        return true;
    }
}