(function () {
"use strict";

angular.module('MenuApp')
.component('shiftButton', {
  templateUrl: 'src/templates/shift.button.template.html',
  bindings: {
  	params:'<',
  	date:'<',
  	shiftData:'<',
  	search:'<'
  }
});

})();