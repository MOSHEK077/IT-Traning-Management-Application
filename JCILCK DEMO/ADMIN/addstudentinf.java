import java.rmi.*;

public interface addstudentinf extends Remote {
    public String studentdata(String sname, String course, String cf, String mid, String edq)
            throws RemoteException;// interface successfully created;

    public String studentdel(String sid) throws RemoteException;

    public String studentupdate(String id, String name, String ed) throws RemoteException;

    public String batch(String baname, String course, String trainer, String timing)
            throws RemoteException;

    public String update(String batchid, String trainer, String timing) throws RemoteException;

    public String delete(String batchid) throws RemoteException;

    public String newcourse(String cname, String cdu, String des, String fee) throws RemoteException;

}