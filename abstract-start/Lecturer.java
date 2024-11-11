public class Lecturer extends Person implements Payable {
  private String timetable;

  public void setTimeTable(String tt) {
    timetable = tt;
  }

  public String getTimeTable() {
    return timetable;
  }

  public String greet() {
    return "To: " + getEmail() + "\nHi Lecturer " + getName() + ",\n";
  }

  public void payAmount(int n) {
    System.out.println(n);
  }
}
