package com.example.eduquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/*
 * MAD Lab Program — Educational Quiz App
 * ─────────────────────────────────────────────────────────────────
 * Aim  : Build a Mixed-Subject Educational Quiz Android App with:
 *        • 10 Questions across 5 subjects
 *        • 15-second countdown timer per question
 *        • Colour-coded correct / wrong feedback
 *        • Score tracking with SharedPreferences (High Score)
 *        • Result screen with grade and percentage
 * ─────────────────────────────────────────────────────────────────
 *
 * Files:
 *   MainActivity.java    → Home / Welcome screen
 *   QuizActivity.java    → Quiz question + timer screen
 *   ResultActivity.java  → Final score / result screen
 *   Question.java        → Data model
 *
 *   activity_main.xml    → Home layout
 *   activity_quiz.xml    → Quiz layout
 *   activity_result.xml  → Result layout
 * ─────────────────────────────────────────────────────────────────
 */
public class MainActivity extends AppCompatActivity {

    // SharedPreferences key for storing high score
    public static final String PREFS_NAME  = "QuizPrefs";
    public static final String KEY_BEST    = "bestScore";

    private Button   btnStartQuiz;
    private TextView tvHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartQuiz = findViewById(R.id.btnStartQuiz);
        tvHighScore  = findViewById(R.id.tvHighScore);

        // Load and display best score from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int best = prefs.getInt(KEY_BEST, 0);
        tvHighScore.setText("Best Score: " + best + " / 10");

        // Navigate to QuizActivity on click
        btnStartQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuizActivity.class);
            startActivity(intent);
        });
    }

    // Refresh high score when returning from ResultActivity
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int best = prefs.getInt(KEY_BEST, 0);
        tvHighScore.setText("Best Score: " + best + " / 10");
    }
}
