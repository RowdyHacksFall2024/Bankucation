package com.example.bankucation;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

public class QuizActivity extends AppCompatActivity {

    Dictionary dictionary;
    private Map.Entry<String, String> currentEntry;
    private Iterator<Map.Entry<String, String>> iterator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Lesson lesson = loadQuestions();

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