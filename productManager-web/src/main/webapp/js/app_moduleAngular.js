/**
 * Angular Module javascript
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
					$scope.endDate = dateNow.yyyymmdd(1);

					let dateBefore = monthLast(dateNow)
					$scope.beginDate = dateBefore.yyyymmdd(1);

					// get all Customer and fill combo
					$http.get(urlBase + '/customer').success(function(data) {
						$scope.customers = data;

					});

					// get Order by customer and dates
					$scope.findOrders = function findOrders() {

						if ($scope.customerSelect > 0) {
							$http.get(
									urlBase + '/customer/'
											+ $scope.customerSelect
											+ '/order?startDate='
											+ $scope.beginDate + '&endDate='
											+ $scope.endDate).success(
									function(data) {
										$scope.listOrders = data;

									}).error(
									function(data) {

										swal("Oops, 'Data not found",
												"'Orders not found!", "info")

									});
						} else {

							swal(
									"Oops, 'Customer name is required",
									"'Insufficient Data! Please provide values for Customer name!",
									"error")
						}
					};

				});
