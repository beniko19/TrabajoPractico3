import algoritmoGoloso.Cencista;
import algoritmoGoloso.RadioCensal;
import app.ManejoJSON;
import grafos.GrafoVecinos;
import java.io.File;
import java.util.ArrayList;
import java.util.stream.Stream;

public class main {
    public static void main(String[] args) {

        GrafoVecinos grafoVecinos = ManejoJSON.leerJSON(new File("pruebaManejoJSON.json"));
        ArrayList<Cencista> cencistas = new ArrayList<>();
        Stream.iterate(0, n -> n +1).limit(grafoVecinos.tamano()).forEach(grafoTamanio -> cencistas.add(new Cencista()));

        /*for (int i = 0; i < grafoVecinos.tamano(); i++) {
            cencistas.add(new Cencista());
        }*/

        RadioCensal radioCensal = new RadioCensal(grafoVecinos, cencistas);
        radioCensal.asignarCensistasAManzanas();

        /*for (int i = 0; i < radioCensal.getCensistas().size(); i++) {
            System.out.println(radioCensal.getCensistas().get(i).toString());
        }*/

        cencistas.stream().forEach(System.out::println);
    }
}
