package com.foodget.util.parsing;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlParseDom {
	static XmlParseDom xmlParseDom;
	static {
		xmlParseDom = new XmlParseDom();
	}
	public XmlParseDom getXmlParseDom(){
		return xmlParseDom;
	}
	private XmlParseDom(){};
	public static NodeList getNodeList(String tagName, Document document){
		NodeList cols=null;
		XPath xpath = XPathFactory.newInstance().newXPath();
		try {
			cols = (NodeList)xpath.evaluate(tagName, document, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return cols;
	}
	public static Document xmlPeraseDocument(String xml){
		Document document=null;
		InputSource is = new InputSource(new StringReader(xml));
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return document;
	}
	public static Document createDocument(String xml){
		Document document=null;
		InputSource is = new InputSource(new StringReader(xml));
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return document;
	}
}
