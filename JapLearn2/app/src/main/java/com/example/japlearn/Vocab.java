package com.example.japlearn;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Vocab extends AppCompatActivity {

    private static final String TAG = "Vocab";
    private TextView tvFrontVocab;
    private EditText txtUserInput;
    private ImageButton btnPrev, btnNext;
    private int currentLessonIndex = 1;
    private int lessonCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);

        // Initialize views
        tvFrontVocab = findViewById(R.id.tvFrontVocab);
        txtUserInput = findViewById(R.id.txtUserInputVocab);
        btnPrev = findViewById(R.id.btnPrevVocab);
        btnNext = findViewById(R.id.btnNextVocab);

        // Initialize Firebase Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Load the current lesson
        loadLesson(currentLessonIndex);

        // Set up click listener for btnNext
        btnNext.setOnClickListener(v -> {
            if (currentLessonIndex < lessonCount - 1) {
                currentLessonIndex++;
                if (currentLessonIndex == 4) { // Return to MainActivity when reaching Lesson1.9
                    startActivity(new Intent(Vocab.this, MainActivity.class));
                } else {
                    // Clear text and reset background color
                    txtUserInput.setText("");
                    txtUserInput.setBackgroundColor(getResources().getColor(R.color.white));
                    loadLesson(currentLessonIndex); // Load the next lesson
                }
            }
        });


        // Set up click listener for btnPrev
        btnPrev.setOnClickListener(v -> {
            if (currentLessonIndex > 0) {
                currentLessonIndex--;
                if (currentLessonIndex == 4) { // Return to MainActivity when reaching Lesson1.4
                    startActivity(new Intent(Vocab.this, MainActivity.class));
                } else {
                    loadLesson(currentLessonIndex);
                }
            }
        });

        // Check user input when pressing Enter
        txtUserInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                checkUserInput(txtUserInput.getText().toString().trim());
                return true;
            }
            return false;
        });
    }

    private void loadLesson(int index) {
        // Construct the document ID based on the lesson index
        String documentId = "Lesson2." + (index + 1);

        // Fetch the lesson document
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Vocabulary")
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
        String character = document.getString("word");
        String answer = document.getString("answer");

        // Display the data in the appropriate TextViews
        tvFrontVocab.setText(character);

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

    private void checkUserInput(String userInput) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String documentId = "Lesson2." + (currentLessonIndex + 1);
        db.collection("Vocabulary")
                .document(documentId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String answer = documentSnapshot.getString("answer");
                        if (userInput.equalsIgnoreCase(answer)) {
                            runOnUiThread(() -> txtUserInput.setBackgroundColor(getResources().getColor(R.color.green)));
                        } else {
                            runOnUiThread(() -> txtUserInput.setBackgroundColor(getResources().getColor(R.color.red)));
                        }
                    } else {
                        Log.e(TAG, "Document does not exist: " + documentId);
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error getting lesson document", e);
                });
    }

}
