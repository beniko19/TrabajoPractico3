package algoritmoGoloso;

import grafos.GrafoVecinos;

import java.util.ArrayList;

public class RadioCensal {
    private GrafoVecinos _grafoVecinos;
    private ArrayList<Cencista> _cencistas;
    private ArrayList<Integer> _manzanasConCensitas;
    private int _cencistaActual;

    public RadioCensal(GrafoVecinos grafoVecinos, ArrayList<Cencista> cencistas){
        _grafoVecinos = grafoVecinos;
        _cencistas = cencistas;
        _manzanasConCensitas = new ArrayList<>();
        _cencistaActual = 0;
    }

    public void asignarCensistasAManzanas(){
        while ((_manzanasConCensitas.size() != _grafoVecinos.tamano()) || (_cencistaActual < _cencistas.size())){
            for (int manzanaActual = 0; manzanaActual < _grafoVecinos.tamano(); manzanaActual++) {
                asignarManzanaACensista(manzanaActual);
            }
            _cencistaActual++;
        }
    }

    private void asignarManzanaACensista(int manzanaActual) {
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

    private boolean manzanaAsignadaComparteCalle(int manzanaActual) {
        boolean flag = false;
        ArrayList<Integer> manzanasAsignadas =_cencistas.get(_cencistaActual).conocerManzanasAsignadas();
        for (int i = 0; i < manzanasAsignadas.size(); i++) {
            if (_grafoVecinos.sonVecinos(manzanaActual, manzanasAsignadas.get(i)))
                flag = true;
        }
        return flag;
    }

    private boolean censistaTieneLugarParaManzana(int cencistaActual) {
        return _cencistas.get(cencistaActual).cantDeManzanasAsignadas() < 3;
    }

    private boolean censistaTieneManzanaAsignada(int cencistaActual) {
        return _cencistas.get(cencistaActual).tieneManzanaAsignada();
    }

    public String getCensitasConManzanasAsignadas(){
        return _cencistas.toString();
    }

    public ArrayList<Cencista> getCensistas() {
        return _cencistas;
    }
}
