package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.*;
import static ru.javawebinar.topjava.util.ValidationUtil.*;
import static ru.javawebinar.topjava.web.SecurityUtil.*;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public MealTo get(int id) {
        log.info("get {}", id);
        return createWithExcess(service.get(id, authUserId()), false);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, authUserId());
    }

    public MealTo create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        assureEntityAssigned(meal, authUserId());
        return createWithExcess(service.create(meal), false);
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        assureEntityAssigned(meal, authUserId());
        service.update(meal, authUserId());
    }

    public List<MealTo> getAll() {
        log.info("getAll");
        return getWithExcess(service.getAll(authUserId()), authUserCaloriesPerDay());
    }

    public List<MealTo> getFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        log.info("getFiltered {} {} - {} {}", startDate, startTime, endDate, endTime);
        return getFilteredWithExcess(
                service.getAll(authUserId()),
                authUserCaloriesPerDay(),
                startTime == null ? LocalTime.MIN : startTime,
                endTime == null ? LocalTime.MAX : endTime,
                startDate == null ? LocalDate.MIN : startDate,
                endDate == null ? LocalDate.MAX : endDate
        );
    }
}