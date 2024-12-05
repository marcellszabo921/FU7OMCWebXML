package hu.domparse.fu7omc;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

import org.w3c.dom.*;

public class DOMWriteFU7OMC {

    public static void main(String[] args) {
        // Metódus meghívása az XML fájl generálásához
        writeFactoryDataToXML();
    }

    private static void writeFactoryDataToXML() {
        try {
            // Dokumentum létrehozása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Gyökérelem létrehozása
            Element rootElement = document.createElement("Ipar");
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xsi:noNamespaceSchemaLocation", "XMLSchemaFU7OMC.xsd");
            document.appendChild(rootElement);

            // Gyar elem hozzáadása
            Element gyar = document.createElement("Gyar");
            gyar.setAttribute("Gyarkod", "A1");

            // Gyar adatai
            addTextElement(document, gyar, "Nev", "AcélMűvek");
            addCimElement(document, gyar);
            addTextElement(document, gyar, "Adoszam", "111111111-1-11");

            // Műszakvezetők
            addMuszakVezeto(document, gyar, "M1", "Trombitás Elemér", "Lakatos", 62);
            addMuszakVezeto(document, gyar, "M2", "Nagy Ferenc", "Gépész", 55);

            // Szakmunkások
            addSzakmunkas(document, gyar, "SZ1", "Szabó Gábor", "Kisegítő", 32);
            addSzakmunkas(document, gyar, "SZ2", "Kovács László", "Villanyszerelő", 45);

            // Gyakornokok
            addGyakornok(document, gyar, "Gyak1", "Illés Dániel", 20, "2024.02.14","8 hét");
            addGyakornok(document, gyar, "Gyak2", "Hegedűs Emese", 22,"2024.02.14","8 hét");

            // Megrendelők
            addMegrendelo(document, gyar, "M1","Máv Zrt.", "Budapest", "Máv", "32", "+36 20 456 4353");
            addMegrendelo(document, gyar, "M2","Volánbusz Zrt.", "Debrecen", "Nagyerdei körút", "5", "+36 30 123 4567");

            // Rendelések
            addRendeles(document, gyar, "GYR1","R1","Vasúti Sín", "Acél Lemez", "Csavar", "12345654.00", "2024-03-21");
            addRendeles(document, gyar, "GYR2","R2","Betongerenda", "Acélcső", "", "5789000.00", "2024-04-10");

            // A gyár elem hozzáadása a gyökér elemhez
            rootElement.appendChild(gyar);

            // Dokumentum kiírása fájlba és konzolra
            writeXMLToFileAndConsole(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Gyar címének hozzáadása
    private static void addCimElement(Document document, Element gyar) {
        Element cim = document.createElement("Cim");

        // Város, Utca, Házszám
        addTextElement(document, cim, "Varos", "Miskolc");
        addTextElement(document, cim, "Utca", "Szép lány");
        addTextElement(document, cim, "Hazszam", "54");

        gyar.appendChild(cim);
    }

    // Műszakvezető hozzáadása
    private static void addMuszakVezeto(Document document, Element gyar, String kod, String nev, String vegzettseg, int eletkor) {
        Element muszakVezeto = document.createElement("MuszakVezeto");
        muszakVezeto.setAttribute("M_kod", kod);

        addTextElement(document, muszakVezeto, "Nev", nev);
        addTextElement(document, muszakVezeto, "Vegzettseg", vegzettseg);
        addTextElement(document, muszakVezeto, "Eletkor", String.valueOf(eletkor));

        gyar.appendChild(muszakVezeto);
    }

    // Szakmunkás hozzáadása
    private static void addSzakmunkas(Document document, Element gyar, String kod, String nev, String vegzettseg, int eletkor) {
        Element szakmunkas = document.createElement("Szakmunkas");
        szakmunkas.setAttribute("Sz_kod", kod);

        addTextElement(document, szakmunkas, "Nev", nev);
        addTextElement(document, szakmunkas, "Vegzettseg", vegzettseg);
        addTextElement(document, szakmunkas, "Eletkor", String.valueOf(eletkor));

        gyar.appendChild(szakmunkas);
    }

    // Gyakornok hozzáadása
    private static void addGyakornok(Document document, Element gyar, String kod, String nev, int eletkor, String kezd, String ido) {
        Element gyakornok = document.createElement("Gyakornok");
        gyakornok.setAttribute("Gy_kod", kod);

        addTextElement(document, gyakornok, "Nev", nev);
        addTextElement(document, gyakornok, "Eletkor", String.valueOf(eletkor));
        Element gyak = document.createElement("Gyakorlat");
        addTextElement(document, gyak, "Kezdete", kezd);
        addTextElement(document, gyak, "Idotartam", ido);
        gyakornok.appendChild(gyak);
        gyar.appendChild(gyakornok);
    }

    // Megrendelő hozzáadása
    private static void addMegrendelo(Document document, Element gyar, String kod, String nev, String varos, String utca, String hazszam, String telefonszam) {
        Element megrendelo = document.createElement("Megrendelo");
        megrendelo.setAttribute("Mkod", kod);

        addTextElement(document, megrendelo, "Nev", nev);
        Element cim = document.createElement("Cim");
        addTextElement(document, cim, "Varos", varos);
        addTextElement(document, cim, "Utca", utca);
        addTextElement(document, cim, "Hazszam", hazszam);
        megrendelo.appendChild(cim);

        addTextElement(document, megrendelo, "Telefonszam", telefonszam);

        gyar.appendChild(megrendelo);
    }

    // Rendelés hozzáadása
    private static void addRendeles(Document document, Element gyar, String kod1, String kod2, String termek1, String termek2, String termek3, String osszeg, String datum) {
        Element rendeles = document.createElement("Rendeles");
        rendeles.setAttribute("GyarMR", kod1);
        rendeles.setAttribute("Rkod", kod2);

        addTextElement(document, rendeles, "Termek", termek1);
        if (!termek2.isEmpty()) addTextElement(document, rendeles, "Termek", termek2);
        if (!termek3.isEmpty()) addTextElement(document, rendeles, "Termek", termek3);
        addTextElement(document, rendeles, "Osszeg", osszeg);
        addTextElement(document, rendeles, "Datum", datum);

        gyar.appendChild(rendeles);
    }

    // Szöveg elem hozzáadása
    private static void addTextElement(Document document, Element parentElement, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(textContent));
        parentElement.appendChild(element);
    }

    // Az XML fájlba és konzolra történő írás
    private static void writeXMLToFileAndConsole(Document document) {
        try {
            // Transformer létrehozása és beállítása
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // Fájlba írás
            File xmlFile = new File("XMLFU7OMC2.xml");
            transformer.transform(new DOMSource(document), new StreamResult(xmlFile));

            // Konzolra írás
            transformer.transform(new DOMSource(document), new StreamResult(System.out));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
