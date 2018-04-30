require(['dojo/_base/lang', 'dojox/grid/DataGrid', 'dojo/data/ItemFileWriteStore', 'dojo/dom'],
    function(lang, DataGrid, ItemFileWriteStore, dom){

        /*set up data store*/
        let data = {
            identifier: "id",
            items: []
        };
        let data_list = [
            { col1: "normal", col2: false, col3: 'But are not followed by two hexadecimal', col4: 29.91},
            { col1: "important", col2: false, col3: 'Because a % sign always indicates', col4: 9.33},
            { col1: "important", col2: false, col3: 'Signs can be selectively', col4: 19.34}
        ];
        let rows = 60;
        for(let i = 0, l = data_list.length; i < rows; i++){
            data.items.push(lang.mixin({ id: i+1 }, data_list[i%l]));
        }
        let store = new ItemFileWriteStore({data: data});

        /*set up layout*/
        let layout = [[
            {'name': 'Column 1', 'field': 'id', 'width': '100px'},
            {'name': 'Column 2', 'field': 'col2', 'width': '100px'},
            {'name': 'Column 3', 'field': 'col3', 'width': '200px'},
            {'name': 'Column 4', 'field': 'col4', 'width': '150px'}
        ]];

        /*create a new grid*/
        let grid = new DataGrid({
            id: 'grid',
            store: store,
            structure: layout,
            rowSelector: '20px'});

        /*append the new grid to the div*/
        grid.placeAt("gridDiv");

        /*Call startup() to render the grid*/
        grid.startup();
    });