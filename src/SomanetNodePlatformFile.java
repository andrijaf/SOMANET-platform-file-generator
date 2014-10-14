import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by afeher on 9/24/14.
 */
public class SomanetNodePlatformFile {

    private SomanetCoreDeviceType somanetCoreDeviceType;
    Map<String, List<Element>> xmlElementsMap = new HashMap<String, List<Element>>();

    public SomanetNodePlatformFile(SomanetCoreDeviceType somanetCoreDeviceType) {
        this.somanetCoreDeviceType = somanetCoreDeviceType;
    }

    public void addElements(Element element){
        if (xmlElementsMap.get(element.getTagName()) == null)
            xmlElementsMap.put(element.getTagName(), new ArrayList<Element>());
        xmlElementsMap.get(element.getTagName()).add(element);
    }

    public List<Element> getElementsByTagName(String xmlTagName){
        return xmlElementsMap.get(xmlTagName);
    }

    public String getComNodeId(){
        return ((Element)xmlElementsMap.get("Package").get(0).getElementsByTagName("Node").item(0)).getAttribute("Id");
    }

    public String getIfmNodeId(){
        return ((Element)xmlElementsMap.get("Package").get(xmlElementsMap.get("Package").size()-1).getElementsByTagName("Node").item(xmlElementsMap.get("Package").get(xmlElementsMap.get("Package").size()-1).getElementsByTagName("Node").getLength()-1)).getAttribute("Id");
    }
}
