'use strict';

angular.module('myApp.mensagens', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/mensagens', {
        templateUrl: 'mensagens/mensagens.html',
        controller: 'MensagensCtrl'
    });
    }])

    .controller('MensagensCtrl', ['$scope', '$http', '$q', 'loginService', 'mensagensService', function ($scope, $http, $q, loginService, mensagensService) {

        
        $scope.page = '/mensagens';
  
        

        function getMensagens() {
            mensagensService.getMensagens(loginService.getId())
                .success(function (data) {
                $scope.mensagens = data;
                console.log($scope.mensagens = data);
                readMensagens();
            })
                .error(function (error) {
                console.log(error.message);
            });
        }
        
        function readMensagens() {
            mensagensService.readMensagens({id : loginService.getId()})
            .success(function (data) {
            if(data){
                console.log("sucesso");
            }
        })
            .error(function (error) {
            console.log(error.message);
        });
    }
        
        getMensagens();
    

    }]).service("mensagensService", function ($http, $q, nomeBanco) {

    this.getMensagens = function (id) {
        return $http.get(nomeBanco.getLink() + 'clientes/mensagens/' + id);
    }
    this.readMensagens = function (id) {
        return $http.post(nomeBanco.getLink() + 'clientes/mensagens/lidas' , id);
    }
});