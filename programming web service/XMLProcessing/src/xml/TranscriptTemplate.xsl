<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:tns="http://www.example.org/TranscriptXMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.example.org/TranscriptXMLSchema/transcript.xsd"
	version="1.0">
	<xsl:template match="/">
		<xsl:variable name="transcript"
			select="document('transcript.xml')/tns:transcript" />

		<xsl:element name="tns:trancript">
			<xsl:element name="tns:name">
				<xsl:value-of select="/tns:transcript/tns:person/tns:firstName" />
				<xsl:text> </xsl:text>
				<xsl:value-of select="/tns:transcript/tns:person/tns:lastName" />
			</xsl:element>
			<xsl:element name="tns:university">
				<xsl:value-of select="/tns:transcript/tns:university" />
			</xsl:element>
			<xsl:element name="tns:degree">
				<xsl:value-of select="/tns:transcript/tns:degree" />
			</xsl:element>

			<xsl:element name="tns:dateOfGraduation">
				<xsl:value-of select="/tns:transcript/tns:dateOfGraduation" />
			</xsl:element>
			<xsl:element name="tns:dateOfEnrolment">
				<xsl:value-of select="/tns:transcript/tns:dateOfEnrolment" />
			</xsl:element>


			<xsl:for-each select="/tns:transcript/tns:module">
				<xsl:element name="tns:module">
					<xsl:element name="tns:moduleName">
						<xsl:value-of select="tns:moduleName" />
					</xsl:element>
					<xsl:element name="tns:moduleCode">
						<xsl:value-of select="tns:moduleCodePrefix" />
						<xsl:value-of select="tns:moduleCodeSuffix" />
					</xsl:element>

					<xsl:element name="tns:moduleGrade">
						<xsl:value-of select="tns:moduleGrade" />
					</xsl:element>
					<xsl:element name="tns:moduleCredits">
						<xsl:value-of select="tns:moduleCredits" />
					</xsl:element>
					<xsl:element name="tns:moduleTerm">
						<xsl:value-of select="tns:moduleTerm" />
					</xsl:element>
					<xsl:element name="tns:moduleYear">
						<xsl:value-of select="tns:moduleYear" />
					</xsl:element>
				</xsl:element>
			</xsl:for-each>

			<xsl:element name="tns:GPA">
				<xsl:value-of
					select="(4*(sum($transcript/tns:module[tns:moduleGrade='A']/tns:moduleCredits)) + 
					3*(sum($transcript/tns:module[tns:moduleGrade='B']/tns:moduleCredits)) + 
					2*(sum($transcript/tns:module[tns:moduleGrade='C']/tns:moduleCredits)) + 
					1*(sum($transcript/tns:module[tns:moduleGrade='D']/tns:moduleCredits))) 
					div (sum($transcript/tns:module/tns:moduleCredits))" />
			</xsl:element>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>