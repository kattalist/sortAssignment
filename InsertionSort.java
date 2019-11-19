/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertionsort;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 073787251
 */
public class InsertionSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> line = new ArrayList<>();
        menu(line);
    }

    /**
     * Generates the menu and performs operations based on user input.
     * @param line the list of strings entered by the user
     */
    public static void menu(ArrayList<String> line) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1 - Read names from file and display list");
        System.out.println("2 - Jeff: Selection Sort ascending and display list");
        System.out.println("3 - Jeff: Selection Sort descending and display list");
        System.out.println("4 - Nate: Bubble Sort ascending and display list");
        System.out.println("5 - Nate: Bubble Sort descending and display list");
        System.out.println("6 - Jack: Insertion Sort ascending and display list");
        System.out.println("7 - Jack: Insertion Sort descending and display list");
        System.out.println("8 - Binary Search");
        System.out.println("9 - Exit");
        switch (keyboard.nextInt()) {
            case 1:
                String ln = keyboard.nextLine();
                while (!ln.equals("xx")) {
                    if (!ln.equals("")) {
                        line.add(ln);
                    }
                    ln = keyboard.nextLine();
                }
                System.out.println("The unsorted list is " + print(line));
                menu(line);
                break;
            case 2:
                System.out.println(print(selectionSort(line, true)));
                menu(line);
                break;
            case 3:
                System.out.println(print(selectionSort(line, false)));
                menu(line);
                break;
            case 4:
                System.out.println(print(bubbleSort(line, true)));
                menu(line);
                break;
            case 5:
                System.out.println(print(bubbleSort(line, false)));
                menu(line);
                break;
            case 6:
                System.out.println(print(insertionSort(line, true)));
                menu(line);
                break;
            case 7:
                System.out.println(print(insertionSort(line, false)));
                menu(line);
                break;
            case 8:
                System.out.println("Enter the string to search for: ");
                keyboard.nextLine();
                String search = keyboard.nextLine();
                System.out.println("The sorted list is " + print(selectionSort(line, true)));
                int searchedIndex = binarySearch(selectionSort(line, true), search);
                if (searchedIndex != -1) {
                    System.out.println("The specified string is at index " + searchedIndex);
                } else {
                    System.out.println("The specified string was not found");
                }
                menu(line);
                break;
            case 9:
                break;
        }
    }

    /**
     * Uses an insertion sort to iterate over the array and sort it in ascending
     * or descending order.
     *
     * @param line The unsorted list of elements to be sorted
     * @param mode Whether the array is to be sorted alphabetically (true) or
     * reverse-alphabetically (false)
     * @return An alphabetically sorted list of the elements
     */
    public static ArrayList<String> insertionSort(ArrayList<String> line, boolean mode) {
        //JACK'S CODE
        //Iterate over every element in the array, except the first
        for (int i = 1; i < line.size(); i++) {
            //Create temporary integer and string variables
            int j = i;
            String temp;
            //Repeat as long as the element being checked is "out of order" with the element directly before it in the array
            while (j > 0 && ((mode && line.get(j - 1).compareTo(line.get(j)) > 0) || (!mode && line.get(j - 1).compareTo(line.get(j)) < 0))) {
                //Set the temporary string variable to the element being checked
                temp = line.get(j);
                //Remove the element from the array
                line.remove(j);
                //Add the element back to the array in front of the element it was out of order with
                line.add(j - 1, temp);
                //Repeat until the element reaches its place in the array
                j--;
            }
        }
        //Return the sorted array
        return line;
    }

    /**
     * Uses a bubble sorting tactic on the ArrayList of strings to sort into
     * ascending or descending order based on the boolean mode.
     *
     * @param str input string to be sorted
     * @param mode whether it is ascending or descending
     * @return the sorted list of elements
     */
    public static ArrayList<String> bubbleSort(ArrayList<String> str, boolean mode) {
        //NATE'S CODE
        boolean sorted = false;
        String temp;
        while (!sorted) {
            sorted = true;
            for (int j = 0; j < str.size(); j++) {
                for (int i = j + 1; i < str.size(); i++) {
                    if (str.get(i).compareTo(str.get(j)) < 0 && mode) {
                        sorted = false;
                        temp = str.get(i);
                        str.set(i, str.get(j));
                        str.set(j, temp);
                    } else if (str.get(i).compareTo(str.get(j)) > 0 && !mode) {
                        sorted = false;
                        temp = str.get(i);
                        str.set(i, str.get(j));
                        str.set(j, temp);
                    }
                }
            }
        }
        return str;
    }

    /**
     * iterates through array and replaces string positions in pursuit of
     * selection sort ascending and descending mode based on boolean entry.
     *
     * @param names input string to be sorted
     * @param mode boolean selection entry for ascending or descending order
     * @return the selection sorted array
     */
    public static ArrayList<String> selectionSort(ArrayList<String> names, boolean mode) {
        //JEFF'S CODE
        int n = names.size();
        int j = 0;

        while (j < n) { // while there are still names to iterate through in the array
            int minVal = j; // set minVal to mark down value in the array
            int i = j + 1; // set i to be one more than j so that it can check which one is larger later

            while (i < n) { // while there are more i values to be compared
                if (mode == true) { // if user specified array to be sorted in ascending order
                    if (names.get(i).compareTo(names.get(minVal)) < 0) { // if i value is smaller than smallest value so far
                        minVal = i; // then set value to i
                    }
                    i++;
                } else { // if user specified array to be sorted in descending order
                    while (i < n) { //  while there are more i values to be comapared
                        if (names.get(i).compareTo(names.get(minVal)) > 0) { // minVal here actually represents maximum value
                            minVal = i; // set the value to i
                        }
                        i++;
                    }

                }
            }
            if (minVal != j) { // if the value specified does not equal j, switch the places of the value and j
                String temp = names.get(j);
                names.set(j, names.get(minVal));
                names.set(minVal, temp);
            }
            j++;
        }

        return names; // return the sorted array of names

    }

    /**
     * Prints out a string representation of the ArrayList.
     *
     * @param ln the list to be printed
     * @return a string comprised of the list elements
     */
    public static String print(ArrayList<String> ln) {
        String out = "";
        for (String str : ln) {
            out += (str + " ");
        }
        return out;
    }

    /**
     * Searches for the user-entered surname and returns the index of said
     * string.
     *
     * @param ln the sorted list being searched
     * @param target the string being searched for
     * @return the index of the surname within the list
     */
    public static int binarySearch(ArrayList<String> ln, String target) {
        int low = 0, high = ln.size(), mid = 0;
        try {
            //Repeat as long as the low value does not exceed the high value
            while (low <= high) {
                //Set mid to be the average of the high and low value (the midpoint)
                mid = (low + high) / 2;
                //If the target value has been reached, return the value of mid as the index
                if (ln.get(mid).equals(target)) {
                    return mid;
                //Otherwise, if the middle value is greater than the target (later alpabetically), repeat using a sub-array made up of the lower half of the previous array
                } else if (ln.get(mid).compareTo(target) > 0) {
                    high = mid--;
                //Otherwise, the middle value is less than the target (earlier alphabetically, so repeat using a sub-array made up of the upper half of the previous array
                } else {
                    low = mid++;
                }
            }
        //In the event that an index out of bounds exception is reached, the array must empty. If so, return a -1 to indicate the string was not found
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }
        return -1;
    }
}
