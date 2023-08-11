package com.zaki.demo.usertest;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestUtil {
    public static final ObjectMapper mapper = new ObjectMapper();
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException{
        return mapper.writeValueAsBytes(object);
    }
}
