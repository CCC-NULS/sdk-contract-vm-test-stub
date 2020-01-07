package io.nuls.contract.util;

import io.nuls.contract.model.bo.ModelWrapper;
import io.nuls.contract.rockdb.manager.RocksDBManager;
import io.nuls.contract.rockdb.service.RocksDBService;
import io.nuls.core.model.ByteUtils;
import io.nuls.core.model.StringUtils;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

import java.lang.reflect.Field;

public class ContractDBUtil {

    private static final RuntimeSchema MODEL_WRAPPER_SCHEMA = RuntimeSchema.createFrom(ModelWrapper.class);

    public static <T> boolean putModel(String area, byte[] key, T value) {
        if (!baseCheckArea(area)) {
            return false;
        }
        if (key == null || value == null) {
            return false;
        }
        try {
            byte[] bytes = getModelSerialize(value);
            boolean result= RocksDBService.put(area, key, bytes);
             //保存集合类的属性值
            result= putBeanModel(area,key,value);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    private static <T> boolean putBeanModel(String area, byte[] key, T obj){
        Field[] fields=obj.getClass().getDeclaredFields();
        try{
            for(int i=0;i<fields.length;i++){
                if(ObjectBeanUtil.WRAPPER_CLASS.contains(fields[i].getType().getName())){
                    fields[i].setAccessible(true);
                    byte[] newKey=ByteUtils.concatenate(key,fields[i].getName().getBytes());
                    putModel(area,newKey,fields[i].get(obj));
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private static <T> T getBeanModel(String area, byte[] key,  T obj){
        Field[] fields=obj.getClass().getDeclaredFields();
        try{
            for(int i=0;i<fields.length;i++){
                if(ObjectBeanUtil.WRAPPER_CLASS.contains(fields[i].getType().getName())){
                    fields[i].setAccessible(true);
                    byte[] newKey=ByteUtils.concatenate(key,fields[i].getName().getBytes());
                    Object fieldValue=getModel(area,newKey,fields[i].getType());
                    fields[i].set(obj,fieldValue);
                }
            }
            return obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static boolean baseCheckArea(String areaName) {
        if (StringUtils.isBlank(areaName) || RocksDBManager.getTable(areaName) == null) {
            return false;
        }
        return true;
    }

    public static <T> T getModel(byte[] value, Class<T> clazz) {
        if (value == null) {
            return null;
        }
        try {
            ModelWrapper model = new ModelWrapper();
            ProtostuffIOUtil.mergeFrom(value, model, MODEL_WRAPPER_SCHEMA);
            if (clazz != null && model.getT() != null) {
                return clazz.cast(model.getT());
            }
            return (T) model.getT();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   private static <T> byte[] getModelSerialize(T value) {
        ModelWrapper modelWrapper = new ModelWrapper(value);
        byte[] bytes = ProtostuffIOUtil.toByteArray(modelWrapper, MODEL_WRAPPER_SCHEMA, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        return bytes;
    }

    public static <T> T getModel(String area, byte[] key, Class<T> clazz) {
        if (!baseCheckArea(area)) {
            return null;
        }
        if (key == null) {
            return null;
        }
        try {
            byte[] bytes = RocksDBService.get(area, key);
            if (bytes == null) {
                return null;
            }
            ModelWrapper model = new ModelWrapper();
            ProtostuffIOUtil.mergeFrom(bytes, model, MODEL_WRAPPER_SCHEMA);
            if (clazz != null && model.getT() != null) {
                //return clazz.cast(model.getT());
                //设置集合类的属性值
                return getBeanModel(area,key,clazz.cast(model.getT()));
            }
            return (T) model.getT();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
