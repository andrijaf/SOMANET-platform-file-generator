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
                platformFile.addSomanetNode(SomanetDeviceType.C22);
                break;
            case 2:
                platformFile.addSomanetNode(SomanetDeviceType.C21);
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
            System.out.print(nodeOption.toString());
            SomanetDeviceType nodeType;
            SomanetDXLink linkEndpoint1;
            SomanetDXLink linkEndpoint2;
            switch (nodeOption) {
                case 1:
                    nodeType = SomanetDeviceType.C22;
                    System.out.print("Choose last node's DX port");
                    linkEndpoint1 = readDXPortOption(in);
                    System.out.print("Choose current node's DX port");
                    linkEndpoint2 = readDXPortOption(in);
                    platformFile.addSomanetNode(nodeType, linkEndpoint1, linkEndpoint2);
                    break;
                case 2:
                    nodeType = SomanetDeviceType.C21;
                    System.out.print("Choose last node's DX port");
                    linkEndpoint1 = readDXPortOption(in);
                    System.out.print("Choose current node's DX port");
                    linkEndpoint2 = readDXPortOption(in);
                    platformFile.addSomanetNode(nodeType, linkEndpoint1, linkEndpoint2);
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }

        platformFile.writeToFile("/home/afeher/workspace-java/SOMANET-platform-file-generator/resources/generated.xn");

    }

    public static SomanetDXLink readDXPortOption(Scanner in) {
        Integer portOption;
        System.out.print("\n\t(1) COM_DX_A\n" +
                "\t(2) COM_DX_B\n" +
                "\t(3) IFM_DX_A\n" +
                "\t(4) IFM_DX_B (Available on: IFM Drive DC100, IFM Drive DC300)\n" +
                ":");
        portOption = in.nextInt();
        SomanetDXLink port = null;
        switch (portOption) {
            case 1:
                port = SomanetDXLink.COM_A;
                break;
            case 2:
                port = SomanetDXLink.COM_B;
                break;
            case 3:
                port = SomanetDXLink.IFM_A;
                break;
            case 4:
                port = SomanetDXLink.IFM_B;
                break;
        }
        return port;
    }
}