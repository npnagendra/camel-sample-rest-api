<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" />
    <xsl:template match="/" name="rootTemplate" >
        <xsl:apply-templates select="automobile" />
    </xsl:template>
    <xsl:template match="automobile" name="automobile-to-vehicle">
        <xsl:element name="vehicle">
            <xsl:copy-of select="node()"/>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>