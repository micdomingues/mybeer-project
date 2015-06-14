'use strict';

angular.module('myApp.recomendados', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/recomendados', {
        templateUrl: 'recomendados/recomendados.html',
        controller: 'RecomendadosCtrl'
    });
    }])

    .controller('RecomendadosCtrl', ['$scope', '$http', '$q', 'loginService', 'recomendadosService', function ($scope, $http, $q, loginService, favoritosService) {

  
    function getRecomendados() {
        favoritosService.getRecomendados(loginService.getId())
            .success(function (data) {
            $scope.recomendados = data;
            console.log($scope.recomendados = data);
        })
            .error(function (error) {
            console.log(error.message);
        });
    }
        
        getRecomendados();
    

    }]).service("recomendadosService", function ($http, $q) {

    this.getRecomendados = function (id) {
        return $http.get('http://frkey.noip.me:3636/br.unicamp/rest/clientes/bares/recomendados/' + id);

    }
});