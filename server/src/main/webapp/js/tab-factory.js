define([
    "./js/orgstructure/orgstructureTab.js",
    "./js/orgstructure/orgstructureModule.js"
],function(orgstructure, module){
    let tab;
    return{
        createTab:function(tabContainer, item){

            switch(item.id) {
                case "orgstructure":{
                    tab = orgstructure.createTab(item);
                }break;
            }
            if(tab) {
                tabContainer.addChild(tab);
                tabContainer.selectChild(tab);
                module.parse();
            }
        }
    }
});