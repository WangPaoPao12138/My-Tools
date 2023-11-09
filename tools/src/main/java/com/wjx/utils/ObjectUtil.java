package com.wjx.utils;

import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 对象处理工具类
 *
 * @author Gin
 * @description
 * @date 2023/11/9 21:19
 */
public class ObjectUtil {
    public static void main(String[] args) {
        String input = null;
        stringNull2Empty(input);
    }

    //将一个对象 String 类型属性 从 null 置为 "" 打印需要
    public static <T> void stringNull2Empty(T obj) {
        //先判断入参类型 空属性
        if (obj == null) return;
        //对象clazz
        Class<?> clazz = obj.getClass();
        //String类型|基本类型 直接返回
        if (clazz.isAssignableFrom(String.class) || ClassUtils.isPrimitiveOrWrapper(obj.getClass())) {
            return;
        } else if (!ClassUtils.isPrimitiveOrWrapper(obj.getClass()) //非基本类型
                && !clazz.isAssignableFrom(Iterable.class) && !clazz.isAssignableFrom(Map.class)) {//非集合类型
            Field[] fields = obj.getClass().getDeclaredFields();
            try {
                for (Field field : fields) {
                    //设置可访问权限
                    field.setAccessible(true);
                    //获取到属性的值的Class对象
                    Class<?> fieldType = field.getType();
                    Object fieldObj = field.get(obj);

                    //String 属性
                    if (fieldType.isAssignableFrom(String.class)) {
                        Object fieldValue = field.get(obj);
                        if (fieldValue == null || fieldValue.equals("null")) {
                            field.set(obj, "");
                        }
                    } else if (field.getType().isAssignableFrom(List.class)) { //新增复杂对象 List 处理
                        //检查泛型
                        Type fieldGenericType = field.getGenericType();
                        if (fieldGenericType instanceof ParameterizedType) {
                            Method m = null;
                            if (fieldObj == null) continue;
                            m = fieldType.getDeclaredMethod("size");
                            int size = (int) m.invoke(fieldObj);//调用list的size方法，得到list的长度
                            for (int i = 0; i < size; i++) {//遍历list，调用get方法，获取list中的对象实例
                                Method listGetM = fieldType.getDeclaredMethod("get", int.class);
                                if (!listGetM.isAccessible()) {
                                    listGetM.setAccessible(true);
                                }
                                Object itmObj = listGetM.invoke(fieldObj, i);
                                stringNull2Empty(itmObj);
                            }
                        }
                    } else {//其余对象
                        stringNull2Empty(fieldObj);
                    }
                }
            } catch (Exception e) {
            }
        }//其余类型不操作
    }

}
