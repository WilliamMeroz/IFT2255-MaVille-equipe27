package maville.equipe27.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maville.equipe27.models.RequeteTravail;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class RequeteTravailDataStore {
    private final String FILE_NAME;

    public RequeteTravailDataStore(String filename) {
        this.FILE_NAME = filename;
    }

    public boolean saveRequete(RequeteTravail requeteTravail) {
        ArrayList<RequeteTravail> requeteTravails = (ArrayList<RequeteTravail>) getRequetes();
        requeteTravails.add(requeteTravail);

        boolean success;
        try(FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeHierarchyAdapter(List.class, new RequeteTravailJsonAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            gson.toJson(requeteTravails, fileWriter);
            success = true;
        } catch (IOException e) {
            success = false;
            e.printStackTrace();
        }

        return success;
    }

    public List<RequeteTravail> getRequetes() {
        List<RequeteTravail> requeteTravailList = null;
        try (FileReader reader = new FileReader(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(List.class, new RequeteTravailJsonAdapter());
            Gson gson = builder.create();
            requeteTravailList = gson.fromJson(reader, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requeteTravailList;
    }
}
