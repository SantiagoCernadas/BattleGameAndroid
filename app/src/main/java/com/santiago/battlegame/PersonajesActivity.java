package com.santiago.battlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.santiago.battlegame.Interface.BattleGameApi;
import com.santiago.battlegame.Model.Personaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonajesActivity extends AppCompatActivity {

    private TextView txtPersonajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personajes);

        txtPersonajes = findViewById(R.id.txtPersonajes);
        getPersonajes();
    }

    public void getPersonajes(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.100.23:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BattleGameApi battleGameApi = retrofit.create(BattleGameApi.class);

        Call<List<Personaje>> call = battleGameApi.getPersonajes();

        call.enqueue(new Callback<List<Personaje>>() {
            @Override
            public void onResponse(Call<List<Personaje>> call, Response<List<Personaje>> response) {
                if(!response.isSuccessful()){
                    txtPersonajes.setText("codigo: " + response.code());
                    return;
                }
                List<Personaje> personajes = response.body();
                for (Personaje personaje: personajes){
                    String content = "";
                    content += "id: " + personaje.getId() + "\n";
                    content += "nombre: " + personaje.getNombre() + "\n";
                    content += "fuerza: " + personaje.getFuerza() + "\n";
                    content += "velocidad: " + personaje.getVelocidad() + "\n";
                    content += "agilidad: " + personaje.getAgilidad() + "\n";
                    content += "resistencia: " + personaje.getResistencia() + "\n";
                    content += "inteligencia: " + personaje.getInteligencia() + "\n\n";
                    txtPersonajes.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Personaje>> call, Throwable t) {
                txtPersonajes.setText(t.getMessage());
            }
        });
    }
}