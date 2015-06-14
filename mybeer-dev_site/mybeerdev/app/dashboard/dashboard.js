'use strict';

angular.module('myApp.dashboard', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/dashboard', {
        templateUrl: 'dashboard/dashboard.html',
        controller: 'DashboardCtrl'
    });
    }])

    .controller('DashboardCtrl', ['$scope','$http','$q', 'loginService','eventosService', function ($scope, $http , $q,  loginService, eventosService) {
        
        console.log("ueh foi");
        
    $scope.rate = 3;
    $scope.x = 2;
    $scope.max = 5;
    $scope.isReadonly = true;
    $scope.hoveringOver = function (value) {
        $scope.overStar = value;
    };
    
        
        
        $scope.eventos = null;
        
        function getEventos() {
            eventosService.getEventos()
                .success(function (data) {
                $scope.eventos = data;
                console.log($scope.eventos);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getEventos();
        console.log("ueh foi");
        
    }]).service("eventosService", function ($http, $q,nomeBanco) {

    this.getEventos = function () {
        return $http.get(nomeBanco.getLink() + 'eventos');

    }
});