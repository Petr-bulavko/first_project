package org.example.projectWebsite.servlet;

import org.example.projectWebsite.command.Command;
import org.example.projectWebsite.command.CommandFactory;
import org.example.projectWebsite.command.CommandResult;
import org.example.projectWebsite.command.NavigationType;
import org.example.projectWebsite.dbconnection.ConnectionPool;
import org.example.projectWebsite.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/mainController")
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().closePool();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionPool.getInstance();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");
        Command command = CommandFactory.defineCommand(commandName);
        CommandResult result;
        try {
            result = command.execute(request);
            switch (result.getNavigationType()){
                case FORWARD:
                    getServletContext().getRequestDispatcher(result.getPage()).forward(request, response);
                    break;
                case REDIRECT:
                    response.sendRedirect(result.getPage());
                    break;
                default:
                    throw new EnumConstantNotPresentException(NavigationType.class, result.getNavigationType().name());
            }
        } catch (CommandException e) {
            throw new ServletException("Exception during execute command", e);
        }
    }
}
