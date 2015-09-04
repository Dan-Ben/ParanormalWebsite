<?xml version="1.0" encoding="UTF-8" ?><!--Dagnija Kaulina-Brante 13114310-->
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    
    <xsl:param name="vhouseTitle"></xsl:param>
    <xsl:param name="vNextName"></xsl:param>
    <xsl:param name="vPrevName"></xsl:param>
    
    <xsl:include href="ParanormalInclude.xsl"/>
    
    <xsl:template match="*">
        <xsl:apply-templates select="HHouse"/>
    </xsl:template>  
     
    <xsl:template match="HHouse">
		<div id="infoFull">	
			<xsl:for-each select="house">
				<xsl:if test="./title=$vhouseTitle">
					<div id="title"><h2 id="fullTitle"><xsl:value-of select="./title" /></h2>
						<xsl:value-of select="./address" />
						(<xsl:value-of select="./buildDate" />)
						<p><b>Current Owner:</b> <xsl:value-of select="./ownerNow" /></p>
						<br/>
						<div id="img">
                                                    <!--This will produce html 
                                                    <img> tag with 
                                                    the src attribute properly containing an image path from each 
                                                    <image> tag in original xml-->
							<xsl:element name="img">
								<xsl:attribute name="src"><xsl:value-of select="./image[@id='new']" />
								</xsl:attribute>
							</xsl:element>
						</div>
						<br/>
						<h3><u>Activity's happening!</u></h3>
							<p><b>Activity since:</b> <xsl:value-of select="./activity/asince" /></p>
							<p><b>Tragic events happen:</b> <xsl:value-of select="./activity/aHappen" /></p>
							<p><b>Events happening:</b><xsl:value-of select="./activity/pananormal" /></p>
								<xsl:if test="openForPublic/code/@id='2'">
									<p>Date open:<xsl:value-of select="openForPublic/code/date" /></p>
									<p>Opening time:<xsl:value-of select="openForPublic/code/time" /></p>
									<p>Price for adults: <xsl:value-of select="openForPublic/code/price/adult" /> $</p>
									<p>Price for children: <xsl:value-of select="openForPublic/code/price/children" /> $ (For children 12 years of age and younger)</p>
									<p>Contact: <xsl:value-of select="openForPublic/code/contact" /></p>
									<br/>
								</xsl:if>
						<xsl:if test="openForPublic/code/@id='1'">
							<h3  style="color: red;">Closed for public!</h3><br/>
						</xsl:if>
					</div>
                              <h3>
                        <xsl:element name="a"><xsl:attribute name="href">ParanormalHouseNext?title=<xsl:value-of select="$vPrevName"/></xsl:attribute><button class="button">Previous</button></xsl:element>
                        <xsl:element name="a">
                            <xsl:attribute name="href">ParanormalHouseNext?title=<xsl:value-of select="$vNextName"/></xsl:attribute>
                            <button class="button">Next</button>
                        </xsl:element>
                                                </h3>
					<div id="update">
						
					
					<xsl:element name="form">
						<xsl:attribute name="action">ParanormalHouseNext</xsl:attribute>
						
                                                <xsl:attribute name="method">post</xsl:attribute>
                                                <xsl:element name="input">
							<xsl:attribute name="value"><xsl:value-of select="./title"/></xsl:attribute>
							<xsl:attribute name="type">hidden</xsl:attribute>
							<xsl:attribute name="name">title</xsl:attribute>
						</xsl:element>
					<table>
                                            <tr>
                                            <td>Owner Now:</td>
                                            
						<td><xsl:element name="input">
							<xsl:attribute name="value"><xsl:value-of select="./ownerNow"/></xsl:attribute>
							<xsl:attribute name="type">String</xsl:attribute>
							<xsl:attribute name="size">50</xsl:attribute>
							<xsl:attribute name="name">ownerNow</xsl:attribute>
						</xsl:element>
                                                </td>
                                            </tr>
                                       
					<br/>
                                        <tr>
                                        <td>
                                            Date open:</td>
                                        <td>
						<xsl:element name="input">
							<xsl:attribute name="value"><xsl:value-of select="openForPublic/code/date"/></xsl:attribute>
							<xsl:attribute name="type">String</xsl:attribute>
							<xsl:attribute name="size">50</xsl:attribute>
							<xsl:attribute name="name">date</xsl:attribute>
						</xsl:element>
                                        </td>
                                        </tr>
                                        
					<br/>
                                        <tr>
                                        <td>
                                            Time open:</td>
                                        <td>
						<xsl:element name="input">
							<xsl:attribute name="value"><xsl:value-of select="openForPublic/code/time"/></xsl:attribute>
							<xsl:attribute name="type">String</xsl:attribute>
							<xsl:attribute name="size">50</xsl:attribute>
							<xsl:attribute name="name">time</xsl:attribute>
						</xsl:element>
                                        </td>
                                        </tr>
                                        
					<br/>
</table>
					<input type="submit" value="Update" class="button"/>
					<a class="anchor">
                                        <xsl:attribute name="href">ParanormalHouseNext?title=<xsl:value-of select="$vhouseTitle"/>
                                        </xsl:attribute>
                                        <input type="button" value="Cancel" class="button"/>
                                        </a>           
				 </xsl:element>
					</div> 	
				</xsl:if>
			</xsl:for-each>		
		</div>
    </xsl:template>
</xsl:transform>