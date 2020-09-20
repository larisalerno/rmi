package utils;

import java.util.Stack;

public class RandomIDGenerator {

    public static Stack<Integer> generateNumbers(int collectionSize) {
        Stack<Integer> collection = new Stack<>();

        for (int i = 0; i < collectionSize; i++) {
            collection.push((Integer) i);
            System.out.println(i);
        }


        return collection;
    }
}
