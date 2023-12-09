package org.liu.demo.mongodb.util;

import cn.hutool.core.util.RandomUtil;
import org.liu.demo.mongodb.pojo.Company;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * @Author lzs
 * @Date 2022/11/5 10:23
 **/
public class BeanValueUtils {

    public static <T> void autoSetValue(T source) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class<?> clazz = source.getClass();
//        Field[] declaredFields = clazz.getDeclaredFields();
//        for (Field field : declaredFields) {
//            if (!field.getName().equals("id")) {
//                String setName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
//                Object value = generateValueByType(field.getDeclaringClass().getSimpleName(), field.getDeclaredAnnotations());
//                Method method = clazz.getDeclaredMethod(setName, field.getDeclaringClass());
//                method.invoke(source, value);
//            }
//        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().startsWith("set") && !method.getName().equals("setId")) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Object value = generateValueByType(parameterTypes[0].getSimpleName());
                method.invoke(source, value);
            }
        }
    }

    private static Object generateValueByType(String simpleName, Annotation[] annotations) {

        return null;
    }

    private static Object generateValueByType(String simpleTypeName) {
        if (simpleTypeName.equals("String")) {
            int randomInt = RandomUtil.randomInt(100);
            return RandomUtil.randomString(randomInt);
        } else if (simpleTypeName.equals("Long")) {
            return RandomUtil.randomLong(100000000L);
        } else if (simpleTypeName.equals("Integer")) {
            return RandomUtil.randomInt(Integer.MAX_VALUE);
        } else if (simpleTypeName.equals("Double")) {
            return RandomUtil.randomDouble(9999999.99);
        } else if (simpleTypeName.equals("Short")) {
            return RandomUtil.randomInt(Short.MAX_VALUE);
        } else if (simpleTypeName.equals("BigDecimal")) {
            return RandomUtil.randomBigDecimal(new BigDecimal("100000000"));
        } else if (simpleTypeName.equals("Date")) {
            return RandomUtil.randomDay(-3000, 3000);
        } else if (simpleTypeName.contains("[")) {
            int randomInt = RandomUtil.randomInt(10);
            String arrayTypeName = simpleTypeName.replace("[]", "");
            if (arrayTypeName.equals("String")) {
                String[] value = new String[randomInt];
                for (int i = 0; i < randomInt; i++) {
                    value[i] = RandomUtil.randomString(5);
                }
                return value;
            } else if (arrayTypeName.equals("Long")) {
                return new Long[]{RandomUtil.randomLong(100000000L)};
            } else if (arrayTypeName.equals("Integer")) {
                return new Integer[]{RandomUtil.randomInt(Integer.MAX_VALUE)};
            } else if (arrayTypeName.equals("Double")) {
                return new Double[]{RandomUtil.randomDouble()};
            } else if (arrayTypeName.equals("Short")) {
                return new Short[]{Integer.valueOf(RandomUtil.randomInt(Short.MAX_VALUE)).shortValue()};
            } else if (arrayTypeName.equals("BigDecimal")) {
                return new BigDecimal[]{RandomUtil.randomBigDecimal()};
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Company company = new Company();
        autoSetValue(company);
        System.out.println(company);
    }
}
