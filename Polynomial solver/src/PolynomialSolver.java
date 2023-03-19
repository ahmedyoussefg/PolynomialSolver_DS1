import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


interface IPolynomialSolver {
    /**
     * Set polynomial terms (coefficients & exponents)
     * @param poly: name of the polynomial
     * @param terms: array of [coefficients][exponents]
     */
    void setPolynomial(char poly, int[][] terms);

    /**
     * Print the polynomial in ordered human readable representation
     * @param poly: name of the polynomial
     * @return: polynomial in the form like 27x^2+x-1
     */
    String print(char poly);

    /**
     * Clear the polynomial
     * @param poly: name of the polynomial
     */
    void clearPolynomial(char poly);

    /**
     * Evaluate the polynomial
     * @param poly: name of the polynomial
     * @param value: the polynomial constant value
     * @return the value of the polynomial
     */
    float evaluatePolynomial(char poly, float value);

    /**
     * Add two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return the result polynomial
     */
    int[][] add(char poly1, char poly2);

    /**
     * Subtract two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);

    /**
     * Multiply two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return: the result polynomial
     */
    int[][] multiply(char poly1, char poly2);
}


public class PolynomialSolver implements IPolynomialSolver{
    public static class SingleLinkedList {
        public static class Node {
            int element;
            Node next;
        }
        public static int indx = 0;
        public static Node [] head = {null,null,null,null};
        public static SingleLinkedList list_A = new SingleLinkedList();
        public static SingleLinkedList list_B = new SingleLinkedList();
        public static SingleLinkedList list_C = new SingleLinkedList();
        public static SingleLinkedList list_R = new SingleLinkedList();


        public void add(Object element) {
            Node added = (Node) element;
            if (head[indx] == null) {
                head[indx] = added; // it is the first element
            } else {
                Node to_the_last = head[indx];
                // Loop until we reach to the last element (which points to null)
                while (to_the_last.next != null) {
                    to_the_last = to_the_last.next;
                }
                // the added object becomes the last element
                to_the_last.next = added;
            }
        }
        public int size() {
            if (head[indx] == null)
            {
                return 0; // empty
            }
            Node node = new Node();
            node = head[indx];
            int counter = 0;
            while (node.next != null)
            {
                node = node.next;
                counter++;
            }
            return (counter + 1);
        }
        public Object get(int index) {
            Node node = new Node();
            int counter = 0;
            node = head[indx];
            while (counter != index && node.next != null)
            {
                node = node.next;
                counter++;
            }
            // needs special handling for out of boundary indexes.
            return node;
        }
        public void clear() {
            while (!isEmpty())
            {
                remove(0); // remove first index
            }
        }

        public boolean isEmpty() {
            if (head[indx] == null)
                return true; // empty
            return false;
        }
        public void remove(int index) {
            if (head[indx] == null)
            {
                System.out.println("Error");
                return;
            }
            if (index == 0)
            {
                head[indx] = head[indx].next;
                return;
            }
            Node node = new Node();
            node = head[indx];
            int counter = 0;
            while (counter != index - 1)
            {
                node = node.next;
                counter++;
            }
            node.next = node.next.next;
        }


    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner read = new Scanner(System.in);
        PolynomialSolver pol = new PolynomialSolver();
        char key; // A,B,C
        boolean [] just_cleared = new boolean[]{false,false,false};
        while(read.hasNext())
        {
            switch(read.next()) {
                case "set":
                    key = read.next().charAt(0);
                    if (key != 'A' && key != 'B' && key != 'C')
                    {
                        System.out.println("Error");
                        return;
                    }
                    String poly_str = read.next().replaceAll("\\[|\\]", "");
                    String[] poly_arr = poly_str.split(",");
                    int[][] terms = new int[poly_arr.length][2];
                    if (poly_arr.length == 1 && poly_arr[0].isEmpty())
                    {
                        System.out.println("Error");
                        return;
                    }
                    else
                    {
                        for (int i = 0; i < poly_arr.length; i++)
                        {
                            terms[i][0] = Integer.parseInt(poly_arr[i]); // coefficients
                            terms[i][1] = poly_arr.length - 1 - i; // exponents
                        }
                    }
                    pol.setPolynomial(key, terms);
                    just_cleared[key - 65] = false;
                    break;
                case "print":
                    key = read.next().charAt(0);
                    if (key != 'A' && key != 'B' && key != 'C')
                    {
                        System.out.println("Error");
                        return;
                    }
                    System.out.println(pol.print(key));
                    break;
                case "add":
                    key = read.next().charAt(0);
                    char key2 = read.next().charAt(0);
                    if (key != 'A' && key != 'B' && key != 'C')
                    {
                        System.out.println("Error");
                        return;
                    }
                    if (key2 != 'A' && key2 != 'B' && key2 != 'C')
                    {
                        System.out.println("Error");
                        return;
                    }
                    if (just_cleared[key2 - 65])
                    {
                        System.out.println("Error");
                        return;
                    }
                    int [][] r = pol.add(key, key2);
                    break;
                case "sub":

                    break;
                case "mult":

                    break;
                case "clear":
                        key = read.next().charAt(0);
                        if (key != 'A' && key != 'B' && key != 'C')
                        {
                            System.out.println("Error");
                            return;
                        }
                        pol.clearPolynomial(key);
                        just_cleared[key - 65] = true;
                    System.out.println("[]");
                    break;
                case "eval":
                    break;
                default:
                    System.out.println("Error");
                    break;

            }
        }
    }

    public void setPolynomial(char poly, int[][] terms) {
        switch (poly)
        {
            case 'A':
                for (int i = 0; i < terms.length; i++)
                {
                    SingleLinkedList.Node coff = new SingleLinkedList.Node();
                    coff.element = terms[i][0];
                    SingleLinkedList.indx = 0;
                    SingleLinkedList.list_A.add(coff);
                }
                break;
            case 'B':
                for (int i = 0; i < terms.length; i++)
                {
                    SingleLinkedList.Node coff = new SingleLinkedList.Node();
                    coff.element = terms[i][0];
                    SingleLinkedList.indx = 1;
                    SingleLinkedList.list_B.add(coff);
                }
                break;
            case 'C':
                for (int i = 0; i < terms.length; i++)
                {
                    SingleLinkedList.Node coff = new SingleLinkedList.Node();
                    coff.element = terms[i][0];
                    SingleLinkedList.indx = 2;
                    SingleLinkedList.list_C.add(coff);
                }
                break;
        }
    }

    public String print(char poly) {
        String printed = "";
        switch(poly)
        {
            case 'A':
                SingleLinkedList.indx = 0;
                int len = SingleLinkedList.list_A.size();
                SingleLinkedList.Node coffs = new SingleLinkedList.Node();
                for (int i = 0; i < len; i++) {
                    SingleLinkedList.indx = 0;
                    coffs = (SingleLinkedList.Node) SingleLinkedList.list_A.get(i);
                    int digit = coffs.element;
                    if (i != 0 && digit > 0 && !printed.equals(""))
                    {
                        printed += "+";
                    }
                    if ((len - 1 - i) == 0)
                    {
                        printed += digit;
                        continue;
                    }
                    if (digit == 0)
                    {
                        continue;
                    }
                    else if (digit == 1)
                    {
                        printed += "x";
                    }
                    else if (digit > 0)
                    {
                        printed += digit + "x";
                    }
                    else if (digit < 0)
                    {
                        printed += digit + "x";
                    }
                    if (len - 1 - i == 1)
                    {
                        continue;
                    }
                    else
                    {
                        printed += "^" + (len -1 - i);
                    }
                }
                break;
            case 'B':
                SingleLinkedList.indx = 1;
                int len2 = SingleLinkedList.list_B.size();
                SingleLinkedList.Node coffs2 = new SingleLinkedList.Node();
                for (int i = 0; i < len2; i++) {
                    SingleLinkedList.indx = 1;
                    coffs2 = (SingleLinkedList.Node) SingleLinkedList.list_B.get(i);
                    int digit = coffs2.element;
                    if (i != 0 && digit > 0 && !printed.equals(""))
                    {
                        printed += "+";
                    }
                    if ((len2 - 1 - i) == 0)
                    {
                        printed += digit;
                        continue;
                    }
                    if (digit == 0)
                    {
                        continue;
                    }
                    else if (digit == 1)
                    {
                        printed += "x";
                    }
                    else if (digit > 0)
                    {
                        printed += digit + "x";
                    }
                    else if (digit < 0)
                    {
                        printed += digit + "x";
                    }
                    if (len2 - 1 - i == 1)
                    {
                        continue;
                    }
                    else
                    {
                        printed += "^" + (len2 -1 - i);
                    }
                }
                break;
            case 'C':
                SingleLinkedList.indx = 2;
                int len3 = SingleLinkedList.list_C.size();
                SingleLinkedList.Node coffs3 = new SingleLinkedList.Node();
                for (int i = 0; i < len3; i++) {
                    SingleLinkedList.indx = 2;
                    coffs3 = (SingleLinkedList.Node) SingleLinkedList.list_C.get(i);
                    int digit = coffs3.element;
                    if (i != 0 && digit > 0 && !printed.equals(""))
                    {
                        printed += "+";
                    }
                    if ((len3 - 1 - i) == 0)
                    {
                        printed += digit;
                        continue;
                    }
                    if (digit == 0)
                    {
                        continue;
                    }
                    else if (digit == 1)
                    {
                        printed += "x";
                    }
                    else if (digit > 0)
                    {
                        printed += digit + "x";
                    }
                    else if (digit < 0)
                    {
                        printed += digit + "x";
                    }
                    if (len3 - 1 - i == 1)
                    {
                        continue;
                    }
                    else
                    {
                        printed += "^" + (len3 -1 - i);
                    }
                }
                break;
        }
        return printed;
    }

    public void clearPolynomial(char poly) {
        switch (poly)
        {
            case 'A':
                SingleLinkedList.indx = 0;
                SingleLinkedList.list_A.clear();
                break;
            case 'B':
                SingleLinkedList.indx = 1;
                SingleLinkedList.list_B.clear();
                break;
            case 'C':
                SingleLinkedList.indx = 2;
                SingleLinkedList.list_C.clear();
                break;
        }
    }

    public float evaluatePolynomial(char poly, float value) {
        return 0;
    }

    public int[][] add(char poly1, char poly2) {
        int size1 = 0, size2 = 0;
        int letter_1 = 0, letter_2 = 0;
        SingleLinkedList.indx = poly1 - 65;
        switch(poly1) {
            case 'A':
                size1 = SingleLinkedList.list_A.size();
                letter_1 = 0;
                break;
            case 'B':
                size1 = SingleLinkedList.list_B.size();
                letter_1 = 1;
                break;
            case 'C':
                size1 = SingleLinkedList.list_C.size();
                letter_1 = 2;
                break;
        }
        SingleLinkedList.indx = poly2 - 65;
        switch(poly2) {
            case 'A':
                size2 = SingleLinkedList.list_A.size();
                letter_2 = 0;
                break;
            case 'B':
                size2 = SingleLinkedList.list_B.size();
                letter_2 = 1;
                break;
            case 'C':
                size2 = SingleLinkedList.list_C.size();
                letter_2 = 2;
                break;
        }
        if (size1 >= size2)
        {
            int [][] r = new int[size1][2];
            for (int i = 0; i < size1; i++)
            {
                SingleLinkedList.Node Temp1 = new SingleLinkedList.Node();
                if (i > size2 - 1)
                {
                    SingleLinkedList.indx = poly1 - 65;
                    if (letter_1 == 0)
                        Temp1 = (SingleLinkedList.Node) SingleLinkedList.list_A.get(i);
                    else if (letter_1 == 1)
                        Temp1 = (SingleLinkedList.Node) SingleLinkedList.list_B.get(i);
                    else if (letter_1 == 2)
                        Temp1 = (SingleLinkedList.Node) SingleLinkedList.list_C.get(i);
                    r[i][0] += Temp1.element;
                    r[i][1] = size1 - i - 1;
                    continue;
                }
                SingleLinkedList.Node Temp2 = new SingleLinkedList.Node();
                SingleLinkedList.indx = poly1 - 65;
                if (letter_1 == 0)
                    Temp1 = (SingleLinkedList.Node) SingleLinkedList.list_A.get(i);
                else if (letter_1 == 1)
                    Temp1 = (SingleLinkedList.Node) SingleLinkedList.list_B.get(i);
                else if (letter_1 == 2)
                    Temp1 = (SingleLinkedList.Node) SingleLinkedList.list_C.get(i);
                SingleLinkedList.indx = poly2 - 65;
                if (letter_2 == 0)
                    Temp2 = (SingleLinkedList.Node) SingleLinkedList.list_A.get(i);
                else if (letter_2 == 1)
                    Temp2 = (SingleLinkedList.Node) SingleLinkedList.list_B.get(i);
                else if (letter_2 == 2)
                    Temp2 = (SingleLinkedList.Node) SingleLinkedList.list_C.get(i);
                r[i][0] = Temp1.element + Temp2.element;
                r[i][1] = size1 - 1 - i;
            }
            return r;
        }
        else // if size2 > size1
        {
            int [][] r = new int[size2][2];
            for (int i = 0; i < size2; i++)
            {
                SingleLinkedList.Node Temp1 = new SingleLinkedList.Node();
                if (i > size1 - 1)
                {
                    SingleLinkedList.indx = poly2 - 65;
                    if (letter_2 == 0)
                        Temp1 = (SingleLinkedList.Node) SingleLinkedList.list_A.get(i);
                    else if (letter_2 == 1)
                        Temp1 = (SingleLinkedList.Node) SingleLinkedList.list_B.get(i);
                    else if (letter_2 == 2)
                        Temp1 = (SingleLinkedList.Node) SingleLinkedList.list_C.get(i);
                    r[i][0] += Temp1.element;
                    r[i][1] = size2 - i - 1;
                    continue;
                }
                SingleLinkedList.Node Temp2 = new SingleLinkedList.Node();
                SingleLinkedList.indx = poly1 - 65;
                if (letter_1 == 0)
                    Temp1 = (SingleLinkedList.Node) SingleLinkedList.list_A.get(i);
                else if (letter_1 == 1)
                    Temp1 = (SingleLinkedList.Node) SingleLinkedList.list_B.get(i);
                else if (letter_1 == 2)
                    Temp1 = (SingleLinkedList.Node) SingleLinkedList.list_C.get(i);
                SingleLinkedList.indx = poly2 - 65;
                if (letter_2 == 0)
                    Temp2 = (SingleLinkedList.Node) SingleLinkedList.list_A.get(i);
                else if (letter_2 == 1)
                    Temp2 = (SingleLinkedList.Node) SingleLinkedList.list_B.get(i);
                else if (letter_2 == 2)
                    Temp2 = (SingleLinkedList.Node) SingleLinkedList.list_C.get(i);
                r[i][0] = Temp1.element + Temp2.element;
                r[i][1] = size2 - 1 - i;
            }
            return r;
        }
    }

    public int[][] subtract(char poly1, char poly2) {
        return new int[0][];
    }

    public int[][] multiply(char poly1, char poly2) {
        return new int[0][];
    }
}