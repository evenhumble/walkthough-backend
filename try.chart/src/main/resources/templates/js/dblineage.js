
// init graph
var g = new dagreD3.graphlib.Graph({
    compound: true
}).setGraph({}).setDefaultEdgeLabel(function () {
    return {};
});

data = {"db_name":"tmp","fields":[{"col_name":"tmp0","db_name":"tmp0","source_fields":[{"col_name":"second_0","db_name":"second_0","source_fields":[],"table_name":"second_0"},{"col_name":"second_1","db_name":"second_1","source_fields":[],"table_name":"second_1"},{"col_name":"second_2","db_name":"second_2","source_fields":[],"table_name":"second_2"},{"col_name":"second_3","db_name":"second_3","source_fields":[],"table_name":"second_3"}],"table_name":"tmp_table0"},{"col_name":"tmp1","db_name":"tmp1","source_fields":[{"col_name":"second_0","db_name":"second_0","source_fields":[],"table_name":"second_0"},{"col_name":"second_1","db_name":"second_1","source_fields":[],"table_name":"second_1"},{"col_name":"second_2","db_name":"second_2","source_fields":[],"table_name":"second_2"},{"col_name":"second_3","db_name":"second_3","source_fields":[],"table_name":"second_3"}],"table_name":"tmp_table1"},{"col_name":"tmp2","db_name":"tmp2","source_fields":[{"col_name":"second_0","db_name":"second_0","source_fields":[],"table_name":"second_0"},{"col_name":"second_1","db_name":"second_1","source_fields":[],"table_name":"second_1"},{"col_name":"second_2","db_name":"second_2","source_fields":[],"table_name":"second_2"},{"col_name":"second_3","db_name":"second_3","source_fields":[],"table_name":"second_3"}],"table_name":"tmp_table2"},{"col_name":"tmp3","db_name":"tmp3","source_fields":[{"col_name":"second_0","db_name":"second_0","source_fields":[],"table_name":"second_0"},{"col_name":"second_1","db_name":"second_1","source_fields":[],"table_name":"second_1"},{"col_name":"second_2","db_name":"second_2","source_fields":[],"table_name":"second_2"},{"col_name":"second_3","db_name":"second_3","source_fields":[],"table_name":"second_3"}],"table_name":"tmp_table3"},{"col_name":"tmp4","db_name":"tmp4","source_fields":[{"col_name":"second_0","db_name":"second_0","source_fields":[],"table_name":"second_0"},{"col_name":"second_1","db_name":"second_1","source_fields":[],"table_name":"second_1"},{"col_name":"second_2","db_name":"second_2","source_fields":[],"table_name":"second_2"},{"col_name":"second_3","db_name":"second_3","source_fields":[],"table_name":"second_3"}],"table_name":"tmp_table4"},{"col_name":"tmp5","db_name":"tmp5","source_fields":[{"col_name":"second_0","db_name":"second_0","source_fields":[],"table_name":"second_0"},{"col_name":"second_1","db_name":"second_1","source_fields":[],"table_name":"second_1"},{"col_name":"second_2","db_name":"second_2","source_fields":[],"table_name":"second_2"},{"col_name":"second_3","db_name":"second_3","source_fields":[],"table_name":"second_3"}],"table_name":"tmp_table5"},{"col_name":"tmp6","db_name":"tmp6","source_fields":[{"col_name":"second_0","db_name":"second_0","source_fields":[],"table_name":"second_0"},{"col_name":"second_1","db_name":"second_1","source_fields":[],"table_name":"second_1"},{"col_name":"second_2","db_name":"second_2","source_fields":[],"table_name":"second_2"},{"col_name":"second_3","db_name":"second_3","source_fields":[],"table_name":"second_3"}],"table_name":"tmp_table6"},{"col_name":"tmp7","db_name":"tmp7","source_fields":[{"col_name":"second_0","db_name":"second_0","source_fields":[],"table_name":"second_0"},{"col_name":"second_1","db_name":"second_1","source_fields":[],"table_name":"second_1"},{"col_name":"second_2","db_name":"second_2","source_fields":[],"table_name":"second_2"},{"col_name":"second_3","db_name":"second_3","source_fields":[],"table_name":"second_3"}],"table_name":"tmp_table7"},{"col_name":"tmp8","db_name":"tmp8","source_fields":[{"col_name":"second_0","db_name":"second_0","source_fields":[],"table_name":"second_0"},{"col_name":"second_1","db_name":"second_1","source_fields":[],"table_name":"second_1"},{"col_name":"second_2","db_name":"second_2","source_fields":[],"table_name":"second_2"},{"col_name":"second_3","db_name":"second_3","source_fields":[],"table_name":"second_3"}],"table_name":"tmp_table8"},{"col_name":"tmp9","db_name":"tmp9","source_fields":[{"col_name":"second_0","db_name":"second_0","source_fields":[],"table_name":"second_0"},{"col_name":"second_1","db_name":"second_1","source_fields":[],"table_name":"second_1"},{"col_name":"second_2","db_name":"second_2","source_fields":[],"table_name":"second_2"},{"col_name":"second_3","db_name":"second_3","source_fields":[],"table_name":"second_3"}],"table_name":"tmp_table9"}],"table_name":"tmp"};
renderDatalineage(data);
function renderDatalineage(dataLineage){
    //render first to level
    var rootLabel = [1,dataLineage.db_name,dataLineage.table_name].join("_");
    g.setNode(rootLabel,{
        label:rootLabel,
        class:"level_1"
    });
    if(dataLineage.fields==undefined||dataLineage.fields.length==0) return;
    var fields = dataLineage.fields;
    for (var index in fields) {
        renderTree(fields[index],rootLabel,2);
    }
}
function renderTree(dbField,parentLabel,level){
  var levelClassPrefix="level";
  //render self
  var nodeLabel=[level,dbField.db_name,dbField.table_name,dbField.col_name].join("_");
  var levelClass = levelClassPrefix+"_"+level;
  g.setNode(nodeLabel,
        {
            label:nodeLabel,
            class: levelClass
        }
    );
  g.setEdge(nodeLabel,parentLabel);
  var sourceFields = dbField.source_fields;
  if(sourceFields==undefined||sourceFields.length==0) return;
  for (var index in sourceFields) {
    renderTree(sourceFields[index],nodeLabel,level+1);
  }
}


// // Here we"re setting nodeclass, which is used by our custom drawNodes function
// // below.
// g.setNode("l0", { label: "targetTable", class: "level_1" });
// g.setNode(1, { label: "targetTable.Field1", class: "level_2" });
// g.setNode(2, { label: "targetTable.Field2", class: "level_2" });
// g.setNode(3, { label: "targetTable.Field3", class: "level_2" });
// g.setNode(4, { label: "sourceTable.Field1", class: "level_3" });
// g.setNode(5, { label: "sourceTable.Field2", class: "level_3" });
// g.setNode(6, { label: "sourceTable.Field3", class: "level_3" });
// g.setNode(7, { label: "sourceTable.Field4", class: "level_4" });
// g.setNode(8, { label: "sourceTable.Field5", class: "level_4" });
// g.nodes().forEach(function (v) {
//     var node = g.node(v);
//     // Round the corners of the nodes
//     node.rx = node.ry = 10;
// });
// g.setEdge(1, "l0");
// g.setEdge(2, "l0");
// g.setEdge(3, "l0");
// g.setEdge(4, 1);
// g.setEdge(5, 2);
// g.setEdge(6, 3);
// g.setEdge(7, 2);
// g.setEdge(8, 1);
var render = new dagreD3.render();
// Set up an SVG group so that we can translate the final graph.
var svg = d3.select("svg"),
    svgGroup = svg.append("g");
// Run the renderer. This is what draws the final graph.
render(d3.select("svg g"), g);
// Center the graph
// var xCenterOffset = (svg.attr("width") - g.graph().width) / 2;
// svgGroup.attr("transform", "translate(" + xCenterOffset + ", 20)");
// svg.attr("height", g.graph().height + 40);