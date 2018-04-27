define([
    "dojo/store/Memory",
    "dojo/store/Observable",
    "dijit/tree/ObjectStoreModel",
    "dijit/Tree"
], function(Memory, Observable, ObjectStoreModel, Tree){

    // Create test store, adding the getChildren() method required by ObjectStoreModel
    myStore = new Memory({
        data: [
            { id: 'workspace', name:'Рабочая область'},
            { id: 'organiztion', name:'Справочник организации', parent: 'workspace', clickable: false},
            { id: 'orgstructure', name:'Оргструктура', parent: 'organiztion', clickable: true},
            { id: 'allPersons', name:'Все сотрудники', parent: 'organiztion', clickable: true},
            { id: 'order', name:'Поручения', parent: 'workspace', clickable: false},
            { id: 'allOrders', name:'Все поручения', parent: 'order', clickable: true},
            { id: 'myOrder', name:'Мои поручения', parent: 'order', clickable: true},
            { id: 'orderForMe', name:'Поручения мне', parent: 'order', clickable: true}
        ],
        getChildren: function(object){
            return this.query({parent: object.id});
        }
    });

    myStore = new Observable(myStore);

    myModel = new ObjectStoreModel({
        store: myStore,
        query: {id: 'workspace'}
    });
});