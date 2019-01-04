<!DOCTYPE html>
<html lang="en">
<head>
    <title id='Description'>The demo demonstrates the Minimized state of jqxMenu.</title>
    <link rel="stylesheet" href="../js/jqwidgets/styles/jqx.base.css" type="text/css" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1 maximum-scale=1 minimum-scale=1" />	
    <script type="text/javascript" src="../js/app/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="../js/app/demos.js"></script>
    <script type="text/javascript" src="../js/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="../js/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="../js/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="../js/jqwidgets/jqxcheckbox.js"></script>
    <script type="text/javascript" src="../js/jqwidgets/jqxmenu.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            // create jqxMenu
            $("#jqxMenu").jqxMenu({ width: '100%', height: '32px', autoSizeMainItems: true});
            $("#jqxMenu").jqxMenu('minimize');
            $("#minimizeCheckbox").jqxCheckBox({ checked: true });
            $("#minimizeCheckbox").on('change', function () {
                if ($("#minimizeCheckbox").val()) {
                    $("#jqxMenu").jqxMenu('minimize');
                }
                else $("#jqxMenu").jqxMenu('restore');
            });
            $("#jqxMenu").css('visibility', 'visible');
        });
    </script>
</head>
<body class='default'>
    <div id='jqxMenu' style='visibility: hidden;'>
        <ul>
            <li><a href="#Home">Home</a></li>
            <li><a href="#Products">Catelog</a>
                <ul>
                    <li><a href="#PCProducts">Product</a></li>
                    <li><a href="#MobileProducts">Product Category</a></li>
                </ul>
            </li>     
        </ul>
    </div>
    <br />
    <div id="minimizeCheckbox">Minimized</div>
    
    <div id="mainFrame">
    
    </div>
</body>
</html>