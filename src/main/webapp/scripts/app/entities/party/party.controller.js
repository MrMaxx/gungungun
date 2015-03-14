'use strict';

angular.module('gungungunApp')
    .controller('PartyController', function ($scope, Party, User, Hero) {
        $scope.partys = [];
        $scope.users = User.query();
        $scope.heros = Hero.query();
        $scope.loadAll = function() {
            Party.query(function(result) {
               $scope.partys = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Party.update($scope.party,
                function () {
                    $scope.loadAll();
                    $('#savePartyModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Party.get({id: id}, function(result) {
                $scope.party = result;
                $('#savePartyModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Party.get({id: id}, function(result) {
                $scope.party = result;
                $('#deletePartyConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Party.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deletePartyConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.party = {name: null, id: null};
        };
    });
