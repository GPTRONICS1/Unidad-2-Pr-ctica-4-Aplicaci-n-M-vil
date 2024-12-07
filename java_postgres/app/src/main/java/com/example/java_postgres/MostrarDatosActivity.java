package com.example.java_postgres;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MostrarDatosActivity extends AppCompatActivity {

    private ListView listViewDatos;
    private ArrayAdapter<String> adapter;
    private List<String> listaDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);

        listViewDatos = findViewById(R.id.listViewDatos);
        listaDatos = new ArrayList<>();

        // Llamar al método para obtener los datos
        obtenerDatosDeLaBaseDeDatos();
    }

    private void obtenerDatosDeLaBaseDeDatos() {
        // Configurar Retrofit para hacer la solicitud
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.97/") // Cambia la URL por la dirección donde esté alojada la aplicación web
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Datos>> call = apiService.obtenerDatos();

        call.enqueue(new Callback<List<Datos>>() {
            @Override
            public void onResponse(Call<List<Datos>> call, Response<List<Datos>> response) {
                if (response.isSuccessful()) {
                    // Obtener la lista de datos desde la respuesta
                    List<Datos> datos = response.body();

                    // Verificar que los datos no sean nulos o vacíos
                    if (datos != null && !datos.isEmpty()) {
                        for (Datos dato : datos) {
                            listaDatos.add(dato.getNombres() + " " + dato.getPrimerApellido());
                        }

                        // Configurar el adaptador para el ListView
                        adapter = new ArrayAdapter<>(MostrarDatosActivity.this, android.R.layout.simple_list_item_1, listaDatos);
                        listViewDatos.setAdapter(adapter);
                    } else {
                        // Si no hay datos, mostrar mensaje
                        Toast.makeText(MostrarDatosActivity.this, "No se encontraron datos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Si la respuesta no fue exitosa
                    Toast.makeText(MostrarDatosActivity.this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<Datos>> call, Throwable t) {
                // Manejar error de conexión
                Toast.makeText(MostrarDatosActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
