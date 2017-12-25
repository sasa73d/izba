angular.module('HeadModule')
.controller('UserCtrl', ['$scope','$http', 'passData', function($scope, $http, passData) {
    var urlConection = 'http://localhost:8080/first-rest/webapi/users';
    $http.get(urlConection).then(function(response) {
        $scope.users = response.data;
        passData.setUsers($scope.users);
        log(users);
    });
    $scope.passUser = function() {
        if ($scope.selected == null) {
            passData.setUserId(undefined);
        } else {
            passData.setUserId($scope.selected.id);
        }
    }
    $scope.setActive = function(item) {
        angular.element(document.querySelectorAll('.active')).removeClass('active');
        angular.element(document.querySelector( '#' + item.currentTarget.getAttribute('id'))).addClass('active');
    }
}])