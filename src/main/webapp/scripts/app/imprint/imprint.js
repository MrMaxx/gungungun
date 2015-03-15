'use strict';

angular.module('gungungunApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('imprint', {
                parent: 'site',
                url: '/imprint',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/imprint/imprint.html',
                        controller: 'ImprintController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('imprint');
                        return $translate.refresh();
                    }]
                }
            });
    });
