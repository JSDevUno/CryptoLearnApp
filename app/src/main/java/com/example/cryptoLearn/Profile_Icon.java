package com.example.cryptoLearn;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.ShapeAppearanceModel;

public class Profile_Icon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_icon);

        ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        int itemCount = 6;

        // Array of prices
        Integer[] prices = {1000, 1500, 2000, 2500, 3000, 3500};
        // Array of drawable resource IDs
        /*int[] imageResources = {
                R.drawable.image1, // replace with actual drawable names
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image6
        };*/

        for (int i = 0; i < itemCount; i++) {
            // Create container LinearLayout for each item
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setGravity(Gravity.CENTER);
            itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            // Create ShapeableImageView for the profile icon
            ShapeableImageView imageView = new ShapeableImageView(this);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(200, 200);
            imageParams.setMargins(0, 0, 0, 16); // Add bottom margin
            imageView.setLayoutParams(imageParams);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setImageResource(R.drawable.circle_background); // Set each image from array -> imageResources[i]
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setShapeAppearanceModel(
                    ShapeAppearanceModel.builder()
                            .setAllCornerSizes(ShapeAppearanceModel.PILL) // Makes it circular
                            .build()
            );
            imageView.setStrokeColor(ContextCompat.getColorStateList(this, R.color.highlight));
            imageView.setStrokeWidth(15f);
            itemLayout.addView(imageView);

            final int index = i; // Final variable to use inside click listener
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showConfirmationDialog(index);
                }
            });

            // Create a LinearLayout to hold CL label and number
            LinearLayout textLayout = new LinearLayout(this);
            textLayout.setOrientation(LinearLayout.HORIZONTAL);
            textLayout.setGravity(Gravity.CENTER);

            // Create TextView for "CL" label
            TextView clText = new TextView(this);
            clText.setText("CL");
            clText.setTextColor(getResources().getColor(R.color.highlight));
            clText.setTextSize(14); // Adjust font size
            textLayout.addView(clText);

            // Create TextView for "1000" text
            TextView numberText = new TextView(this);
            numberText.setText(String.valueOf(prices[i]));
            numberText.setTextColor(getResources().getColor(R.color.white));
            numberText.setTextSize(14); // Adjust font size
            textLayout.addView(numberText);

            // Add the text layout to the item layout
            itemLayout.addView(textLayout);

            // Set GridLayout.LayoutParams with margins for spacing
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = GridLayout.LayoutParams.WRAP_CONTENT;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.setMargins(30, 20, 30, 20); // Set spacing between items
            params.setGravity(Gravity.CENTER);
            itemLayout.setLayoutParams(params);

            // Add item layout to GridLayout
            gridLayout.addView(itemLayout);
        }
    }
    private void showConfirmationDialog(int index) {
        // Inflate the custom layout for the dialog
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_confirmation, null);

        // Build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        // Set up dialog buttons
        AlertDialog dialog = builder.create();

        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        Button confirmButton = dialogView.findViewById(R.id.confirmButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); // Dismiss dialog on cancel
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action for confirm button
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
