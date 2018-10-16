package com.justinluoma.adlister.controllers.util;

import com.justinluoma.adlister.models.Ad;

import java.util.ArrayList;
import java.util.List;

public class Sublist {
    public static List<Ad> get(List<Ad> list, int start, int count) {
        if (start + count > list.size())
            count = list.size() - start;
        List<Ad> out = new ArrayList<>(count);
        for (int i = start; i < start + count; i++) {
            System.out.println(list.get(i).categories());
            out.add(list.get(i));
        }
        return out;
    }
}
