package com.example.cryptoLearn.education;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryRepository {

    private static CategoryRepository instance;

    private final List<String> categories = new ArrayList<String>() {{
        add("Introduction");
        add("History");
        add("Blockchain Networks");
        add("Advanced Topics");
        add("Other Topics");
    }};

    private final Map<String, List<String>> lessonsMap = new HashMap<String, List<String>>() {{
        put("Introduction", List.of("Lesson 1", "Lesson 2", "Lesson 3"));
        put("History", List.of("Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4"));
        put("Blockchain Networks", List.of("Lesson 1", "Lesson 2"));
        put("Advanced Topics", List.of("Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5"));
        put("Other Topics", List.of("Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5"));
    }};

    private CategoryRepository() {}

    public static synchronized CategoryRepository getInstance() {
        if (instance == null) {
            instance = new CategoryRepository();
        }
        return instance;
    }

    public List<String> getCategories() {
        return new ArrayList<>(categories);
    }

    public List<String> getLessons(String category) {
        return lessonsMap.getOrDefault(category, new ArrayList<>());
    }

    public void addCategory(String category, List<String> lessons) {
        categories.add(category);
        lessonsMap.put(category, lessons);
    }
}
