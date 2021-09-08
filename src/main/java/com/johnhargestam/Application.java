package com.johnhargestam;

import java.util.concurrent.TimeUnit;

public class Application {
  public static void main(String[] args) {
    var service = new HelloService();
    String hello = service.asyncHello("John", 2, TimeUnit.SECONDS).blockingSingle();
    System.out.println(hello);
  }
}
