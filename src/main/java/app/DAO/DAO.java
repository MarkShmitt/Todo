package app.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T, ID> {
    List<T> findALL() throws SQLException;
    boolean save(T o) throws SQLException;
    boolean update(T o) throws SQLException;
    boolean delete(T o) throws SQLException;
}
