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

  <div class="header">
      <div class="dl-title">
        <a href="http://www.twisin.com/" title="文档库地址" target="_blank"><!-- 仅仅为了提供文档的快速入口，项目中请删除链接 -->
          <span class="lp-title-port"></span><span class="dl-title-text">【VR金锐战队】</span>
        </a>
      </div>

    <div class="dl-log">欢迎您	<span class="dl-log-user">${user.account}</span><a href="sign_out" title="退出系统" class="dl-log-quit">[退出]</a><a href="http://www.twisin.com/" title="" class="dl-log-quit">VR金锐战队</a>
    </div>
  </div>
   <div class="content">
    <div class="dl-main-nav">
      <div class="dl-inform"><div class="dl-inform-title">贴心小秘书<s class="dl-inform-icon dl-up"></s></div></div>
      <ul id="J_Nav"  class="nav-list ks-clear">
      <!--   <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">首页</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-order">表单页</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-inventory">搜索页</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-supplier">详情页</div></li> -->
        <li class="nav-item"><div class="nav-item-inner nav-system">系统管理</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-report">数据统计</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-operate">运营管理</div></li>
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
        /*             {
          id:'menu', 
          homePage : 'code',
          menu:[{
              text:'首页内容w',
              items:[
                {id:'code',text:'首页代码',href:'main/code.html',closeable : false},
                {id:'main-menu',text:'顶部导航',href:'main/menu.html'},
                {id:'second-menu',text:'右边菜单',href:'main/second-menu.html'},
                {id:'dyna-menu',text:'动态菜单',href:'main/dyna-menu.html'}
              ]
            },{
              text:'页面操作',
              items:[
                {id:'operation',text:'页面常见操作',href:'main/operation.html'},
                {id:'quick',text:'页面操作快捷方式',href:'main/quick.html'}  
              ]
            },{
              text:'文件结构',
              items:[
                {id:'resource',text:'资源文件结构',href:'main/resource.html'},
                {id:'loader',text:'引入JS方式',href:'main/loader.html'}  
              ]
            }]
          },{
            id:'form',
            menu:[{
                text:'表单页面',
                items:[
                  {id:'code',text:'表单代码',href:'form/code.html'},
                  {id:'example',text:'表单示例',href:'form/example.html'},
                  {id:'introduce',text:'表单简介',href:'form/introduce.html'},
                  {id:'valid',text:'表单基本验证',href:'form/basicValid.html'},
                  {id:'advalid',text:'表单复杂验证',href:'form/advalid.html'},
                  {id:'remote',text:'远程调用',href:'form/remote.html'},
                  {id:'group',text:'表单分组',href:'form/group.html'},
                  {id:'depends',text:'表单联动',href:'form/depends.html'}
                ]
              },{
                text:'成功失败页面',
                items:[
                  {id:'success',text:'成功页面',href:'form/success.html'},
                  {id:'fail',text:'失败页面',href:'form/fail.html'}
                
                ]
              },{
                text:'可编辑表格',
                items:[
                  {id:'grid',text:'可编辑表格',href:'form/grid.html'},
                  {id:'form-grid',text:'表单中的可编辑表格',href:'form/form-grid.html'},
                  {id:'dialog-grid',text:'使用弹出框',href:'form/dialog-grid.html'},
                  {id:'form-dialog-grid',text:'表单中使用弹出框',href:'form/form-dialog-grid.html'}
                ]
              }]
          },{
            id:'search',
            menu:[{
                text:'搜索页面',
                items:[
                  {id:'code',text:'搜索页面代码',href:'search/code.html'},
                  {id:'example',text:'搜索页面示例',href:'search/example.html'},
                  {id:'example-dialog',text:'搜索页面编辑示例',href:'search/example-dialog.html'},
                  {id:'introduce',text:'搜索页面简介',href:'search/introduce.html'}, 
                  {id:'config',text:'搜索配置',href:'search/config.html'}
                ]
              },{
                text : '更多示例',
                items : [
                  {id : 'tab',text : '使用tab过滤',href : 'search/tab.html'}
                ]
              }]
          },{
            id:'detail',
            menu:[{
                text:'详情页面',
                items:[
                  {id:'code',text:'详情页面代码',href:'detail/code.html'},
                  {id:'example',text:'详情页面示例',href:'detail/example.html'},
                  {id:'introduce',text:'详情页面简介',href:'detail/introduce.html'}
                ]
              }]
          }, */
          {
              id:'system',
              menu:[{
                  text:'系统管理',
                  items:[
                    {id:'user',text:'用户管理',href:'user/index'}
                  ]
                }]
            },{
                id:'report',
                menu:[{
                    text:'数据统计',
                    items:[
                      /* {id:'site',text:'场地统计',href:'site'}, */
                      {id:'device',text:'设备统计',href:'report/device'}
                    ]
                  }]
              },{
                  id:'operate',
                  menu:[{
                      text:'运营管理',
                      items:[
                        {id:'game',text:'游戏管理',href:'game/index'},
                        {id:'site',text:'场地管理',href:'site/index'}
                      ]
                    }]
                }];
      new PageUtil.MainPage({
        modulesConfig : config
      });
    });
  </script>
 </body>
</html>
