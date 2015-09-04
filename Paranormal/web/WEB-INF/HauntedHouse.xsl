<?xml version="1.0" encoding="UTF-8" ?><!--Dagnija Kaulina-Brante 13114310-->
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:include href="ParanormalInclude.xsl"/>	
       <xsl:template match="*">
		<xsl:apply-templates select="HHouse"/>
	</xsl:template>                         
        <xsl:template match="HHouse">
            <xsl:for-each select="//house">
		<div id="general">
                    <div class="imgHouse">
                        <xsl:element name="img">
				<xsl:attribute name="src" ><xsl:value-of select="image[@id='old']"/>
				</xsl:attribute>
			</xsl:element>
                    </div>
                    <xsl:element name="a">
                        <xsl:attribute name="href">ParanormalHouseNext?title=<xsl:value-of select="title"/></xsl:attribute>
                        <h3><xsl:value-of select="title"/></h3>
                    </xsl:element>
                    <h4><xsl:value-of select="buildDate"/></h4>
		</div>
            </xsl:for-each>
        </xsl:template> 
</xsl:transform>   