package hu.domparse.fu7omc;

import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DOMModifyFU7OMC {
    public static void main(String[] args) {
        // Módosító metódus meghívása a megadott XML fájlra
        modifyXMLElements("./XMLFU7OMC.xml");
    }

    private static void modifyXMLElements(String filePath) {
        try {
            // XML fájl beolvasása
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(xmlFile);

            // 1. Módosítás: Műszakvezető név módosítása
            NodeList muszakVezetoList = document.getElementsByTagName("MuszakVezeto");
            Element muszakVezetoElement = (Element) muszakVezetoList.item(0); // Első műszakvezető
            muszakVezetoElement.getElementsByTagName("Nev").item(0).setTextContent("Trombitás Elemér");

            // 2. Módosítás: Szakmunkás életkorának módosítása
            NodeList szakmunkasList = document.getElementsByTagName("Szakmunkas");
            Element szakmunkasElement = (Element) szakmunkasList.item(2); // Harmadik szakmunkás
            szakmunkasElement.getElementsByTagName("Eletkor").item(0).setTextContent("56");

            // 3. Módosítás: Gyakornok név módosítása
            NodeList gyakornokList = document.getElementsByTagName("Gyakornok");
            Element gyakornokElement = (Element) gyakornokList.item(1); // Második gyakornok
            gyakornokElement.getElementsByTagName("Nev").item(0).setTextContent("Hegedűs Emese");

            // 4. Módosítás: Megrendelő címének frissítése
            NodeList megrendeloList = document.getElementsByTagName("Megrendelo");
            Element megrendeloElement = (Element) megrendeloList.item(0); // Első megrendelő
            Element cimElement = (Element) megrendeloElement.getElementsByTagName("Cim").item(0);
            cimElement.getElementsByTagName("Varos").item(0).setTextContent("Debrecen");
            cimElement.getElementsByTagName("Utca").item(0).setTextContent("Kossuth Lajos utca");
            cimElement.getElementsByTagName("Hazszam").item(0).setTextContent("101");

            // 5. Módosítás: Rendelés termék árának módosítása
            NodeList rendelesList = document.getElementsByTagName("Rendeles");
            Element rendelesElement = (Element) rendelesList.item(0); // Első rendelés
            NodeList osszegNodes = rendelesElement.getElementsByTagName("Osszeg");
            Element osszegElement = (Element) osszegNodes.item(0); // The <Osszeg> element
            osszegElement.setTextContent("19999"); // Update the value

            // A módosított XML kiírása a konzolra
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            // A módosított XML fájl konzolra történő kiíratása
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}