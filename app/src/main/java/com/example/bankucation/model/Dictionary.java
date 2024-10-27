package com.example.bankucation.model;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.annotation.NonNull;

import com.example.bankucation.DictionaryActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {
    Map<String, String> dictionary;

    public Dictionary(){
        dictionary = new HashMap<>();

    }

    @NonNull
    @Override
    public String toString() {
        return "Dictionary{" +
                "dictionary=" + dictionary +
                '}';
    }

    public void setDictionary(Map<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }
    public void loadDictionary(Context context) throws IOException {
        AssetManager assetManager = context.getAssets();
            InputStream file = assetManager.open("test.csv");
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                dictionary.put(parts[0], parts[1]);
            }
        }
    }


