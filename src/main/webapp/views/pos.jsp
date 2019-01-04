<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Main Page</title>
</head>
<body ng-app="demo">
<hr/>
<div class="container" ng-controller="ProductController">
    <div class="row">
        <button ng-click="getAllProducts()" class="btn btn-sm btn-info">View All Products</button>
    </div>
    <hr/>
    <div class="row">
        <p>{{allProducts | json}}</p>
    </div>
    <hr/>
    <div class="row" ng-repeat="product in allProducts">
        <div class="">
            <h3>{{product.id}}</h3>
            <h3>{{product.productCode}}</h3>
            <h3>{{product.productName}}</h3>
        </div>
    </div>
</div>
</body>
<script src="js/lib/angular.min.js"></script>
<script src="js/lib/ui-bootstrap-tpls-2.5.0.min.js"></script>
<script src="js/app/app.js"></script>
<script src="js/app/ProductController.js"></script>
<script src="js/app/ProductService.js"></script>
<link rel="stylesheet" href="css/lib/bootstrap.min.css"/>
<link rel="stylesheet" href="css/app/app.css"/>
</html>