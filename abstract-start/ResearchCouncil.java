public class ResearchCouncil implements Billable, Emailable {
    private String name;
    private String email;

    public void payBill(int n) {
        System.out.println("Bill amount: " + n);
    }

    public void sendEmail() {
        System.out.println("Email sent to Research Council");
    }
}