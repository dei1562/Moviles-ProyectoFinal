package com.example.proyectofinal;

public class Config {

    public static final String URL_Display = "https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=k4anUu450QKtswp7flGEziNSjbGAoxtZ";
    public static final String URL_GetUser = "https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=k4anUu450QKtswp7flGEziNSjbGAoxtZ";

    // keys for send and request script
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_UNAME = "username";
    public static final String KEY_PASS = "password";

    // json tags
    public static final String TAG_ARRAY = "results";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "title";
    public static final String TAG_UNAME = "type";
    public static final String TAG_PASS = "url";

    // base id use to pass the value of user id into intent
    public static final String BASE_ID = "base_id";
}
