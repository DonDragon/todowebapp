package ua.od.hillel.todo.utils;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.InternalResourceView;

public class JstlView extends InternalResourceView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setContentType("text/html;charset=UTF-8");
        // Determine the path for the request dispatcher.
        String dispatcherPath = prepareForRendering(request, response);

        // set original view being asked for as a request parameter
        request.setAttribute("partial", dispatcherPath );

    // force everything to be template.jsp
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/_template.jsp");
        for (String n : model.keySet()) {
            request.setAttribute(n, model.get(n));
        }

        requestDispatcher.include(request, response);

    }

}