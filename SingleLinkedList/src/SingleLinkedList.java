import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

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


public class SingleLinkedList implements ILinkedList {
    /* Implement your linked list class here*/

    public static class Node
    {
        int element;
        Node next;
    }
    Node head = null;
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        Node element1 = new Node();
        ILinkedList my_list = new SingleLinkedList();
        ILinkedList sub_list = new SingleLinkedList();

        element1.element = 5;
        Node element2 = new Node();
        element2.element = 7;
        Node element3 = new Node();
        element3.element = 9;
        Node element4 = new Node();
        element4.element = 15;
        my_list.add(element1);
        my_list.add(element2);
        my_list.add(element3);
        my_list.add(element4);
//        my_list.remove(0);
        Node element5 = new Node();
        element5.element = 6;
        sub_list = my_list.sublist(3,3);
//        my_list.add(1, element5);
//
//        Node element6 = new Node();
//        element6.element = 11;
//        my_list.set(1, element6);
        System.out.println("Size my_list= " + my_list.size());
        System.out.println("Size sub_list= " + sub_list.size());

        for (int i = 0; i < sub_list.size(); i++)
        {
            Node printed = (Node) sub_list.get(i);
            System.out.println(printed.element);
        }
//        System.out.println(my_list.isEmpty());
//        my_list.clear();

//        System.out.println(my_list.contains(element5));
//        System.out.println("Size= " + my_list.size());
//        for (int i = 0; i < my_list.size(); i++)
//        {
//            Node printed = (Node) my_list.get(i);
//            System.out.println(printed.element);
//        }
//        System.out.println(my_list.isEmpty());
    }

    public void add(int index, Object element) {
        Node added_at = (Node) element;
        Node till_the_index = head;
        if (head == null) // if the list is empty
        {
            if (index != 0) // the prompted is not the first index
            {
                System.out.println("Error");
                return;
            }
            else
            {
                head = added_at; // become first
            }
        }
        else // head not null
        {
            if (index == 0)
            {
                added_at.next = head;
                head = added_at;
                return;
            }
            int counter = 0;
            while (counter != index - 1)
            {
                till_the_index = till_the_index.next;
                counter++;
            }
            added_at.next = till_the_index.next;
            till_the_index.next = added_at;
        }
    }

    public void add(Object element) {
        Node added = (Node) element;
        if (head == null)
        {
            head = added; // it is the first element
        }
        else
        {
            Node to_the_last = head;
            // Loop until we reach to the last element (which points to null)
            while (to_the_last.next != null)
            {
                to_the_last = to_the_last.next;
            }
            // the added object becomes the last element
            to_the_last.next = added;
        }
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
        Node till_the_index = head;
        if (head == null) // if the list is empty
        {
            if (index != 0) // the prompted is not the first index
            {
                System.out.println("Error");
                return;
            }
            else
            {
                head = added_at; // add first
            }
        }
        else // head not null
        {
            if (index == 0)
            {
                added_at.next = head.next;
                head = added_at;
                return;
            }
            int counter = 0;
            while (counter != index - 1)
            {
                till_the_index = till_the_index.next;
                counter++;
            }
            added_at.next = till_the_index.next.next;
            till_the_index.next = added_at;
        }
    }

    public void clear() {
        while (!isEmpty())
        {
            remove(0); // remove first index
        }
    }

    public boolean isEmpty() {
        if (head == null)
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
        ILinkedList sub = new SingleLinkedList();
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