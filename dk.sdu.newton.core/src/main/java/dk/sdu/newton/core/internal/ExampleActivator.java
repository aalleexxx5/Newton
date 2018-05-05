package dk.sdu.newton.core.internal;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


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
	    LwjglApplicationConfiguration cfg =
			    new LwjglApplicationConfiguration();
	    cfg.title = "Newton";
	    cfg.width = 800;
	    cfg.height = 720;
	    cfg.useGL30 = false;
	    cfg.resizable = false;
	
	    new LwjglApplication(new dk.sdu.newton.core.internal.Game(), cfg);
	
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop( BundleContext bc ) throws Exception {
    
    }
}

