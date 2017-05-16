(function () {
"use strict";

angular.module('MenuApp')
.component('lines', {
  templateUrl: 'src/templates/lines.template.html',
  bindings: {
    line: '<',
    country: '<',
    factory: '<'
  }
});

})();
