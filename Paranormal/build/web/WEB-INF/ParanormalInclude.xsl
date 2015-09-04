<?xml version="1.0" encoding="UTF-8" ?><!--Dagnija Kaulina-Brante 13114310-->
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">
            <html>
              <head>
            	<title>Haunted House</title>
<style>
@import url(http://fonts.googleapis.com/css?family=Cinzel+Decorative);
body {background-color: black;}
#infoFull{width: 60%;margin-left: auto; margin-right: auto; background: radial-gradient(white, grey, black); }
#title{text-align: center; }
#img{border-style:outset; width: 300px; height: 200px; margin-left: auto; margin-right: auto; margin-top: 1%; }
p{text-align: justify; padding-top: 15px; padding-left: 25px; padding-right: 25px;}
#fullTitle{text-align: center; color: black; font-family: 'Cinzel Decorative', cursive; text-shadow: 1px -1px  #E0E0D1;}                     
#banner{border-bottom: 3px ridge grey;}
#bannerimg{display: block; 
		width: 15%; 
		margin-left: auto; 
		margin-right: auto;}
h1{text-align:center; 
	color: #44CDF9;
	font-family: 'Cinzel Decorative', cursive;}
#navigation{float: left; 
            position: relative; }
td{color:white;
    font-family: 'Cinzel Decorative', cursive;}
#general{float: right; 
            position: relative; 
            width: 220px; 
            height: 220px; 
            margin: 2%; 
            background: radial-gradient(white, grey, black);}
#general:hover{
            background:white;
            border-radius:25px;
            text-shadow: 4px -6px  #E0E0D1;
}
.imgHouse{border-style:outset; 
            width: 100px; 
            height: 100px; 
            margin-left: auto; 
            margin-right: auto; 
            margin-top: 1%;}
h3,h4{text-align: center;
            font-size: 20px;
}
#navigation{width: 16%;
            position:relative;
            padding-top: 5%;
            height: 800px;
            float: left;
            border-right: ridge;
}
h2{text-align: center;font-size: 30px;
	font-family: 'Cinzel Decorative', cursive;
	}
h1{font-size: 40px;}
h2, h1{color: red; }
h2:hover{background: grey;}
#update{border: 3px ridge grey;
    align:middle;
padding: 5%;
border-radius:25px;}
    button{align:middle;}
p{font-size: 20px;
    text-align: center;}
u{font-size: 25px;
    text-shadow: 4px -2px  #E0E0D1;}
.button {
  background: #000000;
  background-image: -webkit-linear-gradient(top, #000000, #747d82);
  background-image: -moz-linear-gradient(top, #000000, #747d82);
  background-image: -ms-linear-gradient(top, #000000, #747d82);
  background-image: -o-linear-gradient(top, #000000, #747d82);
  background-image: linear-gradient(to bottom, #000000, #747d82);
  -webkit-border-radius: 28;
  -moz-border-radius: 28;
  border-radius: 28px;
  font-family: Georgia;
  color: #a3a3a3;
  font-size: 22px;
  padding: 11px 27px 11px 27px;
  text-decoration: none;
text-align: center;
   
}

.button:hover {
  background: #d9d9d9;
  background-image: -webkit-linear-gradient(top, #d9d9d9, #faf7f7);
  background-image: -moz-linear-gradient(top, #d9d9d9, #faf7f7);
  background-image: -ms-linear-gradient(top, #d9d9d9, #faf7f7);
  background-image: -o-linear-gradient(top, #d9d9d9, #faf7f7);
  background-image: linear-gradient(to bottom, #d9d9d9, #faf7f7);
  text-decoration: none;
}
    .imgEntity{border-style:outset; 
            width: 100px; 
            height: 100px; 
            margin-left: auto; 
            margin-right: auto; 
            margin-top: 1%;}
    #imgEnt{border-style:outset; width: 300px; height: 300px; margin-left: auto; margin-right: auto; margin-top: 1%; }
</style>

              </head>
              <body>
		<div id="banner"><img src="img/banner.jpg" id="bannerimg"/>
				<h1>PARANORMAL</h1>
                </div>
		<div id="navigation">
                    <table>
                        <tr>
                            <td><a href="main.html"><h2><xsl:value-of select="//navigation/main[@id='home']"/></h2></a>
                            </td>
                        </tr>
                         <tr>
                            <td><a href="ParanormalHousesServlet"><h2><xsl:value-of select="//navigation/main[@id='house']"/> </h2></a>
                            </td>
                        </tr>
                        <tr>
                            <td> <a href="ParanormalEntitiesServlet"><h2><xsl:value-of select="//navigation/main[@id='entities']"/></h2></a></td>
                        </tr>
                        <tr>
                          
                            <td><a href="ParanormalPhenomenaServlet"><h2><xsl:value-of select="//navigation/main[@id='phenomena']"/></h2></a></td>
                        </tr>
                                     
                    </table>
		</div>              
<xsl:apply-templates select="*"/>
              </body>
            </html>
	</xsl:template>
</xsl:transform>