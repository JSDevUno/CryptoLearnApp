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
        add("Another Topics");
    }};

    // Map for storing lessons with descriptions
    private final Map<String, List<Lesson>> lessonsMap = new HashMap<String, List<Lesson>>() {{
        put("Introduction", List.of(
                new Lesson("IntroLessonTitle 1", "IntroLessonDecription1"),
                new Lesson("IntroLessonTitle 2", "IntroLessonDecription2"),
                new Lesson("IntroLessonTitle 3", "IntroLessonDecription3")
        ));
        put("History", List.of(
                new Lesson("HistoryLessonTitle 1", "HistoryLessonDecription1"),
                new Lesson("HistoryLessonTitle 2", "HistoryLessonDecription2"),
                new Lesson("HistoryLessonTitle 3", "HistoryLessonDecription3"),
                new Lesson("HistoryLessonTitle 4", "HistoryLessonDecription4")
        ));
        put("Blockchain Networks", List.of(
                new Lesson("BlockchainLessonTitle 1", "BlockchainLessonDecription1"),
                new Lesson("BlockchainLessonTitle 2", "BlockchainLessonDecription2")
        ));
        put("Advanced Topics", List.of(
                new Lesson("AdvancedLessonTitle 1", "AdvancedLessonDecription1"),
                new Lesson("AdvancedLessonTitle 2", "AdvancedLessonDecription2"),
                new Lesson("AdvancedLessonTitle 3", "AdvancedLessonDecription3"),
                new Lesson("AdvancedLessonTitle 4", "AdvancedLessonDecription4"),
                new Lesson("AdvancedLessonTitle 5", "AdvancedLessonDecription5")
        ));
        put("Other Topics", List.of(
                new Lesson("OtherLessonTitle 1", "OtherLessonDecription1"),
                new Lesson("OtherLessonTitle 2", "OtherLessonDecription2"),
                new Lesson("OtherLessonTitle 3", "OtherLessonDecription3"),
                new Lesson("OtherLessonTitle 4", "OtherLessonDecription4"),
                new Lesson("OtherLessonTitle 5", "OtherLessonDecription5")
        ));
        put("Another Topics", List.of(
                new Lesson("AnotherLessonTitle 1", "AnotherLessonDecription1"),
                new Lesson("AnotherLessonTitle 2", "AnotherLessonDecription2"),
                new Lesson("AnotherLessonTitle 3", "AnotherLessonDecription3"),
                new Lesson("AnotherLessonTitle 4", "AnotherLessonDecription4"),
                new Lesson("AnotherLessonTitle 5", "AnotherLessonDecription5")
        ));
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

    public List<Lesson> getLessons(String category) {
        return lessonsMap.getOrDefault(category, new ArrayList<>());
    }

    public void addCategory(String category, List<Lesson> lessons) {
        categories.add(category);
        lessonsMap.put(category, lessons);
    }

    // Inner class to represent a Lesson with a title and description
    public static class Lesson {
        private final String title;
        private final String description;

        public Lesson(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }
}
