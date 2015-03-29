'use strict';

angular.module('gungungunApp')
    .factory('BehaviorUpdateService',
        function ($http, $q) {

            function updateBehavior(behavior, heroId, partyId, userId) {
                var deferred = $q.defer();

                $http({
                    method: 'PUT',
                    url: 'api//users/'+userId+'/parties/'+partyId+'/heroes/'+heroId+'/behaviors',
                    data: behavior
                }).then(function(response){
                    deferred.resolve(response.data);
                });

                return deferred.promise;
            }
            return {
                updateBehavior:updateBehavior
            }
        });