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
        <li class="nav-item"><div class="nav-item-inner nav-system">系统管理</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-report">股东中心</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-operate">运营管理</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-operate">监控管理</div></li>
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
          {
              id:'system',
              menu:[{
                  text:'系统管理',
                  items:[
                    {id:'user',text:'用户管理',href:'user/index'}
                  ]
                }]
          },
          {
              id:'report',
              menu:[{
                  text:'销售额统计',
                  items:[
                    /* {id:'site',text:'场地统计',href:'site'}, */
                    {id:'all_price',text:'总销售额',href:'report/all'},
                    {id:'province_price',text:'省份销售额',href:'report/province'},
                    {id:'city_price',text:'城市销售额',href:'report/city'},
                    {id:'site_sales',text:'场地销售额',href:'sales/site_sales'},
                    {id:'device_sales',text:'设备销售额',href:'sales/device_sales'}

                  ]
                }, {
                text:'运行统计',
                items:[
                    /* 1可以根据省城市场地和设备对游戏运行情况进行统计，以便人工来对账
                    *  2二级菜单为 游戏运行细节，具体多少次，每次的运行时长
                    *  3这是
                    * */
                    {id:'report_game',text:'游戏运行统计',href:'report/report_game'},

                ]
            }]
            },
         {
             id:'operate',
             menu:[{
                 text:'运营管理',
                 items:[
                   {id:'game',text:'游戏管理',href:'game/index'},
                   {id:'site',text:'场地管理',href:'site/index'},
                   {id:'price',text:'游戏定价',href:'game/price'},
                 ]
               }]
           }, 
          {
              id:'monitor',
              menu:[{
                  text:'监控管理',
                  items:[
                      {id:'map',text:'场地分布',href:'monitor/map'}

                  ]
              }]
          }

      ];
      new PageUtil.MainPage({
        modulesConfig : config
      });
    });
  </script>
 </body>
</html>
