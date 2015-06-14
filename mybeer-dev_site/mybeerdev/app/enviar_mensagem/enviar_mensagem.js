'use strict';

angular.module('myApp.enviar_mensagem', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/enviar_mensagem', {
        templateUrl: 'enviar_mensagem/enviar_mensagem.html',
        controller: 'EnviarMensagemCtrl'
    });
    }])

    .controller('EnviarMensagemCtrl', ['$scope', '$http', '$q', 'loginService', 'mensagemService', function ($scope, $http, $q, loginService, mensagemService) {

  
        
    

    }]).service("mensagemService", function ($http, $q, nomeBanco) {

    this.getenviar_mensagem = function (id) {
        return $http.get(nomeBanco.getLink() + 'clientes/enviar_mensagem/' + id);

    }
});