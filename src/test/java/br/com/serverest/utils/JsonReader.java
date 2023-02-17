package br.com.serverest.utils;

import com.google.gson.Gson;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JsonReader {
        public  static String readJson(String path) throws IOException {
            String json = String.join(" ",
                    Files.readAllLines(
                            Paths.get(path),
                            StandardCharsets.UTF_8)
            );
            return json;
        }
        public static Object readJsonFile(String path) throws IOException, ParseException {
            File file = new File(path);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(file));
            return obj;
        }
}

