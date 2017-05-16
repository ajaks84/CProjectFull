(function () {
"use strict";

angular.module('MenuApp')
.component('countries', {
  templateUrl: 'src/templates/countries.template.html',
  bindings: {
    country: '<'
  }
});

})();
