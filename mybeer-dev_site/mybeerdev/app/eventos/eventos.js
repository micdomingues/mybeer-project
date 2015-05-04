'use strict';

angular.module('myApp.eventos', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/eventos', {
            templateUrl: 'eventos/eventos.html',
            controller: 'eventoController'
        });
    }])

    .controller('eventoController', ['$scope', function($scope) {
        
        
        $scope.evento = {};
        $scope.alerts = [];
        
        $scope.conversorDate = function(data){

            var d = angular.copy(data);
            var dMon = d.getMonth();
            var dDay = d.getDate();
            var dYear = d.getFullYear();
            var date = dDay +"/" + dMon + "/" + dYear;

            return date;
        }
        
        $scope.limparForm = function(){
            $scope.evento = {};
        }
        
        

        $scope.sendCardapio = function (evento) {

            var res = $http.post('http://frkey.noip.me:3636/br.unicamp/rest/cardapio/insereCardapio', newData);
            res.success(function (data, status, headers, config) {

                $scope.limparForm();

                $scope.alerts.push({
                    type: 'success',
                    msg: 'Evento adicionado com sucesso'
                });

                var message = data;
            });
            res.error(function (data, status, headers, config) {

                $scope.alerts.push({
                    type: 'danger',
                    msg: 'Erro:' + JSON.stringify({
                        data: data
                    })
                });

            });
        }
        

    }]).service("cardapioService", function ($http, $q) {
    var deferred = $q.defer();
    $http.get('http://frkey.noip.me:3636/br.unicamp/rest/cardapio/listarTodos').then(function (data) {
        deferred.resolve(data);
    });

    this.getCardapios = function () {
        return deferred.promise;
    }




});