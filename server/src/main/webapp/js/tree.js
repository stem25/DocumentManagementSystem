define([
    "dojo/dom",
    "dojo/store/Memory",
    "dijit/tree/ObjectStoreModel",
    "dijit/Tree",
    "dojo/domReady!"
], function(dom, Memory, ObjectStoreModel, Tree){

    // Create test store, adding the getChildren() method required by ObjectStoreModel
    var myStore = new Memory({
        data: [
            { id: 'workspace', name:'Рабочая область'},
            { id: 'organiztion', name:'Справочник организации', parent: 'workspace'},
            { id: 'orgstructure', name:'Оргструктура', parent: 'organiztion'},
            { id: 'allPersons', name:'Все сотрудники', parent: 'organiztion'},
            { id: 'order', name:'Поручения', parent: 'workspace'},
            { id: 'allOrders', name:'Все поручения', parent: 'order'},
            { id: 'myOrder', name:'Мои поручения', parent: 'order'},
            { id: 'orderForMe', name:'Поручения мне', parent: 'order'}
        ],
        getChildren: function(object){
            return this.query({parent: object.id});
        }
    });

    // Create the model
    let myModel = new ObjectStoreModel({
        store: myStore,
        query: {id: 'workspace'}
    });

    // Create the Tree.
    let tree = new Tree({
        model: myModel,
        autoExpand: true,
        showRoot: false
    });
    return {
        getTree: function () {
            return tree;
        }
    }
});