import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by afeher on 9/24/14.
 */
public class SomanetNodeElementContainer {

    private SomanetDeviceType somanetDeviceType;
    Map<String, List<Element>> xmlElementsMap = new HashMap<String, List<Element>>();

    public SomanetNodeElementContainer(SomanetDeviceType somanetDeviceType) {
        this.somanetDeviceType = somanetDeviceType;
    }

    public void addElements(String destination, Element element){
        if (xmlElementsMap.get(destination) == null)
            xmlElementsMap.put(destination, new ArrayList<Element>());
        xmlElementsMap.get(destination).add(element);
    }

    public List<Element> getPackageElements(){
        return xmlElementsMap.get("Packages");
    }

    public List<Element> getLinkElements(){
        return xmlElementsMap.get("Links");
    }

    public List<Element> getExternalDeviceElements(){
        return xmlElementsMap.get("ExternalDevices");
    }

    public List<Element> getJTAGChainElements(){
        return xmlElementsMap.get("JTAGChain");
    }

    public List<Element> getNodeElements(){
        return xmlElementsMap.get("Nodes");
    }

    public String getComNodeId(){
        return ((Element)xmlElementsMap.get("Packages").get(0).getElementsByTagName("Node").item(0)).getAttribute("Id");
    }

    public String getIfmNodeId(){
        return ((Element)xmlElementsMap.get("Packages").get(xmlElementsMap.get("Packages").size()-1).getElementsByTagName("Node").item(xmlElementsMap.get("Packages").get(xmlElementsMap.get("Packages").size()-1).getElementsByTagName("Node").getLength()-1)).getAttribute("Id");
    }
}
