package br.com.serverest.utils;

import java.text.Normalizer;

public class NormalizerString {
    public static String getNormalizerString(String text){
        return Normalizer.normalize(text, Normalizer.Form.NFC);
    }
}
