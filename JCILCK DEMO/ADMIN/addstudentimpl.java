import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;

public class addstudentimpl extends UnicastRemoteObject implements addstudentinf {
    Connection cn;
    Statement st;

    public addstudentimpl() throws RemoteException {
        try {
            // data base connection;
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jclick", "root", "");
            st = cn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String studentdata(String sname, String course, String cf, String mid, String edq) throws RemoteException {
        try {
            String qr = "insert into student (Studentname,Course,CourseFees,Mailid,Education) values('" + sname
                    + "','" + course + "','" + cf + "','" + mid + "','" + edq + "')";
            st.executeUpdate(qr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "successfully student added!";
    }

    public String studentdel(String sid) throws RemoteException {
        try {
            String sql = "delete from student where Studentid='" + sid + "'";
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "successfully student deleted!";
    }

    public String studentupdate(String id, String name, String ed) {
        try {
            String sql2 = "update student set StudentName='" + name + "',Education='" + ed + "' WHERE StudentID ='" + id
                    + "'";
            st.executeUpdate(sql2);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Student Details Updated Successfully";
    }

    public String batch(String baname, String course, String trainer, String timing)// insert
            throws RemoteException {
        try {
            String sql1 = "insert into batch (baname,course,trainer,timing) values('" + baname + "','" + course + "','"
                    + trainer + "','" + timing + "')";
            st.executeUpdate(sql1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "added";
    }

    public String update(String batchid, String trainer, String timing) throws RemoteException {// update
        try {
            String sql = "update batch SET trainer ='" + trainer + "' ,timing='" + timing + "' WHERE batchid='"
                    + batchid + "'";
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // query2
        return "updated";
    }

    public String delete(String batchid) throws RemoteException {
        // query3
        try {
            String sql = "delete from batch where batchid='" + batchid + "'";
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "deleted";
    }

    public String newcourse(String cname, String cdu, String des, String fee) throws RemoteException {
        try {
            String qr = "insert into course(CourseName,Duration,Description,fee) values('" + cname + "','" + cdu + "','"
                    + des + "','" + fee + "')";
            st.executeUpdate(qr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Successfully Added";
    }

}