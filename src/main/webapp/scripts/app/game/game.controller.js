'use strict';

angular.module('gungungunApp')
    .controller('GameController', function ($scope, $http, $stateParams, Principal, Fight) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });

        $scope.width = 1000;
        $scope.height = 680;

        $scope.fightId = $stateParams.fightId;

        $scope.$on('KONVA:READY', function kineticReady (event, stage) {

            $http.get('/api/fights/'+$scope.fightId+'/events').
                success(function(data) {
                    var loader = new Konva.Loader(config.assets);
                    loader.onComplete(function(){
                        var game = new GameEngine(data, stage);
                        game.run();
                    });
                    loader.load();
                });

        });

    });
