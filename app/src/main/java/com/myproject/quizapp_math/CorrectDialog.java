package com.myproject.quizapp_math;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myproject.quizapp_math.R;
import com.myproject.quizapp_math.QuizActivity;

public class CorrectDialog {

    private Context mContext;

    private Dialog correctDialog;

    private QuizActivity mquizActivity;

    public CorrectDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void correctDialog(int score, final QuizActivity quizActivity){

        mquizActivity = quizActivity;

        correctDialog = new Dialog(mContext);
        correctDialog.setContentView(R.layout.correct_dialog);

        Button btCorrectDialog = (Button) correctDialog.findViewById(R.id.bt_correct_dialog);

        score(score);


        btCorrectDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctDialog.dismiss();
                quizActivity.showQuestions();
            }
        });

        correctDialog.show();
        correctDialog.setCancelable(false);
        correctDialog.setCanceledOnTouchOutside(false);

        correctDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    private void score(int score) {

        TextView textViewScore = (TextView) correctDialog.findViewById(R.id.text_score);
        textViewScore.setText("Score: " + String.valueOf(score));
    }

}
