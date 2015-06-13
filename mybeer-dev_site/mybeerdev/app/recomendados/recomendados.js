'use strict';

angular.module('myApp.dashboard', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/dashboard', {
        templateUrl: 'dashboard/dashboard.html',
        controller: 'DashboardCtrl'
    });
    }])

    .controller('DashboardCtrl', ['$scope','$http','$q', 'loginService', function ($scope, $http , $q,  loginService) {
    $scope.rate = 3;
    $scope.max = 5;
    $scope.isReadonly = true;
//    $scope.isCliente = null;
//    $scope.usuario = {};
//    var promise = {};

    $scope.hoveringOver = function (value) {
        $scope.overStar = value;
    };
    

//    $scope.getUsuario = function () {
//        var id = loginService.getId();
//        if (id != null) {
//            var deferred = $q.defer();
//            $http.get('http://default-environment-fnmmqcmuin.elasticbeanstalk.com/rest/pessoas/' + id).then(function (data) {
//                deferred.resolve(data);
//            });
//            
//            return deferred.promise;
//        }
//    }
//    
//    var promise = $scope.getUsuario();
//        promise.then(function (data) {
//            $scope.usuario = data.data;
//            
//            if($scope.usuario.tipo === 'C'){
//                $scope.isCliente = true;
//            }else{
//                $scope.isCliente = false;
//            }
//        });
        
    }]);