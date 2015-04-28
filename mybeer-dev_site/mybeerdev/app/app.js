'use strict';

// Declare app level module which depends on views, and components
var PageApp = angular.module('myApp', [
  'ngRoute',   
  'myApp.dashboard',
  'myApp.perfil',
  'myApp.estatisticas',
  'myApp.cardapios',
  'myApp.eventos',
  'myApp.version',
  'ui.bootstrap'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/dashboard'});
}]);

PageApp.controller('PageController', function ($scope) {
    $scope.page = 0;

    $scope.setPage = function (newValue) {
        $scope.page = newValue;
    }

    $scope.isSet = function (value) {
        if (value === $scope.page) {
            return true;
        }

        return false;
    }

    $scope.rate = 3;
    $scope.max = 5;
    $scope.isReadonly = true;

    $scope.hoveringOver = function (value) {
        $scope.overStar = value;
    };


}).controller('RatingCtrl', function ($scope) {
    $scope.rate = 3;
    $scope.max = 5;
    $scope.isReadonly = true;

    $scope.hoveringOver = function (value) {
        $scope.overStar = value;
    };


}).controller('AccordionDemoCtrl', function ($scope) {
    $scope.oneAtATime = true;

    $scope.groups = [
        {
            title: 'Favoritados',
            content: 'Graficos de Favoritados'
    },
        {
            title: 'Avaliações da Qualidade',
            content: 'Graficos da Qualidade'
    },
        {
            title: 'Avaliações de Preço',
            content: 'Graficos do Preço'
    }
  ];

    $scope.items = ['Item 1', 'Item 2', 'Item 3'];

    $scope.addItem = function () {
        var newItemNo = $scope.items.length + 1;
        $scope.items.push('Item ' + newItemNo);
    };

    $scope.status = {
        isFirstOpen: true,
        isFirstDisabled: false
    };
});