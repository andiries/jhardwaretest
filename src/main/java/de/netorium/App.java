package de.netorium;

import org.jutils.jhardware.HardwareInfo;
import org.jutils.jhardware.model.*;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import static java.net.NetworkInterface.getNetworkInterfaces;

public class App
{
    public static void main( String[] args ) {

        Map<String, String> fullBiosInfo = null, fullMotherboardInfo = null, fullProcessorInfo = null,
                fullOSInfo = null;

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

        getNetworkMac();
    }

    private static void getNetworkMac() {
        InetAddress ip;
        try {

            ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            System.out.print("Current MAC address : ");

            printMACAddress(mac);

        } catch (UnknownHostException e) {

            e.printStackTrace();

        } catch (SocketException e){

            e.printStackTrace();

        }
    }

    private static void printMACAddress(byte[] mac) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }
        System.out.println(sb.toString());
    }

    private static void printComponentInfo(String clazz, Map<String, String> info) {
        System.out.printf( "Info from: %s\n", clazz);

        for (Map.Entry<String, String> entry : info.entrySet())
        {
            System.out.printf("  %s: %s\n", entry.getKey(), entry.getValue());
        }
    }
}
