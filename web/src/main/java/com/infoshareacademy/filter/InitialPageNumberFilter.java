package com.infoshareacademy.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(
        filterName = "PageNumberFilter",
        urlPatterns = {"/list"},
        initParams = {
                @WebInitParam(name = "page", value = "1")
        })
public class InitialPageNumberFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) {

    }


    @Override
    public void destroy() {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       
        String reqParameter = servletRequest.getParameter("page");
        if (reqParameter == null || reqParameter.isEmpty()) {

            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/list?page=1");
            requestDispatcher.forward(servletRequest, servletResponse);

        } else if (!reqParameter.matches("-?(0|[1-9]\\d*)")){
            servletRequest.setAttribute("page", 1);
        }

        filterChain.doFilter(servletRequest, servletResponse);


    }



}
