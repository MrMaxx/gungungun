'use strict';

angular.module('gungungunApp')
    .controller('UserFightsController', function (
        $scope, $interval, Principal, UserFightService) {

        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            $scope.initializeController();
        });

        $scope.totalServerItems = 0;
        $scope.fights = [];

        $scope.initializeController = function(){
            UserFightService.getFights($scope.account.id).then(function(fights){
                angular.forEach(fights, function(fight){
                    // initially a fight has no winner
                    if(fight.winner){
                        fight['processed'] = true;
                        fight['hasWon'] = ($scope.account.id == fight.winner.id);
                    }else{
                        fight['processed'] = false;
                    }
                    fight['participants'] = '';
                    var count = 0;
                    angular.forEach(fight.participatingUser, function(participant, index){
                        if(count != 0){
                            fight['participants'] = ', '+fight['participants'];
                        }
                        if($scope.account.id != participant.id){
                            fight['participants'] = fight['participants']+participant.userName;
                            count = count +1;
                        }
                    });
                });

                $scope.fights = fights;
            });
        };

        $scope.$on('Fight:created', function (event, newVal) {
            $scope.initializeController();
            $interval(function(){
                $scope.initializeController();
            }, 10000, 1);
        });

        $scope.fightsOptions = {
            data: 'fights',
            columnDefs: [
                {
                    field:'hasWon',
                    displayName:'state',
                    width:50,
                    cellTemplate: '' +
                        '<div class="ngCellText" ng-class="col.colIndex()" style="text-align: center;">' +
                        '   <span ng-cell-text>' +
                        '       <span style="{{row.getProperty(\'processed\') && row.getProperty(\'hasWon\') ?\'\':\'display:none;\'}}color:green;" class="glyphicon glyphicon-plus"></span>' +
                        '       <span style="{{row.getProperty(\'processed\') && !row.getProperty(\'hasWon\')?\'\':\'display:none;\'}};color:red;" class="glyphicon glyphicon-minus"></span>' +
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
                {field:'participants', displayName:'Participants'},
                {field:'arena.arenaKey', displayName:'Arena'},
                {
                    displayName:'',
                    width:50,
                    cellTemplate:'' +
                        '<div style="padding-left:15px;padding-top:4px;">' +
                        '   <a style="{{row.getProperty(\'processed\')?\'\':\'display:none;\'}};padding-left:0px;" ui-sref="game({fightId:{{row.getProperty(\'id\')}}})">' +
                        '       <span aria-hidden="true" class="glyphicon glyphicon-play"></span>' +
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
