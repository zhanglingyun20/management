<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title>VR金锐战队</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link href="${pageContext.request.contextPath}/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
   <link href="${pageContext.request.contextPath}/assets/css/main-min.css" rel="stylesheet" type="text/css" />
 </head>
 <body>
<div id="content" class="hide">
  	  <label class="control-label" id="error_message"><s></s></label>
      <form id="J_Form" class="form-horizontal" action="user/change_pwd">
       <input type="hidden" name="id" id ="J_id">
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>账号：</label>
            <div class="controls">
              <input name="account" id="account" type="text" data-rules="{required:true}" class="input-normal control-text">
            </div>
          </div>
        </div>
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>原始密码：</label>
            <div class="controls">
              <input name="password" id="password" type="password" data-rules="{required:true}" class="input-normal control-text">
            </div>
          </div>
        </div>
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>新密码：</label>
            <div class="controls">
              <input name="newPwd" id="newPwd"  type="password" data-rules="{required:true}" class="input-normal control-text">
            </div>
          </div>
        </div>
       <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>重复新密码：</label>
            <div class="controls">
              <input name ="newPwdConfirm" id="newPwd_confirm"  type="password" data-rules="{required:true}" class="input-normal control-text">
            </div>
          </div>
        </div>       
      </form>
    </div>
  <div class="header">
      <div class="dl-title">
        <a href="http://www.twisin.com/" title="文档库地址" target="_blank"><!-- 仅仅为了提供文档的快速入口，项目中请删除链接 -->
          <span class="lp-title-port"></span><span class="dl-title-text">【VR金锐战队】</span>
        </a>
      </div>

    <div class="dl-log"><span  ><a href="javascript:void(0)" class="dl-log-quit" id="change_pwd">修改密码</a></span> 欢迎您	<span class="dl-log-user">${user.account}</span><a href="sign_out" title="退出系统" class="dl-log-quit">[退出]</a><a href="http://www.twisin.com/" title="" class="dl-log-quit">VR金锐战队</a>
    </div>
  </div>
   <div class="content">
    <div class="dl-main-nav">
      <div class="dl-inform"><div class="dl-inform-title">贴心小秘书<s class="dl-inform-icon dl-up"></s></div></div>
      <ul id="J_Nav"  class="nav-list ks-clear">
        <% 
        	String userType = (String)session.getAttribute("userType");
        	if(userType.equals("system")) 
        	{
        %>
        	<li class="nav-item"><div class="nav-item-inner nav-system">系统管理</div></li>
        <% 
        	}
        %>
        
        <% 
        	if(userType.equals("system")||userType.equals("shareholders")) 
        	{
        %>
        	 <li class="nav-item"><div class="nav-item-inner nav-report">股东中心</div></li>
        <% 
        	}
        %>
       	 <li class="nav-item"><div class="nav-item-inner nav-operate">运营管理</div></li>
        <% 
        	if(userType.equals("system")||userType.equals("shareholders")) 
        	{
        %>
        	 <li class="nav-item"><div class="nav-item-inner nav-monitor">监控管理</div></li>
        <% 
        	}
        %>
      
      </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">
    </ul>
   </div>
  <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.8.1.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bui.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/config.js"></script>

  <script>
    BUI.use('common/main',function(){
      var config = [
              <% 
              	if(userType.equals("system")||userType.equals("shareholders")) 
              	{
              %>
	              {
	                  id:'system',
	                  menu:[{
	                      text:'系统管理',
	                      items:[
	                        {id:'user',text:'用户管理',href:'user/index'}
	                      ]
	                    }]
	              },
              <% 
              	}
              %>         
	          <% 
	          	if(userType.equals("system")||userType.equals("shareholders")) 
	          	{
	          %>
		          {
		              id:'report',
		              menu:[
		                {
		                  text:'销售额统计',
		                  items:[
		                    /* {id:'site',text:'场地统计',href:'site'}, */
		                    /* {id:'all_price',text:'总销售额',href:'report/all'},
		                    {id:'province_price',text:'省份销售额',href:'report/province'},
		                    {id:'city_price',text:'城市销售额',href:'report/city'}, */
		                    {id:'site_sales',text:'场地销售额',href:'sales/site_sales'},
		                    {id:'device_sales',text:'设备销售额',href:'sales/device_sales'},
		                    {id:'compare_bill',text:'账单对比',href:'sales/compare_bill'},
		                  ]
		                }, 
		                {
			                text:'运行统计',
				                items:[
				                    /* 1可以根据省城市场地和设备对游戏运行情况进行统计，以便人工来对账
				                    *  2二级菜单为 游戏运行细节，具体多少次，每次的运行时长
				                    *  3这是
				                    * */
				                    {id:'report_game',text:'游戏运行统计',href:'report/report_game'},
				
				                ]
				         }
		              ]
		            },
	          <% 
	          	}
	          %>
         {
             id:'operate',
             menu:[{
                 text:'运营管理',
                 items:[
                     <% 
                    	if(userType.equals("system")||userType.equals("shareholders")) 
                    	{
                    %>
	                    {id:'game',text:'游戏管理',href:'game/index'},
	                    {id:'site',text:'场地管理',href:'site/index'},
                    <% 
                    	}
                    %>
                   {id:'bill',text:'记账',href:'bill/index'}
                   /* {id:'device_game',text:'设备游戏管理',href:'device_game/index'} */
                   /* {id:'price',text:'游戏定价',href:'game/price'}, */
                 ]
               }]
           }, 
	       <% 
	       	if(userType.equals("system")||userType.equals("shareholders")) 
	       	{
	       %>
	          {
	              id:'monitor',
	              menu:[{
	                  text:'监控管理',
	                  items:[
	                      {id:'map',text:'场地分布',href:'monitor/map'}

	                  ]
	              }]
	          }
	       <% 
	       	}
	       %> 

      ];
      new PageUtil.MainPage({
        modulesConfig : config
      });
    });
    
    BUI.use('bui/overlay',function(Overlay){
	  	var dialog = new Overlay.Dialog({
		      title:'修改密码',
		      contentId:'content',
		      success:function () {
		    	form && form.submit();
		        /* this.close(); */
		        return false;
		      }
		});
	  	
		var Overlay = BUI.Overlay,
		Form = BUI.Form;
		var form = new Form.HForm({
		  srcNode : '#J_Form',
	      submitType : 'ajax',
	      callback : function(data){
	        if(data.code=='success'){
	        	 dialog.close();
	        	 window.location.href='sign_out';
	        }else
	        {
	        	BUI.Message.Alert(data.message);
	        	return false;
	        }
	      }
		}).render();
		
        $('#change_pwd').on('click',function () {
            dialog.show();
        });
    });

  </script>
 </body>
</html>
