<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/common/layout_login.jsp"
	charEncoding="UTF-8">
	<c:param name="title" value="Contact" />
	<c:param name="body">
		<html>
<head>
<title>Contact Page</title>
<style type="text/css">
body, div, h1, form, fieldset, input, textarea {
	margin: 0; padding: 0; border: 0; outline: none;
}



body {
	background: #f5f5f5;	
	font-family: sans-serif; 
}

#contact {
	width: 600px; margin: 60px auto; padding: 60px 30px;
	background-image:'${pageContext.request.contextPath}/img/notebook.jpg' ; border: 1px solid #e1e1e1;
	-moz-box-shadow: 0px 0px 8px #444;
	-webkit-box-shadow: 0px 0px 8px #444;
}
#error {
color:red;
}

h1 {
	font-size: 35px; color: #445668; text-transform: uppercase;
	text-align: center; margin: 0 0 35px 0; text-shadow: 0px 1px 0px #f2f2f2;
}

label {
	float: left; clear: left; margin: 11px 20px 0 0; width: 95px;
	text-align: right; font-size: 16px; color: #445668; 
	text-transform: uppercase; text-shadow: 0px 1px 0px #f2f2f2;
}

input {
	width: 290px; height: 35px; padding: 5px 20px 0px 20px; margin: 0 0 20px 0; 
	background: #5E768D;
	background: -moz-linear-gradient(top, #546A7F 0%, #5E768D 20%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#546A7F), color-stop(20%,#5E768D)); /* webkit */
	border-radius: 5px; -moz-border-radius: 5px; -webkit-border-radius: 5px;
	-moz-box-shadow: 0px 1px 0px #f2f2f2;-webkit-box-shadow: 0px 1px 0px #f2f2f2;
	font-family: sans-serif; font-size: 16px; color: #f2f2f2; text-shadow: 0px -1px 0px #334f71; 
}
	input::-webkit-input-placeholder  {
    	color: #a1b2c3; text-shadow: 0px -1px 0px #38506b;  
	}
	input:-moz-placeholder {
	    color: #a1b2c3; text-shadow: 0px -1px 0px #38506b; 
	}

textarea {
	width: 400px; height: 200px; padding: 12px 20px 0px 20px; margin: 0 0 20px 0; 
	background: #5E768D;
	background: -moz-linear-gradient(top, #546A7F 0%, #5E768D 20%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#546A7F), color-stop(20%,#5E768D)); /* webkit */
	border-radius: 5px; -moz-border-radius: 5px; -webkit-border-radius: 5px;
	-moz-box-shadow: 0px 1px 0px #f2f2f2;-webkit-box-shadow: 0px 1px 0px #f2f2f2;
	font-family: sans-serif; font-size: 16px; color: #f2f2f2; text-shadow: 0px -1px 0px #334f71; 
}
	textarea::-webkit-input-placeholder  {
    	color: #a1b2c3; text-shadow: 0px -1px 0px #38506b;  
	}
	textarea:-moz-placeholder {
	    color: #a1b2c3; text-shadow: 0px -1px 0px #38506b; 
	}
	
input:focus, textarea:focus {
	background: #728eaa;
	background: -moz-linear-gradient(top, #668099 0%, #728eaa 20%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#668099), color-stop(20%,#728eaa)); /* webkit */
}

input[type=submit] {
	width: 145px; height: 39px; float: right; padding: 10px 15px; margin: 0 15px 0 0;
	-moz-box-shadow: 0px 0px 5px #999;-webkit-box-shadow: 0px 0px 5px #999;
	border: 1px solid #556f8c;
	cursor: pointer;
}


</style>

</head>
<body background="${pageContext.request.contextPath}/img/notebook">
	<br />
	<br />
	 <c:if test="${not empty status}">
		<div class="alert alert-succes">
		 <c:out value="${status}" />
		 </div>
		 </c:if>
		 <div id="contact">
	<form:form method="post" action="${pageContext.request.contextPath}/contact" modelAttribute="contactData"
		cssClass="form">
		<fieldset>
			<legend>
				<Strong>Contact:</Strong>
			</legend>
			<strong><br />Contact us at:</strong> <br /> <br /> <strong>
				 <a>ichallengeyu@gmail.com</a>
			<br /> <br /> <strong> Or fill in </strong> 
			<br />
			<div class="form_settings">
				<label class="email" for="name"><strong>NAME</strong></label>
				<div class="controls">
				<form:errors path="name" cssClass="error help-inline inline"
						element="span" id="error"/>
					<form:input path="name" cssClass="span3" cssErrorClass="error" />
					
				</div>
			</div>

			<div class="form_settings">
				<label class="control-label" for="email"><strong>EMAIL</strong></label>
				<div class="controls">
				<form:errors path="email" cssClass="error help-inline inline"
						element="span" id="error"/>
					<form:input path="email" cssClass="span3" cssErrorClass="error" />
					
				</div>
			</div>
			<div class="form_settings">
				<label class="control-label" for="subject"><strong>SUBJECT</strong></label>
				<div class="controls">
				<form:errors path="subject" cssClass="error help-inline inline"
						element="span" id="error"/>
					<form:input path="subject" cssClass="span3"
						cssErrorClass="error" />
					
				</div>
			</div>
			<div class="form_settings">
				<label class="control-label" for="message"><strong>MESSAGE</strong></label>
				<div class="controls">
				<form:errors path="message" cssClass="error help-inline inline"
						element="span" id="error" />
					<form:textarea path="message" cssClass="span3"
						cssErrorClass="error" />
					
				</div>
			</div>

			
                   <input type="submit" class="btn btn-primary"
                        value="Send">&nbsp;
                    
					
               
		</fieldset>
	</form:form>
</div>




</body>
		</html>
	</c:param>
</c:import>