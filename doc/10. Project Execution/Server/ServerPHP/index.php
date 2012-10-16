<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<TITLE> AppsolutDistribute</TITLE>
	<META NAME="Author" CONTENT="Mattias Isene - for Group25 in DAT255 course at Chalmers">
	<META NAME="Subject" CONTENT="AppsoluteDistribute Android App - Databases">
	<META NAME="Description" CONTENT="This webserver is setup for handling users and groups for Android app AppsolutDistribute">
	<META NAME="Keywords" CONTENT="Android, App, AppsolutDistribute, Appsolut, Distribute">
	<META NAME="Language" CONTENT="en-us">
	<META NAME="Abstract" CONTENT="AppsolutDistribute Android App">
	<META NAME="Copyright" CONTENT="© Copyright Mattias Isene">
	<META NAME="Designer" CONTENT="Mattias Isene">
	<META NAME="Revisit-After" CONTENT="30 Days">
	<META NAME="Distribution" CONTENT="Global">
	<META NAME="Robots" CONTENT="INDEX, FOLLOW">
	<META NAME="RATING" CONTENT="General">
	<META NAME="GOOGLEBOT" CONTENT="NOARCHIVE">
	<META NAME="ROBOTS" CONTENT="NOARCHIVE">
	<META HTTP-EQUIV="Pragma" CONTENT="NO">
	<META HTTP-EQUIV="imagetoolbar" CONTENT="NO">
	<META HTTP-EQUIV="Content-Type" content="text/html; charset=iso-8859-1">
  <meta http-equiv="content-type" content="text/html; charset=windows-1250">
  <meta name="generator" content="notepad">
  
  <script language="javascript"> 
  function validateRadio(form1)
  { 
    thisOne = -1;
  
    for (i=0; i < form1.radio1.length; i++)
    { 
      if (form1.radio1[i].checked) { 
        thisOne = i;
        whichOne = (thisOne+1);
        idIt=document.getElementsByTagName("INPUT")[i].id 
      } 
    } 
    if (thisOne == -1)
    { 
      alert("You must select a radio button!"); 
      return false; 
    } 
    else
    { 
      alert("The Value you selected is: " +form1.radio1[thisOne].value+ "\nThe Name of the Radio Button is: radio" +whichOne+ "\nThe ID of the Radio Button is: "+idIt); 
      return true; 
    } 
  } 
  </script>
  </head>

  <body bgcolor="#eeeeee">
  <div class="div_content">
  
  <p>
  <form name=form1> 
  <input type=radio value="Red" name="radio1" ID="My Red Button">1 
  <input type=radio value="Yellow" name="radio1" ID="My Yellow Button">2 
  <input type=radio value="Green" name="radio1" ID="My Green Button">3 
  <input type=button value="Validate" onclick="validateRadio(form1)";> 
  </form> 
  </p>

  <hr>
  
  <input id="button" type="button" value="Click me" />
  
  </body>
  
  <div class="div_menu"><?php include("menu.php"); ?></div>
    
</html>