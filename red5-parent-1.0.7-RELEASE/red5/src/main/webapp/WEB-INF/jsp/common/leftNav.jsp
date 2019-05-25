<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2017/12/4
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>、
<style>
    body,html{height: 100%;background: #E2E2E2}
    body,ul{margin:0;padding:0}
    body{font:14px "微软雅黑","宋体","Arial Narrow",HELVETICA;-webkit-text-size-adjust:100%;}
    .nav1 li{list-style:none}
    .nav1 a{text-decoration:none;}
    /* 以上实际使用若已初始化可删除 */

    /* nav1 */
    .nav1{margin-top:50px;width: 220px;height: 100%;background: #263238;transition: all .3s;z-index: unset;}
    .nav1 a{display: block;overflow: hidden;padding-left: 20px;line-height: 46px;max-height: 46px;color: #ABB1B7;transition: all .3s;}
    .nav1 a span{margin-left: 30px;}
    .nav1-item{position: relative;}
    .nav1-item.nav1-show{border-bottom: none;}
    .nav1-item ul{display: none;background: rgba(0,0,0,.1);}
    .nav1-item.nav1-show ul{display: block;}
    .nav1-item>a:before{content: "";position: absolute;left: 0px;width: 2px;height: 46px;background: #34A0CE;opacity:0;transition: all .3s;}
    .nav1 .nav1-icon{font-size: 20px;position: absolute;margin-left:-1px;}

    .nav1-show .nav1-more{transform:rotate(90deg);}
    .nav1-show,.nav1-item>a:hover{color: #FFF;background:rgba(0,0,0,.1);}
    .nav1-show>a:before,.nav1-item>a:hover:before{opacity:1;}
    .nav1-item li:hover a{color: #FFF;background: rgba(0, 0, 0,.1);}

    /* nav1-mini */
    .nav1-mini.nav1{width: 60px;}
    .nav1-mini.nav1 .nav1-icon{/* margin-left:-2px; */}
    .nav1-mini.nav1 .nav1-item>a span{display: none;}
    .nav1-mini.nav1 .nav1-more{margin-right: -20px;}
    .nav1-mini.nav1 .nav1-item ul{position: absolute;top:0px;left:60px;width: 180px;z-index: 99;background:#3C474C;overflow: hidden;}
    .nav1-mini.nav1 .nav1-item:hover{background:rgba(255,255,255,.1);}
    .nav1-mini.nav1 .nav1-item:hover .nav1-item a{color:#FFF;}
    .nav1-mini.nav1 .nav1-item:hover a:before{opacity:1;}
    .nav1-mini.nav1 .nav1-item:hover ul{display: block;}
</style>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jsp"%>
<script src="/static/js/plugins/jquery-3.2.1.min.js"></script>
<div class="nav1 navbar-fixed-top">

    <ul id="aat">

    </ul>
</div>
<script>

        var nav1List = [
            {
                "p" : "个人信息维护",
                "a" : "/userInfo/self"
            },
            {
                "p" : "考勤统计",
                "a":"/quality/self"
            },
            {
                "p" : "课程评价",
                "a":"/evaluate/stuView"
            }
        ]
        var nav1Data = '';
        for(let i=0;i<nav1List.length;i++){
            nav1Data+= "<li class='nav1-item' id='navStyle"+i+"'><a href='"+nav1List[i].a+"'><span>"+nav1List[i].p+"</span></a><li>"
        }
        console.log(nav1Data)
        $('#aat').html(nav1Data)


    $(function(){
        // nav1收缩展开
        $('#aat').on('click','.nav1-item>a',function(){
            if (!$('.nav1').hasClass('nav1-mini')) {
                if ($(this).next().css('display') == "none") {
                    //展开未展开
                    $('.nav1-item').children('ul').slideUp(300);
                    $(this).next('ul').slideDown(300);
                    $(this).parent('li').addClass('nav1-show').siblings('li').removeClass('nav1-show');
                }else{
                    //收缩已展开
                    $(this).next('ul').slideUp(300);
                    $('.nav1-item.nav1-show').removeClass('nav1-show');
                }
            }
        });
        // 标志点击样式
        $('#aat').on('click','.nav1-item>ul>li',function(){
            $('.nav1-item>ul>li').css('background','none')
            $(this).css('background','yellow')
//  	$(this).siblings().css('background','none')
        })
        //nav1-mini切换
        $('#mini').on('click',function(){
            if (!$('.nav1').hasClass('nav1-mini')) {
                $('.nav1-item.nav1-show').removeClass('nav1-show');
                $('.nav1-item').children('ul').removeAttr('style');
                $('.nav1').addClass('nav1-mini');
            }else{
                $('.nav1').removeClass('nav1-mini');
            }
        });
    });
</script>
