package ninegroup.ninegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends Activity implements View.OnClickListener{

    LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        // Create a LinearLayout in which to add the ImageView
        mLinearLayout = new LinearLayout(this);
        //mLinearLayout.setPadding(0, R.dimen.activity_vertical_margin, 0, 0);

        // Instantiate an ImageView and define its properties
        ImageView i = new ImageView(this);
        i.setOnClickListener(this);
        i.setImageResource(R.drawable.tabuleirorafael);
        i.setAdjustViewBounds(true); // set the ImageView bounds to match the Drawable's dimensions
        i.setLayoutParams(new Gallery.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        // Add the ImageView to the layout and set the layout as the content view
        mLinearLayout.addView(i);
        setContentView(mLinearLayout);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
    
}
