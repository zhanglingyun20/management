<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>设备数据统计</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${pageContext.request.contextPath}/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/css/page-min.css" rel="stylesheet" type="text/css" />   <!-- 下面的样式，仅是为了显示代码，而不应该在项目中使用-->
    <link href="${pageContext.request.contextPath}/assets/css/prettify.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="container">
    <div class="row">
        <form id="searchForm" class="form-horizontal span24">
        <input type="hidden" name ="deviceCode" value="${deviceCode}">
            <br class="row">
                <div class="control-group span10">
                    <label class="control-label">游戏名称：</label>
                    <div class="controls">
                        <input type="text" class="control-text" name="gameName">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">游戏编码：</label>
                    <div class="controls">
                        <input type="text" class="control-text" name="gameCode">
                    </div>
                </div>
                </br>
                <div class="control-group span8">
                    <label class="control-label">日期：</label>
                    <div class="controls">
                        <input type="text" class=" calendar" name="reportTime">
                    </div>
                </div>
                 <div class="span3 offset5">
                    <button  type="button" id="btnSearch" class="button button-primary">搜索</button>
                </div>
    </form>
</div>
<div class="search-grid-container">
    <div id="grid"></div>
</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bui-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/config-min.js"></script>
<script type="text/javascript">
    BUI.use('common/page');
</script>

<script type="text/javascript">
    BUI.use(['common/search','bui/overlay'],function (Search,Overlay) {
        /*       editing = new BUI.Grid.Plugins.DialogEditing({
         contentId : 'content', //设置隐藏的Dialog内容
         autoSave : true, //添加数据或者修改数据时，自动保存
         triggerCls : 'btn-edit',

         }), */
        columns = [
            {title:'游戏名称',dataIndex:'gameName',width:'20%'},
            {title:'游戏编号',dataIndex:'gameCode',width:'20%'},
            {title:'单价',dataIndex:'price',width:'20%'},
            {title:'运行次数',dataIndex:'runCount',width:'20%'},
            {title:'总销售额(元)',dataIndex:'salesAmount',width:'20%'}
        ],

                gridCfg = Search.createGridCfg(columns, {
                    tbar : {
                        items : [
                            {text : '<i class="icon-plus"></i>导出报表',btnCls : 'button button-small',handler:addFunction}
                        ]
                    }
                    // 插件形式引入多选表格
                });

        function addFunction() {
            window.location.href = "${pageContext.request.contextPath}/download/deviceSalesDetail?download_type=deviceSalesDetail&"+
                    +  $('#searchForm').serialize();
        }

        store = Search.createStore('device_sales_details_list',
                {
                    sortInfo : {
                        field : 'sales',
                        direction : 'desc'
                    },
                    autoLoad : true,
           		 	params : {
        			 deviceCode : '${deviceCode}'
        		  },
                    pageSize : 15,
                    proxy : {
                        method : 'post',
                        dataType : 'json'
                    }
                });
        var  search = new Search({
                    store : store,
                    gridCfg : gridCfg
                }),
                grid = search.get('grid');

    });
</script>
</body>
</html>
