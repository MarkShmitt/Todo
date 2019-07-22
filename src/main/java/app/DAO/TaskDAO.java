package app.DAO;

import app.entities.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements DaoTask {
    private TaskDAO(){
    }

    private static class SingletonHelper {
        private static final TaskDAO INSTANCE = new TaskDAO();
    }

    public static TaskDAO getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to create JDBC db connection " + e.toString() + e.getMessage());
        }
        DriverManager.registerDriver(new org.postgresql.Driver());
        String DATABASE_URL = "jdbc:postgresql://ec2-54-246-84-100.eu-west-1.compute.amazonaws.com:5432/dc7aha6528ehqp?sslmode=require";
        String USER = "lnzzlviqcbhhxh";
        String PASS = "14a418bd1fcb4b52516f5ed28ca6cfe61fd1947eeb1b6fac1648e27b1c0cd650";
        return DriverManager.getConnection(DATABASE_URL, USER, PASS);
    }

    @Override
    public List<Task> findALL() throws SQLException {
        List<Task> listTasks = new ArrayList<>();
        String SQL = "SELECT * FROM todolist";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                int id = rs.getInt("task_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                boolean complete = rs.getBoolean("completeFlag");

                Task task = new Task(id, title, description, complete);
                listTasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listTasks;
    }

    @Override
    public boolean save(Task task) {
        String SQL = "INSERT INTO todolist(title, description, completeFlag) VALUES(?, ?, ?)";
        boolean rowInserted = false;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setBoolean(3, task.getCompleteFlag());

            rowInserted = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rowInserted;
    }

    @Override
    public boolean update(Task task) throws SQLException{
        String SQL = "UPDATE todolist SET title = ?, description = ?, completeFlag = ? WHERE task_id = ?";
        boolean rowUpdated = false;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setBoolean(3, task.getCompleteFlag());
            pstmt.setInt(4, task.getId());

            rowUpdated = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(Task task) throws SQLException{
        String SQL = "DELETE FROM todolist WHERE task_id = ?";
        boolean rowDeleted = false;
        try(Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, task.getId());

            rowDeleted = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rowDeleted;
    }
}
