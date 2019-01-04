<!DOCTYPE html>
<html>
<head>
  <script src="js/lib/angular.min.js"></script>
  <script src="js/lib/script.js"></script>

  <style>
    label
    {
      display: inline-block;
      width: 120px;
      vertical-align: middle;
    }

    input
    {
      display: inline-block;
      vertical-align: middle;
    }

    input.ng-invalid
    {
      border: solid red 2px;
    }

    textarea
    {
      width: 300px;
      height: 80px;
    }
  </style>
</head>

<body ng-app="mainModule">
  <div ng-controller="mainController">
    
    <h3>2. AJAX form submission with ng-submit</h3>
    <form name="branchFform" ng-submit="submitData(branch, 'ajaxSubmitResult1')" novalidate>
      <label for="branchCode">Branch Code</label>
      <input id="branchCode" type="text" name="branchCode" ng-model="branch.branchCode" required /><br />
      <label for="address1">Address1</label>
      <input id="address1" type="text" name="address1" ng-model="branch.address1" required /><br />
      <label for="address2">Address2</label>
      <input id="address2" type="text" name="address2" ng-model="branch.address2" required /><br />
       <label for="address3">Address2</label>
      <input id="address3" type="text" name="address3" ng-model="branch.address3" required /><br />
       <label for="conatactNumber">Conatact Number</label>
      <input id="conatactNumber" type="text" name="conatactNumber" ng-model="branch.conatactNumber" required /><br />
      <label for="email">Email</label>
      <input id="email" type="text" name="email" ng-model="branch.email" required /><br />
       <label for="companyId">Company Id</label>
      <input id="companyId" type="text" name="companyId" ng-model="branch.companyId" required /><br />
      <br />
      <button type="submit"
              ng-disabled="personForm2.$invalid">Submit</button>
    </form>
    <br />
    <strong><label for="submitDebugText1">Submit result:</label></strong><br />
    <textarea id="submitDebugText1">{{ajaxSubmitResult1 | json}}</textarea><br />
    <br />    
  </div>
</body>
</html>