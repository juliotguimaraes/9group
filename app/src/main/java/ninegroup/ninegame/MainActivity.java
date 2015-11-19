package ninegroup.ninegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
        posicionarLogo();
        montarTabuleiro(size.x, size.y);
    }

    protected void posicionarLogo(){
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.meuLayout);
        ImageView logo = new ImageView(this);

        DisplayMetrics display = this.getResources().getDisplayMetrics();
        int largura_tela = display.widthPixels;

        float proporcao_tela = 0.5f;
        int largura = (int)(largura_tela * proporcao_tela);
        int altura = (int) (largura_tela * 0.4345f);

        int esquerda = (largura_tela - largura) / 2;
        logo.setX(esquerda);
        logo.setY(0);

        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(largura, altura);
        logo.setLayoutParams(parms);

        logo.setImageResource(R.drawable.logo);
        rl.addView(logo);
    }

    protected void montarTabuleiro(int width, int height){
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.meuLayout);
        float linha[] = new float[6];
        float coluna[] = new float[3];

        float margem_esquerda = 0.19f;
        float margem_cima = 0.2f;

        int tamanho_casa = 75;
        float tamanho_linha = 0.12f;
        float tamanho_coluna = 0.20f;

        linha[0] = (tamanho_linha * 0) + margem_cima;
        linha[1] = (tamanho_linha * 1) + margem_cima;
        linha[2] = (tamanho_linha * 2) + margem_cima;
        linha[3] = (tamanho_linha * 3) + margem_cima;
        linha[4] = (tamanho_linha * 4) + margem_cima;
        linha[5] = (tamanho_linha * 5) + margem_cima;

        coluna[0] = (tamanho_coluna * 0) + margem_esquerda;
        coluna[1] = (tamanho_coluna * 1) + margem_esquerda;
        coluna[2] = (tamanho_coluna * 2) + margem_esquerda;

        CasaView casa_1 = new CasaView(this, linha[0]*height, coluna[0]*width, tamanho_casa);
        CasaView casa_2 = new CasaView(this, linha[1]*height, coluna[0]*width, tamanho_casa);
        CasaView casa_3 = new CasaView(this, linha[2]*height, coluna[0]*width, tamanho_casa);
        CasaView casa_4 = new CasaView(this, linha[5]*height, coluna[0]*width, tamanho_casa);
        CasaView casa_5 = new CasaView(this, linha[0]*height, coluna[1]*width, tamanho_casa);
        CasaView casa_6 = new CasaView(this, linha[2]*height, coluna[1]*width, tamanho_casa);
        CasaView casa_7 = new CasaView(this, linha[5]*height, coluna[1]*width, tamanho_casa);
        CasaView casa_8 = new CasaView(this, linha[0]*height, coluna[2]*width, tamanho_casa);
        CasaView casa_9 = new CasaView(this, linha[1]*height, coluna[2]*width, tamanho_casa);
        CasaView casa_10 = new CasaView(this, linha[2]*height, coluna[2]*width, tamanho_casa);
        CasaView casa_11 = new CasaView(this, linha[3]*height, coluna[2]*width, tamanho_casa);
        CasaView casa_12 = new CasaView(this, linha[4]*height, coluna[2]*width, tamanho_casa);
        CasaView casa_13 = new CasaView(this, linha[5]*height, coluna[2]*width, tamanho_casa);
        CasaView casa_14 = new CasaView(this, linha[4]*height, coluna[0]*width, tamanho_casa);

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
        rl.addView(casa_13);
        rl.addView(casa_14);
    }

}

class CasaView extends ImageView {

    public CasaView(Context context, float posicaoY, float posicaoX, int tamanho_casa){
        super(context);
        this.setX(posicaoX);
        this.setY(posicaoY);
        this.setClickable(true);
        this.setImageResource(R.drawable.pergunta_futura);

        int tamanho = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tamanho_casa, getResources().getDisplayMetrics());

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
        this.setImageResource(R.drawable.pergunta_atual);
    }
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
