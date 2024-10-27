package com.example.bankucation;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class QuestionActivity extends AppCompatActivity {

    Dictionary dictionary;
    TextView termView;
    TextView definition;
    private Map.Entry<String, String> currentEntry;
    private Iterator<Map.Entry<String, String>> iterator;

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

        Lesson lesson = loadQuestions();
        Button nextQuestion_btn = findViewById(R.id.nextQuestion_btn);

        TextView questionView = findViewById(R.id.questionView);
        TextView answerView = findViewById(R.id.answerView);

        RadioButton radioButton1 = findViewById(R.id.radioButton1);
        RadioButton radioButton2 = findViewById(R.id.radioButton2);
        RadioButton radioButton3 = findViewById(R.id.radioButton3);


        questionView.setText(lesson.getQuizBank().get(lesson.getQuestionIndex()).getQuestionText());
//        answerView.setText(lesson.getQuizBank().get(lesson.getQuestionIndex()).getAnswerText());
        for (int i = 0; i < lesson.getQuizBank().get(lesson.getQuestionIndex()).getOtherChoices().size(); i++) {
            if (i == 0) {
                radioButton1.setText(lesson.getQuizBank().get(lesson.getQuestionIndex()).getAnswerText());
            }
            if (i == 1) {
                radioButton2.setText(lesson.getQuizBank().get(lesson.getQuestionIndex()).getOtherChoices().get(i-1));
            }
            if (i == 2) {
                radioButton3.setText(lesson.getQuizBank().get(lesson.getQuestionIndex()).getOtherChoices().get(i-1));
            }
        }
        lesson.incQuestionIndex();

        nextQuestion_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButton1.isChecked()) {
                    Toast.makeText(QuestionActivity.this,"Correct!",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuestionActivity.this,"Wrong!",Toast.LENGTH_SHORT).show();
                }

                if (lesson.getQuestionIndex() < lesson.getQuizBank().size()) {
                    questionView.setText(lesson.getQuizBank().get(lesson.getQuestionIndex()).getQuestionText());
//                    answerView.setText(lesson.getQuizBank().get(lesson.getQuestionIndex()).getAnswerText());
                    for (int i = 0; i < lesson.getQuizBank().get(lesson.getQuestionIndex()).getOtherChoices().size(); i++) {
                        if (i == 0) {
                            radioButton1.setText(lesson.getQuizBank().get(lesson.getQuestionIndex()).getAnswerText());
                        }
                        if (i == 1) {
                            radioButton2.setText(lesson.getQuizBank().get(lesson.getQuestionIndex()).getOtherChoices().get(i-1));
                        }
                        if (i == 2) {
                            radioButton3.setText(lesson.getQuizBank().get(lesson.getQuestionIndex()).getOtherChoices().get(i-1));
                        }
                    }
                    lesson.incQuestionIndex();
                } else {
                    Intent completionIntent = new Intent(QuestionActivity.this, CompletionActivity.class);
                    startActivity(completionIntent);
                }
            }
        });

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