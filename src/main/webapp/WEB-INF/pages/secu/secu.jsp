<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>洞察-东方财富网</title>
<style type="text/css">
</style>
</head>
<body>
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li class="active">东方财富网</li>
			</ul>
			<!-- .breadcrumb -->
			<div class="nav-search" id="nav-search">
				<form class="form-search">
					<span class="input-icon"> <input type="text"
						placeholder="Search ..." class="nav-search-input"
						id="nav-search-input" autocomplete="off" /> <i
						class="icon-search nav-search-icon"></i>
					</span>
				</form>
			</div>
		</div>                                  
	</div>
	<div class="container">
		<div class="list_main">
			<form class="form-horizontal" role="form">
				<div class="col-xs-14 col-sm-10">
					<div class="input-group" style="margin-top: 20px;display: -webkit-box;">
						<input type="text" class="form-control search-query"
							placeholder="查询多少条数据(2975)" id="pageSize" name="pageSize"
							style="padding-left:10px;background-color: #CCE8CF;width: 160px;"><span class="input-group-btn"
							style="margin-top: 15px;width: inherit;">
							<button type="button" class="btn btn-purple btn-sm"
								id="btnFeedback">
								抓取<sup style="color: pink;">[主]</sup><i
									class="icon-cloud-download icon-on-right bigger-110"></i>
							</button>&nbsp;&nbsp;
							<button type="button" class="btn btn-grass btn-sm" id="btnFeed">
								抓取<sup style="color: green;">[次]</sup> <i
									class="icon-cloud-download icon-on-right bigger-110"></i>
							</button>
						</span>
						<input id="sssssssss" class="form-control" data-date-format="yy-mm-dd" style="width: 100px;">
						<font style="color: red;font-weight: bold;">（选择时间查询）</font>
					</div>
				</div>
				<!-- <div class="col-xs-12 col-sm-1">
					<button class="btn btn-primary btn-sm" id="gritter-center"
						style="margin-top: 20px;">测试提示</button>
				</div> -->
				<div class="col-xs-14 col-sm-2">
					<h3 style="margin-bottom: 10px;display:none;" id="yanchi">
						<small style="color: red;font-size: 12px">正b在执行...&nbsp;
							&nbsp; &nbsp; </small><i class="icon-spinner icon-spin orange bigger-600"
							style="font-size: 20"></i>
					</h3>
				</div>
				<table class="table table-bordered" style="width: 50%;">
					<tbody id="main_tbody">
						
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<script src="${ctxStatic }/assets/js/ace-elements.min.js"></script>
	<script src="${ctxStatic }/assets/js/ace.min.js"></script>
	<script src="${ctxStatic }/assets/js/jquery-ui-1.10.3.full.min.js"></script>
	<script src="${ctxStatic }/assets/js/jquery.gritter.min.js"></script>
	<script type="text/javascript">
		$(function() {
			//查询数据
			var pageNo = 1;
			$("#sssssssss").datepicker({
				showOtherMonths : true,
				selectOtherMonths : true,
				dateFormat : "yy-mm-dd"
			});
			$("#sssssssss").change(function() {
				search();
			});
			$("#search").bind("click", function() {
				search();
			});
			$("#jisuan").bind("click", function() {
				var lc = $("#lc").val();
				var dj = $("#dj").val();
				var yh = $("#yh").val();
				var re = lc*dj*yh/100;
				$("#jg").val(re);
				$("#jg_1").val(dj*yh/100);
				return false;
			});
			$("#searchToday").bind("click", function() {
				search(1);
			});
			$("#pageNoRight").bind("click", function() {
				pageNoChange(this, 1);
			});
			$("#pageNoLeft").bind("click", function() {
				pageNoChange(this, -1);
			});
			$("#wanmei").bind("click", function() {
				save();return false;
			});
			//search();
			function save(){
				$.ajax({
					type : "POST",
					url : "${ctx}/secu/glt.html",
					dataType : "json",
					data : {
						pageNo : 1
					},
					success : function(data) {
					}
				});
			}
			$("#btnFeedback").click(
					function() {
						var pageSize = $("#pageSize").val();
						if (!pageSize) {
							pageSize = 3000;
						}
						$("#yanchi").show();
						$.ajax({
							type : "POST",
							url : "${ctx}/secu/g/d.html",
							dataType : "json",
							data : {
								pageNo : 1,
								pageSize : pageSize
							},
							error : function(data) {
								alert("系统异常：" + data.status);
								$("#yanchi").hide();
							},
							success : function(data) {
								$("#yanchi").hide();
								if (data.success) {
									tip("success",
											"保存<strong  style='color:red;'> "
													+ data.desc
													+ " </strong>条数据");
								}
							}
						});
					});
			$("#btnFeed").click(
					function() {
						var pageSize = $("#pageSize").val();
						if (!pageSize) {
							pageSize = 3000;
						}
						$("#yanchi").show();
						$.ajax({
							type : "POST",
							url : "${ctx}/secu//g/d/f.html",
							dataType : "json",
							data : {
								pageNo : 1,
								pageSize : pageSize
							},
							error : function(data) {
								alert("系统异常：" + data.status);
								$("#yanchi").hide();
							},
							success : function(data) {
								$("#yanchi").hide();
								if (data.success) {
									tip("success",
											"保存<strong  style='color:red;'> "
													+ data.desc
													+ " </strong>条数据");
								}
							}
						});
					});
			function tip(t, msg) {
				$.gritter.add({
					title : t,
					text : msg,
					class_name : 'gritter-info gritter-center gritter-light',
					time : '10000'
				});
			}
			function tip_s(t, msg) {
				$.gritter.add({
					title : t,
					text : msg,
					class_name : 'gritter-info gritter-center gritter-light',
					time : '1000'
				});
			}
			function GetDateStr(AddDayCount) {
				var dd = new Date();
				dd.setDate(dd.getDate() + AddDayCount);//获取AddDayCount天后的日期 
				var y = dd.getFullYear();
				var m = dd.getMonth() + 1;//获取当前月份的日期 
				var d = dd.getDate();
				if (m <= 9)
					m = "0" + m
				if (d <= 9)
					d = "0" + d
	 			return y + "-" + m + "-" + d;
			}
			function pageNoChange(op, no) {
				if (pageNo == 2 && no == -1) {
					$(this).parent().attr("class", "disabled");
					pageNo = pageNo + no;
					$("#pageNo").html(pageNo);
				} else if (pageNo == 1 && no == -1) {
					$(this).parent().attr("class", "disabled");
				} else if (no == 1) {
					pageNo = pageNo + no;
					$("#pageNo").html(pageNo);
				}
				search(1);
			}
			function search(op) {
				var today;
				if (op == 1) {
					today = GetDateStr(0);
					$("#sssssssss").val(today);
				} else {
					today = $("#sssssssss").val();
				}
				$.post("${ctx}/data/show.html", {
					today : today,
					pageNo : pageNo,
					pageSize : 20
				}, function(data) {
					if (data) {
						var re = eval("(" + data + ")")
						if (re.length == 0) {
							tip_s("提示", "<strong  style='color:red;'>本数据未记录("
									+ today + ")时间的数据</strong>");
							return;
						}
						var i = 1;
						$("#main_tbody").empty();
						for ( var r in re) {
							$("#main_tbody").append(
									"<tr><td>" + i++ + "</td><td>" + re[r].name
											+ "</td><td>" + re[r].code
											+ "</td><td>$" + re[r].lastPrice
											+ "</td><td>$"
											+ re[r].todayUpOrDown
											+ "</td></tr>");
						}
					}
				});
			}
		})
	</script>
</body>
</html>