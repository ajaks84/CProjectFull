(function () {
'use strict';

angular.module('MenuApp')
.config(RoutesConfig);

RoutesConfig.$inject = ['$stateProvider', '$urlRouterProvider'];
function RoutesConfig($stateProvider, $urlRouterProvider) {

  // Redirect to countries page if no other URL matches
  $urlRouterProvider.otherwise('/countries');

  // *** Set up UI states ***
  $stateProvider

  .state('countries', {
    url: '/countries',
    templateUrl: 'src/templates/pick.country.template.html',
    controller: 'CountryController',
    controllerAs: 'countriesCtrl',
    resolve: {
      countries: ['DataService', function (DataService) {
                return DataService.getCountries();
              }]
         }

  })

  .state('factories', {
    url: '/factories/{countryId}',
    templateUrl: 'src/templates/pick.factory.template.html',
    controller: 'FactoryController as factCtrl',
     resolve: {
      country: ['$stateParams','DataService', function ($stateParams,DataService) {
                return DataService.getCountryById($stateParams.countryId)
              }],
      factories: ['$stateParams','DataService', function ($stateParams,DataService) {
                return DataService.getFactoriesByCountry($stateParams.countryId);
              }]
         }

  })

  .state('lines', {
    url: '/lines/{factoryId}/{countryId}',
    templateUrl: 'src/templates/pick.line.template.html',
    controller: 'LinesController as linesCtrl',
     resolve: {
              country: ['$stateParams','DataService', function ($stateParams,DataService) {
                        //console.log($stateParams.countryId);
                        return DataService.getCountryById($stateParams.countryId)
                      }],

              factory: ['$stateParams','DataService', function ($stateParams,DataService) {
                        //console.log($stateParams.factoryId);
                        return DataService.getFactoryById($stateParams.factoryId);
                      }],


                 lines: ['$stateParams','DataService', function ($stateParams,DataService) {
                        //console.log($stateParams.factoryId);
                        return DataService.getLinesByFactory($stateParams.factoryId);
                      }]
         }

  })

  .state('reports', {
    url: '/reports/{lineId}/{countryId}/{factoryId}',
    templateUrl: 'src/templates/pick.report.template.html',
    controller: 'ReportController as rptCtrl',
     resolve: {
              line: ['$stateParams','DataService', function($stateParams,DataService){
                        console.log($stateParams.lineId);
                      return DataService.getLineById($stateParams.lineId)
                      }],

              country: ['$stateParams','DataService', function ($stateParams,DataService) {
                        console.log($stateParams.countryId);
                        return DataService.getCountryById($stateParams.countryId)
                      }],

              factory: ['$stateParams','DataService', function ($stateParams,DataService) {
                        console.log($stateParams.factoryId);
                        return DataService.getFactoryById($stateParams.factoryId);
                      }]
         }

  })

  .state('report', {
    url: '/report',
    templateUrl: 'src/templates/report.template.html',
    controller: 'EditReportController as rptCtrl',
    params : { lineId: null, 
               reportDate:null,
               shift:null,
               country:null

                }
    //resolve: {
           //  line: ['$stateParams','DataService', function($stateParams,DataService){
           //          console.log($stateParams.lineId);
           //          return DataService.getLineById($stateParams.lineId)
           //        }],
           //  reportDate: ['$stateParams', function($stateParams){       
           //            console.log("date is "+$stateParams.reportDate);
           //            return $stateParams.reportDate
           //          }],
           // shift: ['$stateParams', function($stateParams){
           //            console.log($stateParams.shift);
           //            return $stateParams.shift
           //        }]

         //}

});
 
}

})();
