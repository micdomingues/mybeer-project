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

            var res = $http.post('http://frkey.noip.me:3636/br.unicamp/rest/logins/executeLogin', user);
            res.success(function (data, status, headers, config) {
                var message = data;
                console.log(data);
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
        $scope.logar = function (usuario,senha){
            console.log(usuario);
            console.log(senha);
            if(usuario != null && senha !=null){
                $scope.isUser($scope.user);           

              
            }else{
                $scope.msg = "Dados não inseridos";
            }
        }
        
      
        
        
       
    }]);