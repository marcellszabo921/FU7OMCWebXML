package hu.domparse.fu7omc;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMQueryFU7OMC {
    private Document document;

    public DOMQueryFU7OMC(Document document) {
        this.document = document;
    }

    // Adatlekérdezés egy adott Gyar kód alapján
    public void queryByGyarKod(String gyarKod) {
        NodeList gyarList = document.getElementsByTagName("Gyar");
        for (int i = 0; i < gyarList.getLength(); i++) {
            Node gyarNode = gyarList.item(i);
            if (gyarNode.getAttributes().getNamedItem("Gyarkod").getNodeValue().equals(gyarKod)) {
                System.out.println("Gyar kód: " + gyarKod);
                NodeList childList = gyarNode.getChildNodes();
                for (int j = 0; j < childList.getLength(); j++) {
                    Node child = childList.item(j);
                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println(child.getNodeName() + " : " + child.getTextContent());
                    }
                }
                System.out.println();
                return;
            }
        }
        System.out.println("Gyar kód nem található: " + gyarKod);
    }
}
