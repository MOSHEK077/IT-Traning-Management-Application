import java.rmi.Naming;

public class addstudentserver {

    public static void main(String[] args) {
        try {
            addstudentimpl in = new addstudentimpl();
            Naming.rebind("addstudentserver", in);
        } catch (Exception e) {
        }

    }
}