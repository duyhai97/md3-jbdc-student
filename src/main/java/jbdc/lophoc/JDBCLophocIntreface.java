package jbdc.lophoc;

import model.Lophoc;
import model.Student;

import java.sql.SQLException;
import java.util.List;

public interface JDBCLophocIntreface {
    List<Lophoc> selectAll();

    void create(Lophoc lophoc);

    Lophoc selectById(int id);

    void edit(int id, Lophoc user) throws SQLException;

    void delete(int id);

}
