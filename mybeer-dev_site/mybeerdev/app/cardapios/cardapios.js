'use strict';

angular.module('myApp.cardapios', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/cardapios', {
            templateUrl: 'cardapios/cardapios.html',
            controller: 'CardapioController'
        });
}])
    .controller('CardapioController', ['$scope', '$http', 'myService', function ($scope, $http, myService) {

        $scope.getData = function () {
            // Call the async method and then do stuff with what is returned inside our own then function
            myService.async().then(function (d) {
                $scope.data = d;
                console.log(d);
            });
        };
        
        $scope.getData();

        $scope.cardapio = {};
        
        
        $scope.cardapio.dtInicio;
        $scope.cardapio.dtFim;
        
        $scope.cardapio.checkboxSemanal = {
            segunda: false,
            terca: false,
            quarta: false,
            quinta: false,
            sexta: false,
            sabado: false,
            domingo: false
        }
        
        
        $scope.cardapio.tipoDataCardapio = {
            name: 'intervaloDataSemanal',
            value: 0
        };
        
    }])
.factory('myService', function ($http) {
    var promise;
    var myService = {
        async: function () {
            if (!promise) {
                // $http returns a promise, which has a then function, which also returns a promise
                promise = $http.get('http://echo.jsontest.com/key/value/one/two').then(function (response) {
                    // The then function here is an opportunity to modify the response
                    // The return value gets picked up by the then in the controller.
                    return response.data;
                });
            }
            // Return the promise to the controller
            return promise;
        }
    };
    return myService;
})
.controller('DatepickerDemoCtrl', function ($scope) {
    
$scope.today = function() {
    $scope.dt = new Date();
};
$scope.today();

$scope.clear = function () {
    $scope.dt = null;
};


$scope.toggleMin = function() {
    $scope.minDate = $scope.minDate ? null : new Date();
};
$scope.toggleMin();

$scope.open = function($event) {
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
    
});
