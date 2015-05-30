'use strict';

angular.module('myApp.promocoes', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/promocoes', {
        templateUrl: 'promocoes/promocoes.html',
        controller: 'PromocoesController'
    });
    }])

    .controller('PromocoesController', ['$scope','$http','promocoesService','toaster', function ($scope,$http,promocoesService, toaster) {

        
    $scope.promocoes = [];
    $scope.promocao = {};
    $scope.alerts = [];

    $scope.conversorDate = function (data) {

        if (data != null) {


            var d = angular.copy(data);
            var dMon = d.getMonth();
            var dDay = d.getDate();
            var dYear = d.getFullYear();
            var date = dDay + "/" + dMon + "/" + dYear;

            return date;
        }
        return data;
    }

    $scope.limparForm = function () {
        $scope.promocao = {};
    }
    
    $scope.sendPromocao = function (promocao) {
        console.log("Ola");
        var res = $http.post('http://frkey.noip.me:3636/br.unicamp/rest/cardapio/insereCardapio', promocao);
        res.success(function (data, status, headers, config) {

            $scope.limparForm();

             toaster.pop('success', "Sucesso", "Cardápio adicionado com sucesso");

            var message = data;
        });
        res.error(function (data, status, headers, config) {
                       
        toaster.pop('error', "Erro Interno", JSON.stringify({data:data}));

        });
    }

    $scope.criarPromocoes = function(){
        
        $scope.promocoes.data = $scope.conversorDate($scope.promocoes.data);
        var promocoes = angular.copy($scope.promocoes);
        console.log($scope.promocoes);
        $scope.sendPromocao(promocoes);
    }
    
    
    var promise = promocoesService.getPromocoes();
        promise.then(function (data) {
            $scope.promocoes = data.data;            
            $scope.promocoes.dia = $scope.promocoes.data.getDate();
            $scope.promocoes.mes = $scope.promocoes.data.getMonth();
            $scope.promocoes.ano = $scope.promocoes.data.getFullYear();
            console.log($scope.promocoes);
        })

    

    }]).service("promocoesService", function ($http, $q) {
    var deferred = $q.defer();
    $http.get('hhttp://frkey.noip.me:3636/br.unicamp/rest/promocoes').then(function (data) {
        deferred.resolve(data);
    });

    this.getPromocoes = function () {
        return deferred.promise;
    }


});