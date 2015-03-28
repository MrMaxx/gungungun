'use strict';

angular.module('gungungunApp')
    .factory('Party', function ($resource, Principal) {
        return $resource('api/users/:userId/parties/:partyId', {partyId: '@id'}, {
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
