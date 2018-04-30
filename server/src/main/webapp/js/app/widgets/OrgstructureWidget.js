define([
    "dojo/_base/declare",
    "dijit/_Widget",
    "dijit/_TemplatedMixin",
    "dijit/_WidgetsInTemplateMixin",
    "dijit/_AttachMixin",
    "app/widgets/OrgstructureTree",
    "dojo/text!./templates/Orgstructure/Orgstructure.html"
], function(declare,_Widget, _TemplatedMixin, _WidgetsInTemplateMixin, _AttachMixin, tree, template){
    return declare("OrgstructureWidget",[_Widget, _TemplatedMixin, _WidgetsInTemplateMixin,_AttachMixin], {
        templateString: template,
        widgetsInTemplate: true,
        data:"loading",
        onLoadWidget: function(){

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
                }
            };
            dojo.xhrGet(xhrArgs);
        }
    });
});