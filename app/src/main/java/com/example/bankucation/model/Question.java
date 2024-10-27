package com.example.bankucation.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Question implements Parcelable {
    // class variables
    private String questionText;
    private String answerText;
    private ArrayList<String> otherChoices;
    //Variables inside of each Question-MJ
    public Question(String questionText,String answerText, ArrayList<String> otherChoices) {
        this.questionText = questionText;
        this.answerText = answerText;
        this.otherChoices = otherChoices;
    } //Constructor for each Question-MJ

    protected Question(Parcel in) {
        questionText = in.readString();
        answerText = in.readString();
        otherChoices = in.createStringArrayList();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    //questionText
    public String getQuestionText() {                     //GETTERS&SETTERS-MJ
        return questionText;
    }
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    //answerText
    public String getAnswerText() {
        return answerText;
    }
    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    //otherChoices
    public ArrayList<String> getOtherChoices() {
        return otherChoices;
    }
    public void setOtherChoices(ArrayList<String> otherChoices) {
        this.otherChoices = otherChoices;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(questionText);
        dest.writeString(answerText);
        dest.writeStringList(otherChoices);
    }
}
