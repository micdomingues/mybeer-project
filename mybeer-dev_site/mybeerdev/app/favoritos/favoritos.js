'use strict';

angular.module('myApp.favoritos', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/favoritos', {
        templateUrl: 'favoritos/favoritos.html',
        controller: 'FavoritosCtrl'
    });
    }])

    .controller('FavoritosCtrl', ['$scope', '$http', '$q', 'loginService', 'favoritosService', function ($scope, $http, $q, loginService, favoritosService) {

  
    function getFavoritos() {
        favoritosService.getFavoritos(loginService.getId())
            .success(function (data) {
            $scope.favoritos = data;
            console.log($scope.favoritos = data);
        })
            .error(function (error) {
            console.log(error.message);
        });
    }
        
        getFavoritos();
    

  }]).service("favoritosService", function ($http, $q) {

    this.getFavoritos = function (id) {
        return $http.get('http://frkey.noip.me:3636/br.unicamp/rest/clientes/bares/favoritos/' + id);

    }
});