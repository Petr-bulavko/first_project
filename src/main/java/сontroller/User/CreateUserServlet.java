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

@WebServlet("/user/create")
public class CreateUserServlet extends HttpServlet {

    BasicDao<User> userBasicDao = new UserBasicDaoImp();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/createUser.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String name = request.getParameter("name");
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");
            User users = new User(name, mail, password);
            userBasicDao.create(users);
            response.sendRedirect(request.getContextPath() + "/");
        } catch (Exception ex) {

            getServletContext().getRequestDispatcher("/create").forward(request, response);
        }
    }
}