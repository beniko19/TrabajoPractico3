package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grafos.GrafoVecinos;

import java.io.*;

public class ManejoJSON {

    public static void generarJson(String nombreArchivoGuardar, GrafoVecinos grafoVecinos){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(grafoVecinos);
        try{
            FileWriter writer = new FileWriter(nombreArchivoGuardar);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static GrafoVecinos leerJSON(File nombreArchivoCargar){
        Gson gson = new Gson();
        GrafoVecinos grafo = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nombreArchivoCargar));
            grafo = gson.fromJson(reader, GrafoVecinos.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return grafo;
    }
}
