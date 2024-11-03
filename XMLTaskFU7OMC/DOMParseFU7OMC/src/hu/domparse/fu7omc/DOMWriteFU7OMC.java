package hu.domparse.fu7omc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWriteFU7OMC {
    private Element element;

    public DOMWriteFU7OMC(Element element) {
        this.element = element;
    }

    public void printXmlStructure(Element element, String indent) {
        // Kiírja az aktuális elemet a megfelelő behúzással
        System.out.println(indent + "<" + element.getNodeName() + ">");

        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                // Rekurzív hívás a gyermek elemekre
                printXmlStructure((Element) node, indent + "  "); // A behúzás növelése
            } else if (node.getNodeType() == Node.TEXT_NODE) {
                String content = node.getTextContent().trim();
                if (!content.isEmpty()) {
                    // A szöveges tartalom kiírása a megfelelő behúzással
                    System.out.println(indent + "  " + content);
                }
            }
        }
        // Záró tag kiírása a megfelelő behúzással
        System.out.println(indent + "</" + element.getNodeName() + ">");
    }

    public void writeXmlFile(Document doc, String filePath) throws Exception{
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
        System.out.println("Sucessfully written to " + filePath);
    }
}
