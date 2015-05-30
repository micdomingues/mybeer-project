'use strict';

angular.module('myApp.lancamentos', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/lancamentos', {
        templateUrl: 'lancamentos/lancamentos.html',
        controller: 'lancamentoController'
    });
    }])

    .controller('lancamentoController', ['$scope','$http','lancamentoService','toaster', function ($scope,$http,lancamentoService, toaster) {


        $scope.lancamentos = [];
        $scope.lancamento = {};
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
        $scope.lancamento = {};
    }
    
    $scope.sendlancamento = function (lancamento) {

        var res = $http.post('http://frkey.noip.me:3636/br.unicamp/rest/lancamento/inserelancamento', lancamento);
        res.success(function (data, status, headers, config) {

            $scope.limparForm();
            
            toaster.pop('success', "Sucesso", "Lançamento adicionado com sucesso");

            var message = data;
        });
        res.error(function (data, status, headers, config) {

            
            toaster.pop('error', "Erro Interno", JSON.stringify({data:data}));

        });
        
        $scope.getlancamentos();
    }

    $scope.criarlancamentos = function(){
        
        $scope.lancamento.data = $scope.conversorDate($scope.lancamento.data);
        var lancamento = angular.copy($scope.lancamento);
        //PEGAR CODIGO DO BAR
        lancamento.codbar = 1;
        $scope.sendlancamento(lancamento);
    }
    
    var mesesString = ["JAN","FEV","MAR","ABR","MAI","JUN","JUL","AGO","SET","OUT","NOV","DEZ"];
    
        $scope.getlancamentos = function(){
            var promise = lancamentoService.getlancamentos();
            promise.then(function (data) {
                $scope.lancamentos = data.data.lancamento;  
                angular.forEach($scope.lancamentos, function(value, key) {
                    value.dia = value.data.substring(0,2);
                    value.mes = value.data.substring(3,5);
                    value.mesString = mesesString[Number(value.mes)-1];
                    value.ano = value.data.substring(6,10);
                });
            })
        }
 

        $scope.getlancamentos();

    }]).service("lancamentoService", function ($http, $q) {
    var deferred = $q.defer();
    $http.get('http://tomcat-unicampft.rhcloud.com/br.unicamp/rest/lancamento/listarTodos').then(function (data) {
        deferred.resolve(data);
    });

    this.getlancamentos = function () {
        return deferred.promise;
    }


});