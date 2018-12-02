package com.example.networktest;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX解析类
 * 
 * @author huang
 * 
 */
public class MyHandler extends DefaultHandler {
	private StringBuilder builder;
	private String nodeName;

	private StringBuilder id;
	private StringBuilder name;
	private StringBuilder version;

	@Override
	public void startDocument() throws SAXException {
		builder = new StringBuilder();
		id = new StringBuilder();
		name = new StringBuilder();
		version = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// 记录节点名
		nodeName = localName;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if ("id".equals(nodeName)) {
			id.append(ch, start, length);
		} else if ("name".equals(nodeName)) {
			name.append(ch, start, length);
		} else if ("version".equals(nodeName)) {
			version.append(ch, start, length);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("app".equals(localName)){
			builder.append("id:" + id.toString().trim() + "\n");
			builder.append("name:" + name.toString().trim() + "\n");
			builder.append("version:" + version.toString().trim() + "\n" + "\n");
			id.setLength(0);
			name.setLength(0);
			version.setLength(0);
		}
		
	}

	@Override
	public void endDocument() throws SAXException {
	}

	public String getResult() {
		return builder.toString();
	}
}
