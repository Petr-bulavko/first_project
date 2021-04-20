package сontroller.Film;

import dao.BasicDao;
import dao.FilmBasicDaoImp;
import model.Film;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/film/create")
public class CreateFilmServlet extends HttpServlet {

    BasicDao<Film> filmBasicDao = new FilmBasicDaoImp();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/createFilm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String filmName = request.getParameter("filmName");
            int filmYear = Integer.parseInt(request.getParameter("filmYear"));
            String filmGenre = request.getParameter("filmGenre");
            String filmCountry = request.getParameter("filmCountry");
            Film film = new Film(filmName, filmYear, filmGenre, filmCountry);
            filmBasicDao.create(film);
            response.sendRedirect(request.getContextPath() + "/");
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/create").forward(request, response);
        }
    }
}