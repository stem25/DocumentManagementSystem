define([
    "dijit/layout/BorderContainer",
    "dijit/layout/ContentPane",
    "dijit/layout/TabContainer",
    "dojo/on",
    "./js/tree.js",
    "dojo/domReady!"
], function(BorderContainer, ContentPane, TabContainer, on, tree){
    // create a BorderContainer as the top widget in the hierarchy
    let bc = new BorderContainer({
        class: "demoLayout",
        id: "appLayout"
    });

    // create a ContentPane as the left pane in the BorderContainer
    let cp1 = new ContentPane({
        region: "left",
        content: tree.getTree(),
        splitter: true,
        id: "leftCol",
        focused: false
    });
    bc.addChild(cp1);

    // create a TabContainer as the center pane in the BorderContainer,
    // which itself contains two children
    let tc = new TabContainer({region: "center"});
    let tab1 = new ContentPane({title: "tab 1"});
    tc.addChild( tab1 );
    bc.addChild(tc);

    on(tree.getTree(), "dblclick", function(item, node, evt) {
        if (item.id === "orgstructure") {
            let tab = new ContentPane({
                title: item.name,
                closable: true
            });
            tab.set("href", "./js/templates/orgstructure.html");
            tc.addChild(tab);
        }
    });


    // put the top level widget into the document, and then call startup()
    document.body.appendChild(bc.domNode);
    bc.startup();
});