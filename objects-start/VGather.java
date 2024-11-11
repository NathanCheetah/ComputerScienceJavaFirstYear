public class VGather {

    public static void main(String[] args){
        System.out.println("How Many Students In Class?");
        int n = Comp122.getInt("");
        int[] grades = new int[n];
        int total = 0;

        for(int i=0; i<n; i++) {
            System.out.println("Enter a grade:");
            grades[i] = Comp122.getInt("");      
        }

        for(int x=0; x<grades.length; x++){
            total += grades[x];
        }

        double average = (double) total/n;
        double rounded = Math.round(average * 100.0) / 100.0;

        System.out.println(rounded);
    }
}
