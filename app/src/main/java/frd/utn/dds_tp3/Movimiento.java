package frd.utn.dds_tp3;

import org.json.JSONObject;

public class Movimiento {

    String creado;
    int estado;
    int id;
    int idCuenta;
    double importe;
    int tipo;

    public Movimiento(JSONObject json){
        try {
            creado = json.getString("creado");
            estado = json.getInt( "estado");
            id = json.getInt("id");
            idCuenta = json.getInt( "idCuenta");
            importe = json.getDouble( "importe");
            tipo = json.getInt("tipo");

            //if(json.has("tipo") && !json.isNull("tipo"))
              //  tipo = json.getInt("tipo");

        } catch(Exception e){}
    }

    public String toString(){
        return "Movimiento ID: " + id + " | Tipo: " + tipo;
    }

}
