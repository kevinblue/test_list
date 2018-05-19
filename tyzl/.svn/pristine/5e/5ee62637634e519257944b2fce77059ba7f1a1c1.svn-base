package com.tenwa.kernal.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkflowRejectCondition {
	public String name() default "";
	public String description() default "";
	public String sourceWorkflowName() default "";
	public String rejectWorkflowName() default "";
}
