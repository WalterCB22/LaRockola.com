package persistencia;

import java.sql.*;

public class ConexionBD {
  
    private String DB_driver = "";
    private String url = "";
    private String db = "";
    private String host = "";
    private String username = "";
    private String password = "";
    private ResultSet resultset = null; 
    private Statement stmt = null;
    public Connection conexion = null;
    
    
    public ConexionBD() {
        DB_driver = "com.mysql.jdbc.Driver";
        host = "localhost:3306";
        db = "rockola";
        url = "jdbc:mysql://"+host+"/"+db;
        username = "root";
        password = "admin";
        
        try{
            //asignacion de Driver
            Class.forName(DB_driver);
        }catch(ClassNotFoundException ex){
            System.out.println("Error en la asignacion del driver");
        }
        try{
            conexion = DriverManager.getConnection(url, username, password);
            System.out.println("conexion Exitosa");
        }catch(SQLException ex){
            System.out.println("Error en la conexion");
        }
    }
    
    public Connection getConnection(){
        return conexion;
    }
    
    public void closeConnection(){
        if(conexion != null){
            try{
                conexion.close();
            }catch(SQLException ex){
                System.out.println("Error al cerrar la conexion");
            }
        }
    }
    
    public ResultSet consultarDB(String sentencia){
        try{
            stmt = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = stmt.executeQuery(sentencia);
        }catch(SQLException ex){
            System.out.println("Error al consultar en la conexion");
        }
        return resultset;
    }
    
    public boolean insertarBD(String sentencia){
        try{
            stmt = conexion.createStatement();
            stmt.execute(sentencia);
            return true;
        }catch(SQLException ex){
            System.out.println("Error al insertar en la base de datos");
            return false;
        }
    }
    
    public boolean borrarBD(String sentencia){
        try{
            stmt = conexion.createStatement();
            stmt.execute(sentencia);
            return true;
        }catch(SQLException ex){
            System.out.println("Error al borrar en la base de datos");
            return false;
        }
    }
    
    public boolean actualizarBD(String sentencia){
        try{
            stmt = conexion.createStatement();
            stmt.execute(sentencia);
            return true;
        }catch(SQLException ex){
            System.out.println("Error al actualizar en la base de datos");
            return false;
        }
    }
    
    public boolean setAutoCommitBD(boolean commit){
        try{
            conexion.setAutoCommit(commit);
            return true;
        }catch(SQLException ex){
            System.out.println("Error al hacer autocommit");
            return false;
        }
    }
    
    public boolean commitBD(){
        try{
            conexion.commit();
            return true;
        }catch(SQLException ex){
            System.out.println("Error al hacer autocommit");
            return false;
        }
    }
    
    public boolean rollbackBD(){
        try{
            conexion.rollback();
            return true;
        }catch (SQLException sqlex) {
            System.out.println("Error al hacer rollback " + sqlex.getMessage());
            return false;
        }
    }
}
