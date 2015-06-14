'use strict';

angular.module('myApp.bares', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/bar/:id', {
        templateUrl: 'bares/bares.html',
        controller: 'BarCtrl'
    });
    }])

    .controller('BarCtrl', ['$scope','$http','$q', 'loginService','baresService','$routeParams',function ($scope, $http , $q,  loginService, baresService,$routeParams) {
        
        console.log($routeParams.id);
        
  
        
        
        
        
    }]).service("baresService", function ($http, $q,nomeBanco) {
    
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