package com.example.cryptoLearn.education;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.imageview.ShapeableImageView;
import com.example.cryptoLearn.R;

import java.util.List;

public class Categories extends AppCompatActivity {

    private GridLayout gridLayout;

    // Array of drawable resource IDs for each category
    /*private final int[] categoryImages = {
            R.drawable.image1,  // Replace with actual drawable names
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        gridLayout = findViewById(R.id.gridLayout);
        populateCategoryCards();
    }

    private void populateCategoryCards() {
        List<String> categoriesList = CategoryRepository.getInstance().getCategories();

        for (int i = 0; i < categoriesList.size(); i++) {
            String category = categoriesList.get(i);

            LinearLayout cardLayout = new LinearLayout(this);
            cardLayout.setOrientation(LinearLayout.VERTICAL);
            cardLayout.setGravity(Gravity.CENTER);
            cardLayout.setPadding(16, 16, 16, 16);
            cardLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.field_background));

            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = 0;
            layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            layoutParams.setMargins(10, 10, 10, 10);
            cardLayout.setLayoutParams(layoutParams);

            // ShapeableImageView for the image
            ShapeableImageView imageView = new ShapeableImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    300
            ));
            imageView.setPadding(30, 30, 30, 30);
            imageView.setScaleType(ShapeableImageView.ScaleType.FIT_XY);
            imageView.setStrokeWidth(3);
            imageView.setStrokeColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.highlight)));
            imageView.setBackgroundColor(ColorStateList.valueOf(0xFFA9A9A9).getDefaultColor()); // REMOVE THIS IF I HAVE AN IMAGE ALREADY

            // Set the specific drawable for this category, or a fallback color if there are more categories than images
            /*if (i < categoryImages.length) {
                imageView.setImageResource(categoryImages[i]);
            } else {
                imageView.setBackgroundColor(ColorStateList.valueOf(0xFFA9A9A9).getDefaultColor());
            }*/

            // TextView for category title
            TextView titleText = new TextView(this);
            titleText.setText("Category:");
            titleText.setTextColor(ContextCompat.getColor(this, R.color.card));
            titleText.setTextSize(18);
            titleText.setGravity(Gravity.CENTER);
            titleText.setPadding(15, 15, 15, 0);
            titleText.setTypeface(ResourcesCompat.getFont(this, R.font.righteous));

            // TextView for dynamic category name
            TextView categoryTitle = new TextView(this);
            categoryTitle.setText(category);
            categoryTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            categoryTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
            categoryTitle.setPadding(15, 15, 15, 30);

            // Add components to the card layout
            cardLayout.addView(imageView);
            cardLayout.addView(titleText);
            cardLayout.addView(categoryTitle);

            // Add card layout to GridLayout
            gridLayout.addView(cardLayout);
        }
    }
}
