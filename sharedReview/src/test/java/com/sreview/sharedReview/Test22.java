package com.sreview.sharedReview;

import org.junit.jupiter.api.Test;

public class Test22 {

    @Test
    void contextLoads() {
        String url = "http://localhost:8080/file/temp/496f31303e764592a7e86674bbad7760.jpg";
        String temp = "http://localhost:8080/file/temp/";

        String replace = url.replace(temp, "");
        System.out.println(replace);

    }
}
