angular.module('HeadModule')
.controller('PhotosCtrl', ['$scope', '$http', 'passData', function($scope, $http, passData) {
    var urlConection = 'https://jsonplaceholder.typicode.com/photos?albumId=';
    $scope.album = passData.getAlbumTitle();
    $http.get(urlConection + '1').then(function (response) {
        /*if ($scope.photos) {
            delete $scope.photos;
        }*/
        $scope.photos = response.data;

    });

    $scope.$watch(function() {return passData.getAlbumId();}, function(newValue, oldValue) {
        if (newValue !== oldValue) {
            passData.setAlbumId(newValue);
            $scope.album = passData.getAlbumTitle();

            $http.get(urlConection + passData.getAlbumId()).then(function(response) {
                if ($scope.photos) {
                    delete $scope.photos;
                }
                $scope.photos = response.data;

            });
        }
    }, true);

}]);