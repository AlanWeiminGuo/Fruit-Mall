<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>天气预报</title>
<link rel = "stylesheet" type = "text/css" href = "resource/Css/tableStyle.css">




<script language="javascript" type="text/javascript" src = "resource/Script/city.js"></script>
<script language="javascript" type="text/javascript" src = "resource/Script/function.js"></script>

</head>

<body>
<form id="cityForm" width = "25%">
<select id="selectProvince" onChange="getCity(this.options[this.selectedIndex].value)">

	<option value="">-请选择-</option>
	<option value="北京市">北京市</option>
	<option value="上海市">上海市</option>
	<option value="天津市">天津市</option>
	<option value="重庆市">重庆市</option>
	<option value="河北省">河北省</option>
	<option value="山西省">山西省</option>
	<option value="内蒙古自治区">内蒙古自治区</option>
	<option value="辽宁省">辽宁省</option>
	<option value="吉林省">吉林省</option>
	<option value="黑龙江省">黑龙江省</option>
	<option value="江苏省">江苏省</option>
	<option value="浙江省">浙江省</option>
	<option value="安徽省">安徽省</option>
	<option value="福建省">福建省</option>
	<option value="江西省">江西省</option>
	<option value="山东省">山东省</option>
	<option value="河南省">河南省</option>
	<option value="湖北省">湖北省</option>
	<option value="湖南省">湖南省</option>
	<option value="广东省">广东省</option>
	<option value="广西壮族自治区">广西壮族自治区</option>
	<option value="海南省">海南省</option>
	<option value="四川省">四川省</option>
	<option value="贵州省">贵州省</option>
	<option value="云南省">云南省</option>
	<option value="西藏自治区">西藏自治区</option>
	<option value="陕西省">陕西省</option>
	<option value="甘肃省">甘肃省</option>
	<option value="宁夏回族自治区">宁夏回族自治区</option>
	<option value="青海省">青海省</option>
	<option value="新疆维吾尔族自治区">新疆维吾尔族自治区</option>
	<option value="香港特别行政区">香港特别行政区</option>
	<option value="澳门特别行政区">澳门特别行政区</option>
	<option value="台湾省">台湾省</option>
</select> 


<select id="selectCity">
	<option>-城市-</option>
</select> 

<input type="button" value=" 查 询 " onClick="buttonClick()">

</form>

<p id = "Marquee"></p>

<table id="weatherTable" cellpadding="0" cellspacing="1"  border="1"  width="25%"> </table>

<br>


<input type = "button" id = "moreButton" value = "  明天 >>  " style="display:none" onClick = "moreInformation()" >

<br>



</body>
</html>


