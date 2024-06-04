package org.example.tlias.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//什么时候生效
@Target(ElementType.METHOD)//作用在类上？还是方法上？
public @interface MyLog {
}
