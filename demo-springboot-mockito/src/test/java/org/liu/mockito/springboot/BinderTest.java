package org.liu.mockito.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
public class BinderTest {

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;
    @Autowired
    private Environment environment;

    @Test
    void test() {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        // 1. Get a source (usually the Spring Environment)
        Iterable<ConfigurationPropertySource> source = ConfigurationPropertySources.get(environment);

        //另一个获取Binder的方式
//        Binder binder = Binder.get(environment);

// 2. Create the Binder
        Binder binder = new Binder(source);

// 3. Bind to a class
        BindResult<MyConfig> result = binder.bind("app.settings", MyConfig.class);
//        MyConfig config = binder.bind("app.settings", MyConfig.class).orElseGet(MyConfig::new); // Create empty POJO if missing

// 4. Access the result
        MyConfig config = result.get();
        System.out.println(config);

        // Binding to a List of Strings
        List<String> servers = binder.bind("app.servers", Bindable.listOf(String.class)).get();
        System.out.println(servers);
// Binding to a Map
        Map<String, AppUser> users = binder.bind("app.users", Bindable.mapOf(String.class, AppUser.class)).get();
        System.out.println(users);
    }

}
