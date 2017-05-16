(function() {
	"use strict";

	angular.module('MenuApp')
		.controller('EditorController', EditorController);

	EditorController.$inject = ['DataService', '$stateParams', '$scope', '$location'];

	function EditorController(DataService, $stateParams, $scope, $location) {
		var $ctrl = this;
		if ($stateParams.params == null) {
			$location.path("/login/")
		} else if ($stateParams.params.length == 0) {
			// console.log("Going to countries");
			// $location.path("/countries/") // Correct reference "Back" from Editor and that would work (probably)
		} else {
			$ctrl.params = $stateParams.params;
			$ctrl.path = $stateParams.path.slice(1); //var n=str.slice(5);
			$ctrl.data = $stateParams.data;
			// $ctrl.send = {};


			console.log($ctrl.params);

			if ($ctrl.path == 'factories') {
				$ctrl.send = $stateParams.data //= "country :" + JSON.stringify($ctrl.data)
				console.log($ctrl.send)
			}
			if ($ctrl.path == 'lines') {
				$ctrl.send = $stateParams.data 
				console.log($ctrl.send)
			}




			if ($ctrl.path == 'report') {
				$ctrl.send = $stateParams
				console.log($stateParams)
				if ($stateParams.params[0].deleted!=undefined){
					console.log("God to go")
					$ctrl.stub = 'Consumption'
										console.log($ctrl.stub)

				}
				
			}

			// ui-sref="factories({country:$ctrl.country})"
			// console.log($ctrl.params[0].country_id);
			console.log($ctrl.path);
			//console.log($ctrl.data);

			$ctrl.fields = Object.getOwnPropertyNames($ctrl.params[0]);
			// $ctrl.class = 'col-md-6 col-sm-12 col-xs-12';
			// if ($ctrl.path == 'Line') {
			// 	//console.log("Line")
			// 	$ctrl.class = 'row';
			// }

			$scope.insertEntry = function() {
				// console.log($ctrl.entry)
				$ctrl.saveMethod($ctrl.entry);
			};

			$scope.setParam = function(entry) {
				$ctrl.edit = 1;
				$ctrl.editEntry = entry;
				delete $ctrl.editEntry.$$hashKey;
				// console.log('Edit Entry is: ')
				// console.log($ctrl.editEntry)
				$ctrl.index = $ctrl.params.indexOf(entry);
				//console.log($ctrl.index)
			};

			$ctrl.updateEntry = function() {
				//console.log($ctrl.editEntry)
				//var msg = JSON.stringify($ctrl.editEntry)
				//console.log(msg)
				$ctrl.saveMethod($ctrl.editEntry);
				$ctrl.edit = 0;
			};

			$ctrl.saveMethod = function(entry) {
				if ($ctrl.stub == 'Consumption') {
					var entryCons = angular.copy(entry);
					delete entryCons.name;
					delete entryCons.unit;
					delete entryCons.deleted;
					console.log(entry);
					var msgCons = JSON.stringify(entryCons);
					console.log(msgCons);

				}
				// console.log(entry)
				var msg = JSON.stringify(entry);
				console.log(msg)
				if ($ctrl.path == 'countries') {
					DataService.saveCountry(msg);
					if (entry.id == undefined) {
						$ctrl.params.push({
							"id": "n/a",
							"name": entry.name
						});
					}
					// if (entry.id == undefined) {
					// 	entry.id = "n/a"
					// 	$ctrl.params.push(entry);
					// }
				}
				if ($ctrl.path == 'factories') {
					DataService.saveFactory(msg);
					if (entry.id == undefined) {
						$ctrl.params.push({
							"id": "n/a",
							"name": entry.name,
							"country_id": entry.country_id

						});
					}
				}
				if ($ctrl.path == 'lines') {
					DataService.saveLine(msg);
					if (entry.id == undefined) {
						$ctrl.params.push({
							"id": "n/a",
							"name": entry.name,
							"product_id": entry.productId,
							"speed": entry.speed,
							"unit": entry.unit,
							"capacity": entry.capacity,
							"buildYear": entry.buildYear,
							"factoryId": entry.factoryId
						});
					}
				}
				if ($ctrl.path == 'report' && $ctrl.stub!='Consumption') {
					DataService.saveReport(msg);
					if (entry.id == undefined) {
						$ctrl.params.push({
							"id": "n/a",
							"date": entry.date,
							"shift": entry.shift,
							"register": entry.register,
							"input": entry.input,
							"output": entry.output,
							"lineId": entry.lineId
						});
					}
				}
				if ($ctrl.stub == 'Consumption') {
					DataService.saveCons(msgCons);
					if (entry.id == undefined) {
						$ctrl.params.push({
							"id": "n/a",
							"name": entry.name,
							"quantity": entry.quantity,
							"unit": entry.unit,
							"deleted": entry.deleted,
							"materialId": entry.materialId,
							"reportId": entry.reportId
						});
					}
				}
			};

			$scope.deleteEntry = function(param) {
				var index = $ctrl.params.indexOf(param);
				if ($ctrl.path === 'countries' && param.id != 'n/a') {
					DataService.deleteCountry(param.id);
				}
				if ($ctrl.path === 'factories' && param.id != 'n/a') {
					DataService.deleteFactory(param.id);
				}
				if ($ctrl.path === 'lines' && param.id != 'n/a') {
					DataService.deleteLine(param.id);
				}
				if ($ctrl.path === 'report' && param.id != 'n/a'&& $ctrl.stub!='Consumption') {
					DataService.deleteReport(param.id);
				}
				if ($ctrl.stub == 'Consumption' && param.id != 'n/a') {
					DataService.deleteCons(param.id);
				}
				if (index > -1) {
					$ctrl.params.splice(index, 1);
				}
			};
		}
	}

})();