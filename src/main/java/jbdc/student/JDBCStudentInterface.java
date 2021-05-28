package jbdc.student;

import model.Student;
import model.ViewAll;

import java.sql.SQLException;
import java.util.List;

public interface JDBCStudentInterface  {

    List<Student> selectAll();

    boolean create(Student student, int[] p );

    ViewAll selectById(int id);

    void edit(int id, Student user) throws SQLException;

    void delete(int id);

    List<ViewAll> viewAll();

    ViewAll viewByID(int id);

}
