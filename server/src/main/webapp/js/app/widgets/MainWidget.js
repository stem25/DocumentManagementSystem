define([
    "../../dojo/_base/declare",
    "dijit/_Widget",
    "dijit/_TemplatedMixin",
    "dijit/_WidgetsInTemplateMixin",
    "dijit/layout/BorderContainer",
    "dijit/layout/ContentPane",
    "dijit/layout/TabContainer",
    "app/widgets/MenuTree",
    "app/widgets/ContentWidget",
    "dojo/text!./templates/MainWidget.html"
], function(declare, _Widget, _TemplatedMixin, _WidgetsInTemplateMixin,  BorderContainer, ContentPane, TabContainer, tree, ContentWidget, template){
    return declare("MainWidget",[_Widget, _TemplatedMixin, _WidgetsInTemplateMixin], {
        templateString: template,
        widgetsInTemplate: true,
        onLoadMenu: function(){

        },
        onTreeDblClick:function(item, node, evt){
            if(!item.clickable) return;
            let pane = null;
            this.tabContainer.getChildren().forEach(function(cp){
                if(cp.title === item.name){
                    console.log(item.name + "   " +cp.title);
                    pane = cp;
                }
            });
            if(pane){
                console.log("update");
                this.tabContainer.selectChild(pane);
            }else{
                console.log("create");
                this.newTab(item);
            }
        },

        newTab:function(item){
            let cp;
            require(["app/widgets/"+item.id+"Widget"], function(widget){
                cp = new ContentWidget({
                    item: item,
                    content: "<div data-dojo-type='"+item.id+"Widget'>TempContext</div>"
                });
            });
            this.tabContainer.addChild(cp);
            this.tabContainer.selectChild(cp);

        }
    });
});