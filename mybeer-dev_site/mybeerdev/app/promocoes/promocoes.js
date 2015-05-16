'use strict';

angular.module('myApp.promocoes', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/promocoes', {
        templateUrl: 'promocoes/promocoes.html',
        controller: 'PromocoesController'
    });
    }])

    .controller('PromocoesController', ['$scope','$http','promocoesService', function ($scope,$http,promocoesService) {


    $scope.promocoes = [];
    $scope.promocoes = {};
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
        $scope.evento = {};
    }
    
    $scope.sendEvento = function (evento) {

        var res = $http.post('http://frkey.noip.me:3636/br.unicamp/rest/cardapio/insereCardapio', promocoes);
        res.success(function (data, status, headers, config) {

            $scope.limparForm();

            $scope.alerts.push({
                type: 'success',
                msg: 'Evento adicionado com sucesso'
            });

            var message = data;
        });
        res.error(function (data, status, headers, config) {

            console.log("ERRO");
            
            $scope.alerts.push({
                type: 'danger',
                msg: 'Erro:' + JSON.stringify({
                    data: data
                })
            });

        });
    }

    $scope.criarEventos = function(){
        
        $scope.promocoes.data = $scope.conversorDate($scope.promocoes.data);
        var promocoes = angular.copy($scope.promocoes);
        console.log($scope.promocoes);
        $scope.sendPromoçao(promocoes);
    }
    
    
    var promise = promocoesService.getEventos();
        promise.then(function (data) {
            $scope.eventos = data.data;            
            $scope.eventos.dia = $scope.eventos.data.getDate();
            $scope.eventos.mes = $scope.eventos.data.getMonth();
            $scope.eventos.ano = $scope.eventos.data.getFullYear();
            console.log($scope.eventos);
        })

    

    }]).service("promocoesService", function ($http, $q) {
    var deferred = $q.defer();
    $http.get('http://http://localhost:8080/br.unicamp/rest/eventos/listarTodos').then(function (data) {
        deferred.resolve(data);
    });

    this.getEventos = function () {
        return deferred.promise;
    }


});