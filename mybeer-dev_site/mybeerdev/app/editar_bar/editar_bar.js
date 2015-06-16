'use strict';

angular.module('myApp.editar_bar', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/editar_bar', {
        templateUrl: 'editar_bar/editar_bar.html',
        controller: 'editarBarController'
    });
    }])

    .controller('editarBarController', ['$scope','$http','editarBarService','toaster','nomeBanco','loginService', function ($scope,$http,editarBarService, toaster,nomeBanco,loginService) {


        
        function getUser() {
            editarBarService.getDadosUser(loginService.getId())
                .success(function (data) {
                $scope.usuario = data;
                console.log($scope.usuario);
                if($scope.usuario.codbar != null){

                    getBar($scope.usuario.codbar);
                }

            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getUser();

        function getBar(id) {
            editarBarService.getBar(id)
                .success(function (data) {
                $scope.bar = data;
                $scope.bar.mediaqualidade = Math.abs($scope.bar.mediaqualidade);
                console.log($scope.bar);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        $scope.editarBar = function (bar) {

            var res = $http.post(nomeBanco.getLink() + 'bares', bar);
            res.success(function (data, status, headers, config) {

                if(data){
                    toaster.pop('success', "Sucesso", "Bar alterado com sucesso");
                }else{
                    toaster.pop('error', "Erro", "Erro ao salvar os dados, tente novamente.");
                }

                var message = data;

            });
            res.error(function (data, status, headers, config) {

                toaster.pop('error', "Erro Interno", JSON.stringify({
                    data: data
                }));

            });
        }
        
        
    }]).service("editarBarService", function ($http, $q, nomeBanco) {

    this.getBar = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/' + id);
    }
    
    this.getDadosUser = function (id) {
        return $http.get(nomeBanco.getLink() + 'pessoas/' + id);

    }

});