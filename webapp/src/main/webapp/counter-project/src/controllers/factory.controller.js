(function() {
	"use strict";

	angular.module('MenuApp')
		.controller('FactoryController', FactoryController);

	FactoryController.$inject = ['DataService', '$stateParams', '$location'];

	function FactoryController(DataService, $stateParams, $location) {
		var $ctrl = this;



		// Works with queries don't forget 'factories'
		// $ctrl.factories = factories;
		// console.log($ctrl.factories);


		// Works with params don't forget '$stateParams' and '$location'
		if ($stateParams.params == null) {
			$location.path("/login/")
		} else {
			$ctrl.path = $location.path();
			console.log($stateParams.params);
			$ctrl.country = $stateParams.params.country;
			var promise = DataService.getFactoriesByCountry($ctrl.country.id);
			promise.then(function(response) {
				$ctrl.factories = response;
				//console.log($ctrl.factories);
			});
		}
	}

})();