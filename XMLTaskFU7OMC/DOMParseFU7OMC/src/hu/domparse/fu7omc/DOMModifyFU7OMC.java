package hu.domparse.fu7omc;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMModifyFU7OMC {
    private Document document;

    public DOMModifyFU7OMC(Document document) {
        this.document = document;
    }

    // Adat módosítása egy adott Gyar kód alapján
    public void modifyGyarKod(String gyarKod, String newNev, String newCim) {
        NodeList gyarList = document.getElementsByTagName("Gyar");
        boolean found = false; // Flag for found status

        for (int i = 0; i < gyarList.getLength(); i++) {
            Node gyarNode = gyarList.item(i);
            if (gyarNode.getAttributes().getNamedItem("Gyarkod").getNodeValue().equals(gyarKod)) {
                found = true; // Set found flag to true
                NodeList childList = gyarNode.getChildNodes();
                for (int j = 0; j < childList.getLength(); j++) {
                    Node child = childList.item(j);
                    if (child.getNodeName().equals("Nev")) {
                        // Csak akkor frissítjük, ha az új név nem üres
                        if (!newNev.trim().isEmpty()) {
                            child.setTextContent(newNev);
                        }
                    }
                    if (child.getNodeName().equals("Cim")) {
                        // Módosítjuk a címet is, ha nem üres
                        NodeList cimChildren = child.getChildNodes();
                        for (int k = 0; k < cimChildren.getLength(); k++) {
                            Node cimChild = cimChildren.item(k);
                            if (cimChild.getNodeName().equals("Varos")) {
                                // Csak akkor frissítjük, ha az új város nem üres
                                if (!newCim.trim().isEmpty()) {
                                    cimChild.setTextContent(newCim); // Példa: új város beállítása
                                }
                            }
                        }
                    }
                }
                System.out.println("Módosítva: Gyar kód: " + gyarKod + ", új név: " + (newNev.trim().isEmpty() ? "nem változott" : newNev) + ", új cím: " + (newCim.trim().isEmpty() ? "nem változott" : newCim));
                return; // Exit after modification
            }
        }
        if (!found) {
            System.out.println("Gyar kód nem található: " + gyarKod);
        }
    }
}