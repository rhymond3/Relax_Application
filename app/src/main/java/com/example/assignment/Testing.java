package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class Testing extends AppCompatActivity {

    private Button confirm;
    private ImageButton back;
    private TextView textViewQuestion;
    private TextView textViewQuestionCount;
    private RadioGroup rbGroup;
    private RadioButton r1;
    private RadioButton r2;
    private RadioButton r3;
    private RadioButton r4;
    private RadioButton r5;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCounterTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        textViewQuestion = findViewById(R.id.textview_question);
        textViewQuestionCount = findViewById(R.id.textview_question_count);
        rbGroup = findViewById(R.id.radio_group);
        r1 = findViewById(R.id.radio_button1);
        r2 = findViewById(R.id.radio_button2);
        r3 = findViewById(R.id.radio_button3);
        r4 = findViewById(R.id.radio_button4);
        r5 = findViewById(R.id.radio_button5);
        confirm = findViewById(R.id.button_confirm);
        back = findViewById(R.id.imageButton);

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestion();
        questionCounterTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answered){
                    if(r1.isChecked() || r2.isChecked() || r3.isChecked() || r4.isChecked() || r5.isChecked()){
                        checkAnswer();
                        showNextQuestion();
                    }
                    else{
                        Toast.makeText(Testing.this,"Please select an answer",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void showNextQuestion(){

        rbGroup.clearCheck();

        if(questionCounter < questionCounterTotal){
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            r1.setText(currentQuestion.getOption1());
            r2.setText(currentQuestion.getOption2());
            r3.setText(currentQuestion.getOption3());
            r4.setText(currentQuestion.getOption4());
            r5.setText(currentQuestion.getOption5());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCounterTotal);
            confirm.setText("Next");
            answered=false;
        }else{
            finishQuiz();
        }
    }

    private void finishQuiz(){
        Intent intent = new Intent(this, Result.class);
        intent.putExtra("result", score);
        startActivity(intent);
        finish();
    }

    private void checkAnswer(){
        answered = true;

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNo = rbGroup.indexOfChild(rbSelected) +1;
        if(answerNo == 1){
            score += 0;
        }
        else if(answerNo == 2){
            score += 1;
        }
        else if(answerNo == 3){
            score += 2;
        }
        else if(answerNo == 4){
            score += 3;
        }
        else if (answerNo == 5){
            score += 4;
        }

    }
}
