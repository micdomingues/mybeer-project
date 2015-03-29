var app = angular.module('app',['ngRoute', 'ui.bootstrap']);

app.config(function($routeProvider, $locationProvider)
           {
    // remove o # da url
    $locationProvider.html5Mode(true);

    $routeProvider

    // para a rota '/', carregaremos o template home.html e o controller 'HomeCtrl'
        .when('/', {
        templateUrl : 'app/views/dashboard.html'
    })
    
    .when('/dashboard',{
        templateUrl : 'app/views/dashboard.html'
    })

    // para a rota '/sobre', carregaremos o template sobre.html e o controller 'SobreCtrl'
        .when('/perfil', {
        templateUrl : 'app/views/perfil.html',
        controller : 'perfilController'
    })

    // para a rota '/contato', carregaremos o template contato.html e o controller 'ContatoCtrl'
        .when('/contato', {
        templateUrl : 'app/views/contato.html'
    })
    
        .when('/estatisticas', {
        templateUrl : 'app/views/estatisticas.html'
    })

        .when('/cardapio', {
        templateUrl : 'app/views/cardapios.html'
    })


    // caso não seja nenhum desses, redirecione para a rota '/'
        .otherwise ({ redirectTo: '/' });
});