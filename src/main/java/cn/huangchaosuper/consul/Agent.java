package cn.huangchaosuper.consul;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chaohuang on 07/02/2017.
 */
public class Agent {
    private static final Logger logger = LoggerFactory.getLogger(Agent.class);

    public static void premain(String agentArs, Instrumentation inst)
            throws IOException {
        ConfigUtils.initProperties(agentArs);
        logger.info("consul host = {}",ConfigUtils.getProperty("CONSUL_HOST"));
        logger.info("consul port = {}",ConfigUtils.getProperty("CONSUL_PORT","8500"));
        new RegisterConsul().register();
        logger.info("agent load completed");
    }
}
