'use strict';

angular.module('myApp.eventos', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/eventos', {
            templateUrl: 'eventos/eventos.html',
            controller: 'View1Ctrl'
        });
    }])

    .controller('View1Ctrl', [function() {

    }]);