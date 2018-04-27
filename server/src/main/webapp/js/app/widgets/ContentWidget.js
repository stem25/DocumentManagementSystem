define([
    "dojo/_base/declare",
    "dijit/layout/ContentPane"
], function(declare, ContentPane){
    return declare("ContentWidget",[ContentPane], {
        closable:true,
        item:null,
        _setItemAttr: function(item){
            this.item = item;
            this.title = item.name;
        }
    });
});