package com.bilal.atleta.controller.managers;

import com.bilal.atleta.model.Atleta;

import java.util.List;


public interface AtletaCallback {
    void onSuccess(List<Atleta> playerList);
    void onSucces();

    void onFailure(Throwable t);
}
