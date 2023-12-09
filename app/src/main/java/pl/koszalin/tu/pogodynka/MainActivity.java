package pl.koszalin.tu.pogodynka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_prognoza = (Button) findViewById(R.id.button_prognozaPogody);

        btn_prognoza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView miasto = (AutoCompleteTextView) findViewById(R.id.Miasto_input);
                Intent prognozaIntent = new Intent(MainActivity.this,PrognozaActivity.class);
                prognozaIntent.putExtra("miasto",miasto.getText().toString());
                startActivity(prognozaIntent);
            }
        });
        Button btn_aktualne = (Button) findViewById(R.id.button_aktualne);
        btn_aktualne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView miasto = (AutoCompleteTextView) findViewById(R.id.Miasto_input);
                Intent actualIntent = new Intent(MainActivity.this,ActualInStationActivity.class);
                actualIntent.putExtra("miasto",miasto.getText().toString());
                startActivity(actualIntent);
            }
        });
        Button btn_settings = (Button) findViewById(R.id.button_settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView miasto = (AutoCompleteTextView) findViewById(R.id.Miasto_input);
                Intent actualIntent = new Intent(MainActivity.this,SettingsActivity.class);
                actualIntent.putExtra("miasto",miasto.getText().toString());
                startActivity(actualIntent);
            }
        });
    }

}