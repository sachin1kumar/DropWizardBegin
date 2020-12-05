import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropWizardApplication extends Application<DropWizardConfiguration> {

    public static void main(String[] args) throws Exception {
        new DropWizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "Hello Drop Wizard";
    }

    @Override
    public void initialize(Bootstrap<DropWizardConfiguration> bootstrap) {

    }

    @Override
    public void run(DropWizardConfiguration dropWizardConfiguration, Environment environment) throws Exception {
        final DropWizardResource resource = new DropWizardResource(
                dropWizardConfiguration.getTemplate(),
                dropWizardConfiguration.getDefaultName());
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(dropWizardConfiguration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
