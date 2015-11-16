package ninegroup.ninegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        montarTabuleiro();
    }

    protected void montarTabuleiro(){
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.meuLayout);

        CasaView casa_1 = new CasaView(this, (float)100.0, (float)100.0);
        CasaView casa_2 = new CasaView(this, (float)400.0, (float)100.0);
        CasaView casa_3 = new CasaView(this, (float)700.0, (float)100.0);
        CasaView casa_4 = new CasaView(this, (float)1000.0, (float)100.0);

        rl.addView(casa_1);
        rl.addView(casa_2);
        rl.addView(casa_3);
        rl.addView(casa_4);
    }
    
}

class CasaView extends ImageView {

    public CasaView(Context context, float posicaoY, float posicaoX){
        super(context);
        this.setY(posicaoY);
        this.setClickable(true);
        this.setImageResource(R.drawable.pergunta_atual);

        int tamanho = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(tamanho, tamanho);
        this.setLayoutParams(parms);

        this.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                trocarImagem();

                Intent intent = new Intent(v.getContext(), QuestionActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    public void trocarImagem() {
        this.setImageResource(R.drawable.pergunta_futura);
    }
}