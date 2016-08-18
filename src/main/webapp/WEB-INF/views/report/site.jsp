<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <head>
   <title>场地数据统计</title>
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
            <label class="control-label">账号：</label>
            <div class="controls">
              <input type="text" class="control-text" name="account">
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label">用户名：</label>
            <div class="controls">
              <input type="text" class="control-text" name="username">
            </div>
          </div>
        </div>
        <div class="row">
          <div class="control-group span8">
            <label class="control-label">状态：</label>
            <div class="controls" >
              <select  id="state" name="state">
                <option value="normal">正常</option>
                <option value="forbidden">禁用</option>
              </select>
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
  </script> 
<script type="text/javascript">
  BUI.use(['common/search','bui/overlay'],function (Search,Overlay) {
      columns = [
          {title:'序号',dataIndex:'id',width:'10%',renderer:function(v){
            return Search.createLink({
              id : 'detail' + v,
              title : '账号信息',
              text : v,
              href : 'detail/example.html'
            });
          }},
          {title:'',dataIndex:'account',width:'20%'},
          {title:'用户名',dataIndex:'username',width:'20%'},
          {title:'账号状态',dataIndex:'state',width:'20%',
        	  renderer : function(value)
        	  {
        		  if ('normal'==value) {return "正常"}
        		  if ('forbidden'==value) {return "禁用"}
          	  }
          },
          {title:'账号类型',dataIndex:'userType',width:'20%',
        	  renderer : function(value)
        	  {
        		  if ('system'==value) {return "管理员"}
        		  if ('site'==value) {return "场地账号"}
          	  }
          },
          {title:'操作',dataIndex:'',width:'30%',renderer : function(value,obj){
            var editStr =  Search.createLink({ //链接使用 此方式
                id : 'edit' + obj.id,
                title : '编辑学生信息',
                text : '编辑',
                href : 'search/edit.html'
              }),
              delStr = '<span class="grid-command btn-del" title="删除学生信息">删除</span>';//页面操作不需要使用Search.createLink
            return editStr + delStr;
          }}
        ],

        gridCfg = Search.createGridCfg(columns,{
            tbar : {
              items : [
                {text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:addFunction},
                {text : '<i class="icon-edit"></i>编辑',btnCls : 'button button-small',handler:function(){alert('编辑');}},
                {text : '<i class="icon-remove"></i>删除',btnCls : 'button button-small',handler : delFunction}
              ]
            },
            plugins : [editing,BUI.Grid.Plugins.CheckSelection,BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
          });
      
		store = Search.createStore('list',
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
      delItems(selections);
    }

    function delItems(items){
      var ids = [];
      BUI.each(items,function(item){
        ids.push(item.id);
      });

      if(ids.length){
        BUI.Message.Confirm('确认要删除选中的记录么？',function(){
          $.ajax({
            url : '../data/del.php',
            dataType : 'json',
            data : {ids : ids},
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
  
  
  $(function(){
	  $("select[name='userType']").change(function(){
		  var $_type = $(this).val();
		  if('site'==$_type){
			  showSite();
		  }else{
			  removeSite();
		  }
	  });
  });
  
  function showSite(){
	  $("div[id='site_div']").show();
  }
  
  function removeSite(){
	  $("div[id='site_div']").hide();
  }
  

	var Overlay = BUI.Overlay,
	Form = BUI.Form;
	var form = new Form.HForm({
	  srcNode : '#J_Form',
      submitType : 'ajax',
      callback : function(data){
        if(data.code=='success'){
        	 dialog.close();
        	 window.location.reload();
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
<body>
</html>  
