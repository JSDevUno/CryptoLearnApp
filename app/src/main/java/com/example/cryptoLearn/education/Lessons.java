package com.example.cryptoLearn.education;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.cryptoLearn.R;

import java.util.List;

public class Lessons extends AppCompatActivity {

    private LinearLayout lessonsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        lessonsContainer = findViewById(R.id.lessons_container);

        // Get the selected category from the intent
        String category = getIntent().getStringExtra("category");

        if (category != null) {
            setTitle("Lessons - " + category);
            displayLessonsForCategory(category);
        }
    }

    private void displayLessonsForCategory(String category) {
        // Fetch lessons from repository
        List<String> lessons = CategoryRepository.getInstance().getLessons(category);

        // Display each lesson as a card
        for (String lesson : lessons) {
            LinearLayout lessonCard = new LinearLayout(this);
            lessonCard.setOrientation(LinearLayout.VERTICAL);
            lessonCard.setBackground(ContextCompat.getDrawable(this, R.drawable.header_home));
            lessonCard.setPadding(16, 16, 16, 16);
            lessonCard.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            // Lesson title
            TextView lessonTitle = new TextView(this);
            lessonTitle.setText(lesson);
            lessonTitle.setTextSize(18);
            lessonTitle.setTextColor(ContextCompat.getColor(this, R.color.black));
            lessonTitle.setPadding(0, 8, 0, 4);

            // Add lesson title to the card
            lessonCard.addView(lessonTitle);

            // Add lesson card to the container
            lessonsContainer.addView(lessonCard);
        }
    }
}
