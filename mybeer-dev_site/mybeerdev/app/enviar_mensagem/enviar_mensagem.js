'use strict';

angular.module('myApp.enviar_mensagem', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/enviar_mensagem', {
        templateUrl: 'enviar_mensagem/enviar_mensagem.html',
        controller: 'EnviarMensagemCtrl'
    });
    }])

    .controller('EnviarMensagemCtrl', ['$scope', '$http', '$q', 'loginService', 'mensagemService','toaster', function ($scope, $http, $q, loginService, mensagemService,toaster) {

  
        $scope.page = '/enviar_mensagem';
        $scope.mensagem = {}
        $scope.mensagem.ids = [];
        function getUser() {
            mensagemService.getDadosUser(loginService.getId())
                .success(function (data) {
                $scope.usuario = data;
                console.log($scope.usuario);
                getUsuarios($scope.usuario.codbar)
                getMensagens($scope.usuario.codbar);
               
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getUser();

        
        function getUsuarios(id) {
            mensagemService.listarClientesFavoritos(id)
                .success(function (data) {
                $scope.usuarios = data;
                console.log($scope.usuarios);

            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        
        $scope.enviarMensagem =  function(mensagem) {
            console.log(mensagem);
            mensagem.idfuncionario = $scope.usuario.id;
            mensagemService.sendMensagem(mensagem)
                .success(function (data) {
                if(data){
                    toaster.pop('success', "Sucesso", "Mensagem enviada com sucesso!");
                    $scope.mensagem = {};
                    $scope.mensagem.ids = [];
                }else{
                    toaster.pop('error', "error", "Erro inexperado, tente novamente.");
                }
               
            })
                .error(function (error) {
                toaster.pop('error', "error", "Erro interno");
            });
        }
        
        
        function getMensagens(id){
     
            mensagemService.getMensagens(id)
                .success(function (data) {
                if(data){
                    $scope.mensagens = data;
                    console.log($scope.mensagens);
                }
            })
                .error(function (error) {
                toaster.pop('error', "error", "Erro interno");
            });
        }

        
    }]).service("mensagemService", function ($http, $q, nomeBanco) {

    this.getMensagens = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/mensagens/' + id);

    }
    this.listarClientesFavoritos = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/clientes/favoritos/' + id);

    }
    
    this.getDadosUser = function (id) {
        return $http.get(nomeBanco.getLink() + 'pessoas/' + id);

    }
    
    this.sendMensagem = function (id) {
        return $http.put(nomeBanco.getLink() + 'mensagens', id);

    }
});