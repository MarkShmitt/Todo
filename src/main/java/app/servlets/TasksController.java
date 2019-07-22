package app.servlets;

import app.DAO.TaskDAO;
import app.entities.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TasksController extends HttpServlet {
    private TaskDAO daoTask = TaskDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        try {
        switch (action) {
            case "/insert":
                addTask(req, resp);
                break;
            case "/delete":
                deleteTask(req, resp);
                break;
            case "/update":
                updateTask(req, resp);
                break;
            default:
                listTasks(req, resp);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        boolean complete = Boolean.valueOf(req.getParameter("completeFlag"));
        Task newTask = new Task(title, description, complete);
        daoTask.save(newTask);
        resp.sendRedirect("list");
    }

    private void listTasks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("MainPage.jsp");
        List<Task> tasks = daoTask.findALL();
        req.setAttribute("tasks", tasks);
        dispatcher.forward(req, resp);
    }

    private void deleteTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        Task task = new Task(id);
        daoTask.delete(task);
        resp.sendRedirect("list");
    }

    private void updateTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        boolean completeFlag = Boolean.parseBoolean(req.getParameter("completeFlag"));
        Task task = new Task(id, title, description, completeFlag);
        daoTask.update(task);
        resp.sendRedirect("list");
    }
}
