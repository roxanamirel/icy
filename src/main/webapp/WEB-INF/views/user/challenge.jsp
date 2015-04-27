<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Challenge</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/Form.css" />
</head>
<body>
<div class="form-style-5">
<form:form method="post" action="${pageContext.request.contextPath}/user/challengecreated"
						modelAttribute="challenge" 
						value="/challengecreated">
<fieldset>
<legend><span class="number">1</span> Challenge Info</legend>
<form:input  path = "challengename" type="text" name="field1" placeholder="Name of Challenge *" />
<form:textarea path = "challengedesc" name="field2" placeholder="Description of challenge"/>
<label for="category">Category:</label>
<form:select  path= "challengecategory" id="job" name="field3" >
  <form:option value="IT">IT</form:option>
  <form:option value="sports">Sports</form:option>
  <form:option value="cooking">Cooking</form:option>
  <form:option value="fashion">Fashion</form:option>
  <form:option value="other">Other</form:option>
</form:select>      
</fieldset>
<fieldset>
<legend><span class="number">2</span> Additional Info</legend>
<form:textarea path = "challengeadditionalinfo" name="field4" placeholder="About Challenge"></form:textarea>
</fieldset>
<input type="submit" value="Create Challenge" />
</form:form>
</div>
</body>
</html>