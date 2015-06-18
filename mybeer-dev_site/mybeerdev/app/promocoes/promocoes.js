'use strict';

angular.module('myApp.promocoes', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/promocoes', {
        templateUrl: 'promocoes/promocoes.html',
        controller: 'PromocoesCtrl'
    });
    }])

    .controller('PromocoesCtrl', ['$scope','$http','toaster','nomeBanco','promocaoClienteService','loginService','$filter', function ($scope,$http, toaster,nomeBanco,promocaoClienteService,loginService,$filter) {

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
        
        $scope.conversorDate = function (data) {
            if (data != null) {
                var d = angular.copy(data);
                var dMon = d.getMonth() + 1 ;
                var dDay = d.getDate();
                var dYear = d.getFullYear();
                var date = dDay + "/" + dMon + "/" + dYear;
                return date;
            }
            return data;
        }

        $scope.criarPromocoes = function(promocaoVinda) {

            var promocao = angular.copy(promocaoVinda);
            console.log(promocao);
            promocao.datainicio = $scope.conversorDate(promocao.datainicio);
            promocao.datafim = $scope.conversorDate(promocao.datafim);
            
            promocao.codbar = $scope.usuario.codbar;
            promocao.idfuncionario = $scope.usuario.id;
            
            
            promocaoClienteService.sendPromocao(promocao)
                .success(function (data) {
                if(data){
                    toaster.pop('success', "Sucesso", "Promoção adicionada com sucesso");
                    if($scope.usuario.codbar != null){
                        getPromocoesBar($scope.usuario.codbar);

                    }else{
                        getPromocoesCliente();
                    }
                    
                    $scope.promocao = {};
                }else{
                    toaster.pop('error', "error", "Verifique se as datas estão corretas");
                }
                
                
            })
                .error(function (error) {
                       toaster.pop('error', "error", "Erro interno");
                console.log(error.message);
            });
        
        };

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
    
    this.sendPromocao = function (data) {
        return $http.put(nomeBanco.getLink() + 'promocoes', data);

    }

}).filter('convertPromocao', function () {
    return function (tipo){
        var nome;
        if (tipo == "G") {
            nome = "Geral";
        } else {
            nome = "Favoritados";
        }
        return nome;
    }
});