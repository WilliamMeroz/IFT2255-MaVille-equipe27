package maville.equipe27.helpers;

import maville.equipe27.models.RequeteTravail;
import maville.equipe27.models.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.lang.reflect.Type;

public class RequeteTravailStore {
    private final String FILE_NAME;

    public RequeteTravailStore(String fileName) {
        this.FILE_NAME = fileName;
    }

    public List<RequeteTravail> getRequetes() {
        List<RequeteTravail> requetes  = new ArrayList<>();
        try (Reader reader = new FileReader(FILE_NAME)) {
            Gson gson = new Gson();
            Type requeteType = new TypeToken<List<User>>() {}.getType();
            requetes = gson.fromJson(reader, requeteType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requetes;
    }

    public Boolean saveRequetes(RequeteTravail requete) {
        List<RequeteTravail> requetes = (List<RequeteTravail>) getRequetes();
        requetes.add(requete);

        boolean success;
        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            Gson gson = new Gson();
            gson.toJson(requetes, fileWriter);
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        }

        return success;
    }
}

