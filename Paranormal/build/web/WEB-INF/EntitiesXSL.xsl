<?xml version="1.0" encoding="UTF-8" ?><!--Daniel Benhamou x13341086-->
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:include href="ParanormalInclude.xsl"/>	
       <xsl:template match="*">
		<xsl:apply-templates select="Entities"/>
	</xsl:template>      
        
                                              
        <xsl:template match="Entities">
            <xsl:for-each select="//Entity">
		<div id="general">
                    <div class="imgEntity">
                        <xsl:element name="img">
				<xsl:attribute name="src" ><xsl:value-of select="image[@id='thumbnail']"/>
				</xsl:attribute>
			</xsl:element>
                    </div>
                    
                    <xsl:element name="a">
                        <xsl:attribute name="href">ParanormalEntitiesNext?name=<xsl:value-of select="name"/></xsl:attribute>
                        <h3><xsl:value-of select="name"/></h3>
                    </xsl:element>
		</div>
            </xsl:for-each>
        </xsl:template> 
</xsl:transform>   