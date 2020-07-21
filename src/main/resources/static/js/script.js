var app = angular.module("InventoryList", ['ngRoute']);

// Controller Part
app.controller("InventoryListController", function($scope, $http, $routeParams, $location) {

    $scope.departments = [];
    $scope.mols = [];
    $scope.filterForm = {
        department: "",
        mol: ""
    };

    // Now load the data from server
    _refreshFilterFormData();

    // var url = $location.absUrl();
    // var params = $location.search('mol, codeDepartment');
    // console.log($location.search('mol'));
// $location.search() => {foo: 'yipee', baz: 'xoxo'}

     // _clearFormData();

    // // HTTP POST/PUT methods for add/edit employee
    // // Call: http://localhost:8080/employee
    // $scope.submitEmployee = function() {
    //
    //     var method = "";
    //     var url = "";
    //
    //     if ($scope.employeeForm.empId == -1) {
    //         method = "POST";
    //         url = '/employee';
    //     } else {
    //         method = "PUT";
    //         url = '/employee';
    //     }
    //
    //     $http({
    //         method: method,
    //         url: url,
    //         data: angular.toJson($scope.employeeForm),
    //         headers: {
    //             'Content-Type': 'application/json'
    //         }
    //     }).then(_success, _error);
    // };

    // $scope.createEmployee = function() {
    //     _clearFormData();
    // }
    //
    // // HTTP DELETE- delete employee by Id
    // // Call: http://localhost:8080/employee/{empId}
    // $scope.deleteEmployee = function(employee) {
    //     $http({
    //         method: 'DELETE',
    //         url: '/employee/' + employee.empId
    //     }).then(_success, _error);
    // };

    // // In case of edit
    // $scope.editEmployee = function(employee) {
    //     $scope.employeeForm.empId = employee.empId;
    //     $scope.employeeForm.empNo = employee.empNo;
    //     $scope.employeeForm.empName = employee.empName;
    // };

    // Private Method
    // HTTP GET- get all employees collection
    // Call: http://localhost:8080/employees
    function _refreshFilterFormData() {

        $http({
            method: 'GET',
            url: '/user/rest/departments'
        }).then(
            function(res) { // success
                $scope.departments = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );

// GET inventory by id
        console.log($routeParams['invNumberId']);
        $http({
            method: 'GET',
            url: '/user/rest/invnumber',
            params: {'id':$routeParams['invNumberId']}
        }).then(
            function(res) { // success
                $scope.mols = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );

        $http({
            method: 'GET',
            url: '/user/rest/mols'
        }).then(
            function(res) { // success
                $scope.mols = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _success(res) {
        _refreshFilterFormData();
        // _clearFormData();
    }

    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }

    // Clear the form
    function _clearFormData() {
        $scope.filterForm.department = "";
        $scope.filterForm.mol = "";

    };
});

app.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.

        when('/users/:invNumberId', {
            // templateUrl: ('/resources/angularjs/templates/getUser.html'),
            controller: 'InventoryListController'

        }).
        otherwise({
            redirectTo: '/users'
        });
    }]);

function searchInvNumber() {
    window.location.href='/user/' + $("#invnumber").val() + '/';
}

