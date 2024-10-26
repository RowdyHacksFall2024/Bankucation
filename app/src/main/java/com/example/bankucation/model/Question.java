package com.example.bankucation.model;

import java.util.ArrayList;

public class Question {
    private String questionText;
    private String answerText;
    private ArrayList<String> otherChoices;
    //Variables inside of each Question-MJ
    public Question(String questionText,String answerText, ArrayList<String> otherChoices) {
        this.questionText = questionText;
        this.answerText = answerText;
        this.otherChoices = otherChoices;
    } //Constructor for each Question-MJ

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
}
