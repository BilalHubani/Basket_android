package com.bilal.atleta.controller.managers;

import android.util.Log;

import com.bilal.atleta.controller.services.AtletaService;
import com.bilal.atleta.model.Atleta;
import com.bilal.atleta.util.CustomProperties;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AtletaManager {
    private static AtletaManager ourInstance;
    private List<Atleta> atletas;
    private Retrofit retrofit;
    private AtletaService atletaService;

    private AtletaManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        atletaService = retrofit.create(AtletaService.class);
    }

    public static AtletaManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new AtletaManager();
        }

        return ourInstance;
    }

    /* GET - GET ALL PLAYER */

    public synchronized void getAllAtletas(final AtletaCallback atletaCallback) {
        Call<List<Atleta>> call = atletaService.getAllAtleta(UserLoginManager.getInstance().getBearerToken());

        call.enqueue(new Callback<List<Atleta>>() {
            @Override
            public void onResponse(Call<List<Atleta>> call, Response<List<Atleta>> response) {
                atletas = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    atletaCallback.onSuccess(atletas);
                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Atleta>> call, Throwable t) {
                Log.e("AtletaManager->", "getAllAtletas()->ERROR: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }

    public Atleta getAtleta(String id) {
        for (Atleta atlet : atletas) {
            if (atlet.getId().toString().equals(id)) {
                return atlet;
            }
        }

        return null;
    }

    /* POST - CREATE PLAYER */

    public synchronized void createAtleta(final AtletaCallback atletaCallback,Atleta atleta) {
        Call<Atleta> call = atletaService.createAtleta(UserLoginManager.getInstance().getBearerToken(), atleta);
        call.enqueue(new Callback<Atleta>() {
            @Override
            public void onResponse(Call<Atleta> call, Response<Atleta> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    //atletaCallback.onSuccess1(apuestas1x2);
                    Log.e("Atleta->", "createAtleta: OK" + 100);

                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Atleta> call, Throwable t) {
                Log.e("AtletaManager->", "createAtleta: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }

    /* PUT - UPDATE PLAYER */
    public synchronized void updateAtleta(final AtletaCallback atletaCallback, Atleta atleta) {
        Call <Atleta> call = atletaService.updateAtleta(UserLoginManager.getInstance().getBearerToken() ,atleta);
        call.enqueue(new Callback<Atleta>() {
            @Override
            public void onResponse(Call<Atleta> call, Response<Atleta> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("Atleta->", "updateAtleta: OOK" + 100);

                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Atleta> call, Throwable t) {
                Log.e("AtletaManager->", "updateAtleta: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }

    /* DELETE - DELETE PLAYER */
    public synchronized void deleteAtleta(final AtletaCallback atletaCallback, Long id) {
        Call <Void> call = atletaService.deleteAtleta(UserLoginManager.getInstance().getBearerToken() ,id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("Atleta->", "Deleted: OK");

                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("AtletaManager->", "deleteAtleta: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }


    /* GET - TOP PLAYERS BY X DATEBIRTH */

    public synchronized void getAtletasByBirthdate(final AtletaCallback atletaCallback, String birthdate) {
        Call<List<Atleta>> call = atletaService.getAtletasByBirthdate(UserLoginManager.getInstance().getBearerToken(), birthdate);
        call.enqueue(new Callback<List<Atleta>>() {
            @Override
            public void onResponse(Call<List<Atleta>> call, Response<List<Atleta>> response) {
                atletas = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    atletaCallback.onSuccess(atletas);

                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Atleta>> call, Throwable t) {
                Log.e("AtletaManager->", "getAtletasByBirthdate: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }

}
