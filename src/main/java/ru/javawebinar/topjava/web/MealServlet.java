package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.InMemoryMealDaoImpl;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealDao mealDao;

    public MealServlet() {
        super();
        mealDao = new InMemoryMealDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            log.debug("Meals list page");

            List<MealTo> meals = MealsUtil.getFilteredWithExcess(mealDao.getAllMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
            req.setAttribute("meals", meals);
            req.setAttribute("dateFormat", TimeUtil.FORMATTER);
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        } else {
            log.debug(String.format("Meal update page. Meal id: %s", id));

            req.setAttribute("meal", mealDao.getMeal(Integer.parseInt(id)));
            req.setAttribute("dateFormat", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            req.getRequestDispatcher("/mealUpdate.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        log.debug(req.getParameter("nothing") == null ? "isNull" : "isNotNull");
        switch (req.getParameter("operation")) {
            case "create":
                if (validateParameters(req, Arrays.asList("dateTime", "description", "calories"))) {
                    mealDao.addMeal(new Meal(
                            0,
                            LocalDateTime.parse(req.getParameter("dateTime")),
                            req.getParameter("description"),
                            Integer.parseInt(req.getParameter("calories"))
                    ));

                    log.debug("Added a meal");
                }

                break;
            case "update":
                if (validateParameters(req, Arrays.asList("id", "dateTime", "description", "calories"))) {
                    mealDao.updateMeal(new Meal(
                            Integer.parseInt(req.getParameter("id")),
                            LocalDateTime.parse(req.getParameter("dateTime")),
                            req.getParameter("description"),
                            Integer.parseInt(req.getParameter("calories"))
                    ));

                    log.debug(String.format("Updated meal with id: %s", req.getParameter("id")));
                }

                break;
            case "delete":
                if (validateParameters(req, Arrays.asList("id"))) {
                    mealDao.deleteMeal(Integer.parseInt(req.getParameter("id")));

                    log.debug(String.format("Deleted meal with id: %s", req.getParameter("id")));
                }

                break;
        }

        resp.sendRedirect("meals");
    }

    private boolean validateParameters(HttpServletRequest request, List<String> parameters) {
        for (String parameter : parameters) {
            if (request.getParameter(parameter) == null || request.getParameter(parameter).isEmpty()) {
                return false;
            }
        }

        return true;
    }
}
