<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <head>
   <title>历史账单</title>
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
            <label class="control-label">游戏名称：</label>
            <div class="controls">
              <input type="text" class="control-text" name="gameName">
            </div>
          </div>
        </div>
       <div class="row">
          <div class="control-group span9">
            <label class="control-label">记账日期：</label>
            <div class="controls">
              <input type="text" class=" calendar" name="startTime"><span> - </span><input name="endTime" type="text" class=" calendar">
            </div>
          </div>
          <div class="span3 offset2">
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
 
	BUI.use([ 'common/search', 'bui/overlay' ], function(Search, Overlay) {
		editing = new BUI.Grid.Plugins.DialogEditing({
			contentId : 'content', //设置隐藏的Dialog内容
			autoSave : true, //添加数据或者修改数据时，自动保存
			triggerCls : 'btn-edit'
		}), 
		columns = [ 
		    {title : '序号',dataIndex : 'id',width : '5%'},
		    {title : '游戏名称',dataIndex : 'gameName',width : '15%'}, 
		    {title : '金额(元)',dataIndex : 'amount',width : '15%'}, 
		    {title : '记账人',dataIndex : 'userName',width : '15%'}, 
		    {title : '记账日期',dataIndex : 'billDate',width : '15%',
	        	  renderer : function(value)
	        	  {
	        		  if (value!='') {return new Date(value).toLocaleString ()}
	          	  }
		    }, 
		    {title : '录入时间',dataIndex : 'createTime',width : '15%',
	        	  renderer : function(value)
	        	  {
	        		  if (value!='') {return new Date(value).toLocaleString ()}
	          	  }
		    },
		    {title : '备注',dataIndex : 'remark',width : '25%'}
		],

		gridCfg = Search.createGridCfg(columns, {
			tbar : {
				items : [ {text : '所属场地：${siteName} -->所属设备：${deviceName}'}]
			},
			plugins : [ editing, BUI.Grid.Plugins.CheckSelection,
					BUI.Grid.Plugins.AutoFit ]
		// 插件形式引入多选表格
		});

		store = Search.createStore('bill_history_list', {
			sortInfo : {
				field : 'id',
				direction : 'desc'
			},
			autoLoad : true,
			 params : {
				 deviceGameId : '${deviceGameId}',
			},
			pageSize : 10,
			proxy : {
				method : 'post',
				dataType : 'json'
			}
		});
		var search = new Search({
			store : store,
			gridCfg : gridCfg
		}), grid = search.get('grid');
	});
</script>
</body>
</html>  
