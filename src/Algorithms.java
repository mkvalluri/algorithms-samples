import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Algorithms {
    public static void main(String[] args) {
        System.out.println("Hello!");
        List<Integer> result = calculate(Arrays.asList(5,32,1, 7,10,50,19,21,2));
        result.forEach(System.out::println);
    }

    static List<Integer> calculate(List<Integer> integers) {

        // We need min of 3 numbers to compare.
        if (integers.size() < 3) {
            return new ArrayList<>();
        }

        integers.sort(Comparator.comparingInt(Integer::intValue));

        //We loop through all the numbers with complexity of O(N)
        for(int i = integers.size() - 1; i > 1; i--) {
            int sum = integers.get(i);
            int n2 = integers.get(i - 1);

            //We do a binary search, thus trying to find the number in O(logN) time
            //complexity
            Integer n1 = num(integers, 0, i - 2, sum - n2);

            if (n1 != null) {
                return Arrays.asList(n1, n2, sum);
            }
        }

        //We didn't find anything.
        return new ArrayList<>();
    }

    private static Integer num(List<Integer> integers, int startingIndex, int endingIndex, int n1) {
        int midPoint = (startingIndex + endingIndex) / 2;

        if (n1 == integers.get(midPoint)) {
            return n1;
        }

        if (startingIndex == endingIndex) {
            return null;
        }

        if (integers.get(midPoint) < n1) {
            return num(integers, midPoint + 1, endingIndex, n1);
        } else {
            return num(integers, startingIndex, midPoint, n1);
        }
    }
}
