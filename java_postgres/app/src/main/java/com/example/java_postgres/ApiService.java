package com.example.java_postgres;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.GET;
import java.util.List;

public interface ApiService {

    // Método para enviar datos
    @FormUrlEncoded
    @POST("guardar_datos_java.php")  // Aquí se agrega la ruta específica
    Call<ResponseModel> enviarDatos(
            @Field("nombres") String nombres,
            @Field("primer_apellido") String primerApellido,
            @Field("segundo_apellido") String segundoApellido,
            @Field("edad") String edad,
            @Field("fecha_nacimiento") String fechaNacimiento
    );

    // Método para obtener datos
    @GET("obtener_datos.php") // El endpoint donde se obtienen los datos
    Call<List<Datos>> obtenerDatos();
}
