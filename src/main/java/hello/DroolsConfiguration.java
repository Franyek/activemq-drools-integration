package hello;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.PrintWriter;

@Configuration
public class DroolsConfiguration {

    private static Logger log = LoggerFactory.getLogger(DroolsConfiguration.class);

    private void buildDrlFile(String ruleText, String filePath) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(filePath);
        printWriter.print(ruleText);
        printWriter.close();
    }

    public void updateRules(String rule) throws FileNotFoundException {
        buildDrlFile(rule, droolsFolder.getPath() + File.separator + "new_rule.drl");

        KieServices kieServices = getKieServices();
        KieFileSystem kfs = getKieFileSystem(kieServices);
        MessageConsumer.kc = getKieContainer(kieServices, kfs);
    }

    @Bean
    public KieContainer kieContainer(KieServices kieServices, KieFileSystem kfs) {
        return getKieContainer(kieServices, kfs);
    }

    private KieContainer getKieContainer(KieServices kieServices, KieFileSystem kfs) {
        KieRepository kieRepository = kieServices.getRepository();

        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);

        kieServices.newKieBuilder(kfs).buildAll();

        KieContainer c = kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
        return c;
    }

    @Bean
    public KieServices kieServices() {
        return KieServices.Factory.get();
    }

    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    @Value("${drools.folder}")
    private File droolsFolder;

    private KieFileSystem getKieFileSystem(KieServices kieServices) {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        loadFilesRecursively(kieFileSystem, droolsFolder);
        return kieFileSystem;
    }

    @Bean
    public KieFileSystem kieFileSystem(KieServices kieServices) {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        loadFilesRecursively(kieFileSystem, droolsFolder);
        return kieFileSystem;
    }

    private static void loadFilesRecursively(KieFileSystem kieFileSystem, File file) {
        if (file.isFile()) {
            Resource resource = ResourceFactory.newFileResource(file);
            log.info("Loading Drools resource {}", file);
            kieFileSystem.write(resource);
        } else if (file.isDirectory()) {
            for (final File entry : file.listFiles(filter())) {
                loadFilesRecursively(kieFileSystem, entry);
            }
        }
    }

    private static FilenameFilter filter() {
        return (dir, name) -> !name.startsWith("~");
    }

}
