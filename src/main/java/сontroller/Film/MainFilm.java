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
import java.util.List;

@WebServlet("/film")
public class MainFilm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicDao<Film> filmBasicDao = new FilmBasicDaoImp();
        List<Film> film = filmBasicDao.getAll();
        req.setAttribute("film", film);
        getServletContext().getRequestDispatcher("/adminFilms.jsp").forward(req, resp);
    }
}
