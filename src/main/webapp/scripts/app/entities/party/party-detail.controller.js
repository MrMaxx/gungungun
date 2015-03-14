'use strict';

angular.module('gungungunApp')
    .controller('PartyDetailController', function ($scope, $stateParams, Party, User, Hero) {
        $scope.party = {};
        $scope.load = function (id) {
            Party.get({id: id}, function(result) {
              $scope.party = result;
            });
        };
        $scope.load($stateParams.id);
    });
