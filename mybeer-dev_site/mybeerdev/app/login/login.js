'use strict';

angular.module('myApp.login', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/login', {
            templateUrl: 'login/login.html',
            controller: 'LoginController'
        });
    }])

    .controller('LoginController', ['$scope','loginService','$http',function($scope,loginService,$http) {

        $scope.isUser = function (user) {

            var res = $http.post('http://localhost:8080/br.unicamp/rest/login/executeLogin', user);
            res.success(function (data, status, headers, config) {
                var message = data;
                if(data){
                    loginService.login(data);
                }else{
                    $scope.msg = "Usuário ou senha inválidos";
                }
            });
            res.error(function (data, status, headers, config) {
                $scope.msg = "erro";

            });
        }
        
        
        $scope.user = {};
        var msg='';
        $scope.logar = function (){
            if($scope.user.usuario != null && $scope.user.senha !=null){
                $scope.isUser($scope.user);           

              
            }else{
                $scope.msg = "Dados não inseridos";
            }
        }
        
      
        
        
       
    }]);