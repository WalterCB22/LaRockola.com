<%-- 
    Document   : index
    Created on : 27/09/2021, 07:16:59 PM
    Author     : walte
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"/>
        <script src = "http://ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body >
        <div class="container-fluid" ng-app="rockola" ng-controller="cancionController">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="#" ng-click="mostrarform()">Guardar Cancion</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" ng-click="listarCanciones()" >Listar Canciones</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class ="row d-flex justify-content-center">
                <div class="col-4">
                    <h1> 
                        <center>
                            <span ng-show="boolactualizar">Actualizar</span> Canciones!</h1> 
                        </center>
                </div>   
            </div>
            <div class="container-fluid" ng-show="!mostrarLista">
                <div class ="row d-flex justify-content-center" >
                    <div class="col-6">
                        <div class="mb-3">
                            <label for="cancion" class="form-label">Nombre de la cancion</label>
                            <input type="text" class="form-control" ng-model="cancion" placeholder="Escriba el nombre de la cancion">
                        </div>
                        <div class="mb-3">
                            <label for="artista" class="form-label">Nombre del artista</label>
                            <input type="text" class="form-control" ng-model="artista" placeholder="Escriba el nombde del artista">
                        </div>
                        <div class="mb-3">
                            <label for="genero" class="form-label">Genero de la cancion</label>
                            <input type="text" class="form-control" ng-model="genero" placeholder="Escriba el nombre de la cancion">
                        </div>
                        <div class="col-auto">
                            <button type="button" ng-click="guardarCancion()" ng-show="!boolactualizar"class="btn btn-primary mb-3">Guardar Cancion</button>
                            <button type="button" ng-click="actualizarCancion(id)" ng-show="boolactualizar" class="btn btn-primary mb-3">Actualizar Cancion</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid" ng-show="mostrarLista">
                <div class ="row d-flex justify-content-center " >
                    <div class="ool-6">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Cancion</th>
                                    <th scope="col">Genero</th>
                                    <th scope="col">Artista</th>
                                    <th scope="col">Acciones</th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="cancion in listaCanciones">
                                    <th scope="row">{{cancion.id}}</th>
                                    <td>{{cancion.nombre_cancion}}</td>
                                    <td>{{cancion.genero}}</td>
                                    <td>{{cancion.artista}}</td>
                                    <td>
                                        <button type="button" ng-click="mostarActualizar(cancion)" class="btn btn-warning mb-3">Actualizar</button>
                                        <button type="button" ng-click="borrarCancion(cancion.id)" class="btn btn-danger mb-3">Borrar</button>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <script src="cancionCtrl.js"></script>

    </body>
</html>
