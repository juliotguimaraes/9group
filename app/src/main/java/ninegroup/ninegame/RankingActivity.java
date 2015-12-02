package ninegroup.ninegame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class RankingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        preencheRanking();
    }

    private void preencheRanking() {
        File file = new File(getFilesDir(), getString(R.string.arquivo_ranking));
        TextView tv;
        BufferedReader br;
        String line;

        if(!file.exists()) {
            return;
        }

        tv = (TextView)findViewById(R.id.ranking_text);

        try {
            br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            while(line != null) {
                tv.append(line);
                line = br.readLine();
            }
        } catch(FileNotFoundException ex) {}
          catch(IOException ex) {}
    }

}
