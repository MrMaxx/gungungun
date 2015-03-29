'use strict';

angular.module('gungungunApp')
    .controller('MyPartyController', function (
        $scope, Principal, TokenBlueprint, Party, Hero, BehaviorUpdateService) {

        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });

        TokenBlueprint.query(function(tokenBlueprints){
            $scope.tokenBlueprints = tokenBlueprints;
        });

        $scope.party = {};
        $scope.heroes = {};
        $scope.behaviors = {};

        $scope.tokenBlueprintSelected = {};
        $scope.heuristicSelected = {};

        $scope.userAccount = {};

        Principal.identity().then(function(account){
            $scope.userAccount = account;
            Party.query({userId:account.id}, function(parties){
                if(parties && parties.length > 0){
                    $scope.party = parties[0];
                    Hero.query({userId:account.id,partyId: parties[0].id}, function(heroes){

                        angular.forEach(heroes, function(hero, index){
                            $scope.heroes[hero.id] = hero;
                            $scope.tokenBlueprintSelected[hero.id] = hero.tokenBlueprint;

                            angular.forEach(hero.behaviors, function(behavior, index){
                                $scope.heuristicSelected[behavior.id] = {heuristicName: behavior.heuristicName};
                                $scope.behaviors[behavior.id] = behavior;
                                $scope.behaviors[behavior.id].heroId = hero.id;
                            });

                        });

                    });
                }else{
                    Logger.error("User has no parties defined.");
                }
            });
        });

        $scope.changeName = function(id){
            $scope.heroes[id].$update({userId:$scope.userAccount.id,partyId: $scope.party.id});
        };

        $scope.heuristics = [
            {heuristicName: 'NONE'},
            {heuristicName: 'GOOD_SHOOTING_POSITION'},
            {heuristicName: 'CLOSER_DISTANCE_TO_NEAREST_ENEMY'},
            {heuristicName: 'CLOSE_TO_LONG_RANGE_WEAPON_RANGE'},
            {heuristicName: 'SHOOTING_IS_GOOD'}
        ];


        $scope.behaviorSelected = function(heroId, behaviorId, item, model){
            $scope.heuristicSelected[behaviorId].heuristicName = item.heuristicName;
            var behavior = $scope.behaviors[behaviorId];
            if(item.heuristicName != behavior.heuristicName){
                behavior.heuristicName = item.heuristicName;
                BehaviorUpdateService.updateBehavior(behavior, heroId, $scope.party.id, $scope.userAccount.id);
            }
        };

        $scope.typeSelected = function (id, item, model){
            $scope.tokenBlueprintSelected[id] = item;
            if($scope.tokenBlueprintSelected[id].id != $scope.heroes[id].tokenBlueprint.id){
                $scope.heroes[id].tokenBlueprint = item;
                $scope.heroes[id].$update({userId:$scope.userAccount.id,partyId: $scope.party.id});
            }
        };


    });
