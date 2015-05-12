'use strict';

angular.module('myApp.login', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/login', {
            templateUrl: 'login/login.html',
            controller: 'LoginController'
        });
    }])

    .controller('LoginController', ['$scope','loginService',function($scope,loginService) {

        
        $scope.user = {};
        $scope.msg='';
        $scope.logar = function (){
            if($scope.user.usuario != null && $scope.user.senha !=null){
                
                if(!loginService.login($scope.user,$scope.msg)){
                    $scope.msg ="Usuário ou Senha inválido";
                }else{
                }
            }else{
                $scope.msg = "Dados não inseridos";
            }
        }
        
      
       
    }]);