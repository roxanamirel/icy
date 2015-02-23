<!DOCTYPE html>
<!-- saved from url=(0066)http://twitter.github.com/bootstrap/examples/starter-template.html -->
<html lang="en">
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>IChallengeYou</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Les styles -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap/datepicker.css" >
    <style>
    
      body 
      {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="http://twitter.github.com/bootstrap/assets/ico/favicon.png">
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          
          <div class="brand">IChallengeYou</div>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li><a href='${pageContext.request.contextPath}/login'>Log In</a></li>
                <li><a href="${pageContext.request.contextPath}/help">Help</a></li>
                <li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
             
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="container">

     ${param.body}

    </div> <!-- /container -->
    


  

</body>


</html>