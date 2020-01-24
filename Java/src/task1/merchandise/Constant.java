package task1.merchandise;

import java.util.ArrayList;
import java.util.List;
import org.javatuples.Triplet;

public class Constant {
    public static String fileName = "src/task1/merchandise/input.txt";
    public static List<Triplet<Integer, Character, Integer>> orderList = new ArrayList<>();     // All orders are stored in this list

    public static int numberOfOrder;       // contains total number of orders
    public static int totalSmall = 4;       // contains total number of small T-Shirt
    public static int totalMedium = 5;      // contains total number of small T-Shirt
    public static int totalLarge = 2;       // contains total number of small T-Shirt
    public static int totalCap = 4;     // contains total number of small T-Shirt


    public static Character small = 'S';
    public static Character medium = 'M';
    public static Character large = 'L';
    public static Character cap = 'C';

}
