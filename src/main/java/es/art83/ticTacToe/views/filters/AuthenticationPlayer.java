package es.art83.ticTacToe.views.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.art83.ticTacToe.controllers.ControllerFactory;
import es.art83.ticTacToe.models.utils.TicTacToeStateModel;

@WebFilter("/logged/*")
public class AuthenticationPlayer implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ControllerFactory controllerFactory = (ControllerFactory) ((HttpServletRequest) request)
                .getSession().getAttribute("controllerFactory");
        TicTacToeStateModel ticTacToeStateModel = controllerFactory.getTicTacToeApplicationModel();
        if (ticTacToeStateModel == TicTacToeStateModel.INITIAL
                || ticTacToeStateModel == TicTacToeStateModel.FINAL) {
            ((HttpServletResponse) response).sendRedirect("../login.xhtml");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
