'use strict';

angular
    .module('gungungunApp')
    .service('UserRankingService', function UserService(
        $http, $q) {

        this.getUserPage = function(page, pagesize) {

            var deferred = $q.defer();

            if( !page || !pagesize || page < 1 || pagesize < 1){
                deferred.resolve({});
                return deferred.promise;
            }

            $http({
                method: 'GET',
                url: '/api/users',
                params:{page:page, pagesize:pagesize}
            }).then(function(response){
                return deferred.resolve(response.data);
            });

            return deferred.promise;
        }

        this.getUserRankedPage = function(userId, pagesize) {

            var deferred = $q.defer();

            if( !pagesize || pagesize < 1){return deferred.resolve({});}

            $http({
                method: 'GET',
                url: '/api/users',
                params:{pagesize:pagesize, userId:userId}
            }).then(function(response){
                return deferred.resolve(response.data);
            });

            return deferred.promise;
        }

    });
