package com.example.cryptoLearn.education;

import com.example.cryptoLearn.R;

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
                new Lesson("IntroLessonTitle 1", "IntroLessonDecription1",  R.drawable.coins),
                new Lesson("IntroLessonTitle 2", "IntroLessonDecription2",  R.drawable.circle_background),
                new Lesson("IntroLessonTitle 3", "IntroLessonDecription3",  R.drawable.circle_background)
        ));
        put("History", List.of(
                new Lesson("HistoryLessonTitle 1", "HistoryLessonDecription1",  R.drawable.circle_background),
                new Lesson("HistoryLessonTitle 2", "HistoryLessonDecription2",  R.drawable.circle_background),
                new Lesson("HistoryLessonTitle 3", "HistoryLessonDecription3",  R.drawable.circle_background),
                new Lesson("HistoryLessonTitle 4", "HistoryLessonDecription4",  R.drawable.circle_background)
        ));
        put("Blockchain Networks", List.of(
                new Lesson("BlockchainLessonTitle 1", "BlockchainLessonDecription1",  R.drawable.circle_background),
                new Lesson("BlockchainLessonTitle 2", "BlockchainLessonDecription2",  R.drawable.circle_background)
        ));
        put("Advanced Topics", List.of(
                new Lesson("AdvancedLessonTitle 1", "AdvancedLessonDecription1",  R.drawable.circle_background),
                new Lesson("AdvancedLessonTitle 2", "AdvancedLessonDecription2",  R.drawable.circle_background),
                new Lesson("AdvancedLessonTitle 3", "AdvancedLessonDecription3",  R.drawable.circle_background),
                new Lesson("AdvancedLessonTitle 4", "AdvancedLessonDecription4",  R.drawable.circle_background),
                new Lesson("AdvancedLessonTitle 5", "AdvancedLessonDecription5",  R.drawable.circle_background)
        ));
        put("Other Topics", List.of(
                new Lesson("OtherLessonTitle 1", "OtherLessonDecription1",  R.drawable.circle_background),
                new Lesson("OtherLessonTitle 2", "OtherLessonDecription2",  R.drawable.circle_background),
                new Lesson("OtherLessonTitle 3", "OtherLessonDecription3",  R.drawable.circle_background),
                new Lesson("OtherLessonTitle 4", "OtherLessonDecription4",  R.drawable.circle_background),
                new Lesson("OtherLessonTitle 5", "OtherLessonDecription5",  R.drawable.circle_background)
        ));
        put("Another Topics", List.of(
                new Lesson("AnotherLessonTitle 1", "AnotherLessonDecription1",  R.drawable.circle_background),
                new Lesson("AnotherLessonTitle 2", "AnotherLessonDecription2",  R.drawable.circle_background),
                new Lesson("AnotherLessonTitle 3", "AnotherLessonDecription3",  R.drawable.circle_background)
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
        private final int imageResourceId; // Add an image resource field

        public Lesson(String title, String description, int imageResourceId) {
            this.title = title;
            this.description = description;
            this.imageResourceId = imageResourceId;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
        public int getImageResourceId() {
            return imageResourceId;
        }
    }
}
