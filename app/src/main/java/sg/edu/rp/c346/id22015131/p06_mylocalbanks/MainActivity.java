package sg.edu.rp.c346.id22015131.p06_mylocalbanks;

import static android.provider.Settings.System.getConfiguration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

import android.app.LocaleManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tvDBS, tvOCBC, tvUOB;

    String wordClicked = "";
    ArrayList<Bank> banklist = new ArrayList<Bank>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDBS = findViewById(R.id.tvDBS);
        tvOCBC = findViewById(R.id.tvOCBC);
        tvUOB = findViewById(R.id.tvUOB);

        registerForContextMenu(tvDBS);
        registerForContextMenu(tvOCBC);
        registerForContextMenu(tvUOB);

        banklist.add(new Bank("dbs", "https://www.dbs.com.sg", 1800111111));
        banklist.add(new Bank("ocbc", "https://www.ocbc.com", 1800363333));
        banklist.add(new Bank("uob", "https://www.uob.com.sg", 1800222212));

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, 0, 0, getText((R.string.website)));
        menu.add(0, 1, 1, ((R.string.contact)));
        menu.add(0, 2, 2, ((R.string.favourite)));

        if (v == tvDBS) {
            wordClicked = "dbs";
        } else if (v == tvOCBC) {
            wordClicked = "ocbc";
        } else if (v == tvUOB) {
            wordClicked = "uob";
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        for (int i = 0; i < banklist.size(); i++) {
            if (wordClicked.equalsIgnoreCase(banklist.get(i).getName())) {
                if (item.getItemId() == 0) {
                    String url = banklist.get(i).getUrl();
                    Toast.makeText(getApplicationContext(), getText((R.string.openWebsite)), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == 1) {
                    int contactNum = banklist.get(i).getContactNum();
                    Toast.makeText(getApplicationContext(), getText((R.string.openContact)), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactNum));
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == 2) {
                    setFav();
                    return true;

                }
            }
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.EnglishSelection) {
            setLocale("en");

        } else {
            setLocale("ja");
        }
        return true;
    }

    private void setLocale(String language) {
        Resources res = getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(language));
        res.updateConfiguration(conf, metrics);
        onConfigurationChanged(conf);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConf) {
        super.onConfigurationChanged(newConf);
        tvDBS.setText(getText((R.string.dbs)));
        tvOCBC.setText(getText(((R.string.ocbc))));
        tvUOB.setText(getText(((R.string.uob))));
    }

    public void setFav() {
        if (wordClicked.equalsIgnoreCase("dbs")) {
            int newColor = (tvDBS.getCurrentTextColor() == Color.RED) ? Color.BLACK : Color.RED;
            tvDBS.setTextColor(newColor);
        } else if (wordClicked.equalsIgnoreCase("ocbc")) {
            int newColor = (tvOCBC.getCurrentTextColor() == Color.RED) ? Color.BLACK : Color.RED;
            tvOCBC.setTextColor(newColor);
        } else if (wordClicked.equalsIgnoreCase("uob")) {
            int newColor = (tvUOB.getCurrentTextColor() == Color.RED) ? Color.BLACK : Color.RED;
            tvUOB.setTextColor(newColor);
        }
    }
}