'use strict';

angular.module('myApp.funcionarios', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/funcionarios', {
        templateUrl: 'funcionarios/funcionarios.html',
        controller: 'funcionarioController'
    });
    }])

.controller('funcionarioController', ['$scope', '$http', 'funcionarioService', 'loginService', 'nomeBanco', 'toaster', function ($scope, $http, funcionarioService, loginService, nomeBanco, toaster) {


    $scope.funcionarios = [];
    $scope.funcionario = {};
    $scope.alerts = [];
    $scope.page = '/funcionarios';

    //hora
    $scope.mytime = new Date();
    $scope.ismeridian = false;

    function getUser() {
        funcionarioService.getDadosUser(loginService.getId())
            .success(function (data) {
                $scope.usuarioLogado = data;
                console.log($scope.usuarioLogado);
                getFuncionarios();
            })
            .error(function (error) {
                console.log(error.message);
            });
    }

    getUser();


    $scope.conversorDate = function (data) {

        if (data != null) {
            var d = angular.copy(data);
            var dMon = d.getMonth();
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
        $scope.funcionario = {};
    }

    $scope.sendfuncionario = function (funcionario) {

        var res = $http.put(nomeBanco.getLink() + 'funcionarios', funcionario);
        res.success(function (data, status, headers, config) {

            if (data) {
                toaster.pop('success', "Sucesso", "Funcionário adicionado com sucesso");
                $scope.limparForm();
            } else {
                toaster.pop('error', "Erro", "O funcionário não pode ser cadastrado");
            }

            var message = data;


        });
        res.error(function (data, status, headers, config) {

            toaster.pop('error', "Erro", "O funcionário não pode ser cadastrado");
        });

    }

    $scope.criarFuncionario = function () {

        var funcionario = angular.copy($scope.funcionario);
        funcionario.codbar = $scope.usuarioLogado.codbar;
        $scope.sendfuncionario(funcionario);
    }

    function getFuncionarios() {
        funcionarioService.getFuncionarios($scope.usuarioLogado.codbar)
            .success(function (data) {
                $scope.funcionarios = data;
                console.log($scope.funcionarios);

            })
            .error(function (error) {
                console.log(error.message);
            });
    }







    }]).service("funcionarioService", function ($http, $q, nomeBanco) {

    this.getDadosUser = function (id) {
        return $http.get(nomeBanco.getLink() + 'pessoas/' + id);

    }


    this.getFuncionarios = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/funcionarios/' + id);

    }



}).filter('convertTipo', function () {
    return function (tipo){
        var nome;
        if (tipo == "G") {
        nome = "Gerente";
        } else {
            nome = "Funcionário";
        }
    return nome;
    }
});