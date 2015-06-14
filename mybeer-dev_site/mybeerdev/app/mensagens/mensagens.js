'use strict';

angular.module('myApp.mensagens', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/mensagens', {
        templateUrl: 'mensagens/mensagens.html',
        controller: 'MensagensCtrl'
    });
    }])

    .controller('MensagensCtrl', ['$scope', '$http', '$q', 'loginService', 'mensagensService', function ($scope, $http, $q, loginService, favoritosService) {

  
    function getMensagens() {
        favoritosService.getMensagens(loginService.getId())
            .success(function (data) {
            $scope.mensagens = data;
            console.log($scope.mensagens = data);
        })
            .error(function (error) {
            console.log(error.message);
        });
    }
        
        getMensagens();
    

    }]).service("mensagensService", function ($http, $q) {

    this.getMensagens = function (id) {
        return $http.get('http://frkey.noip.me:3636/br.unicamp/rest/clientes/bares/mensagens/' + id);

    }
});