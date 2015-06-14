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
  
    function getFavoritos() {
        favoritosService.getFavoritos(loginService.getId())
            .success(function (data) {
            $scope.favoritos = data;
            console.log($scope.favoritos);

        })
            .error(function (error) {
            console.log(error.message);
        });
    }
        
        getFavoritos();
        

  }]).service("favoritosService", function ($http, $q,nomeBanco) {

    this.getFavoritos = function (id) {
        return $http.get(nomeBanco.getLink() + 'clientes/bares/favoritos/' + id);
    }
    

});