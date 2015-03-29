app.controller('perfilController', function ($scope) {

    $scope.userName = "meuNome";
    $scope.userAge = "userAge";
    $scope.userEmail = "userEmail";
    $scope.userNickName = "userNickName";
    $scope.userCity = "userCity";
    $scope.userDrinks = "userDrinks";
    $scope.userFood = "userFood";
    $scope.userStatus = "userStatus";

    $scope.user = {
        age: "MyAge",
        email: "MyEmail",
        nickName: "MyNickName",
        city: "MyCity",
        drinks: "MyDrinks",
        food: "MyFood",
        status: "MyStatus",
        image: "https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=100"
    }
});