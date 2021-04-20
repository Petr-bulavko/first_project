package сontroller.User;

import dao.BasicDao;
import dao.UserBasicDaoImp;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class MainUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicDao<User> userBasicDao = new UserBasicDaoImp();
        List<User> users = userBasicDao.getAll();
        //Присваиваем users справа users слева чтобы вывести на jsp
        req.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/adminUsers.jsp").forward(req, resp);
    }
}
