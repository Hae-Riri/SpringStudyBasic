package com.example.springstudybasic.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1); //true

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2); //true
    }

    @Test
    void clientBeanABTest() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, ClientBeanB.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1); //true

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2); //true

        ClientBeanB clientBeanB1 = ac.getBean(ClientBeanB.class);
        int countB1 = clientBeanB1.logic();
        assertThat(count1).isEqualTo(1); //true

        ClientBeanB clientBeanB2 = ac.getBean(ClientBeanB.class);
        int countB2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2); //true

//        System.out.println("첫번째로 요청해서 받은 clientBean에 주입된 프로토타입 빈 :" + clientBean1.prototypeBean);
//        System.out.println("두번째로 요청해서 받은 clientBean에 주입된 프로토타입 빈 :" + clientBean2.prototypeBean);

        System.out.println("처음 요청해서 받아온 clientBeanB에 주입된 프로토타입 빈 :" + clientBeanB1.prototypeBeanB);
        System.out.println("다시 요청해서 받아온 clientBeanB에 주입된 프로토타입 빈 :" + clientBeanB2.prototypeBeanB);
    }

    static class ClientBean {
//        private final PrototypeBean prototypeBean;

//        @Autowired // 생성자 하나니까 어노테이션이 없어도 의존관계 주입이 된다.
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    static class ClientBeanB {
        private final PrototypeBean prototypeBeanB;

        public ClientBeanB(PrototypeBean prototypeBean) {
            this.prototypeBeanB = prototypeBean;
        }

        public int logic() {
            prototypeBeanB.addCount();
            int count = prototypeBeanB.getCount();
            return count;
        }
    }

    @Scope("prototype")
     static class PrototypeBean {
        private int count = 0;
        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
