package com.bilal.atleta.controller.managers;

import com.bilal.atleta.model.UserToken;

public interface LoginCallback {
    void onSuccess(UserToken userToken);
    void onFailure(Throwable t);
}
