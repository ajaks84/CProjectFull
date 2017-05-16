(function() {
  'use strict';

  angular.module('MenuApp')
    .config(RoutesConfig);

  RoutesConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

  function RoutesConfig($stateProvider, $urlRouterProvider) {

    // Redirect to countries page if no other URL matches
    $urlRouterProvider.otherwise('/login');

    // *** Set up UI states ***
    $stateProvider

      .state('login', {
      url: '/login',
      templateUrl: 'src/modules/authentification/templates/login.template.html',
      controller: 'LoginController',
      controllerAs: 'loginCtrl'
    })

    .state('error', {
      url: '/error',
      templateUrl: 'src/modules/authentification/templates/error.template.html'
    })

    .state('countries', {
      url: '/countries',
      templateUrl: 'src/templates/pick.country.template.html',
      controller: 'CountryController',
      controllerAs: 'countriesCtrl',
      // resolve: {
      //   countries: ['DataService', function(DataService) {
      //     return DataService.getCountries();
      //   }]
      // }
    })

    .state('editor', {
      url: '/editor',
      templateUrl: 'src/templates/editor.template.html',
      controller: 'EditorController',
      controllerAs: 'editorCtrl',
      params: {
        params: null,
        path: undefined,
        data: null
      }
    })

    .state('factories', {
      url: '/factories',  // !!!!!careful with this  url: '/factories/{countryId}' it might broke editor behaviour
      templateUrl: 'src/templates/pick.factory.template.html',
      controller: 'FactoryController as factCtrl',
      params: {
        params: null
      }
      // resolve: {
      //   factories: ['$stateParams', 'DataService', function($stateParams, DataService) {
      //     return DataService.getFactoriesByCountry($stateParams.countryId);
      //   }]
      // }

    })

    .state('lines', {
      url: '/lines',
      templateUrl: 'src/templates/pick.line.template.html',
      controller: 'LinesController as linesCtrl',
      params: {
        params: null
      }

    })

    .state('reports', {
      url: '/reports',
      templateUrl: 'src/templates/pick.report.template.html',
      controller: 'ReportController as rptCtrl',
      params: {
        params: null
      },
      // resolve:{
      //    shifts: ['DataService', function (DataService) {
      //               return DataService.getShifts();
      //           }]
      //         }
    })

    .state('report', {
      url: '/report',
      templateUrl: 'src/templates/report.template.html',
      params: {
        params: null
      },
      controller: 'EditReportController as eRptCtrl',
      // resolve: {
      //   PreviousState: [
      //     "$state",
      //     function($state) {
      //       var currentStateData = {
      //         Name: $state.current.name,
      //         Params: $state.params,
      //         URL: $state.href($state.current.name, $state.params)
      //       };
      //       //console.log(currentStateData);
      //       return currentStateData;
      //     }
      //   ]
      // }

    });

  }

})();