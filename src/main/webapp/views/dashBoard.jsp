<!DOCTYPE html>
<html lang="en">
<head>
    <title id="Description">This demo showcases jqxLayout's right-to-left support.</title>
    <link rel="stylesheet" href="../js/jqwidgets/styles/jqx.base.css" type="text/css" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1 maximum-scale=1 minimum-scale=1" />	
    <style type="text/css">
        .jqx-layout-group-auto-hide-content-vertical
        {
            width: 200px;
        }
        .jqx-ribbon-content-section
        {
            text-align: right;
        }
    </style>
    <script type="text/javascript" src="../js/app/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="../js/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="../js/jqwidgets/jqxribbon.js"></script>
    <script type="text/javascript" src="../js/jqwidgets/jqxlayout.js"></script>
    <script type="text/javascript" src="../js/jqwidgets/jqxtree.js"></script>
    <script type="text/javascript" src="../js/app/demos.js"></script>
    <script type="text/javascript" src="../js/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="../js/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="../js/jqwidgets/jqxcheckbox.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxmenu.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            // the 'layout' JSON array defines the internal structure of the layout
            var layout = [{
                type: 'layoutGroup',
                width: '100%',
                orientation: 'horizontal',
                items: [{
                    type: 'autoHideGroup',
                    alignment: 'left',
                    width: '20%',
                    unpinnedWidth: '20%',
                    items: [{
                        type: 'layoutPanel',
                        title: 'Toolbox',
                        contentContainer: 'ToolboxPanel'
                    }, {
                        type: 'layoutPanel',
                        title: 'Help',
                        contentContainer: 'HelpPanel'
                    }]
                }, {
                    type: 'layoutGroup',
                    orientation: 'vertical',
                    width: '80%',
                    items: [ {
                        type: 'tabbedGroup',
                        height: '10%',
                        pinnedHeight: 30,
                        items: [{
                            type: 'layoutPanel',
                            title: 'Error List',
                            contentContainer: 'ErrorListPanel'
                        }, {
                            type: 'layoutPanel',
                            title: 'Output',
                            contentContainer: 'OutputPanel',
                            selected: true
                        }]
                    }]
                }]
            }];
            $('#jqxLayout').jqxLayout({ width: '100%', height: 800, layout: layout, rtl: true });
        });
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
<body>
    <div id="jqxLayout">
        <!--The panel content divs can have a flat structure-->
        <!--autoHideGroup-->
        <div data-container="ToolboxPanel">
        <div id='jqxMenu' style='visibility: hidden;'>
        <ul>
            <li><a href="#Home">Home</a></li>
            <li><a href="#Solutions">Solutions</a>
                <ul style='width: 250px;'>
                    <li><a href="#Education">Education</a></li>
                    <li><a href="#Financial">Financial services</a></li>
                    <li><a href="#Government">Government</a></li>
                    <li><a href="#Manufacturing">Manufacturing</a></li>
                    <li type='separator'></li>
                    <li>Software Solutions
                                <ul style='width: 220px;'>
                                    <li><a href="#ConsumerPhoto">Consumer photo and video</a></li>
                                    <li><a href="#Mobile">Mobile</a></li>
                                    <li><a href="#RIA">Rich Internet applications</a></li>
                                    <li><a href="#TechnicalCommunication">Technical communication</a></li>
                                    <li><a href="#Training">Training and eLearning</a></li>
                                    <li><a href="#WebConferencing">Web conferencing</a></li>
                                </ul>
                    </li>
                    <li><a href="#">All industries and solutions</a></li>
                </ul>
            </li>
            <li><a href="#Products">Products</a>
                <ul>
                    <li><a href="#PCProducts">PC products</a></li>
                    <li><a href="#MobileProducts">Mobile products</a></li>
                    <li><a href="#AllProducts">All products</a></li>
                </ul>
            </li>     
        </ul>
    </div>
    <br />
    <br />
    <div id="minimizeCheckbox">Minimized</div>
            List of tools</div>
        <div data-container="HelpPanel">
            Help topics</div>
        <!--documentGroup-->
        <div data-container="Document1Panel">
            <div style="float: right;">
                Document 1 content</div>
        </div>
        <div data-container="Document2Panel">
            <div style="float: right;">
                Document 2 content</div>
        </div>
        <!--bottom tabbedGroup-->
        <div data-container="ErrorListPanel">
            List of errors</div>
        <div data-container="OutputPanel">
            Output</div>
        <!--right tabbedGroup-->
        <div data-container="SolutionExplorerPanel">
            <div id="solutionExplorerTree" style="border: none;">
            </div>
        </div>
        <div data-container="PropertiesPanel">
            List of properties</div>
    </div>
</body>
</html>
