package com.example.yaowang.myquiz;

import android.provider.BaseColumns;

public final class QuizTable {

    //this class is to create table of quiz with the columns of question and option,
    // and in this table,
     public static class QuestionTable implements BaseColumns{ //Click "command + B", it will show you the default BaseColumns class.
         // BaseColumns provides two more concerns- String  _ID which is a primary key,
         // and String _COUNT which is the total number of entities
         public static final String TABLE_NAME= "quiz_questions"; // create the table title
         public static final String COLUMN_QUESTION = "question";// create the column of question
         public static final String COLUMN_OPTION1 = "option1";//create the column of option1
         public static final String COLUMN_OPTION2 = "option2";//create the column of option2
         public static final String COLUMN_OPTION3 = "option3";//create the column of option3
         public static final String COLUMN_ANSWER_NO="answer_no";//create the column of the correct answer number

     }

}
