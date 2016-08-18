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
  </script> 
<script type="text/javascript">
  BUI.use(['common/search','bui/overlay'],function (Search,Overlay) {
/*       editing = new BUI.Grid.Plugins.DialogEditing({
          contentId : 'content', //设置隐藏的Dialog内容
          autoSave : true, //添加数据或者修改数据时，自动保存
          triggerCls : 'btn-edit',
        
        }), */
      columns = [
		  {title:'序号',dataIndex:'id',width:'10%'},
		  {title:'设备编号',dataIndex:'deviceCode',width:'10%'},
          {title:'设备mac',dataIndex:'deviceMac',width:'20%'},
          {title:'游戏进程',dataIndex:'gameProcess',width:'20%'},
          {title:'游戏编号',dataIndex:'gameCode',width:'20%'},
          {title:'运行次数',dataIndex:'runCount',width:'10%'},
          {title:'上报时间',dataIndex:'createTime',width:'20%',
        	  renderer : function(value)
        	  {
        		  if (value!='') {return new Date(value).toLocaleString ()}
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
      
		store = Search.createStore('details_list',		
		{
		    sortInfo : {
		    field : 'runCount',
		    direction : 'desc'
		 },
		 autoLoad : true,
		 params : {
			 deviceCode : '${record.deviceCode}',
			 deviceMac : '${record.deviceMac}',
			 gameCode : '${record.gameCode}',
			 gameProcess : '${record.gameProcess}',
			 reportTime : '${record.reportTime}'
			 
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
<body>
</html>  
