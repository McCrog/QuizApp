package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton oneImageRadioButton, twoImageRadioButton, threeImageRadioButton, fourImageRadioButton;
    /*
    * Variable
    */
    private int score = 0;
    private int question = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display(question);
    }

    /*
    * Check user answer
    */
    public void checkAnswer(View view) {
        switch (question) {
            case 1: {
                if (checkingRadioButton("George Washington")) {
                    score++;
                    displayToast(true);
                } else {
                    displayToast(false);
                }
                question++;
                changeContent(question);
                break;
            }
            case 2: {
                boolean[] answer = {true, true, false, true};
                if (checkingCheckbox(answer)) {
                    score++;
                    displayToast(true);
                } else {
                    displayToast(false);
                }
                question++;
                changeContent(question);
                break;
            }
            case 3: {
                if (checkingText(getString(R.string.donald_trump))) {
                    score++;
                    displayToast(true);
                } else {
                    displayToast(false);
                }
                question++;
                changeContent(question);
                break;
            }
            case 4: {
                boolean[] answer = {false, false, true, false};
                if (checkingRadioButtonWithImage(answer)) {
                    score++;
                    displayToast(true);
                } else {
                    displayToast(false);
                }
                question++;
                changeContent(question);
                break;
            }
            case 5: {
                boolean[] answer = {false, false, true, false};
                if (checkingRadioGroupWithImage(answer)) {
                    score++;
                    displayToast(true);
                } else {
                    displayToast(false);
                }
                question++;
                changeContent(question);
                break;
            }
        }
    }

    /*
    * The method starts the quiz from the beginning
    */
    public void restartGame(View view) {
        score = 0;
        question = 1;
        changeContent(question);
    }

    /*
    * The method changes the content depending on the number of the question.
    */
    private void changeContent(int questionNumber) {
        switch (questionNumber) {
            case 1:
                setContentView(R.layout.activity_main);
                display(questionNumber);
                break;
            case 2:
                setContentView(R.layout.checkbox_question);
                display(questionNumber);
                break;
            case 3:
                setContentView(R.layout.text_question);
                display(questionNumber);
                break;
            case 4:
                setContentView(R.layout.image_question);
                display(questionNumber);
                break;
            case 5:
                setContentView(R.layout.image_radio_button_question);
                display(questionNumber);
                break;
            case 6:
                setContentView(R.layout.final_layout);
                display(questionNumber);
                break;
        }
    }

    /*
    * Display score, question number and question
    */
    private void display(int questionNumber) {
        TextView scoreString = (TextView) findViewById(R.id.score_text);
        scoreString.setText(getString(R.string.score_text, score));
        TextView questionNumberString = (TextView) findViewById(R.id.question_number_text);
        questionNumberString.setText(getString(R.string.question_number_text, question));

        switch (questionNumber) {
            case 1: {
                TextView questionString1 = (TextView) findViewById(R.id.question_text);
                questionString1.setText(getString(R.string.question_number_1));

                RadioButton firstRadioButton = (RadioButton) findViewById(R.id.first_radio_button);
                RadioButton secondRadioButton = (RadioButton) findViewById(R.id.second_radio_button);
                RadioButton thirdRadioButton = (RadioButton) findViewById(R.id.third_radio_button);
                firstRadioButton.setText(getString(R.string.abraham_lincoln));
                secondRadioButton.setText(getString(R.string.george_washington));
                thirdRadioButton.setText(getString(R.string.thomas_jefferson));
                break;
            }
            case 2: {
                TextView questionString2 = (TextView) findViewById(R.id.question_text);
                questionString2.setText(getString(R.string.question_number_2));

                CheckBox firstCheckBox = (CheckBox) findViewById(R.id.first_checkbox);
                CheckBox secondCheckBox = (CheckBox) findViewById(R.id.second_checkbox);
                CheckBox thirdCheckBox = (CheckBox) findViewById(R.id.third_checkbox);
                CheckBox fourthCheckBox = (CheckBox) findViewById(R.id.fourth_checkbox);
                firstCheckBox.setText(getString(R.string.alabama));
                secondCheckBox.setText(getString(R.string.new_mexico));
                thirdCheckBox.setText(getString(R.string.saint_petersburg));
                fourthCheckBox.setText(getString(R.string.ohio));
                break;
            }
            case 3: {
                TextView questionString3 = (TextView) findViewById(R.id.question_text);
                questionString3.setText(getString(R.string.question_number_3));
                break;
            }
            case 4: {
                TextView questionString4 = (TextView) findViewById(R.id.question_text);
                questionString4.setText(getString(R.string.question_number_4));
                radioButtonWithImageHandel();
                break;
            }
            case 5: {
                TextView questionString4 = (TextView) findViewById(R.id.question_text);
                questionString4.setText(getString(R.string.question_number_4));
                break;
            }
            case 6: {
                TextView resultString = (TextView) findViewById(R.id.result_text_fiels);

                // The variable questionNumberString is assigned an empty string for the correct operation of the application.
                // A variable is declared at the beginning of the method and is called for all layers
                // For the final layer is not required.
                questionNumberString.setText("");

                resultString.setText(getString(R.string.result_text, checkWon()));
                break;
            }
        }
    }

    /*
    * The method checks the number of points scored and determines the victory or defeat
    */
    private String checkWon() {
        if (score == 5)
            return "You win!";
        return "You lose";
    }

    /*
    * The method helps to handle radio button selection events.
    * Due to the fact that the radio buttons are not children of the radio group,
    * it is possible to select any of the four radio buttons.
    * This method is to prevent this behavior.
    */
    private void radioButtonWithImageHandel() {
        oneImageRadioButton = (RadioButton) findViewById(R.id.first_image_radio_button);
        twoImageRadioButton = (RadioButton) findViewById(R.id.second_image_radio_button);
        threeImageRadioButton = (RadioButton) findViewById(R.id.third_image_radio_button);
        fourImageRadioButton = (RadioButton) findViewById(R.id.fourth_image_radio_button);

        oneImageRadioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                oneImageRadioButton.setChecked(true);
                twoImageRadioButton.setChecked(false);
                threeImageRadioButton.setChecked(false);
                fourImageRadioButton.setChecked(false);
            }
        });

        twoImageRadioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                oneImageRadioButton.setChecked(false);
                twoImageRadioButton.setChecked(true);
                threeImageRadioButton.setChecked(false);
                fourImageRadioButton.setChecked(false);
            }
        });

        threeImageRadioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                oneImageRadioButton.setChecked(false);
                twoImageRadioButton.setChecked(false);
                threeImageRadioButton.setChecked(true);
                fourImageRadioButton.setChecked(false);
            }
        });

        fourImageRadioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                oneImageRadioButton.setChecked(false);
                twoImageRadioButton.setChecked(false);
                threeImageRadioButton.setChecked(false);
                fourImageRadioButton.setChecked(true);
            }
        });
    }

    /*
    * The method displays a toast message
    */
    private void displayToast(boolean answer) {
        if (answer)
            Toast.makeText(this, getString(R.string.toast_correct_message), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, getString(R.string.toast_incorrect_message), Toast.LENGTH_SHORT).show();
    }

    /*
    * Method for checking the response in the RadioButton.
    */
    private boolean checkingRadioButton(String answer) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_button_group);
        int count = radioGroup.getChildCount();
        boolean checked = false;

        for (int i = 0; i < count; i++) {
            RadioButton btn = (RadioButton) radioGroup.getChildAt(i);
            String radioButtonString = btn.getText().toString();

            if (radioButtonString.toLowerCase().equals(answer.toLowerCase()) && btn.isChecked())
                checked = true;
        }
        return checked;
    }

    /*
    * Method for checking the response in the CheckBoxes.
    */
    private boolean checkingCheckbox(boolean[] answer) {
        boolean checked = false;
        int countTrue = 0;
        int count = 0;

        CheckBox firstCheckBox = (CheckBox) findViewById(R.id.first_checkbox);
        CheckBox secondCheckBox = (CheckBox) findViewById(R.id.second_checkbox);
        CheckBox thirdCheckBox = (CheckBox) findViewById(R.id.third_checkbox);
        CheckBox fourthCheckBox = (CheckBox) findViewById(R.id.fourth_checkbox);
        boolean[] checkedCheckbox = {firstCheckBox.isChecked(), secondCheckBox.isChecked(),
                thirdCheckBox.isChecked(), fourthCheckBox.isChecked()};

        for (int i = 0; i < 4; i++) {
            if (answer[i])
                countTrue++;
            if (answer[i] && checkedCheckbox[i])
                count++;
        }

        if (count == countTrue)
            checked = true;

        return checked;
    }

    /*
    * Method for checking the response in the EditText field.
    */
    private boolean checkingText(String answer) {
        boolean checked = false;

        EditText answerField = (EditText) findViewById(R.id.answer_field);
        String userAnswer = answerField.getText().toString().toLowerCase();

        if (answer.toLowerCase().equals(userAnswer))
            checked = true;

        return checked;
    }

    /*
    * Method for checking the response in the RadioButton on image question.
    */
    private boolean checkingRadioButtonWithImage(boolean[] answer) {
        boolean checked = false;
        int countTrue = 0;
        int count = 0;

        RadioButton firstFlagRadioButton = (RadioButton) findViewById(R.id.first_image_radio_button);
        RadioButton secondFlagRadioButton = (RadioButton) findViewById(R.id.second_image_radio_button);
        RadioButton thirdFlagRadioButton = (RadioButton) findViewById(R.id.third_image_radio_button);
        RadioButton fourthFlagRadioButton = (RadioButton) findViewById(R.id.fourth_image_radio_button);
        boolean[] checkedRadioButton = {firstFlagRadioButton.isChecked(), secondFlagRadioButton.isChecked(),
                thirdFlagRadioButton.isChecked(), fourthFlagRadioButton.isChecked()};

        for (int i = 0; i < 4; i++) {
            if (answer[i])
                countTrue++;
            if (answer[i] && checkedRadioButton[i])
                count++;
        }

        if (count == countTrue)
            checked = true;

        return checked;
    }

    /*
    * Method for checking the response in the RadioGroup on image question.
    */
    private boolean checkingRadioGroupWithImage(boolean[] answer) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_button_image_group);

        boolean checked = false;
        int countTrue = 0;
        int count = 0;

        boolean[] checkedRadioButtonGroup = new boolean[4];

        for (int i = 0; i < 4; i++) {
            RadioButton btn = (RadioButton) radioGroup.getChildAt(i);
            checkedRadioButtonGroup[i] = btn.isChecked();
            if (answer[i])
                countTrue++;
            if (answer[i] && checkedRadioButtonGroup[i])
                count++;
        }

        if (count == countTrue)
            checked = true;

        return checked;
    }
}
