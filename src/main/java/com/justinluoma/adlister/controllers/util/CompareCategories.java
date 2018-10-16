package com.justinluoma.adlister.controllers.util;

import java.util.Arrays;
import java.util.List;

public class CompareCategories {
    public static boolean compare(String[] a1, String[] a2) {
        Arrays.sort(a1);
        Arrays.sort(a2);
        return Arrays.equals(a1, a2);
    }
    public static boolean compare(List<String> a1, String[] a2) {
        String[] tmp = a1.toArray(new String[0]);
        Arrays.sort(tmp);
        Arrays.sort(a2);
        return Arrays.equals(tmp, a2);
    }
}
