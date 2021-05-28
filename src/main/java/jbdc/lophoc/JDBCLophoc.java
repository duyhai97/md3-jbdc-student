package jbdc.lophoc;

import model.Lophoc;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCLophoc implements JDBCLophocIntreface{

    Connection getConnection(){
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
    public List<Lophoc> selectAll() {
        List<Lophoc> lophocList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select  * from lophoc");
//            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");

                lophocList.add(new Lophoc(id,name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lophocList;
    }

    @Override
    public void create(Lophoc lophoc) {

    }

    @Override
    public Lophoc selectById(int id) {
        return null;
    }

    @Override
    public void edit(int id, Lophoc user) throws SQLException {

    }

    @Override
    public void delete(int id) {

    }
}
