package com.example.bankucation.model;

import java.util.ArrayList;

public class Lesson {
    // Define dictionaryBank and quizBank as ArrayLists
    private ArrayList<String> dictionaryBank;
    private ArrayList<String> quizBank;

    // Constructor
    public Lesson(ArrayList<String> dictionaryBank, ArrayList<String> quizBank) {
        this.dictionaryBank = dictionaryBank;
        this.quizBank = quizBank;
    }

    // Getter for dictionaryBank
    public ArrayList<String> getDictionaryBank() {
        return dictionaryBank;
    }

    // Setter for dictionaryBank
    public void setDictionaryBank(ArrayList<String> dictionaryBank) {
        this.dictionaryBank = dictionaryBank;
    }

    // Getter for quizBank
    public ArrayList<String> getQuizBank() {
        return quizBank;
    }

    // Setter for quizBank
    public void setQuizBank(ArrayList<String> quizBank) {
        this.quizBank = quizBank;
    }

    // Method to add an entry to dictionaryBank
    public void addDictionaryEntry(String entry) {
        this.dictionaryBank.add(entry);
    }

    // Method to add a question to quizBank
    public void addQuizQuestion(String question) {
        this.quizBank.add(question);
    }
}