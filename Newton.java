// imports
import java.lang.Math;

public class Newton {
    public static void main(String[] args) {
        // Read in values
        double n = Double.parseDouble(args[0]);
        double guess = Double.parseDouble(args[1]);
        sqRoot(n, guess);
    }

    public static void sqRoot(double n, double guess) {
        // Your code here
        double x = n;
        double root = x/2;
        boolean hold = true;

        while(hold = true){
            root = 0.5*(x+(n/x));

            double calc=java.lang.Math.abs(root-x);
            if(calc < guess){
                break;
            }
            x = root;
        }

        System.out.println(root);
    }   
}