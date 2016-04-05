package de.netorium;

import org.jutils.jhardware.HardwareInfo;
import org.jutils.jhardware.model.*;
import org.jutils.jhardware.util.OSDetector;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Map;

public class App
{
    public static void main( String[] args ) {

        if (OSDetector.isMac()) {
            System.out.println("We are on a mac. This OS is not supported by now\n");
            return;
        }


        Map<String, String> fullBiosInfo, fullMotherboardInfo, fullProcessorInfo, fullOSInfo;

        try {
            BiosInfo biosInfo = HardwareInfo.getBiosInfo();
            fullBiosInfo = biosInfo.getFullInfo();
            printComponentInfo(biosInfo.getClass().getSimpleName(), fullBiosInfo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            MotherboardInfo motherboardInfo = HardwareInfo.getMotherboardInfo();
            fullMotherboardInfo = motherboardInfo.getFullInfo();
            printComponentInfo(motherboardInfo.getClass().getSimpleName(), fullMotherboardInfo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            ProcessorInfo processorInfo = HardwareInfo.getProcessorInfo();
            fullProcessorInfo = processorInfo.getFullInfo();
            printComponentInfo(processorInfo.getClass().getSimpleName(), fullProcessorInfo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            OSInfo osInfo = HardwareInfo.getOSInfo();
            fullOSInfo = osInfo.getFullInfo();
            printComponentInfo(osInfo.getClass().getSimpleName(), fullOSInfo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        getNetworkInterfaceId();
    }

    private static void getNetworkInterfaceId() {
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            while(networks.hasMoreElements()) {
                NetworkInterface network = networks.nextElement();
                if (!network.getName().contains("eth")) {
                    continue;
                }

                byte[] mac = network.getHardwareAddress();
                if(mac != null) {
                    System.out.print("Current MAC address : ");

                    StringBuilder sb = new StringBuilder();
                    sb.append(network.getName() + " ");
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    System.out.println(sb.toString());
                }
            }
        } catch (SocketException e){
            System.out.println("Exception receiving network interface id: " + e.getMessage());
        }
    }

    private static void printComponentInfo(String clazz, Map<String, String> info) {
        System.out.printf( "Info from: %s\n", clazz);

        for (Map.Entry<String, String> entry : info.entrySet())
        {
            System.out.printf("  %s: %s\n", entry.getKey(), entry.getValue());
        }
    }
}
