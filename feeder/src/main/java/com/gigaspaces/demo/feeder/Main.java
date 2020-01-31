package com.gigaspaces.demo.feeder;

import com.gigaspaces.client.WriteModifiers;
import com.gigaspaces.demo.common.*;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;

import java.util.Random;
import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static final String GS_LOOKUP_GROUPS = "GS_LOOKUP_GROUPS";
    public static final String GS_LOOKUP_LOCATORS = "GS_LOOKUP_LOCATORS";

    private static final String spaceName = "mySpace";

    private GigaSpace gigaSpace;

    static {
        // for debug purposes
        // 1. set from environment variable, XAP checks this for lookup settings
        System.out.println("lookup locators env variable: " + System.getenv(GS_LOOKUP_LOCATORS));
        System.out.println("lookup groups   env variable: " + System.getenv(GS_LOOKUP_GROUPS));
        // 2. set from System.property, XAP also checks this for lookup settings
        System.out.println("lookup locators System property: " + System.getProperty("com.gs.jini_lus.locators"));
        System.out.println("lookup groups   System property: " + System.getProperty("com.gs.jini_lus.groups"));
        // System.setProperty("com.gs.jini_lus.locators", System.getenv(GS_LOOKUP_LOCATORS));
        // System.setProperty("com.gs.jini_lus.groups", System.getenv(GS_LOOKUP_GROUPS));
    }

    private void initialize() {
        SpaceProxyConfigurer spaceProxyConfigurer = new SpaceProxyConfigurer(spaceName);
        gigaSpace = new GigaSpaceConfigurer(spaceProxyConfigurer).gigaSpace();


    }

    public void feeder() {
        for( int i=0; i < 100; i++ ) {
            Person person = new Person();
            Random rand = new Random();
            int age =  rand.nextInt(44) + 21;
            person.setAge(age);
            person.setFirstName("A" + String.valueOf(age));
            person.setLastName("Smith");
            person.setId(i);

            gigaSpace.write(person);
        }
    }


    public static void main(String[] args) {
        try {

            Main feeder = new Main();
            feeder.initialize();
            feeder.feeder();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}