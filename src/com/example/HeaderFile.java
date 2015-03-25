package com.example;

public class HeaderFile {
    private native void  doVoid();  
    native int doShort();  
    native void doArray(Object[] o );  
    native int doInt(int i);      //byte ,short ,int,long,float,double ,boolean,char        
    native int doInt(double d);    //byte ,short ,int,long,float,double ,boolean,char  
    native int doInt(Object o);      
    native int doInt(double d1,double d2);  
    static native int doInt(double d1 ,double d2,double d3);  
    static native int doInt(double d1 ,float f,boolean b ,char[] c );    
     
    native int doInt(int[] i);  
    native int doInt(int[] i1,double[] i2 );      
    static native int doInt(int[] i1,double[] i2 ,Object[] o );  
     
    public native String doString(String s);  
    public native Object doObject(Object o );  
//    public native Enumeration doInterface(Iterator it);  
//    public native Student doStudent(Student s);  
     
//  native int[] doInt(int[] i);  //byte ,short ,int,long,float,double ,boolean,char  
    public native String[] doString(String[] s);  
    public native Object[] doObjects(Object[] o );  
//    public native Enumeration[] doInterface(Iterator[] it);  
//    public native Student[] doStudent(Student[] s);  
            
//    public native static Object doAll(int[] i , String[] s , Student[] student ); 
}
