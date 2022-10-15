function getCity(currProvince){ 
	//当前 所选择 的 省 
	var currProvincecurrProvince = currProvince;
  
	//清空 城市 下拉选单 
	document.all.selectCity.length = 0 ;
 
	for (var i = 0 ;i <cityArray.length;i++){  
  
		//得到 当前省 在 城市数组中的位置 
		if(cityArray[i][0]==currProvince){  
  
			//得到 当前省 所辖制的 地市 
			tmpcityArray = cityArray[i][1].split("|") ;

  			for(var j=0;j<tmpcityArray.length;j++){ 

 				//填充 城市 下拉选单 
				document.all.selectCity.options[document.all.selectCity.length] = new Option(tmpcityArray[j]); 
			} 
		} 
	} 
} 

var xmlhttp=null; 
 
function buttonClick(){

	var obj = document.getElementById('selectCity');
	var city = obj.options[obj.selectedIndex].text;
	getWeather(city);

}
   
function getWeather(string){    
 
	var id=string.substring(string.indexOf("(")+1,string.indexOf(")"));  
 
	if (window.XMLHttpRequest){   
		xmlhttp=new XMLHttpRequest();   
	}   

	else if (window.ActiveXObject){          

		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");    
	}   
	else{ 
  
		xmlhttp=new ActiveXObject("Msxml2.XMLHTTP"); 
  
	}   

	xmlhttp.open("GET","http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName?theCityName="+id,true);
	xmlhttp.onreadystatechange=stateChange;   
	xmlhttp.send(null);   
}   

function stateChange(){ 
  
	if(xmlhttp.readyState==4){   

	if(xmlhttp.status==200){   

		//获取所有返回的数据   
		var data=xmlhttp.responseXML; 

		//首先清除 table 的所有内容   
		table = document.getElementById("weatherTable"); 
		var pchildren = table.childNodes;
		for(var a=0; a<pchildren.length; a++){

			table.removeChild(pchildren[a]);

		}

		document.getElementById("moreButton").style.display="none";

		var date = getXMLData(data,6).substring(0,getXMLData(data,6).indexOf("日")+1);

		var weather = getXMLData(data,6).substring(getXMLData(data,6).indexOf("日")+1,getXMLData(data,6).length);

		var weatherImage1 = getXMLData(data,8);
		var weatherImage2 = getXMLData(data,9);

		row = table.insertRow(table.rows.length);			
		row.insertCell(row.cells.length).innerHTML= getXMLData(data,0);
		row.insertCell(row.cells.length).innerHTML= getXMLData(data,1); 

		row = table.insertRow(table.rows.length); 	
		row.width = "3%";
		row.insertCell(row.cells.length).innerHTML= date;
		row.insertCell(row.cells.length).innerHTML= "<img src = \"resource/images/" + weatherImage1+"\"/>" +"<img src = \"resource/images/" + weatherImage2+"\"/>"+ "   " + weather; 
 
		row = table.insertRow(table.rows.length);     
		row.insertCell(row.cells.length).innerHTML= "温度:";
		row.insertCell(row.cells.length).innerHTML= getXMLData(data,5);  
				 
		row = table.insertRow(table.rows.length);     
		row.insertCell(row.cells.length).innerHTML= "风力:";
		row.insertCell(row.cells.length).innerHTML= getXMLData(data,7);  
		document.getElementById("moreButton").style.display="block"; 

		var weatherMarquee = document.getElementById("Marquee");
		weatherMarquee.innerHTML = "<marquee direction=up onmouseover=this.stop() onmouseout=this.start() bgcolor = ccffdc height=15 width=25% scrollAmount=1>"+getXMLData(data,10)+"</marquee>";


		}   
	}   
  
} 
	
function moreInformation(){

	var data=xmlhttp.responseXML; 

	var date_1 = getXMLData(data,13).substring(0,getXMLData(data,13).indexOf("日")+1);
	var weather_1 = getXMLData(data,13).substring(getXMLData(data,13).indexOf("日")+1,getXMLData(data,13).length);

	var weatherImage1_1 = getXMLData(data,15);
	var weatherImage2_1 = getXMLData(data,16);

	row = table.insertRow(table.rows.length); 	
	row.insertCell(row.cells.length).innerHTML= date_1;
	row.insertCell(row.cells.length).innerHTML= "<img src = \"resource/images/" + weatherImage1_1+"\"/>" +"<img src = \"resource/images/" + weatherImage2_1 +"\"/>"+ weather_1; 

	row = table.insertRow(table.rows.length);     
	row.insertCell(row.cells.length).innerHTML= "温度:";
	row.insertCell(row.cells.length).innerHTML= getXMLData(data,12);  
		 
	row = table.insertRow(table.rows.length);     
	row.insertCell(row.cells.length).innerHTML= "风力:";
	row.insertCell(row.cells.length).innerHTML= getXMLData(data,14); 
 
	document.getElementById("moreButton").style.display="none";
}


function getXMLData(data,number){
		
	return data.getElementsByTagName("string")[number].firstChild.nodeValue;

}