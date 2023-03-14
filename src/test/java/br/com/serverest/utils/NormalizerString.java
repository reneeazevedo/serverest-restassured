package br.com.serverest.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


public class NormalizerString {
    public static String getNormalizerString(String text) throws UnsupportedEncodingException {
        byte[] textBytes = text.getBytes();

        String encodedString = new String(textBytes, StandardCharsets.UTF_8);

        return encodedString;
    }
}
