<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <head>
   <title>设备游戏列表</title>
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
            <label class="control-label">场地名称：</label>
            <div class="controls">
              <input type="text" class="control-text" name="siteName">
            </div>
          </div>
          
          <div class="control-group span8">
            <label class="control-label">游戏名称：</label>
            <div class="controls">
              <input type="text" class="control-text" name="gameName">
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label">设备名称：</label>
            <div class="controls">
              <input type="text" class="control-text" name="deviceName">
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
  
  
  <div id="content" class="hide">
  	  <label class="control-label" id="error_message"><s></s></label>
      <form id="J_Form" class="form-horizontal" action="add_bill">
      <input type="hidden" name="deviceGameId" id="deviceGameId">
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>记账金额：</label>
            <div class="controls">
               <input type="text" class="control-text" name="amountStr"  data-rules="{number:true}">
            </div>
          </div>
        </div>
        
       <div class="row">
	        <div class="control-group span8">
	        	  <label class="control-label">记账日期：</label>
	          <div class="controls">
	           <input id="date" type="text" class="calendar calendar-time" name="date"/>
	          </div>
			</div>
        </div>
        
       <div class="row">
          <div class="control-group span15">
            <label class="control-label">备注：</label>
            <div class="controls control-row4">
              <textarea name="remark" class="input-large" type="text" maxlength="250"></textarea>
            </div>
          </div>
	  </div>
      </form>
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
		    {title : '场地名称',dataIndex : 'siteName',width : '20%'}, 
		    {title : '设备名称',dataIndex : 'deviceName',width : '20%'}, 
		    {title : '游戏名称',dataIndex : 'gameName',width : '15%'}, 
		    {title : '游戏编码',dataIndex : 'gameCode',width : '10%'}, 
		    {title : '记账',
		    	renderer : function() {
				return '<span class="grid-command add_bill">记账</span>';
			
		    	}
		    }, 
            {title:'历史记账',dataIndex:'deviceName',width:'10%',renderer : function(value,obj){
                var str =  Search.createLink({ //链接使用 此方式
                    id : 'edit' + value,
                    title : '历史记账',
                    text : "历史记账",
                    href : 'device_game/bill_history?deviceGameId='+obj.id+'&deviceName='+obj.deviceName+'&siteName='+obj.siteName
                });
                return str;
            }}
		],

		gridCfg = Search.createGridCfg(columns, {
			tbar : {
				items : []
			},
			plugins : [ editing, BUI.Grid.Plugins.CheckSelection,
					BUI.Grid.Plugins.AutoFit ]
		// 插件形式引入多选表格
		});

		store = Search.createStore('device_game_list', {
			sortInfo : {
				field : 'id',
				direction : 'desc'
			},
			autoLoad : true,
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

		grid.on('cellclick', function(ev) {
			var record = ev.record, //点击行的记录
			field = ev.field, //点击对应列的dataIndex
			target = $(ev.domTarget); //点击的元素
			$("#deviceGameId").val(record.id);
			if (target.hasClass('add_bill')) {
				addFunction();
			}
		});

		function addFunction() {
			dialog.show();
		}

	});

	var Overlay = BUI.Overlay, Form = BUI.Form;
	var form = new Form.HForm({
		srcNode : '#J_Form',
		submitType : 'ajax',
		callback : function(data) {
			if (data.code == 'success') {
				dialog.close();
				search.load();
			} else {
				$("span[id='error_messge']").val(data.messge);
				BUI.Message.Alert(data.message);
				return false;
			}
		}
	}).render();
	var dialog = new Overlay.Dialog({
		title : '记账',
		contentId : 'content',
		success : function() {
			form && form.submit();
			/* this.close(); */
			return false;
		}
	});
</script>
</body>
</html>  
