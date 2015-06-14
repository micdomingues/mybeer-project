'use strict';

angular.module('myApp.login', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/login', {
            templateUrl: 'login/login.html',
            controller: 'LoginController'
        });
    }])

    .controller('LoginController', ['$scope','loginService','$http','nomeBanco',function($scope,loginService,$http,nomeBanco) {

        $scope.registro = false;
        
        $scope.isUser = function (user) {

            var res = $http.post(nomeBanco.getLink() + 'logins/executeLogin', user);
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
            login.cpf = pessoa.cpf;
                var res = $http.put(nomeBanco.getLink() + 'logins', login);
            res.success(function (data, status, headers, config) {
                var message = data;
                console.log(angular.isNumber(parseInt(data)));
                console.log(parseInt(data));
                if(!isNaN(parseInt(data))){
                    console.log(data);
                        pessoa.tipo = "C";
                        pessoa.id = parseInt(data);
                        //cadastrar pessoa
                        var res = $http.put(nomeBanco.getLink() + 'pessoas', pessoa);
                        res.success(function (data, status, headers, config) {
                            var message = data;
                            if(data){
                                console.log(data);
                                $scope.registro = false;
                                $scope.msg = "Cadastro Realizado com Sucesso";
                                $scope.sucesso = true;
                            }else{
                                $scope.msg = "Dados ja existentes";
                            }
                        });
                        res.error(function (data, status, headers, config) {
                            $scope.msg = "Erro interno. Contate o administrador" + data;

                        });
                  
                    
                }else{
                    $scope.msg = data;
                }
            });
            res.error(function (data, status, headers, config) {
                $scope.msg = "Erro interno. Contate o administrador" + data;

            });
        }

        
       
    }]);