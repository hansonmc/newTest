
package com.jlc.api.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.PropertyConfigurator;

import sun.reflect.Reflection;

/**
 * 日志类
 * @author $Author: 王健 $
 * @version $Date: 2003/11/06 13:48:58 $
 */

public class Logger{
  
  private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(""); 
  
  static {
    PropertyConfigurator.configure(Logger.class.getResource("/log4j.properties"));
  }
  
  //=========================================================================================
  public static void debug(String message){
    Class className = Reflection.getCallerClass(2);
    if (log.isDebugEnabled()){
      Throwable t = new Throwable();
      log.debug(message + " - "+getInvokeName(t,className.getName()));
    }
  }

  //=========================================================================================
  public static void info(String message){
    Class className = Reflection.getCallerClass(2);
    if (log.isInfoEnabled()){
      Throwable t = new Throwable();
      log.info(message + " - "+getInvokeName(t,className.getName()));
    }
  }

  //=========================================================================================
  public static void error(String message){
    Class className = Reflection.getCallerClass(2);
    Throwable t = new Throwable();
    log.error(message + " - "+getInvokeName(t,className.getName()));
  }  
  
  //=========================================================================================
  private static String getInvokeName(Throwable throwable,String className){
    StringWriter sw = new StringWriter();  
    PrintWriter pw = new PrintWriter(sw);
    throwable.printStackTrace(pw);
    StringBuffer out = new StringBuffer(sw.toString());
    return out.substring(out.indexOf(className),out.indexOf("\n",out.indexOf(className)));
  }
}