'use strict';

angular.module('myApp.perfil', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/perfil', {
            templateUrl: 'perfil/perfil.html',
            controller: 'perfil'
        });
    }])

    .controller('perfil', ["$scope",function($scope) {

        $scope.userName = "Fernanda";
        $scope.userAge = "Idade";
        $scope.userEmail = "Email";
        $scope.userNickName = "Apelido";
        $scope.userCity = "Cidade";
        $scope.userDrinks = "Drinks Favorito";
        $scope.userFood = "Comida Favorita";
        $scope.userStatus = "Status Relacionamento";

        $scope.user = {
            age: "23 anos",
            email: "fernanda@gmail.com",
            nickName: "Fer",
            city: "São Paulo",
            drinks: "Margarita",
            food: "Empanadinho ao molho especial",
            status: "Solteiro",
            image: "http://not1.xpg.uol.com.br/wp-content/uploads/2011/01/corte-de-cabelo-rosto-redondo.bmp"
        }
        
    }]);