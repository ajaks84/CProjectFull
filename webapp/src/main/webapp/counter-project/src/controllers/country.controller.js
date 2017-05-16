(function() {
	"use strict";

	angular.module('MenuApp')
		.controller('CountryController', CountryController);

	CountryController.$inject = ['DataService', '$location'];

	function CountryController(DataService, $location) {
		var $ctrl = this;
		$ctrl.path = $location.path();
		// console.log($location.path())
		// console.log(countries);
		// if (countries == null) {
		// 	$location.path("/login/")
		// } else {
		// 	$ctrl.countries = countries;
		// 	//console.log(countries);
		// }

		var promise = DataService.getCountries();
		promise.then(function(response) {
			$ctrl.countries = response;
		}, function(fail) {
			// console.log(fail);
			$location.path("/login/")
		});

		// if ($ctrl.countries == null) {
		// 				$location.path("/login/")
		// 			} 

	}


})();