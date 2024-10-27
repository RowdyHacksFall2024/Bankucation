package com.example.bankucation;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

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

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    Lesson lesson = loadQuestions();

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