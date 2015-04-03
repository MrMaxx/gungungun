'use strict';

angular
    .module('gungungunApp')
    .factory('UserFightService',
            function ($http, $q, $log) {

        function createFight(attackingUserId, defendingUserId) {
            var deferred = $q.defer();

            $log.debug('FightService: Creating Fight with User '+defendingUserId);
            $http({
                method: 'POST',
                url: '/api/fights',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: $.param({
                    attackingUserId:attackingUserId,
                    defendingUserId:defendingUserId
                })
            }).then(function(response){
                deferred.resolve(response.data);
            });

            return deferred.promise;
        }
        function getFights(activeUserId) {
            var deferred = $q.defer();

            $log.debug('FightService: getting Fights');
            $http({
                method: 'GET',
                url: '/api/users/'+activeUserId+'/fights'
            }).then(function(response){
                deferred.resolve(response.data);
            });

            return deferred.promise;
        }
        function getFight(fightId) {
            var deferred = $q.defer();

            $log.debug('FightService: getting Fight with id:'+fightId);
            $http({
                method: 'GET',
                url: '/api/fights/'+fightId
            }).then(function(response){
                deferred.resolve(response.data);
            });

            return deferred.promise;
        }
        return {
            getFights: getFights,
            getFight: getFight,
            createFight:createFight
        }
    });