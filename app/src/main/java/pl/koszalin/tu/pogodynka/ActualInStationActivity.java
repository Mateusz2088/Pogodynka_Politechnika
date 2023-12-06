package pl.koszalin.tu.pogodynka;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ActualInStationActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_station);
        getData(getCity());
    }

    private void getData(String cityName){
        String url = "https://danepubliczne.imgw.pl/api/data/synop/station/"+cityName.toLowerCase();
        requestQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showWeather(response.toString());
                Toast.makeText(getApplicationContext(), "Done "+response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error :" + error.toString());
            }
        });

        requestQueue.add(stringRequest);
    }
    private String getCity(){
        Intent intent = getIntent();
        return intent.getStringExtra("miasto");
    }
    private void showWeather(String inputJSON) {
        try {
            JSONObject weather = new JSONObject(inputJSON);
            TextView miejscowosc = (TextView) findViewById(R.id.cityValue);
            miejscowosc.setText(weather.getString("stacja"));
            TextView temp = (TextView) findViewById(R.id.tempValue);
            temp.setText(weather.getString("temperatura")+"st.C");
            TextView time = (TextView) findViewById(R.id.dateTimeValue);
            time.setText(weather.getString("data_pomiaru")+"\n "+weather.getString("godzina_pomiaru")+":00");
            TextView pressure = (TextView) findViewById(R.id.preasureValue);
            pressure.setText(weather.getString("cisnienie")+" hPa");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}

