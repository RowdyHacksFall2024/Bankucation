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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class LessonActivity extends AppCompatActivity {
    Dictionary dictionary;
    private Map.Entry<String, String> currentEntry;
    private Iterator<Map.Entry<String, String>> iterator;


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
        Button quiz_btn = findViewById((R.id.quiz_btn));
        ProgressBar progressBar = findViewById(R.id.progressBar);

        TextView term_textView = findViewById(R.id.term_textView);
        TextView definition_textView = findViewById(R.id.definition_textView);

        Lesson lesson = loadQuestions();

        iterator = dictionary.getDictionary().entrySet().iterator();
        if (iterator.hasNext()) {
            Map.Entry<String, String> currentEntry = iterator.next();
            term_textView.setText(currentEntry.getKey());
            definition_textView.setText(currentEntry.getValue());
        }

//        term_textView.setText(dictionary.getDictionary().get(lesson.getQuestionIndex()).getQuestionText());
//        definition_textView.setText(lesson.getQuizBank().get(lesson.getQuestionIndex()).getAnswerText());

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int progress= progressBar.getProgress();
                progressBar.setProgress(progress+10);
                if (progress +10  == 90)
                Toast.makeText(LessonActivity.this,"Almost There...",Toast.LENGTH_SHORT).show();
                if (progress >= 100) {
                    Toast.makeText(LessonActivity.this,"Quiz Time!",Toast.LENGTH_SHORT).show();
                    quiz_btn.setVisibility(View.VISIBLE);
                }
                if (iterator.hasNext()) {
                    Map.Entry<String, String> currentEntry = iterator.next();
                    term_textView.setText(currentEntry.getKey());
                    definition_textView.setText(currentEntry.getValue());
                } else {
                    Intent questionIntent = new Intent(LessonActivity.this, QuestionActivity.class);
                    startActivity(questionIntent);
                }
            }
        });

        quiz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LessonActivity.this, QuestionActivity.class);
                startActivity(intent);
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

//            Dictionary dictionary = new Dictionary();
            try {
                createDictionary();
            } catch (Exception e){
                throw new RuntimeException(e);
            }

            return new Lesson(dictionary, quizBank);    // FIXME holds empty dictionary
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    // Could not load questions
    }

    private void createDictionary() throws IOException {
        Dictionary dictionary = new Dictionary();
        dictionary.loadDictionary(this);
        this.dictionary = dictionary;
    }
}