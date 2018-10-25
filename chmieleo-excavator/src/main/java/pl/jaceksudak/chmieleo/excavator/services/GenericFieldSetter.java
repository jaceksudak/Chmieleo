package pl.jaceksudak.chmieleo.excavator.services;

import pl.jaceksudak.chmieleo.excavator.exceptions.ExcavatorPropertiesApplierException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class GenericFieldSetter<T> {

    private T t;

    public GenericFieldSetter(T t) {
        this.t = t;
    }

    public void setField(String propertyName, String propertyValue) {
        try {
            Field field = findField(propertyName);
            field.setAccessible(true);
            Object value = wrapValue(propertyValue, field);
            field.set(t, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new ExcavatorPropertiesApplierException(e);
        }
    }

    private Field findField(String fieldName) throws NoSuchFieldException {
        Class clazz = t.getClass();
        for (;;) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getName().equals(fieldName)) {
                    return field;
                }
            }
            if (clazz.getSuperclass() != Object.class) {
                clazz = clazz.getSuperclass();
            } else {
                throw new NoSuchFieldException();
            }
        }
    }


    private Object wrapValue(String value, Field field) {
        try {
            if (field.getType().isEnum()) {
                return Enum.valueOf((Class<Enum>) field.getType(), value);
            } else {
                Constructor<?> constructor = field.getType().getConstructor(String.class);
                return constructor.newInstance(value);
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
            throw new ExcavatorPropertiesApplierException(e);
        }
    }
}
