'use strict';

angular.module('myApp.dashboard', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/dashboard', {
            templateUrl: 'dashboard/dashboard.html',
            controller: 'RatingCtrl'
        });
    }])

    .controller('RatingCtrl', [function() {
        $scope.rate = 3;
        $scope.max = 5;
        $scope.isReadonly = true;

        $scope.hoveringOver = function (value) {
            $scope.overStar = value;
        };
    }]);