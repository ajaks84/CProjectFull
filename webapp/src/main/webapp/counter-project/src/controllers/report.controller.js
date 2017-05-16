(function() {
  'use strict';

  angular.module('MenuApp')
    .controller('ReportController', ReportController);


  ReportController.$inject = ['DataService', '$stateParams', '$scope', '$location'];

  function ReportController(DataService, $stateParams, $scope, $location) {
    var $ctrl = this;
    if ($stateParams.params == null) {
      $location.path("/login/")
    } else {
      var MyDate = new Date();

      $scope.dateOptions = {
        dateFormat: "yy-mm-dd",
      };


      // $ctrl.params = $stateParams.params;
      $ctrl.search = false;
      $ctrl.path = $location.path();
      $ctrl.factory = $stateParams.params.factory;
      $ctrl.country = $stateParams.params.country;
      $ctrl.line = $stateParams.params.line;

       // $ctrl.params.factory = $ctrl.factory;
       // $ctrl.params.country = $ctrl.country;
       // $ctrl.params.line = $ctrl.line;
      console.log($ctrl);
      $ctrl.curDayDate = formatDate(MyDate, 0);
      $ctrl.prvDayDate = formatDate(MyDate, -1);
      // $ctrl.hardCodedDate = '2017-03-16';
      //$ctrl.currentShifts = DataService.getShifts(curDayDate);
      //console.log($ctrl.shifts);

      var promise = DataService.getShifts($ctrl.line.id, $ctrl.curDayDate);
      promise.then(function(response) {
        $ctrl.currentShifts = response;
        //console.log($ctrl.currentShifts) 
      });

      var promise = DataService.getShifts($ctrl.line.id, $ctrl.prvDayDate);
      promise.then(function(response) {
        $ctrl.recentShifts = response;
        //console.log($ctrl.recentShifts) 
      });

      var promise = DataService.getProductGroup($ctrl.line.productId);
      promise.then(function(response) {
        $ctrl.product = response;
        // console.log($ctrl.product)
      });
      //$ctrl.shiftsWithDate = setDateForShifts($ctrl.curDayDate,$ctrl.shifts);
      //console.log($ctrl.shiftsWithDate);

      $scope.getDataForReport = function() {
        // console.log($scope.pDate)
        $ctrl.reportDate = formatDate($scope.pDate, 0)
        $ctrl.shift = $scope.shift
        $ctrl.search = true;

        // console.log($ctrl.reportDate)
        // console.log($ctrl.shift)
      };

      function formatDate(date, correction) {
        var formatedDate = date.getFullYear() +
          '-' + ('0' + (date.getMonth() + 1)).slice(-2) +
          '-' + ('0' + (date.getDate() + correction)).slice(-2);
        return formatedDate;
      }

      //   function setDateForShifts(date, shifts) {

      //     var array = $.map(shifts, function(value, index) {
      //     return [value];
      // });

      //         array.forEach(function(current_value) {
      //             current_value.date = date;

      //         });

      //         console.log(array)

      // return array;
      //       }

    }

  }

})();