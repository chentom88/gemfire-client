package io.pivotal.gemfire.continuousQuery;

import com.gemstone.gemfire.cache.RegionService;
import com.gemstone.gemfire.cache.query.CqEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.listener.ContinuousQueryDefinition;
import org.springframework.data.gemfire.listener.ContinuousQueryListener;
import org.springframework.data.gemfire.listener.ContinuousQueryListenerContainer;
import org.springframework.data.gemfire.support.GemfireCache;
import org.springframework.stereotype.Component;

/**
 * Created by pivotal on 4/20/16.
 */


public class Container {
//    <bean id="gemfireListenerContainer" class="org.springframework.data.gemfire.listener.ContinuousQueryListenerContainer">
//    <property name="cache" ref="gemfireCache"/>
//    <property name="queryListeners">
//    <!-- set of listeners -->
//    <set>
//    <bean class="org.springframework.data.gemfire.listener.ContinuousQueryDefinition" >
//    <constructor-arg value="SELECT * from /region" />
//    <constructor-arg ref="eventListener" />
//    </bean>
//    </set>
//    </property>
//    </bean>

//    @Autowired
  //  RegionService gemfireCache;

//    public ContinuousQueryListenerContainer continuousQueryListenerContainer(){
//        System.out.println("Registering CQSpringListener");
//        ContinuousQueryListenerContainer continuousQueryListenerContainer = new ContinuousQueryListenerContainer();
//        continuousQueryListenerContainer.setCache(gemfireCache);
//        ContinuousQueryDefinition continuousQueryDefinition = new ContinuousQueryDefinition("select * from /envelope where origin='bob'", continuousQueryListener());
//        continuousQueryListenerContainer.addListener(continuousQueryDefinition);
//        continuousQueryListenerContainer().start();
//        return continuousQueryListenerContainer;
//    }
//
//
//    public ContinuousQueryListener continuousQueryListener(){
//        return new CQSpringListener();
//    }

}
