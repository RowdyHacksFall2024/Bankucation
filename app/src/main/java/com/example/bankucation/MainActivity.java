package com.example.bankucation;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bankucation.model.Lesson;
import com.example.bankucation.model.Question;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Zachary code
    private Lesson lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Zachary Code
        loadQuestions();
//        System.out.println(getLesson()); // FIXME crashes app

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button learn = findViewById(R.id.learn_btn);
        Button dictionary = findViewById(R.id.dictionary_btn);


        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "your learning now", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, LessonActivity.class);
                startActivity(intent);
            }

        });

        dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "dictionary", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, DictionaryActivity.class);
                startActivity(intent);
            }

        });
    }

    // Zachary code
    public void loadQuestions() {
        Log.d("Load Questions", "Get Assets");
        ArrayList<Question> quizBank = new ArrayList<Question>();
        AssetManager assetManager = this.getAssets();
        try {
            InputStream inputStream = assetManager.open("questions.txt");
            quizBank = Lesson.loadQuizBank(inputStream);
            lesson = new Lesson(quizBank);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Lesson getLesson() {
        return lesson;
    }
}