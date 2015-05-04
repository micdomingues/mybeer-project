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
            image: "http://devilsworkshop.org/files/2013/01/enlarged-facebook-profile-picture.jpg"
        }
        
    }]);