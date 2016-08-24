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
                <div class="control-group span8">
                    <label class="control-label">设备mac：</label>
                    <div class="controls">
                        <input type="text" class="control-text" name="deviceMac">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">设备编码：</label>
                    <div class="controls">
                        <input type="text" class="control-text" name="deviceCode">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">游戏编号：</label>
                    <div class="controls">
                        <input type="text" class="control-text" name="gameCode">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">游戏进程：</label>
                    <div class="controls">
                        <input type="text" class="control-text" name="gameProcess">
                    </div>
                </div>
                <div class="span3 offset5">
                    <button  type="button" id="btnSearch" class="button button-primary">搜索</button>
                </div>
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
            {title:'场地名称',dataIndex:'siteName',width:'10%'},
            {title:'场地级别',dataIndex:'siteLevel',width:'10%'},
            {title:'设备编号',dataIndex:'deviceCode',width:'10%'},
            {title:'设备名称',dataIndex:'deviceName',width:'10%'},
            {title:'设备mac',dataIndex:'deviceMac',width:'20%'},
            {title:'游戏进程',dataIndex:'gameProcess',width:'20%'},
            {title:'游戏名称',dataIndex:'gameName',width:'20%'},
            {title:'游戏编号',dataIndex:'gameCode',width:'20%'},
            {title:'运行次数',dataIndex:'runCount',width:'10%',renderer : function(value,obj){
                var str =  Search.createLink({ //链接使用 此方式
                    id : 'edit' + value,
                    title : '游戏当日运行次数',
                    text : value,
                    href : 'report/device_details?deviceCode='+obj.deviceCode+'&deviceMac='+obj.deviceMac+'&gameProcess='+obj.gameProcess+'&gameCode='+obj.gameCode+'&reportTime='+new Date(obj.reportTime).toLocaleDateString()
                });
                return str;
            }},
            {title:'运行日期',dataIndex:'reportTime',width:'20%',
                renderer : function(value)
                {
                    if (value!='') {return new Date(value).toLocaleDateString()}
                }
            }
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

        store = Search.createStore('report_game_list',
                {
                    sortInfo : {
                        field : 'runCount',
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
