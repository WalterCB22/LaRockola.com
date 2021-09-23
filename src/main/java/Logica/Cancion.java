package Logica;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import persistencia.ConexionBD;


public class Cancion {
    private int id;
    private String nombre_cancion;
    private String genero;
    private String artista;

    public Cancion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre_cancion() {
        return nombre_cancion;
    }

    public void setNombre_cancion(String nombre_cancion) {
        this.nombre_cancion = nombre_cancion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }
    
    public boolean guardarCancion(){
        ConexionBD conexion = new ConexionBD();
        String sentencia = "INSERT INTO canciones(nombre_cancion, genero, artista)"
                + " VALUES ( '" + this.nombre_cancion + "','" + this.genero + "',"
                + "'" + this.artista +  "');  ";
        //Vamos a configurar el setAutocommit de la conexionBD a falso
        if(conexion.setAutoCommitBD(false)){
            if(conexion.insertarBD(sentencia)){
                conexion.commitBD();
                conexion.closeConnection();
                return true;
            } else{ //si no logro insertar en la BD
                conexion.rollbackBD();
                conexion.closeConnection();
                return false;
            }
        } else{
            conexion.closeConnection();
            return false;
        }
    }
    public boolean borrarCancion(int id){
        ConexionBD conexion = new ConexionBD();
        String sentencia = "DELETE FROM canciones WHERE id = '" + id + "';";
        if(conexion.setAutoCommitBD(false)){
            if(conexion.borrarBD(sentencia)){
                conexion.commitBD();
                conexion.closeConnection();
                return true;
            } else{
                conexion.rollbackBD();
                conexion.closeConnection();
                return false;
            }
        } else {
            conexion.closeConnection();
            return false;
        }
    }
    public boolean actualizarCancion(){
        ConexionBD conexion = new ConexionBD();
        String sentencia = "UPDATE canciones SET "
                + "nombre_cancion='"+this.nombre_cancion 
                + "',genero='"+this.genero
                + "',artista='"+this.artista
                + "' WHERE id="+ this.id +";";
        System.out.println(sentencia);
        if(conexion.setAutoCommitBD(false)){
            if(conexion.actualizarBD(sentencia)){
                conexion.commitBD();
                conexion.closeConnection();
                return true;
            } else{
                conexion.rollbackBD();
                conexion.closeConnection();
                return false;
            }
           
        } else{
            conexion.closeConnection();
            return false;
        }
    }
    public List<Cancion> listarCanciones() throws SQLException{
        ConexionBD conexion = new ConexionBD();
        String sentencia = "SELECT * FROM canciones ORDER BY id ASC;";
        List<Cancion> listaCanciones = new ArrayList<>();
        ResultSet datos = conexion.consultarDB(sentencia);
        
        Cancion cancion;
        while (datos.next()) {
            cancion = new Cancion();
            cancion.setId(Integer.parseInt(datos.getString("id")));
            cancion.setNombre_cancion(datos.getString("nombre_cancion"));
            cancion.setGenero(datos.getString("genero"));
            cancion.setArtista(datos.getString("artista"));
            listaCanciones.add(cancion);
        }
        conexion.closeConnection();
        return listaCanciones;
    }
    public Cancion obtenerCancion(int id) throws SQLException{
        ConexionBD conexion = new ConexionBD();
        String sentencia = "SELECT * FROM canciones WHERE id = '" + id + "';";
        ResultSet datos = conexion.consultarDB(sentencia);
        if(datos.next()){
            Cancion cancion = new Cancion();
            cancion.setId(Integer.parseInt(datos.getString("id")));
            cancion.setNombre_cancion(datos.getString("nombre_cancion"));
            cancion.setGenero(datos.getString("genero"));
            cancion.setArtista(datos.getString("artista"));
            return cancion;
        } else{
            conexion.closeConnection();
            return null; //no había contacto
        }
    
    }
    @Override 
    public String toString(){
        String mensaje = "ID:"+id+"\nNombre: "+nombre_cancion+"\nGenero: "+genero+"\nArtista: "+artista; 
        return mensaje;
    }
}
