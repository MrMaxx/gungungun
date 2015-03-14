'use strict';

angular.module('gungungunApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


