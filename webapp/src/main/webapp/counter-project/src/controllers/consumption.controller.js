(function () {
"use strict";

angular.module('MenuApp')
.controller('ConsumptionController', ConsumptionController);

ConsumptionController.$inject = ['DataService','consumption'];
function ConsumptionController(DataService,consumption) {
  var $ctrl = this;

  $ctrl.consumption = consumption;

    console.log('ConsumptionController')


   function getMaterialDataById(materialId) {

      console.log('getMaterialDataById')
      
    var promise = DataService.getMaterialDataById(materialId);
                        promise.then(function(response){

$ctrl.material = response
                 console.log(response)

                }); 
  }
}

})();
