<%-- 
    Document   : index
    Created on : 27-Oct-2014, 16:31:40
    Author     : Dagnija Kaulina Brante x13114310
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Paranormal</title>
         <script>
                function mouseOver() {
                    document.getElementById("dare").style.color = "red";
                }

                function mouseOut() {
                    document.getElementById("dare").style.color = "black";
                }
        </script>
        <style>
                    body{ background: black;}
                   #greeting{color:grey; 
                             font-size: 45px;
                             font-family: 'Cinzel Decorative';
                             text-shadow: 1px -1px  #FF3333;
                             text-align: center; 
                             text-shadow: 1px -1px  #E0E0D1;
                             }
                    .button {
                        background-image: -webkit-linear-gradient(top, #000000, #747d82);
                        background-image: -moz-linear-gradient(top, #000000, #747d82);
                        background-image: -ms-linear-gradient(top, #000000, #747d82);
                        background-image: -o-linear-gradient(top, #000000, #747d82);
                        border-radius: 28px;
                        font-family: Georgia;
                        color: #a3a3a3;
                        font-size: 22px;
                        padding: 11px 27px 11px 27px;
                      }

                      .button:hover {
                        background: #d9d9d9;
                        background-image: -webkit-linear-gradient(top, #d9d9d9, #faf7f7);
                        background-image: -moz-linear-gradient(top, #d9d9d9, #faf7f7);
                        background-image: -ms-linear-gradient(top, #d9d9d9, #faf7f7);
                        background-image: -o-linear-gradient(top, #d9d9d9, #faf7f7);
                      }
        </style>
    </head>
    <body>
	<div>
            <img src="img/banner.jpg" style="display: block; width: 15%; padding-top: 12%; margin-left: auto; margin-right: auto;"/>
            <h1 id="greeting">
                <center>
                    <script>
                        //initializes the object with the current date and time.
			var today= new Date();
                        //get hours
			var hour= today.getHours();
			var greeting;

			if(hour>18){
				greeting='Good Evening!';
			}
			else if (hour>12){
				greeting='Good afternoon!';
			}
			else if (hour>0){
				greeting='Good morning!';
			}
			else{
				greeting='Welcome!';
				}
			document.write('<h3>'+greeting+'</h3>');
                    </script>
                </center>
            </h1>
            <a href="main.html">
                <center><button onmouseover="mouseOver()" onmouseout="mouseOut()" class="button">Enter</button></center>
            </a>
                <center><h1 id="dare">Do you dare to enter?</h1></center>
        </div>
    </body>
</html>

