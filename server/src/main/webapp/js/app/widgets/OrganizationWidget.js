define([
    "dojo/_base/declare",
    "dijit/_Widget",
    "dijit/_TemplatedMixin",
    "dijit/_WidgetsInTemplateMixin",
    "dijit/_AttachMixin",
    "dojo/text!./templates/Organization/Organization.html",
    "dijit/form/Form",
    "dijit/form/Button",
    "dijit/form/ValidationTextBox",
    "dijit/form/DateTextBox",
    "dojo/data/ObjectStore",
    "dojo/store/JsonRest"
], function(declare,_Widget, _TemplatedMixin, _WidgetsInTemplateMixin, _AttachMixin, template, Form, Button, ValidationTextBox, DateTextBox, ObjectStore, JsonRest){
    return declare("OrganizationWidget",[_Widget, _TemplatedMixin, _WidgetsInTemplateMixin,_AttachMixin], {
        templateString: template,
        widgetsInTemplate: true,
        myStore: new JsonRest({target:"rest/entity/Organization/1"})
    });
});