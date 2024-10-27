package com.example.bankucation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bankucation.model.Dictionary;

import java.util.Iterator;
import java.util.Map;

public class DictionaryActivity extends AppCompatActivity {
    Dictionary dictionary;
    TextView termView;
    TextView definition;
    private Map.Entry<String, String> currentEntry;
    private Iterator <Map.Entry<String, String>> iterator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dictionary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        createDictionary();
        termView = findViewById(R.id.termView);
        definition = findViewById(R.id.definition);
        Button next = findViewById(R.id.next);
        iterator = dictionary.getDictionary().entrySet().iterator();
        if (iterator.hasNext()) {
            Map.Entry<String, String> currentEntry = iterator.next();
            termView.setText(currentEntry.getKey());
            definition.setText(currentEntry.getValue());
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iterator.hasNext()) {
                    Map.Entry<String, String> currentEntry = iterator.next();
                    termView.setText(currentEntry.getKey());
                    definition.setText(currentEntry.getValue());
                } else {
                    iterator = dictionary.getDictionary().entrySet().iterator();
                    if (iterator.hasNext()) {
                        Map.Entry<String, String> currentEntry = iterator.next();
                        termView.setText(currentEntry.getKey());
                        definition.setText(currentEntry.getValue());
                    } else {
                        termView.setText("");
                        definition.setText("");
                    }
                }

            }
        });
    }


    private void createDictionary(){
        Dictionary dictionary = new Dictionary();
        dictionary.loadDictionary(this);
        this.dictionary = dictionary;
    }







}