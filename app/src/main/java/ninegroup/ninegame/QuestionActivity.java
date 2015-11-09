package ninegroup.ninegame;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuestionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);

        TextView question = (TextView)findViewById(R.id.question);
        String questionString = "[POSCOMP 2010] A Engenharia de Requisitos \u00e9 um processo que envolve todas as atividades exigidas para criar e manter o documento de requisitos de sistema. Sobre a Engenharia de Requisitos, considere as afirmativas a seguir.";
        questionString += "\n";
        questionString += "I. A Engenharia de Requisitos, como todas as outras atividades de Engenharia de Software, precisa ser adaptada \u00e0s necessidades do processo, do projeto, do produto e do pessoal que est\u00e1 fazendo o trabalho.";
        questionString += "\n";
        questionString += "II. No est\u00e1gio de levantamento e an\u00e1lise dos requisitos, os membros da equipe t\u00e9cnica de desenvolvimento do software trabalham com o cliente e os usu\u00e1rios finais do sistema para descobrir mais informa\u00e7\u00f5es sobre o dom\u00ednio da aplica\u00e7\u00e3o, que servi\u00e7os o sistema deve oferecer, o desempenho exigido do sistema, as restri\u00e7\u00f5es de hardware, entre outras informa\u00e7\u00f5es.";
        questionString += "\n";
        questionString += "III. Na medida em que a informa\u00e7\u00e3o de v\u00e1rios pontos de vista \u00e9 coletada, os requisitos emergentes s\u00e3o consistentes.";
        questionString += "\n";
        questionString += "A valida\u00e7\u00e3o de requisitos se ocupa de mostrar que estes realmente definem o sistema que o cliente deseja. Ela \u00e9 importante porque a ocorr\u00eancia de erros em um documento de requisitos pode levar a grandes custos relacionados ao retrabalho.";
        question.setText(Html.fromHtml(questionString));

        RadioButton rb = (RadioButton)findViewById(R.id.radioButton1);
        rb.setText(Html.fromHtml("a) Somente as afirmativas I e II s\u00e3o corretas."));
        System.out.println(Html.fromHtml("a) Somente as afirmativas I e II s\u00e3o \u00e9 e \u00e9 corretas."));
        rb = (RadioButton) findViewById(R.id.radioButton2);
        rb.setText(Html.fromHtml("b) Somente as afirmativas I e III s\u00e3o corretas."));
        rb = (RadioButton) findViewById(R.id.radioButton3);
        rb.setText(Html.fromHtml("c) Somente as afirmativas III e IV s\u00e3o corretas."));
        rb = (RadioButton) findViewById(R.id.radioButton4);
        rb.setText(Html.fromHtml("d) Somente as afirmativas I, II e IV s\u00e3o corretas."));
        rb = (RadioButton) findViewById(R.id.radioButton5);
        rb.setText(Html.fromHtml("e) Somente as afirmativas II, III e IV s\u00e3o corretas."));

        Button button = (Button)findViewById(R.id.buttonResponder);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
