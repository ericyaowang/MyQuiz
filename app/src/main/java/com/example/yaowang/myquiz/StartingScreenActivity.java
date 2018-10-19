package com.example.yaowang.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartingScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        final Button startQuiz = findViewById(R.id.btn_start_quiz_loop);

        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz();
            }
        });
    }

    //Creating a method to start quiz which can be called by the button "startQuiz"
    private void startQuiz(){
        Intent intent = new Intent(StartingScreenActivity.this, Quiz.class);

        startActivity(intent);

    }

}
