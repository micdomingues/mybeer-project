'use strict';

angular.module('myApp.search', ['ngRoute'])

.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/search/:id', {
        templateUrl: 'search/search.html',
        controller: 'SearchCtrl'
    });
    }])

    .controller('SearchCtrl', ['$scope','$http','$q', 'loginService','baresService','$routeParams',function ($scope, $http , $q,  loginService, baresService,$routeParams) {
        
        console.log($routeParams.id);
        
        function getAvaliacoes(index,id) {
            baresService.getAvaliacoes(id)
                .success(function (data) {
                $scope.bares[index].avaliacao = data;
                console.log( $scope.bares);
                console.log(index);
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

       
        
        function getBares() {
            baresService.getBares($routeParams.id)
                .success(function (data) {
                $scope.bares = data;
                angular.forEach($scope.bares, function(value, index){
                    getAvaliacoes(index,value.codbar);
                });
                
            })
                .error(function (error) {
                console.log(error.message);
            });
        }

        getBares();
        
       
        
    }]).service("baresService", function ($http, $q,nomeBanco) {
    
    this.getBares = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/search/' + id);

    }
    
    this.getAvaliacoes = function (id) {
        return $http.get(nomeBanco.getLink() + 'bares/avaliacoes/' + id);

    }
   
    
});