package com.justinluoma.adlister.controllers.util;

import java.util.HashMap;

public class Json {
    public static HashMap<String, Object> gen(String[] keys,Object... values) {
        HashMap<String, Object> v = new HashMap<>(keys.length);
        for (int i = 0; i < keys.length; i++) {
            v.put(keys[i], values[i]);
        }
        return v;
    }
}
