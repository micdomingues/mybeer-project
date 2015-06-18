'use strict';

angular.module('myApp.bares', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/bar/:id', {
        templateUrl: 'bares/bares.html',
        controller: 'BarCtrl'
    });
    }])

    .controller('BarCtrl', ['$scope','$http','$q', 'loginService','baresServicePage','$routeParams','$modal',function ($scope, $http , $q,  loginService, baresServicePage,$routeParams,$modal) {
        
        console.log($routeParams.id);
        
  
        function getBar() {
            baresServicePage.getBar($routeParams.id)
                .success(function (data) {
                $scope.bar = data;
                $scope.bar.mediaqualidade = Math.abs($scope.bar.mediaqualidade);
                console.log($scope.bar);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getBar();
        
        function getPromocoes() {
            baresServicePage.getPromocoes($routeParams.id)
                .success(function (data) {
                $scope.promocoes = data;
                console.log($scope.promocoes);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getPromocoes();
        
        function getEventos() {
            baresServicePage.getEventos($routeParams.id)
                .success(function (data) {
                $scope.eventos = data;
                console.log($scope.eventos);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getEventos();
        
        function getAvaliacoes() {
            baresServicePage.getAvaliacoes($routeParams.id)
                .success(function (data) {
                $scope.avaliacoes = data;
                console.log($scope.avaliacoes);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getAvaliacoes();
        
        function getCardapioDia() {
            baresServicePage.getCardapioDia($routeParams.id)
                .success(function (data) {
                $scope.cardapio = data;
                console.log($scope.cardapio);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getCardapioDia();
        
        $scope.openModal = function (evento,id) {
            var modalInstance = $modal.open({
                animation: true,
                templateUrl: id,
                controller: 'ModalInstanceCtrl',
                size: 'lg',
                resolve: {
                    items: function () {
                        return evento;
                    }
                }
            });
        }
        
    }]).service("baresServicePage", function ($http, $q,nomeBanco) {
    
    this.getBar = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/' + id);

    }
    
    this.getPromocoes = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/promocoes/gerais/' + id);

    }
    
    this.getEventos = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/eventos/' + id);

    }
    
    this.getAvaliacoes = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/avaliacoes/' + id);

    }
    
    this.getCardapioDia = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/cardapiododia/' + id);

    }
    
}).controller('ModalInstanceCtrl', function ($scope, $modalInstance,items) {

    $scope.data = items;
    console.log(items);
    $scope.ok = function () {
        $modalInstance.close();
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});