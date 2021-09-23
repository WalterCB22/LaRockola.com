<%-- 
    Document   : peticionesCanciones
    Created on : 22/09/2021, 06:14:14 PM
    Author     : walte
--%>


<%@page import="java.util.List"%>
<%@page import="com.google.gson.Gson"%>

<%@page import="Logica.Cancion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 

    String respuesta = "{";
    String proceso = request.getParameter("proceso");
    Cancion c = new Cancion();
    switch(proceso){
        case "guardarCancion": 
            c.setNombre_cancion(request.getParameter("nombre_cancion"));
            c.setGenero(request.getParameter("genero"));
            c.setArtista(request.getParameter("artista"));
            if(c.guardarCancion()){
                respuesta += "\"" + proceso + "\": true";
            }else{
                respuesta += "\"" + proceso + "\": false";  
            }
            break;
            
        case "listarCanciones":
            List<Cancion> listaCanciones = c.listarCanciones();
            if(listaCanciones.isEmpty()){
                respuesta += "\"" + proceso + "\": false";
            }else{
                respuesta += "\"" + proceso + "\": true,\"Canciones\":" + new Gson().toJson(listaCanciones);
            }
            break;
            
        case "borrarCanciones":
            int identificacion = Integer.parseInt(request.getParameter("id"));
            if(c.borrarCancion(identificacion)){
                respuesta += "\"" + proceso + "\": true";
            }else{
                respuesta += "\"" + proceso + "\": false";  
            }
            break;
        case "obtenerCancion":
            int id = Integer.parseInt(request.getParameter("id"));
            Cancion c2 = new Cancion();
            c2 = c.obtenerCancion(id);
            if(c2 == null){
                respuesta += "\"" + proceso + "\": false,\"Cancion\": la cancion no existe";  
            }
            else{
                respuesta += "\"" + proceso + "\": true,\"Cancion\":" + new Gson().toJson(c2);        
            }
            break;
        case "actualizarCancion":
            c.setId(Integer.parseInt(request.getParameter("id")));
            c.setNombre_cancion(request.getParameter("nombre_cancion"));
            c.setGenero(request.getParameter("genero"));
            c.setArtista(request.getParameter("artista"));
            if(c.actualizarCancion()){
                respuesta += "\"" + proceso + "\": true";
            }else{
                respuesta += "\"" + proceso + "\": false";  
            }
            break;
        default:
            break;
    }
    respuesta+="}";
    response.setContentType("application/json;charset=iso-8859-1");

    out.print(respuesta);

%>
