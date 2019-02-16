package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {

    Meal create(Meal meal);

    void delete(int id, int userId);

    Meal get(int id, int userId);

    void update(Meal meal, int userId);

    List<Meal> getAll(int userId);
}