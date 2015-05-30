'use strict';

angular.module('myApp.cardapios', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/cardapios', {
            templateUrl: 'cardapios/cardapios.html',
            controller: 'CardapioController'
        });
}])
    .controller('CardapioController', ['$scope', '$http', 'cardapioService','toaster', function ($scope, $http, cardapioService, toaster) {
        
        if ($scope.cardapios == null) {
            $scope.cardapios = [];
        }

        var promise = cardapioService.getCardapios();
        promise.then(function (data) {
            $scope.cardapios = data.data;
            console.log($scope.cardapios);
        })


        $scope.cardapio = {};
        $scope.alerts = [];
        $scope.cardapio.datainicio;
        $scope.cardapio.datafim;

        $scope.checkboxSemanal = {
            segunda: false,
            terca: false,
            quarta: false,
            quinta: false,
            sexta: false,
            sabado: false,
            domingo: false
        }


        $scope.tipoDataCardapio = {
            name: 'intervaloDataSemanal',
            value: 0
        };


        $scope.criarCardapio = function () {

            $scope.cardapio.datainicio = $scope.conversorDate($scope.cardapio.datainicio);
            $scope.cardapio.datafim = $scope.conversorDate($scope.cardapio.datafim);


            $scope.cardapio.idfuncionario = 1;
            console.log($scope.cardapio);

            var cardapio = angular.copy($scope.cardapio);


            $scope.sendCardapio(cardapio);

        }

        $scope.sendCardapio = function (cardapio) {

            var res = $http.post('http://frkey.noip.me:3636/br.unicamp/rest/cardapio/insereCardapio', cardapio);
            res.success(function (data, status, headers, config) {

                $scope.limparForm();

                toaster.pop('success', "Sucesso", "Cardápio adicionado com sucesso");

                var message = data;

                // $scope.cardapios = cardapioService.getCardapios();
            });
            res.error(function (data, status, headers, config) {

                toaster.pop('error', "Erro Interno", JSON.stringify({data:data}));

            });
        }

        $scope.limparForm = function () {
            $scope.cardapio = {};
        }

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

    }])
    .controller('DatepickerDemoCtrl', function ($scope) {

        $scope.today = function () {
            $scope.dt = new Date();
        };
        $scope.today();

        $scope.clear = function () {
            $scope.dt = null;
        };


        $scope.toggleMin = function () {
            $scope.minDate = $scope.minDate ? null : new Date();
        };
        $scope.toggleMin();

        $scope.open = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };

        $scope.formats = ['dd/MMMM/yyyy', 'yyyy/MM/dd', 'dd/MM/yyyy', 'shortDate'];
        $scope.format = $scope.formats[2];

    }).service("cardapioService", function ($http, $q) {
        var deferred = $q.defer();
    $http.get('http://tomcat-unicampft.rhcloud.com/br.unicamp/rest/cardapio').then(function (data) {
            deferred.resolve(data);
        });

        this.getCardapios = function () {
            return deferred.promise;
        }




    });