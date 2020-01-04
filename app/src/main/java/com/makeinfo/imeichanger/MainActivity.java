package com.makeinfo.imeichanger;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

public class MainActivity extends AppCompatActivity {

    Button btnApply;
    Button help;
   // CheckBox cbRandomize;
    EditText edtCurrentIMEINo;
    EditText edtNewIMEINo;
    TextView tvOriginalIMEINo;
    SharedPreferences bundle;
    private static final String TAG = "DayL";
    private StartAppAd startAppAd = new StartAppAd(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, "208829607", true);
        setContentView(R.layout.activity_main);

        tvOriginalIMEINo = (TextView) findViewById(R.id.tvOriginalIMEINo);
        edtCurrentIMEINo = (EditText) findViewById(R.id.edit_currentimei);
        edtNewIMEINo = (EditText) findViewById(R.id.edit_newimei);
        btnApply = (Button) findViewById(R.id.btnApply);
        help = (Button) findViewById(R.id.btnBuyPro);
        // btnBuyPro = (Button) findViewById(R.id.btnBuy);

        TelephonyManager tm = (TelephonyManager) getSystemService(android.content.Context.TELEPHONY_SERVICE);
        edtCurrentIMEINo.setText((new StringBuilder()).append(tm.getDeviceId().toString()).toString());
        bundle = getSharedPreferences("IMEI_settings", 1);
        if (bundle.getString("OriginalIMEI", "").toString().equalsIgnoreCase(""))
        {
            android.content.SharedPreferences.Editor editor = bundle.edit();
            editor.putString("OriginalIMEI", tm.getDeviceId().toString());     //created imei number to shared preference
            editor.commit();
        }
        tvOriginalIMEINo.setText((new StringBuilder("Original : ")).append(bundle.getString("OriginalIMEI", "").toString()).toString());

        btnApply.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            android.content.SharedPreferences.Editor editor = bundle.edit();
                                            editor.putString("CurrentIMEI", edtNewIMEINo.getText().toString());                         //this will edit imei number to sharedpreference
                                            editor.commit();
                                            Toast.makeText(getApplicationContext(), "Reboot to Apply", Toast.LENGTH_SHORT).show();
                                        }
                                    }
        );
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,HelpActivity.class);
                startActivity(i);
                startAppAd.showAd();
                startAppAd.loadAd();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        startAppAd.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        startAppAd.onPause();
    }
    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();
        startAppAd.showAd(); // show the ad
        startAppAd.loadAd(); // load the next ad
        super.onBackPressed();
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
            Intent i = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(i);
        }
        if (id == R.id.remove_ads) {
            Intent i = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


}
