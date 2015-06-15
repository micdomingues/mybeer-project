'use strict';

angular.module('myApp.cadastrar_funcionario', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/cadastrar_funcionario', {
        templateUrl: 'cadastrar_funcionario/cadastrar_funcionario.html',
        controller: 'cadastrarFuncionarioCtrl'
    });
    }])

    .controller('cadastrarFuncionarioCtrl', ['$scope','$http','cadastrarFuncionarioService','toaster','nomeBanco','loginService', function ($scope,$http,cadastrarFuncionarioService, toaster,nomeBanco,loginService) {


   
    }]).service("cadastrarFuncionarioService", function ($http, $q, nomeBanco) {

    this.getcadastrar_funcionario = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/cadastrar_funcionario/' + id);

    }
});