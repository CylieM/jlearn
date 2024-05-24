package com.example.japlearn;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Lesson421 extends AppCompatActivity {

    private static final String TAG = "Lesson421";
    private TextView tvFront;
    private EditText txtUserInput;
    private ImageButton btnPrev, btnNext;
    private int currentLessonIndex = 4; // Start from Lesson1.5
    private int lessonCount = 8; // Total lessons including quizzes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson421);

        // Initialize views
        tvFront = findViewById(R.id.tvFront);
        txtUserInput = findViewById(R.id.txtUserInput);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);

        // Initialize Firebase Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Load the current lesson
        loadLesson(currentLessonIndex);

        // Set up click listener for btnNext
        btnNext.setOnClickListener(v -> {
            if (currentLessonIndex < lessonCount - 1) {
                currentLessonIndex++;
                if (currentLessonIndex == 4) { // Return to MainActivity when reaching Lesson1.9
                    startActivity(new Intent(Lesson421.this, MainActivity.class));
                } else {
                    // Clear text and reset background color
                    txtUserInput.setText("");
                    txtUserInput.setBackgroundColor(getResources().getColor(R.color.white));
                    loadLesson(currentLessonIndex);
                }
            }
        });

        // Set up click listener for btnPrev
        btnPrev.setOnClickListener(v -> {
            if (currentLessonIndex > 0) {
                currentLessonIndex--;
                if (currentLessonIndex == 4) { // Return to MainActivity when reaching Lesson1.4
                    startActivity(new Intent(Lesson421.this, MainActivity.class));
                } else {
                    loadLesson(currentLessonIndex);
                }
            }
        });
    }

    private void loadLesson(int index) {
        // Construct the document ID based on the lesson index
        String documentId = "Lesson1." + (index + 1);

        // Fetch the lesson document
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("lessons")
                .document(documentId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        displayLesson(documentSnapshot);
                    } else {
                        Log.e(TAG, "Document does not exist: " + documentId);
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error getting lesson document", e);
                });
    }

    private void displayLesson(DocumentSnapshot document) {
        // Get data from Firestore document
        String character = document.getString("character");
        String answer = document.getString("answer");

        // Display the data in the appropriate TextViews
        tvFront.setText(character);
        txtUserInput.setText(""); // Clear previous input

        // Check user input against the answer
        txtUserInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String userInput = txtUserInput.getText().toString().trim();
                if (userInput.equalsIgnoreCase(answer)) {
                    // If user input matches the answer, set background color to green
                    txtUserInput.setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    // If user input does not match the answer, set background color to red
                    txtUserInput.setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
        });
    }
}
