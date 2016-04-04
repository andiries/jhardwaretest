package de.netorium;

import org.jutils.jhardware.HardwareInfo;
import org.jutils.jhardware.model.*;

import java.util.Map;

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

        System.out.println( "Hello World!" );
    }

    private static void printComponentInfo(String clazz, Map<String, String> info) {
        System.out.printf( "Info from: %s\n", clazz);

        for (Map.Entry<String, String> entry : info.entrySet())
        {
            System.out.printf("  %s: %s\n", entry.getKey(), entry.getValue());
        }
    }
}
