'use strict';

angular.module('myApp.editar_bar', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/editar_bar', {
        templateUrl: 'editar_bar/editar_bar.html',
        controller: 'editarBarController'
    });
    }])

    .controller('editarBarController', ['$scope','$http','editarBarService','toaster','nomeBanco','loginService', function ($scope,$http,editarBarService, toaster,nomeBanco,loginService) {


        $scope.bar = {}
        $scope.bar.nomefantasia = "ola";
        
        $scope.editarBar = function(){
            
        }
       
    }]).service("editarBarService", function ($http, $q, nomeBanco) {

    this.getEditarBar = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/editar_bar/' + id);

    }
});