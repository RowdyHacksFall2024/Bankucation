package com.example.bankucation;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bankucation.model.Dictionary;
import com.example.bankucation.model.Lesson;
import com.example.bankucation.model.Question;

import java.io.InputStream;
import java.util.ArrayList;

public class LessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lesson);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button next_btn = findViewById(R.id.next_btn);
//        Button skip_btn = findViewById(R.id.skip_btn);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        TextView term_textView = findViewById(R.id.term_textView);
        TextView definition_textView = findViewById(R.id.definition_textView);

        Lesson lesson = loadQuestions();

        term_textView.setText(lesson.getQuizBank().get(lesson.getQuestionIndex()).getQuestionText());
        definition_textView.setText(lesson.getQuizBank().get(lesson.getQuestionIndex()).getAnswerText());


        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int progress= progressBar.getProgress();
                progressBar.setProgress(progress+10);
                Toast.makeText(LessonActivity.this,"Almost There",Toast.LENGTH_SHORT).show();
            }
        });

        // Zachary code
        // Skip Button Listener
//        skip_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {    // Skip button pressed
//
//                // Open completion window
//                Intent completionIntent = new Intent(LessonActivity.this, CompletionActivity.class);
//                startActivity(completionIntent);
//            }
//        });
    }
    public Lesson loadQuestions() {
        Log.d("Load Questions", "Get Assets");
        ArrayList<Question> quizBank = new ArrayList<Question>();
        AssetManager assetManager = this.getAssets();
        try {
            InputStream inputStream = assetManager.open("questions.xml");
            quizBank = Lesson.loadQuizBank(inputStream);

            Dictionary dictionary = new Dictionary();
//            dictionary.loadDictionary(DictionaryActivity.class);    // FIXME method wants DictionaryActivity but this is main activity

            return new Lesson(dictionary, quizBank);    // FIXME holds empty dictionary
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    // Could not load questions
    }
}