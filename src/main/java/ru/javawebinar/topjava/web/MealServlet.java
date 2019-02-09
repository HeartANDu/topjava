package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.InMemoryMealDaoImpl;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
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
        log.debug("forwarding to meals page");

        List<MealTo> meals = MealsUtil.getFilteredWithExcess(mealDao.getAllMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
        req.setAttribute("meals", meals);
        req.setAttribute("dateFormat", TimeUtil.FORMATTER);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
