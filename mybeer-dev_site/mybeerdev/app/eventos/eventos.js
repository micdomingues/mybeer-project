'use strict';

angular.module('myApp.eventos', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/eventos', {
        templateUrl: 'eventos/eventos.html',
        controller: 'eventoController'
    });
    }])

    .controller('eventoController', ['$scope','$http','eventoService','toaster', function ($scope,$http,eventoService, toaster) {


    $scope.eventos = [];
    $scope.evento = {};
    $scope.alerts = [];
        
        //hora
        $scope.mytime = new Date();
        $scope.ismeridian = false;

    $scope.conversorDate = function (data) {

        if (data != null) {
            var d = angular.copy(data);
            var dMon = d.getMonth();
            var dDay = d.getDate();
            var dYear = d.getFullYear();
            var dHour = $scope.mytime.getHours();
            var dMinute = $scope.mytime.getMinutes();
            var date = dDay + "/" + dMon + "/" + dYear + " " + dHour +":"+ dMinute;

            return date;
        }
        return data;
    }

    $scope.limparForm = function () {
        $scope.evento = {};
    }
    
    $scope.sendEvento = function (evento) {

        var res = $http.put('http://default-environment-fnmmqcmuin.elasticbeanstalk.com/rest/eventos', evento);
        res.success(function (data, status, headers, config) {

            $scope.limparForm();
            
           toaster.pop('success', "Sucesso", "Evento adicionado com sucesso");
        
            var message = data;
        });
        res.error(function (data, status, headers, config) {

           
            toaster.pop('error', "Erro Interno", JSON.stringify({data:data}));


        });
        
        $scope.getEventos();
    }

    $scope.criarEventos = function(){
        
        $scope.evento.data = $scope.conversorDate($scope.evento.data);
        var evento = angular.copy($scope.evento);
        //PEGAR CODIGO DO BAR
        evento.codbar = 1;
        $scope.sendEvento(evento);
    }
    
    var mesesString = ["JAN","FEV","MAR","ABR","MAI","JUN","JUL","AGO","SET","OUT","NOV","DEZ"];
    
        $scope.getEventos = function(){
            var promise = eventoService.getEventos();
            promise.then(function (data) {
                $scope.eventos = data.data.evento;  
                angular.forEach($scope.eventos, function(value, key) {
                    value.dia = value.data.substring(0,2);
                    value.mes = value.data.substring(3,5);
                    value.mesString = mesesString[Number(value.mes)-1];
                    value.ano = value.data.substring(6,10);
                });
            })
        }
 

        $scope.getEventos();

    }]).service("eventoService", function ($http, $q) {
    var deferred = $q.defer();
    $http.get('http://default-environment-fnmmqcmuin.elasticbeanstalk.com/rest/eventos').then(function (data) {
        deferred.resolve(data);
    });

    this.getEventos = function () {
        return deferred.promise;
    }


});