'use strict';

angular.module('myApp.lancamentos', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/lancamentos', {
        templateUrl: 'lancamentos/lancamentos.html',
        controller: 'lancamentoController'
    });
    }])

    .controller('lancamentoController', ['$scope','$http','lancamentoService','toaster','nomeBanco','loginService', function ($scope,$http,lancamentoService, toaster,nomeBanco,loginService) {


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

        var res = $http.put(nomeBanco.getLink() + 'lancamentos', lancamento);
        res.success(function (data, status, headers, config) {

            $scope.limparForm();
            
            toaster.pop('success', "Sucesso", "Lançamento adicionado com sucesso");

            var message = data;
        });
        res.error(function (data, status, headers, config) {

            
            toaster.pop('error', "Erro Interno", JSON.stringify({data:data}));

        });
        
        $scope.getLancamentos();
    }

    $scope.criarlancamentos = function(){
        
        $scope.lancamento.data = $scope.conversorDate($scope.lancamento.data);
        var lancamento = angular.copy($scope.lancamento);
        //PEGAR CODIGO DO BAR
        lancamento.idfuncionario = loginService.getId();
        $scope.sendlancamento(lancamento);
    }
    
    
        $scope.getLancamentos = function(){
            var promise = lancamentoService.getLancamentos(loginService.getId());
            promise.then(function (data) {
                $scope.lancamentos = data.data.lancamento;  
              
            })
        }
 

        $scope.getLancamentos();

    }]).service("lancamentoService", function ($http, $q, nomeBanco) {

    this.getLancamentos = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/lancamentos/' + id);

    }
});