<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
 <head>
   <title>记账列表</title>
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
            <label class="control-label">记账日期：</label>
            <div class="controls">
             <input type="text" class=" calendar" name="queryDate">
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
  
  
  <div id="update_remark" class="hide">
  	  <label class="control-label" id="error_message"><s></s></label>
      <form id="J_Form_update" class="form-horizontal" action="update_remark">
      <input type="hidden" name="id" id ="J_id">
       <div class="row">
          <div class="control-group span15">
            <label class="control-label">备注：</label>
            <div class="controls control-row4">
              <textarea id="J_remark" name="remark" class="input-large" type="text" maxlength="250"></textarea>
            </div>
          </div>
	  </div>
      </form>
    </div>
    
    
    
    <div id="content" class="hide">
  	  <label class="control-label" id="error_message"><s></s></label>
      <form id="J_Form" class="form-horizontal" action="add_site_bill">
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
			    {title : '场地名称',dataIndex : 'siteName',width : '15%'}, 
			    {title : '金额(元)',dataIndex : 'amount',width : '15%'}, 
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
			    {title : '备注',dataIndex : 'remark',width : '25%'},
		         <% 
		         	String userType = (String)session.getAttribute("userType");
		        	if(userType.equals("site")) 
		        	{
		        %>
				    {title : '修改备注',
				    	renderer : function() {
						return '<span class="grid-command update-remark">修改备注</span>';
					
				    	}
				    }
		        <% 
		        	}
		        %>

		],

		gridCfg = Search.createGridCfg(columns, {
			tbar : {
				items : [ 
				         <% 
				        	if(userType.equals("site")) 
				        	{
				        %>
				        	{text : '<i class="icon-plus"></i>记账',btnCls : 'button button-small',handler:addFunction}
				        <% 
				        	}
				        %>
				         
				        ]
			},
			plugins : [ editing, BUI.Grid.Plugins.CheckSelection,
					BUI.Grid.Plugins.AutoFit ]
		// 插件形式引入多选表格
		});

		store = Search.createStore('site_bill_list', {
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
			if (target.hasClass('update-remark')) {
				$("#J_remark").val(record.remark);
				$("#J_id").val(record.id);
				updateRemark();
			}
		});

		function addFunction() {
			var type = '${user.userType}';
			if("site"!=type){
				BUI.Message.Alert("场地账号才能记账");
				return ;
			}
			dialog.show();
		}
		
		function updateRemark() {
			update_dialog.show();
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
	
	
	var update_dialog = new Overlay.Dialog({
		title : '修改备注',
		contentId : 'update_remark',
		success : function() {
			form_update && form_update.submit();
			/* this.close(); */
			return false;
		}
	});
	
	
	var Overlay = BUI.Overlay, Form = BUI.Form;
	var form_update = new Form.HForm({
		srcNode : '#J_Form_update',
		submitType : 'ajax',
		callback : function(data) {
			if (data.code == 'success') {
				update_dialog.close();
				search.load();
			} else {
				$("span[id='error_messge']").val(data.messge);
				BUI.Message.Alert(data.message);
				return false;
			}
		}
	}).render();
</script>
</body>
</html>  
