'use strict';

angular.module('gungungunApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('myparty', {
                parent: 'site',
                url: '/myparty',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/party/party.html',
                        controller: 'MainController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('party');
                        return $translate.refresh();
                    }]
                }
            });
    });
