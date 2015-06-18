'use strict';

// Declare app level module which depends on views, and components
var PageApp = angular.module('myApp', [
  'ngRoute',
  'myApp.dashboard',
  'myApp.perfil',
  'myApp.estatisticas',
  'myApp.cardapios',
  'myApp.eventos',
  'myApp.login',
  'myApp.promocoes',
  'myApp.lancamentos',
  'myApp.funcionarios',
  'myApp.avaliacoes',
  'myApp.favoritos',
  'myApp.mensagens',
  'myApp.enviar_mensagem',
  'myApp.bares',
  'myApp.search',
  'myApp.editar_bar',
  'myApp.cadastrar_funcionario',
  'myApp.recomendados',
  'myApp.version',
  'toaster',
  'fxpicklist',
  'ui.bootstrap'
]).
config(['$routeProvider', function ($routeProvider) {
    $routeProvider.otherwise({
        redirectTo: '/dashboard'
    });
}]);

PageApp.controller('PageController', function ($location,$scope, $q, $http, loginService,nomeBanco) {
    $scope.search = null;
    //    $scope.isCliente = null;
    $scope.usuario = {};
    //    var promise = {};
    $scope.idUser = loginService.getId();
    
    
    $scope.setPage = function (newValue) {
        $scope.page = newValue;
    }

    $scope.searchBar = function(bar){
        location.href= "#/search/" + bar;
    }
    
    $scope.isSet = function (value) {
        if (value === $location.path()) {
            return true;
        }

        return false;
    }
    
    $scope.setPage($location.path());

    $scope.rate = 3;
    $scope.max = 5;
    $scope.isReadonly = true;

    $scope.hoveringOver = function (value) {
        $scope.overStar = value;
    };

    $scope.islooged = function () {
        $scope.idUser = loginService.getId();
        return loginService.islooged();
    }

    $scope.logout = function () {
        $scope.isCliente = null;
        loginService.logout();

    }


    $scope.getUsuario = function () {
        var id = loginService.getId();
        var deferred = $q.defer();
        if (id != null) {
            $http.get(nomeBanco.getLink() + 'pessoas/' + id).then(function (data) {
                deferred.resolve(data);
            });

            return deferred.promise;
        }
        return deferred.promise;
    }


    $scope.verificaUsuario = function () {
        if ($scope.isCliente === null || $scope.isCliente === undefined) {
            var promise = $scope.getUsuario();
            promise.then(function (data) {
                $scope.usuario = data.data;
                if ($scope.usuario.tipo === 'C') {
                    $scope.isCliente = true;
                } else {
                    $scope.isCliente = false;
                }
            });
        }
        return true;
    }

    $scope.verificaUsuario();

    $scope.$watch('idUser', function (newValue, oldValue) {
        $scope.verificaUsuario();
    }, true);

}).controller('RatingCtrl', function ($scope) {
    $scope.rate = 3;
    $scope.max = 5;
    $scope.isReadonly = true;

    $scope.hoveringOver = function (value) {
        $scope.overStar = value;
    };


}).filter('nl2br', ['$sce', function ($sce) {
    return function (text) {
        return text ? $sce.trustAsHtml(text.replace(/\n/g, '<br/>')) : '';
    };
}]);

PageApp.factory('sessionService', ['$http', function ($http) {
    return {
        set: function (key, value) {
            return sessionStorage.setItem(key, value);
        },
        get: function (key) {
            return sessionStorage.getItem(key);
        },
        destroy: function (key) {
            return sessionStorage.removeItem(key);
        }
    }

}]);

PageApp.factory('loginService', function ($http, $location, sessionService) {
    return {
        login: function (data) {
            sessionService.set('user', data.id);
            $location.path('/dashboard');

        },
        logout: function () {
            sessionService.destroy('user');
            $location.path('/login');
        },
        islooged: function () {
            if (sessionService.get('user')) {
                return true;
            }
        },
        getId: function () {
            return sessionService.get('user');
        }
    }
});

PageApp.run(function ($rootScope, $location, loginService) {
    var routespermission = ['/dashboard', '/perfil', '/cardapios', '/estatisticas', '/eventos', '/avaliacoes'];
    $rootScope.$on('$routeChangeStart', function () {
        if (routespermission.indexOf($location.path()) != -1 && !loginService.islooged()) {
            $location.path('/login');
        }
    });
}).service('nomeBanco',function(){
    this.getLink = function(){
        //http://default-environment-fnmmqcmuin.elasticbeanstalk.com/rest/
//        "http://frkey.noip.me:3636/br.unicamp/rest/";
        return "http://frkey.noip.me:3636/br.unicamp/rest/";
    }
});