define([
    "dojo/dom",
    "dojo/request"
], function(dom, request){
    let data = undefined;
    request("rest/test/").then(
        function(text){
            data = text;
        }, function(error){
            data = error;
        });
    return {
            parse: function(){
                let node = dom.byId("orgstructure");
                node.innerHTML = data;
            }
        };
});