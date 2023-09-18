package org.liu.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ResolvableType;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * https://blog.csdn.net/qq_42187215/article/details/130467076
 *
 * @Author lzs
 * @Date 2023/9/5 11:49
 **/
public class ResolvableTypeTest {

    @Test
    public void test() {
        // 直接获取拿不到泛型, 必须向上转型为 { PropBar } 才能拿到
        assertNull(ResolvableType.forClass(ComponentBar.class).resolveGeneric(0));

        // 向上转型为 { PropBar }
        assertEquals(RawBar.class, ResolvableType.forClass(ComponentBar.class).getSuperType().resolve());

        // 获取具体的泛型: org.springframework.stereotype.Component
        assertEquals(org.springframework.stereotype.Component.class,
                ResolvableType.forClass(ComponentBar.class).getSuperType().resolveGeneric(0)
        );

        // 获取具体的泛型: java.lang.String
        assertEquals(java.lang.String.class,
                ResolvableType.forClass(StringBar.class).getSuperType().resolveGeneric(0)
        );
    }

    @Test
    public void extra_raw_inject() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("org.liu");
        ConfigurableListableBeanFactory beanFactory = ac.getBeanFactory();

        // 1. 指定泛型为 MockKeyValueTemplate<String, String>
        String[] names1 = beanFactory.getBeanNamesForType(
                ResolvableType.forClassWithGenerics(MockKeyValueTemplate.class, String.class, String.class)
        );

        assertEquals(1, names1.length);
        assertEquals("stringKeyValueTemplate", names1[0]);

        // 2. 指定泛型为 MockKeyValueTemplate<String, Object>
        String[] names2 = beanFactory.getBeanNamesForType(
                ResolvableType.forClassWithGenerics(MockKeyValueTemplate.class, String.class, Object.class)
        );

        assertEquals(1, names2.length);
        assertEquals("objectKeyValueTemplate", names2[0]);

        // 3. 仅指定类型, 不指定泛型
        String[] names3 = beanFactory.getBeanNamesForType(
                ResolvableType.forClass(MockKeyValueTemplate.class)
        );

        assertEquals(2, names3.length);
        assertEquals("stringKeyValueTemplate", names3[0]);
        assertEquals("objectKeyValueTemplate", names3[1]);

        // 4. 指定嵌套泛型: Map<String, Map<String, Integer>>
        String[] names4 = beanFactory.getBeanNamesForType(
                ResolvableType.forClassWithGenerics(
                        Map.class,
                        ResolvableType.forClass(String.class),
                        ResolvableType.forClassWithGenerics(Map.class, String.class, Integer.class)
                )
        );

        assertEquals(1, names4.length);
        assertEquals("nestedMap", names4[0]);
    }

}
