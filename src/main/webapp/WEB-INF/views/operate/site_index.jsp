<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <head>
   <title>场地列表</title>
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
            <label class="control-label">用户账号：</label>
            <div class="controls">
              <input type="text" class="control-text" name="account">
            </div>
          </div>
        </div>
        <div class="row">
	          <div class="control-group span15">
	            <label class="control-label">所在地：</label>
	            <div class="controls bui-form-group-select"  data-type="city">
	              <select  class="input-small" name="province">
	                <option value="">请选择</option>
	              </select>
	              <select class="input-small"  name="city" ><option value="">请选择</option></select>
	              <select class="input-small"  name="county" ><option value="">请选择</option></select>
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
      <form id="J_Form" class="form-horizontal" action="add">
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>游戏名称：</label>
            <div class="controls">
              <input name="gameName" type="text" data-rules="{required:true}" class="input-normal control-text">
            </div>
          </div>
        </div>
        
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>游戏进程：</label>
            <div class="controls">
              <input name="gameProcess" type="text" data-rules="{required:true}" class="input-normal control-text">
            </div>
          </div>
        </div>
        
                
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>游戏版本：</label>
            <div class="controls">
              <input name="gameVersion" type="text" data-rules="{required:true}" class="input-normal control-text">
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
  BUI.use(['common/search','bui/overlay'],function (Search,Overlay) {
      editing = new BUI.Grid.Plugins.DialogEditing({
          contentId : 'content', //设置隐藏的Dialog内容
          autoSave : true, //添加数据或者修改数据时，自动保存
          triggerCls : 'btn-edit',
        
        }),
      columns = [
		  {title:'序号',dataIndex:'id',width:'10%'},
          {title:'场地账号',dataIndex:'account',width:'20%'},
          {title:'场地名称',dataIndex:'siteName',width:'20%'},
          {title:'所属省',dataIndex:'province',width:'20%'},
          {title:'所属市',dataIndex:'city',width:'20%'},
          {title:'所属区',dataIndex:'county',width:'20%'}
/*           {title:'已定价游戏',dataIndex:'fixPriceGameCount',width:'10%',renderer : function(value,obj){
              var str =  Search.createLink({ //链接使用 此方式
                  id : 'edit' + obj.id,
                  title : '编辑学生信息',
                  text : '编辑',
                  href : 'search/edit.html'
                });
              return str;
          }}, */
/*           {title:'已定价游戏',dataIndex:'fixPriceGameCount',width:'20%'},
          {title:'游戏状态',dataIndex:'state',width:'20%',
        	  renderer : function(value)
        	  {
        		  if ('active'==value) {return "正常"}
        		  if ('forbidden'==value) {return "禁用"}
          	  }
          } */
        ],

        gridCfg = Search.createGridCfg(columns,{
            tbar : {
              items : [
                /*{text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:addFunction} ,
                {text : '<i class="icon-remove"></i>激活',btnCls : 'button button-small',handler : activeFunction},
                {text : '<i class="icon-remove"></i>禁用',btnCls : 'button button-small',handler : delFunction} */
              ]
            },
            plugins : [editing,BUI.Grid.Plugins.CheckSelection,BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
          });
      
		store = Search.createStore('site_list',
		{
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
    var  search = new Search({
        store : store,
        gridCfg : gridCfg
      }),
      grid = search.get('grid');
    
    function addFunction(){
    	dialog.show();
    }
    
    //删除操作
    function delFunction(){
      var selections = grid.getSelection();
      delItems(selections,"forbidden");
    }
    
    function activeFunction(){
        var selections = grid.getSelection();
        delItems(selections,"active");
      }

    function delItems(items,state){
      var ids = [];
      BUI.each(items,function(item){
        ids.push(item.id);
      });
      if(ids.length){
        BUI.Message.Confirm('确认要禁用选中的记录么？',function(){
          $.ajax({
            url : 'update_state',
            dataType : 'json',
            type : 'post',
            data : {"ids" : ids.join(","),"state":state},
            success : function(data){
              if(data.success){ //删除成功
                search.load();
              }else{ //删除失败
                BUI.Message.Alert('删除失败！');
              }
            }
        });
        },'question');
      }
    }

    //监听事件，删除一条记录
    grid.on('cellclick',function(ev){
      var sender = $(ev.domTarget); //点击的Dom
      if(sender.hasClass('btn-del')){
        var record = ev.record;
        delItems([record]);
      }
    });
    
  });
  
	var Overlay = BUI.Overlay,
	Form = BUI.Form;
	var form = new Form.HForm({
	  srcNode : '#J_Form',
      submitType : 'ajax',
      callback : function(data){
        if(data.code=='success'){
        	 dialog.close();
        	 search.load();
        }else
        {
        	$("span[id='error_messge']").val(data.messge);
        	BUI.Message.Alert(data.message);
        	 return false;
        }
      }
	}).render();
	
	var dialog = new Overlay.Dialog({
	      title:'新增用户',
	      contentId:'content',
	      success:function () {
	    	form && form.submit();
	        /* this.close(); */
	        return false;
	      }
	    });
</script>
</body>
</html>  
