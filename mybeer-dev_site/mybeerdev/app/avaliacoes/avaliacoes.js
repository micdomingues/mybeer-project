'use strict';

angular.module('myApp.avaliacoes', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/avaliacoes', {
        templateUrl: 'avaliacoes/avaliacoes.html',
        controller: 'PromocoesController'
    });
    }])

    .controller('PromocoesController', ['$scope','$http','promocoesService','toaster', function ($scope,$http,promocoesService, toaster) {

        
    

    }]).service("promocoesService", function ($http, $q) {
  


});