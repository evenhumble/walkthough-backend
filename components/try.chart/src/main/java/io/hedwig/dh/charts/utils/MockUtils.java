package io.hedwig.dh.charts.utils;

import com.alibaba.fastjson.JSON;
import io.hedwig.dh.charts.model.DataLineage;
import io.hedwig.dh.charts.web.dto.DBField;
import io.hedwig.dh.charts.web.dto.DataLineageDTO;

import java.util.ArrayList;
import java.util.List;

public class MockUtils {

    public static DataLineageDTO mockDataLineageDTO() {
        DataLineageDTO dto = new DataLineageDTO();
        dto.setDbName("tmp");
        dto.setName("tmp");
        List<DBField> dbFields = new ArrayList<DBField>();
        for (int i = 0; i < 10; i++) {
            DBField field = new DBField();
            field.setName("tmp" + i);
            field.setDbName("tmp" + i);
            field.setTableName("tmp_table" + i);
            List<DBField> fields = new ArrayList<DBField>();
            for (int j = 0; j < 4; j++) {
                DBField field1 = new DBField();
                field1.setName("second_" + j);
                field1.setDbName("second_" + j);
                field1.setTableName("second_" + j);
                fields.add(field1);
                List<DBField> fields2 = new ArrayList<DBField>();
                for (int k = 0; k < 4 ; k++) {
                    DBField field2 = new DBField();
                    field2.setName("third_" + k);
                    field2.setDbName("third_" + k);
                    field2.setTableName("third_" + k);
                    fields2.add(field2);
                }
                field1.setChildren(fields2);
            }
            dbFields.add(field);
            field.setChildren(fields);
        }
        dto.setChildren(dbFields);
        return dto;
    }

    public static DataLineage mockDataLineage(){

        DataLineage dl = new DataLineage();
        DataLineageDTO dto = mockDataLineageDTO();
        dl.setDataLineageJson(JSON.toJSONString(dto));
        dl.setDbName("testdb"+System.currentTimeMillis());
        dl.setTableName("test_table"+System.currentTimeMillis());
        dl.setActive(1);
        return dl;
    }


    public static String flareJSON="{\n" +
            " \"name\": \"flare\",\n" +
            " \"children\": [\n" +
            "  {\n" +
            "   \"name\": \"analytics\",\n" +
            "   \"children\": [\n" +
            "    {\n" +
            "     \"name\": \"cluster\",\n" +
            "     \"children\": [\n" +
            "      {\"name\": \"AgglomerativeCluster\", \"size\": 3938},\n" +
            "      {\"name\": \"CommunityStructure\", \"size\": 3812},\n" +
            "      {\"name\": \"HierarchicalCluster\", \"size\": 6714},\n" +
            "      {\"name\": \"MergeEdge\", \"size\": 743}\n" +
            "     ]\n" +
            "    },\n" +
            "    {\n" +
            "     \"name\": \"graph\",\n" +
            "     \"children\": [\n" +
            "      {\"name\": \"BetweennessCentrality\", \"size\": 3534},\n" +
            "      {\"name\": \"LinkDistance\", \"size\": 5731},\n" +
            "      {\"name\": \"MaxFlowMinCut\", \"size\": 7840},\n" +
            "      {\"name\": \"ShortestPaths\", \"size\": 5914},\n" +
            "      {\"name\": \"SpanningTree\", \"size\": 3416}\n" +
            "     ]\n" +
            "    },\n" +
            "    {\n" +
            "     \"name\": \"optimization\",\n" +
            "     \"children\": [\n" +
            "      {\"name\": \"AspectRatioBanker\", \"size\": 7074}\n" +
            "     ]\n" +
            "    }\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"animate\",\n" +
            "   \"children\": [\n" +
            "    {\"name\": \"Easing\", \"size\": 17010},\n" +
            "    {\"name\": \"FunctionSequence\", \"size\": 5842},\n" +
            "    {\n" +
            "     \"name\": \"interpolate\",\n" +
            "     \"children\": [\n" +
            "      {\"name\": \"ArrayInterpolator\", \"size\": 1983},\n" +
            "      {\"name\": \"ColorInterpolator\", \"size\": 2047},\n" +
            "      {\"name\": \"DateInterpolator\", \"size\": 1375},\n" +
            "      {\"name\": \"Interpolator\", \"size\": 8746},\n" +
            "      {\"name\": \"MatrixInterpolator\", \"size\": 2202},\n" +
            "      {\"name\": \"NumberInterpolator\", \"size\": 1382},\n" +
            "      {\"name\": \"ObjectInterpolator\", \"size\": 1629},\n" +
            "      {\"name\": \"PointInterpolator\", \"size\": 1675},\n" +
            "      {\"name\": \"RectangleInterpolator\", \"size\": 2042}\n" +
            "     ]\n" +
            "    },\n" +
            "    {\"name\": \"ISchedulable\", \"size\": 1041},\n" +
            "    {\"name\": \"Parallel\", \"size\": 5176},\n" +
            "    {\"name\": \"Pause\", \"size\": 449},\n" +
            "    {\"name\": \"Scheduler\", \"size\": 5593},\n" +
            "    {\"name\": \"Sequence\", \"size\": 5534},\n" +
            "    {\"name\": \"Transition\", \"size\": 9201},\n" +
            "    {\"name\": \"Transitioner\", \"size\": 19975},\n" +
            "    {\"name\": \"TransitionEvent\", \"size\": 1116},\n" +
            "    {\"name\": \"Tween\", \"size\": 6006}\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"data\",\n" +
            "   \"children\": [\n" +
            "    {\n" +
            "     \"name\": \"converters\",\n" +
            "     \"children\": [\n" +
            "      {\"name\": \"Converters\", \"size\": 721},\n" +
            "      {\"name\": \"DelimitedTextConverter\", \"size\": 4294},\n" +
            "      {\"name\": \"GraphMLConverter\", \"size\": 9800},\n" +
            "      {\"name\": \"IDataConverter\", \"size\": 1314},\n" +
            "      {\"name\": \"JSONConverter\", \"size\": 2220}\n" +
            "     ]\n" +
            "    },\n" +
            "    {\"name\": \"DataField\", \"size\": 1759},\n" +
            "    {\"name\": \"DataSchema\", \"size\": 2165},\n" +
            "    {\"name\": \"DataSet\", \"size\": 586},\n" +
            "    {\"name\": \"DataSource\", \"size\": 3331},\n" +
            "    {\"name\": \"DataTable\", \"size\": 772},\n" +
            "    {\"name\": \"DataUtil\", \"size\": 3322}\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"display\",\n" +
            "   \"children\": [\n" +
            "    {\"name\": \"DirtySprite\", \"size\": 8833},\n" +
            "    {\"name\": \"LineSprite\", \"size\": 1732},\n" +
            "    {\"name\": \"RectSprite\", \"size\": 3623},\n" +
            "    {\"name\": \"TextSprite\", \"size\": 10066}\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"flex\",\n" +
            "   \"children\": [\n" +
            "    {\"name\": \"FlareVis\", \"size\": 4116}\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"physics\",\n" +
            "   \"children\": [\n" +
            "    {\"name\": \"DragForce\", \"size\": 1082},\n" +
            "    {\"name\": \"GravityForce\", \"size\": 1336},\n" +
            "    {\"name\": \"IForce\", \"size\": 319},\n" +
            "    {\"name\": \"NBodyForce\", \"size\": 10498},\n" +
            "    {\"name\": \"Particle\", \"size\": 2822},\n" +
            "    {\"name\": \"Simulation\", \"size\": 9983},\n" +
            "    {\"name\": \"Spring\", \"size\": 2213},\n" +
            "    {\"name\": \"SpringForce\", \"size\": 1681}\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"query\",\n" +
            "   \"children\": [\n" +
            "    {\"name\": \"AggregateExpression\", \"size\": 1616},\n" +
            "    {\"name\": \"And\", \"size\": 1027},\n" +
            "    {\"name\": \"Arithmetic\", \"size\": 3891},\n" +
            "    {\"name\": \"Average\", \"size\": 891},\n" +
            "    {\"name\": \"BinaryExpression\", \"size\": 2893},\n" +
            "    {\"name\": \"Comparison\", \"size\": 5103},\n" +
            "    {\"name\": \"CompositeExpression\", \"size\": 3677},\n" +
            "    {\"name\": \"Count\", \"size\": 781},\n" +
            "    {\"name\": \"DateUtil\", \"size\": 4141},\n" +
            "    {\"name\": \"Distinct\", \"size\": 933},\n" +
            "    {\"name\": \"Expression\", \"size\": 5130},\n" +
            "    {\"name\": \"ExpressionIterator\", \"size\": 3617},\n" +
            "    {\"name\": \"Fn\", \"size\": 3240},\n" +
            "    {\"name\": \"If\", \"size\": 2732},\n" +
            "    {\"name\": \"IsA\", \"size\": 2039},\n" +
            "    {\"name\": \"Literal\", \"size\": 1214},\n" +
            "    {\"name\": \"Match\", \"size\": 3748},\n" +
            "    {\"name\": \"Maximum\", \"size\": 843},\n" +
            "    {\n" +
            "     \"name\": \"methods\",\n" +
            "     \"children\": [\n" +
            "      {\"name\": \"add\", \"size\": 593},\n" +
            "      {\"name\": \"and\", \"size\": 330},\n" +
            "      {\"name\": \"average\", \"size\": 287},\n" +
            "      {\"name\": \"count\", \"size\": 277},\n" +
            "      {\"name\": \"distinct\", \"size\": 292},\n" +
            "      {\"name\": \"div\", \"size\": 595},\n" +
            "      {\"name\": \"eq\", \"size\": 594},\n" +
            "      {\"name\": \"fn\", \"size\": 460},\n" +
            "      {\"name\": \"gt\", \"size\": 603},\n" +
            "      {\"name\": \"gte\", \"size\": 625},\n" +
            "      {\"name\": \"iff\", \"size\": 748},\n" +
            "      {\"name\": \"isa\", \"size\": 461},\n" +
            "      {\"name\": \"lt\", \"size\": 597},\n" +
            "      {\"name\": \"lte\", \"size\": 619},\n" +
            "      {\"name\": \"max\", \"size\": 283},\n" +
            "      {\"name\": \"min\", \"size\": 283},\n" +
            "      {\"name\": \"mod\", \"size\": 591},\n" +
            "      {\"name\": \"mul\", \"size\": 603},\n" +
            "      {\"name\": \"neq\", \"size\": 599},\n" +
            "      {\"name\": \"not\", \"size\": 386},\n" +
            "      {\"name\": \"or\", \"size\": 323},\n" +
            "      {\"name\": \"orderby\", \"size\": 307},\n" +
            "      {\"name\": \"range\", \"size\": 772},\n" +
            "      {\"name\": \"select\", \"size\": 296},\n" +
            "      {\"name\": \"stddev\", \"size\": 363},\n" +
            "      {\"name\": \"sub\", \"size\": 600},\n" +
            "      {\"name\": \"sum\", \"size\": 280},\n" +
            "      {\"name\": \"update\", \"size\": 307},\n" +
            "      {\"name\": \"variance\", \"size\": 335},\n" +
            "      {\"name\": \"where\", \"size\": 299},\n" +
            "      {\"name\": \"xor\", \"size\": 354},\n" +
            "      {\"name\": \"_\", \"size\": 264}\n" +
            "     ]\n" +
            "    },\n" +
            "    {\"name\": \"Minimum\", \"size\": 843},\n" +
            "    {\"name\": \"Not\", \"size\": 1554},\n" +
            "    {\"name\": \"Or\", \"size\": 970},\n" +
            "    {\"name\": \"Query\", \"size\": 13896},\n" +
            "    {\"name\": \"Range\", \"size\": 1594},\n" +
            "    {\"name\": \"StringUtil\", \"size\": 4130},\n" +
            "    {\"name\": \"Sum\", \"size\": 791},\n" +
            "    {\"name\": \"Variable\", \"size\": 1124},\n" +
            "    {\"name\": \"Variance\", \"size\": 1876},\n" +
            "    {\"name\": \"Xor\", \"size\": 1101}\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"scale\",\n" +
            "   \"children\": [\n" +
            "    {\"name\": \"IScaleMap\", \"size\": 2105},\n" +
            "    {\"name\": \"LinearScale\", \"size\": 1316},\n" +
            "    {\"name\": \"LogScale\", \"size\": 3151},\n" +
            "    {\"name\": \"OrdinalScale\", \"size\": 3770},\n" +
            "    {\"name\": \"QuantileScale\", \"size\": 2435},\n" +
            "    {\"name\": \"QuantitativeScale\", \"size\": 4839},\n" +
            "    {\"name\": \"RootScale\", \"size\": 1756},\n" +
            "    {\"name\": \"Scale\", \"size\": 4268},\n" +
            "    {\"name\": \"ScaleType\", \"size\": 1821},\n" +
            "    {\"name\": \"TimeScale\", \"size\": 5833}\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"util\",\n" +
            "   \"children\": [\n" +
            "    {\"name\": \"Arrays\", \"size\": 8258},\n" +
            "    {\"name\": \"Colors\", \"size\": 10001},\n" +
            "    {\"name\": \"Dates\", \"size\": 8217},\n" +
            "    {\"name\": \"Displays\", \"size\": 12555},\n" +
            "    {\"name\": \"Filter\", \"size\": 2324},\n" +
            "    {\"name\": \"Geometry\", \"size\": 10993},\n" +
            "    {\n" +
            "     \"name\": \"heap\",\n" +
            "     \"children\": [\n" +
            "      {\"name\": \"FibonacciHeap\", \"size\": 9354},\n" +
            "      {\"name\": \"HeapNode\", \"size\": 1233}\n" +
            "     ]\n" +
            "    },\n" +
            "    {\"name\": \"IEvaluable\", \"size\": 335},\n" +
            "    {\"name\": \"IPredicate\", \"size\": 383},\n" +
            "    {\"name\": \"IValueProxy\", \"size\": 874},\n" +
            "    {\n" +
            "     \"name\": \"math\",\n" +
            "     \"children\": [\n" +
            "      {\"name\": \"DenseMatrix\", \"size\": 3165},\n" +
            "      {\"name\": \"IMatrix\", \"size\": 2815},\n" +
            "      {\"name\": \"SparseMatrix\", \"size\": 3366}\n" +
            "     ]\n" +
            "    },\n" +
            "    {\"name\": \"Maths\", \"size\": 17705},\n" +
            "    {\"name\": \"Orientation\", \"size\": 1486},\n" +
            "    {\n" +
            "     \"name\": \"palette\",\n" +
            "     \"children\": [\n" +
            "      {\"name\": \"ColorPalette\", \"size\": 6367},\n" +
            "      {\"name\": \"Palette\", \"size\": 1229},\n" +
            "      {\"name\": \"ShapePalette\", \"size\": 2059},\n" +
            "      {\"name\": \"SizePalette\", \"size\": 2291}\n" +
            "     ]\n" +
            "    },\n" +
            "    {\"name\": \"Property\", \"size\": 5559},\n" +
            "    {\"name\": \"Shapes\", \"size\": 19118},\n" +
            "    {\"name\": \"Sort\", \"size\": 6887},\n" +
            "    {\"name\": \"Stats\", \"size\": 6557},\n" +
            "    {\"name\": \"Strings\", \"size\": 22026}\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"vis\",\n" +
            "   \"children\": [\n" +
            "    {\n" +
            "     \"name\": \"axis\",\n" +
            "     \"children\": [\n" +
            "      {\"name\": \"Axes\", \"size\": 1302},\n" +
            "      {\"name\": \"Axis\", \"size\": 24593},\n" +
            "      {\"name\": \"AxisGridLine\", \"size\": 652},\n" +
            "      {\"name\": \"AxisLabel\", \"size\": 636},\n" +
            "      {\"name\": \"CartesianAxes\", \"size\": 6703}\n" +
            "     ]\n" +
            "    },\n" +
            "    {\n" +
            "     \"name\": \"controls\",\n" +
            "     \"children\": [\n" +
            "      {\"name\": \"AnchorControl\", \"size\": 2138},\n" +
            "      {\"name\": \"ClickControl\", \"size\": 3824},\n" +
            "      {\"name\": \"Control\", \"size\": 1353},\n" +
            "      {\"name\": \"ControlList\", \"size\": 4665},\n" +
            "      {\"name\": \"DragControl\", \"size\": 2649},\n" +
            "      {\"name\": \"ExpandControl\", \"size\": 2832},\n" +
            "      {\"name\": \"HoverControl\", \"size\": 4896},\n" +
            "      {\"name\": \"IControl\", \"size\": 763},\n" +
            "      {\"name\": \"PanZoomControl\", \"size\": 5222},\n" +
            "      {\"name\": \"SelectionControl\", \"size\": 7862},\n" +
            "      {\"name\": \"TooltipControl\", \"size\": 8435}\n" +
            "     ]\n" +
            "    },\n" +
            "    {\n" +
            "     \"name\": \"data\",\n" +
            "     \"children\": [\n" +
            "      {\"name\": \"Data\", \"size\": 20544},\n" +
            "      {\"name\": \"DataList\", \"size\": 19788},\n" +
            "      {\"name\": \"DataSprite\", \"size\": 10349},\n" +
            "      {\"name\": \"EdgeSprite\", \"size\": 3301},\n" +
            "      {\"name\": \"NodeSprite\", \"size\": 19382},\n" +
            "      {\n" +
            "       \"name\": \"render\",\n" +
            "       \"children\": [\n" +
            "        {\"name\": \"ArrowType\", \"size\": 698},\n" +
            "        {\"name\": \"EdgeRenderer\", \"size\": 5569},\n" +
            "        {\"name\": \"IRenderer\", \"size\": 353},\n" +
            "        {\"name\": \"ShapeRenderer\", \"size\": 2247}\n" +
            "       ]\n" +
            "      },\n" +
            "      {\"name\": \"ScaleBinding\", \"size\": 11275},\n" +
            "      {\"name\": \"Tree\", \"size\": 7147},\n" +
            "      {\"name\": \"TreeBuilder\", \"size\": 9930}\n" +
            "     ]\n" +
            "    },\n" +
            "    {\n" +
            "     \"name\": \"events\",\n" +
            "     \"children\": [\n" +
            "      {\"name\": \"DataEvent\", \"size\": 2313},\n" +
            "      {\"name\": \"SelectionEvent\", \"size\": 1880},\n" +
            "      {\"name\": \"TooltipEvent\", \"size\": 1701},\n" +
            "      {\"name\": \"VisualizationEvent\", \"size\": 1117}\n" +
            "     ]\n" +
            "    },\n" +
            "    {\n" +
            "     \"name\": \"legend\",\n" +
            "     \"children\": [\n" +
            "      {\"name\": \"Legend\", \"size\": 20859},\n" +
            "      {\"name\": \"LegendItem\", \"size\": 4614},\n" +
            "      {\"name\": \"LegendRange\", \"size\": 10530}\n" +
            "     ]\n" +
            "    },\n" +
            "    {\n" +
            "     \"name\": \"operator\",\n" +
            "     \"children\": [\n" +
            "      {\n" +
            "       \"name\": \"distortion\",\n" +
            "       \"children\": [\n" +
            "        {\"name\": \"BifocalDistortion\", \"size\": 4461},\n" +
            "        {\"name\": \"Distortion\", \"size\": 6314},\n" +
            "        {\"name\": \"FisheyeDistortion\", \"size\": 3444}\n" +
            "       ]\n" +
            "      },\n" +
            "      {\n" +
            "       \"name\": \"encoder\",\n" +
            "       \"children\": [\n" +
            "        {\"name\": \"ColorEncoder\", \"size\": 3179},\n" +
            "        {\"name\": \"Encoder\", \"size\": 4060},\n" +
            "        {\"name\": \"PropertyEncoder\", \"size\": 4138},\n" +
            "        {\"name\": \"ShapeEncoder\", \"size\": 1690},\n" +
            "        {\"name\": \"SizeEncoder\", \"size\": 1830}\n" +
            "       ]\n" +
            "      },\n" +
            "      {\n" +
            "       \"name\": \"filter\",\n" +
            "       \"children\": [\n" +
            "        {\"name\": \"FisheyeTreeFilter\", \"size\": 5219},\n" +
            "        {\"name\": \"GraphDistanceFilter\", \"size\": 3165},\n" +
            "        {\"name\": \"VisibilityFilter\", \"size\": 3509}\n" +
            "       ]\n" +
            "      },\n" +
            "      {\"name\": \"IOperator\", \"size\": 1286},\n" +
            "      {\n" +
            "       \"name\": \"label\",\n" +
            "       \"children\": [\n" +
            "        {\"name\": \"Labeler\", \"size\": 9956},\n" +
            "        {\"name\": \"RadialLabeler\", \"size\": 3899},\n" +
            "        {\"name\": \"StackedAreaLabeler\", \"size\": 3202}\n" +
            "       ]\n" +
            "      },\n" +
            "      {\n" +
            "       \"name\": \"layout\",\n" +
            "       \"children\": [\n" +
            "        {\"name\": \"AxisLayout\", \"size\": 6725},\n" +
            "        {\"name\": \"BundledEdgeRouter\", \"size\": 3727},\n" +
            "        {\"name\": \"CircleLayout\", \"size\": 9317},\n" +
            "        {\"name\": \"CirclePackingLayout\", \"size\": 12003},\n" +
            "        {\"name\": \"DendrogramLayout\", \"size\": 4853},\n" +
            "        {\"name\": \"ForceDirectedLayout\", \"size\": 8411},\n" +
            "        {\"name\": \"IcicleTreeLayout\", \"size\": 4864},\n" +
            "        {\"name\": \"IndentedTreeLayout\", \"size\": 3174},\n" +
            "        {\"name\": \"Layout\", \"size\": 7881},\n" +
            "        {\"name\": \"NodeLinkTreeLayout\", \"size\": 12870},\n" +
            "        {\"name\": \"PieLayout\", \"size\": 2728},\n" +
            "        {\"name\": \"RadialTreeLayout\", \"size\": 12348},\n" +
            "        {\"name\": \"RandomLayout\", \"size\": 870},\n" +
            "        {\"name\": \"StackedAreaLayout\", \"size\": 9121},\n" +
            "        {\"name\": \"TreeMapLayout\", \"size\": 9191}\n" +
            "       ]\n" +
            "      },\n" +
            "      {\"name\": \"Operator\", \"size\": 2490},\n" +
            "      {\"name\": \"OperatorList\", \"size\": 5248},\n" +
            "      {\"name\": \"OperatorSequence\", \"size\": 4190},\n" +
            "      {\"name\": \"OperatorSwitch\", \"size\": 2581},\n" +
            "      {\"name\": \"SortOperator\", \"size\": 2023}\n" +
            "     ]\n" +
            "    },\n" +
            "    {\"name\": \"Visualization\", \"size\": 16540}\n" +
            "   ]\n" +
            "  }\n" +
            " ]\n" +
            "}";
}
