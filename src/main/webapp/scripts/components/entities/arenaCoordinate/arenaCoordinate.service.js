'use strict';

angular.module('gungungunApp')
    .factory('ArenaCoordinate', function ($resource) {
        return $resource('api/arenaCoordinates/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
