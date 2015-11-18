package ninegroup.ninegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Point size = new Point();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindowManager().getDefaultDisplay().getSize(size);
        montarTabuleiro(size.x, size.y);
    }

    protected void montarTabuleiro(int width, int height){
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.meuLayout);
        float linha[] = new float[5];
        float coluna[] = new float[3];

        linha[0] = 0.0f;
        linha[1] = 0.2f;
        linha[2] = 0.4f;
        linha[3] = 0.6f;
        linha[4] = 0.8f;

        coluna[0] = 0.0f;
        coluna[1] = 0.3f;
        coluna[2] = 0.6f;

        CasaView casa_1 = new CasaView(this, linha[0]*height, coluna[0]*width);
        CasaView casa_2 = new CasaView(this, linha[1]*height, coluna[0]*width);
        CasaView casa_3 = new CasaView(this, linha[2]*height, coluna[0]*width);
        CasaView casa_4 = new CasaView(this, linha[4]*height, coluna[0]*width);
        CasaView casa_5 = new CasaView(this, linha[0]*height, coluna[1]*width);
        CasaView casa_6 = new CasaView(this, linha[2]*height, coluna[1]*width);
        CasaView casa_7 = new CasaView(this, linha[4]*height, coluna[1]*width);
        CasaView casa_8 = new CasaView(this, linha[0]*height, coluna[2]*width);
        CasaView casa_9 = new CasaView(this, linha[1]*height, coluna[2]*width);
        CasaView casa_10 = new CasaView(this, linha[2]*height, coluna[2]*width);
        CasaView casa_11 = new CasaView(this, linha[3]*height, coluna[2]*width);
        CasaView casa_12 = new CasaView(this, linha[4]*height, coluna[2]*width);

        rl.addView(casa_1);
        rl.addView(casa_2);
        rl.addView(casa_3);
        rl.addView(casa_4);
        rl.addView(casa_5);
        rl.addView(casa_6);
        rl.addView(casa_7);
        rl.addView(casa_8);
        rl.addView(casa_9);
        rl.addView(casa_10);
        rl.addView(casa_11);
        rl.addView(casa_12);
    }
    
}

class CasaView extends ImageView {

    public CasaView(Context context, float posicaoY, float posicaoX){
        super(context);
        this.setX(posicaoX);
        this.setY(posicaoY);
        this.setClickable(true);
        this.setImageResource(R.drawable.pergunta_atual);

        int tamanho = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(tamanho, tamanho);
        this.setLayoutParams(parms);

        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QuestionActivity.class);
                v.getContext().startActivity(intent);

                trocarImagem();
            }
        });
    }

    public void trocarImagem() {
        this.setImageResource(R.drawable.pergunta_futura);
    }
}