app.controller('HomeCtrl', function ($rootScope, $location) {
    $rootScope.activetab = $location.path();
});

app.controller('SobreCtrl', function ($rootScope, $location) {
    $rootScope.activetab = $location.path();
});

app.controller('ContatoCtrl', function ($rootScope, $location) {
    $rootScope.activetab = $location.path();
});


app.controller('RatingCtrl', function ($scope) {
    $scope.rate = 3;
    $scope.max = 5;
    $scope.isReadonly = true;

    $scope.hoveringOver = function (value) {
        $scope.overStar = value;
    };

});

app.controller('AccordionDemoCtrl', function ($scope) {
    $scope.isOpen = [true, false, false, false];
    $scope.open = function (index) {
        $scope.isOpen[index] = true;
    }

    $scope.oneAtATime = true;

    $scope.groups = [
        {
            title: 'Favoritados',
            content: 'Grafico dos favoritados'
        },
        {
            title: 'Avaliações da Qualidade',
            content: 'Graficos da Qualidade'
        },
        {
            title: 'Avaliações de Preço',
            content: 'Graficos do Preço'
        }
    ];

    $scope.items = ['Item 1', 'Item 2', 'Item 3'];

    $scope.addItem = function () {
        var newItemNo = $scope.items.length + 1;
        $scope.items.push('Item ' + newItemNo);
    };

    $scope.status = {
        isFirstOpen: true,
        isFirstDisabled: false
    };

    if ($scope.isOpen[0]) {
        setTimeout(function () {
            Morris.Donut({
                element: 'gResumo',
                data: [
                    {
                        value: 70,
                        label: 'Ótimo'
                    },
                    {
                        value: 15,
                        label: 'Bom'
                    },
                    {
                        value: 10,
                        label: 'Razoável'
                    },
                    {
                        value: 5,
                        label: 'Ruim'
                    }
                ],
                backgroundColor: '#ccc',
                labelColor: '#060',
                colors: [
                    '#0BA462',
                    '#39B580',
                    '#67C69D',
                    '#95D7BB'
                ],
                formatter: function (x) {
                    return x + "%"
                }
            });


        }, 100);

    } else if ($scope.isOpen[1]) {
        setTimeout(function () {
            Morris.Bar({
                element: 'gFavoritados',
                data: [
                    {
                        x: '2011 Q1',
                        y: 0
                    },
                    {
                        x: '2011 Q2',
                        y: 1
                    },
                    {
                        x: '2011 Q3',
                        y: 2
                    },
                    {
                        x: '2011 Q4',
                        y: 3
                    },
                    {
                        x: '2012 Q1',
                        y: 4
                    },
                    {
                        x: '2012 Q2',
                        y: 5
                    },
                    {
                        x: '2012 Q3',
                        y: 6
                    },
                    {
                        x: '2012 Q4',
                        y: 7
                    },
                    {
                        x: '2013 Q1',
                        y: 8
                    }
            ],
                xkey: 'x',
                ykeys: ['y'],
                labels: ['Y'],
                barColors: function (row, series, type) {
                    if (type === 'bar') {
                        var red = Math.ceil(255 * row.y / this.ymax);
                        return 'rgb(' + red + ',0,0)';
                    } else {
                        return '#000';
                    }
                }
            });
        }, 100);

    };



});

app.controller('PageController', function ($scope) {
    $scope.page = 0;

    $scope.setPage = function (newValue) {
        $scope.page = newValue;
    }

    $scope.isSet = function (value) {
        if (value === $scope.page) {
            return true;
        }

        return false;
    }
});