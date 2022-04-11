package org.example.projectWebsite.filter;

import org.example.projectWebsite.exception.ServiceException;
import org.example.projectWebsite.model.Film;
import org.example.projectWebsite.service.FilmService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = {"/index.jsp", "/jsp/user/authorizedUsersPageFilms.jsp", "/jsp/user/usersWithoutRegistrationPageFilms.jsp"})
public class AllFilmsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        FilmService service = new FilmService();
        try {
            List<Film> allFilms = service.findAllFilms();
            request.setAttribute("allFilms", allFilms);
            filterChain.doFilter(request, servletResponse);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
