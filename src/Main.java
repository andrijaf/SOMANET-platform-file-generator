import java.util.Scanner;

/**
 * Created by afeher on 9/21/14.
 */

public class Main {

    public static void main(String[] args) {

        PlatformFile platformFile = new PlatformFile();

        Boolean exit = false;

        Scanner in = new Scanner(System.in);

        // Add first node
        Integer firstNodeOption;
        System.out.print("Choose first node's SOMANET Core module:\n" +
                "\t(1) SOMANET Core C22\n" +
                "\t(2) SOMANET Core C21\n" +
                "\t(0) Exit\n:");
        firstNodeOption = in.nextInt();
        switch (firstNodeOption) {
            case 1:
                platformFile.addSomanetNode(SomanetCoreDeviceType.C22);
                break;
            case 2:
                platformFile.addSomanetNode(SomanetCoreDeviceType.C21);
                break;
            case 0:
                exit = true;
                break;
        }

        while (!exit) {

            // Add first node
            Integer nodeOption;
            System.out.print("Choose next node's SOMANET Core module:\n" +
                    "\t(1) SOMANET Core C22\n" +
                    "\t(2) SOMANET Core C21\n" +
                    "\t(0) Save & Exit\n:");
            nodeOption = in.nextInt();
            SomanetCoreDeviceType nodeType;
            SomanetNodeDXLinkEndpoint linkEndpoint1;
            SomanetNodeDXLinkEndpoint linkEndpoint2;
            switch (nodeOption) {
                case 1:
                    nodeType = SomanetCoreDeviceType.C22;
                    System.out.print("Choose previous node's DX port");
                    linkEndpoint1 = readDXPortOption(in);
                    System.out.print("Choose current node's DX port");
                    linkEndpoint2 = readDXPortOption(in);
                    platformFile.addSomanetNode(nodeType, linkEndpoint1, linkEndpoint2);
                    break;
                case 2:
                    nodeType = SomanetCoreDeviceType.C21;
                    System.out.print("Choose last node's DX port");
                    linkEndpoint1 = readDXPortOption(in);
                    System.out.print("Choose current node's DX port");
                    linkEndpoint2 = readDXPortOption(in);
                    platformFile.addSomanetNode(nodeType, linkEndpoint1, linkEndpoint2);
                    break;
                case 0:
                    System.out.print("Enter file name (default: generated.xn): ");
                    in.nextLine();
                    String fileName = in.nextLine();
                    if (!fileName.isEmpty()) {
                        if (!fileName.endsWith(".xn")){
                            fileName += ".xn";
                        }
                    } else {
                        fileName = "generated.xn";
                    }
                    platformFile.writeToFile("/home/afeher/workspace-java/SOMANET-platform-file-generator/export/" + fileName);
                    exit = true;
                    break;
            }
        }


    }

    public static SomanetNodeDXLinkEndpoint readDXPortOption(Scanner in) {
        Integer portOption;
        System.out.print("\n\t(1) COM_DX_A\n" +
                "\t(2) COM_DX_B\n" +
                "\t(3) IFM_DX_A\n" +
                "\t(4) IFM_DX_B (Available on: IFM Drive DC100, IFM Drive DC300)\n" +
                ":");
        portOption = in.nextInt();
        SomanetNodeDXLinkEndpoint port = null;
        switch (portOption) {
            case 1:
                port = SomanetNodeDXLinkEndpoint.COM_A;
                break;
            case 2:
                port = SomanetNodeDXLinkEndpoint.COM_B;
                break;
            case 3:
                port = SomanetNodeDXLinkEndpoint.IFM_A;
                break;
            case 4:
                port = SomanetNodeDXLinkEndpoint.IFM_B;
                break;
        }
        return port;
    }
}