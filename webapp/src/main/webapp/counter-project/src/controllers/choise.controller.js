(function () {
'use strict';

angular.module('MenuApp')
.controller('ChoiseController', ChoiseController);


ChoiseController.$inject = ['lineName','shifts','$scope'];
function ChoiseController(lineName,shifts,$scope) {
  var chCtrl = this;

  $scope.dateOptions = {
    dateFormat: "yy-mm-dd"    
    };

  chCtrl.lineName = lineName;
  chCtrl.shifts = shifts;
  

  var MyDate = new Date();

  var MyDateString = ('0' + MyDate.getDate()).slice(-2) + '/'
             + ('0' + (MyDate.getMonth()+1)).slice(-2) + '/'
             + MyDate.getFullYear();

             console.log(MyDateString)

  

}

})();
