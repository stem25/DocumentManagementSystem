define([
    "dojo/_base/declare",
    "dijit/_Widget",
    "dijit/_TemplatedMixin",
    "dijit/_WidgetsInTemplateMixin",
    "dijit/_AttachMixin",
    "dojox/widget/SortList",
    "dojo/text!./orgstructure.html"
], function(declare,_Widget, _TemplatedMixin, _WidgetsInTemplateMixin, _AttachMixin, SortList, template){
    return declare("OrgstructureWidget",[_Widget, _TemplatedMixin, _WidgetsInTemplateMixin,_AttachMixin], {
        templateString: template,
        widgetsInTemplate: true,
        data:"loading",
        onLoadWidget: function(){
            this.list();
        },
        list:function(){
            let that = this;
            let xhrArgs = {
                url: "rest/entity/Organization",
                handleAs: "text",
                load: function(data){
                    that.data = data;
                },
                error: function(error){
                    that.data = error;
                    that.orgTree.refresh();
                }
            };
            dojo.xhrGet(xhrArgs);
        }
    });
});