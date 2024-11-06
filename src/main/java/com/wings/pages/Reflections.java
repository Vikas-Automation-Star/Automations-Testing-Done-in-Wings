package com.wings.pages;

public class Reflections {
    public static void main(String args[]) {


    }
}
//Class<?> getObj = Class.forName("Pages.Student");
//////             System.out.println("Class Name : "+ getObj.getName());
////             System.out.println("Simple Name : "+ getObj.getSimpleName());
//Student studentObject = (Student) getObj.newInstance();
//Method methods = getObj.getDeclaredMethod("showData",null);
//            methods.setAccessible(true);
//            methods.invoke(studentObject,null);
//
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//        e.printStackTrace();
//
//        } catch (NoSuchMethodException e) {
//        throw new RuntimeException(e);
//        } catch (InvocationTargetException e) {
//        throw new RuntimeException(e);
//        }