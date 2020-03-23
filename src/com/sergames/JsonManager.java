package com.sergames;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class JsonManager {
    ArrayList<Entry> read() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("scores.json");
        JSONArray jsonArray = (JSONArray) parser.parse(reader);
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Entry> topEntries = new ArrayList<>();
        JSONObject obj = null;

        if (jsonArray != null) {
            for (Object o : jsonArray) {
                obj = (JSONObject) o;
                entries.add(new Entry(obj.get("PLAYER").toString(), Integer.parseInt(obj.get("SCORE").toString())));
            }
            Comparator<Entry> compareByScore = Comparator.comparing(Entry::getScore);
            entries.sort(compareByScore.reversed());

            if (entries.size() < 5) topEntries.addAll(entries);
            else {
                for (int i = 0; i < 5; i++) {
                    topEntries.add(entries.get(i));
                }
            }
        }
        return topEntries;
    }

    void write(String player, int score) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("scores.json");
        JSONArray jsonArray = (JSONArray) parser.parse(reader);
        JSONObject object = new JSONObject();
        object.put("PLAYER", player);
        object.put("SCORE", score);
        jsonArray.add(object);
        FileWriter writer = new FileWriter("scores.json");
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();
    }
}
