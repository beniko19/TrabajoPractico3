package algoritmoGoloso;

import java.util.ArrayList;

public class Cencista {
    private static int _id = 0;
    private boolean _manzanaAsignada;
    private ArrayList<Integer> _manzanas;
    private int id;
    public Cencista(){
        id = ++_id;
        _manzanaAsignada = false;
        _manzanas = new ArrayList<>();
    }

    public boolean tieneManzanaAsignada(){
        return _manzanaAsignada;
    }

    public ArrayList<Integer> conocerManzanasAsignadas(){
        return _manzanas;
    }

    public int conocerID(){
        return id;
    }

    public void asignarManzanas(int manzana){
        _manzanas.add(manzana);
        _manzanaAsignada = true;
    }

    public int cantDeManzanasAsignadas(){
        return _manzanas.size();
    }

    @Override
    public String toString(){
        StringBuilder toString = new StringBuilder();
        toString.append("ID: ").append(id).append("\n").append("Manzanas Asignadas: ").append(_manzanas.toString());
        return toString.toString();
    }
}
