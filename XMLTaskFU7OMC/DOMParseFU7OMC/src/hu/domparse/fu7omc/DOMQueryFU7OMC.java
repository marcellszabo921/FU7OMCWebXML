package hu.domparse.fu7omc;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class DOMQueryFU7OMC {

    // A fő metódus, ami meghívja a queryXMLDocument metódust a megadott XML fájllal
    public static void main(String[] args) {
        queryXMLDocument("./XMLFU7OMC.xml");
    }

    // Metódus, amely az XML fájl beolvasására és feldolgozására szolgál
    private static void queryXMLDocument(String filePath) {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document document = dBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            //1. Az összes műszakvezető adatainak kiírása
            queryXMLAllMuszakVezetok(document);
            System.out.println("-------------------------------");
            //2. Az összes szakmunkás adatainak kiírása
            queryXMLAllSzakmunkasok(document);
            System.out.println("-------------------------------");
            //3. Az összes gyakornok adatainak kiírása
            queryXMLAllGyakornokok(document);
            System.out.println("-------------------------------");
            //4. Az összes megrendelő adatainak kiírása
            queryXMLAllMegrendelok(document);
            System.out.println("-------------------------------");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Műszakvezetők kiírása
    private static void queryXMLAllMuszakVezetok(Document document) {
        System.out.println("\n1. \033 Az összes műszakvezető adatainak kiírása" );

        NodeList muszakVezetoNodeList = document.getElementsByTagName("MuszakVezeto");
        for (int i = 0; i < muszakVezetoNodeList.getLength(); i++) {
            Node muszakVezetoNode = muszakVezetoNodeList.item(i);
            if (muszakVezetoNode.getNodeType() == Node.ELEMENT_NODE) {
                Element muszakVezetoElement = (Element) muszakVezetoNode;
                String kod = muszakVezetoElement.getAttribute("M_kod");
                String nev = muszakVezetoElement.getElementsByTagName("Nev").item(0).getTextContent();
                String vegzettseg = muszakVezetoElement.getElementsByTagName("Vegzettseg").item(0).getTextContent();
                String eletkor = muszakVezetoElement.getElementsByTagName("Eletkor").item(0).getTextContent();

                System.out.println("<MuszakVezeto Kod=\"" + kod + "\">");
                System.out.println("    <Nev>" + nev + "</Nev>");
                System.out.println("    <Vegzettseg>" + vegzettseg + "</Vegzettseg>");
                System.out.println("    <Eletkor>" + eletkor + "</Eletkor>");
                System.out.println("</MuszakVezeto>");
            }
        }
    }

    // Szakmunkások kiírása
    private static void queryXMLAllSzakmunkasok(Document document) {
        System.out.println("\n2. Az összes szakmunkás adatainak kiírása");

        NodeList szakmunkasNodeList = document.getElementsByTagName("Szakmunkas");
        for (int i = 0; i < szakmunkasNodeList.getLength(); i++) {
            Node szakmunkasNode = szakmunkasNodeList.item(i);
            if (szakmunkasNode.getNodeType() == Node.ELEMENT_NODE) {
                Element szakmunkasElement = (Element) szakmunkasNode;
                String kod = szakmunkasElement.getAttribute("Sz_kod");
                String nev = szakmunkasElement.getElementsByTagName("Nev").item(0).getTextContent();
                String vegzettseg = szakmunkasElement.getElementsByTagName("Vegzettseg").item(0).getTextContent();
                String eletkor = szakmunkasElement.getElementsByTagName("Eletkor").item(0).getTextContent();

                System.out.println("<Szakmunkas Kod=\"" + kod + "\">");
                System.out.println("    <Nev>" + nev + "</Nev>");
                System.out.println("    <Vegzettseg>" + vegzettseg + "</Vegzettseg>");
                System.out.println("    <Eletkor>" + eletkor + "</Eletkor>");
                System.out.println("</Szakmunkas>");
            }
        }
    }

    // Gyakornokok kiírása
    private static void queryXMLAllGyakornokok(Document document) {
        System.out.println("\n3. Az összes gyakornok adatainak kiírása");

        NodeList gyakornokNodeList = document.getElementsByTagName("Gyakornok");
        for (int i = 0; i < gyakornokNodeList.getLength(); i++) {
            Node gyakornokNode = gyakornokNodeList.item(i);
            if (gyakornokNode.getNodeType() == Node.ELEMENT_NODE) {
                Element gyakornokElement = (Element) gyakornokNode;
                String kod = gyakornokElement.getAttribute("Gy_kod");
                String nev = gyakornokElement.getElementsByTagName("Nev").item(0).getTextContent();
                String eletkor = gyakornokElement.getElementsByTagName("Eletkor").item(0).getTextContent();

                System.out.println("<Gyakornok Kod=\"" + kod + "\">");
                System.out.println("    <Nev>" + nev + "</Nev>");
                System.out.println("    <Eletkor>" + eletkor + "</Eletkor>");
                System.out.println("</Gyakornok>");
            }
        }
    }

    // Megrendelők kiírása
    private static void queryXMLAllMegrendelok(Document document) {
        System.out.println("\n4. Az összes megrendelő adatainak kiírása");

        NodeList megrendeloNodeList = document.getElementsByTagName("Megrendelo");
        for (int i = 0; i < megrendeloNodeList.getLength(); i++) {
            Node megrendeloNode = megrendeloNodeList.item(i);
            if (megrendeloNode.getNodeType() == Node.ELEMENT_NODE) {
                Element megrendeloElement = (Element) megrendeloNode;
                String kod = megrendeloElement.getAttribute("Mkod");
                String nev = megrendeloElement.getElementsByTagName("Nev").item(0).getTextContent();
                String varos = megrendeloElement.getElementsByTagName("Varos").item(0).getTextContent();
                String utca = megrendeloElement.getElementsByTagName("Utca").item(0).getTextContent();
                String hazszam = megrendeloElement.getElementsByTagName("Hazszam").item(0).getTextContent();
                String telefonszam = megrendeloElement.getElementsByTagName("Telefonszam").item(0).getTextContent();

                System.out.println("<Megrendelo Kod=\"" + kod + "\">");
                System.out.println("    <Nev>" + nev + "</Nev>");
                System.out.println("    <Cim>");
                System.out.println("        <Varos>" + varos + "</Varos>");
                System.out.println("        <Utca>" + utca + "</Utca>");
                System.out.println("        <Hazszam>" + hazszam + "</Hazszam>");
                System.out.println("    </Cim>");
                System.out.println("    <Telefonszam>" + telefonszam + "</Telefonszam>");
                System.out.println("</Megrendelo>");
            }
        }
    }
}
