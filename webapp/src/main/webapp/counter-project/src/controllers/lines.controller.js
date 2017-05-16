(function() {
  "use strict";

  angular.module('MenuApp')
    .controller('LinesController', LinesController);

  LinesController.$inject = ['DataService', '$stateParams', '$location'];

  function LinesController(DataService, $stateParams, $location) {
    var $ctrl = this;

    // console.log($stateParams);

    if ($stateParams.params == null) {
      $location.path("/login/")
    } else {
      $ctrl.path = $location.path();
      $ctrl.factory = $stateParams.params.factory;
      $ctrl.country = $stateParams.params.country;
      var promise = DataService.getLinesByFactory($ctrl.factory.id);
      promise.then(function(response) {
        $ctrl.lines = response;
        //console.log($ctrl.lines);
      });
    }
    // console.log($ctrl.factory);
    // console.log($ctrl.country);
  }

})();