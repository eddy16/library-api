package com.edcg.model.jaxb

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

/**
 * Created by Edgar on 06/05/2017.
 */
@Canonical
class LoginRequest {
    String username
    String password

    @JsonCreator
    LoginRequest(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username
        this.password = password
    }
}
