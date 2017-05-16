(function () {
"use strict";

angular.module('MenuApp')
.component('shifts', {
  templateUrl: 'src/templates/shifts.template.html',
  bindings: {
   shift: '<',
    name: '<',
    date: '<'
  }
});

})();
