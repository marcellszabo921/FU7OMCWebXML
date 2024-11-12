package hu.domparse.fu7omc;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class DOMReadFU7OMC {

    public static void main(String[] args) {
        try {
            File inputFile = new File("XMLFU7OMC.xml");
            Document document = parseXML(inputFile);

            queryXMLGyarkodok(document);
            queryXMLMuszakVezetok(document);
            queryXMLSzakmunkasok(document);
            queryXMLGyakornokok(document);
            queryXMLMegrendelok(document);
            queryXMLRendelesek(document);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // XML beolvasása
    public static Document parseXML(File file) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(file);
    }

    // Gyárak kiírása
    private static void queryXMLGyarkodok(Document document) {
        NodeList gyarNodeList = document.getElementsByTagName("Gyar");
        for (int i = 0; i < gyarNodeList.getLength(); i++) {
            Node gyarNode = gyarNodeList.item(i);
            if (gyarNode.getNodeType() == Node.ELEMENT_NODE) {
                Element gyarElement = (Element) gyarNode;
                String gyarkod = gyarElement.getAttribute("Gyarkod");
                String nev = gyarElement.getElementsByTagName("Nev").item(0).getTextContent();
                String varos = gyarElement.getElementsByTagName("Varos").item(0).getTextContent();
                String utca = gyarElement.getElementsByTagName("Utca").item(0).getTextContent();
                String hazszam = gyarElement.getElementsByTagName("Hazszam").item(0).getTextContent();
                String adoszam = gyarElement.getElementsByTagName("Adoszam").item(0).getTextContent();

                System.out.println("-------------------------------");
                System.out.println("Gyar:");
                System.out.println("    Gyarkod=\"" + gyarkod + "\"");
                System.out.println("    Nev: " + nev);
                System.out.println("    Cim:");
                System.out.println("        Varos: " + varos);
                System.out.println("        Utca: " + utca);
                System.out.println("        Hazszam: " + hazszam);
                System.out.println("    Adoszam: " + adoszam);
            }
        }
    }

    // Műszakvezetők kiírása
    private static void queryXMLMuszakVezetok(Document document) {
        NodeList muszakVezetoNodeList = document.getElementsByTagName("MuszakVezeto");
        for (int i = 0; i < muszakVezetoNodeList.getLength(); i++) {
            Node muszakVezetoNode = muszakVezetoNodeList.item(i);
            if (muszakVezetoNode.getNodeType() == Node.ELEMENT_NODE) {
                Element muszakVezetoElement = (Element) muszakVezetoNode;
                String M_kod = muszakVezetoElement.getAttribute("M_kod");
                String nev = muszakVezetoElement.getElementsByTagName("Nev").item(0).getTextContent();
                String vegzettseg = muszakVezetoElement.getElementsByTagName("Vegzettseg").item(0).getTextContent();
                String eletkor = muszakVezetoElement.getElementsByTagName("Eletkor").item(0).getTextContent();

                System.out.println("-------------------------------");
                System.out.println("MuszakVezeto:");
                System.out.println("    MuszakVezetoKod=\"" + M_kod + "\"");
                System.out.println("    Nev: " + nev);
                System.out.println("    Vegzettseg: " + vegzettseg);
                System.out.println("    Eletkor: " + eletkor);
            }
        }
    }

    // Szakmunkások kiírása
    private static void queryXMLSzakmunkasok(Document document) {
        NodeList szakmunkasNodeList = document.getElementsByTagName("Szakmunkas");
        for (int i = 0; i < szakmunkasNodeList.getLength(); i++) {
            Node szakmunkasNode = szakmunkasNodeList.item(i);
            if (szakmunkasNode.getNodeType() == Node.ELEMENT_NODE) {
                Element szakmunkasElement = (Element) szakmunkasNode;
                String Sz_kod = szakmunkasElement.getAttribute("Sz_kod");
                String nev = szakmunkasElement.getElementsByTagName("Nev").item(0).getTextContent();
                String vegzettseg = szakmunkasElement.getElementsByTagName("Vegzettseg").item(0).getTextContent();
                String eletkor = szakmunkasElement.getElementsByTagName("Eletkor").item(0).getTextContent();

                System.out.println("-------------------------------");
                System.out.println("Szakmunkas:");
                System.out.println("    SzakmunkasKod=\"" + Sz_kod + "\"");
                System.out.println("    Nev: " + nev);
                System.out.println("    Vegzettseg: " + vegzettseg);
                System.out.println("    Eletkor: " + eletkor);
            }
        }
    }

    // Gyakornokok kiírása
    private static void queryXMLGyakornokok(Document document) {
        NodeList gyakornokNodeList = document.getElementsByTagName("Gyakornok");
        for (int i = 0; i < gyakornokNodeList.getLength(); i++) {
            Node gyakornokNode = gyakornokNodeList.item(i);
            if (gyakornokNode.getNodeType() == Node.ELEMENT_NODE) {
                Element gyakornokElement = (Element) gyakornokNode;
                String Gy_kod = gyakornokElement.getAttribute("Gy_kod");
                String nev = gyakornokElement.getElementsByTagName("Nev").item(0).getTextContent();
                String eletkor = gyakornokElement.getElementsByTagName("Eletkor").item(0).getTextContent();

                System.out.println("-------------------------------");
                System.out.println("Gyakornok:");
                System.out.println("    GyakornokKod=\"" + Gy_kod + "\"");
                System.out.println("    Nev: " + nev);
                System.out.println("    Eletkor: " + eletkor);

                NodeList gyakorlatNodes = gyakornokElement.getElementsByTagName("Gyakorlat");
                for (int j = 0; j < gyakorlatNodes.getLength(); j++) {
                    Element gyakorlatElement = (Element) gyakorlatNodes.item(j);
                    String kezdet = gyakorlatElement.getElementsByTagName("Kezdete").item(0).getTextContent();
                    String idotartam = gyakorlatElement.getElementsByTagName("Idotartam").item(0).getTextContent();

                    System.out.println("    Gyakorlat:");
                    System.out.println("        Kezdete: " + kezdet);
                    System.out.println("        Idotartam: " + idotartam);
                }
            }
        }
    }

    // Megrendelők kiírása
    private static void queryXMLMegrendelok(Document document) {
        NodeList megrendeloNodeList = document.getElementsByTagName("Megrendelo");
        for (int i = 0; i < megrendeloNodeList.getLength(); i++) {
            Node megrendeloNode = megrendeloNodeList.item(i);
            if (megrendeloNode.getNodeType() == Node.ELEMENT_NODE) {
                Element megrendeloElement = (Element) megrendeloNode;
                String Mkod = megrendeloElement.getAttribute("Mkod");
                String nev = megrendeloElement.getElementsByTagName("Nev").item(0).getTextContent();
                String varos = megrendeloElement.getElementsByTagName("Varos").item(0).getTextContent();
                String utca = megrendeloElement.getElementsByTagName("Utca").item(0).getTextContent();
                String hazszam = megrendeloElement.getElementsByTagName("Hazszam").item(0).getTextContent();
                String telefonszam = megrendeloElement.getElementsByTagName("Telefonszam").item(0).getTextContent();

                System.out.println("-------------------------------");
                System.out.println("Megrendelo:");
                System.out.println("    MegrendeloKod=\"" + Mkod + "\"");
                System.out.println("    Nev: " + nev);
                System.out.println("    Cim:");
                System.out.println("        Varos: " + varos);
                System.out.println("        Utca: " + utca);
                System.out.println("        Hazszam: " + hazszam);
                System.out.println("    Telefonszam: " + telefonszam);
            }
        }
    }

    // Rendelések kiírása
    private static void queryXMLRendelesek(Document document) {
        NodeList rendelesNodeList = document.getElementsByTagName("Rendeles");
        for (int i = 0; i < rendelesNodeList.getLength(); i++) {
            Node rendelesNode = rendelesNodeList.item(i);
            if (rendelesNode.getNodeType() == Node.ELEMENT_NODE) {
                Element rendelesElement = (Element) rendelesNode;
                String GyarMR = rendelesElement.getAttribute("GyarMR");
                String Rkod = rendelesElement.getAttribute("Rkod");
                NodeList termekNodes = rendelesElement.getElementsByTagName("Termek");

                System.out.println("-------------------------------");
                System.out.println("Rendeles:");
                System.out.println("    RendelesKodok=");
                System.out.println("        GyarMR= " + GyarMR);
                System.out.println("        Rkod= " + Rkod);
                for (int j = 0; j < termekNodes.getLength(); j++) {
                    String termek = termekNodes.item(j).getTextContent();
                    System.out.println("    Termek: " + termek);
                }
                String osszeg = rendelesElement.getElementsByTagName("Osszeg").item(0).getTextContent();
                String datum = rendelesElement.getElementsByTagName("Datum").item(0).getTextContent();
                System.out.println("    Osszeg: " + osszeg);
                System.out.println("    Datum: " + datum);
            }
        }
    }
}
