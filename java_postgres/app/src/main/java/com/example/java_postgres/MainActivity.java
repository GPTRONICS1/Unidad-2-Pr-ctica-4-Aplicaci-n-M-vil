package com.example.java_postgres;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText nombres, primerApellido, segundoApellido, edad, fechaNacimiento;
    Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa los componentes de la vista
        nombres = findViewById(R.id.etNombres);
        primerApellido = findViewById(R.id.etPrimerApellido);
        segundoApellido = findViewById(R.id.etSegundoApellido);
        edad = findViewById(R.id.etEdad);
        fechaNacimiento = findViewById(R.id.etFechaNacimiento);
        enviar = findViewById(R.id.btnEnviar);

        // Configura el click listener para enviar los datos
        enviar.setOnClickListener(v -> enviarDatos());

        // Botón para mostrar los datos
        Button btnMostrarDatos = findViewById(R.id.btnMostrarDatos);
        btnMostrarDatos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MostrarDatosActivity.class);
            startActivity(intent);
        });
    }

    private void enviarDatos() {
        // Obtiene los datos del formulario
        String nombresTexto = nombres.getText().toString();
        String primerApellidoTexto = primerApellido.getText().toString();
        String segundoApellidoTexto = segundoApellido.getText().toString();
        String edadTexto = edad.getText().toString();
        String fechaNacimientoTexto = fechaNacimiento.getText().toString();

        // Valida que todos los campos estén llenos
        if (nombresTexto.isEmpty() || primerApellidoTexto.isEmpty() || segundoApellidoTexto.isEmpty() || edadTexto.isEmpty() || fechaNacimientoTexto.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Configuración de OkHttpClient con logging
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // Loguea las peticiones y respuestas HTTP
                .build();

        // Configuración de Retrofit con el OkHttpClient y el GsonConverter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.97/")  // URL base (solo el host)
                .client(client)  // Agrega el cliente con el interceptor
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crea la instancia de ApiService usando Retrofit
        ApiService apiService = retrofit.create(ApiService.class);

        // Realiza la llamada a la API
        Call<ResponseModel> call = apiService.enviarDatos(
                nombresTexto,
                primerApellidoTexto,
                segundoApellidoTexto,
                edadTexto,
                fechaNacimientoTexto
        );

        // Maneja la respuesta de la API
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseModel responseModel = response.body();
                    Toast.makeText(getApplicationContext(), "Datos guardados correctamente.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
