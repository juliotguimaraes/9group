package ninegroup.ninegame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class MainActivity extends Activity {

    static GameScores score = new GameScores();
    static boolean etico = true;
    static int casaAtual = 0;
    static boolean[] casasPassadas = new boolean[14];
    private CasaView[] casasView = new CasaView[14];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Point size = new Point();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindowManager().getDefaultDisplay().getSize(size);
        posicionarCreditos();
        posicionarRanking();
        posicionarRegras();
        montarTabuleiro(size.x, size.y);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==0 && (MainActivity.casaAtual == 10)){
            // o jogador completou a fase
            escreveRanking();
        }
        if(resultCode==0 && etico) {
            casasPassadas[requestCode] = true;
            casasView[requestCode].trocarImagem(R.drawable.pergunta_passada);
            if (requestCode + 1 < casasView.length) {
                casasView[requestCode + 1].trocarImagem(R.drawable.pergunta_atual);
            }
        } else if(resultCode==1 && !etico) {
            if(casaAtual == 13){
                casasPassadas[6] = true;
                casasView[6].trocarImagem(R.drawable.pergunta_passada);
                casasView[13].trocarImagem(R.drawable.pergunta_atual);
            }
        } else if (resultCode == 0 && !etico){
            casasPassadas[requestCode] = true;
            casasView[requestCode].trocarImagem(R.drawable.pergunta_passada);
            casasView[requestCode - 1].trocarImagem(R.drawable.pergunta_atual);
        }
    }

    private void escreveRanking() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Entre com seu nome");

        // Set up the input
        final EditText input = new EditText(this);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
                File file = new File(getFilesDir(), getString(R.string.arquivo_ranking));
                BufferedWriter bw;

                try {
                    bw = new BufferedWriter(new FileWriter(file, true));

                    if(!file.exists()) {
                        file.createNewFile();
                    }

                    if(etico) {
                        bw.write(input.getText().toString() + " " + score.calculateEthicFinalScore() + '\n');
                    }
                    else {
                        bw.write(input.getText().toString() + " " + score.calculateNonEthicFinalScore() + '\n');
                    }

                    bw.close();
                } catch(IOException ex) {
                    return;
                }
            }
        });

        builder.show();
    }

    private void posicionarCreditos() {
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.meuLayout);
        ImageView credito = new CreditosView(this);

        DisplayMetrics display = this.getResources().getDisplayMetrics();
        int largura_tela = display.widthPixels;

        float proporcao_tela = 0.3f;
        int largura = (int)(largura_tela * proporcao_tela * 0.9f);
        int altura = (int) (largura_tela * 0.3980f);

        credito.setX(largura_tela - largura - 50);
        credito.setY(0);

        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(largura, altura);
        credito.setLayoutParams(parms);

        credito.setImageResource(R.drawable.creditos);
        rl.addView(credito);
    }

    private void posicionarRanking() {
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.meuLayout);
        ImageView credito = new RankingView(this);

        DisplayMetrics display = this.getResources().getDisplayMetrics();
        int largura_tela = display.widthPixels;

        float proporcao_tela = 0.3f;
        int largura = (int)(largura_tela * proporcao_tela * 0.9f);
        int altura = (int) (largura_tela * 0.3980f);

        credito.setX(50);
        credito.setY(0);

        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(largura, altura);
        credito.setLayoutParams(parms);

        credito.setImageResource(R.drawable.ranking);
        rl.addView(credito);
    }

    private void posicionarRegras() {
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.meuLayout);
        ImageView regra = new RegrasView(this);

        DisplayMetrics display = this.getResources().getDisplayMetrics();
        int largura_tela = display.widthPixels;

        float proporcao_tela = 0.3f;
        int largura = (int)(largura_tela * proporcao_tela * 0.9f);
        int altura = (int) (largura_tela * 0.3980f);

        regra.setX((largura_tela - largura)/2);
        regra.setY(0);

        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(largura, altura);
        regra.setLayoutParams(parms);

        regra.setImageResource(R.drawable.regras);
        rl.addView(regra);
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

        if (id == MainActivity.casaAtual) {
            this.setImageResource(R.drawable.pergunta_atual);
        } else {
            this.setImageResource(R.drawable.pergunta_futura);
        }
        this.id = id;

        int tamanho = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tamanhoCasa, getResources().getDisplayMetrics());

        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(tamanho, tamanho);
        this.setLayoutParams(parms);

        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verificaCasa()) {
                    return;
                }
                Intent intent = new Intent(v.getContext(), QuestionActivity.class);
                //v.getContext().startActivity(intent);
                ((Activity) v.getContext()).startActivityForResult(intent, getID());
            }
        });
    }

    private int getID() {
        return id;
    }

    private boolean verificaCasa() {
        return id == MainActivity.casaAtual;
    }

    public void trocarImagem(int imageId) {
        this.setImageResource(imageId);
    }
}

class CreditosView extends ImageView {

    public CreditosView(Context context) {
        super(context);
        this.setClickable(true);

        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreditosActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}

class RegrasView extends ImageView {

    public RegrasView(Context context) {
        super(context);
        this.setClickable(true);

        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegrasActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}

class RankingView extends ImageView {

    public RankingView(final Context context) {
        super(context);
        this.setClickable(true);

        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RankingActivity.class);
                v.getContext().startActivity(intent);
                //CODIGO DE PEGAR O NOME DO USUARIO QUANDO TERMINAR O JOGO
                /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Entre com seu nome");

                // Set up the input
                final EditText input = new EditText(context);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //m_Text = input.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();*/
            }
        });
    }
}