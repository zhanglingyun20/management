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
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">场地账号：</label>
                    <div class="controls">
                        <input type="text" class="control-text" name="account">
                    </div>
                </div>
                <div class="control-group span10">
                    <label class="control-label">场地名称：</label>
                    <div class="controls">
                        <input type="text" class="control-text" name="siteName">
                    </div>
                </div>
                <div class="control-group span10">
                    <label class="control-label">起始日期：</label>
                    <div class="controls bui-form-group" data-rules="{dateRange : true}">
                        <input name="startDate" data-tip="{text : '起始日期'}" class="input-small calendar" type="text"><label>&nbsp;-&nbsp;</label>
                        <input name="endDate" data-tip="{text : '结束日期'}"  class="input-small calendar" type="text">
                    </div>
                </div>
             <div class="span3 offset5">
                    <button  type="button" id="btnSearch" class="button button-primary">搜索</button>
                </div>
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
            {title:'场地账号',dataIndex:'account',width:'10%'},
            {title:'场地名称',dataIndex:'siteName',width:'10%'},
            {title:'总销售额(元)',dataIndex:'salesAmount',width:'20%'},
            {title:'场地销售详情',dataIndex:'salesAmount',width:'10%',renderer : function(value,obj){
                var str =  Search.createLink({ //链接使用 此方式
                    id : 'edit' + value,
                    title : '场地销售详情',
                    text : "场地销售详情",
                    href : 'sales/device_sales?account='+obj.account+"&endDate="+$("#endDate").attr("value")
                    +"&startDate="+$("#startDate").val()
                });
                return str;
            }}
        ],

                gridCfg = Search.createGridCfg(columns,{
                    /*  tbar : {
                     items : [
                     {text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:addFunction},
                     {text : '<i class="icon-remove"></i>激活',btnCls : 'button button-small',handler : activeFunction},
                     {text : '<i class="icon-remove"></i>禁用',btnCls : 'button button-small',handler : delFunction}
                     ]
                     }, */
                    /*  plugins : [editing,BUI.Grid.Plugins.CheckSelection,BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格 */
                });

        store = Search.createStore('site_sales_list',
                {
                    sortInfo : {
                        field : 'salesAmount',
                        direction : 'desc'
                    },
                    autoLoad : true,
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
