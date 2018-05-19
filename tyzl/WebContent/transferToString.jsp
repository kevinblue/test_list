<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>转换</title>
</head>
<body>
    <table>
       <tr>
           <td>
              <textarea  rows="12" cols="200" id="id_leftString" style="overflow:auto;"></textarea>
           </td>
      </tr> 
      <tr>  
            <td>
              <input style="width:80px;" type="button" value="转换" onclick="javascript:transfer();"/>
            </td>   
       </tr> 
      <tr>    
          <td>
              <textarea rows="12" cols="200" id="id_rightString" style="overflow:auto;"></textarea>
           </td>
       </tr>
    </table>
    <script type="text/javascript" language="javascript">
    var prefix = "    	.append(\" \\n   ",suffix= " \")";
       function transfer()
       {
           var leftString = document.getElementById('id_leftString').value;
           var rightString = document.getElementById('id_rightString');
           var leftStringLineArr = leftString.split("\n");
           var tempRightString = "";
           for(var index=0;index<leftStringLineArr.length;index++)
           {
        	   tempRightString+=prefix;
        	   tempRightString+=leftStringLineArr[index];
               tempRightString+=suffix;
               tempRightString+="\n";
           }
           rightString.value=tempRightString;
       }
    </script>
</body>
</html>

