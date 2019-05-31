<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="../common/header.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>考勤明细</title>
    <%@ include file="../common/resources.jsp" %>
    <link href="/static/plugins/bootstrap/file/css/fileinput.min.css" rel="stylesheet"/>
    <style>
        #main {
            position: absolute;
            top: 51px;
            left: 225px;
            width: 85%;

        }
        .border {border-radius:10px;}

    </style>
</head>]
<body style="background-color: #00F7DE">
<div id="main">
    <div id="mainBody">
        <div class=" col-sm-10 border" style="margin-top: 50px;margin-left: 100px;background-color: white">
                <center>
                    <div id="attendanceInfo"  style="height: 400px; padding-top: 20px;padding-left: 20px">

                    </div>
                </center>
        </div>

        <div id="queryBox" class=" col-sm-10 border" style="background-color: white;margin-top: 50px;margin-bottom: 30px;margin-left: 100px;">
            <h4 style="padding-top:20px;padding-left: 20px"><strong>查看明细</strong></h4>
            <div>
                <div class="form-inline">
                    <div class="form-group" style="margin-left: 100px">
                        <label for="classId">课程编号</label>
                        <input type="text" class="form-control" id="classId" placeholder="输入课程编号">
                    </div>
                    <div class="form-group" style="margin-left: 50px">
                        <label for="classNum">课程节次</label>
                        <input type="text" class="form-control" id="classNum" placeholder="输入课程节次">
                    </div>
                    <button  style="margin-left: 50px" id="queryInfo" class="btn btn-default">查询</button>
                </div>
                <div style="margin-top: 20px">
                    <div class="col-sm-5" style="display: inline-block;margin-left: 100px">
                        <h5>未出勤名单</h5>
                        <div id="unAttendanceStu">
                            <table class="table table-striped" style="background-color: white" >
                                <tr>
                                    <th>学号</th>
                                    <th>姓名</th>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="col-sm-5" style="display: inline-block">
                        <h5>识别率低于80%名单：</h5>
                        <div id="faceAttendance">
                            <table class="table table-bordered" style="background-color: white">
                                <tr>
                                    <th>学号</th>
                                    <th>姓名</th>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/topNav.jsp"></jsp:include>
<jsp:include page="../common/leftNavTeach.jsp"></jsp:include>
<%@ include file="../common/resources-foot.jsp" %>
</body>
<script src="/static/js/plugins/echarts.min.js"/>
<script src="/static/js/plugins/jquery-3.2.1.min.js"></script>
<script src="/static/js/user/common.js"></script>
<script src="/static/plugins/bootstrap/file/js/fileinput.min.js"></script>
<script src="/static/plugins/bootstrap/file/js/locales/zh.js"></script>
<script>

    $.ajax({
        url: "/quality/teachView",
        type: "post",
        data: "classId=" +${classId},
        dataType: "json",
        success: function(show) {
            if(show.status == 1 || show.success) {

                var myChart = echarts.init(document.getElementById("attendanceInfo"))

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '课程统计'
                    },
                    tooltip: {
                        trigger: 'axis',
                        formatter: function (params) {
                            var param = params[0];
                            var param2 = params[1];
                            var res = '<div><p>考勤成功率:' + param.data + '%</p></div>';
                            res = res + '<div><p>考勤总数:' + show.data.attendanceCount[param.dataIndex] + '</p></div>';
                            res = res + '<div><p>实际考勤数:' + show.data.attendanceSuccess[param.dataIndex]+ '</p></div>';
                            res = res + '<div><p>人脸识别成功率:' + param2.data + '</p></div>';
                            res = res + '<div><p>人脸识别数:' + show.data.faceCount[param.dataIndex] + '</p></div>';
                            res = res + '<div><p>人脸识别成功数:' + show.data.faceSuccess[param.dataIndex] + '</p></div>';
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
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: show.data.classNums,
                        axisLabel: {
                            formatter: '第{value}节课'
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
                            name: '最高考勤率',
                            type: 'line',
                            data: show.data.attendanceRate,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            }
                        },
                        {
                            name: '最高人脸识别成功律',
                            type: 'line',
                            data: show.data.faceRate,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            }
                        }
                    ]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option, true);
            }
        }
    });

    $(document).on("click", "#queryInfo", function() {
        var loginName = $("#classId").val();
        var password = $("#classNum").val();

        $.ajax({
            url: "/quality/recordIndicator",
            type: "post",
            data: "classId=" + loginName + "&classNum=" + password,
            dataType: "json",
            success: function(show) {
                if(show.state == 0 || !show.success)
                {
                    layer.msg(show.message, {
                        icon: 1,
                        time: 2000
                    }, function()
                    {

                    });
                }
                else
                {
                    var astu = show.data.unInStudents;
                    var rstu = show.data.recoTusers;
                    var st1 = "<table class=\"table table-bordered\" style=\"background-color: white\"><tr><th>学号</th><th>姓名</th></tr>";
                    var st2 ="<table class=\"table table-bordered\" style=\"background-color: white\"><tr><th>学号</th><th>姓名</th></tr>";
                    for  (var i = 0 ; i < astu.length; i++ ) {
                        st1 = st1.concat(" <tr><td>"+astu[i].userId+"</td><td>"+astu[i].userName+"</td></tr>")
                    }
                    st1=st1.concat("</table>")
                    for  (var i = 0 ; i <rstu.length; i++ ) {
                        st2 = st2.concat(" <tr><td>"+rstu[i].userId+"</td><td>"+rstu[i].userName+"</td></tr>")
                    }
                    st2=st2.concat("</table>")
                    $('#unAttendanceStu').html(st1);
                    $('#faceAttendance').html(st2);

                }
            }
        });
    });
    $('.nav1-item>ul>li').css('background','none')
    $('#navStyle3').css('background','orange')
    $('#navStyle3>a').css('color','#FFF')
    $('#navStyle3>a').css('background','rgba(0,0,0,.1)')
</script>
</html>
