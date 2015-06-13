'use strict';

angular.module('myApp.dashboard', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/dashboard', {
        templateUrl: 'favoritos/favoritos.html',
        controller: 'FavoritosCtrl'
    });
    }])

    .controller('FavoritosCtrl', ['$scope','$http','$q', 'loginService', function ($scope, $http , $q,  loginService) {
    

    }]);