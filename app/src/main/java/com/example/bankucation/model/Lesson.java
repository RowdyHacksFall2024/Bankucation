package com.example.bankucation.model;
import android.content.res.AssetManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Lesson {
    private Dictionary dictionaryBank;
    private ArrayList<Question> quizBank;
    private int dictionaryIndex;
    private int questionIndex;

    // Constructor
//    public Lesson(ArrayList<Dictionary> dictionaryBank, ArrayList<Question> quizBank) {
    public Lesson(Dictionary dictionaryBank, ArrayList<Question> quizBank) {

        this.dictionaryBank = dictionaryBank;
        this.quizBank = quizBank;
        this.dictionaryIndex = 0;
        this.questionIndex = 0;
    }

    // Dictionary Bank
    public Dictionary getDictionaryBank() {
        return dictionaryBank;
    }
    public void setDictionaryBank(Dictionary dictionaryBank) {
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

    // Dictionary Index
    public int getDictionaryIndex() {
        return dictionaryIndex;
    }
    public void setDictionaryIndex(int dictionaryIndex) {
        this.dictionaryIndex = dictionaryIndex;
    }
    public void incDictionaryIndex() {  // Increment
        setDictionaryIndex(getDictionaryIndex() + 1);
    }

    // Question Index
    public int getQuestionIndex() {
        return questionIndex;
    }
    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }
    public void incQuestionIndex() {    // Increment
        setQuestionIndex(getQuestionIndex() + 1);
    }

    // Progress
    public int getTotalIndexes () {
        return getQuizBank().size() + getDictionaryBank().getDictionary().size();
    }
    public int getProgress() {
        double percentage = (double)(getQuestionIndex() + getDictionaryIndex()) / getTotalIndexes();
        return (int)percentage;
    }

    // Load arraylist of questions from XML file
    public static ArrayList<Question> loadQuizBank(InputStream inputStream) {
        Log.d("Lesson", "Load Quiz Bank");

        // Try reading Questions file
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            ArrayList<Question> quizBank = new ArrayList<Question>();

            String line;
            while ((line = reader.readLine()) != null) {
                // New QuizBank
                line.trim().replace("\t","");
                if(line.equals("<QuizBank>")) {
                    while (!(line = reader.readLine().trim().replace("\t","")).equals("</QuizBank>")) {
                        String questionText = "";
                        String answerText = "";
                        ArrayList<String> otherAnswers = new ArrayList<String>();

                        // New Question
                        if (line.equals("<Question>")) {
                            // Read Question Text
                            if ((line = reader.readLine().trim().replace("\t","")).equals("<QuestionText>")) {
                                questionText = reader.readLine().trim();
                            }
                            line = reader.readLine();

                            // Read Answer Text
                            if ((line = reader.readLine().trim().replace("\t","")).equals("<AnswerText>")) {
                                answerText = reader.readLine().trim();
                            }
                            line = reader.readLine();

                            // Read Other Choices
                            while ((line = reader.readLine().trim().replace("\t","")).equals("<OtherChoice>")) {
                                otherAnswers.add(reader.readLine().trim());
                                line = reader.readLine();
                            }
                        }
                        // Add whole question to quizBank
                        quizBank.add(new Question(questionText, answerText, otherAnswers));
                    }
                }
            }
            return quizBank;    // Return entire quizBank

        } catch (Exception e)    {
            e.printStackTrace();
        }
        return null;
    }

    // toString
    public String toString() {
        String output = "";
        for (int i = 0; i < quizBank.size(); i++) {
            output += "Question Text: " + quizBank.get(i).getQuestionText() + "\n";
            output += "Answer Text:   " + quizBank.get(i).getAnswerText() + "\nOther Answers: ";
            for (int j = 0; j < quizBank.get(i).getOtherChoices().size(); j++) {
                if (j > 0) {
                    output += ", ";
                }
                output += quizBank.get(i).getOtherChoices().get(j);
            }
            output += "\n\n";
        }
        return output;
    }

}

