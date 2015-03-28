'use strict';

angular.module('gungungunApp')
    .factory('Hero', function ($resource) {
        return $resource('api//users/:userId/parties/:partyId/heroes/:id', {}, {
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
