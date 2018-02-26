package info.kesavan.thulasi.karnam;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewDebug;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             try{
                 Snackbar.make(view, "Say thanks: thulasi@kesavan.info", Snackbar.LENGTH_LONG)
                         .setAction("Action", null).show();

                 Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                 emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"angel@kesavan.info"});
                 emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding கர்ணம்");
                 emailIntent.putExtra(Intent.EXTRA_TEXT, "Thanks for the கர்ணம்.");
                 emailIntent.setType("message/rfc822");
                 startActivity(emailIntent);
             }
             catch(NullPointerException e){
                 Snackbar.make(view, "Set your Mailbox first", Snackbar.LENGTH_LONG)
                         .setAction("Action", null).show();
             }

            }
        });

        final Button buttonRandom = findViewById(R.id.calcRandom);
        buttonRandom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Random r = new Random();
                int Low  = 2216;
                int High = 7783;
                int Height = r.nextInt(High-Low) + Low;
                int Length = r.nextInt(High-Low) + Low;

                EditText l = findViewById(R.id.length);
                EditText h = findViewById(R.id.height);
                l.setText(String.valueOf(Length));
                h.setText(String.valueOf(Height));
            }

        });

        final Button button = findViewById(R.id.calcKarnam);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView karnamTamil = findViewById(R.id.answer);
                TextView karnamPyth = findViewById(R.id.answer2);
                TextView msgWarning = findViewById(R.id.textMsg);
                EditText l = findViewById(R.id.length);
                EditText h = findViewById(R.id.height);
                TextView msgMatch = findViewById(R.id.textMatch);
                msgWarning.setTextColor(Color.BLACK);
                msgMatch.setText("");

                try {
                    Double actualLength = Double.parseDouble(l.getText().toString());
                    Double actualHeight = Double.parseDouble(h.getText().toString());
                    Double finalL, finalH;

                    if( actualHeight <= 0 || actualLength <= 0 ) {
                        msgWarning.setText("Zero or negative values not accepted");
                        msgWarning.setTextColor(Color.BLUE);
                    }else {

                        if (actualLength < actualHeight) {
                            finalH = actualLength;
                            finalL = actualHeight;
                        } else {
                            finalH = actualHeight;
                            finalL = actualLength;
                        }

                        Double ansTamil = (7 * finalL / 8) + (finalH / 2);
                        Double ansPyth = sqrt((finalL * finalL) + (finalH * finalH));

                        DecimalFormat df = new DecimalFormat("#.####");
                        ansPyth = Double.valueOf(df.format(ansPyth));
                        ansTamil = Double.valueOf(df.format(ansTamil));

                        karnamTamil.setText(Double.toString(ansTamil));
                        karnamPyth.setText(Double.toString(ansPyth));

                        Double match = (ansTamil*100/ansPyth);
                        match = match>100?200-match:match;
                        DecimalFormat dm = new DecimalFormat("#.##");
                        match = Double.valueOf(dm.format(match));
                        msgWarning.setText("Calculated successfully. Matching : ");

                        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                        msgMatch.setText(Double.toString( match) +"%");
                        msgMatch.setTypeface(boldTypeface);
                        msgMatch.setTextColor(Color.GREEN);

                    }

                } catch (NumberFormatException e) {
                    msgWarning.setText("Please enter numbers only");
                    msgWarning.setTextColor(Color.RED);
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

/*
*

    <!--  This is Screenshot part .
     Copied from http://www.androhub.com/take-a-screenshot-programmatically-in-android/-


    <!- -  Button which will take full page screenshot
    <Button
        android:id="@+id/full_page_screenshot"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/full_page_screenshot"
        android:textColor="@android:color/white"
        android:textSize="14sp" />

       Hidden Text which will shown when taking screenshot from below Button  - ->
    <TextView
        android:id="@+id/hidden_text"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="@string/hidden_text"
        android:textColor="@android:color/white"
        android:textSize="14sp"
        android:visibility="invisible" />

    <!--  Button which will take screenshot after hiding some view and showing some view  -- >
    <Button
        android:id="@+id/custom_page_screenshot"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/custom_page_screenshot"
        android:textColor="@android:color/white"
        android:textSize="14sp" />


    <!--  ImageView to show taken Screenshot  - ->
    <ImageView
        android:id="@+id/image_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scaleType="fitCenter"
        android:src="@mipmap/ic_launcher" />


    <!--  -End of Screenshot  - ->


    -->
*
* */