package com.example.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

import com.example.assignment.QuestionContract.*;

import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyQuiz.db";
    private static final int DATABASE_VERSION =1;
    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_OPTION5 + " TEXT " +
                ")";

        db.execSQL(SQL_CREATE_QUESTION_TABLE);
        fillQuestionTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);

    }

    private void fillQuestionTable(){
        Question q1 = new Question("I feel tired." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q1);
        Question q2 = new Question("I find it very hard to relax or \"wind-down\"." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q2);
        Question q3 = new Question("I find it hard to make decisions." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q3);
        Question q4 = new Question("My heart races and I find myself breathing rapidly." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q4);
        Question q5 = new Question("I have trouble thinking clearly." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q5);
        Question q6 = new Question("I eat too much or too little." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q6);
        Question q7 = new Question("I get headaches." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q7);
        Question q8 = new Question("I feel emotionally numb." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q8);
        Question q9 = new Question("I think about my problems over and over again during the day." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q9);
        Question q10 = new Question("I have sleeping problems(e.g.trouble falling asleep, trouble staying asleep, trouble waking up, nightmares,etc)." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q10);
        Question q11 = new Question("I have trouble feeling hopeful." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q11);
        Question q12 = new Question("I find myself taking unnecessary risks or engaging in behaviour hazardous to health and/ or safety." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q12);
        Question q13 = new Question("I have back and neck pain, or other chronic tension-linked pain." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q13);
        Question q14 = new Question("I use caffeine or nicotine more than usual." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q14);
        Question q15 = new Question("I feel overwhelmed and helpless." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q15);
        Question q16 = new Question("I have nervous habits(e.g. biting my nails, grinding my teeth, fidgeting, pacing, etc)." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q16);
        Question q17 = new Question("I forget little things (e.g. where I put my keys, people's names, details discussed during the last work meeting)." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q17);
        Question q18 = new Question("I have stomach upset(e.g. nausea, vomiting, diarrhea, constipation, gas)." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q18);
        Question q19 = new Question("I am irritable and easily annoyed." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q19);
        Question q20 = new Question("I have mood- swings and feel over- emotional." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q20);
        Question q21 = new Question("I find it hard to concentrate." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q21);
        Question q22 = new Question("I have trouble feeling that my life is meaningful." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q22);
        Question q23 = new Question("I am withdrawn and feel distant and cut off from other people." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q23);
        Question q24 = new Question("I use alcohol and/ or other drugs to try and help cope." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q24);
        Question q25 = new Question("My work performance has declined and I have trouble completing things." , "Never", "Seldom", "Sometimes", "Often", "Always");
        addQuestion(q25);

    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionTable.COLUMN_OPTION5,question.getOption5());
        db.insert(QuestionTable.TABLE_NAME,null,cv);

    }

    public List<Question> getAllQuestion(){
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM QUIZ ORDER BY random() LIMIT 10", null);
        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setOption5(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION5)));
                questionList.add(question);
            }while(c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
