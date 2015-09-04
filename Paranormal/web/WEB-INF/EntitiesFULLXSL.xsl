<?xml version="1.0" encoding="UTF-8" ?><!--Daniel Benhamou x13341086-->
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
 <xsl:param name="vName"></xsl:param>
 <xsl:param name="vNext_entity"></xsl:param>
 <xsl:param name="vPrev_entity"></xsl:param>
 <xsl:include href="ParanormalInclude.xsl"/>
    
    	
    <xsl:template match="*">
        <xsl:apply-templates select="Entities"/>
    </xsl:template>
    
	
	<xsl:template match="Entities">
            
            <div id="infoFull">	
		<xsl:for-each select="Entity">
		<xsl:if test="./name=$vName">
		<h2 id="fullTitle"><xsl:value-of select="./name" /></h2>
			<br/>
			<div id="imgEnt">
					<xsl:element name="img">
					<xsl:attribute name="src"><xsl:value-of select="./image[@id='fullpic']" />
					</xsl:attribute>
					</xsl:element>
			</div>
			<br/>
			<h3>Information</h3>
			<p><b>History:</b> <xsl:value-of select="./information/history" /></p>
			<p><b>Height:</b> <xsl:value-of select="./information/height" /></p>
			<p><b>Sightings:</b><xsl:value-of select="./information/sightings"/></p>
			
			<xsl:element name="a"><xsl:attribute name="href">ParanormalEntitiesNext?name=<xsl:value-of select="$vNext_entity"/></xsl:attribute>Next</xsl:element>
                              | <xsl:element name="a"><xsl:attribute name="href">ParanormalEntitiesNext?name=<xsl:value-of select="$vPrev_entity"/></xsl:attribute>Prev</xsl:element>
                              | <xsl:element name="a"><xsl:attribute name="href">EntitiesUpdateServlet?name=<xsl:value-of select="$vName"/></xsl:attribute>Update Sightings</xsl:element>
			
                
                
                
                <xsl:element name="form">
						<xsl:attribute name="action">ParanormalEntitiesNext</xsl:attribute>
						<xsl:attribute name="method">post</xsl:attribute>            
						<xsl:element name="input">
							<xsl:attribute name="value"><xsl:value-of select="./name"/></xsl:attribute>
							<xsl:attribute name="type">hidden</xsl:attribute>
							<xsl:attribute name="name">name</xsl:attribute>
						</xsl:element>
					<table>
                                            <tr>
                                            <td>Sightings:</td>
						<td><xsl:element name="input">
							<xsl:attribute name="value"><xsl:value-of select="./sightings"/></xsl:attribute>
							<xsl:attribute name="type">String</xsl:attribute>
							<xsl:attribute name="size">50</xsl:attribute>
							<xsl:attribute name="name">sightings</xsl:attribute>
						</xsl:element>
                                                </td>
                                            </tr>
                                       
					
                                        
					
                                      
</table>
					<input type="submit" value="Update" class="button"/>
					<a class="anchor">
                                        <xsl:attribute name="href">ParanormalEntitiesNext?name=<xsl:value-of select="$vName"/>
                                        </xsl:attribute>
                                        <input type="button" value="Cancel" class="button"/>
                                        </a>           
				 </xsl:element>
                
             
                
                
                
                
                
                </xsl:if>
			</xsl:for-each>
			</div>
		
	
	
	</xsl:template>
	
	
</xsl:transform>