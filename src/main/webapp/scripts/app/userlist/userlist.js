'use strict';

angular.module('gungungunApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('userlist', {
                parent: 'site',
                url: '/userlist',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/userlist/userlist.html'
                    },
                    'usersInRange@userlist': {
                        templateUrl: 'scripts/app/userlist/userlist.userranking.html',
                        controller: 'UserListController'
                    },
                    'fights@userlist': {
                        templateUrl: 'scripts/app/userlist/userlist.fights.html',
                        controller: 'UserFightsController'
                    }

                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('userlist');
                        return $translate.refresh();
                    }]
                }
            });
    });
