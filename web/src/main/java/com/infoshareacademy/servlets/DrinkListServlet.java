package com.infoshareacademy.servlets;

import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.testClasses.Drink;
import com.infoshareacademy.testClasses.DrinkService;
import com.infoshareacademy.testClasses.DrinksDatabase;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@WebServlet("/list")
public class DrinkListServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(TestSingleDrinkServlet.class.getName());

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        DrinkService.loadDrinkList();

        final List<Drink> drinkList = DrinksDatabase.getINSTANCE().getDrinks();
        final List<String> categoryList = DrinkService.getAllCategories(drinkList);

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("drinkList", drinkList);



        dataModel.put("categories", categoryList);

        Template template = templateProvider.getTemplate(getServletContext(), "receipeList.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.error(e.getMessage());
        }
    }
}