define([
    "dojo/_base/declare",
    "dijit/_Widget",
    "dijit/_TemplatedMixin",
    "dijit/_WidgetsInTemplateMixin",
    "dijit/_AttachMixin",
    "app/widgets/OrdersTable",
    "dojo/text!./templates/Orders/OrdersGrid.html"
], function(declare,_Widget, _TemplatedMixin, _WidgetsInTemplateMixin, _AttachMixin,table, template){
    return declare("OrdersWidget",[_Widget, _TemplatedMixin, _WidgetsInTemplateMixin,_AttachMixin], {
        templateString: template,
        widgetsInTemplate: true
    });
});