package com.github.rodlibs.persistentcookieokhttp3;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class PersistencesCookies {

    private Context context;
    public CookieJar cookieJar;
    private boolean hasCookies;
    private ArrayList<Cookie> listCookies;
    private String KEY_COOKIE_STORE = "CookieStore";



    public PersistencesCookies(Context conte){
        this.context = conte;
        hasCookies = false;
        listCookies = new ArrayList<>();
        cookieJar = new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                if (hasCookies == false) {
                    for (Cookie i : cookies) {
                        saveSharedPreference(url.host(), i);
                    }
                }
            }
            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                Cookie cookie = getSharedPreference(url.host());
                if (cookie != null) {
                    listCookies.add(cookie);
                    hasCookies = true;
                }
                return listCookies;
            }
        };
    }




    private void saveSharedPreference(String key, Cookie cookie){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isActive",true);
        editor.putString("name",cookie.name());
        editor.putString("value",cookie.value());
        editor.putLong("expiresAt",cookie.expiresAt());
        editor.putString("domain",cookie.domain());
        editor.putString("path",cookie.path());
        editor.putBoolean("secure",cookie.secure());
        editor.putBoolean("httpOnly",cookie.httpOnly());
        editor.putBoolean("persistent",cookie.persistent());
        editor.putBoolean("hostOnly",cookie.hostOnly());
        editor.apply();

        saveKey(key);
    }



    private Cookie getSharedPreference(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("isActive", false)) {
            String name = sharedPreferences.getString("name", "");
            String value = sharedPreferences.getString("value", "");
            long expiresAt = sharedPreferences.getLong("expiresAt", 0);
            String domain = sharedPreferences.getString("domain", "");
            String path = sharedPreferences.getString("path", "");
            boolean secure = sharedPreferences.getBoolean("secure", false);
            boolean httpOnly = sharedPreferences.getBoolean("httpOnly", false);
            boolean persistent = sharedPreferences.getBoolean("persistent", false);
            boolean hostOnly = sharedPreferences.getBoolean("hostOnly", false);

            Cookie cookie = new Cookie.Builder()
                    .name(name)
                    .value(value)
                    .expiresAt(expiresAt)
                    .domain(domain)
                    .path(path)
                    .secure()
                    .httpOnly()
                    .hostOnlyDomain(domain)
                    .build();
            return cookie;
        }
        else{
            return null;
        }
    }



    public Cookie getCookies() {
        return getSharedPreference(getKey());
    }



    public boolean hasCookies(String host){
        Cookie cookie = getCookies();
       return cookie != null && host.contains(cookie.domain());
    }



    private void saveKey(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_COOKIE_STORE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key",key);
        editor.apply();
    }


    private String getKey(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_COOKIE_STORE, Context.MODE_PRIVATE);
        String key = sharedPreferences.getString("key", "");
        return key;
    }


    private void removeKey(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_COOKIE_STORE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("key");
        editor.clear();
        editor.apply();
    }



    public void removeAllCookie(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(getKey(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("isActive");
        editor.remove("name");
        editor.remove("value");
        editor.remove("expiresAt");
        editor.remove("domain");
        editor.remove("path");
        editor.remove("secure");
        editor.remove("httpOnly");
        editor.remove("persistent");
        editor.remove("hostOnly");
        editor.clear();
        editor.apply();
        hasCookies = true;
        listCookies.clear();
        removeKey();
    }
}