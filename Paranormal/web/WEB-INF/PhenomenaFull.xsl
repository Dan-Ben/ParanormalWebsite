<?xml version="1.0" encoding="UTF-8" ?>
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:param name="vPhenomenaTitle"></xsl:param>
    <xsl:param name="vNextName"></xsl:param>
    <xsl:param name="vPrevName"></xsl:param>
    <xsl:include href="ParanormalInclude.xsl"/>
    	
    <xsl:template match="*">
        <xsl:apply-templates select="Phenomena"/>
    </xsl:template>
    <xsl:template match="/">
		
	</xsl:template>
	
	<xsl:template match="Phenomena">
            
            <div id="infoFull">	
		<xsl:for-each select="entity">
		<xsl:if test="./title=$vEntityTitle">
			<div id="title"><h2 id="fullTitle"><xsl:value-of select="./name" /></h2>
			<br/>
			<div id="img">
					<xsl:element name="img">
					<xsl:attribute name="src"><xsl:value-of select="./image[@id='new']" />
					</xsl:attribute>
					</xsl:element>
			</div>
			<br/>
			<h3>Info</h3>
			<p><b>Place:</b> <xsl:value-of select="./information/Place" /></p>
			<p><b>History:</b> <xsl:value-of select="./information/History" /></p>
			
			
			
			</div>
			<xsl:element name="a"><xsl:attribute name="href">ParanormalPhenomenaNext?title=<xsl:value-of select="$vNextName"/></xsl:attribute>Next</xsl:element>
                              | <xsl:element name="a"><xsl:attribute name="href">ParanormalPhenomenaNext?title=<xsl:value-of select="$vPrevName"/></xsl:attribute>Prev</xsl:element>
			</xsl:if>
		</xsl:for-each>
                
                <div><a href="ParanormalPhenomenaServlet" style="color:red;"><h3>Back to Phenomena</h3></a></div>
	
	</div>
	</xsl:template>
	
	
</xsl:transform>