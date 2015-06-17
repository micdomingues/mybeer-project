'use strict';

angular.module('myApp.avaliacoes', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/avaliacoes', {
        templateUrl: 'avaliacoes/avaliacoes.html',
        controller: 'AvaliacoesCtrl'
    });
    }])

    .controller('AvaliacoesCtrl', ['$scope','$http','avaliacoesService','toaster','loginService','nomeBanco', function ($scope,$http,avaliacoesService, toaster,loginService, nomeBanco) {

        $scope.page = '/avaliacoes';
        
        function getAvaliacoes() {
            avaliacoesService.getAvaliacoes(loginService.getId())
                .success(function (data) {
                $scope.avaliacoes = data;
                console.log(data);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getAvaliacoes();


    }]).service("avaliacoesService", function ($http, $q,nomeBanco) {
  
    this.getAvaliacoes = function (id) {
        return $http.get(nomeBanco.getLink() + 'clientes/avaliacoes/' + id);
    }

});