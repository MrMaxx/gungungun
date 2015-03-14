'use strict';

angular.module('gungungunApp')
    .controller('TokenBlueprintDetailController', function ($scope, $stateParams, TokenBlueprint) {
        $scope.tokenBlueprint = {};
        $scope.load = function (id) {
            TokenBlueprint.get({id: id}, function(result) {
              $scope.tokenBlueprint = result;
            });
        };
        $scope.load($stateParams.id);
    });
