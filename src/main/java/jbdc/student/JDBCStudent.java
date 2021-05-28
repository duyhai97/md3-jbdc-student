package jbdc.student;

import model.Lophoc;
import model.Student;
import model.ViewAll;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCStudent implements JDBCStudentInterface {
    Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_manager", "root", "123456");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Student> selectAll() {
        List<Student> studentList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select  * from student");
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dateOfBirth = rs.getString("dateOfBirth");
                String address = rs.getString("address");
                studentList.add(new Student(id,name,dateOfBirth,address));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return studentList;
    }

    @Override
    public boolean create(Student student , int[] s) {
        Connection connection = getConnection();
        PreparedStatement pstmt = null;
        PreparedStatement pstmAssigntment = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement("insert student (name ,dateOfBirth, address) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);

            connection.setAutoCommit(false);

            pstmt.setString(1,student.getName());
            pstmt.setString(2,student.getDateOfBirth());
            pstmt.setString(3,student.getAddress());
            int rowAffected  = pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            int pID = 0;

            if (rs.next()){
                pID = rs.getInt(1);
            }
            if (rowAffected == 1){
                pstmAssigntment = connection.prepareStatement("insert into student_lophoc (id_student, id_lophoc) values (?,?)");
                for (int permisionID : s){
                    pstmAssigntment.setInt(1,pID);
                    pstmAssigntment.setInt(2,permisionID);
                    pstmAssigntment.executeUpdate();
                }
                connection.commit();
            }
            else  connection.rollback();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            if (connection != null){
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(throwables.getMessage());


        }
        finally {
            try {
                if (rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if (pstmAssigntment != null) pstmAssigntment.close();
                if (connection != null) connection.close();


            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println(throwables.getMessage());
            }
        }
        return false;
    }

    @Override
    public ViewAll selectById(int id) {
        return  null;
    }



    @Override
    public void edit(int id, Student user) throws SQLException {

    }

    @Override
    public void delete(int id) {

    }


    @Override
    public List<ViewAll> viewAll() {
        List<ViewAll> list = new ArrayList();
        Connection connection =  getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select id,name,dateOfBirth,address,id_lophoc,name_lophoc from student_lophoc\n" +
                    "join student s on s.id = student_lophoc.id_student\n" +
                    "join lophoc l on l.id_lophoc = student_lophoc.id_lophocKN");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int studentId = resultSet.getInt("id");
               String studentName = resultSet.getString("name");
               String dateOfBirth = resultSet.getString("dateOfBirth");
               String address = resultSet.getString("address");
               int lophocId = resultSet.getInt("id_lophoc");
               String lophocName = resultSet.getString("name_lophoc");

               Student student = new Student(studentId,studentName,dateOfBirth,address);
               Lophoc lophoc = new Lophoc(lophocId,lophocName);
               list.add(new ViewAll(student,lophoc));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public ViewAll viewByID(int id) {
        Connection connection = getConnection();
        ViewAll viewAll = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select id,name,dateOfBirth,address,id_lophoc,name_lophoc from student_lophoc\n" +
                    "join student s on s.id = student_lophoc.id_student\n" +
                    "join lophoc l on l.id_lophoc = student_lophoc.id_lophocKN where id = ?" );
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String studentName = resultSet.getString("name");
                String dateOfBirth = resultSet.getString("dateOfBirth");
                String address = resultSet.getString("address");
                int lophocId = resultSet.getInt("id_lophoc");
                String lophocName = resultSet.getString("name_lophoc");
                Student student = new Student(id,studentName,dateOfBirth,address);
                Lophoc lophoc = new Lophoc(lophocId,lophocName);
                viewAll = new ViewAll(student,lophoc);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return viewAll;
    }
}
