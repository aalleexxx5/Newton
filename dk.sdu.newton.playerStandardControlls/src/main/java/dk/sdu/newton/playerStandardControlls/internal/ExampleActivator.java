package dk.sdu.newton.playerStandardControlls.internal;

import java.util.Dictionary;
import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import dk.sdu.newton.playerStandardControlls.ExampleService;

/**
 * Extension of the default OSGi bundle activator
 */
public final class ExampleActivator
    implements BundleActivator
{
    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start( BundleContext bc )
        throws Exception
    {
        System.out.println( "STARTING dk.sdu.newton.playerStandardControlls" );

        Dictionary props = new Properties();
        // add specific service properties here...

        System.out.println( "REGISTER dk.sdu.newton.playerStandardControlls.ExampleService" );

        // Register our example service implementation in the OSGi service registry
        bc.registerService( ExampleService.class.getName(), new ExampleServiceImpl(), props );
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop( BundleContext bc )
        throws Exception
    {
        System.out.println( "STOPPING dk.sdu.newton.playerStandardControlls" );

        // no need to unregister our service - the OSGi framework handles it for us
    }
}

