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

    static int casaAtual = 0;
    static boolean[] casasPassadas = new boolean[14];
    private CasaView[] casasView = new CasaView[14];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Point size = new Point();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindowManager().getDefaultDisplay().getSize(size);
        posicionarLogo();
        montarTabuleiro(size.x, size.y);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            casasPassadas[requestCode] = true;
            casaAtual++;
            casasView[requestCode].trocarImagem(R.drawable.pergunta_passada);
            if(requestCode + 1 < casasView.length) {
                casasView[requestCode + 1].trocarImagem(R.drawable.pergunta_atual);
            }
        }
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

        casasView[10] = new CasaView(this, linha[0]*height, coluna[0]*width, tamanho_casa, 10);
        casasView[11] = new CasaView(this, linha[1]*height, coluna[0]*width, tamanho_casa, 11);
        casasView[12] = new CasaView(this, linha[2]*height, coluna[0]*width, tamanho_casa, 12);
        casasView[1] = new CasaView(this, linha[5]*height, coluna[0]*width, tamanho_casa, 1);
        casasView[9] = new CasaView(this, linha[0]*height, coluna[1]*width, tamanho_casa, 9);
        casasView[13] = new CasaView(this, linha[2]*height, coluna[1]*width, tamanho_casa, 13);
        casasView[2] = new CasaView(this, linha[5]*height, coluna[1]*width, tamanho_casa, 2);
        casasView[8] = new CasaView(this, linha[0]*height, coluna[2]*width, tamanho_casa, 8);
        casasView[7] = new CasaView(this, linha[1]*height, coluna[2]*width, tamanho_casa, 7);
        casasView[6] = new CasaView(this, linha[2]*height, coluna[2]*width, tamanho_casa, 6);
        casasView[5] = new CasaView(this, linha[3]*height, coluna[2]*width, tamanho_casa, 5);
        casasView[4] = new CasaView(this, linha[4]*height, coluna[2]*width, tamanho_casa, 4);
        casasView[3] = new CasaView(this, linha[5]*height, coluna[2]*width, tamanho_casa, 3);
        casasView[0] = new CasaView(this, linha[4]*height, coluna[0]*width, tamanho_casa, 0);

        for(CasaView cv : casasView) {
            rl.addView(cv);
        }
    }

}

class CasaView extends ImageView {

    private final int id;

    public CasaView(Context context, float posicaoY, float posicaoX, int tamanhoCasa, int id) {
        super(context);
        this.setX(posicaoX);
        this.setY(posicaoY);
        this.setClickable(true);
        if(id != 0) {
            this.setImageResource(R.drawable.pergunta_futura);
        }
        else {
            this.setImageResource(R.drawable.pergunta_atual);
        }
        this.id = id;

        int tamanho = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tamanhoCasa, getResources().getDisplayMetrics());

        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(tamanho, tamanho);
        this.setLayoutParams(parms);

        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificaCasa()) {
                    return;
                }
                Intent intent = new Intent(v.getContext(), QuestionActivity.class);
                //v.getContext().startActivity(intent);
                ((Activity)v.getContext()).startActivityForResult(intent, getID());
            }
        });
    }

    private int getID() {
        return id;
    }

    private boolean verificaCasa() {
        if(id == MainActivity.casaAtual) {
            return true;
        }
        return false;
    }

    public void trocarImagem(int imageId) {
        this.setImageResource(imageId);
    }
}