package pers.dzj0821.SchoolLeaveSystem.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pers.dzj0821.SchoolLeaveSystem.type.UserType;

/**
 * 访问添加此注解的Controller方法需要大于等于注解value的权限
 * @author dzj0821
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserTypeRequired {
	public UserType value();
}
