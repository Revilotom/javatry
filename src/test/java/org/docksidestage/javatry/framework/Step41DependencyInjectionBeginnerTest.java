/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.framework;

import org.docksidestage.bizfw.basic.objanimal.Cat;
import org.docksidestage.bizfw.di.container.MyContainer;
import org.docksidestage.bizfw.di.container.SimpleDiContainer;
import org.docksidestage.bizfw.di.nondi.NonDiDirectFirstAction;
import org.docksidestage.bizfw.di.nondi.NonDiDirectSecondAction;
import org.docksidestage.bizfw.di.usingdi.UsingDiWebFrameworkProcess;
import org.docksidestage.bizfw.di.usingdi.settings.UsingDiModule;
import org.docksidestage.javatry.colorbox.Temp;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Dependency Injection (DI) as beginner level. <br>
 * Show answer by log() or write answer on comment for question of javadoc.
 * @author jflute
 * @author Tom Oliver
 */
public class Step41DependencyInjectionBeginnerTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        Precondition
    //                                                                        ============
    /**
     * Search "Dependency Injection" by internet and learn it in thirty minutes. (study only) <br>
     * ("Dependency Injection" をインターネットで検索して、30分ほど学んでみましょう。(勉強のみ))
     */
    public void test_whatis_DependencyInjection() {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // What is Dependency Injection?
        // - - - - - (your answer?)

        // To pass the dependencies of a class as parameters to it either at instantiation (constructor injection)
        // or via setter methods (setter injection)

        //
        //
        // _/_/_/_/_/_/_/_/_/_/
    }

    // ===================================================================================
    //                                                                 Non DI Code Reading
    //                                                                 ===================
    /**
     * What is the difference between NonDiDirectFirstAction and NonDiDirectSecondAction? <br>
     * (NonDiDirectFirstAction と NonDiDirectSecondAction の違いは？)
     */
    public void test_nondi_difference_between_first_and_second() {
        // your answer? => 
        // and your confirmation code here freely

        // It has totally different method implementations.

        NonDiDirectFirstAction f = new NonDiDirectFirstAction();
        f.wakeupMe();

        log("no error");

        try{

            NonDiDirectSecondAction s = new NonDiDirectSecondAction();
            s.wakeupMe();
        }
        catch (Exception e){
            log("threw an error");
        }

    }

    /**
     * What is the difference between NonDiDirectSecondAction and NonDiFactoryMethodAction? <br>
     * (NonDiDirectSecondAction と NonDiFactoryMethodAction の違いは？)
     */
    public void test_nondi_difference_between_second_and_FactoryMethod() {
        // your answer? =>
        // In the factory method class, the repeated code for petting the dog etc. is extracted
        // into a new method called createAnimal, its the same for createSuperCarDealer


    }

    /**
     * What is the difference between NonDiFactoryMethodAction and NonDiIndividualFactoryAction? <br>
     * (NonDiFactoryMethodAction と NonDiIndividualFactoryAction の違いは？)
     */
    public void test_nondi_difference_between_FactoryMethod_and_IndividualFactory() {
        // your answer? => 
        // the createAnimal method has been extracted to the "NonDiAnimalFactory" class.
        // same with createSuperCarDealer
    }

    // ===================================================================================
    //                                                               Using DI Code Reading
    //                                                               =====================
    /**
     * What is the difference between UsingDiAccessorAction and UsingDiAnnotationAction? <br>
     * (UsingDiAccessorAction と UsingDiAnnotationAction の違いは？)
     */
    public void test_usingdi_difference_between_Accessor_and_Annotation() {
        // your answer? => 
        // and your confirmation code here freely
        // In the accessor action, the dependencies animal and supercar dealer are injected via the setter methods
        // in the annotation class the dependencies to be injected are marked with annotations.
    }

    /**
     * What is the difference between UsingDiAnnotationAction and UsingDiDelegatingAction? <br>
     * (UsingDiAnnotationAction と UsingDiDelegatingAction の違いは？)
     */
    public void test_usingdi_difference_between_Annotation_and_Delegating() {
        // your answer? => 
        // and your confirmation code here freely

        // The delegation class contains only the Bark method from the dog and the orderSuperCarMethod
        // from the superCar process.
    }

    // ===================================================================================
    //                                                           Execute like WebFramework
    //                                                           =========================
    /**
     * Execute callFriend() of accessor and annotation and delegating actions by UsingDiWebFrameworkProcess.
     * (And you can increase hit-points of sleepy cat in this method) <br>
     * (accessor, annotation, delegating の Action の callFriend() を UsingDiWebFrameworkProcess 経由で実行してみましょう。
     * (眠い猫のヒットポイントをこのメソッド内で増やしてもOK))
     */
    public void test_usingdi_UsingDiWebFrameworkProcess() throws Exception {
        // execution code here

        UsingDiModule modified = new UsingDiModule(){
            @Override
            protected Cat createPlayingCat() {
                return new Cat(){
                    @Override
                    protected void downHitPoint() {}
                };
            }
        };

        SimpleDiContainer container = SimpleDiContainer.getInstance();
        container.registerModule(modified);
        container.resolveDependency();

        UsingDiWebFrameworkProcess web = new UsingDiWebFrameworkProcess();
        web.requestAccessorCallFriend();

        web.requestAnnotationCallFriend();

        web.requestDelegatingCallFriend();
    }

    /**
     * What is concrete class of instance variable "animal" of UsingDiAnnotationAction? (when registering UsingDiModule) <br>
     * (UsingDiAnnotationAction のインスタンス変数 "animal" の実体クラスは？ (UsingDiModuleを登録した時))
     */
    public void test_usingdi_whatis_animal() {
        // your answer? => Too lazy dog
        // and your confirmation code here freely



        SimpleDiContainer container = SimpleDiContainer.getInstance();
        container.registerModule(new UsingDiModule());
        container.resolveDependency();

        UsingDiWebFrameworkProcess web = new UsingDiWebFrameworkProcess();
        web.requestAccessorCallFriend();



    }

    // ===================================================================================
    //                                                                        DI Container
    //                                                                        ============
    /**
     * What is DI container? <br>
     * (DIコンテナとは？)
     */
    public void test_whatis_DIContainer() {
        // your answer? => 
        // and your confirmation code here freely

        // A DI Container is a class that binds Classes to instances of those classes.
        // The members of the variables will be copied to the class when the DI is resolved.
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What is class or file of DI settings that defines MemberBhv class as DI component in the following Lasta Di application? <br>
     * (以下のLasta DiアプリケーションでMemberBhvクラスをDIコンポーネントとして定義しているDI設定クラスもしくはファイルは？) <br>
     * 
     * https://github.com/lastaflute/lastaflute-example-harbor
     */
    public void test_zone_search_component_on_LastaDi() {
        // your answer? => BsMemberBhv
    }

    /**
     * What is class or file of DI settings that defines MemberBhv class as DI component in the following Spring application? <br>
     * (以下のSpringアプリケーションでMemberBhvクラスをDIコンポーネントとして定義しているDI設定クラスもしくはファイルは？) <br>
     * 
     * https://github.com/dbflute-example/dbflute-example-on-springboot
     */
    public void test_zone_search_component_on_Spring() {
        // your answer? => BsMemberBhv
    }
}
