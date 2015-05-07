package org.javax.annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/5/7
 * Time: 21:59
 */
public class CrawlSchedulerFactoryBean extends SchedulerFactoryBean {


    protected static Log log = LogFactory.getLog(CrawlSchedulerFactoryBean.class.getName());


    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    protected void registerJobsAndTriggers() throws SchedulerException {


        String[] beanNames = applicationContext.getBeanNamesForType(Object.class);
        try {

            for (String beanName : beanNames) {

                Class<?> targetClass = applicationContext.getType(beanName);

                if (targetClass.isAnnotationPresent(TriggerType.class)) {

                    Object targetObject = applicationContext.getBean(beanName);
                    String targetMethod = "";
                    TriggerType triggerType = targetClass.getAnnotation(TriggerType.class);

                    String cronExpression = triggerType.cronExpression();
                    Method[] methods = targetClass.getDeclaredMethods();

                    for (Method method : methods) {
                        if (method.isAnnotationPresent(TriggerMethod.class)) {

                            targetMethod = method.getName();
                        }
                    }

                    registerJobs(targetObject, targetMethod, beanName, cronExpression);


                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void registerJobs(Object targetObject, String targetMethod, String beanName, String cronExpression) throws Exception {

        // 声明包装业务类
        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        jobDetailFactoryBean.setTargetObject(targetObject);
        jobDetailFactoryBean.setTargetMethod(targetMethod);
        jobDetailFactoryBean.setBeanName(beanName);
        jobDetailFactoryBean.afterPropertiesSet();

        // 获取JobDetail
        JobDetail jobDetail = jobDetailFactoryBean.getObject();

        // 声明定时器
        CronTriggerBean cronTriggerBean = new CronTriggerBean();
        cronTriggerBean.setJobDetail(jobDetail);
        cronTriggerBean.setCronExpression(cronExpression);
        cronTriggerBean.setBeanName(beanName + "CronBean");
        cronTriggerBean.afterPropertiesSet();

        // 将定时器注册到Factory
        List<Trigger> triggerList = new ArrayList<Trigger>();
        triggerList.add(cronTriggerBean);
        Trigger[] triggers = triggerList.toArray(new Trigger[triggerList.size()]);
        setTriggers(triggers);
        super.registerJobsAndTriggers();
    }
}
