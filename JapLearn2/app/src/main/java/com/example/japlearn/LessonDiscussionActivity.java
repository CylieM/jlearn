package com.example.japlearn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LessonDiscussionActivity extends AppCompatActivity {

    private static final String TAG = "LessonDiscussionActivity";
    private TextView tvFront, tvPronunciation, tvDesc, tvBtnName, tvBtnExample, tvRomaji;
    private ImageView ivArrow;
    private int currentLessonIndex = 0;
    private int lessonCount = 0;
    private boolean isRomajiName = true; // Flag to track if Romaji is currently set to Name or Examples

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons_discussion);

        // Initialize views
        tvFront = findViewById(R.id.tvFront);
        tvPronunciation = findViewById(R.id.tvPronunciation);
        tvRomaji = findViewById(R.id.tvRomaji);
        tvDesc = findViewById(R.id.tvDesc);
        tvBtnName = findViewById(R.id.tvBtnName);
        tvBtnExample = findViewById(R.id.tvBtnExample);
        ivArrow = findViewById(R.id.ivArrow);

        // Initialize Firebase Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Fetch data from Firestore
        db.collection("lessons")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        lessonCount = task.getResult().size();
                        if (lessonCount > 0) {
                            loadLesson(currentLessonIndex);
                        }
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });

        // Set up click listener for btnNext
        findViewById(R.id.btnNext).setOnClickListener(v -> {
            if (currentLessonIndex < lessonCount - 1) {
                currentLessonIndex++;
                if (currentLessonIndex == 4) { // Transition to Lesson421 when reaching Lesson1.5
                    startActivity(new Intent(LessonDiscussionActivity.this, Lesson421.class));
                } else {
                    loadLesson(currentLessonIndex);
                }
            }
        });

        // Set up click listener for btnPrev
        findViewById(R.id.btnPrev).setOnClickListener(v -> {
            if (currentLessonIndex > 0) {
                currentLessonIndex--;
                loadLesson(currentLessonIndex);
            }
        });

        // Set up click listeners for the buttons
        tvBtnName.setOnClickListener(v -> {
            if (!isRomajiName) {
                tvRomaji.setText("Romaji Name");
                tvDesc.setText(currentRomaji); // Show the current romaji description
                setArrowMarginLeft(490); // Set margin for Name
                isRomajiName = true;
            }
        });

        tvBtnExample.setOnClickListener(v -> {
            if (isRomajiName) {
                tvRomaji.setText("Romaji Examples");
                tvDesc.setText(currentExamples); // Show the current examples description
                setArrowMarginLeft(860); // Set margin for Example
                isRomajiName = false;
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

    private String currentRomaji = "";
    private String currentExamples = "";

    private void displayLesson(DocumentSnapshot document) {
        // Get data from Firestore document
        String character = document.getString("character");
        String pronunciation = document.getString("pronunciation");
        currentRomaji = document.getString("romaji");
        currentExamples = document.getString("examples");

        // Display the data in the appropriate TextViews
        tvFront.setText(character);
        tvPronunciation.setText(pronunciation);

        if (isRomajiName) {
            tvRomaji.setText("Romaji Name");
            tvDesc.setText(currentRomaji); // Initially set to Romaji description
        } else {
            tvRomaji.setText("Romaji Examples");
            tvDesc.setText(currentExamples); // Initially set to Examples description
        }
    }

    private void setArrowMarginLeft(int marginLeft) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) ivArrow.getLayoutParams();
        params.leftMargin = marginLeft;
        ivArrow.setLayoutParams(params);
    }
}
