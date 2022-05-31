package app;

import algoritmoGoloso.Cencista;
import algoritmoGoloso.RadioCensal;
import grafos.GrafoVecinos;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Enlace {
    private GrafoVecinos grafoVecinos;
    private RadioCensal radioCensal;

    public Enlace(){
        grafoVecinos = new GrafoVecinos();
    }

    public void cargarGrafo(File nombreArchivoTxt){
        grafoVecinos = ManejoJSON.leerJSON(nombreArchivoTxt);
    }

    public void agregarManzana(){
        grafoVecinos.agregarVertice();
    }

    public void agregarCalleAManzana(int manzana, int manzana2){
        grafoVecinos.agregarVecino(manzana, manzana2);
    }

    public void agregarCensitas(int cantCencistas){
        radioCensal = new RadioCensal(grafoVecinos, cantCencistas);
    }

    public Cencista obtenerCensita(int cencista){
        return radioCensal.getCencista(cencista);
    }

    public ArrayList<Cencista> obtenerTodosLosCencistas(){
        return radioCensal.getCensistas();
    }

    public ArrayList<Integer> obtenerManzanasAsignadasACencista(int cencista){
        return radioCensal.getCencista(cencista).conocerManzanasAsignadas();
    }

    public void asignarCensitasAManzanas(){
        radioCensal.asignarCensistasAManzanas();
    }
}
