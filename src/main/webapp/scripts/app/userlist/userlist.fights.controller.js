'use strict';

angular.module('gungungunApp')
    .controller('UserFightsController', function (
        $scope, Principal, UserFightService) {

        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            $scope.initializeController();
        });

        $scope.totalServerItems = 0;
        $scope.fights = [];

        $scope.initializeController = function(){
            UserFightService.getFights($scope.account.id).then(function(fights){
                angular.forEach(fights, function(fight, index){
                    fight['hasWon'] = ($scope.account.id == fight.attackingUserId && fight.outcome == 'ATTACKER_WON')
                        || ($scope.account.is == fight.defendingingUserId && fight.outcome == 'DEFENDER_WON');
                });
                $scope.fights = fights;
            });
        };

        $scope.$on('Fight:created', function (event, newVal) {
            $scope.initializeController();
            $interval(function(){
                $scope.initializeController();
            },30000);
        });

        $scope.fightsOptions = {
            data: 'fights',
            columnDefs: [
                {
                    field:'fightState',
                    displayName:'state',
                    width:50,
                    cellTemplate: '' +
                        '<div class="ngCellText" ng-class="col.colIndex()">' +
                        '   <span ng-cell-text>' +
                        '       <i style="{{row.getProperty(\'hasWon\') && row.getProperty(\'outcome\')==\'ATTACKER_WON\'?\'\':\'display:none;\'}};color:green;font-weight:bold;" class="fa fa-plus-square"></i>' +
                        '       <i style="{{!row.getProperty(\'hasWon\') && row.getProperty(\'outcome\')==\'ATTACKER_WON\'?\'\':\'display:none;\'}};color:red;font-weight:bold;" class="fa fa-plus-square"></i>' +
                        '       <i style="{{row.getProperty(\'hasWon\') && row.getProperty(\'outcome\')==\'DEFENDER_WON\'?\'\':\'display:none;\'}};color:green;font-weight:bold;" class="fa fa-minus-square"></i>' +
                        '       <i style="{{!row.getProperty(\'hasWon\') && row.getProperty(\'outcome\')==\'DEFENDER_WON\'?\'\':\'display:none;\'}};color:red;font-weight:bold;" class="fa fa-minus-square"></i>' +
                        '       <i style="{{!row.getProperty(\'outcome\')?\'\':\'display:none;\'}};font-weight:bold;" class="fa fa-spinner fa-spin"></i>' +
                        '   </span>' +
                        '</div>'
                },
                {
                    field:'createdAt',
                    displayName:'Date',
                    width:90,
                    cellTemplate: '' +
                        '<div class="ngCellText" ng-class="col.colIndex()">' +
                        '  <span ng-cell-text>{{row.getProperty(col.field) | date:\'dd.MM HH:mm\'}}</span>' +
                        '</div>'
                },
                {field:'attackingUserName', displayName:'Attacker'},
                {field:'defendingUserName', displayName:'Defender'},
                {
                    displayName:'',
                    width:50,
                    cellTemplate:'' +
                        '<div style="padding-left:15px;padding-top:4px;">' +
                        '   <a target="_blank" href="gameclient/index.html?userId={{account.id}}&fightId={{row.getProperty(\'id\')}}&access_token={{accessToken}}" style="display:{{row.getProperty(\'outcome\')?\'block\':\'none\'}};">' +
                        '       <i style="font-size:20px;font-weight:bold;" class="fa fa-eye"></i>' +
                        '   </a>' +
                        '</div>'
                }

            ],
            enablePaging: false,
            enableSorting: false,
            showFooter: false,
            totalServerItems: 'totalServerItems',
            enableCellSelection: false,
            beforeSelectionChange: function() {
                return false;
            }
        };




    });
