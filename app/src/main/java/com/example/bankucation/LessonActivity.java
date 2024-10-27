package com.example.bankucation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bankucation.model.Question;

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
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Button quiz_btn = findViewById(R.id.quiz_btn);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int progress= progressBar.getProgress();
                progressBar.setProgress(progress+10);
                if (progress >80)
                Toast.makeText(LessonActivity.this,"Almost There",Toast.LENGTH_SHORT).show();

                if (progress + 10 >= 100) {
                    Toast.makeText(LessonActivity.this,"LESSON DONE!!!",Toast.LENGTH_SHORT).show();
                    quiz_btn.setVisibility(View.VISIBLE);
                }

            }
        });

        quiz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LessonActivity.this,"QUIZ TIME!!!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LessonActivity.this,QuestionActivity.class);
                startActivity(intent);
            }
        });



    }

}