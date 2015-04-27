'use strict';

angular.module('myApp.estatisticas', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/estatisticas', {
            templateUrl: 'estatisticas/estatisticas.html',
            controller: 'EstatisticasController'
        });
    }])

    .controller('EstatisticasController', ['$scope',function($scope) {

      
       
    }]);