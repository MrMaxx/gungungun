'use strict';

angular.module('gungungunApp')
    .factory('Behavior', function ($resource) {
        return $resource('api//users/:userId/parties/:id/heroes/:heroId/behaviors/:id', {}, {
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
