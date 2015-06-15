'use strict';

angular.module('myApp.promocoes', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/promocoes', {
        templateUrl: 'promocoes/promocoes.html',
        controller: 'PromocoesCtrl'
    });
    }])

    .controller('PromocoesCtrl', ['$scope','$http','toaster','nomeBanco','promocaoClienteService','loginService', function ($scope,$http, toaster,nomeBanco,promocaoClienteService,loginService) {

        function getPromocoesCliente() {
            promocaoClienteService.getPromocoes(loginService.getId())
                .success(function (data) {
                $scope.promocoes = data;
                console.log($scope.promocoes);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getPromocoesCliente();
        
        
    }]).service("promocaoClienteService", function ($http, $q, nomeBanco) {

    this.getPromocoes = function (id) {
        return $http.get(nomeBanco.getLink() + 'clientes/promocoes/favoritos/' + id);

    }
});