import domfu7omc1015.DOMRead;
import domfu7omc1015.DomWriteFU7OMC;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "src/resources/FU7OMC_orarend.xml";

        DOMRead dm = new DOMRead(path);
        Document dom = dm.buildUpDom();
        DomWriteFU7OMC write = new DomWriteFU7OMC(dom.getDocumentElement());

        try {
            write.printXmlStructure(dom.getDocumentElement(),"");
            write.writeXmlFile(dom, "src/resources/FU7OMC_orarend1.xml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}