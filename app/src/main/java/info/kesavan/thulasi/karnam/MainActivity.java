package info.kesavan.thulasi.karnam;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        WebView w = findViewById(R.id.w1);
        w.loadData("<html>hyptenuse=&#8730;(a<sup>2</sup>+b<sup>2</sup>)", "text/html; charset=UTF-8", null);

        final Button button = findViewById(R.id.calcKarnam);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView karnamTamil = findViewById(R.id.answer);
                TextView karnamPyth = findViewById(R.id.answer2);
                EditText l = findViewById(R.id.length);
                EditText h = findViewById(R.id.height);

                Double actualLength = Double.parseDouble(l.getText().toString());
                Double actualHeight = Double.parseDouble(h.getText().toString());
                Double finalL,finalH;
                if(actualLength < actualHeight ) {
                     finalH = actualLength;
                     finalL = actualHeight;
                }else {
                     finalH = actualHeight;
                     finalL = actualLength;
                }
                //

                //aaaaa.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"), Html.FROM_HTML_MODE_COMPACT);

                karnamTamil.setText(Double.toString((7*finalL/8 )+ (finalH/2)));
                karnamPyth.setText(Double.toString(sqrt((finalL*finalL)+ (finalH*finalH))));
                //karnamTamil.setText(Double.toString(finalL));
                // Code here executes on main thread after user presses button
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
