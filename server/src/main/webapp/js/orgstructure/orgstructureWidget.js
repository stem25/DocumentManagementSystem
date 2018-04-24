define([
    "dojo/dom"
], function(dom){
    return {
            parse: function(data){
               let node = dom.byId("orgstructure");
               if(node){
                   node.innerHTML = data;
               }
            }
        };
});