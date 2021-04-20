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

@WebServlet("/film/edit")
public class EditFilmServlet extends HttpServlet {
    BasicDao<Film> filmBasicDao = new FilmBasicDaoImp();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Film film = filmBasicDao.getById(id);

            if(film.getId()!=0) {
                request.setAttribute("film", film);
                getServletContext().getRequestDispatcher("/editFilm.jsp").forward(request, response);
            }
            else {
                request.setAttribute("id", id);
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
            }
        }
        catch(Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String filmName = request.getParameter("filmName");
            int filmYear = Integer.parseInt(request.getParameter("filmYear"));
            String filmGenre = request.getParameter("filmGenre");
            String filmCountry = request.getParameter("filmCountry");
            Film film = new Film(id, filmName, filmYear, filmGenre, filmCountry);
            filmBasicDao.updateById(film);
            response.sendRedirect(request.getContextPath() + "/");
        }
        catch(Exception ex) {

            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }
}