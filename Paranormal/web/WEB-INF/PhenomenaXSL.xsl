<?xml version="1.0" encoding="UTF-8" ?>
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:include href="ParanormalInclude.xsl"/>	

     <xsl:template match="*">
		<xsl:apply-templates select="paranorm"/>
	</xsl:template>      
         
         <div class="imgPhenomena">
                        <xsl:element name="img">
				<xsl:attribute name="src" ><xsl:value-of select="image[@id='thumb']"/>
				</xsl:attribute>
			</xsl:element>
         </div>                        
      
                                       
        <xsl:template match="paranorm">
            <xsl:template match="paranorm">
            <xsl:for-each select="//phenomena">     
                <div id="general">    
                    
                    <xsl:element name="a">
                        <xsl:attribute name="href">ParanormalPhenomenaNext?name=<xsl:value-of select="name"/></xsl:attribute>
                <h3><xsl:value-of select="name"/></h3>
                
                </xsl:element>
                
                
                
                </div>
            </xsl:for-each>
        </xsl:template>
        </xsl:template> 
            
</xsl:transform> 

  