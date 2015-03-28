'use strict';

angular.module('gungungunApp')
    .controller('MyPartyController', function ($scope, Principal, TokenBlueprint, Party, Hero) {

        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });

        TokenBlueprint.query(function(tokenBlueprints){
            $scope.tokenBlueprints = tokenBlueprints;
        });

        $scope.heroes = {};
        $scope.tokenBlueprintSelected = {};
        $scope.heuristicSelected = {};
        Principal.identity().then(function(account){
            Party.query({userId:account.id}, function(parties){
                if(parties && parties.length > 0){
                    Hero.query({userId:account.id,partyId: parties[0].id}, function(heroes){

                        angular.forEach(heroes, function(hero, index){
                            $scope.heroes[hero.id] = hero;
                            $scope.tokenBlueprintSelected[hero.id] = hero.tokenBlueprint;

                            angular.forEach(hero.behaviors, function(behavior, index){
                                $scope.heuristicSelected[behavior.id] = behavior;
                            });

                        });

                    });
                }else{
                    Logger.error("User has no parties defined.");
                }
            });
        });

        $scope.changeName = function(id){
            Logger.debug("changing name to "+$scope.heroes[id].name+" for hero with id = "+id);
        };

        $scope.heroes = {
            "1": {
                "id": 1,
                "name": "Hannibal",
                "tokenBlueprint": {
                    "id": 1,
                    "tokenKey": "GRUNT",
                    "health": 100,
                    "actionsPerTurn": 4,
                    "longRangeAttackRange": 10,
                    "longRangeWeaponDamageMinimum": 5,
                    "longRangeWeaponDamageMaximum": 10,
                    "shortRangeAttackRange": 1,
                    "shortRangeWeaponDamageMinimum": 10,
                    "shortRangeWeaponDamageMaximum": 25,
                    "moveRange": 2
                }
            }
        };

        $scope.heuristics = [
            {heuristicName: 'NONE'},
            {heuristicName: 'GOOD_SHOOTING_POSITION'},
            {heuristicName: 'CLOSER_DISTANCE_TO_NEAREST_ENEMY'},
            {heuristicName: 'NEAREST_ENEMY_CLOSE_TO_LONG_RANGE_WEAPON_RANGE'},
            {heuristicName: 'SHOOTING_IS_GOOD'}
        ];


        $scope.behaviorSelected = function(heroId, behaviorId, item, model){
            $scope.heuristicSelected[behaviorId].heuristicName = item.heuristicName;
        };

        $scope.typeSelected = function (id, item, model){
            $scope.tokenBlueprintSelected[id] = item;
        };


    });
