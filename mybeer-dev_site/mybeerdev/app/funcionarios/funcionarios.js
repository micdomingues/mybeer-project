'use strict';

angular.module('myApp.funcionarios', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/funcionarios', {
        templateUrl: 'funcionarios/funcionarios.html',
        controller: 'funcionarioController'
    });
    }])

    .controller('funcionarioController', ['$scope','$http','funcionarioService', function ($scope,$http,funcionarioService) {


        $scope.funcionarios = [];
        $scope.funcionario = {};
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
        $scope.funcionario = {};
    }
    
    $scope.sendfuncionario = function (funcionario) {

        var res = $http.post('http://default-environment-fnmmqcmuin.elasticbeanstalk.com/rest/funcionario/inserefuncionario', funcionario);
        res.success(function (data, status, headers, config) {

            $scope.limparForm();
            
            $scope.alerts.push({
                type: 'success',
                msg: 'funcionario adicionado com sucesso'
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
        
        $scope.getfuncionarios();
    }

    $scope.criarfuncionarios = function(){
        
        $scope.funcionario.data = $scope.conversorDate($scope.funcionario.data);
        var funcionario = angular.copy($scope.funcionario);
        //PEGAR CODIGO DO BAR
        funcionario.codbar = 1;
        $scope.sendfuncionario(funcionario);
    }
    
    var mesesString = ["JAN","FEV","MAR","ABR","MAI","JUN","JUL","AGO","SET","OUT","NOV","DEZ"];
    
        $scope.getfuncionarios = function(){
            var promise = funcionarioService.getfuncionarios();
            promise.then(function (data) {
                $scope.funcionarios = data.data.funcionario;  
                angular.forEach($scope.funcionarios, function(value, key) {
                    value.dia = value.data.substring(0,2);
                    value.mes = value.data.substring(3,5);
                    value.mesString = mesesString[Number(value.mes)-1];
                    value.ano = value.data.substring(6,10);
                });
            })
        }
 

        $scope.getfuncionarios();

    }]).service("funcionarioService", function ($http, $q) {
    var deferred = $q.defer();
    $http.get('http://default-environment-fnmmqcmuin.elasticbeanstalk.com/rest/funcionario').then(function (data) {
        deferred.resolve(data);
    });

    this.getfuncionarios = function () {
        return deferred.promise;
    }


});