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
    public static ArrayList<Entry> read() {
        JSONParser parser = new JSONParser();
        FileReader reader = null;
        JSONArray jsonArray = null;
        JSONObject obj = null;
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Entry> topEntries = new ArrayList<>();
        try {
            reader = new FileReader("scores.json");
            jsonArray = (JSONArray) parser.parse(reader);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        if (jsonArray != null) {
            for (Object o : jsonArray) {
                obj = (JSONObject) o;
                entries.add(new Entry(obj.get("PLAYER").toString(), Integer.parseInt(obj.get("SCORE").toString())));
            }
            Comparator<Entry> compareByScore = Comparator.comparing(Entry::getScore);
            entries.sort(compareByScore.reversed());
            if (entries.size() < 5) topEntries.addAll(entries);
            else for (int i = 0; i < 5; i++) topEntries.add(entries.get(i));
        }
        return topEntries;
    }

    public static void write(Entry entry) {
        JSONParser parser = new JSONParser();
        FileReader reader = null;
        JSONArray jsonArray = null;
        try {
            reader = new FileReader("scores.json");
            jsonArray = (JSONArray) parser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject object = new JSONObject();
        object.put("PLAYER", entry.getPlayer());
        object.put("SCORE", entry.getScore());
        jsonArray.add(object);
        FileWriter writer = null;
        try {
            writer = new FileWriter("scores.json");
            writer.write(jsonArray.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
