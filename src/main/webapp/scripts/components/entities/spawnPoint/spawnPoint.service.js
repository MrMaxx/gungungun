'use strict';

angular.module('gungungunApp')
    .factory('SpawnPoint', function ($resource) {
        return $resource('api/spawnPoints/:id', {}, {
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
