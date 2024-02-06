package com.web.framework.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jakarta.annotation.PostConstruct;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class QueryService {

    private Map<String, String> queriesMap = new HashMap<>();

    @PostConstruct
	public void init() {
		try {
			// Load XML file

			PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			Resource[] resources = resolver.getResources("classpath*:**/*queries.xml");
			for (Resource resource : resources) {
				// Get input stream for each resource
				InputStream inputStream = resource.getInputStream();

				// Parse XML
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(inputStream);

				// Get query nodes
				NodeList queryNodes = document.getElementsByTagName("query");

				// Iterate over query nodes
				for (int i = 0; i < queryNodes.getLength(); i++) {
					Node node = queryNodes.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						String name = element.getAttribute("name");
						String query = element.getTextContent().trim();
						queriesMap.put(name, query);
					}
				}

				// Close input stream
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public String getQuery(String queryName) {
        return queriesMap.get(queryName).replace("\n"," ").replace("\t"," ");
    }
}

