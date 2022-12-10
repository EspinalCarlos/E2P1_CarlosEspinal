
package e2p1_carlosespinal;
import java.util.ArrayList;

public class MaquinaEstados {
    private static ArrayList<String> estados = new ArrayList<>();
    private static ArrayList<String> estados_aceptacion = new ArrayList<>();
    private static ArrayList<String> aristas = new ArrayList<>();
    private static String estado_actual;
    
    
    public MaquinaEstados(String estados, String aristas) {
        this.estados = splitStr(estados,';');
        extractAcceptNodes();
        this.aristas = splitStr(aristas,';');
        this.estado_actual = this.estados.get(0);
        
        
    }
    public static ArrayList<String> splitStr(String str, char delim) {
        ArrayList<String> arreglo = new ArrayList<>();
        
        String[] arrayString = str.split(Character.toString(delim));
        
        for (int i = 0; i < arrayString.length; i++) {
            arreglo.add(arrayString[i]);
        }
       
            
        return arreglo;
    }

    public static ArrayList<String> getEstados() {
        return estados;
    }

    public static void setEstados(ArrayList<String> estados) {
        MaquinaEstados.estados = estados;
    }

    public static ArrayList<String> getEstados_aceptacion() {
        return estados_aceptacion;
    }

    public static void setEstados_aceptacion(ArrayList<String> estados_aceptacion) {
        MaquinaEstados.estados_aceptacion = estados_aceptacion;
    }

    public static ArrayList<String> getAristas() {
        return aristas;
    }

    public static void setAristas(ArrayList<String> aristas) {
        MaquinaEstados.aristas = aristas;
    }

    public static String getEstado_actual() {
        return estado_actual;
    }

    public static void setEstado_actual(String estado_actual) {
        MaquinaEstados.estado_actual = estado_actual;
    }
    
    public static void extractAcceptNodes() {
        String m = ""; 
        for(String estado: estados) {
            if (estado.contains(".")) {
                estado = estado.substring(1);
                estados_aceptacion.add(estado);
            }
        }
    }
    public static String getArista(String str) {
        for (int i = 0; i < aristas.size(); i++) {
            if (aristas.get(i).contains(str)) {
                return aristas.get(i);
            }
            
        }
        return "";
        
    }
    
    public static String computeStr(String std) {
        String output = "";
        estado_actual = estados.get(0);
        for (int i = 0; i < std.length(); i++) {
            String ar = getArista(estado_actual+','+std.charAt(i));
            if (!ar.equals("")) {
                estado_actual = ar.split(",")[2];
                output += ar.split(",")[0]+':'+ar.split(",")[1]+"->"+ar.split(",")[2] + "\n";
                
            }
            else {
                output+="Rechazada";
                return output;
            }
        }
        if (estados_aceptacion.contains(estado_actual)) {
            output += "Aceptada";
            return output;
        }
        else {
            output += "Rechazada";
            return output;
        }
    }
}
