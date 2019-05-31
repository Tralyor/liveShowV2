<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="../common/header.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>考勤统计</title>
    <%@ include file="../common/resources.jsp" %>
    <link href="/static/plugins/bootstrap/file/css/fileinput.min.css" rel="stylesheet"/>
    <style>
        #main {
            position: absolute;
            top: 51px;
            left: 225px;
            width: 85%;

        }
        .live-event-chart{
            display: none;
            transition: all .2s linear;
        }
    </style>
</head>
<div id="main">
    <div id="mainBody">
        <c:forEach var="uClass" items="${userClass}">

            <div class=" col-sm-10" style="margin-top: 50px">
                <a class="btn btn-large btn-primary btn-block live-event" type="button" id="btn${uClass.id}">${uClass.className}</a>
                <div>
                    <div id="btn${uClass.id}Title" class="live-event-chart">
                        <center><h4 style="display: inline-block">应到次数：</h4><span>&nbsp;&nbsp;&nbsp;&nbsp;</span><h4 style="display: inline-block">实到次数：</h4></center>
                    </div>
                    <center><div id="btn${uClass.id}Info" class="live-event-chart" style="height: 400px">

                    </div></center>

                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="../common/topNav.jsp"></jsp:include>
<jsp:include page="../common/leftNav.jsp"></jsp:include>
<%@ include file="../common/resources-foot.jsp" %>
</body>
<script src="/static/js/plugins/echarts.min.js"/>
<script src="/static/js/plugins/jquery-3.2.1.min.js"></script>
<script src="/static/js/user/common.js"></script>
<script src="/static/plugins/bootstrap/file/js/fileinput.min.js"></script>
<script src="/static/plugins/bootstrap/file/js/locales/zh.js"></script>
<script>
    $(".live-event").click(function(){
        var id = $(this).attr("id");
        var btnId = "#"+id+"Info";
        var btnTitle= "#"+id +"Title"
        if  ($(btnId).css('display')!="none") {
            $(btnId).css('display','none')
            $(btnTitle).css('display','none')
        } else {
            $(btnId).css('display','block')
            $(btnTitle).css('display','block')
        }
        // 基于准备好的dom，初始化echarts实例
        $.ajax({
            url: "/quality/user",
            type: "post",
            data: "classId=" +id.substring(3,id.length),
            dataType: "json",
            success: function(show) {
                if(show.state == 1 || show.success) {
                    $(btnTitle).html("<center><h4 style=\"display: inline-block\">应到次数:"+show.data.totalAttendanceNum+"</h4><span>&nbsp;&nbsp;&nbsp;&nbsp;</span><h4 style=\"display: inline-block\">实到次数:"+show.data.successAttendaceNum+"</h4></center>")

                    echarts.dispose(document.getElementById(id+"Info"))
                    var myChart = echarts.init(document.getElementById(id+"Info"))

                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '人脸识别成功率曲线'
                        },
                        tooltip: {
                            trigger: 'axis',
                            formatter: function (params) {
                                var param = params[0];
                                var res='<div><p>成功率:'+param.data+'%</p></div>'
                                res = res + '<div><p>识别成功次数:' + show.data.recoSuccess[param.dataIndex] + '</p></div>'
                                res = res + '<div><p>识别总次数:' + show.data.recoCounts[param.dataIndex] + '</p></div>'

                                return res;
                            }
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                dataZoom: {
                                    yAxisIndex: 'none'
                                },
                                dataView: {readOnly: false},
                                magicType: {type: ['line', 'bar']},
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        xAxis:  {
                            type: 'category',
                            boundaryGap: false,
                            data: [],
                            axisLabel: {
                                formatter: '节第{value}课'
                            }
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}%'
                            }
                        },
                        series: [
                            {
                                name:'最高成功律',
                                type:'line',
                                data:[],
                                markPoint: {
                                    data: [
                                        {type: 'max', name: '最大值'},
                                        {type: 'min', name: '最小值'}
                                    ]
                                }
                            }
                        ]
                    };
                    option.xAxis.data = show.data.classNums;
                    option.series[0].data = show.data.rateNums;
                    console.log(option)
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option,true);
                }
            }
        });

    })
    $('.nav1-item>ul>li').css('background','none')
    $('#navStyle1').css('background','orange')
    $('#navStyle1>a').css('color','#FFF')
    $('#navStyle1>a').css('background','rgba(0,0,0,.1)')
</script>
</html>
