'use strict';

angular.module('myApp.promocoes', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/promocoes', {
        templateUrl: 'promocoes/promocoes.html',
        controller: 'PromocoesCtrl'
    });
    }])

    .controller('PromocoesCtrl', ['$scope','$http','toaster','nomeBanco','promocaoClienteService','loginService', function ($scope,$http, toaster,nomeBanco,promocaoClienteService,loginService) {

        $scope.page = '/promocoes';
        
        
        function getPromocoesBar(id) {
            promocaoClienteService.getPromocoesBar(id)
                .success(function (data) {
                $scope.promocoes = data;
                console.log($scope.promocoes);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        
        function getUser() {
            promocaoClienteService.getDadosUser(loginService.getId())
                .success(function (data) {
                $scope.usuario = data;
                console.log($scope.usuario);
                if($scope.usuario.codbar != null){

                    getPromocoesBar($scope.usuario.codbar);

                }

            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getUser();

        
        
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
    
    this.getPromocoesBar = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/promocoes/' + id);

    }
    

    this.getDadosUser = function (id) {
        return $http.get(nomeBanco.getLink() + 'pessoas/' + id);

    }
});