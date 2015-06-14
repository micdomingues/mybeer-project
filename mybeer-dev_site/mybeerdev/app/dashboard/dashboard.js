'use strict';

angular.module('myApp.dashboard', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/dashboard', {
        templateUrl: 'dashboard/dashboard.html',
        controller: 'DashboardCtrl'
    });
    }])

    .controller('DashboardCtrl', ['$scope','$http','$q', 'loginService','dashboardService', function ($scope, $http , $q,  loginService, dashboardService) {
        
        console.log("ueh foi");
        
    $scope.rate = 3;
    $scope.x = 2;
    $scope.max = 5;
    $scope.isReadonly = true;
    $scope.hoveringOver = function (value) {
        $scope.overStar = value;
    };
    
        // get usuarios
        
        function getUser() {
            dashboardService.getDadosUser(loginService.getId())
                .success(function (data) {
                $scope.usuario = data;
                console.log($scope.usuario);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getUser();
        
        //get eventos
        
        function getEventos() {
            dashboardService.getEventos()
                .success(function (data) {
                $scope.eventos = data;
                console.log($scope.eventos);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getEventos();
        
        //getBaresFavoritos
        function getBaresFavoritos() {
            dashboardService.getBaresFavoritos(loginService.getId())
                .success(function (data) {
                $scope.favoritos = data;
                console.log($scope.favoritos);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getBaresFavoritos();
        
        
        //getMensagens
        function getMensagens() {
            dashboardService.getMensagens(loginService.getId())
                .success(function (data) {
                $scope.mensagens = data;
                console.log($scope.mensagens);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getMensagens();
        
        
       
        
        
        
        
    }]).service("dashboardService", function ($http, $q,nomeBanco) {

    this.getEventos = function () {
        return $http.get(nomeBanco.getLink() + 'eventos');

    }
    
    this.getMensagens = function (id) {
        return $http.get(nomeBanco.getLink() + 'clientes/mensagens/' + id);

    }
    this.getDadosUser = function (id) {
        return $http.get(nomeBanco.getLink() + 'pessoas/' + id);

    }
    
    this.getBaresFavoritos = function (id) {
        return $http.get(nomeBanco.getLink() + 'clientes/bares/favoritos/' + id);

    }
    
}).filter('novo', function() {
    return function(input) {
        var stringNovo = "";
       if(input != "true"){
           stringNovo = "novo"
       }
        return stringNovo;
    };
});