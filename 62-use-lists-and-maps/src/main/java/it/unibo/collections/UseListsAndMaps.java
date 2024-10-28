package it.unibo.collections;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {
    private static final int START = 1000;
    private static final int END = 2000;
    private static final int ELEMENTS_TO_ADD = 100000;
    private static final int TIMES_TO_READ = 1000;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        for (int i = START; i < END; i++) {
            arrayList.add(i);
        }
        /* 
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        LinkedList<Integer> linkedList = new LinkedList<Integer>(arrayList);

        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        int lastElem = arrayList.get(arrayList.size() - 1);
        arrayList.set(arrayList.size() - 1, arrayList.get(0));
        arrayList.set(0, lastElem);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        /*
        for (Integer e: arrayList) {
            System.out.print("[" + e + "], ");
        }
        System.out.println();
        */

        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */

        long time = System.nanoTime();
        long millis;

        for (int i = 0; i < ELEMENTS_TO_ADD; i++) {
            arrayList.addFirst(i);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(
            "Adding "
                + ELEMENTS_TO_ADD
                + " elements in the head of an ArrayList took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );

        time = System.nanoTime();

        for (int i = 0; i < ELEMENTS_TO_ADD; i++) {
            linkedList.addFirst(i);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(
            "Adding "
                + ELEMENTS_TO_ADD
                + " elements in the head of a LinkedList took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );
        System.out.println();

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        time = System.nanoTime();

        for (int i = 0; i < TIMES_TO_READ; i++) {
            arrayList.get(arrayList.size()/2);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(
            "Reading "
                + TIMES_TO_READ
                + " elements in the middle of an ArrayList took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );

        time = System.nanoTime();

        for (int i = 0; i < TIMES_TO_READ; i++) {
           linkedList.get(linkedList.size()/2);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(
            "Reading "
                + TIMES_TO_READ
                + " elements in the middle of a LinkedList took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );
        System.out.println();

        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        Map<String, Long> worldPopulation = new HashMap<String, Long>();

        worldPopulation.put("Africa", 1110635000L);
        worldPopulation.put("Americas", 972005000L);
        worldPopulation.put("Antartica", 0L);
        worldPopulation.put("Asia", 4298723000L);
        worldPopulation.put("Europe", 742452000L);
        worldPopulation.put("Oceania", 38304000L);

        /*
         * 8) Compute the population of the world
         */

        long numPopulation = 0;
        for (long val: worldPopulation.values()) {
            numPopulation += val;
        }
        System.out.println("The population of the world is " + numPopulation);
    }
}
