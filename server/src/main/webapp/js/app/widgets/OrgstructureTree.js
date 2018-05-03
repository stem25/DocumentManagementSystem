define([
    "dojo/dom",
    "dijit/registry",
    "dojo/store/Memory",
    "dojo/store/Observable",
    "dijit/tree/ObjectStoreModel",
    "dijit/Tree",
    "app/widgets/ContentWidget"
], function(dom, registry, Memory, Observable, ObjectStoreModel, Tree, ContentWidget){
    let structure;
    let orgStore;
    let xhrArgs = {
        url: "rest/entity/Organization",
        handleAs: "json",
        load: function(data){
            structure = data;

            //Добавляем корневой каталог всем организациям
            for(let element of structure){
                element['parent'] = 0;
                element['type'] = "Organization";
            }
            //Добавляем корневой каталог
            structure.push({"id":0, "name":"WORKSPACE"});
            orgStore = new Memory({
                data: structure,
                getChildren: function(object){
                    return this.query({parent: object.id});
                }
            });
            orgStore = new Observable(orgStore);

            let orgModel = new ObjectStoreModel({
                store: orgStore,
                query: {id: 0}
            });

            let tree = new Tree({
                model: orgModel,
                showRoot: false
            });
            tree.connect(tree, "onOpen", function(item, node){
                console.log(item);
                addDeparments(item, orgStore)
            });

            tree.connect(tree, "onDblClick", function(item, node){
                let mainTabContainer = registry.byId("mainTabContainer");
                if(mainTabContainer){
                    openTab(item, mainTabContainer);
                }
            });
            tree.placeAt("orgstructureTree");
            tree.startup();

        },
        error: function(error){
            dom.byId("orgstructure_error").innerHTML = "ERROR: "+ error.message;
        }
    };
    dojo.xhrGet(xhrArgs);

    let addDeparments = function(item, orgStore) {
        let xhrArgs = {
            url: "rest/entity/Department?org_id=" + item.id,
            handleAs: "json",
            load: function (data) {
                for (let element of data) {
                    element['id'] = item.id + '.' + element.id;
                    element['parent'] = parseInt(item.id);
                    element['type'] = "Department";
                    orgStore.add({id: element.id, name: element.name, parent: element.parent});
                }
            },
            error: function (error) {
            }
        };
        if (item.type === "Organization") {
            dojo.xhrGet(xhrArgs);
        }
    };

    let openTab = function(item, tabContainer){
        let cp;
        if(item.type === "Organization"){
            require(["app/widgets/OrganizationWidget"],function(Widget){
                cp = new ContentWidget({
                    item: item,
                    content: "<div data-dojo-type='OrganizationWidget'></div>"
                });
            });
        } else if(item.type === "Department"){
            require(["app/widgets/DepartmentWidget"],function(Widget){
                cp = new ContentWidget({
                    item: item,
                    content: "<div data-dojo-type='DepartmentWidget'></div>"
                });
            });
        }
        tabContainer.addChild(cp);
        tabContainer.selectChild(cp);
    }
});