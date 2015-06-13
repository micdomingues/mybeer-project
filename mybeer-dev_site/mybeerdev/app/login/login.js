'use strict';

angular.module('myApp.login', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/login', {
            templateUrl: 'login/login.html',
            controller: 'LoginController'
        });
    }])

    .controller('LoginController', ['$scope','loginService','$http',function($scope,loginService,$http) {

        $scope.registro = false;
        
        $scope.isUser = function (user) {

            var res = $http.post('http://default-environment-fnmmqcmuin.elasticbeanstalk.com/rest/logins/executeLogin', user);
            res.success(function (data, status, headers, config) {
                var message = data;
                if(data){
                    loginService.login(data);
                    console.log(data);
                }else{
                    $scope.msg = "Usuário ou senha inválidos";
                }
            });
            res.error(function (data, status, headers, config) {
                $scope.msg = "Erro no login" + data;

            });
        }
        
        
        $scope.user = {};
        var msg='';
        $scope.logar = function (usuario,senha){
            if(usuario != null && senha !=null){
                $scope.isUser($scope.user);           

              
            }else{
                $scope.msg = "Dados não inseridos";
            }
        }
        
      
        
        $scope.cadastrar = function (login,pessoa) {
            if(login.usuario || login)

            var res = $http.post('http://default-environment-fnmmqcmuin.elasticbeanstalk.com/rest/logins', login);
            res.success(function (data, status, headers, config) {
                var message = data;
                if(data){
                    console.log(data);
                    
                    pessoa.tipo = "C";
                    pessoa.id = data.id;
                    //cadastrar pessoa
                    var res = $http.post('http://default-environment-fnmmqcmuin.elasticbeanstalk.com/rest/pessoas', pessoa);
                    res.success(function (data, status, headers, config) {
                        var message = data;
                        if(data){
                            console.log(data);
                        }else{
                            $scope.msg = "Usuário ja existente";
                        }
                    });
                    res.error(function (data, status, headers, config) {
                        $scope.msg = "Erro interno. Contate o administrador" + data;

                    });
                    
                }else{
                    $scope.msg = "Usuário ja existente";
                }
            });
            res.error(function (data, status, headers, config) {
                $scope.msg = "Erro interno. Contate o administrador" + data;

            });
        }

        
       
    }]);