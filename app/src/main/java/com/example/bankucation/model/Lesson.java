package com.example.bankucation.model;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

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

    // Load arraylist of questions from XML file
    public static ArrayList<Question> loadQuizBank(InputStream inputStream) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            ArrayList<Question> quizBank = new ArrayList<Question>();

            String line;
            while ((line = reader.readLine()) != null) {
                // new quizbank
                if(line.equals("<QuizBank>")) {
                    while (!(line = reader.readLine()).trim().equals("</QuizBank>")) {
                        line = reader.readLine().trim();
                        String questionText = "";
                        String answerText = "";
                        ArrayList<String> otherAnswers = new ArrayList<String>();
                        // new question
                        if (line.equals("<Question>")) {

                            if ((line = reader.readLine().trim()).equals("<QuestionText>")) {
                                questionText = reader.readLine().trim();
                            }
                            reader.readLine();
                            if ((line = reader.readLine().trim()).equals("<AnswerText>")) {
                                answerText = reader.readLine().trim();
                            }
                            reader.readLine();
                            while ((line = reader.readLine().trim()).equals("<OtherChoice>")) {
                                otherAnswers.add(reader.readLine().trim());
                                reader.readLine();
                            }
                        }
                        quizBank.add(new Question(questionText, answerText, otherAnswers));
                    }
                }
            }
            return quizBank;
        } catch (Exception e)    {
            e.printStackTrace();
        }
        return null;
    }
}
