'use strict';

angular.module('myApp.dashboard', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/dashboard', {
        templateUrl: 'dashboard/dashboard.html',
        controller: 'DashboardCtrl'
    });
    }])

    .controller('DashboardCtrl', ['$scope','$http','$q', 'loginService','dashboardService', function ($scope, $http , $q,  loginService, dashboardService) {
        
        console.log("ueh foi");
        
    $scope.rate = 3;
    $scope.x = 2;
    $scope.max = 5;
    $scope.isReadonly = true;
    $scope.hoveringOver = function (value) {
        $scope.overStar = value;
    };
    
        // get usuarios
        
        function getUser() {
            dashboardService.getDadosUser(loginService.getId())
                .success(function (data) {
                $scope.usuario = data;
                console.log($scope.usuario);
                
                //getBaresFavoritos
                function getBaresFavoritos() {
                    dashboardService.getBaresFavoritos(loginService.getId())
                        .success(function (data) {
                        $scope.favoritos = data;
                        console.log($scope.favoritos);
                        console.log("acima eh fav");
                    })
                        .error(function (error) {
                        console.log(error.message);
                    });
                }

                getBaresFavoritos();


                //getMensagens
                function getMensagens() {
                    dashboardService.getMensagens(loginService.getId())
                        .success(function (data) {
                        $scope.mensagens = data;
                        console.log($scope.mensagens);
                    })
                        .error(function (error) {
                        console.log(error.message);
                    });
                }

                getMensagens();


                function getPromocoes() {
                    dashboardService.getPromocoes($scope.usuario.codbar)
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
                    dashboardService.getEventos($scope.usuario.codbar)
                        .success(function (data) {
                        $scope.eventos = data;
                        console.log("evento" + $scope.eventos);
                    })
                        .error(function (error) {
                        console.log(error.message);
                    });
                }

                function getEventosCliente() {
                    dashboardService.getEventosCliente(loginService.getId())
                        .success(function (data) {
                        $scope.eventos = data;
                        console.log("evento" + $scope.eventos);
                    })
                        .error(function (error) {
                        console.log(error.message);
                    });
                }
                
                if($scope.usuario.codbar != null){
                    getEventos();    
                }
                else{
                    getEventosCliente();
                }
                


                function getBar() {
                    dashboardService.getBar($scope.usuario.codbar)
                        .success(function (data) {
                        $scope.bar = data;
                        console.log($scope.bar);
                    })
                        .error(function (error) {
                        console.log(error.message);
                    });
                }

                getBar();
                
                function getCardapioDia() {
                    dashboardService.getCardapioDia($scope.usuario.codbar)
                        .success(function (data) {
                        $scope.cardapio = data;
                        console.log($scope.cardapio);
                    })
                        .error(function (error) {
                        console.log(error.message);
                    });
                }

                getCardapioDia();


                function getPromocoesCliente() {
                    dashboardService.getPromocoesCliente($scope.usuario.id)
                        .success(function (data) {
                        $scope.promocoesCliente = data;
                        console.log($scope.promocoesCliente);
                    })
                        .error(function (error) {
                        console.log(error.message);
                    });
                }

                getPromocoesCliente();

                function getLancamentos() {
                    dashboardService.getLancamentos($scope.usuario.id)
                        .success(function (data) {
                        $scope.lancamentos = data;
                        console.log($scope.lancamentos);
                    })
                        .error(function (error) {
                        console.log(error.message);
                    });
                }

                getLancamentos();
                
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getUser();
        
        
    }]).service("dashboardService", function ($http, $q,nomeBanco) {

    this.getPromocoes = function (id) {
        console.log(id);
        return $http.get(nomeBanco.getLink() + 'bares/promocoes/' + id);

    }
    
    this.getPromocoesCliente = function (id) {
        console.log(id);
        return $http.get(nomeBanco.getLink() + 'clientes/promocoes/favoritos/' + id);

    }

    this.getEventos = function (id) {
        console.log(id);
        return $http.get(nomeBanco.getLink() + 'bares/eventos/' + id);

    } 
    this.getEventosCliente = function (id) {
        console.log(id);
        return $http.get(nomeBanco.getLink() + 'clientes/eventos/favoritos/' + id);

    }
    
    this.getMensagens = function (id) {
        return $http.get(nomeBanco.getLink() + 'clientes/mensagens/' + id);

    }
    this.getDadosUser = function (id) {
        return $http.get(nomeBanco.getLink() + 'pessoas/' + id);

    }
    
    this.getBaresFavoritos = function (id) {
        return $http.get(nomeBanco.getLink() + 'clientes/bares/favoritos/' + id);

    }
    
    this.getBar = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/' + id);

    }
    
    this.getLancamentos = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/lancamentos/' + id);

    } 
    this.getCardapioDia = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/cardapiododia/' + id);

    }
    
}).filter('novo', function() {
    return function(input) {
        var stringNovo = "";
       if(input != true){
           stringNovo = "novo"
       }
        return stringNovo;
    };
});