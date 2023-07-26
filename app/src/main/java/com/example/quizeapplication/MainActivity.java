package com.example.quizeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView total;
    TextView questiontext;
    Button a,b,c,d;
    Button sub;
    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        total = findViewById(R.id.total);
        questiontext = findViewById(R.id.question);
        a =findViewById(R.id.ans_a);
        b =findViewById(R.id.ans_b);
        c =findViewById(R.id.ans_c);
        d =findViewById(R.id.ans_d);
        sub =findViewById(R.id.submit);

        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);
        sub.setOnClickListener(this);

        total.setText("Total question : "+totalQuestion);


        loadNewQuestion();

    }

    @Override
    public void onClick(View view) {

        a.setBackgroundColor(Color.WHITE);
        b.setBackgroundColor(Color.WHITE);
        c.setBackgroundColor(Color.WHITE);
        d.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId() == R.id.submit)
        {
            if(selectedAnswer.equals(QuestionAnswer.corretAnswer[currentQuestionIndex])){
                score ++;
            }
            currentQuestionIndex ++;
            loadNewQuestion();


        }else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.BLUE);
        }

    }
    void loadNewQuestion(){
        if(currentQuestionIndex==totalQuestion){
            finishQuize();
            return;
        }

        questiontext.setText(QuestionAnswer.question[currentQuestionIndex]);
        a.setText(QuestionAnswer.choice[currentQuestionIndex][0]);
        b.setText(QuestionAnswer.choice[currentQuestionIndex][1]);
        c.setText(QuestionAnswer.choice[currentQuestionIndex][2]);
        d.setText(QuestionAnswer.choice[currentQuestionIndex][3]);
    }
    void finishQuize(){
        String passStatus = "";
        if(score>totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this).setTitle(passStatus).setMessage("Score is "+score+" Out of "+totalQuestion).setPositiveButton("Restart",((dialogInterface, i) -> restartQuize())).setCancelable(false).show();

    }
    void restartQuize(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
}