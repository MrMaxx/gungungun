'use strict';

angular.module('gungungunApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
