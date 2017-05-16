(function () {
"use strict";

angular.module('MenuApp')
.component('consumption', {
  templateUrl: 'src/templates/consumption.template.html',
  bindings: {
    consumption: '<',
  }
});

})();