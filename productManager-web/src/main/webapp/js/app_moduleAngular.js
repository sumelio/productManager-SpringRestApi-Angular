/**
 * Angular Module javascript
 * 
 * @Author Freddy.lemus
 * 
 */
var productManagerModule = angular.module('beitechApp', [ 'ngAnimate' ]);

productManagerModule
		.controller(
				'beitechController',
				function($scope, $http) {

					// API Rest
					var urlBase = "/productManager-api-rest/v1";

					// Get dates
					let dateNow = new Date();
					$scope.fromDate = dateNow.yyyymmdd(1);

					let dateMonthBefore = monthLast(dateNow)
					$scope.untilDate = dateMonthBefore.yyyymmdd(1);

					// Function: Get all Customer and fill select
					$http.get(urlBase + '/customer').success(function(data) {
						$scope.customers = data;

					});

					// Function: Get Order by customer and dates
					$scope.findOrders = function findOrders() {

						if ($scope.customerSelect > 0) {
							$http.get(
									urlBase + '/customer/'
											+ $scope.customerSelect
											+ '/order?fromDate='
											+ $scope.untilDate + '&untilDate='
											+ $scope.fromDate
								 ).success(
									function(data) {
										$scope.listOrders = data.orders;

									}
								 ).error(
									function(data) {
										$scope.listOrders = null;

										swal("Oops, 'Data not found",
												"'Orders not found!", "info")

									});
						} else {

							swal(
									"'Customer name is required",
									"'Insufficient Data! Please provide values for Customer name!",
									"error")
						}
					};

				});
