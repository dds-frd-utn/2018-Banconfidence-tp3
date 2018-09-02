package frd.utn.dds_tp3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.List;
// import android.widget.Toast;

// Imports necesarios para hacer la ListView
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnEjecutar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new getMovimientos().execute();
            }
        });
    }


    private class getMovimientos extends AsyncTask<String, String, List<Movimiento>> {

        @Override
        protected List<Movimiento> doInBackground(String... params) {

            // 10.0.2.2:8080 nos conecta a localhost
            return RESTService.makeGetRequest(
                    "http://10.0.2.2:8080/Banconfidence/rest/movimiento/cuenta/1/todos");
        }

        protected void onPostExecute(List<Movimiento> result) {
            // Sacamos el botón "Ejecutar"
            findViewById(R.id.btnEjecutar).setVisibility(View.GONE);

            // ListView
            ArrayAdapter<Movimiento> adapter = new ArrayAdapter<Movimiento>(getApplicationContext(), R.layout.activity_listview, result);
            ListView listView = (ListView) findViewById(R.id.mobile_list);
            listView.setAdapter(adapter);

            /* Original
            *  Toast notificacion = Toast.makeText(
            *          getApplicationContext(), result.toString(), Toast.LENGTH_LONG); // usamos el método toString de la clase Movimiento
            *  notificacion.show();
            */
        }
    }
}
