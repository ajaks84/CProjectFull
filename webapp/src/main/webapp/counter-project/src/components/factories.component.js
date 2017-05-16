(function () {
"use strict";

angular.module('MenuApp')
.component('factories', {
  templateUrl: 'src/templates/factories.template.html',
  bindings: {
    factory: '<',
    country: '<'
  }
});

})();
