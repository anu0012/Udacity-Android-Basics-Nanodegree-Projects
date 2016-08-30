package com.example.anuragsharma.quizzy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int correctAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method displays result of the Quiz in a Toast
     */
    public void displayResult(View v) {
        correctAnswers = 0;
        RadioButton r1 = (RadioButton) findViewById(R.id.ans_1);
        RadioButton r2 = (RadioButton) findViewById(R.id.ans_2);
        RadioButton r4 = (RadioButton) findViewById(R.id.ans_4);
        RadioButton r5 = (RadioButton) findViewById(R.id.ans_5);
        RadioButton r6 = (RadioButton) findViewById(R.id.ans_6);
        EditText et = (EditText) findViewById(R.id.ans_7);
        CheckBox c1 = (CheckBox) findViewById(R.id.ans_3_B);
        CheckBox c2 = (CheckBox) findViewById(R.id.ans_3_C);
        CheckBox c3 = (CheckBox) findViewById(R.id.ans_3_D);
        CheckBox c4 = (CheckBox) findViewById(R.id.ans_3_A);

        if (r1.isChecked()) correctAnswers++;
        if (r2.isChecked()) correctAnswers++;
        if (r4.isChecked()) correctAnswers++;
        if (r5.isChecked()) correctAnswers++;
        if (r6.isChecked()) correctAnswers++;
        if (et.getText().toString().trim().equalsIgnoreCase(getString(R.string.ans_7)))
            correctAnswers++;
        if (c1.isChecked() && c2.isChecked() && c3.isChecked() && !c4.isChecked()) correctAnswers++;

        Toast.makeText(this, correctAnswers + "/7 are correct.", Toast.LENGTH_LONG).show();
    }
}
