'use strict';

angular.module('myApp.estatisticas', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/estatisticas', {
            templateUrl: 'estatisticas/estatisticas.html',
            controller: 'View1Ctrl'
        });
    }])

    .controller('View1Ctrl', [function() {

    }]);