'use strict';

angular.module('myApp.favoritos', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/favoritos', {
        templateUrl: 'favoritos/favoritos.html',
        controller: 'FavoritosCtrl'
    });
    }])

.controller('FavoritosCtrl', ['$scope', '$http', '$q', 'loginService', 'favoritosService', function ($scope, $http, $q, loginService, favoritosService) {

    $scope.favoritos = [];
    $scope.cardapio = [];


    function getCardapioDia(id) {
        favoritosService.getCardapioDia($scope.favoritos[id].codbar)
            .success(function (data) {
                $scope.favoritos[id].cardapio = data;
                console.log($scope.favoritos[id].cardapio);
            })
            .error(function (error) {
                console.log(error.message);
            });
    }

    function getFavoritos() {
        favoritosService.getFavoritos(loginService.getId())
            .success(function (data) {
                $scope.favoritos = data;
                console.log($scope.favoritos);
                angular.forEach($scope.favoritos, function (value, key) {
                    getCardapioDia(key);

                });

            })
            .error(function (error) {
                console.log(error.message);
            });
    }

    getFavoritos();


  }]).service("favoritosService", function ($http, $q, nomeBanco) {

    this.getFavoritos = function (id) {
        return $http.get(nomeBanco.getLink() + 'clientes/bares/favoritos/' + id);
    }

    this.getCardapioDia = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/cardapiododia/' + id);

    }

});