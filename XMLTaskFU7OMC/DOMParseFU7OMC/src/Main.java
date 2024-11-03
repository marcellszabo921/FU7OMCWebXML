import hu.domparse.fu7omc.DOMReadFU7OMC;
import hu.domparse.fu7omc.DOMWriteFU7OMC;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "src/resources/XMLFU7OMC.xml";

        DOMReadFU7OMC dm = new DOMReadFU7OMC(path);
        Document dom = dm.buildUpDom();
        DOMWriteFU7OMC write = new DOMWriteFU7OMC(dom.getDocumentElement());

        try {
            write.printXmlStructure(dom.getDocumentElement(),"");
            write.writeXmlFile(dom, "src/resources/XMLFU7OMC1.xml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}