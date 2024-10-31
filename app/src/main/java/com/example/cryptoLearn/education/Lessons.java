package com.example.cryptoLearn.education;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.cryptoLearn.R;
import com.google.android.material.imageview.ShapeableImageView;

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
        List<CategoryRepository.Lesson> lessons = CategoryRepository.getInstance().getLessons(category);

        // Load the font using ResourcesCompat for compatibility with older SDKs
        Typeface righteousFont = ResourcesCompat.getFont(this, R.font.righteous);

        // Display each lesson as a card
        for (int i = 0; i < lessons.size(); i++) {
            CategoryRepository.Lesson lesson = lessons.get(i);

            // Create the main card layout
            LinearLayout lessonCard = new LinearLayout(this);
            lessonCard.setOrientation(LinearLayout.VERTICAL);
            lessonCard.setPadding(16, 16, 16, 16);
            lessonCard.setBackground(ContextCompat.getDrawable(this, R.drawable.header_home));
            lessonCard.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            lessonCard.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.card));
            lessonCard.setElevation(4);

            // Title TextView
            TextView title = new TextView(this);
            title.setText(lesson.getTitle()); // Set the title from Lesson object
            title.setTextSize(25);
            title.setTextColor(ContextCompat.getColor(this, R.color.black));
            title.setTypeface(righteousFont);
            lessonCard.addView(title);

            // Lesson Number TextView
            TextView lessonNumber = new TextView(this);
            lessonNumber.setText("Lesson " + (i + 1)); // Dynamic lesson number
            lessonNumber.setTextSize(18);
            lessonNumber.setTextColor(ContextCompat.getColor(this, R.color.black));
            lessonNumber.setTypeface(righteousFont);
            lessonCard.addView(lessonNumber);

            // Horizontal layout for icon and description
            LinearLayout horizontalLayout = new LinearLayout(this);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
            horizontalLayout.setPadding(0, 10, 0, 0);

            // ShapeableImageView for the lesson icon
            ShapeableImageView icon = new ShapeableImageView(this);
            icon.setLayoutParams(new LinearLayout.LayoutParams(60, 60));
            icon.setPadding(5, 5, 5, 5);
            icon.setStrokeWidth(3);
            icon.setStrokeColor(ContextCompat.getColorStateList(this, R.color.btn));
            icon.setImageResource(R.drawable.circle_background);
            horizontalLayout.addView(icon);

            // Description TextView
            TextView description = new TextView(this);
            description.setText(lesson.getDescription()); // Set the description from Lesson object
            description.setTextSize(15);
            description.setTextColor(ContextCompat.getColor(this, R.color.black));
            description.setTypeface(righteousFont);
            description.setPadding(16, 0, 0, 0);
            horizontalLayout.addView(description);

            // Add horizontal layout to the card
            lessonCard.addView(horizontalLayout);

            // Add the card to the main container
            lessonsContainer.addView(lessonCard);
        }
    }
}
