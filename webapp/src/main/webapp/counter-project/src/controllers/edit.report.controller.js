(function() {
  'use strict';

  angular.module('MenuApp')
    .controller('EditReportController', EditReportController);

  EditReportController.$inject = ['$stateParams', 'DataService', '$location'];

  function EditReportController($stateParams, DataService, $location) {
    var $ctrl = this;
    if ($stateParams.params == null) {
      $location.path("/login/")
    } else {
      $ctrl.path = $location.path();
      $ctrl.reports = new Array();
      $ctrl.search = $stateParams.search;
      if ($stateParams.params.search) {
        console.log($stateParams);
        $ctrl.params = $stateParams.params;
        var promise = DataService.getReportData($ctrl.params.line.id, $ctrl.params.reportDate, $ctrl.params.shift);
        promise.then(function(response) {
          $ctrl.report = response;
          getConsumprionList();
        });
      } else {
        console.log($stateParams);
        // console.log($stateParams.params.search);
        // $ctrl.params = $stateParams.params.params;

        // console.log($ctrl.params)
        // $ctrl.params.country = $stateParams.params.country;
        if($stateParams.params.data == undefined){
        $ctrl.params = $stateParams.params.params;}else{
          $ctrl.params = $stateParams.params.data.params;
          console.log('change me')
        }

        if ($stateParams.params.shiftData != undefined) {
          $ctrl.report = $stateParams.params.shiftData;
        } else {
          $ctrl.report = $stateParams.params.data.report
        }
        //console.log($ctrl.params);
        //console.log($ctrl.params.country.name);
        getConsumprionList();
      }

      function getConsumprionList() {
        var promise = DataService.getConsumprionByReportId($ctrl.report.id);
        promise.then(function(response) {
          $ctrl.consumptionList = response;
        });
        $ctrl.reports.push($ctrl.report);
      }
    }

  }
})();