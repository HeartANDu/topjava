package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealDaoImpl implements MealDao {
    private static final AtomicInteger CURRENT_ID = new AtomicInteger();
    private static final Map<Integer, Meal> MEAL_DB = new ConcurrentHashMap<>();

    static {
        MEAL_DB.put(CURRENT_ID.incrementAndGet(), new Meal(CURRENT_ID.get(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        MEAL_DB.put(CURRENT_ID.incrementAndGet(), new Meal(CURRENT_ID.get(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        MEAL_DB.put(CURRENT_ID.incrementAndGet(), new Meal(CURRENT_ID.get(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        MEAL_DB.put(CURRENT_ID.incrementAndGet(), new Meal(CURRENT_ID.get(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        MEAL_DB.put(CURRENT_ID.incrementAndGet(), new Meal(CURRENT_ID.get(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        MEAL_DB.put(CURRENT_ID.incrementAndGet(), new Meal(CURRENT_ID.get(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public void addMeal(Meal meal) {
        MEAL_DB.put(CURRENT_ID.incrementAndGet(), new Meal(
                CURRENT_ID.get(),
                meal.getDateTime(),
                meal.getDescription(),
                meal.getCalories()
        ));
    }

    @Override
    public void deleteMeal(int id) {
        MEAL_DB.remove(id);
    }

    @Override
    public void updateMeal(Meal meal) {
        MEAL_DB.put(meal.getId(), new Meal(
                meal.getId(),
                meal.getDateTime(),
                meal.getDescription(),
                meal.getCalories()
        ));
    }

    @Override
    public Meal getMeal(int id) {
        Meal meal = MEAL_DB.get(id);
        return new Meal(
                meal.getId(),
                meal.getDateTime(),
                meal.getDescription(),
                meal.getCalories()
        );
    }

    @Override
    public List<Meal> getAllMeals() {
        return MEAL_DB
                .values()
                .stream()
                .map(meal -> new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories()))
                .collect(Collectors.toList());
    }
}
