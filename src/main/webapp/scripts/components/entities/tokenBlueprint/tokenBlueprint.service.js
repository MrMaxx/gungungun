'use strict';

angular.module('gungungunApp')
    .factory('TokenBlueprint', function ($resource) {
        return $resource('api/tokenBlueprints/:id', {}, {
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
