package pl.koszalin.tu.pogodynka;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button start = (Button) findViewById(R.id.button_start);
        Button stop = (Button) findViewById(R.id.button_stop);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMyService();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMyService();
            }
        });

    }
    private void startMyService() {
        Intent serviceIntent = new Intent(this, Popups.class);
        startService(serviceIntent);
    }

    private void stopMyService() {
        Intent serviceIntent = new Intent(this, Popups.class);
        stopService(serviceIntent);
    }
}