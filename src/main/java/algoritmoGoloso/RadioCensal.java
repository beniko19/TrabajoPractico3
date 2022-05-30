package algoritmoGoloso;

import grafos.GrafoVecinos;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class RadioCensal {
    GrafoVecinos _grafoVecinos;
    ArrayList<Cencista> _cencistas;
    ArrayList<Integer> _manzanasConCensitas;
    int _cencistaActual;

    public RadioCensal(GrafoVecinos grafoVecinos, ArrayList<Cencista> cencistas){
        _grafoVecinos = grafoVecinos;
        _cencistas = cencistas;
        _manzanasConCensitas = new ArrayList<>();
        _cencistaActual = 0;
    }

    public void asignarCensistasAManzanas(){
        while (isNotSameSizeGraphWithSquares() && hayCenistaAvailable()){
            Stream.iterate(0, n -> n + 1).limit(_grafoVecinos.tamano())
                    .forEach(this::asignarManzanaACensista);
            /*for (int manzanaActual = 0; manzanaActual < _grafoVecinos.tamano(); manzanaActual++) {
                asignarManzanaACensista(manzanaActual);
            }*/
            _cencistaActual++;
        }
    }

    boolean hayCenistaAvailable() {
        return _cencistaActual < _cencistas.size();
    }

    boolean isNotSameSizeGraphWithSquares() {
        return _manzanasConCensitas.size() != _grafoVecinos.tamano();
    }

    void asignarManzanaACensista(int manzanaActual) {
        if (!censistaTieneManzanaAsignada(_cencistaActual) && !_manzanasConCensitas.contains(manzanaActual)){
            _cencistas.get(_cencistaActual).asignarManzana(manzanaActual);
            _manzanasConCensitas.add(manzanaActual);
        }
        else {
            if (censistaTieneLugarParaManzana(_cencistaActual)){
                if (manzanaAsignadaComparteCalle(manzanaActual) && !_manzanasConCensitas.contains(manzanaActual)){
                    _cencistas.get(_cencistaActual).asignarManzana(manzanaActual);
                    _manzanasConCensitas.add(manzanaActual);
                }
            }
        }
    }

    boolean manzanaAsignadaComparteCalle(int manzanaActual) {
        AtomicBoolean flag = new AtomicBoolean(false);
        ArrayList<Integer> manzanasAsignadas =_cencistas.get(_cencistaActual).conocerManzanasAsignadas();
        manzanasAsignadas.stream().forEach(m -> {
            if (_grafoVecinos.sonVecinos(manzanaActual, m)) {
                flag.set(true);
            }
        });
        /*for (int i = 0; i < manzanasAsignadas.size(); i++) {
            if (_grafoVecinos.sonVecinos(manzanaActual, manzanasAsignadas.get(i)))
                flag.set(true);
        }*/
        return flag.get();
    }

    boolean censistaTieneLugarParaManzana(int cencistaActual) {
        return _cencistas.get(cencistaActual).cantDeManzanasAsignadas() < 3;
    }

    boolean censistaTieneManzanaAsignada(int cencistaActual) {
        return _cencistas.get(cencistaActual).tieneManzanaAsignada();
    }

    public String getCensitasConManzanasAsignadas(){
        return _cencistas.toString();
    }

    public ArrayList<Cencista> getCensistas() {
        return _cencistas;
    }

    public Cencista getCencista(int censita){
        return this._cencistas.get(censita);
    }
}
