<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project4</title>
</head>
<body>
	<tr bgcolor=blue>
		<td>
			<p class="MsoNormal" align="center" style="text-align: center">
				<b><span style="font-size: 24.0pt; color: red;">Welcome
						To <span class="GramE">The</span> Spring 2022 Project 4 <span
						class="SpellE">Enterprise</span> Database System
				</span></b><b><span style="font-size: 24.0pt"><o:p></o:p></span></b>
			</p>
		</td>
	</tr>
	<center>
	<form action="/EnterpriseProject4/sqlServe" method="post">
		<div class='container'>
			<label for='queryArea'>Enter SQL query</label><br />
			<textarea style="background-color: lightyellow" name="queryArea"
				id="queryArea" rows="10" cols="50"></textarea>
			<br />
		</div>
		</center>
		<p>
		<center>
			<input type="submit" value="Execute Command" /> 
			<input type="reset" value="Reset Form"> 
			<input type="reset" value="Clear Form">
		</center>
		</p>
	</form>
</body>
</html>