package ninegroup.ninegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);


        Button button = (Button)findViewById(R.id.buttonResponder);

        QuestionsHandler ql = new QuestionsHandler();
        buildQuestionScreen(ql.getRandomQuestionFromRandomNormalLevel());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rg = (RadioGroup)findViewById(R.id.RadioGroup);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton4) {
                    setResult(Activity.RESULT_OK);
                }
                else {
                    setResult(Activity.RESULT_CANCELED);
                }
                finish();
            }
        });
    }


    private void buildQuestionScreen(Question q){
        TextView question = (TextView)findViewById(R.id.question);
        question.setText(Html.fromHtml(q.getQuestion()));
        RadioButton rb = (RadioButton)findViewById(R.id.radioButton1);
        rb.setText(Html.fromHtml(q.getAlternativeA()));
        rb = (RadioButton) findViewById(R.id.radioButton2);
        rb.setText(Html.fromHtml(q.getAlternativeB()));
        rb = (RadioButton) findViewById(R.id.radioButton3);
        rb.setText(Html.fromHtml(q.getAlternativeC()));
        rb = (RadioButton) findViewById(R.id.radioButton4);
        rb.setText(Html.fromHtml(q.getAlternativeD()));
        rb = (RadioButton) findViewById(R.id.radioButton5);
        rb.setText(Html.fromHtml(q.getAlternativeE()));
    }

}
