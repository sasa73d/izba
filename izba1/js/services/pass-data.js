angular.module('HeadModule')
.service('passData', function() {
    var users;
    var data = {
        id: 0,
        userId: undefined,
        albumId: 0,
        albumTitle: 'quidem molestiae enim'
    };
    return {
        getUsers: function() {
            return users;
        },
        setUsers: function(value) {
            users = value;
        },
        getUserId: function() {
            return data.userId;
        },
        setUserId: function(value) {
            data.userId = value;
        },
        getAlbumId: function() {
            return data.albumId;
        },
        setAlbumId: function(value) {
            data.albumId = value;
        },
        getAlbumTitle: function() {
            return data.albumTitle;
        },
        setAlbumTitle: function(value) {
            data.albumTitle = value;
        }
    };
})