/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DatabaseAccess;

import Models.AllDuePayments;
import entities.Payment;
import entities.Student;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Kavindya
 */
public class PaymentDataHandler {
    private Connection connection;
    private final String connectionURL = "jdbc:mysql://localhost:3306/universityOfSumanadasa_hallManagement";
    ResultSet rs = null;
    PreparedStatement psmnt = null;
    private FileInputStream fis;
    private Student studentinfo;
        private void createDBConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
        {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL, "root", "");
        }

    public void doPayment(ArrayList<Payment> pay) {
         try {
        createDBConnection();
        for(int i=0;i<pay.size();i++)
            {
            psmnt = connection.prepareStatement("INSERT INTO payment ( academic_year,amount,student_id)"+ "values(?,?,?)");
            psmnt.setString(1,pay.get(i).getAcademicYear());
            psmnt.setDouble(2,pay.get(i).getAmount());
            psmnt.setString(3,pay.get(i).getStudentId());
            psmnt.executeUpdate();
            psmnt = connection.prepareStatement("update lives_in SET isPaid=?  where student_id = ? && academic_year=?");
            psmnt.setBoolean(1,true);
            psmnt.setString(2,pay.get(i).getStudentId());
            psmnt.setString(3,pay.get(i).getAcademicYear());
            psmnt.executeUpdate();
            }
        }

        catch (Exception ex) {
        System.out.println("Do payments"+ex);
                         }
    }

    public ArrayList<Payment> getAllPayments() {
       ArrayList<Payment> payments = new ArrayList<Payment>();
        Payment view;
         try {
   createDBConnection();
   psmnt = connection.prepareStatement("SELECT* FROM payment");
   rs = psmnt.executeQuery();
   while (rs.next())
       {
       System.out.println("found");
       view=new Payment();
       view.setPaymentId(rs.getString(1));
       view.setAcademicYear(rs.getString(2));
       view.setAmount(rs.getDouble(3));
       view.setStudentId(rs.getString(4));
       payments.add(view);
        }
        }
        catch (Exception ex) {
        System.out.println("Exception in showing payments"+ex);
                         }
      return payments;
    }

    public ArrayList<AllDuePayments> getAllDuePayments() {
         ArrayList<AllDuePayments> payments = new ArrayList<AllDuePayments>();
         AllDuePayments view;
         try {
   createDBConnection();
   psmnt = connection.prepareStatement("SELECT student.student_id,student.student_first_name,hall.hall_name,lives_in.room_number,lives_in.academic_year,lives_in.balance FROM lives_in,student,hall where lives_in.student_id=student.student_id && lives_in.hall_id=hall.hall_id &&isPaid=?");
   psmnt.setBoolean(1, false);
   rs = psmnt.executeQuery();
   while (rs.next())
       {
       System.out.println("found");
       view=new AllDuePayments();
       view.setStudentid(rs.getString(1));
       view.setStudentname(rs.getString(2));
       view.setHall(rs.getString(3));
       view.setRoomNumber(rs.getInt(4));
       view.setAcademicYear(rs.getString(5));
       view.setAmount(rs.getDouble(6));
       payments.add(view);
        }
        }
        catch (Exception ex) {
        System.out.println("Exception in showing payments"+ex);
                         }
      return payments;
    }



}
