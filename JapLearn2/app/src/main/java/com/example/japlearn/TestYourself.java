package com.example.japlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TestYourself extends AppCompatActivity {

    private List<String> words = new ArrayList<>();
    private List<String> answers = new ArrayList<>();
    private int currentWordIndex = 0;
    private int correctCount = 0;
    private int incorrectCount = 0;
    private int roundCount = 1; // Added round counter
    private boolean answerSubmitted = false; // Flag to track if answer has been submitted

    private TextView tvCorrect;
    private TextView tvIncorrect;
    private TextView tvRound;
    private ImageButton btnPrevTest;
    private ImageButton btnNextTest;
    private EditText txtUserInputTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_yourself);

        // Initialize views
        tvCorrect = findViewById(R.id.tvCorrect);
        tvRound = findViewById(R.id.tvRound);
        tvIncorrect = findViewById(R.id.tvIncorrect);
        btnPrevTest = findViewById(R.id.btnPrevTest);
        btnNextTest = findViewById(R.id.btnNextTest);
        txtUserInputTest = findViewById(R.id.txtUserInputTest);

        // Disable prev button initially
        btnPrevTest.setEnabled(false);

        // Load data from Firestore
        loadDataFromFirestore();

        // Set click listener for Next button
        btnNextTest.setOnClickListener(v -> {
            // Clear user input text and reset background color
            txtUserInputTest.setText("");
            txtUserInputTest.setBackgroundColor(ContextCompat.getColor(TestYourself.this, android.R.color.white));
            answerSubmitted = false; // Reset the flag when moving to the next word

            // Check if the current word is the last one
            if (currentWordIndex < words.size() - 1) {
                currentWordIndex++;
                displayWord();
            } else {
                // End of test, reset counters and show message
                Toast.makeText(TestYourself.this, "End of Test", Toast.LENGTH_SHORT).show();
            }

            // Check if it's the end of the round
            if (roundCount == 5) { // Assuming 5 lessons per round
                roundCount = 1; // Reset round counter
                currentWordIndex = 0; // Reset to first lesson
                resetCounters(); // Reset counters
            } else {
                // Increment round count and display it
                roundCount++;
            }
            tvRound.setText("Round " + roundCount + " / 5");
        });

        // Disable prev button click listener
        btnPrevTest.setOnClickListener(null);

        // Set Enter key listener for txtUserInputTest
        txtUserInputTest.setOnEditorActionListener((v, actionId, event) -> {
            if ((actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) && !answerSubmitted) {
                String userAnswer = txtUserInputTest.getText().toString();
                submitAnswer(userAnswer);
                return true;
            }
            return false;
        });
    }

    private void loadDataFromFirestore() {
        // Access Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Retrieve data from Firestore collection
        db.collection("TestYourself")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null) {
                            for (QueryDocumentSnapshot document : querySnapshot) {
                                // Assuming each document in "TestYourself" collection contains "word" and "answer" fields
                                String word = document.getString("word");
                                String answer = document.getString("answer");
                                words.add(word);
                                answers.add(answer);
                            }
                            // Display first word
                            displayWord();
                        } else {
                            // Error handling
                            Toast.makeText(TestYourself.this, "No documents found", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Error handling
                        Toast.makeText(TestYourself.this, "Error loading data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void displayWord() {
        // Display word at current index
        TextView tvFrontTest = findViewById(R.id.tvFrontTest);
        tvFrontTest.setText(words.get(currentWordIndex));
    }

    private void resetCounters() {
        // Reset correct and incorrect counters
        correctCount = 0;
        incorrectCount = 0;
        updateCounters();
    }

    private void updateCounters() {
        // Update text views with correct and incorrect counts
        tvCorrect.setText("Correct: " + correctCount);
        tvIncorrect.setText("Incorrect: " + incorrectCount);
    }

    // Check user input and update background color
    private void submitAnswer(String userAnswer) {
        String correctAnswer = answers.get(currentWordIndex);
        boolean isCorrect = userAnswer.equalsIgnoreCase(correctAnswer);
        checkAnswer(isCorrect);

        // Set background color based on correctness
        if (isCorrect) {
            txtUserInputTest.setBackgroundColor(ContextCompat.getColor(TestYourself.this, R.color.green));
        } else {
            txtUserInputTest.setBackgroundColor(ContextCompat.getColor(TestYourself.this, R.color.red));
        }

        answerSubmitted = true; // Set the flag to indicate answer has been submitted
    }

    // Check answer and update counters
    private void checkAnswer(boolean isCorrect) {
        if (isCorrect) {
            correctCount++;
        } else {
            incorrectCount++;
        }
        updateCounters();
    }
}
