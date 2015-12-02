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
    Question currentDisplayedQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);


        Button button = (Button)findViewById(R.id.buttonResponder);

        QuestionsHandler ql = new QuestionsHandler();

        switch (MainActivity.casaAtual){
            case 0:
                currentDisplayedQuestion = ql.getRandomQuestionFromSpecificNormalLevel(1);
                break;
            case 1:
                currentDisplayedQuestion = ql.getRandomQuestionFromChangeLevel(1);
                break;
            case 2:
                currentDisplayedQuestion = ql.getRandomQuestionFromSpecificNormalLevel(2);
                break;
            case 3:
                currentDisplayedQuestion = ql.getRandomQuestionFromChangeLevel(2);
                break;
            case 4:
                currentDisplayedQuestion = ql.getRandomQuestionFromSpecificNormalLevel(3);
                break;
            case 5:
                currentDisplayedQuestion = ql.getRandomQuestionFromSpecificNormalLevel(3);
                break;
            case 6:
                currentDisplayedQuestion = ql.getRandomQuestionFromChangeLevel(3);
                break;
            case 7:
                currentDisplayedQuestion = ql.getRandomQuestionFromSpecificNormalLevel(4);
                break;
            case 8:
                currentDisplayedQuestion = ql.getRandomQuestionFromChangeLevel(4);
                break;
            case 9:
                currentDisplayedQuestion = ql.getRandomQuestionFromSpecificNormalLevel(5);
                break;
            case 10:
                currentDisplayedQuestion = ql.getRandomQuestionFromChangeLevel(5);
                break;
            case 11:
                currentDisplayedQuestion = ql.getRandomQuestionFromSpecificNormalLevel(5);
                break;
            case 12:
                currentDisplayedQuestion = ql.getRandomQuestionFromChangeLevel(4);
                break;
            case 13:
                currentDisplayedQuestion = ql.getRandomQuestionFromSpecificNormalLevel(4);
                break;
        }
        buildQuestionScreen(currentDisplayedQuestion);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (didThePlayerAnswerTheQuestionCorrectly(currentDisplayedQuestion) == true){
                    // resposta correta
                    if(MainActivity.casaAtual == 6){
                        MainActivity.etico = true;
                        MainActivity.casaAtual++;
                    } else if(MainActivity.etico){
                        MainActivity.casaAtual++;
                    } else {
                        MainActivity.casaAtual--;
                    }
                    setResult(0);
                } else {
                    // resposta errada
                    if(MainActivity.casaAtual == 6){
                        MainActivity.etico = false;
                        MainActivity.casaAtual = 13;
                    }
                    setResult(1);
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
    public boolean didThePlayerAnswerTheQuestionCorrectly(Question q){
        RadioGroup rg = (RadioGroup) findViewById(R.id.RadioGroup);

        int radioButtonID = rg.getCheckedRadioButtonId();
        View radioButton = rg.findViewById(radioButtonID);
        int idx = rg.indexOfChild(radioButton);

        String checkedAnswer = "";
        switch (idx){
            case 0:
                checkedAnswer = "A";
                break;
            case 1:
                checkedAnswer = "B";
                break;
            case 2:
                checkedAnswer = "C";
                break;
            case 3:
                checkedAnswer = "D";
                break;
            case 4:
                checkedAnswer = "E";
                break;
            default:
                checkedAnswer = "Z";
                break;
        }
        return q.isTheCorrectAnswer(checkedAnswer);
    }

}
