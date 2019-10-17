package org.docksidestage.bizfw.di.container;

import java.util.Map;

import org.docksidestage.bizfw.di.container.component.DiContainerModule;

public class MyContainer implements DiContainerModule {
    @Override
    public void bind(Map<Class<?>, Object> componentMap) {
        System.out.println(
                "what am i doin.."
        );

    }
}
