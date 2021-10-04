var app = angular.module('rockola', []);

app.controller('cancionController', function ($scope, $http) {

    $scope.mostrarform = function () {
        $scope.mostrarLista = false;
        $scope.boolactualizar = false;
        $scope.id=""
         $scope.cancion = "";
         $scope.artista = "";
         $scope.genero = "";
        console.log($scope.mostrarLista)
        
    }
    $scope.listaCanciones = "";
    $scope.guardarCancion = function () {
        
        if ($scope.cancion === undefined || $scope.artista === undefined || $scope.genero === undefined) {
            alert("todos los campos son obligatorios");
        } else {
            let cancion = {
                proceso: 'guardarCancion',
                nombre_cancion: $scope.cancion,
                artista: $scope.artista,
                genero: $scope.genero
            }
            $http({
                method: 'POST',
                url: 'peticionesCanciones.jsp',
                params: cancion
            }).then(function (respuesta) {
                $scope.listarCanciones();
                console.log("funciona")
            });
        }
    }
    $scope.listarCanciones = function () {
        $scope.mostrarLista = true;
        $scope.boolactualizar = false;
        console.log($scope.mostrarLista);
        let peticion = {
            proceso: 'listarCanciones',
        }
        $http({
            method: 'GET',
            url: 'peticionesCanciones.jsp',
            params: peticion
        }).then(function (respuesta) {
            $scope.listaCanciones = respuesta.data.Canciones;
            console.log($scope.listaCanciones);
        });
    }
    $scope.borrarCancion = function (id) {
        let peticion = {
            proceso: 'borrarCanciones',
            id: id
        }
        $http({
            method: 'GET',
            url: 'peticionesCanciones.jsp',
            params: peticion
        }).then(function (respuesta) {
            if (respuesta.data.borrarCanciones) {
                $scope.listarCanciones();
                console.log("funciona")
            } else {
                alert("no se pudo borrar el registro")
            }
        });
    }
    $scope.actualizarCancion = function(id){
        let cancion = { 
                proceso: 'actualizarCancion',
                id:id,
                nombre_cancion: $scope.cancion,
                artista: $scope.artista,
                genero: $scope.genero
            }
        $http({
            method: 'GET',
            url: 'peticionesCanciones.jsp',
            params: cancion
        }).then(function (respuesta) {
            console.log(respuesta);
            $scope.listarCanciones();
        });
    }
    $scope.mostarActualizar = function (cancion){
         $scope.mostrarLista = false;
         $scope.boolactualizar = true;
         $scope.cancion = cancion.nombre_cancion;
         $scope.artista = cancion.artista;
         $scope.genero = cancion.genero;
         $scope.id = cancion.id;
    }

});


