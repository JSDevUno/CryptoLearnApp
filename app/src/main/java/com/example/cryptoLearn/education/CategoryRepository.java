package com.example.cryptoLearn.education;

import com.example.cryptoLearn.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryRepository {

    private static CategoryRepository instance;

    // List of categories
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
                new Lesson("IntroLessonTitle 1", "IntroLessonDescription1", R.drawable.coins),
                new Lesson("IntroLessonTitle 2", "IntroLessonDescription2", R.drawable.circle_background),
                new Lesson("IntroLessonTitle 3", "IntroLessonDescription3", R.drawable.circle_background)
        ));
        put("History", List.of(
                new Lesson("HistoryLessonTitle 1", "HistoryLessonDescription1", R.drawable.circle_background),
                new Lesson("HistoryLessonTitle 2", "HistoryLessonDescription2", R.drawable.circle_background),
                new Lesson("HistoryLessonTitle 3", "HistoryLessonDescription3", R.drawable.circle_background),
                new Lesson("HistoryLessonTitle 4", "HistoryLessonDescription4", R.drawable.circle_background)
        ));
        put("Blockchain Networks", List.of(
                new Lesson("BlockchainLessonTitle 1", "BlockchainLessonDescription1", R.drawable.circle_background),
                new Lesson("BlockchainLessonTitle 2", "BlockchainLessonDescription2", R.drawable.circle_background)
        ));
        put("Advanced Topics", List.of(
                new Lesson("AdvancedLessonTitle 1", "AdvancedLessonDescription1", R.drawable.circle_background),
                new Lesson("AdvancedLessonTitle 2", "AdvancedLessonDescription2", R.drawable.circle_background),
                new Lesson("AdvancedLessonTitle 3", "AdvancedLessonDescription3", R.drawable.circle_background),
                new Lesson("AdvancedLessonTitle 4", "AdvancedLessonDescription4", R.drawable.circle_background),
                new Lesson("AdvancedLessonTitle 5", "AdvancedLessonDescription5", R.drawable.circle_background)
        ));
        put("Other Topics", List.of(
                new Lesson("OtherLessonTitle 1", "OtherLessonDescription1", R.drawable.circle_background),
                new Lesson("OtherLessonTitle 2", "OtherLessonDescription2", R.drawable.circle_background),
                new Lesson("OtherLessonTitle 3", "OtherLessonDescription3", R.drawable.circle_background),
                new Lesson("OtherLessonTitle 4", "OtherLessonDescription4", R.drawable.circle_background),
                new Lesson("OtherLessonTitle 5", "OtherLessonDescription5", R.drawable.circle_background)
        ));
        put("Another Topics", List.of(
                new Lesson("AnotherLessonTitle 1", "AnotherLessonDescription1", R.drawable.circle_background),
                new Lesson("AnotherLessonTitle 2", "AnotherLessonDescription2", R.drawable.circle_background),
                new Lesson("AnotherLessonTitle 3", "AnotherLessonDescription3", R.drawable.circle_background)
        ));
    }};

    // Map for storing quiz questions per category
    private final Map<String, List<Question>> questionMap = new HashMap<String, List<Question>>() {{
        put("Introduction", List.of(
                new Question("What is Blockchain?", List.of("A ledger", "A chain", "A currency", "A coin"), 0),
                new Question("Who invented Bitcoin?", List.of("Elon Musk", "Satoshi Nakamoto", "Vitalik Buterin", "Charlie Lee"), 1)
        ));
        put("History", List.of(
                new Question("When was Bitcoin launched?", List.of("2009", "2010", "2008", "2011"), 0),
                new Question("Which is the first cryptocurrency?", List.of("Ethereum", "Bitcoin", "Litecoin", "Ripple"), 1)
        ));
        put("Blockchain Networks", List.of(
                new Question("What is Ethereum primarily used for?", List.of("Smart contracts", "Payments", "File storage", "VPN"), 0),
                new Question("Which consensus mechanism does Bitcoin use?", List.of("Proof of Work", "Proof of Stake", "Delegated Proof of Stake", "Proof of Authority"), 0)
        ));
        put("Advanced Topics", List.of(
                new Question("What is DeFi?", List.of("Centralized finance", "Decentralized finance", "Traditional banking", "Peer-to-peer lending"), 1),
                new Question("What does DAO stand for?", List.of("Data Access Object", "Digital Assets Online", "Decentralized Autonomous Organization", "Digital Account Operator"), 2)
        ));
        put("Other Topics", List.of(
                new Question("Which coin is considered the first stablecoin?", List.of("Tether", "Bitcoin", "Ethereum", "Litecoin"), 0),
                new Question("What is mining?", List.of("Minting new tokens", "Verifying transactions", "Creating smart contracts", "Generating private keys"), 1)
        ));
        put("Another Topics", List.of(
                new Question("What is a private key?", List.of("A public identifier", "A personal password", "A cryptographic signature", "A secure digital key"), 3),
                new Question("What is a wallet in crypto?", List.of("A place to store crypto", "A banking application", "A ledger of transactions", "A type of blockchain"), 0)
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

    public List<Question> getQuestions(String category) {
        return questionMap.getOrDefault(category, new ArrayList<>());
    }

    // Method to add a category
    public void addCategory(String category, List<Lesson> lessons, List<Question> questions) {
        categories.add(category);
        lessonsMap.put(category, lessons);
        questionMap.put(category, questions);
    }

    // Inner class to represent a Lesson with a title, description, and image
    public static class Lesson {
        private final String title;
        private final String description;
        private final int imageResourceId;

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

    // Inner class to represent a Question with text, choices, and the correct answer index
    public static class Question {
        private final String text;
        private final List<String> choices;
        private final int correctAnswer;

        public Question(String text, List<String> choices, int correctAnswer) {
            this.text = text;
            this.choices = choices;
            this.correctAnswer = correctAnswer;
        }

        public String getText() {
            return text;
        }

        public List<String> getChoices() {
            return choices;
        }

        public int getCorrectAnswer() {
            return correctAnswer;
        }
    }
}
