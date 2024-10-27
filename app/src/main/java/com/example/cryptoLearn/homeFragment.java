package com.example.cryptoLearn;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.graphics.Paint;


public class homeFragment extends Fragment {

    private LinearLayout lessonCardContainer;
    private TextView categoryIntro;
    private TextView categoryHistory;
    private TextView categoryBlockchainNet;

    // Define lesson content for each category
    private final Map<String, List<String>> lessonsMap = new HashMap<String, List<String>>() {{
        put("Introduction", Arrays.asList("Lesson Name", "Lesson Name", "Lesson Name"));
        put("History", Arrays.asList("Lesson Name", "Lesson Name", "Lesson Name", "Lesson Name", "Lesson Name", "Lesson Name", "Lesson Name", "Lesson Name", "Lesson Name"));
        put("Blockchain Networks", Arrays.asList("Lesson Name", "Lesson Name", "Lesson Name"));
    }};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Find views
        lessonCardContainer = view.findViewById(R.id.lesson_card_container);
        categoryIntro = view.findViewById(R.id.category_intro);
        categoryHistory = view.findViewById(R.id.category_history);
        categoryBlockchainNet = view.findViewById(R.id.category_blockchain_net);

        // Set click listeners for each category
        categoryIntro.setOnClickListener(v -> updateLessons("Introduction", categoryIntro));
        categoryHistory.setOnClickListener(v -> updateLessons("History", categoryHistory));
        categoryBlockchainNet.setOnClickListener(v -> updateLessons("Blockchain Networks", categoryBlockchainNet));

        updateLessons("Introduction", categoryIntro);

        return view;
    }

    // Update the lesson section based on the selected category and style the selected category
    private void updateLessons(String category, TextView selectedCategoryTextView) {
        // Clear previous lessons
        lessonCardContainer.removeAllViews();

        // Reset all categories to default style
        resetCategoryStyles();

        // Style the selected category
        selectedCategoryTextView.setTypeface(selectedCategoryTextView.getTypeface(), Typeface.BOLD_ITALIC);
        selectedCategoryTextView.setPaintFlags(selectedCategoryTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        selectedCategoryTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));

        // Get lessons for the selected category
        List<String> lessons = lessonsMap.get(category);

        // Convert 8dp to pixels
        int marginTop8dp = getResources().getDimensionPixelSize(R.dimen.margin_top_8dp);

        // Load the "Righteous" font from resources
        Typeface righteousFont = ResourcesCompat.getFont(requireContext(), R.font.righteous);

        // Add each lesson as a TextView in the lesson container
        if (lessons != null) {
            for (int index = 0; index < lessons.size(); index++) {
                String lesson = lessons.get(index);
                TextView lessonTextView = new TextView(requireContext());
                lessonTextView.setText(lesson);
                lessonTextView.setPadding(40, 40, 40, 40);
                lessonTextView.setBackgroundResource(R.drawable.lesson_cards);
                lessonTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.check_outline, 0);
                lessonTextView.setCompoundDrawablePadding(8);
                lessonTextView.setTextSize(17f);
                lessonTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                lessonTextView.setTypeface(righteousFont); // Apply the Righteous font

                // Set top margin of 8dp for all items except the first one
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                if (index > 0) {
                    layoutParams.topMargin = marginTop8dp;
                }
                lessonTextView.setLayoutParams(layoutParams);

                // Add the TextView to the container
                lessonCardContainer.addView(lessonTextView);
            }
        }
    }

    // Reset all categories to default style
    private void resetCategoryStyles() {
        int defaultColor = ContextCompat.getColor(requireContext(), R.color.black);

        TextView[] categoryTextViews = {categoryIntro, categoryHistory, categoryBlockchainNet};

        // Reset each category TextView to default styling
        for (TextView textView : categoryTextViews) {
            textView.setTypeface(Typeface.DEFAULT); // Set to default typeface (normal, no bold or italic)
            textView.setPaintFlags(textView.getPaintFlags() & ~Paint.UNDERLINE_TEXT_FLAG);
            textView.setTextColor(defaultColor);
            textView.invalidate();  // Force refresh to apply styling immediately
        }
    }
}
