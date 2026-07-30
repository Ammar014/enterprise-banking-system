package com.ammarkanani.banking_app.dto;

public class ApplicationStatusResponse {

    private String application;
    private String status;
    private String version;

    public ApplicationStatusResponse(String application, String status, String version){
        this.application = application;
        this.status = status;
        this.version = version;
    }

    public String getApplication(){
        return application;
    }

    public String getStatus() {
        return status;
    }

    public String getVersion() {
        return version;
    }

}
