    angular.module('app', ['ui.bootstrap']).controller('RatingCtrl', function ($scope) {
  $scope.rate = 3;
  $scope.max = 5;
  $scope.isReadonly = true;

  $scope.hoveringOver = function(value) {
    $scope.overStar = value;
  };


});