




var productManagerModule = angular.module('beitechApp', ['ngAnimate']  );

productManagerModule.controller('beitechController', function ($scope,$http) {
	
 
	// API Rest
	var urlBase="/productManager-api-rest/v1";

   
    //Get dates
    let dateNow = new Date();
    $scope.endDate = dateNow.yyyymmdd(1);

	let dateBefore = monthLast(dateNow)
    $scope.beginDate = dateBefore.yyyymmdd(1);
    


	//get all Customer and display initially in Combo
	$http.get(urlBase+'/customer').
    	success(function(data) {
	        $scope.customers = data;
	      
    });
	
	//get all Orders
	$scope.findOrders = function findOrders() {
	  
	 	if( $scope.customerSelect=="" ){
			alert("Insufficient Data! Please provide values for Customer name");
		}
		else{
		 $http.get(urlBase + '/order/customer?customerId='+$scope.customerSelect+'&startDate='+$scope.beginDate+'&endDate='+$scope.endDate).
		  success(function(data) { 
			 $scope.listOrders = data;  
		 
			}).
			error(function(data) { 
				$scope.listOrders = null;  
			
			   });
		}
	};
		
	 
	  
	
 
	
});

 