angular.module('HeadModule')
.controller('AlbumsCtrl', ['$scope', '$http', 'passData', function($scope, $http, passData) {
    var urlConection = 'https://jsonplaceholder.typicode.com/albums';
    $http.get(urlConection).then(function(response) {
        if ($scope.albums) {
            delete $scope.albums
        }
        $scope.albums = response.data;
        //angular.element(document.querySelector( '#tr-1')).addClass('focus-row');
    })
    $scope.$watch(function() {return passData.getUserId()}, function(newValue, oldValue) {
        if (newValue !== oldValue) {
            passData.setUserId(newValue);
            $scope.userNameId = passData.getUserId();
        }
    });
    $scope.clickRow = function(item, album) {
        var index = item.currentTarget.getAttribute('id').slice(3);
        passData.setAlbumId(index);
        passData.setAlbumTitle(album[0].title);
        angular.element(document.querySelectorAll('.focus-row')).removeClass('focus-row');
        angular.element(document.querySelector( '#' + item.currentTarget.getAttribute('id'))).addClass('focus-row');
    };
}])