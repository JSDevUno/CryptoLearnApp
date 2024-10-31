package com.example.cryptoLearn;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
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

import com.example.cryptoLearn.education.Categories;
import com.example.cryptoLearn.education.CategoryRepository;

import java.util.List;

public class homeFragment extends Fragment {

    private LinearLayout lessonCardContainer;
    private LinearLayout categoryNamesContainer;
    private CategoryRepository repository;
    private LinearLayout seeAllLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        lessonCardContainer = view.findViewById(R.id.lesson_card_container);
        categoryNamesContainer = view.findViewById(R.id.category_names);
        seeAllLayout = view.findViewById(R.id.see_all);
        repository = CategoryRepository.getInstance();

        loadCategories();

        // Display lessons for the first category by default if categories are available
        List<String> categoriesList = repository.getCategories();
        if (!categoriesList.isEmpty()) {
            updateLessons(categoriesList.get(0));
        }

        seeAllLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Categories.class);
            startActivity(intent);
        });
        return view;
    }

    private void loadCategories() {
        List<String> categoriesList = repository.getCategories();
        for (String category : categoriesList) {
            TextView categoryTextView = new TextView(requireContext());
            categoryTextView.setText(category);
            categoryTextView.setPadding(16, 8, 16, 8);
            categoryTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            categoryTextView.setTextSize(16);
            categoryTextView.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.righteous));
            categoryTextView.setGravity(Gravity.CENTER);
            categoryTextView.setClickable(true);

            // Add click listener to update lessons
            categoryTextView.setOnClickListener(v -> updateLessons(category));

            // Add to the category container
            categoryNamesContainer.addView(categoryTextView);
        }
    }

    private void updateLessons(String category) {
        lessonCardContainer.removeAllViews();
        resetCategoryStyles();
        highlightSelectedCategory(category);

        List<CategoryRepository.Lesson> lessons = repository.getLessons(category);

        int marginTop8dp = getResources().getDimensionPixelSize(R.dimen.margin_top_8dp);
        Typeface righteousFont = ResourcesCompat.getFont(requireContext(), R.font.righteous);

        if (lessons != null) {
            for (int index = 0; index < lessons.size(); index++) {
                TextView lessonTextView = new TextView(requireContext());
                lessonTextView.setText(lessons.get(index).getTitle());
                lessonTextView.setPadding(40, 40, 40, 40);
                lessonTextView.setBackgroundResource(R.drawable.lesson_cards);
                lessonTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.check_outline, 0);
                lessonTextView.setCompoundDrawablePadding(8);
                lessonTextView.setTextSize(17f);
                lessonTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                lessonTextView.setTypeface(righteousFont);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                if (index > 0) {
                    layoutParams.topMargin = marginTop8dp;
                }
                lessonTextView.setLayoutParams(layoutParams);

                lessonCardContainer.addView(lessonTextView);
            }
        }
    }

    private void resetCategoryStyles() {
        int defaultColor = ContextCompat.getColor(requireContext(), R.color.black);
        for (int i = 0; i < categoryNamesContainer.getChildCount(); i++) {
            TextView textView = (TextView) categoryNamesContainer.getChildAt(i);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setPaintFlags(textView.getPaintFlags() & ~Paint.UNDERLINE_TEXT_FLAG);
            textView.setTextColor(defaultColor);
        }
    }

    private void highlightSelectedCategory(String category) {
        int highlightColor = ContextCompat.getColor(requireContext(), R.color.highlight);
        for (int i = 0; i < categoryNamesContainer.getChildCount(); i++) {
            TextView textView = (TextView) categoryNamesContainer.getChildAt(i);
            if (textView.getText().toString().equals(category)) {
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD_ITALIC);
                textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                textView.setTextColor(highlightColor);
                break;
            }
        }
    }
}
