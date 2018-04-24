define([
    "dojo/ready",
    "dojo/parser",
    "dijit/layout/BorderContainer",
    "dijit/layout/ContentPane",
    "dijit/layout/TabContainer",
    "dijit/registry",
    "dojo/on",
    "./js/tree.js",
    "./js/tab-factory.js"
    ], function(ready, parser, BorderContainer, ContentPane, TabContainer, registry, on, tree, tabFactory){
    ready(function(){
        let contentPane = registry.byId("leftCol");
        let tabContainer = registry.byId("tabs");
        if(contentPane && tabContainer){
            contentPane.set("content",tree.getTree());
            on(tree.getTree(), "dblclick", function(item, node, evt) {
                tabFactory.createTab(tabContainer, item);
            });
        }
    });
});