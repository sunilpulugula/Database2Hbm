package hibernate;

import java.io.File;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.ant.Hbm2HbmXmlExporterTask;

import hibernate.config.ConfigurationAwareToolTask;
import hibernate.config.ExtendedJDBCConfigurationTask;

/**
 * Created by sunilp on 9/7/15.
 */
public class DB2HBMDemo {

    public static void main(String args[]) {

        try {

            File configFile = new File("/home/sunilp/IdeaProjects/Database2HbmCodeGeneration/src/main/resources/configuration/hibernate.cfg.xml");
            Configuration configuration = getJDBCConfiguration(configFile);
            ConfigurationAwareToolTask configurationAwareToolTask = new ConfigurationAwareToolTask(configuration);

            Hbm2HbmXmlExporterTask hbm2HbmXmlExporter = new Hbm2HbmXmlExporterTask(configurationAwareToolTask);
            hbm2HbmXmlExporter.setDestdir(new File("/home/sunilp/codeGeneration"));
            hbm2HbmXmlExporter.execute();


            System.out.println("Successfully Hbm files created...");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static Configuration getJDBCConfiguration(File configFile)
    {
        ExtendedJDBCConfigurationTask jdbcConfiguration = new ExtendedJDBCConfigurationTask(configFile);
        jdbcConfiguration.setPackageName("com.hrdb");
        jdbcConfiguration.setDetectOptimisticLock(false);
        jdbcConfiguration.setReverseStrategy("org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy");
        return jdbcConfiguration.getConfiguration();
    }


}
