'use strict';

angular.module('myApp.eventos', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/eventos', {
        templateUrl: 'eventos/eventos.html',
        controller: 'eventoController'
    });
    }])

.controller('eventoController', ['$scope', '$http', 'eventoService', 'toaster', 'nomeBanco', 'loginService', function ($scope, $http, eventoService, toaster, nomeBanco, loginService) {


    $scope.eventos = [];
    $scope.evento = {};
    $scope.alerts = [];
    $scope.page = '/eventos';
    //hora
    $scope.mytime = new Date();
    $scope.ismeridian = false;

    $scope.conversorDate = function (data) {

        if (data != null) {
            var d = angular.copy(data);
            var dMon = d.getMonth() + 1;
            var dDay = d.getDate();
            var dYear = d.getFullYear();
            var dHour = $scope.mytime.getHours();
            var dMinute = $scope.mytime.getMinutes();
            var date = dDay + "/" + dMon + "/" + dYear + " " + dHour + ":" + dMinute;

            return date;
        }
        return data;
    }

    $scope.limparForm = function () {
        $scope.evento = {};
    }

    $scope.sendEvento = function (evento) {

        var res = $http.put(nomeBanco.getLink() + 'eventos', evento);
        res.success(function (data, status, headers, config) {

            if(data){
                $scope.limparForm();

                toaster.pop('success', "Sucesso", "Evento adicionado com sucesso");
            }else{
                toaster.pop('error', "Erro", "Evento não adicionado!!");
            }
            

            var message = data;
        });
        res.error(function (data, status, headers, config) {


            toaster.pop('error', "Erro Interno", JSON.stringify({
                data: data
            }));


        });

        $scope.getEventos();
    }

    $scope.criarEventos = function () {

        var evento = angular.copy($scope.evento);
        evento.data = $scope.conversorDate(evento.data);
        
        //PEGAR CODIGO DO BAR
        evento.codbar = $scope.usuario.codbar;
        $scope.sendEvento(evento);
    }

    var mesesString = ["JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"];

   
 
    function getEventosCliente() {
        eventoService.getEventos(loginService.getId())
            .success(function (data) {
                $scope.eventos = data;
                angular.forEach($scope.eventos, function (value, key) {
                    value.dia = value.data.substring(0, 2);
                    value.mes = value.data.substring(3, 5);
                    value.mesString = mesesString[Number(value.mes) - 1];
                    value.ano = value.data.substring(6, 10);
                });
            })
            .error(function (error) {
                console.log(error.message);
            });
    }
    
   

    function getEventosFuncionario() {
        eventoService.getEventos(loginService.getId())
            .success(function (data) {
            $scope.eventos = data;
            angular.forEach($scope.eventos, function (value, key) {
                value.dia = value.data.substring(0, 2);
                value.mes = value.data.substring(3, 5);
                value.mesString = mesesString[Number(value.mes) - 1];
                value.ano = value.data.substring(6, 10);
            });
        })
            .error(function (error) {
            console.log(error.message);
        });
    }

    function getUser() {
        eventoService.getDadosUser(loginService.getId())
            .success(function (data) {
            $scope.usuario = data;
            console.log(data);

            if($scope.usuario.codbar == null){
                getEventosCliente();
            }else{
                getEventosFuncionario();
            }

        })
            .error(function (error) {
            console.log(error.message);
        });
    }

    getUser();

    

    }]).service("eventoService", function ($http, $q, nomeBanco) {


    this.getEventos = function (id) {
        return $http.get(nomeBanco.getLink() + 'clientes/eventos/favoritos/' + id);
    }
    
    this.getDadosUser = function (id) {
        return $http.get(nomeBanco.getLink() + 'pessoas/' + id);

    }
    
    this.getEventos = function (id) {
        console.log(id);
        return $http.get(nomeBanco.getLink() + 'bares/eventos/' + id);

    }

});