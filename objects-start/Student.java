public class Student{
    public String name;
    public boolean hasSubmitted = false;
    public String email;
    public int yearOfBirth;
    public int enrolmentYear;
    public int studentId;
    public int grade = 0;

    public Student(){
        name = "";
        email = "";
        yearOfBirth = 0;
        enrolmentYear = 0;
        studentId = 0;
    }

    public Student(String inputName, String inputEmail, int inputYearOfBirth, int inputEnrolmentYear, int inputStudentId){
        name = inputName;
        email = inputEmail;
        yearOfBirth = inputYearOfBirth;
        enrolmentYear = inputEnrolmentYear;
        studentId = inputStudentId;
    }

    public Student(String inputName, String inputEmail, int inputYearOfBirth){ //override with int birth
        name = inputName;
        email = inputEmail;
        yearOfBirth = inputYearOfBirth;
    }

    public Student(String inputName, String inputEmail, String inputYearOfBirth){ //override with string birth
        name = inputName;
        email = inputEmail;
        yearOfBirth = yearFromString(inputYearOfBirth);
    }

    private int yearFromString(String dateOfBirth){ //changes old dates to new style
        if(dateOfBirth.contains("/")){
            String[] parts = dateOfBirth.split("/");
            return Integer.parseInt(parts[2]);
        }else{
            return Integer.parseInt(dateOfBirth);
        }
    }

    public void updateGrade(int newGrade){
        if(newGrade>=0 && newGrade<=100){
            grade = newGrade;
            hasSubmitted = true;
        }else{
            System.out.println("Enter a grade from 0-100.");
        }
    }

    public int getGrade(){
        return grade;
    }

    public String getEmail(){
        return email;
    }

    public int getYearOfBirth(){
        return yearOfBirth;
    }

    public String getName(){
        return name;
    }

    public int getStudentId(){
        return studentId;
    }

    public int getEnrolmentYear(){
        return enrolmentYear;
    }

    public void submitCoursework(){
        hasSubmitted = true;
    }

    public static void main(String[] args){ //testing
        Student alice = new Student("Alice", "aliceXtreme@aol.com", 1984, 2021, 1234567);
        Student bob = new Student("Max", "Max@gmail.com", 1990, 2020, 9876543);

        alice.updateGrade(85);
        System.out.println(alice.getGrade());
    }
}
