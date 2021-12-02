package refugio.model;

/**
 *
 * @author Jairo
 * @description Clase principal del objeto animal con cada uno de sus atributos
 * y constructores necesarios.
 */
public class Animal {
    
    private int id;
    private String nombre;
    private char sexo;
    private String fechanac;
    private String color;
    private int razaId;
    private String raza;
    private double peso;
    private String caract;
    private int especieId;
    private String especie;

    public Animal() {
        id=this.id;
        nombre=this.nombre;
        sexo=this.sexo;
        fechanac=this.fechanac;
        color=this.color;
        raza=this.raza;
        peso=this.peso;
        caract=this.caract;
    }

    public Animal(int id, String nombre, char sexo, String color, double peso, String caract) {
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.color = color;
        this.peso = peso;
        this.caract = caract;
    }

    public Animal(String nombre, char sexo, String color, int razaId, double peso, String caract) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.color = color;
        this.razaId = razaId;
        this.caract = caract;
        
        this.fechanac="";
        this.especie="";
        this.raza="";
    }
    
    public Animal(String nombre, char sexo, String color, String especie, String raza, double peso, String caract) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.color = color;
        this.especie = especie;
        this.raza = raza;
        this.peso = peso;
        this.caract = caract;
    }
    
    public Animal(String nombre, char sexo, String color, String raza, double peso, String caract) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.color = color;
        this.raza = raza;
        this.peso = peso;
        this.caract = caract;
    }
    

    public Animal(String nombre, char sexo, String color, double peso, String caract) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.color = color;
        this.peso = peso;
        this.caract = caract;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getRazaId() {
        return razaId;
    }

    public void setRazaId(int razaId) {
        this.razaId = razaId;
    }
    
    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCaract() {
        return caract;
    }

    public void setCaract(String caract) {
        this.caract = caract;
    }

    public int getEspecieId() {
        return especieId;
    }

    public void setEspecieId(int especieId) {
        this.especieId = especieId;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
    
    public static String toEspecieString(int idEspecie){
        switch(idEspecie){
            case 1: return "Perro";
            case 2: return "Gato";
            case 5: return "Otra";
            default: return "Desconocida";
        }
    }
    
    public static String toRazaString(int idRaza){
        switch(idRaza){
            case 1: return "Siamés";
            case 2: return "Persa";
            case 3: return "Siberiano";
            case 4: return "Bengalí";
            case 5: return "Angora turco";
            case 6: return "Siberiano";
            case 7: return "Bulldog";
            case 8: return "Labrador";
            case 9: return "Caniche";
            case 10: return "Pastor alemán";
            case 11: return "Chihuahua";
            case 12: return "Terrier";
            case 14: return "Perro genérico";
            case 15: return "Gato genérico";
            default: return "Desconocido";
        }
    }
    
    public static int toRazaInteger(String raza){
        switch(raza){
            case "Siamés": return 1;
            case "Persa": return 2;
            case "Siberiano": return 3;
            case "Bengalí": return 4;
            case "Angora turco": return 5;
            case "Siberiano2": return 6;
            case "Bulldog": return 7;
            case "Labrador": return 8;
            case "Caniche": return 9;
            case "Pastor alemán": return 10;
            case "Chihuahua": return 11;
            case "Terrier": return 12;
            case "Perro genérico": return 14;
            case "Gato genérico": return 15;
            default: return 0;
        }
    }
    
}
