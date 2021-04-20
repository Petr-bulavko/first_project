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

@WebServlet("/user/edit")
public class EditUserServlet extends HttpServlet {
    BasicDao<User> userBasicDao = new UserBasicDaoImp();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            User users = userBasicDao.getById(id);

            if (users != null) {
                request.setAttribute("users", users);
                getServletContext().getRequestDispatcher("/editUser.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");
            User user = new User(id, name, mail, password);
            userBasicDao.updateById(user);
            response.sendRedirect(request.getContextPath() + "/");
        } catch (Exception ex) {

            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }
}