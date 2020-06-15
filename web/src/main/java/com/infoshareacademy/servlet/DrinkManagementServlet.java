package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.Category;
import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.CategoryService;
import com.infoshareacademy.service.DrinkService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/drink_management")
public class DrinkManagementServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(DrinkManagementServlet.class.getName());

    @Inject
    private TemplateProvider templateProvider;

    @EJB
    private DrinkService drinkService;

    @EJB
    private CategoryService categoryService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, Object> dataModel = new HashMap<>();

        ContextHolder contextHolder = new ContextHolder(req.getSession());
        dataModel.put("name", contextHolder.getName());
        dataModel.put("role", contextHolder.getRole());

        Template template = templateProvider.getTemplate(getServletContext(), "addDrinkForm.html");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (
                TemplateException e) {
            packageLogger.error(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Drink drink = new Drink();

        Category category = categoryService.getOrCreate(req.getParameter("category"));

        drink.setDrinkName(req.getParameter("name"));
        drink.setRecipe(req.getParameter("recipe"));
        drink.setCategory(category);
        drink.setAlcoholStatus(req.getParameter("status"));
        drink.setDate(LocalDateTime.now());

        drinkService.save(drink);

    }



}