define([
    "dijit/layout/ContentPane",
    "dojo/text!/server/js/orgstructure/orgstructure.html"//Проблемы с путем
], function(ContentPane, template){
    let tab;
    return {
            createTab: function(item){
                tab = new ContentPane({});
                tab.set("id",item.id);
                tab.set("title",item.name);
                tab.set("closable",true);
                tab.set("content", template);
                tab.set("selected", true);
                return tab;
            }
        };
});