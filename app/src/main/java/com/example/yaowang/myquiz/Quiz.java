package com.example.yaowang.myquiz;

import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Quiz extends AppCompatActivity {


    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;

    private RadioGroup rGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;

    private Button btnConfirmNxt;

    private ColorStateList textColorDefaultRb; // changing the color based on if students' input answers are correct or wrong

    private List<Questions> questionsList;

    private int questionCounter; //showing how many questions has been shown

    private int questionCountTotal; //the total questions in our arraylist

    private Questions currentQuestionNo;// the number of current question

    private int score;//scores gained

    private boolean answered;//to detect if a question has been answered yet

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);

        rGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);

        btnConfirmNxt = findViewById(R.id.btn_confirm_next);

        textColorDefaultRb = rb1.getTextColors(); //just the default text color of radio button

        QuizDatabaseHelper quizDatabaseHelper = new QuizDatabaseHelper(this);
        questionsList = quizDatabaseHelper.getAllQuestions(); // impement all the 5 questions

        questionCountTotal = questionsList.size();
        Collections.shuffle(questionsList); //to shuffle our question list

        showNextQuestion();//call showNextQuestion to go to the next question if there are any questions left

    }

    private void showNextQuestion(){
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rGroup.clearCheck();//clear the selection(UNSELECT) once the process of answering questions is done,
                            // so it can move onto the next question without the color from the last question

        // if statement is to detect if there is any question left,
        // if not(the number of current question is less than the number of total questions), then the quiz will be stopped
        if (questionCounter < questionCountTotal){

            currentQuestionNo = questionsList.get(questionCounter);

            //(continue to the last comment) if yes,then keeps showing the next question
            textViewQuestion.setText(currentQuestionNo.getQuestion());
            rb1.setText(currentQuestionNo.getOption1());
            rb2.setText(currentQuestionNo.getOption2());
            rb3.setText(currentQuestionNo.getOption3());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + " / " + questionCountTotal);// e.g. 3 / 5 which means, it is
                                                                                                        // the third question the student is
                                                                                                    //answering out of 5 questions in total

            answered = false; //move to the next question

            btnConfirmNxt.setText("Confirm");
        } else { //finish the quiz if questionCounter !< questionCountTotal
            finishQuiz(); //call the method finishQuiz() to close the quiz
        }

    }


    private void finishQuiz(){
        finish();
    }

}
