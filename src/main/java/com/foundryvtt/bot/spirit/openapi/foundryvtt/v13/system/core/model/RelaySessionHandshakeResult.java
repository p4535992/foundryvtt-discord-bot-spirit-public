package com.foundryvtt.bot.spirit.openapi.foundryvtt.v13.system.core.model;

public class RelaySessionHandshakeResult extends AbstractFoundryModel {

    private String token;
    private String publicKey;
    private String nonce;
    private Long expires;
    private String error;

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getNonce() {
        return this.nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public Long getExpires() {
        return this.expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
