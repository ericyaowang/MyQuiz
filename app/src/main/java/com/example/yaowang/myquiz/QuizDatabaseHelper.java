package com.example.yaowang.myquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yaowang.myquiz.QuizTable.*;

import java.util.ArrayList;
import java.util.List;


//QuizDatabaseHelper class is to store all the entities- questions and their options by utilising SQLiteOpenHelper
public class QuizDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyQuiz.db"; // create a database of all the quizs here and
                                                            // that will be used in the method of QuizDatabaseHelper
    private static final int DATABASE_VERSION = 1; //define the version of database has been used and
                                                    // the version name will also be used in QuizDatabaseHelper

    private SQLiteDatabase db;

    public QuizDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Create database
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                                                    QuestionTable.TABLE_NAME + " ( " +
                                                    QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    QuestionTable.COLUMN_QUESTION + " TEXT, " +
                                                    QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                                                    QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                                                    QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                                                    QuestionTable.COLUMN_ANSWER_NO + " INTEGER " +
                                                    " ) ";

        sqLiteDatabase.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        //Call the method fillQuestionTable() which contains all the question entities
        fillQuestionTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //this calss is to update the database

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void fillQuestionTable(){
        Questions questionOne = new Questions("Each pass through a loop is called a/an ", "enumeration", "iteration", "culmination",2);
        addQuestions(questionOne);
        Questions questionTwo = new Questions("In a group of nested loops, which loop is executed the most number of times?", "the innermost loop", "the outermost loop", "all loops are executed the same number of times",1);
        addQuestions(questionTwo);
        Questions questionThree = new Questions("The statement  i++;  is equivalent to ", " i = i + 1;", " i = i + i;", "i = i - 1;",1);
        addQuestions(questionThree);
        Questions questionFour = new Questions("If there is more than one statement in the block of a for loop, which of the following must be placed at the beginning and the ending of the loop block?", "parentheses ( )", "French curly braces { }", "arrows < >",2);
        addQuestions(questionFour);
        Questions questionFive = new Questions("Which looping process is best used when the number of iterations is known?", "while", "do-while", "for",3);
        addQuestions(questionFive);
    }

    //Ref. http://75.103.78.27/MathBits/Java/Looping/MCLooping.htm

    private void addQuestions(Questions questions){

       //insert questions and options shown above in "fillQuestionTable" into our database
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, questions.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, questions.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, questions.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, questions.getOption3());
        cv.put(QuestionTable.COLUMN_ANSWER_NO, questions.getAnswerNo());
        db.insert(QuestionTable.TABLE_NAME, null, cv); //insert the content of question one into the database
    }

    public List<Questions> getAllQuestions(){
        List<Questions> questionsList = new ArrayList<>();
        //refer to our database to get our data all the way down
        db = getReadableDatabase();

        // sql query -> request questions from our database
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()){  // this will move our cursor to the first entry if there is no entry

            //the do while loop is to get the content of  next question once a question has been answered and confirmed
            do{
                Questions questions = new Questions();
                questions.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION))); //get the question out of the entry
                questions.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1))); // get the option out of the entry
                questions.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2))); // get the option out of the entry
                questions.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3))); // get the option out of the entry
                questions.setAnswerNo(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NO)));// get the correct answer out of the entry
                questionsList.add(questions);

            }while (c.moveToNext());
        }

        c.close(); //close the cursor we used in if statement
        return questionsList;

    }

}
