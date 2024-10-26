package com.example.bankucation.model;
import java.util.ArrayList;

public class Lesson {
    private ArrayList<Dictionary> dictionaryBank;
    private ArrayList<Question> quizBank;

    // Constructor
    public Lesson(ArrayList<Dictionary> dictionaryBank, ArrayList<Question> quizBank) {
        this.dictionaryBank = dictionaryBank;
        this.quizBank = quizBank;
    }

    // Dictionary Bank
    public ArrayList<Dictionary> getDictionaryBank() {
        return dictionaryBank;
    }
    public void setDictionaryBank(ArrayList<Dictionary> dictionaryBank) {
        this.dictionaryBank = dictionaryBank;
    }

    // Quiz Bank
    public ArrayList<Question> getQuizBank() {
        return quizBank;
    }
    public void setQuizBank(ArrayList<Question> quizBank) {
        this.quizBank = quizBank;
    }
    public void addQuestion(Question newQuestion) {
        quizBank.add(newQuestion);
    }
    public void removeQuestion(Question oldQuestion) {
        quizBank.remove(oldQuestion);
    }
    public void removeQuestion(int index) {
        quizBank.remove(index);
    }

    public void loadQuizBank(String fileName) {

    }
}
