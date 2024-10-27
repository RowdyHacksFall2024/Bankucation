package com.example.bankucation.model;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class Lesson {
//    private ArrayList<Dictionary> dictionaryBank;
    private ArrayList<Question> quizBank;

    // Constructor
//    public Lesson(ArrayList<Dictionary> dictionaryBank, ArrayList<Question> quizBank) {
    public Lesson(ArrayList<Question> quizBank) {

//        this.dictionaryBank = dictionaryBank;
        this.quizBank = quizBank;
    }

    // Dictionary Bank
//    public ArrayList<Dictionary> getDictionaryBank() {
//        return dictionaryBank;
//    }
//    public void setDictionaryBank(ArrayList<Dictionary> dictionaryBank) {
//        this.dictionaryBank = dictionaryBank;
//    }

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
