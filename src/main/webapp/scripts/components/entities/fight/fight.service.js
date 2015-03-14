'use strict';

angular.module('gungungunApp')
    .factory('Fight', function ($resource) {
        return $resource('api/fights/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.createdAt = new Date(data.createdAt);
                    data.processedAt = new Date(data.processedAt);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
