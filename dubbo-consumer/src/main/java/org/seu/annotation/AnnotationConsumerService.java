package org.seu.annotation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.annotation.AnnotationProviderService;
import org.springframework.stereotype.Component;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/7
 */
@Component("annotationConsumerService")
public class AnnotationConsumerService {

    @Reference
    private AnnotationProviderService annotationProviderService;

    public String doSayHi(String name) {
        return annotationProviderService.sayHi(name);
    }
}
