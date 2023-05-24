package com.santiago.battlegame.Interface;

import com.santiago.battlegame.Model.Personaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BattleGameApi {

    @GET("personaje")
    Call<List<Personaje>> getPersonajes();
}
