package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Random;

public class HomeActivity extends AppCompatActivity {

    private TextView questionTV, questionNumberTV;
    private Button optionBtn1,optionBtn2,optionBtn3,optionBtn4;
    private ArrayList<Quiz> quizArrayList;
    Random random; //class in java that helps access the data in an array list
    int currentScore = 1, questionsAttempted = 1, currentPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        questionTV = findViewById(R.id.TVQuestion);
        questionNumberTV = findViewById(R.id.TVNumberOfQuestions);
        optionBtn1 = findViewById(R.id.BtnOption1);
        optionBtn2 = findViewById(R.id.BtnOption2);
        optionBtn3 = findViewById(R.id.BtnOption3);
        optionBtn4 = findViewById(R.id.BtnOption4);

        quizArrayList = new ArrayList<>();
        random = new Random();

        getQuizQuestions(quizArrayList);

        currentPos = random.nextInt(quizArrayList.size()); //generate a random number the interval is the size of the array List
        sendDataToViews(currentPos);

        optionBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(optionBtn1.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
            questionsAttempted++;
            currentPos = random.nextInt(quizArrayList.size());
            sendDataToViews(currentPos); //to change the question after incrementing the position
            }
        });

        optionBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(optionBtn2.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionsAttempted++;
                currentPos = random.nextInt(quizArrayList.size());
                sendDataToViews(currentPos); //to change the question after incrementing the position
            }
        });

        optionBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(optionBtn3.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionsAttempted++;
                currentPos = random.nextInt(quizArrayList.size());
                sendDataToViews(currentPos); //to change the question after incrementing the position
            }
        });

        optionBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(optionBtn4.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionsAttempted++;
                currentPos = random.nextInt(quizArrayList.size());
                sendDataToViews(currentPos); //to change the question after incrementing the position
            }
        });
    }

    private void showBottomSheet(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HomeActivity.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet,(LinearLayout)findViewById(R.id.BottomSheetId));
        //The LayoutInflater class is used to instantiate the contents of layout XML files into their corresponding View objects

        TextView scoreTv = bottomSheetView.findViewById(R.id.TvScore);
        Button restart = bottomSheetView.findViewById(R.id.BtnRestart);

        scoreTv.setText("Your score is \n"+currentScore+"/10");
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionsAttempted = 1;
                currentScore = 1;
                currentPos = random.nextInt(quizArrayList.size());
                sendDataToViews(currentPos);
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
    private void sendDataToViews(int currentPos){
        questionNumberTV.setText("Questions Attempted: "+questionsAttempted+"/10");

        if(questionsAttempted == 10){
            showBottomSheet();
        }else {
            questionTV.setText(quizArrayList.get(currentPos).getQuestion());
            optionBtn1.setText(quizArrayList.get(currentPos).getOption1());
            optionBtn2.setText(quizArrayList.get(currentPos).getOption2());
            optionBtn3.setText(quizArrayList.get(currentPos).getOption3());
            optionBtn4.setText(quizArrayList.get(currentPos).getOption4());
        }
    }

    private void getQuizQuestions(ArrayList<Quiz> quizArrayList) {
        quizArrayList.add(new Quiz("Android is:","Web server","Web browser","operating system","None of these","operating system"));
        quizArrayList.add(new Quiz("Android Operating system is base on:","Mac","Windows","Linux","Other","Linux"));
        quizArrayList.add(new Quiz("Android is developed specially for:","Laptops","Desktops","Servers","Mobile devices","Mobile devices"));
        quizArrayList.add(new Quiz("Android is based on Linux for:","Security","Portability","Networking","All of these","All of these"));
    }
}