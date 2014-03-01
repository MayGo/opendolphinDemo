package ee.maix.demo;

import org.opendolphin.core.client.comm.JavaFXUiThreadHandler;
import org.opendolphin.core.comm.DefaultInMemoryConfig;

public class ApplicationInMemoryStarter {
    public static void main(String[] args) throws Exception {
        DefaultInMemoryConfig config = new DefaultInMemoryConfig();
        config.getServerDolphin().registerDefaultActions();
        config.getClientDolphin().getClientConnector().setUiThreadHandler(new JavaFXUiThreadHandler());
        registerApplicationActions(config);
        ee.maix.demo.Application.clientDolphin = config.getClientDolphin();
        javafx.application.Application.launch(ee.maix.demo.Application.class);
    }

    private static void registerApplicationActions(DefaultInMemoryConfig config) {
        config.getServerDolphin().register(new ApplicationDirector());
    }

}
