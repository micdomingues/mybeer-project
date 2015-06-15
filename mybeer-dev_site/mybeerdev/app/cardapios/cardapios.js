'use strict';

angular.module('myApp.cardapios', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/cardapios', {
            templateUrl: 'cardapios/cardapios.html',
            controller: 'CardapioController'
        });
}])
    .controller('CardapioController', ['$scope', '$http', 'cardapioService', 'toaster', 'loginService', 'nomeBanco', function ($scope, $http, cardapioService, toaster, loginService, nomeBanco) {

        if ($scope.cardapios == null) {
            $scope.cardapios = [];
        }



        $scope.cardapio = {};
        $scope.alerts = [];
        $scope.cardapio.datainicio;
        $scope.cardapio.datafim;

        $scope.cardapio.semana = {
            segunda: false,
            terca: false,
            quarta: false,
            quinta: false,
            sexta: false,
            sabado: false,
            domingo: false
        }


        $scope.tipoDataCardapio = {
            name: null,
            value: 0
        };
        
        
        function getUser() {
            cardapioService.getDadosUser(loginService.getId())
                .success(function (data) {
                $scope.usuario = data;
                console.log($scope.usuario);
                if($scope.usuario.codbar != null){

                    getCardapios();
                }
                
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getUser();
        
        
        function getCardapios() {
            //PASSAR O ID DO BAR
            cardapioService.getCardapios($scope.usuario.codbar)
                .success(function (data) {
                $scope.cardapios = data;
                console.log($scope.cardapio);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }
        
       
        

        $scope.analisaSemanas = function () {
            var temUmTrue = false;
            angular.forEach($scope.cardapio.semana, function (value, index) {
                if (value == true) {
                    temUmTrue = true;
                }
            })
            return temUmTrue;
        }

        $scope.criarCardapio = function () {


            if ($scope.tipoDataCardapio.name == null) {
                $scope.errorOpcoes = true;
            } else {
                if ($scope.tipoDataCardapio.name == "intervaloDataMes" && $scope.cardapio.datainicio == null) {
                    $scope.errorDataInicio = true;
                } else if ($scope.tipoDataCardapio.name == "intervaloDataMes" && $scope.cardapio.datafim == null) {
                    $scope.errorDataFim = true;
                } else if ($scope.tipoDataCardapio.name == "dataSemanalEspecifica" && !$scope.analisaSemanas()) {
                    $scope.errorSemana = true;
                } else {
                    $scope.cardapio.datainicio = $scope.conversorDate($scope.cardapio.datainicio);
                    $scope.cardapio.datafim = $scope.conversorDate($scope.cardapio.datafim);


                    $scope.cardapio.idfuncionario = loginService.getId();
                    console.log($scope.cardapio);

                    var cardapio = angular.copy($scope.cardapio);


                    $scope.sendCardapio(cardapio);
                }

            }


        }

        $scope.sendCardapio = function (cardapio) {

            var res = $http.put(nomeBanco.getLink() + 'cardapios', cardapio);
            res.success(function (data, status, headers, config) {

                $scope.limparForm();

                toaster.pop('success', "Sucesso", "Cardápio adicionado com sucesso");

                var message = data;

            });
            res.error(function (data, status, headers, config) {

                toaster.pop('error', "Erro Interno", JSON.stringify({
                    data: data
                }));

            });
        }

        $scope.limparForm = function () {
            $scope.cardapio = {};
        }

        $scope.conversorDate = function (data) {
            if (data != null) {
                var d = angular.copy(data);
                var dMon = d.getMonth();
                var dDay = d.getDate();
                var dYear = d.getFullYear();
                var date = dDay + "/" + dMon + "/" + dYear;
                return date;
            }
            return data;
        }

    }])
    .controller('DatepickerDemoCtrl', function ($scope) {

        $scope.today = function () {
            $scope.dt = new Date();
        };
        $scope.today();

        $scope.clear = function () {
            $scope.dt = null;
        };


        $scope.toggleMin = function () {
            $scope.minDate = $scope.minDate ? null : new Date();
        };
        $scope.toggleMin();

        $scope.open = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };

        $scope.formats = ['dd/MMMM/yyyy', 'yyyy/MM/dd', 'dd/MM/yyyy', 'shortDate'];
        $scope.format = $scope.formats[2];

    }).service("cardapioService", function ($http, $q, nomeBanco) {

        this.getCardapios = function (id) {
            return $http.get(nomeBanco.getLink() + 'bares/cardapios/' + id);
        }

        this.getDadosUser = function (id) {
            return $http.get(nomeBanco.getLink() + 'pessoas/' + id);

        }
        
    }).filter('semana', function () {
        return function (input) {
            var string = "";
            if (input != null) {
                if (input.segunda == true) {
                    string = " Segunda";
                }
                if (input.terca == true) {
                    string = string + " Terça";
                }
                if (input.quarta == true) {
                    string = string + " Quarta";
                }
                if (input.quinta == true) {
                    string = string + " Quinta";
                }
                if (input.sexta == true) {
                    string = string + " Sexta";
                }
                if (input.sabado == true) {
                    string = string + " Sábado";
                }
                if (input.domingo == true) {
                    string = string + " Domingo";
                }

            }

            return string;
        };
    })