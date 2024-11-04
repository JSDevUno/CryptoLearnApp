package com.example.cryptoLearn.education;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.cryptoLearn.R;

import java.util.List;

public class Quiz extends AppCompatActivity {

    private TextView questionTextView, questionNumberTextView;
    private ProgressBar progressBar;
    private GridLayout optionsContainer;
    private Button nextQuestionButton;

    private List<CategoryRepository.Question> questions;
    private int currentQuestionIndex = 0;
    private Button selectedButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.question_text);
        questionNumberTextView = findViewById(R.id.question_number);
        progressBar = findViewById(R.id.progress_bar);
        optionsContainer = findViewById(R.id.answer_options);
        nextQuestionButton = findViewById(R.id.next_question_button);

        String selectedCategory = getIntent().getStringExtra("category");
        questions = CategoryRepository.getInstance().getQuestions(selectedCategory);

        if (questions != null && !questions.isEmpty()) {
            progressBar.setMax(questions.size());
            loadQuestion(currentQuestionIndex);
        } else {
            Toast.makeText(this, "No questions available", Toast.LENGTH_SHORT).show();
            finish();
        }

        nextQuestionButton.setOnClickListener(v -> {
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                loadQuestion(currentQuestionIndex);
            } else {
                Toast.makeText(this, "Quiz completed!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void loadQuestion(int index) {
        CategoryRepository.Question question = questions.get(index);

        questionTextView.setText(question.getText());
        questionNumberTextView.setText(String.format("%d/%d", index + 1, questions.size()));
        progressBar.setProgress(index + 1);

        optionsContainer.removeAllViews();

        for (int i = 0; i < question.getChoices().size(); i++) {
            String choiceText = question.getChoices().get(i);

            Button choiceButton = new Button(this);
            choiceButton.setText(choiceText);
            choiceButton.setTextColor(ContextCompat.getColor(this, R.color.btn));
            choiceButton.setBackgroundResource(R.drawable.camera_btn); // Apply custom background
            choiceButton.setPadding(16, 16, 16, 16);

            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = 0;
            layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            layoutParams.setMargins(8, 8, 8, 8);  // Add margin for spacing
            choiceButton.setLayoutParams(layoutParams);

            int finalI = i;
            choiceButton.setOnClickListener(view -> {
                if (selectedButton != null) {
                    selectedButton.setSelected(false); // Deselect previous button
                }
                choiceButton.setSelected(true); // Select new button
                selectedButton = choiceButton;

                if (finalI == question.getCorrectAnswer()) {
                    Toast.makeText(Quiz.this, "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Quiz.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                }
            });

            optionsContainer.addView(choiceButton);
        }
    }
}
