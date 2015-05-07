package org.javax.annotation;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/5/7
 * Time: 21:51
 */

@TriggerType(cronExpression = "0/3 * * * * ?")
public class CrawlJob {

    @TriggerMethod
    public void execute() {
        System.out.println("crawl job start...........");
    }

}
