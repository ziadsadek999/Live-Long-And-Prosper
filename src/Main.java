import java.util.*;

public class Main {
    public static void main(String[] args) {
        String init = "50;" +
                "1,2,3;" +
                "4,5,6;" +
                "7,8;9,10;11,12;" +
                "13,14,15,16,17;" +
                "18,19,20,21,22;";
        Problem problem = new Problem(init, "BF", true);
        System.out.println(problem);
    }
}