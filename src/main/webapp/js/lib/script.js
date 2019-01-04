angular.module("mainModule", [])
  .controller("mainController", function ($scope, $http)
  {
    $scope.branch = {
    		branchCode : null,
    		address1 : null,
    		address2 : null,
    		address3 : null,
    		conatactNumber : 0,
    		email : null,
    		companyId : 0
    		};

    $scope.submitData = function (branch, resultVarName)
    {
      var config = {
        params: {
        	branch: branch
        }
      };

     /* $http.post("branchService/saveBranch", null, config)
        .success(function (data, status, headers, config)
        {
          $scope[resultVarName] = data;
        })
        .error(function (data, status, headers, config)
        {
          $scope[resultVarName] = "SUBMIT ERROR";
        });*/
     
      var data=$scope.branch;
      var response = $http.post('branchService/saveBranch',data);
    };
  });