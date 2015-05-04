'use strict';

angular.module('myApp.eventos', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/eventos', {
        templateUrl: 'eventos/eventos.html',
        controller: 'eventoController'
    });
    }])

    .controller('eventoController', ['$scope','$http','eventoService', function ($scope,$http,eventoService) {


    $scope.eventos = [];
    $scope.evento = {};
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

        var res = $http.post('http://frkey.noip.me:3636/br.unicamp/rest/cardapio/insereCardapio', evento);
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
        
        $scope.evento.data = $scope.conversorDate($scope.evento.data);
        var evento = angular.copy($scope.evento);
        console.log($scope.evento);
        $scope.sendEvento(evento);
    }
    
    
    var promise = eventoService.getEventos();
        promise.then(function (data) {
            $scope.eventos = data.data;            
            $scope.eventos.dia = $scope.eventos.data.getDate();
            $scope.eventos.mes = $scope.eventos.data.getMonth();
            $scope.eventos.ano = $scope.eventos.data.getFullYear();
            console.log($scope.eventos);
        })

    

    }]).service("eventoService", function ($http, $q) {
    var deferred = $q.defer();
    $http.get('http://http://localhost:8080/br.unicamp/rest/eventos/listarTodos').then(function (data) {
        deferred.resolve(data);
    });

    this.getEventos = function () {
        return deferred.promise;
    }


});