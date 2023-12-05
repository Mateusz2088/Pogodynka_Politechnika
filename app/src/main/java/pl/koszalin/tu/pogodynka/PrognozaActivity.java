package pl.koszalin.tu.pogodynka;



import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class PrognozaActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prognoza);

        getData("Koszalin,pl");
    }

    private void getData(String cityName){
        String url = "https://api.openweathermap.org/data/2.5/forecast?q="+cityName+"&units=metric&appid=3dbe58c7f23716bce28db7e662c85f86";
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showForecast(response.toString());
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();//display the response on screen
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
    private void showForecast(String inputData){
        try {
            JSONObject forecast = new JSONObject(inputData);
            JSONArray listFromForecast = forecast.getJSONArray("list");
            int forecastLenght = listFromForecast.length();
            TableLayout tabela = (TableLayout) findViewById(R.id.tabela_prognozy);
            for (int i=0; i<forecastLenght;i++){
                //dodawanie pól tekstowych
                final TextView rowTempTextView = new TextView(this);
                final TextView rowHoursTextView = new TextView(this);
                final TextView rowWindyTextView = new TextView(this);

                // pobieranie danych z JSONa
                JSONObject dataRow = listFromForecast.getJSONObject(i);
                JSONObject main = dataRow.getJSONObject("main");
                JSONObject wind = dataRow.getJSONObject("wind");
                // dodawanie danych do pól tekstowych
                rowTempTextView.setText(main.getString("temp")+" st.C");
                rowHoursTextView.setText(dataRow.getString("dt_txt").replace(' ', '\n'));
                rowWindyTextView.setText(wind.getString("speed")+" m/s");
                // dodawanie do tabeli
                final TableRow newRow = new TableRow(this);
                newRow.addView(rowHoursTextView);
                newRow.addView(rowTempTextView);
                newRow.addView(rowWindyTextView);
                tabela.addView(newRow);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
