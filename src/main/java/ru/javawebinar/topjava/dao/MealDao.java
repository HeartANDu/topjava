package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    void addMeal(Meal meal);

    void deleteMeal(int id);

    void updateMeal(Meal meal);

    Meal getMeal(int id);

    List<Meal> getAllMeals();
}
