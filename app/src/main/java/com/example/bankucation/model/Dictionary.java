package com.example.bankucation.model;

import android.content.res.AssetManager;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.bankucation.DictionaryActivity;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dictionary implements Parcelable {
    Map<String, String> dictionary;

    public Dictionary(){
        dictionary = new HashMap<>();

    }

    protected Dictionary(Parcel in) {
    }

    public static final Creator<Dictionary> CREATOR = new Creator<Dictionary>() {
        @Override
        public Dictionary createFromParcel(Parcel in) {
            return new Dictionary(in);
        }

        @Override
        public Dictionary[] newArray(int size) {
            return new Dictionary[size];
        }
    };

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
    public void loadDictionary(DictionaryActivity activity){
        AssetManager manager= activity.getAssets();
        try{
            InputStream file = manager.open("test.csv");
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                dictionary.put(parts[0], parts[1]);
            }
        }catch(Exception e){

            throw new RuntimeException(e);

        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
    }
}
