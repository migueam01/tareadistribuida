//package com.distribuida.config;
//
//import com.google.common.net.HostAndPort;
//import com.orbitz.consul.Consul;
//import com.orbitz.consul.model.agent.ImmutableRegCheck;
//import com.orbitz.consul.model.agent.ImmutableRegistration;
//import com.orbitz.consul.model.agent.Registration;
//import io.quarkus.runtime.ShutdownEvent;
//import io.quarkus.runtime.StartupEvent;
//import org.eclipse.microprofile.config.inject.ConfigProperty;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.event.Observes;
//import java.net.InetAddress;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//
//@ApplicationScoped
//public class AppEvents {
//
//    private String name="servidor";
//    private String id;
//
//    @ConfigProperty(name="quarkus.http.port", defaultValue="8081")
//    private Integer port;
//
//    @ConfigProperty(name="consul.ip", defaultValue="127.0.0.1")
//    private String consulIp;
//
//    @PostConstruct
//    public void inicializar(){
//        id = name + "-" + UUID.randomUUID().toString();
//    }
//
//
//    public void init(@Observes StartupEvent ev) throws Exception {
//        String ip = InetAddress.getLocalHost().getHostAddress();
//
//        System.out.println("**********************IP    :"+ip);
//        System.out.println("**********************PUERTO:"+port);
//        System.out.println("**********************CONSUL"+consulIp);
//
//        //registrar el servicio
//        Consul consulClient = Consul.builder().build();
//
//
//        String urlChequeo = String.format("http://127.0.0.1:%d/q/health/live",port);
//
//        String rule = String.format("traefik.http.routers.servidor.rule=PathPrefix(`/servidor`)");
//        String mid = String.format("traefik.http.routers.servidor.middlewares=mid01");
//        String mid1 = String.format("traefik.http.middlewares.mid01.stripprefix.prefixes=/servidor");
//        List<String> tags = Arrays.asList(rule,mid, mid1);
//
//        Registration service = ImmutableRegistration.builder()
//                .id(id)                 //instancia
//                .name(name)             //nombre
//                .address("127.0.0.1")   //IP
//                .port(port)             //puerto
//                .putMeta("puerto", port.toString())
//                .check(
//                        ImmutableRegCheck.builder().http(urlChequeo).interval("10s").deregisterCriticalServiceAfter("3s").build()
//                )
//                .tags(tags)
//                .build();
//        consulClient.agentClient().register(service);
//
//
//    }
//
//    public void destroy(@Observes ShutdownEvent ev){
//        System.out.println("**************terminando");
//        Consul consulClient = Consul.builder().build();
//        //eliminar el registro
//        consulClient.agentClient().deregister(id);
//
//
//    }
//}
