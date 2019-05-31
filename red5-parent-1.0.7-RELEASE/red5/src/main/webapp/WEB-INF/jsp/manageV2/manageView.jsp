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
            transition: all .2s linear;
        }
    </style>
</head>
<div id="main">
    <div id="mainBody">

            <div class=" col-sm-10" style="margin-top: 50px">
                <a class="btn btn-large btn-primary btn-block live-event" type="button">课程考勤概览</a>
                <div>

                    <center><div id="btnInfo" class="live-event-chart" style="height: 400px">

                    </div></center>

                </div>
            </div>

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

        $.ajax({
            url: "/quality/managerRecordInfo",
            type: "post",
            data:"",
            dataType: "json",
            success: function(show) {
                if(show.state == 1 || show.success) {
                    echarts.dispose(document.getElementById("btnInfo"))
                    var myChart = echarts.init(document.getElementById("btnInfo"))

                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '概览'
                        },
                        tooltip: {
                            trigger: 'axis',
                            formatter: function (params) {
                                var param = params[0];
                                console.log(param);
                                var res='<div><p>成功率:'+param.data+'%</p></div>'
                                // res = res + '<div><p>识别成功次数:' + show.data.recoSuccess[param.dataIndex] + '</p></div>'
                                // res = res + '<div><p>识别总次数:' + show.data.recoCounts[param.dataIndex] + '</p></div>'

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
                                formatter: '{value}'
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
                                name:'最高',
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
                    option.xAxis.data = show.data.className;
                    option.series[0].data = show.data.rates;
                    console.log(option)
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option,true);
                }
            }
        });
</script>
</html>
